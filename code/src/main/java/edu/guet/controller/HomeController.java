package edu.guet.controller;

import edu.guet.domain.CaseInChinaMap;
import edu.guet.vo.HomeVO;
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
    // http://localhost:8845/getCases
    @GetMapping("/getCases")
    @ResponseBody
    public HomeVO HomeVO (){
        HomeVO homeVO = new HomeVO();
        CaseInChinaMap caseInChinaMap1 = new CaseInChinaMap("湖北",100);
        CaseInChinaMap caseInChinaMap2 = new CaseInChinaMap("江西",200);
        CaseInChinaMap caseInChinaMap3 = new CaseInChinaMap("广西",300);
        List<CaseInChinaMap> caseInChinaMaps = new LinkedList<>();
        caseInChinaMaps.add(caseInChinaMap1);
        caseInChinaMaps.add(caseInChinaMap2);
        caseInChinaMaps.add(caseInChinaMap3);

        homeVO.setCaseInChinaMapList(caseInChinaMaps);

        return homeVO;
    }

}
