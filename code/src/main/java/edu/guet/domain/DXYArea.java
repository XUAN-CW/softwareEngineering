package edu.guet.domain;

import java.util.Date;

/**
 * @author XUAN
 * @date 2021/3/16 - 13:15
 * @references
 * @purpose
 * @errors
 */
public class DXYArea {
    int locationId;//城市编号，中国大陆城市编号为邮编，中国大陆以外城市编号暂不知规则
    String provinceShortName;//省份、地区或直辖市简称
    int currentConfirmedCount;//现存确诊人数，值为confirmedCount - curedCount - deadCount
    int confirmedCount;//累计确诊人数
    int suspectedCount;//疑似感染人数
    int curedCount;//治愈人数
    int deadCount;//死亡人数
    Date updateTime;//数据更新时间

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
    }

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
