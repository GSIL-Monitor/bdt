<template>
  <div class="tz-overview">
    <Row :gutter="16">
      <i-col span="24">
        <example></example>
        <br>
        <tz-system-info></tz-system-info>
        <br>
        <tz2-system-info></tz2-system-info>
        <br>
      </i-col>
    </Row>
  </div>
</template>

<script>
  import Example from './components/example.vue'
  import tzSystemInfo from './components/tzSystemInfo.vue'
  import tz2SystemInfo from './components/tz2SystemInfo.vue'
  import {setToken, getToken, setInfo} from '@/libs/util'

  export default {
    name: 'overview',
    data() {
      return {
      }
    },
    components: {
      Example,
      tzSystemInfo,
      tz2SystemInfo
    },
    watch: {
      $route: function (to, form) {
        // console.log(to);
      }
    },
    created() {
      console.log(process.env);

      // this.getAdminAccount();
      //
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
