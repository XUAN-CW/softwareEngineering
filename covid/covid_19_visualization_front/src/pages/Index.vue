<template>
  <div>
    <Overview></Overview>
    <ChinaMap :mapData="mapData" :key="mapData"></ChinaMap>
    <DetailVisualMapHorizontal></DetailVisualMapHorizontal>
  </div>
</template>

<script>
import axios from "axios";
import ChinaMap from "@/components/ChinaMap";
import Overview from "@/components/Overview";
import DetailVisualMapHorizontal from '@/components/DetailVisualMapHorizontal'
export default {
  components: {
    ChinaMap,
    Overview,
    DetailVisualMapHorizontal
  },
  data() {
    return {
      week: [],
      mapData: [],
    };
  },
  created() {
    this.queryCurrentCase();
  },
  methods: {
    queryCurrentCase() {
      axios.get("http://localhost:8801/city-case/queryAWeek").then((res) => {
        this.week = res.data.data.week;
        this.mapData = this.week[0].provinceVOList.map((item) => {
          return {
            name: item.provinceShortName,
            value: item.currentConfirmedCount,
          };
        });
        this.updateMap = true;
      });
    },
  },
};
</script>
<style>
</style>

