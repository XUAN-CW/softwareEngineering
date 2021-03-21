package edu.guet.mapper;

import edu.guet.domain.DXYArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author XUAN
 * @date 2021/3/21 - 23:07
 * @references
 * @purpose
 * @errors
 */
@Mapper
public interface DXYAreaMapper {
    List<DXYArea> queryCurrentCase();
}
