<template>
  <Card>
    <div slot="title" style="display: flex;align-items: center">
      <div style="flex: 1;text-align: left">
        <span>ljzjz：{{ZJZ}}</span>
      </div>
      <div style="flex: inherit;text-align: right;margin-right: 15px">
        <Button type="warning" @click="searchDopeData">刷新</Button>
      </div>
      <div style="flex: inherit;text-align: right;margin-right: 15px">
        <Select v-model="model1" @on-change="weekChange" style="width:150px">
          <Option v-for="item in weekList" :value="item.value" :key="item.value">{{ item.label }}
          </Option>
        </Select>
      </div>
      <div style="flex: inherit;text-align: right">
        起始&ensp;<DatePicker readonly type="datetime" disabled @on-change="daterangeChange"
                            v-model="DateRange" confirm
                            :clearable="false" placement="bottom-end" placeholder="Select date"
                            style="width: 200px"></DatePicker>&ensp;-&ensp;
        当前&ensp;<DatePicker readonly type="datetime" disabled v-model="DateRangeEnd" confirm
                            :clearable="false" placement="bottom-end" placeholder="Select date"
                            style="width: 200px"></DatePicker>&ensp;&ensp;
      </div>
    </div>
    <div id="overLine">
      <div ref="dom" style="height: 550px"></div>
    </div>
    <Spin fix v-if="loading">
      <Icon type="ios-loading" size=18 class="demo-spin-icon-load"></Icon>
    </Spin>
  </Card>

</template>

<script>
  import echarts from 'echarts'
  import {on, off} from '@/libs/tools'

  export default {
    name: 'serviceRequests',
    data() {
      return {
        loading: true,
        weekList: [
          {
            value: '1',
            label: '周一'
          },
          {
            value: '2',
            label: '周二'
          },
          {
            value: '3',
            label: '周三'
          },
          {
            value: '4',
            label: '周四'
          },
          {
            value: '5',
            label: '周五'
          },
          {
            value: '6',
            label: '周六'
          },
          {
            value: '0',
            label: '周天'
          }
        ],
        model1: '',
        dom: null,
        XJZ: 0.00,
        ZJZ: 0.00,
        DateRange: '',
        minVal: 0,
        maxVal: 0,
        DateRangeEnd: ''
      }
    },
    activated() {
    },
    beforeDestroy() {
      off(window, 'resize', this.resize())
    },
    created() {
      for (let i = 0; i < 7; i++) {
        this.weekList[i] = {
          value: this.getWeek(i + 1),
          label: `${this.getWeek(i + 1)}-${this.weekList[i].label}`
        };
        console.log(this.getWeek(i));
      }
      this.model1 = this.formatDateM()
      this.getLJInfo(this.model1);
    },
    methods: {

      format(shijianchuo) {
        let add0 = (m) => {
          return m < 10 ? '0' + m : m
        }
        //shijianchuo是整数，否则要parseInt转换
        var time = new Date(parseInt(shijianchuo));
        var y = time.getFullYear();
        var m = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var mm = time.getMinutes();
        var s = time.getSeconds();
        return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
      },
      weekChange(val) {
        this.getLJInfo(val);
      },
      searchDopeData() {
        this.getLJInfo(this.model1);
      },
      getWeek(i) {
        let now = new Date();
        let getDay = now.getDay();
        if (now.getDay() == 0) {
          getDay = 7;
        }
        let firstDay = new Date(now - (getDay) * 86400000);
        console.log(now, firstDay.getTime(), getDay, (now.getDay() - 1) * 86400000);
        firstDay.setDate(firstDay.getDate() + i);
        let mon = Number(firstDay.getMonth()) + 1;
        mon = mon < 10 ? ('0' + mon) : mon;
        let d = firstDay.getDate()
        d = d < 10 ? ('0' + d) : d;
        return `${now.getFullYear()}-${mon}-${d}`;
      },
      getWeekGetTime(i) {
        let now = new Date();
        let firstDay = new Date(now - (now.getDay() - 1) * 86400000);
        firstDay.setDate(firstDay.getDate() + i);
        let mon = Number(firstDay.getMonth()) + 1;
        mon = mon < 10 ? ('0' + mon) : mon;
        let d = firstDay.getDate()
        d = d < 10 ? ('0' + d) : d;
        return firstDay.getTime();
      },
      //
      formatDateM() {
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return `${y}-${m}-${d}`;
      },
      formatDate(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return `${y}-${m}-${d} 00:00:00`;
      },
      formatDateEnd(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return `${y}-${m}-${d} 23:59:59`;
      },
      daterangeChange(val) {
        console.warn(this.DateRange)
        console.warn(val);
      },
      resize() {
        this.dom.resize()
      },
      getLJInfo(model) {
        this.loading = true;
        let pevTime = this.formatDate(new Date(`${model} 00:00:00`).getTime());
        let nextTime = this.formatDateEnd(new Date(`${model} 00:00:00`).getTime());
        console.log(pevTime);
        this.DateRange = pevTime;
        this.DateRangeEnd = nextTime;
        let srartTime;
        srartTime = new Date(this.DateRange).getTime();
        let params = {
          startTime: srartTime,
          endTime: '',
          tableNo: ''
        }
        this.$api.getLJInfo(params).then((res) => {
          this.loading = false;
          if (res.data.returnCode == 200) {
            let data = res.data.returnObject;
            if (data.ljzjz) {
              this.ZJZ = data.ljzjz[0].ljzjz
            }
            this.setECharts(data);
          }
        }).catch((err) => {
          this.loading = false;
        })
      },
      //
      setECharts(data) {
        let ljxjzName = data.ljzjz.map(e => {
          return this.format(e.createTime)
        })
        ljxjzName = ljxjzName.reverse()
        let ljzjzVal = data.ljzjz.map(e => {
          return e.ljzjz
        });
        ljzjzVal = ljzjzVal.reverse()
        console.log(ljzjzVal);
        this.$nextTick(() => {
          if (!!!this.$refs.dom) {
            return false
          }
          let _this = this;
          const option = {
            toolbox: {
              show: true,
              feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line']},
                restore: {show: true},
                saveAsImage: {show: true}
              }
            },
            dataZoom: [
              {
                show: true,
                start: 50,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                  color: '#fff',
                  shadowBlur: 3,
                  shadowColor: 'rgba(0, 0, 0, 0.6)',
                  shadowOffsetX: 2,
                  shadowOffsetY: 2
                }
              },
              {
                type: 'inside',
                start: 34,
                end: 100,
              },
              {
                show: true,
                yAxisIndex: 0,
                filterMode: 'empty',
                width: 30,
                height: '80%',
                showDataShadow: false,
                left: '97%',
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                  color: '#fff',
                  shadowBlur: 3,
                  shadowColor: 'rgba(0, 0, 0, 0.6)',
                  shadowOffsetX: 2,
                  shadowOffsetY: 2
                }
              }
            ],
            tooltip: {
              // 提示框
              trigger: 'axis',
              // position: function (pt) {
              //   return [pt[0], '10%'];
              // },
              backgroundColor: '#fff',
              borderWidth: 1,
              borderColor: '#e3e3e3',
              confine: true,
              axisPointer: { // 坐标轴指示器配置项
                label: {
                  show: false
                },
                type: 'cross', // 指示器类型，十字准星
                crossStyle: {
                  type: 'solid',
                  color: '#e3e3e3'
                },
                lineStyle: {
                  color: '#e3e3e3'
                }
              },
              textStyle: {
                color: "#aeaeae",
                fontSize: 12
              },
              formatter: function (data) {
                var html = ``;
                data.forEach((item) => {
                  if (item.seriesName.indexOf('率') > -1) {
                    html += `<div style="display:flex;justify-content:space-between"><span>${item.seriesName}：</span><span style="color: ${item.color}">${item.value}%</span></div>`
                  } else {
                    html += `<div style="display:flex;justify-content:space-between"><span>${item.seriesName}：</span><span style="color: ${item.color}">${item.value}</span></div>`
                  }
                });
                return html
              }
            },
            grid: {
              top: '10%',
              left: '1%',
              right: '3%',
              bottom: '1%',
              containLabel: true
            },
            legend: {
              selectedMode: true,
              align: 'left',
              top: 10,
              left: 0,
              data: ['ljxjz', 'ljzjz']
            },
            xAxis: [
              {
                show: false,
                type: 'category',
                boundaryGap: false,
                data: ljxjzName,
                axisLabel: {
                  rotate: 20
                }
              }
            ],
            yAxis: [
              {
                type: "value",
                name: "",
                axisLabel: {
                  textStyle: {
                    color: "#8d8d8d",
                    fontSize: 10
                  }
                },
                splitLine: {
                  show: false
                },
                axisTick: {
                  length: 0
                },
                axisLine: {
                  symbolSize: [0, 0],
                  lineStyle: {
                    color: "#ccc"
                  }
                }
              },
              {
                type: "value",
                name: "",
                axisLabel: {
                  textStyle: {
                    color: "#8d8d8d",
                    fontSize: 10
                  },
                  formatter: ''
                },
                min: 0,
                max: 100,
                position: 'right',
                axisTick: {
                  length: 0
                },
                axisLine: {
                  symbolSize: [0, 0],
                  lineStyle: {
                    color: "#ccc"
                  }
                }
              }
            ],
            series: [
              {
                name: 'ljxjz',
                type: 'line',
                stack: '总量',
                areaStyle: {
                  normal: {
                    // color: '',
                    opacity: 0
                  }
                },
                data: []
              },
              {
                name: 'ljzjz',
                type: 'line',
                stack: '总量',
                label: {
                  normal: {
                    show: false,
                    position: 'top'
                  }
                },
                areaStyle: {
                  normal: {
                    // color: '#398DBF',
                    opacity: 0
                  }
                },
                data: ljzjzVal
              }
            ]
          }
          this.$nextTick(() => {
            this.dom = echarts.init(this.$refs.dom)
            this.dom.setOption(option)
            on(window, 'resize', this.resize())
          })
        })
      }
    },
    mounted() {
    },
    destroyed() {
      clearInterval(window.setIngetLJInfo);
    }
  }
</script>
<style lang="less">
  #overLine {
    position: relative;
  }
  .demo-spin-icon-load {
    animation: ani-demo-spin 1s linear infinite;
  }
  @keyframes ani-demo-spin {
    from {
      transform: rotate(0deg);
    }
    50% {
      transform: rotate(180deg);
    }
    to {
      transform: rotate(360deg);
    }
  }
  .demo-spin-col {
    height: 100px;
    position: relative;
    border: 1px solid #eee;
  }
</style>
