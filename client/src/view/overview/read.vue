<template>
  <div class="bdt-overview">
    <Row :gutter="16">
      <i-col span="24">
        <table12 @on-change="tableDataChange"></table12>
        <br>
      </i-col>
    </Row>
  </div>
</template>

<script>
  import table12 from './components/table12'

  export default {
    name: 'overview',
    data() {
      return {
      }
    },
    components: {
      table12
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
