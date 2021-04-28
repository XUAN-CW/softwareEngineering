<template>
  <div>
    <Overview></Overview>
    <ChinaMap :mapData="mapData" :key="JSON.stringify(mapData)"></ChinaMap>
    <roma :generalSituation="show.generalSituationOfAWeek" :key="JSON.stringify(show.generalSituationOfAWeek) + 3"></roma>
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
        area: "china-test",
        generalSituationOfAWeek: [],
        covidType: "currentConfirmedCount",
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
      this.show.area = "湖南";
      // console.log(this.week)
      // this.show.generalSituationOfAWeek = this.week.map((item) => {
      //   return {
      //     currentConfirmedCount: item.currentConfirmedCount,
      //     confirmedCount: item.confirmedCount,
      //     suspectedCount: item.suspectedCount,
      //     curedCount: item.curedCount,
      //     deadCount: item.deadCount,
      //     updateTime: item.updateTime,
      //   };
      // });
      // console.log(this.show.generalSituationOfAWeek);
    },
    "show.area"() {
      if (this.show.area == "china") {
        console.log(this.show.area);
        this.show.generalSituationOfAWeek = this.week.map((item) => {
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
        this.show.generalSituationOfAWeek = this.week.map((item) => {
          return {
            currentConfirmedCount: item.provinceVOList[SelectedProvince].currentConfirmedCount,
            confirmedCount: item.provinceVOList[SelectedProvince].confirmedCount,
            suspectedCount: item.provinceVOList[SelectedProvince].suspectedCount,
            curedCount: item.provinceVOList[SelectedProvince].curedCount,
            deadCount: item.provinceVOList[SelectedProvince].deadCount,
            updateTime: item.provinceVOList[SelectedProvince].updateTime,
          };
        });
        console.log(this.show.generalSituationOfAWeek);
      }
    },
  },

  methods: {
    queryCurrentCase() {
      axios.get("http://localhost:8801/city-case/queryAWeek").then((res) => {
        this.week = res.data.data.week;
      });
    },
  },
};
</script>
<style>
</style>

