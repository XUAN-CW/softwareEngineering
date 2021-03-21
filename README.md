# 题目《疫情数据可视化系统》

## 项目描述

突发疫情公共卫生事件应急管理已成为中国公共管理学界的一个重要研究领域，疫情防控人人有责。本系统可进行疫情**数据统计分析**及**可视化**，通过对互联网中的疫情数据进行数据获取，并进行**筛选与分析**，进行**地图数据可视化**技术。

## 基本功能

1. **基础功能：**注册与登录等一些软件的基本功能。

2. **数据处理：**疫情数据的获取并清洗，以图表的形式统计疫情信息。将数据以地图的形式进行展示并制作好地图标签。

3. **场景总览：**快速阅览疫情汇总概要信息，包括每日确诊、疑似、治愈、死亡病例统计数据。        

## 扩展功能

1. 实时获取疫情数据，自动将更新的数据存入数据库中，展示出一个动态的地图数据可视化，掌握疫情的实时动态。

2. 可以通过列车车次或航空航班次查询本人是否有过与病患同车次/班次情况。

3. 疫情趋势。根据确诊、疑似、治愈/死亡病例统计数据分析疫情实时趋势，辅助指挥决策。

4. 扩展出其他更具亮点的功能。





# 字段

| 字段名                | 含义 |
| --------------------- | ---- |
| provinceName          |      |
| currentConfirmedCount |      |
| confirmedCount        |      |
| suspectedCount        |      |
| curedCount            |      |
| deadCount             |      |
| updateTime            |      |





| 变量名                | 注释                                                         |
| --------------------- | ------------------------------------------------------------ |
| locationId            | 城市编号，中国大陆城市编号为邮编，中国大陆以外城市编号暂不知规则 |
|                       |                                                              |
|                       |                                                              |
|                       |                                                              |
| provinceShortName     | 省份、地区或直辖市简称                                       |
| currentConfirmedCount | 现存确诊人数，值为confirmedCount - curedCount - deadCount    |
| confirmedCount        | 累计确诊人数                                                 |
| suspectedCount        | 疑似感染人数                                                 |
| curedCount            | 治愈人数                                                     |
| deadCount             | 死亡人数                                                     |
|                       |                                                              |
|                       |                                                              |
| updateTime            | 数据更新时间                                                 |






