package edu.guet.vo;

import edu.guet.domain.DXYArea;
import lombok.Data;

import java.util.List;

/**
 * @author XUAN
 * @date 2021/3/21 - 21:34
 * @references
 * @purpose
 * @errors
 */
@Data
public class HomeVO {
    int allCurrentConfirmedCount;//现存确诊人数，值为confirmedCount - curedCount - deadCount
    int allConfirmedCount;//累计确诊人数
    int allSuspectedCount;//疑似感染人数
    int allCuredCount;//累计治愈人数
    int allDeadCount;//累计死亡人数
    List<DXYArea> dxyAreaList;//各省情况
}
