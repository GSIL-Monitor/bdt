<template>
  <div id="super">
    <Table ref="tables" :columns="tableTitle" :data="tableData"></Table>
  </div>
</template>

<script>
  export default {
    name: "super",
    data() {
      return {
        tableTitle: [
          {
            title: '日期',
            key: 'created'
          },
          {
            title: '时间',
            key: 'createTime'
          },
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
            title: '庄',
            key: 'card'
          },
          {
            title: '闲',
            key: 'xianCard'
          },
          {
            title: '结果',
            key: 'setResult'
          }
        ],
        tableData: [],
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
