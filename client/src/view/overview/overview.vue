<template>
  <div class="bdt-overview">
    <Row :gutter="16">
      <i-col span="6">
        <bdt-run></bdt-run>
        <br>
        <Card>
          <p slot="title">账号登录情况</p>
          <Card :key="i" v-for="(item, i) in isLoginOverviewData">
            <template slot="title">
              <div class="header-box" style="font-size: 16px">
                <div class="col" style="font-weight: bold;">{{item.name}}</div>
                <div class="col" v-if="item.loginStatus">
                  <Button type="primary" ghost size="small">{{'登陆'}}</Button>
                </div>
                <div class="col" v-else>
                  <Button type="error" ghost size="small">{{'未登录'}}</Button>
                </div>
                <div class="col" style="text-align: right;flex: inherit">
                  <Button type="primary" size="small">编辑</Button>
                </div>
              </div>
              <div class="header-box" style="height: 20px"><span class="col">{{item.ipRegion}}/{{item.ipAddress}}</span>
              </div>
            </template>
            <div class="header-box">
              <div class="col">有效金额：{{item.effectiveAmount}}</div>
              <div class="col" style="text-align: right"><a href="">查看统计</a></div>
            </div>
          </Card>
        </Card>
      </i-col>
      <i-col span="18">
        <Card>
          <p slot="title">百家乐牌桌情况</p>
          <Table :columns="columnsData" :data="rowsData"></Table>
        </Card>
        <br>
        <Card>
          <CellGroup slot="title">
            <Cell title="LJXJZ" label="现值：10,000"/>
          </CellGroup>
          <div id="overLine">
            <example style="height: 310px;"/>
          </div>
        </Card>
        <br>
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
              <FormItem label="TZ1FH" prop="user" style="margin:0 5px">
                <Input type="text" v-model="formInline.user" placeholder="Username">
                  <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
              </FormItem>
              <FormItem label="TZ1XH" prop="password" style="margin:0 5px">
                <Input type="password" v-model="formInline.password" placeholder="Password">
                  <Icon type="ios-lock-outline" slot="prepend"></Icon>
                </Input>
              </FormItem>
            </Form>
          </div>
          <div class="">
            <table class="bdt-table">
              <thead>
              <tr>
                <th>
                  <div class="row">账号</div>
                </th>
                <th>
                  <div class="row">投注金额</div>
                </th>
                <th>
                  <div class="row">投注时间限制</div>
                </th>
                <th>
                  <div class="row">投注桌号</div>
                </th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item, index) in tableData" :key="index">
                <td class="name">
                  <div class="row">
                    <Select size="small" :key="index" v-model="item.name" clearable
                            style="width:100px">
                      <Option v-for="op in cityList" :value="op.value" :key="op.value">
                        {{op.label }}
                      </Option>
                    </Select>
                  </div>
                </td>
                <td>
                  <div class="row">{{index}}</div>
                </td>
                <td class="time">
                  <div class="row">
                    <Select size="small" :key="index+Math.random()" v-model="item.time" clearable
                            style="width:150px">
                      <Option v-for="op1 in timelineData" :value="op1.name" :key="op1.name">
                        {{op1.name }}
                      </Option>
                    </Select>
                  </div>
                </td>
                <td>{{index}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </Card>
        <br>
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
          <div class="">
            <table class="bdt-table">
              <thead>
              <tr>
                <th>
                  <div class="row">账号</div>
                </th>
                <th>
                  <div class="row">投注金额</div>
                </th>
                <th>
                  <div class="row">投注时间限制</div>
                </th>
                <th>
                  <div class="row">投注桌号</div>
                </th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item, index) in tableData" :key="index">
                <td class="name">
                  <div class="row">
                    <Select size="small" :key="index" v-model="item.name" clearable
                            style="width:100px">
                      <Option v-for="op in cityList" :value="op.value" :key="op.value">
                        {{op.label }}
                      </Option>
                    </Select>
                  </div>
                </td>
                <td>
                  <div class="row">{{index}}</div>
                </td>
                <td class="time">
                  <div class="row">
                    <Select size="small" :key="index+Math.random()" v-model="item.time" clearable
                            style="width:150px">
                      <Option v-for="op1 in timelineData" :value="op1.name" :key="op1.name">
                        {{op1.name }}
                      </Option>
                    </Select>
                  </div>
                </td>
                <td>{{index}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </Card>
        <br>
      </i-col>
    </Row>
  </div>
</template>

<script>
  import bdtRun from './components/bdt-run.vue'
  import Example from './components/example.vue'

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
        cityList: [
          {
            value: 'New York',
            label: 'New York'
          },
          {
            value: 'London',
            label: 'London'
          },
          {
            value: 'Sydney',
            label: 'Sydney'
          },
          {
            value: 'Ottawa',
            label: 'Ottawa'
          },
          {
            value: 'Paris',
            label: 'Paris'
          },
          {
            value: 'Canberra',
            label: 'Canberra'
          }
        ],
        tableData: [
          {
            name: '1',
            time: '1'
          }
        ],
        switchValue: false,
        qqFans: '',
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
        tz1Data: '',
        tz2Data: '',
        formInline: {
          user: '',
          password: ''
        },
        isLoginOverviewData: []
      }
    },
    components: {
      Example,
      bdtRun
    },
    watch: {
      $route: function (to, form) {
        // console.log(to);
      }
    },
    created() {
      console.log(process.env);
      this.getTableInfo();
      this.getTzSystemInfo(1);
      this.getTzSystemInfo(2);
      this.getAdminAccount();
      //
      this.getUserByAdmin();
    },
    activated() {

    },
    mounted() {
      console.log(this.$config);
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
      },
      getTzSystemInfo(type) {
        let params = {tzxt: type}
        this.$api.getTzSystemInfo(params).then(res => {
          console.log(res);
          if (res.returnCode == 200) {
            if (type == 1) {
              this.tz1Data = res.returnObject;
            } else {
              this.tz2Data = res.returnObject;
            }
          }
        }).catch(err => {

        })
      },
      //
      getAdminAccount() {
        let params = {adminId: this.$cookie.get('token')};
        this.$api.getAdminAccount(params).then(res => {
          console.log(res);
        }).catch(err => {

        })
      },
      //
      getUserByAdmin() {
        let params = {adminId: this.$cookie.get('token')};
        this.$api.getUserByAdmin(params).then(res => {
          if (res.returnCode == 200) {
            this.isLoginOverviewData = res.returnObject;
          }
        }).catch(err => {

        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .bdt-table {
    width: 100%;
    thead {
      tr {
        background: #eeeeee;
        border-bottom: 1px solid #c3c3c3;
      }
      td {
        padding: 5px;
      }
    }
    tbody {
      td {
        text-align: center;
        &.name {
          width: 120px;
        }
        &.time {
          width: 150px;
        }
      }
      tr {
        border-bottom: 1px solid #c3c3c3;
      }
    }
    .row {
      padding: 5px;
    }
  }

  .bdt-overview {
    min-width: 1300px;
    min-height: 3000px;
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
