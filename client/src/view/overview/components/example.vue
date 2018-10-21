<template>
  <Card>
    <div slot="title" style="display: flex;align-items: center">
      <div style="flex: 1;text-align: left">
        <span>现值：{{XJZ}}</span>&ensp;&ensp;&ensp;<span>现值：{{ZJZ}}</span>
      </div>
      <div style="flex: 1;text-align: right">
        起始&ensp;<DatePicker type="datetime" @on-change="daterangeChange" v-model="DateRange" confirm
                    :clearable="false" placement="bottom-end" placeholder="Select date"
                    style="width: 200px"></DatePicker>&ensp;-&ensp;当前&emsp;&emsp;&ensp;&ensp;
      </div>
    </div>
    <div id="overLine">
      <div ref="dom" style="height: 310px"></div>
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
        DateRange: ''
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
    },
    created() {
      let pevTime = this.formatDate(new Date().getTime() - (1000 * 60 * 60 * 3));
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
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
      },
      daterangeChange(val) {
        console.warn(this.DateRange)
        console.warn(val);
      },
      resize() {
        this.dom.resize()
      },
      getLJInfo() {
        // this.DateRange[1] = this.formatDate(new Date().getTime());
        let srartTime;
        srartTime = new Date(this.DateRange).getTime();
        this.DateRange;
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
          this.dom = echarts.init(this.$refs.dom)
          const option = {
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'cross',
                label: {
                  backgroundColor: '#6a7985'
                }
              }
            },
            grid: {
              top: '5%',
              left: '3%',
              right: '3%',
              bottom: '5%',
              containLabel: true
            },
            xAxis: [
              {
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
                type: 'value',
                axisLabel: {
                  color: '#aeaeae'
                },
                axisLine: {
                  lineStyle: {
                    color: '#aeaeae'
                  }
                }
              },
              {
                type: 'value',
                axisLabel: {
                  color: '#aeaeae'
                },
                axisLine: {
                  lineStyle: {
                    color: '#aeaeae'
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
                    color: '#4608A6'
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
                    color: '#398DBF'
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
    beforeDestroy() {
      off(window, 'resize', this.resize())
    }
  }
</script>
