<template>
  <div class="Echarts">
    <div id="roma" style="width: 900px; height: 600px"></div>
  </div>
</template>

<script>
// require('echarts/lib/chart/effectScatter')
// 地图
require("echarts/lib/chart/map");
// 图例
require("echarts/lib/component/legend");
// 提示框
require("echarts/lib/component/tooltip");
// 地图geo
require("echarts/lib/component/geo");
// 中国地图
require("echarts/map/js/china");
export default {
  name: "Echarts",
  props: {
    generalSituation: {},
  },
  data() {
    return {};
  },
  mounted() {
    if (this.generalSituation.length > 0) {
      this.myEcharts();
    }
  },

  methods: {
    myEcharts() {
      let legendData = [
        "现有确诊",
        "累计确诊",
        "累计治愈",
        "累计死亡",
        "现有疑似",
      ];

      let seriesItems = [];
      for (let i = 0; i < legendData.length; i++) {
        let seriesItem = {
          name: legendData[i],
          type: "line",
          stack: "总量",
          data: [],
        };
        seriesItems.push(seriesItem);
      }
      for (let i = this.generalSituation.length - 1; i >= 0; i--) {
        seriesItems[0].data.push(
          this.generalSituation[i].currentConfirmedCount
        );
        seriesItems[1].data.push(this.generalSituation[i].confirmedCount);
        seriesItems[2].data.push(this.generalSituation[i].curedCount);
        seriesItems[3].data.push(this.generalSituation[i].deadCount);
        seriesItems[4].data.push(this.generalSituation[i].suspectedCount);
      }

      let xAxisData = [];
      for (let i = this.generalSituation.length - 1; i > 0; i--) {
        xAxisData.push(i + "天前");
      }
      xAxisData.push("今天");

      console.log(seriesItems);
      var myChart = this.$echarts.init(document.getElementById("roma"));

      let options = {
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: legendData,
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar", "stack", "tiled"] },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: xAxisData,
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: seriesItems,
      };

      // console.log("aaaaaa"+this.resource);
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(options);
    },
  },
};
</script>
    <style>
#roma {
  margin: 20px auto; /* 外边距 */
  width: 1000px;
  height: 720px;
}
</style>

