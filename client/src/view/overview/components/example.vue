<template>
  <Card>
    <CellGroup slot="title" style="display: flex">
      <Cell title="LJXJZ" :label="'现值：'+XJZ"/>
      <Cell title="LJZJZ" :label="'现值：'+ZJZ"/>
    </CellGroup>
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
        ZJZ: 0.00
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
      this.getLJInfo();
      clearInterval(window.setIngetLJInfo);
      window.setIngetLJInfo = setInterval(_ => {
        this.getLJInfo();
      }, 1000 * 5)
    },
    methods: {
      resize() {
        this.dom.resize()
      },
      getLJInfo() {
        let params = {
          startTime: '',
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
        let ljxjzVal = data.ljxjz.map(e => {
          return e.ljxjz
        })
        let ljzjzVal = data.ljzjz.map(e => {
          return e.ljzjz
        });
        console.log(ljxjzVal, ljzjzVal);
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
                data: ljxjzName
              }
            ],
            yAxis: [
              {
                type: 'value'
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
                    show: true,
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
