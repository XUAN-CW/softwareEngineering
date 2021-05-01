package edu.guet.crawler.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author XUAN
 * @date 2021/4/21 - 12:17
 * @references
 * @purpose
 * @errors
 */
@Configuration
@MapperScan("edu.guet.crawler.mapper")
public class MybatisPlusConfig {
}
