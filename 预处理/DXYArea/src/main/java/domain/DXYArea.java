package domain;

import lombok.Data;

import java.util.Date;

/**
 * @author XUAN
 * @date 2021/3/16 - 13:15
 * @references
 * @purpose
 * @errors
 */
@Data
public class DXYArea {
    int locationId;//城市编号，中国大陆城市编号为邮编，中国大陆以外城市编号暂不知规则
    String provinceShortName;//省份、地区或直辖市简称
    int currentConfirmedCount;//现存确诊人数，值为confirmedCount - curedCount - deadCount
    int confirmedCount;//累计确诊人数
    int suspectedCount;//疑似感染人数
    int curedCount;//治愈人数
    int deadCount;//死亡人数
    Date updateTime;//数据更新时间
}
