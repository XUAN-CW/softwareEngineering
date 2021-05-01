package edu.guet.crawler.entity;

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
 * @since 2021-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @TableField("parentId")
    private Long parentId;

    @TableField("updateTime")
    private Date updateTime;

    @TableField("currentConfirm")
    private Integer currentConfirm;

    private Integer confirm;

    private Integer suspect;

    private Integer cure;

    private Integer dead;


}
