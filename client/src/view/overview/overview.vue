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
          <Table :columns="columns1" :data="data1"></Table>
        </Card>
      </i-col>
    </Row>
    <br style="height: 16px">
    <Row :gutter="16">
      <i-col span="8">
        <Card>
          <p slot="title">账号登录情况</p>
          <CellGroup>
            <Cell title="Only show titles"/>
            <Cell title="Display label content" label="label content"/>
            <Cell title="Display right content" extra="details"/>
            <Cell title="Link" extra="details"/>
            <Cell title="Open link in new window" target="_blank"/>
            <Cell title="Disabled"/>
            <Cell title="Selected" selected/>
            <Cell title="With Badge" to="">
              <Badge :count="10" slot="extra"/>
            </Cell>
            <Cell title="With Switch">
              <Switch v-model="switchValue" slot="extra"/>
            </Cell>
          </CellGroup>
        </Card>
      </i-col>
      <!---->
      <i-col span="8">
        <Card>
          <CellGroup slot="title">
            <Cell title="LJXJZ" label="现值：10,000"/>
          </CellGroup>
          <div>23423</div>
        </Card>
      </i-col>
      <i-col span="8">
        <Card>
          <CellGroup slot="title">
            <Cell title="LJZJZ" label="现值：10,000"/>
          </CellGroup>
          <div>23423432</div>
        </Card>
      </i-col>
    </Row>
    <br>
    <!---->
    <Row :gutter="16">
      <i-col span="12">
        <Card>
          <div slot="title">
            <div>
              <Row :gutter="0" type="flex" align="middle">
                <i-col span="5"><span style="font-size: 16px;font-weight: bold">投注子系统TZ1</span>
                </i-col>
                <i-col span="19" style="text-align: right">
                  <span>状态：{{'正常运行'}}</span>&ensp;
                  <Button type="success" disabled>启动</Button>&ensp;
                  <Button type="error">停止</Button>
                </i-col>
              </Row>
            </div>
            <Form style="margin: 5px 0 0 0" ref="formInline" :model="formInline" inline>
              <FormItem prop="user" style="margin:0 5px">
                <Input type="text" v-model="formInline.user" placeholder="Username">
                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
              </FormItem>
              <FormItem prop="password" style="margin:0 5px">
                <Input type="password" v-model="formInline.password" placeholder="Password">
                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
                </Input>
              </FormItem>
            </Form>
          </div>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
      <i-col span="12">
        <Card>
          <div slot="title">
            <div>
              <Row :gutter="0" type="flex" align="middle">
                <i-col span="5"><span style="font-size: 16px;font-weight: bold;">投注子系统TZ1</span>
                </i-col>
                <i-col span="19" style="text-align: right">
                  <span>状态：{{'正常运行'}}</span>&ensp;
                  <Button type="success">启动</Button>&ensp;
                  <Button type="error" disabled>停止</Button>
                </i-col>
              </Row>
            </div>
            <Form style="margin: 5px 0 0 0" ref="formInline" :model="formInline" inline>
              <FormItem prop="user" style="margin:0 5px">
                <Input type="text" v-model="formInline.user" placeholder="Username">
                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
              </FormItem>
              <FormItem prop="password" style="margin:0 5px">
                <Input type="password" v-model="formInline.password" placeholder="Password">
                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
                </Input>
              </FormItem>
            </Form>
          </div>
          <p>卡片内容</p>
          <p>卡片内容</p>
          <p>卡片内容</p>
        </Card>
      </i-col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: 'overview',
    data() {
      return {
        switchValue: false,
        qqFans: '',
        columns1: [
          {
            title: 'Name',
            key: 'name'
          },
          {
            title: 'Age',
            key: 'age'
          },
          {
            title: 'Address',
            key: 'address'
          }
        ],
        data1: [
          {
            name: 'John Brown',
            age: 18,
            address: 'New York No. 1 Lake Park',
            date: '2016-10-03'
          },
          {
            name: 'Jim Green',
            age: 24,
            address: 'London No. 1 Lake Park',
            date: '2016-10-01'
          },
          {
            name: 'Joe Black',
            age: 30,
            address: 'Sydney No. 1 Lake Park',
            date: '2016-10-02'
          },
          {
            name: 'Jon Snow',
            age: 26,
            address: 'Ottawa No. 2 Lake Park',
            date: '2016-10-04'
          }
        ],
        formInline: {
          user: '',
          password: ''
        }
      }
    },
    watch: {
      $route: function (to, form) {
        // console.log(to);
      }
    },
    mounted() {
      console.log(this.$config);
    },
    methods: {}
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
