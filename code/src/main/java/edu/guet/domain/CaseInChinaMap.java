package edu.guet.domain;

import lombok.Data;

/**
 * @author XUAN
 * @date 2021/3/14 - 20:11
 * @references
 * @purpose
 * @errors
 */
@Data
public class CaseInChinaMap {
    String area; //地区
    int confirmed; //确诊人数

    public CaseInChinaMap(String area, int confirmed) {
        this.area = area;
        this.confirmed = confirmed;
    }

}
