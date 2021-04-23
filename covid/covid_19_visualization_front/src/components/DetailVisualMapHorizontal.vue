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
    return {
    };
  },
  mounted() {
    this.myEcharts();
  },

  methods: {

    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("detail_visualMap_horizontal"));

              let options = {
                dataset: {
                    source: [
                        ['value' , 'name'],
                        [89.3, 'Matcha Latte'],
                        [57.1, 'Milk Tea'],
                        [74.4, 'Cheese Cocoa'],
                        [50.1, 'Cheese Brownie'],
                        [89.7, 'Matcha Cocoa'],
                        [68.1, 'Tea'],
                        [19.6, 'Orange Juice'],
                        [10.6, 'Lemon Juice'],
                        [32.7, 'Walnut Brownie']
                    ]
                },
                grid: {containLabel: true},
                xAxis: {name: 'value'},
                yAxis: {type: 'category'},
                visualMap: {
                    orient: 'horizontal',
                    left: 'center',
                    min: 0,
                    max: 100,
                    text: ['High value', 'Low value'],
                    // Map the value column to color
                    dimension: 0,
                    inRange: {
                        color: ['#D7DA8B', '#E15457']
                    }
                },
                series: [
                    {
                        type: 'bar',
                        encode: {
                            // Map the "amount" column to X axis.
                            x: 'value',
                            // Map the "name" column to Y axis
                            y: 'name'
                        }
                    }
                ]
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

