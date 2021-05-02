package edu.guet.crawler.qq_crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author XUAN
 * @date 2021/5/1 - 21:33
 * @references
 * @purpose
 * @errors
 */


@Component
public class AfterServiceStarted implements ApplicationRunner {

    @Autowired
    CovidInChina covidInChina;
    /**
     * 会在服务启动完成后立即执行
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        while (true){
            System.out.println(new Date());
            covidInChina.updateData();
            break;
//            Thread.sleep(1000*60);
        }
    }


}

