package edu.guet.crawler.qq_crawler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.guet.crawler.entity.Area;
import edu.guet.crawler.entity.vo.AreaWithChildren;
import edu.guet.crawler.mapper.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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


    @Autowired
    CovidInChina covidInChina;

    void insertIntoMysqlAndSetIdIfWithoutId(Area area){
        //没有 id 就给他设置一个 id
        if(area.getId() == null){
            //根据 parentId 和 name 查找数据库，有没有这个地方
            QueryWrapper<Area> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("parentId", area.getParentId()).eq("name",area.getName());
            List<Area> areas = areaMapper.selectList(queryWrapper2);

            //没有这个地方,随机生成一个 id
            if(areas.isEmpty()){
                for (;;){
                    area.setId((long) (1000000 + new Random().nextInt(9000000)));
                    QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("id", area.getId());
                    List<Area> user = areaMapper.selectList(queryWrapper);
                    if(user.isEmpty()){
                        break;
                    }
                }
            }else {// 有这个地方,设置为这个地方的 id
                area.setId(areas.get(0).getId());
            }
        }
        areaMapper.insert(area);
    }

    void insertAreaWithChildren(AreaWithChildren parent){
        insertIntoMysqlAndSetIdIfWithoutId(parent);
        List<AreaWithChildren> children=parent.getChildren();
        if(null != children && !children.isEmpty()){
            for (int i = 0; i < children.size(); i++) {
                AreaWithChildren child = children.get(i);
                child.setParentId(parent.getId());
                insertAreaWithChildren(child);
            }
        }
    }
    /**
     * 会在服务启动完成后立即执行
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        while (true){
            System.out.println(new Date());
            AreaWithChildren areaWithChildren = covidInChina.currentChinaData();
            System.out.println("covidInChina:"+JSONObject.toJSONString(areaWithChildren));

            insertAreaWithChildren(areaWithChildren);

//            break;
        }
    }


}

