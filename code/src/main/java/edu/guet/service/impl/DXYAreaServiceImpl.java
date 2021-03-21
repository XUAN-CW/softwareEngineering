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
        homeVO.setDxyAreaList(dxyAreas);
        return homeVO;
    }
}
