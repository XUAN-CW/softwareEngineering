package domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author XUAN
 * @date 2021/4/20 - 21:24
 * @references
 * @purpose
 * @errors
 */
@Data
public class Area {
    String areaName;//省份、地区或直辖市简称
    Date updateTime;//数据更新时间

    int currentConfirmedCount;//现存确诊人数，值为confirmedCount - curedCount - deadCount
    int confirmedCount;//累计确诊人数
    int suspectedCount;//疑似感染人数
    int curedCount;//治愈人数
    int deadCount;//死亡人数
}
