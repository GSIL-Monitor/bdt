<template>
  <Card>
    <p slot="title">百家乐牌桌情况</p>
    <Table :columns="columnsData" :data="rowsData"></Table>
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
            key: 'tableNo'
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
            key: 'card'
          },
          {
            title: '处理结果',
            key: 'result'
          }
        ],
        rowsData: [],
      }
    },
    created() {
      // this.setIn = setInterval(_ => {
      this.getTableInfo();
      // }, 3000)
    },
    methods: {
      getTableInfo() {
        let params = {};
        this.$api.getTableInfo(params).then(res => {
          console.log(123);
          if (res.returnCode == 200) {
            this.rowsData = res.returnObject;
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
