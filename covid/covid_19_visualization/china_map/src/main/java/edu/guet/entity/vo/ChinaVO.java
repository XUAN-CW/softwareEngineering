package edu.guet.entity.vo;

import edu.guet.entity.CityCase;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/21 - 16:12
 * @references
 * @purpose
 * @errors
 */
@Data
public class ChinaVO {

    private List<ProvinceVO> provinceVOList;

    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Date updateTime;
}
