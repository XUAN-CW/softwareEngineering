package edu.guet;

import edu.guet.mapper.CityCaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CovidApplicationTests {

    @Autowired
    CityCaseMapper cityCaseMapper;

    @Test
    void findAll() {
        System.out.println(cityCaseMapper.selectList(null));
    }

    @Test
    void findByUpdateTime(){
        System.out.println(cityCaseMapper.findByUpdateTime());
    }

}
