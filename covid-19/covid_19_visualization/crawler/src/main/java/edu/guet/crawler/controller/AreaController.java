package edu.guet.crawler.controller;



import edu.guet.common.R;
import edu.guet.crawler.qq_crawler.CovidInChina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuan
 * @since 2021-05-01
 */
@RestController
@RequestMapping("/crawler/area")
public class AreaController {

    @Autowired
    CovidInChina covidInChina;

    //http://localhost:8801//crawler/area/getChina
    @GetMapping("getChina")
    public R getChina(){
        return R.ok().data("china",covidInChina.currentChinaData());
    }

}

