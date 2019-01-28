<template>
  <Card :padding="10" class="tz-overview">
    <Tabs type="card" class="tabs-pane-box" @on-click="tabsClick" v-model="tabsVal">
      <Tab-pane v-for="(item, index) in tabsDataList" :key="index" :label="item.label"
                :name="item.name"></Tab-pane>
    </Tabs>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </Card>
</template>

<script>
  // import Example from './components/example.vue'
  // import Example1 from './components/example1.vue'

  export default {
    name: 'overview',
    data() {
      return {
        tabsVal: 'control-lz',
        tabsDataList: [
          {label: "总览", name: "control-lz"},
          {label: "ljzjz-LA", name: "control-la"},
          {label: "ljzjz-LB", name: "control-lb"},
          {label: "ljzjz-LC", name: "control-lc"},
          {label: "ljzjz-LD", name: "control-ld"},
          {label: "ljzjz-LE", name: "control-le"},
          {label: "ljzjz-LF", name: "control-lf"},
          {label: "ljzjz-LG", name: "control-lg"},
        ],
        userInfo: {},
        isSuper: false
      }
    },
    components: {
      // Example,
      // Example1
    },
    watch: {
      $route: function (to, form) {
        this.tabsVal = to.name;
      }
    },
    created() {
      this.tabsVal = this.$route.name;
    },
    activated() {
      this.tabsVal = this.$route.name;
    },
    mounted() {
      //
      console.log(this.$config);
    },
    methods: {
      tabsClick(all) {
        console.log(all);
        this.$router.push({path: `/${all.replace('-', '/')}`})
      },
      tableDataChange(val) {
        console.log('=====>', val);
      }
    }
  }
</script>

<style lang="less" scoped>
  .tz-overview {
    min-width: 1300px;

    .header-box {
      display: flex;
      align-items: center;

      .col {
        flex: 1;
      }
    }
  }
  .qq-group-img {
    display: block;
    margin: 0 auto;
    width: 240px;
  }
  .qq-group-intro {
    padding: 20px;
    font-size: 16px;
  }
</style>
