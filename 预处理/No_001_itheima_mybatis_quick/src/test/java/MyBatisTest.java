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
    //插入操作
    public void test2() throws IOException {

        //模拟user对象
        DXYArea area = new DXYArea();
        //不改代码就运行，第二此运行时会报错，因为 id 为主键
        area.setConfirmedCount(1);
        area.setCuredCount(2);
        area.setCurrentConfirmedCount(3);
        area.setDeadCount(4);
        area.setLocationId(5);
        area.setProvinceShortName("6");
        area.setSuspectedCount(7);
        area.setUpdateTime(new Date());

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//在此设置为 true ，后面就不用手动提交
        //执行操作  参数：namespace+id
        sqlSession.insert("DXYAreaMapper.save",area);

        //mybatis执行更新操作  提交事务
        //sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    @Test
    //插入操作
    public void test3() throws IOException {

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
        String jsonString = JSON.toJSONString(integerStringMap);
        SaveAndRead.save("place.txt",jsonString);
    }

    @Test
    public void test4() throws IOException {

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//在此设置为 true ，后面就不用手动提交


        JSONArray objects = JSON.parseArray(SaveAndRead.read("DXYArea.json"));
        System.out.println(objects.size());
        for (int i = 0; i <objects.size() ; i++) {
            JSONObject key = (JSONObject)objects.get(i);
            String country = (String)key.get("countryEnglishName");
            if ("China".equals(country)){
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

    @Test
    //插入操作
    public void test5() throws IOException {

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
