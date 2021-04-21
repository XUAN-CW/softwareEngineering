package edu.guet.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import edu.guet.entity.CityCase;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/21 - 16:10
 * @references
 * @purpose
 * @errors
 */
@Data
public class ProvinceVO {

    private String provinceShortName;
    private List<CityCase> cityList;

    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Date updateTime;
}
