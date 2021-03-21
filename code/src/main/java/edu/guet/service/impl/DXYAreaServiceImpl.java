package edu.guet.service.impl;

import edu.guet.domain.DXYArea;
import edu.guet.mapper.DXYAreaMapper;
import edu.guet.service.DXYAreaService;
import edu.guet.vo.HomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XUAN
 * @date 2021/3/22 - 0:08
 * @references
 * @purpose
 * @errors
 */
@Service
public class DXYAreaServiceImpl implements DXYAreaService {

    @Autowired
    private DXYAreaMapper dxyAreaMapper;

    public HomeVO queryCurrentCase() {
        HomeVO homeVO = new HomeVO();
        List<DXYArea> dxyAreas = dxyAreaMapper.queryCurrentCase();

        //计算国内整体情况
        homeVO.setAllCurrentConfirmedCount(dxyAreas.stream().mapToInt(DXYArea::getCurrentConfirmedCount).sum());
        homeVO.setAllConfirmedCount(dxyAreas.stream().mapToInt(DXYArea::getConfirmedCount).sum());
        homeVO.setAllSuspectedCount(dxyAreas.stream().mapToInt(DXYArea::getSuspectedCount).sum());
        homeVO.setAllCuredCount(dxyAreas.stream().mapToInt(DXYArea::getCuredCount).sum());
        homeVO.setAllDeadCount(dxyAreas.stream().mapToInt(DXYArea::getDeadCount).sum());

        System.out.println(homeVO);
        homeVO.setDxyAreaList(dxyAreas);
        return homeVO;
    }
}
