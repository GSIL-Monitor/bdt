<template>
  <div class="bdt-overview">
    <Row :gutter="16">
      <i-col span="5">
        <bdt-run></bdt-run>
        <br>
        <login-out></login-out>
      </i-col>
      <i-col span="19">
        <table12 @on-change="tableDataChange"></table12>
        <br>
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
  import bdtRun from './components/bdt-run.vue'
  import Example from './components/example.vue'
  import loginOut from './components/loginOut.vue'
  import table12 from './components/table12'
  import tzSystemInfo from './components/tzSystemInfo.vue'
  import tz2SystemInfo from './components/tz2SystemInfo.vue'
  import {setToken, getToken, setInfo} from '@/libs/util'

  export default {
    name: 'overview',
    data() {
      return {
        timelineData: [
          {name: '00:00~01:00', value: '00:00~01:00'},
          {name: '01:00~02:00', value: '01:00~02:00'},
          {name: '02:00~03:00', value: '02:00~03:00'},
          {name: '03:00~04:00', value: '03:00~04:00'},
          {name: '04:00~05:00', value: '04:00~05:00'},
          {name: '05:00~06:00', value: '05:00~06:00'},
          {name: '06:00~07:00', value: '06:00~07:00'},
          {name: '07:00~08:00', value: '07:00~08:00'},
          {name: '08:00~09:00', value: '08:00~09:00'},
          {name: '09:00~10:00', value: '09:00~10:00'},
          {name: '10:00~11:00', value: '10:00~11:00'},
          {name: '11:00~12:00', value: '11:00~12:00'},
          {name: '12:00~13:00', value: '12:00~13:00'},
          {name: '13:00~14:00', value: '13:00~14:00'},
          {name: '14:00~15:00', value: '14:00~15:00'},
          {name: '15:00~16:00', value: '15:00~16:00'},
          {name: '16:00~17:00', value: '16:00~17:00'},
          {name: '17:00~18:00', value: '17:00~18:00'},
          {name: '18:00~19:00', value: '18:00~19:00'},
          {name: '19:00~20:00', value: '19:00~20:00'},
          {name: '20:00~21:00', value: '20:00~21:00'},
          {name: '21:00~22:00', value: '21:00~22:00'},
          {name: '22:00~23:00', value: '22:00~23:00'},
          {name: '23:00~00:00', value: '23:00~00:00'}
        ],
        tableData: [],
        switchValue: false,
        qqFans: '',
        tz1Data: '',
        tz2Data: ''
      }
    },
    components: {
      Example,
      bdtRun,
      loginOut,
      table12,
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
