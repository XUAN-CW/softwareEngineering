package edu.guet.service.impl;

import com.alibaba.fastjson.JSONArray;
import edu.guet.entity.CityCase;
import edu.guet.entity.vo.ChinaVO;
import edu.guet.entity.vo.ProvinceVO;
import edu.guet.mapper.CityCaseMapper;
import edu.guet.service.CityCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuan
 * @since 2021-04-21
 */
@Service
public class CityCaseServiceImpl extends ServiceImpl<CityCaseMapper, CityCase> implements CityCaseService {


    @Override
    public ChinaVO queryOneDayCase(Date date) {
        Map<String,ProvinceVO> provinceVOMap = new HashMap<>();
        List<CityCase> cityCaseList = baseMapper.findByUpdateTime(date);
        cityCaseList.forEach((cityCase)->{
            String provinceShortName = cityCase.getProvinceShortName();
            ProvinceVO currentProvinceVO = provinceVOMap.get(provinceShortName);
            if(null == currentProvinceVO){
                currentProvinceVO =  new ProvinceVO();
                List<CityCase> cityBelongToThisProvince = new ArrayList<>();
                cityBelongToThisProvince.add(cityCase);
                //
                currentProvinceVO.setUpdateTime(cityCase.getUpdateTime());
                currentProvinceVO.setProvinceShortName(cityCase.getProvinceShortName());
                currentProvinceVO.setCityList(cityBelongToThisProvince);
                currentProvinceVO.setConfirmedCount(cityCase.getConfirmedCount());
                currentProvinceVO.setCuredCount(cityCase.getCuredCount());
                currentProvinceVO.setCurrentConfirmedCount(cityCase.getCurrentConfirmedCount());
                currentProvinceVO.setDeadCount(cityCase.getDeadCount());
                currentProvinceVO.setSuspectedCount(cityCase.getSuspectedCount());
                //
                provinceVOMap.put(cityCase.getProvinceShortName(),currentProvinceVO);
            }else {
                currentProvinceVO.getCityList().add(cityCase);
                currentProvinceVO.setConfirmedCount(currentProvinceVO.getConfirmedCount()+cityCase.getConfirmedCount());
                currentProvinceVO.setCuredCount(currentProvinceVO.getCuredCount()+cityCase.getCuredCount());
                currentProvinceVO.setCurrentConfirmedCount(currentProvinceVO.getCurrentConfirmedCount()+cityCase.getCurrentConfirmedCount());
                currentProvinceVO.setDeadCount(currentProvinceVO.getDeadCount()+cityCase.getDeadCount());
                currentProvinceVO.setSuspectedCount(currentProvinceVO.getSuspectedCount()+cityCase.getSuspectedCount());
            }
        });
        ChinaVO chinaVO = new ChinaVO();
        chinaVO.setProvinceVOList(new ArrayList<>());
        chinaVO.setConfirmedCount(0);
        chinaVO.setCuredCount(0);
        chinaVO.setCurrentConfirmedCount(0);
        chinaVO.setDeadCount(0);
        chinaVO.setSuspectedCount(0);

        provinceVOMap.forEach((provinceShortName,provinceObject)->{
            chinaVO.setUpdateTime(provinceObject.getUpdateTime());
            chinaVO.getProvinceVOList().add(provinceObject);
            chinaVO.setConfirmedCount(chinaVO.getConfirmedCount()+provinceObject.getConfirmedCount());
            chinaVO.setCuredCount(chinaVO.getCuredCount()+provinceObject.getCuredCount());
            chinaVO.setCurrentConfirmedCount(chinaVO.getCurrentConfirmedCount()+provinceObject.getCurrentConfirmedCount());
            chinaVO.setDeadCount(chinaVO.getDeadCount()+provinceObject.getDeadCount());
            chinaVO.setSuspectedCount(chinaVO.getSuspectedCount()+provinceObject.getSuspectedCount());
        });
//        System.out.println(JSONArray.toJSON(chinaVO));








        return chinaVO;
    }
}
