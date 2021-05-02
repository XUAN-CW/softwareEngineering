package edu.guet.crawler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.guet.crawler.entity.Area;
import edu.guet.crawler.entity.vo.AreaWithChildren;
import edu.guet.crawler.mapper.AreaMapper;
import edu.guet.crawler.service.AreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuan
 * @since 2021-05-01
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {


    private void insertIntoMysqlAndSetIdIfWithoutId(Area area){
        //没有 id 就给他设置一个 id
        if(area.getId() == null){
            //根据 parentId 和 name 查找数据库，有没有这个地方
            QueryWrapper<Area> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("parentId", area.getParentId()).eq("name",area.getName());
            List<Area> areas = baseMapper.selectList(queryWrapper2);

            //没有这个地方,随机生成一个 id
            if(areas.isEmpty()){
                for (;;){
                    area.setId((long) (1000000 + new Random().nextInt(9000000)));
                    QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("id", area.getId());
                    List<Area> user = baseMapper.selectList(queryWrapper);
                    if(user.isEmpty()){
                        break;
                    }
                }
            }else {// 有这个地方,设置为这个地方的 id
                area.setId(areas.get(0).getId());
            }
        }
        baseMapper.insert(area);
    }

    @Override
    public void insertAreaWithChildren(AreaWithChildren parent){
        insertIntoMysqlAndSetIdIfWithoutId(parent);
        List<AreaWithChildren> children=parent.getChildren();
        if(null != children && !children.isEmpty()){
            for (AreaWithChildren child : children) {
                child.setParentId(parent.getId());
                insertAreaWithChildren(child);
            }
        }
    }
}
