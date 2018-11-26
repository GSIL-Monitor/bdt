<template>
  <Card>
    <div slot="title" style="display: flex;align-items: center">
      <div style="flex: 1;text-align: left">
        <span>ljxjz：{{XJZ}}</span>&ensp;&ensp;&ensp;<span>ljzjz：{{ZJZ}}</span>
      </div>
      <div style="flex: 1;text-align: right">
        起始&ensp;<DatePicker readonly type="datetime" disabled @on-change="daterangeChange" v-model="DateRange" confirm
                            :clearable="false" placement="bottom-end" placeholder="Select date"
                            style="width: 200px"></DatePicker>&ensp;-&ensp;当前&emsp;&emsp;&ensp;&ensp;
      </div>
    </div>
    <div id="overLine">
      <div ref="dom" style="height: 450px"></div>
    </div>
  </Card>

</template>

<script>
  import echarts from 'echarts'
  import {on, off} from '@/libs/tools'

  export default {
    name: 'serviceRequests',
    data() {
      return {
        dom: null,
        XJZ: 0.00,
        ZJZ: 0.00,
        DateRange: '',
        minVal: 0,
        maxVal: 0
      }
    },
    activated() {
      this.getLJInfo();
      clearInterval(window.setIngetLJInfo);
      window.setIngetLJInfo = setInterval(_ => {
        this.getLJInfo();
      }, 1000 * 3)
    },
    beforeDestroy() {
      clearInterval(window.setIngetLJInfo);
      off(window, 'resize', this.resize())
    },
    created() {
      let pevTime = this.formatDate(new Date().getTime() - (1000 * 60 * 60 * 1));
      this.DateRange = pevTime;
      this.getLJInfo();
      clearInterval(window.setIngetLJInfo);
      window.setIngetLJInfo = setInterval(_ => {
        this.getLJInfo();
      }, 1000 * 5)
    },
    methods: {
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
      daterangeChange(val) {
        console.warn(this.DateRange)
        console.warn(val);
      },
      resize() {
        this.dom.resize()
      },
      getLJInfo() {
        let pevTime = this.formatDate(new Date().getTime());
        console.log(pevTime);
        this.DateRange = pevTime;
        let srartTime;
        srartTime = new Date(this.DateRange).getTime();
        let params = {
          startTime: srartTime,
          endTime: '',
          tableNo: ''
        }
        this.$api.getLJInfo(params).then((res) => {
          if (res.returnCode == 200) {
            let data = res.returnObject;
            if (data.ljxjz[0]) {
              this.XJZ = data.ljxjz[0].ljxjz
            }
            if (data.ljzjz[0]) {
              this.ZJZ = data.ljzjz[0].ljzjz
            }
            this.setECharts(data);
          }
        }).catch((err) => {

        })
      },
      //
      setECharts(data) {
        let ljxjzName = data.ljxjz.map(e => {
          return '副号' + e.fit_no
        })
        ljxjzName = ljxjzName.reverse()
        let ljxjzVal = data.ljxjz.map(e => {
          return e.ljxjz
        })
        ljxjzVal = ljxjzVal.reverse()
        let ljzjzVal = data.ljzjz.map(e => {
          return e.ljzjz
        });
        ljzjzVal = ljzjzVal.reverse()
        // console.log(ljxjzVal, ljzjzVal);
        this.$nextTick(() => {
          if (!!!this.$refs.dom) {
            return false
          }
          let _this = this;
          this.dom = echarts.init(this.$refs.dom)
          const option = {
            toolbox: {
              show: true,
              feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
              }
            },
            dataZoom: [
              {
                show: true,
                start: 94,
                end: 100
              },
              {
                type: 'inside',
                start: 34,
                end: 100
              },
              {
                show: true,
                yAxisIndex: 0,
                filterMode: 'empty',
                width: 30,
                height: '80%',
                showDataShadow: false,
                left: '97%'
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
                data: ljxjzVal
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
          this.dom.setOption(option)
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
