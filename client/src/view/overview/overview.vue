<template>
  <div class="bdt-overview">
    <Row :gutter="16">
      <i-col span="8">
        <Card>
          <p slot="title">BDT运行状态</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
      <i-col span="16">
        <Card>
          <p slot="title">百家乐牌桌情况</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
    </Row>
    <br style="height: 16px">
    <Row :gutter="16">
      <i-col span="8">
        <Card>
          <p slot="title">账号登录情况</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
      <i-col span="8">
        <Card>
          <p slot="title">投注子系统TZ1</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
      <i-col span="8">
        <Card>
          <p slot="title">标准卡片</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
    </Row>
    <br>
    <Row :gutter="16">
      <i-col span="6">col-6</i-col>
      <i-col span="6">col-6</i-col>
      <i-col span="6">col-6</i-col>
      <i-col span="6">col-6</i-col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: 'overview',
    data() {
      return {
        qqFans: ''
      }
    }
  }
</script>

<style scoped>
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
