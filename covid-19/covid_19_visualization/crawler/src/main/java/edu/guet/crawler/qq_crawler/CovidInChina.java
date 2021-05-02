package edu.guet.crawler.qq_crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.guet.crawler.entity.Area;
import edu.guet.crawler.entity.vo.AreaWithChildren;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/5/2 - 9:47
 * @references
 * @purpose
 * @errors
 */
@Component
public class CovidInChina {


    private String getHTML(String url){
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

    private JSONObject crawlingAndToJSONObject() throws ParseException {
        //爬取资源并解析成 json 格式
        JSONObject data=null;
        try {
            String inews = getHTML("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5");
            inews = inews.replace("\\\"","\"");
            inews = inews.replaceFirst("\"data\":\"","\"data\":");
            inews = inews.replace("]}]}]}\"}","]}]}]}}");
            JSONObject all = (JSONObject) JSONObject.parse(inews);
            data = (JSONObject)all.get("data");
        }catch (Exception e){
            e.printStackTrace();
        }

        return data;
    };




    private Area setTotal(Area area,JSONObject total){
        area.setCurrentConfirm(total.getInteger("nowConfirm"));
        area.setConfirm(total.getInteger("confirm"));
        area.setSuspect(total.getInteger("suspect"));
        area.setCure(total.getInteger("heal"));
        area.setDead(total.getInteger("dead"));
        return area;
    }

    private AreaWithChildren parse(JSONObject data) throws ParseException {
        JSONArray areaTree = (JSONArray)data.get("areaTree");
        JSONObject zero = (JSONObject)areaTree.get(0);
        JSONArray provinces = zero.getJSONArray("children");

//        System.out.println(JSONObject.toJSONString(data));
        //获取更新时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date updateTime =  sdf.parse(data.getString("lastUpdateTime"));//string转date

        AreaWithChildren chinaArea = new AreaWithChildren();
        chinaArea.setId((long) 86);
        chinaArea.setName("中国");
        chinaArea.setParentId(null);
        chinaArea.setUpdateTime(updateTime);
        setTotal(chinaArea,(JSONObject)data.get("chinaTotal"));




        List<AreaWithChildren> provinceList = new ArrayList<>();
        for (int i = 0; i < provinces.size(); i++) {
            AreaWithChildren province = new AreaWithChildren();
            JSONObject provinceKey = (JSONObject) provinces.get(i);
            province.setName(provinceKey.getString("name"));
            province.setParentId((long) 86);
            province.setUpdateTime(updateTime);
            setTotal(province,(JSONObject) provinceKey.get("total"));

            List<AreaWithChildren> cityList = new ArrayList<>();
            JSONArray cities = provinceKey.getJSONArray("children");
            for (int j = 0; j < cities.size(); j++) {
                AreaWithChildren city = new AreaWithChildren();
                JSONObject cityKey = (JSONObject) cities.get(j);
                city.setName(cityKey.getString("name"));
                city.setUpdateTime(updateTime);
                setTotal(city,(JSONObject) cityKey.get("total"));

                cityList.add(city);
            }
            province.setChildren(cityList);
            provinceList.add(province);
        }

        chinaArea.setChildren(provinceList);
        return chinaArea;
    }





    public AreaWithChildren currentChinaData(){
        AreaWithChildren areaWithChildren=null;
        try {
            JSONObject data = crawlingAndToJSONObject();
            areaWithChildren= parse(data);

        }catch (Exception e){
            e.printStackTrace();
        }
        return areaWithChildren;
    }
}
