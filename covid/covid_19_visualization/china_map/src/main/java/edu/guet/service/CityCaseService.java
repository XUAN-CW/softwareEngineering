package edu.guet.service;

import edu.guet.entity.CityCase;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.guet.entity.vo.ChinaVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuan
 * @since 2021-04-21
 */
public interface CityCaseService extends IService<CityCase> {

    ChinaVO queryOneDayCase(Date date);
}
