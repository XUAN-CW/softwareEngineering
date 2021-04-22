<template>
  <div class="Echarts">
    <div id="main" style="width: 900px; height: 600px"></div>
  </div>
</template>

<script>
import axios from "axios";

  // require('echarts/lib/chart/effectScatter')
  // 地图
  require('echarts/lib/chart/map')
  // 图例
  require('echarts/lib/component/legend')
  // 提示框
  require('echarts/lib/component/tooltip')
  // 地图geo
  require('echarts/lib/component/geo')
  // 中国地图
  require('echarts/map/js/china')
export default {
  name: "Echarts",
  data() {
    return {
      week: [],
      resource: []
    };
  },
  created() {
    this.queryCurrentCase();
  },

  methods: {
    queryCurrentCase() {
      axios.get("http://localhost:8801/city-case/queryAWeek").then((res) => {
        console.log(res);
        this.week=res.data.data.week;
        this.resource = this.week[0].provinceVOList.map((item) => {
          return {
            name: item.provinceShortName,
            value: item.currentConfirmedCount,
          };
        });

        this.myEcharts();
      });
    },

    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("main"));

      let options = {
        title: {
          text: "实时疫情地图",
          textStyle: {
            color: "red",
            fontWeight: "bold",
          },
          subtext: "数据来自于百度",
          subtextStyle: {
            color: "#aaa",
            fontWeight: "italic",
          },
          sublink: "https://voice.baidu.com/act/newpneumonia/newpneumonia/",
          left: "center",
        },
        geo: {
          show: true,
          map: "china",
          aspectScale: 0.75,
          zoom: 1.25,
          selectedMode: "single",
          label: {
            show: true,
            formatter: "{a}",
          },
          emphasis: {
            label: {
              show: true,
            },
          },
        },
        tooltip: {
          show: true,
          formatter: "{b}<br>确诊：{c}人",
        },
        visualMap: {
          show: true,
          type: "piecewise",
          pieces: [
            { gt: 1000, label: ">1000人" },
            { gt: 99, lte: 1000 },
            { gt: 10, lte: 99 },
            { lt: 10, label: "<10人" },
          ],
          formatter: "{value}人",
        },
        series: [
          {
            type: "map",
            name: "实时疫情地图",
            coordinateSystem: "geo",
            geoIndex: 0,
            data: this.resource,
          },
        ],
      };

    // console.log("aaaaaa"+this.resource);
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(options);
    },
  },
  mounted() {
    
    // this.queryCurrentCase();
    // console.log(this.resource);
    // this.myEcharts();
  },
};
</script>
    <style>
#main {
  margin: 20px auto; /* 外边距 */
  width: 1000px;
  height: 720px;
}
</style>

