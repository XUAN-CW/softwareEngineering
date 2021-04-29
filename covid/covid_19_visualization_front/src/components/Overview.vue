<template>
  <div id="overview">
    <ul>
      <li>
        <div class="overviewBox">
          <div class="overviewBox_case">现有确诊</div>
          <div class="overviewBox_number">5555555</div>
          <span>昨日</span><span>+15</span>
        </div>
      </li>
      <li>
        <div class="overviewBox">
          <div class="overviewBox_case">现有确诊</div>
          <div class="overviewBox_number">5555555</div>
          <span>昨日</span><span>+15</span>
        </div>
      </li>
      <li>
        <div class="overviewBox">
          <div class="overviewBox_case">现有确诊</div>
          <div class="overviewBox_number">5555555</div>
          <span>昨日</span><span>+15</span>
        </div>
      </li>
      <li>
        <div class="overviewBox">
          <div class="overviewBox_case">现有确诊</div>
          <div class="overviewBox_number">5555555</div>
          <span>昨日</span><span>+15</span>
        </div>
      </li>
      <li>
        <div class="overviewBox">
          <div class="overviewBox_case">现有确诊</div>
          <div class="overviewBox_number">5555555</div>
          <span>昨日</span><span>+15</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
};
</script>

<style>
.overviewBox {
  padding-top: 10px;
  padding-bottom: 10px;
}
.overviewBox:hover {
  background-color: whitesmoke;
  border-radius: 20px;
}
.overviewBox_number {
  font-size: 25px;
  padding-top: 5px;
  padding-bottom: 5px;
}

/**********************************************************************************************************/
#overview {
  margin: 20px auto; /* 外边距 */
  width: 1000px;
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
</style>