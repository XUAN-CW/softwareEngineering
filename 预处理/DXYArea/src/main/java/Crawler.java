import Utils.RegionMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import domain.DXYArea;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XUAN
 * @date 2021/3/22 - 11:34
 * @references
 * @purpose
 * @errors
 */
public class Crawler {
    /**
     * @param url
     * @return
     */
    public static String getHTML(String url){
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

    public static void crawlingAndInsert() throws ParseException, IOException {
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
        JSONArray children = zero.getJSONArray("children");

        //获取更新时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date =  sdf.parse(data.getString("lastUpdateTime"));//string转date

        //转换成 DXYArea 对象并插入数据库
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//在此设置为 true ，后面就不用手动提交
        for (int i = 0; i < children.size(); i++) {
            DXYArea area = new DXYArea();
            JSONObject key = (JSONObject) children.get(i);
            area.setProvinceShortName(key.getString("name"));
            area.setLocationId(RegionMap.regionProvMap.get( key.getString("name")));
            //
            JSONObject total = key.getJSONObject("total");
            area.setConfirmedCount(total.getInteger("confirm"));
            area.setCuredCount(total.getInteger("heal"));
            area.setCurrentConfirmedCount(total.getInteger("nowConfirm"));
            area.setDeadCount(total.getInteger("dead"));
            area.setSuspectedCount(total.getInteger("suspect"));
            area.setUpdateTime(date);
            //插入的时候极有可能抛主键 Duplicate entry 的异常
            //抛了异常是正常的，说明数据库里已经有这条数据了
            //已经有了我们就不再做处理，抛完异常什么都不做
            try {
                sqlSession.insert("DXYAreaMapper.save",area);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        sqlSession.close();
    }

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        while (true){

            crawlingAndInsert();
            Thread.sleep(1000*60*60);//每小时爬取一次
        }
    }
}
