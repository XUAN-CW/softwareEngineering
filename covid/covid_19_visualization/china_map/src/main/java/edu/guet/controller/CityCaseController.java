package edu.guet.controller;


import com.alibaba.fastjson.JSONArray;
import edu.guet.R;
import edu.guet.entity.vo.ChinaVO;
import edu.guet.service.CityCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<ChinaVO> chinaVOList = new ArrayList<>();
        Date now = new Date();
        for (int i=0;i<7;i++){
            ChinaVO oneDay = cityCaseService.queryOneDayCase(new Date(now.getTime() - i *1000*60*60*24));
            chinaVOList.add(oneDay);
        }
        return R.ok().data("week",chinaVOList);
    }
}

