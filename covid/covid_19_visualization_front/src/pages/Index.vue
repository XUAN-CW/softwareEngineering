<template>
  <div>
    <Overview></Overview>
    <ChinaMap :mapData="mapData" :key="JSON.stringify(mapData)"></ChinaMap>
    <roma :generalSituation="show.areaCaseOnAWeek" :key="JSON.stringify(show.areaCaseOnAWeek) + 3"></roma>
    <DetailVisualMapHorizontal :mapData="mapData" :key="JSON.stringify(mapData) + 1"></DetailVisualMapHorizontal>
    <Pie4 :mapData="mapData" :key="JSON.stringify(mapData) + 2"></Pie4>
  </div>
</template>

<script>
import axios from "axios";
import ChinaMap from "@/components/ChinaMap";
import Overview from "@/components/Overview";
import DetailVisualMapHorizontal from "@/components/DetailVisualMapHorizontal";
import Pie4 from "@/components/Pie4";
import Roma from "@/components/Roma";

export default {
  components: {
    ChinaMap,
    Overview,
    Roma,
    DetailVisualMapHorizontal,
    Pie4,
  },
  data() {
    return {
      week: [],
      mapData: [],
      show: {
        area: "china-test",//当前地区
        areaCaseOnAWeek: [],//当前地区一周的疫情情况
        covidType: "currentConfirmedCount",//疫情数据的类型，如：当前确诊、累计治愈
        children_name_value: [],//当前地区
      },
    };
  },
  created() {
    this.queryCurrentCase();
  },

  watch: {
    week() {
      this.mapData = this.week[0].provinceVOList.map((item) => {
        return {
          name: item.provinceShortName,
          value: item.currentConfirmedCount,
        };
      });
      this.show.area = "陕西";
      this.show.covidType = "confirmedCount";
      // console.log(this.week)
      // this.show.areaCaseOnAWeek = this.week.map((item) => {
      //   return {
      //     currentConfirmedCount: item.currentConfirmedCount,
      //     confirmedCount: item.confirmedCount,
      //     suspectedCount: item.suspectedCount,
      //     curedCount: item.curedCount,
      //     deadCount: item.deadCount,
      //     updateTime: item.updateTime,
      //   };
      // });
      // console.log(this.show.areaCaseOnAWeek);
    },
    "show.area"() {
      if (this.show.area == "china") {
        // console.log(this.show.area);
        this.show.areaCaseOnAWeek = this.week.map((item) => {
          return {
            currentConfirmedCount: item.currentConfirmedCount,
            confirmedCount: item.confirmedCount,
            suspectedCount: item.suspectedCount,
            curedCount: item.curedCount,
            deadCount: item.deadCount,
            updateTime: item.updateTime,
          };
        });
      } else {
        let today = this.week[0];
        let SelectedProvince = 0;
        for (; SelectedProvince < today.provinceVOList.length; SelectedProvince++) {
          if (today.provinceVOList[SelectedProvince].provinceShortName == this.show.area) {
            break;
          }
        }
        this.show.areaCaseOnAWeek = this.week.map((item) => {
          return {
            currentConfirmedCount: item.provinceVOList[SelectedProvince].currentConfirmedCount,
            confirmedCount: item.provinceVOList[SelectedProvince].confirmedCount,
            suspectedCount: item.provinceVOList[SelectedProvince].suspectedCount,
            curedCount: item.provinceVOList[SelectedProvince].curedCount,
            deadCount: item.provinceVOList[SelectedProvince].deadCount,
            updateTime: item.provinceVOList[SelectedProvince].updateTime,
          };
        });
        // console.log(this.show.areaCaseOnAWeek);
      }

      this.set_children_name_value();
    },
    "show.covidType"() {
      // console.log(this.show.children_name_value);

      this.set_children_name_value();
    }
  },

  methods: {
    queryCurrentCase() {
      axios.get("http://localhost:8801/city-case/queryAWeek").then((res) => {
        this.week = res.data.data.week;
      });
    },
    set_children_name_value() {
      if (this.show.area == 'china') {
        this.show.children_name_value = this.week[0].provinceVOList.map((item) => {
          let valueType;
          switch (this.show.covidType) {
            case "currentConfirmedCount":
              valueType = item.currentConfirmedCount;
              break;
            case "confirmedCount":
              valueType = item.confirmedCount;
              break;
            case "suspectedCount":
              valueType = item.suspectedCount;
              break;
            case "curedCount":
              valueType = item.curedCount;
              break;
            case "deadCount":
              valueType = item.deadCount;
              break
            default:
              valueType = 0
              break;
          }

          return {
            name: item.provinceShortName,
            value: valueType,
          };
        });

      } else {
        let today = this.week[0];
        let SelectedProvince = 0;
        for (; SelectedProvince < today.provinceVOList.length; SelectedProvince++) {
          if (today.provinceVOList[SelectedProvince].provinceShortName == this.show.area) {
            break;
          }
        }
        this.show.children_name_value = today.provinceVOList[SelectedProvince].cityList.map((item) => {
          // console.log(item)
          let valueType;
          switch (this.show.covidType) {
            case "currentConfirmedCount":
              valueType = item.currentConfirmedCount;
              break;
            case "confirmedCount":
              valueType = item.confirmedCount;
              break;
            case "suspectedCount":
              valueType = item.suspectedCount;
              break;
            case "curedCount":
              valueType = item.curedCount;
              break;
            case "deadCount":
              valueType = item.deadCount;
              break
            default:
              valueType = 0
              break;
          }
          return {
            name: item.cityName,
            value: valueType,
          };
        });
      }
    }
  },
};
</script>
<style>
</style>

