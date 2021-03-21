import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/3/2 - 12:04
 * @references
 *   [Java 中 JSON 的使用](https://www.runoob.com/w3cnote/java-json-instro.html)
 * @purpose
 * @errors
 */
public class FastjsonDemo {


    @Test
    public void t(){
        //创建对象
        Student student = new Student();
        student.setId(1);
        student.setName("name");
        List<String> list = new ArrayList<String>();
        list.add("course1");
        list.add("course2");
        student.setCourses(list);

        // 对象转字符串
        String jsonStr = JSONObject.toJSONString(student);
        System.out.println(jsonStr);

        // 字符串转对象
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.get("id"));
        System.out.println(jsonObject.get("name"));
        List<String> course = JSON.parseArray(jsonObject.getJSONArray("courses").toJSONString(),String.class);
        course.forEach(System.out::println);

        //输入不存在属性
        System.out.println(jsonObject.get("123"));

    }

    @Test
    public void array(){
        ArrayList<String> stringList = new ArrayList<String>(16);
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(stringList);
        String listJson = jsonArray.toString();
        System.out.println(listJson);
    }

    @Test
    public void t2(){
        List<Teacher> teacherList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            teacherList.add(new Teacher("jobNumber"+i,"name"+i));
        }

        teacherList.forEach(student -> {
//            System.out.println(student.jobNumber + student.name);
        });

        //对象转字符串
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(teacherList);
        String listJson = jsonArray.toString();
        System.out.println(listJson);

//        字符串转对象
        JSONArray objects = JSON.parseArray(listJson);
        for (int i = 0; i <objects.size() ; i++) {
            JSONObject key = (JSONObject)objects.get(i);
            String name = (String)key.get("name");
            System.out.println(name);
        }
    }

    @Test
    public void covid(){

//        字符串转对象
        JSONArray objects = JSON.parseArray(SaveAndRead.read("DXYArea.json"));
        System.out.println(objects.size());
        for (int i = 0; i <objects.size() ; i++) {
            JSONObject key = (JSONObject)objects.get(i);
            String name = (String)key.get("provinceShortName");
            System.out.println(name);
        }
    }
}
