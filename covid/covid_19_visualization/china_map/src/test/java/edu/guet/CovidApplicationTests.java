package edu.guet;

import edu.guet.mapper.CityCaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    void findByUpdateTime() throws ParseException {
        String dateStr = "2021.05.20 15:15:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        System.out.println(cityCaseMapper.findByUpdateTime(date));
    }

}
