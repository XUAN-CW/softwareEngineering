package edu.guet.crawler.api;

import edu.guet.crawler.entity.vo.AreaWithChildren;
import lombok.Data;

import java.util.Date;

/**
 * @author XUAN
 * @date 2021/5/2 - 0:40
 * @references
 * @purpose
 * @errors
 */
@Data
public class RedisInterface {
    public static Date lastUpdateTime = new Date();
    public static AreaWithChildren chinaWithChildren = null;
}
