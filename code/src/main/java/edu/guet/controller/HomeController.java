package edu.guet.controller;

import edu.guet.domain.DXYArea;
import edu.guet.mapper.DXYAreaMapper;
import edu.guet.service.DXYAreaService;
import edu.guet.vo.HomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/3/14 - 18:15
 * @references
 * @purpose
 * @errors
 */
@Controller
@CrossOrigin
public class HomeController {
    @Autowired
    private DXYAreaService dxyAreaService;

    //http://127.0.0.1:8845/queryCurrentCase
    @RequestMapping("/queryCurrentCase")
    @ResponseBody
    public HomeVO queryCurrentCase(){
        HomeVO homeVO = dxyAreaService.queryCurrentCase();

        return homeVO;
    }

}
