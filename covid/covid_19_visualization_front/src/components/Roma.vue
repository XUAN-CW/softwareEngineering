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
    mapData: {},
  },
  data() {
    return {};
  },
  mounted() {
    this.myEcharts();
  },

  methods: {
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("roma"));

      let options = {
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["邮件营销", "联盟广告", "视频广告", "直接访问", "搜索引擎"],
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
            data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "邮件营销",
            type: "line",
            stack: "总量",
            data: [120, 132, 101, 134, 90, 230, 210],
          },
          {
            name: "联盟广告",
            type: "line",
            stack: "总量",
            data: [220, 182, 191, 234, 290, 330, 310],
          },
          {
            name: "视频广告",
            type: "line",
            stack: "总量",
            data: [150, 232, 201, 154, 190, 330, 410],
          },
          {
            name: "直接访问",
            type: "line",
            stack: "总量",
            data: [320, 332, 301, 334, 390, 330, 320],
          },
          {
            name: "搜索引擎",
            type: "line",
            stack: "总量",
            data: [820, 932, 901, 934, 1290, 1330, 1320],
          },
        ],
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

