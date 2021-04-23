<template>
  <div class="Echarts">
    <div id="detail_visualMap_horizontal"></div>
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
      var myChart = this.$echarts.init(
        document.getElementById("detail_visualMap_horizontal")
      );
      let data = [];
      data.push("value", "name");
      for (let i = 0; i < this.mapData.length; i++) {
        let temp = [];
        temp.push(this.mapData[i].value);
        temp.push(this.mapData[i].name);
        data.push(temp);
      }
      console.log(data);

      let options = {
        dataset: {
          source: data,
        },
        grid: { containLabel: true },
        xAxis: { name: "value" },
        yAxis: { type: "category" },
        visualMap: {
          orient: "horizontal",
          left: "center",
          min: 0,
          max: 100,
          text: ["High value", "Low value"],
          // Map the value column to color
          dimension: 0,
          inRange: {
            color: ["#D7DA8B", "#E15457"],
          },
        },
        series: [
          {
            type: "bar",
            encode: {
              // Map the "value" column to X axis.
              x: "value",
              // Map the "name" column to Y axis
              y: "name",
            },
          },
        ],
      };

      myChart.setOption(options);
    },
  },
};
</script>
<style>
#detail_visualMap_horizontal {
  margin: 20px auto; /* 外边距 */
  width: 1000px;
  height: 720px;
}
</style>

