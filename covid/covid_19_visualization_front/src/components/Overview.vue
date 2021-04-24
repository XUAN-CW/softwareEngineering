<template>
  <div id="overview" class="box_fixed" :class="{ is_fixed: isFixed }">
    <ul>
      <li><OverviewBox></OverviewBox></li>
      <li><OverviewBox></OverviewBox></li>
      <li><OverviewBox></OverviewBox></li>
      <li><OverviewBox></OverviewBox></li>
      <li><OverviewBox></OverviewBox></li>
    </ul>
  </div>
</template>

<script>
import OverviewBox from "@/components/OverviewBox";
export default {
  components: {
    OverviewBox,
  },
  data() {
    return {
      isFixed: false,
      offsetTop: 0,
      tableData: [
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },
  mounted() {
    window.addEventListener("scroll", this.initHeight);
    this.$nextTick(() => {
      this.offsetTop = document.querySelector("#boxFixed").offsetTop;
    });
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    initHeight() {
      var scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      this.isFixed = scrollTop > this.offsetTop ? true : false;
    },
  },
  destroyed() {
    window.removeEventListener("scroll", this.handleScroll);
  },
};
</script>

<style>
#overview {
  margin: 0 auto; /* 外边距 */
  width: 100%;
  overflow: hidden;
}

ul {
  display: table; /*这里是关键步骤：*/
  margin: 0 auto; /*让ul在div中水平居中 */
}
ul::after {
  /*清除浮动 */
  content: "";
  display: inline-block;
  clear: both;
}
ul li {
  float: left; /*让li浮动起来 横向排列*/
  list-style: none; /*隐藏序号*/
  text-align: center; /*让li在ul中水平居中*/
  width: 150px; /*任意设置li的宽度，不设置宽度的话也可以设置padding*/
}

.box_fixed {
  margin: 0 auto;
  width: 100%;
  background: white;
}
.is_fixed {
  position: fixed;
  top: 0;
  z-index: 999;
}
</style>