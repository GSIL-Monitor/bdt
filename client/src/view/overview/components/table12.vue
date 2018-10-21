<template>
  <Card>
    <p slot="title">百家乐牌桌情况{{getTableInfoStatus? '（正常运行）': ''}}</p>
    <Table stripe :columns="columnsData" :data="rowsData"></Table>
  </Card>
</template>

<script>
  export default {
    name: "table-12",
    data() {
      return {
        columnsData: [
          {
            title: '桌号',
            key: 'tableNo',
            width: 80
          },
          {
            title: '更新时间',
            key: 'update'
          },
          {
            title: '局号',
            key: 'battleNo'
          },
          {
            title: '副号',
            key: 'fitNo'
          },
          {
            title: '状态',
            key: 'setStatus'
          },
          {
            title: '处理结果',
            key: 'setResult'
          }
        ],
        rowsData: [],
        wads: {
          '1': '庄',
          '2': '闲',
          '3': '和',
          'null': '--'
        },
        status: {
          '2': '可投注',
          '3': '开牌',
          '1': '新局准备',
          'null': '--',
          '0': '停止投注'
        },
        /* '开始投注': 2,
      '开牌': 3,
      '停止投注': 0,
      '洗牌中': 1*/
        getTableInfoStatus: true
      }
    },
    beforeDestroy() {
      clearInterval(window.setIn);
    },
    created() {
      this.getTableInfo();
      window.setIn = setInterval(_ => {
        this.getTableInfo();
      }, 1000 * 3)
    },
    activated() {

    },
    methods: {
      getTableInfo() {
        let params = {};
        this.$api.getTableInfo(params).then((res) => {
          if (res.returnCode == 200) {
            this.getTableInfoStatus = true;
            this.rowsData = [];
            this.rowsData = res.returnObject;
            this.rowsData.forEach((e) => {
              e.setStatus = this.status[e.status]
              e.setResult = e.result
              e.update = this.formatDateTime(e.updateTime)
            })
            console.log('1231231===>', this.rowsData);
            //
            this.$emit("on-change", this.rowsData);
            //
          }
        }).catch((err) => {
          this.getTableInfoStatus = false;
        })
      },
      formatDateTime(inputTime) {
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
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
