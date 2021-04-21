package edu.guet.mapper;

import edu.guet.entity.CityCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuan
 * @since 2021-04-21
 */
@Repository
public interface CityCaseMapper extends BaseMapper<CityCase> {
    List<CityCase> findByUpdateTime(Date date);
}
