package edu.guet.crawler;

import edu.guet.crawler.mapper.AreaMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrawlerApplicationTests {

    @Autowired
    AreaMapper areaMapper;


    @Test
    void contextLoads() {
        System.out.println(areaMapper.selectList(null));
    }

}
