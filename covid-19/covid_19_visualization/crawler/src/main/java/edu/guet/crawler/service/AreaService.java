package edu.guet.crawler.service;

import edu.guet.crawler.entity.Area;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.guet.crawler.entity.vo.AreaWithChildren;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuan
 * @since 2021-05-01
 */
public interface AreaService extends IService<Area> {
    void insertAreaWithChildren(AreaWithChildren parent);
}
