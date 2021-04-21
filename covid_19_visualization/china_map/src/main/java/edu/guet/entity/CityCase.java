package edu.guet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuan
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CityCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("provinceShortName")
    private String provinceShortName;

    @TableField("cityName")
    private String cityName;

    @TableField("currentConfirmedCount")
    private Integer currentConfirmedCount;

    @TableField("confirmedCount")
    private Integer confirmedCount;

    @TableField("suspectedCount")
    private Integer suspectedCount;

    @TableField("curedCount")
    private Integer curedCount;

    @TableField("deadCount")
    private Integer deadCount;

    @TableField("updateTime")
    private Date updateTime;


}
