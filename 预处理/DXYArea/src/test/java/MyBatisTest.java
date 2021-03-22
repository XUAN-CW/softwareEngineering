/**
 * @author XUAN
 * @date 2020/10/7 - 20:34
 * @references
 * @purpose
 * @errors
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import domain.DXYArea;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyBatisTest {

    @Test
    //处理的资料：https://github.com/BlankerL/DXY-COVID-19-Data/releases/download/2021.01.23/DXYArea.json
    public void dxyarea() throws IOException {

        Map<Integer,String> integerStringMap = new HashMap<>();
        JSONArray objects = JSON.parseArray(SaveAndRead.read("DXYArea.json"));
        System.out.println(objects.size());
        for (int i = 0; i <objects.size() ; i++) {
            JSONObject key = (JSONObject)objects.get(i);
            String country = (String)key.get("countryEnglishName");
            if ("China".equals(country)){
                System.out.println("进度："+i+"/"+objects.size());
                try {
                    integerStringMap.put((Integer) key.get("locationId"),(String) key.get("provinceShortName"));
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("有问题的数据："+i);
                }
            }
        }

        integerStringMap.remove(951001);

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//在此设置为 true ，后面就不用手动提交


//        JSONArray objects = JSON.parseArray(SaveAndRead.read("DXYArea.json"));
        System.out.println(objects.size());
        for (int i = 0; i <objects.size() ; i++) {
            JSONObject key = (JSONObject)objects.get(i);
            if (integerStringMap.containsKey((Integer) key.get("locationId"))){
                System.out.println("进度："+i+"/"+objects.size());
                try {
                    DXYArea area = new DXYArea();
                    area.setConfirmedCount((int) key.get("confirmedCount"));
                    area.setCuredCount((int) key.get("curedCount"));
                    area.setCurrentConfirmedCount((int) key.get("currentConfirmedCount"));
                    area.setDeadCount((int) key.get("deadCount"));
                    area.setLocationId((int) key.get("locationId"));
                    area.setProvinceShortName((String) key.get("provinceShortName"));
                    area.setSuspectedCount((int) key.get("suspectedCount"));
                    long updateTime = (long) key.get(("updateTime"));
                    Date date = new Date();
                    date.setTime(updateTime);
                    area.setUpdateTime(date);
                    sqlSession.insert("DXYAreaMapper.save",area);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("有问题的数据："+i);
                }
            }
        }
        //释放资源
        sqlSession.close();
    }
}
