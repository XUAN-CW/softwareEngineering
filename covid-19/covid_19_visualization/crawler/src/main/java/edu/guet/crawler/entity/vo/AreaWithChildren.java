package edu.guet.crawler.entity.vo;

import edu.guet.crawler.entity.Area;
import lombok.Data;

import java.util.List;

/**
 * @author XUAN
 * @date 2021/5/1 - 22:42
 * @references
 * @purpose
 * @errors
 */
@Data
public class AreaWithChildren extends Area{
    List<AreaWithChildren> children;
}
