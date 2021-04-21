package edu.guet.controller;


import edu.guet.R;
import edu.guet.service.CityCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuan
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/city-case")
public class CityCaseController {

    @Autowired
    CityCaseService cityCaseService;

    //http://localhost:8801/city-case/queryAWeek
    @RequestMapping("queryAWeek")
    R queryAWeek(){
        return R.ok();
    }
}

