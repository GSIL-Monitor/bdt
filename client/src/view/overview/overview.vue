<template>
  <div class="bdt-overview">
    <Row :gutter="16">
      <i-col span="6">
        <template v-if="mainAdmin">
          <bdt-run></bdt-run>
          <br>
        </template>
        <login-out></login-out>
      </i-col>
      <i-col span="18" v-if="mainAdmin">
        <tz11-system-info></tz11-system-info>
        <br>
        <tz12-system-info></tz12-system-info>
        <br>
        <tz13-system-info></tz13-system-info>
        <br>
        <tz14-system-info></tz14-system-info>
        <br>
        <tz2-system-info></tz2-system-info>
        <br>
        <tz3-system-info></tz3-system-info>
        <br>
      </i-col>
    </Row>
  </div>
</template>

<script>
  import bdtRun from './components/bdt-run.vue'
  import loginOut from './components/loginOut.vue'
  import tz11SystemInfo from './components/tz11SystemInfo.vue'
  import tz12SystemInfo from './components/tz12SystemInfo.vue'
  import tz13SystemInfo from './components/tz13SystemInfo.vue'
  import tz14SystemInfo from './components/tz14SystemInfo.vue'
  //
  import tz2SystemInfo from './components/tz2SystemInfo.vue'
  import tz3SystemInfo from './components/tz3SystemInfo.vue'
  import {getInfo} from '../../libs/util'
  export default {
    name: 'overview',
    data() {
      return {
        mainAdmin: false
      }
    },
    components: {
      bdtRun,
      loginOut,
      tz11SystemInfo,
      tz12SystemInfo,
      tz13SystemInfo,
      tz14SystemInfo,
      tz2SystemInfo,
      tz3SystemInfo
    },
    watch: {
      $route(to, form) {
        // console.log(to);
      }
    },
    created() {
      console.log(process.env);
      console.log(getInfo());
      if (getInfo().mainAdminId == null || getInfo().mainAdminId == '') {
        this.mainAdmin = true;
      } else {
        this.mainAdmin = false;
      }
    },
    activated() {

    },
    mounted() {
      console.log(this.$config);
    },
    methods: {
      tableDataChange(val) {
        console.log('=====>', val);
      }
    }
  }
</script>

<style lang="less" scoped>
  .bdt-overview {
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
