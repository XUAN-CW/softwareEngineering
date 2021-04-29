<template>
  <div id="overview" v-if="today">
    <ul>
      <li>
        <div class="overviewBox" lable="currentConfirmedCount" @click="set_covidType($event)">
          <div class="overviewBox_case">现有确诊</div>
          <div class="overviewBox_number">{{today.currentConfirmedCount}}</div>
          <span>昨日</span><span>{{today.currentConfirmedCount - yesterday.currentConfirmedCount}}</span>
        </div>
      </li>
      <li>
        <div class="overviewBox" lable="confirmedCount" @click="set_covidType($event)">
          <div class="overviewBox_case">累计确诊</div>
          <div class="overviewBox_number">{{today.confirmedCount}}</div>
          <span>昨日</span><span>{{today.currentConfirmedCount - yesterday.currentConfirmedCount}}</span>
        </div>
      </li>
      <li>
        <div class="overviewBox" lable="suspectedCount" @click="set_covidType($event)">
          <div class="overviewBox_case">现有疑似</div>
          <div class="overviewBox_number">{{today.suspectedCount}}</div>
          <span>昨日</span><span>{{today.suspectedCount - yesterday.suspectedCount}}</span>
        </div>
      </li>
      <li>
        <div class="overviewBox" lable="curedCount" @click="set_covidType($event)">
          <div class="overviewBox_case">累计治愈</div>
          <div class="overviewBox_number">{{today.curedCount}}</div>
          <span>昨日</span><span>{{today.curedCount - yesterday.curedCount}}</span>
        </div>
      </li>
      <li>
        <div class="overviewBox" lable="deadCount" @click="set_covidType($event)">
          <div class="overviewBox_case">累计死亡</div>
          <div class="overviewBox_number">{{today.deadCount}}</div>
          <span>昨日</span><span>{{today.deadCount - yesterday.deadCount}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    today: {},
    yesterday: {},
  },
  data() {
    return {

    };
  },
  methods: {

    set_covidType(e) {
      // console.log(e.currentTarget.getAttribute("lable"))// 当前元素
      this.set_covidTypeHelper(e.currentTarget.getAttribute("lable"))
    },
    set_covidTypeHelper(covidType){
      this.$parent.set_show_covidType(covidType)
    }
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