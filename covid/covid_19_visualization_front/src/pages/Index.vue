<template>
  <div>
    <Overview></Overview>
    <ChinaMap :mapData="mapData" :key="JSON.stringify(mapData)"></ChinaMap>
    <roma
      :generalSituation="generalSituationOfAWeek"
      :key="JSON.stringify(generalSituationOfAWeek) + 3"
    ></roma>
    <DetailVisualMapHorizontal
      :mapData="mapData"
      :key="JSON.stringify(mapData) + 1"
    ></DetailVisualMapHorizontal>
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
      generalSituationOfAWeek: [],
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
      // console.log(this.week)
      this.generalSituationOfAWeek = this.week.map((item) => {
        return {
          currentConfirmedCount: item.currentConfirmedCount,
          confirmedCount: item.confirmedCount,
          suspectedCount: item.suspectedCount,
          curedCount: item.curedCount,
          deadCount: item.deadCount,
          updateTime: item.updateTime,
        };
      });
      // console.log(this.generalSituationOfAWeek);
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

