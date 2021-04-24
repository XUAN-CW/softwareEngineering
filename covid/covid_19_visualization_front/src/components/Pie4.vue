<template>
  <div class="Echarts">
    <div id="pie4"></div>
  </div>
</template>

<script>
export default {
  props: {
    mapData: {},
  },
  data() {
    return {};
  },
  mounted() {
    if (this.mapData.length > 0) {
      this.myEcharts();
    }
  },

  methods: {
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("pie4"));
      let legendData = [];
      for (let i = 0; i < this.mapData.length; i++) {
        legendData.push(this.mapData[i].name);
      }

      let option = {
        title: {
          text: "主标题名称", // 主标题名称
          subtext: "副标题名称", // 副标题名称
          x: "center", // 标题的位置
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} {b} : {c} ({d}%)",
        },
        legend: {
          orient: "horizontal", // 标签名称垂直排列
          x: "center", // 标签的位置
          y: "bottom",
          data: legendData,
        }, // 标签变量名称
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: {
              show: true,
              type: ["pie", "funnel"],
            },
            restore: { show: true },
            saveAsImage: { show: true }, // 保存图片
          },
        },
        calculable: true,
        series: [
          {
            name: "疫情", // 图表名称
            type: "pie", // 图表类型
            radius: [30, 160], // 图表内外半径大小
            center: ["50%", "50%"], // 图表位置
            roseType: "area",
            label: {
              normal: {
                show: true,
                formatter: "{b}({d}%)", // 显示百分比
              },
            },
            data: this.mapData,
          },
        ],
      };

      myChart.setOption(option);
    },
  },
};
</script>
<style>
#pie4 {
  margin: 20px auto; /* 外边距 */
  width: 1000px;
  height: 620px;
}
</style>

