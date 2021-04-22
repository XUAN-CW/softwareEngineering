<template>
  <div>
    <Overview></Overview>
    <ChinaMap
      :mapData="showChinaMap.mapData"
      v-if="showChinaMap.updateMap"
    ></ChinaMap>
  </div>
</template>

<script>
import axios from "axios";
import ChinaMap from "@/components/ChinaMap";
import Overview from '@/components/Overview'
export default {
  components: {
    ChinaMap,
    Overview
  },
  data() {
    return {
      week: [],
      showChinaMap: {
        mapData: [],
        updateMap: "", //请求到数据再渲染
      },
    };
  },
  created() {
    this.queryCurrentCase();
  },
  methods: {
    queryCurrentCase() {
      axios.get("http://localhost:8801/city-case/queryAWeek").then((res) => {
        this.week = res.data.data.week;
        this.showChinaMap.mapData = this.week[0].provinceVOList.map((item) => {
          return {
            name: item.provinceShortName,
            value: item.currentConfirmedCount,
          };
        });
        this.showChinaMap.updateMap = true;
      });
    },
  },
};
</script>
<style>
</style>

