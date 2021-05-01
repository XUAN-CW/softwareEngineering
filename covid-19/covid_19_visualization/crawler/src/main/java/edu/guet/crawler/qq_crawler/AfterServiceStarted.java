package edu.guet.crawler.qq_crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.guet.crawler.entity.Area;
import edu.guet.crawler.entity.vo.AreaWithChildren;
import edu.guet.crawler.mapper.AreaMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author XUAN
 * @date 2021/5/1 - 21:33
 * @references
 * @purpose
 * @errors
 */


@Component
public class AfterServiceStarted implements ApplicationRunner {

    @Autowired
    AreaMapper areaMapper;
    /**
     * 会在服务启动完成后立即执行
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

//        System.out.println(areaMapper.selectList(null));

        crawlingAndInsert();
    }


    public String getHTML(String url){
        System.out.println("当前 url: "+url);
        String html=null;
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            //2.创建get请求，相当于在浏览器地址栏输入网址
            HttpGet request = new HttpGet(url);
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);
            response.addHeader("currentURL",url);
            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "utf-8");
//                System.out.println(html);
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
//                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return html;
    }

    public void crawlingAndInsert() throws IOException, ParseException {
        //爬取资源并解析成 json 格式
        String inews = getHTML("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5");
        inews = inews.replace("\\\"","\"");
        inews = inews.replaceFirst("\"data\":\"","\"data\":");
        inews = inews.replace("]}]}]}\"}","]}]}]}}");

        //解析 json 到各省
        JSONObject all = (JSONObject) JSONObject.parse(inews);
        JSONObject data = (JSONObject)all.get("data");
        JSONArray areaTree = (JSONArray)data.get("areaTree");
        JSONObject zero = (JSONObject)areaTree.get(0);
        JSONArray provinces = zero.getJSONArray("children");


        //获取更新时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date lastUpdateTime =  sdf.parse(data.getString("lastUpdateTime"));//string转date

        Area chinaArea = new Area();
        chinaArea.setId((long) 86);
        chinaArea.setName("中国");
        chinaArea.setParentId(null);
        chinaArea.setUpdateTime(lastUpdateTime);
        setTotal(chinaArea,(JSONObject)data.get("chinaTotal"));
        areaMapper.insert(chinaArea);




        List<AreaWithChildren> provinceList = new ArrayList<>();
        for (int i = 0; i < provinces.size(); i++) {
            Area province = new Area();
            JSONObject provinceKey = (JSONObject) provinces.get(i);
            province.setName(provinceKey.getString("name"));
            province.setParentId((long) 86);
            province.setUpdateTime(lastUpdateTime);
            setTotal(province,(JSONObject) provinceKey.get("total"));
//            System.out.println("a---a"+JSONObject.toJSONString(province));
//            System.err.println("a---a"+JSONObject.toJSONString(province));


            Area currentProvince = insertIntoMysql(province);
            List<AreaWithChildren> cityList = new ArrayList<>();
            JSONArray cities = provinceKey.getJSONArray("children");
            for (int j = 0; j < cities.size(); j++) {
                Area city = new Area();
                JSONObject cityKey = (JSONObject) cities.get(j);
                city.setName(cityKey.getString("name"));
                city.setParentId(currentProvince.getId());
                city.setUpdateTime(lastUpdateTime);
                setTotal(city,(JSONObject) cityKey.get("total"));
//                System.err.println(JSONObject.toJSONString(city));
                AreaWithChildren cityAWC = new AreaWithChildren();
                cityAWC.setCurrentArea(insertIntoMysql(city));
                cityList.add(cityAWC);
            }
            AreaWithChildren provinceWithChildren=new AreaWithChildren();
            provinceWithChildren.setCurrentArea(currentProvince);
            provinceWithChildren.setChildren(cityList);
            provinceList.add(provinceWithChildren);
        }

        AreaWithChildren chinaWithChildren=new AreaWithChildren();
        chinaWithChildren.setCurrentArea(chinaArea);
        chinaWithChildren.setChildren(provinceList);
//        System.out.println(JSONObject.toJSONString(chinaWithChildren));

    }

    Area setTotal(Area area,JSONObject total){
//        System.out.println(JSONObject.toJSONString(provinceTotal));
        area.setCurrentConfirm(total.getInteger("nowConfirm"));
        area.setConfirm(total.getInteger("confirm"));
        area.setSuspect(total.getInteger("suspect"));
        area.setCure(total.getInteger("heal"));
        area.setDead(total.getInteger("dead"));
        return area;
    }



    Area insertIntoMysql(Area area){
        QueryWrapper<Area> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("parentId", area.getParentId()).eq("name",area.getName());
        List<Area> areas = areaMapper.selectList(queryWrapper2);

        if(areas.isEmpty()){
//            System.out.println("isEmpty");
            for (;;){
                area.setId((long) (1000000 + new Random().nextInt(9000000)));
                QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", area.getId());
                List<Area> user = areaMapper.selectList(queryWrapper);
                if(user.isEmpty()){
                    break;
                }
            }
        }else {
//            System.out.println("------------------");
            area.setId(areas.get(0).getId());
        }


//        System.out.println(JSONObject.toJSONString(area));
        areaMapper.insert(area);

        return area;
    }
}

