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
    <div id="loginAndSignIn">
      <a href=""> 登录 </a>
      <a href=""> 注册 </a>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
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

/**********************************************************************************************************/
#loginAndSignIn {
  top: 10px;
  right: 10px;
  position: fixed;
}
a {
  text-decoration: none;
  color: gray;
}
</style>