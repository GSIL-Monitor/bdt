<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {};
    },
    created() {
      console.log(process.env.NODE_ENV);
    }
  }
</script>

<style lang="less">
  .tabs-pane-box {
    &.ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab-active {
      background-color: #348EED;
      color: white;
    }
  }
  .size {
    width: 100%;
    height: 100%;
  }
  html, body {
    .size;
    overflow: hidden;
    margin: 0;
    padding: 0;
  }
  .dbt-scrollbar {
    overflow-y: auto;
  }
  .dbt-scrollbar::-webkit-scrollbar {
    width: 16px;
    background-color: rgba(217, 217, 217, 1);
  }
  .dbt-scrollbar::-webkit-scrollbar-thumb {
    background-color: #aeaeae;
    border-radius: 16px;
  }
  .dbt-scrollbar::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 12px rgba(217, 217, 217, 1);
    background-color: #fff;
  }
  ::-webkit-scrollbar {
    width: 16px;
    background-color: rgba(217, 217, 217, 1);
  }
  ::-webkit-scrollbar-thumb {
    background-color: #aeaeae;
    border-radius: 16px;
  }
  ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 12px rgba(217, 217, 217, 1);
    background-color: #fff;
  }
  #app {
    .size;
  }
</style>
