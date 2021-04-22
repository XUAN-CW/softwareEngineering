<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="现有确诊" width="180">
      <template>
        <h2>55</h2>
        <span>昨日</span><span>+11</span>
      </template>
    </el-table-column>
    <el-table-column label="疑似" width="180">
      <template>
        <h2>55</h2>
        <span>昨日</span><span>+11</span>
      </template>
    </el-table-column>
    <el-table-column label="累计确诊">
      <template>
        <h2>55</h2>
        <span>昨日</span><span>+11</span>
      </template>
    </el-table-column>
    <el-table-column label="累计治愈">
      <template>
        <h2>55</h2>
        <span>昨日</span><span>+11</span>
      </template>
    </el-table-column>
    <el-table-column label="累计死亡">
      <template>
        <h2>55</h2>
        <span>昨日</span><span>+11</span>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
    };
  },
};
</script>