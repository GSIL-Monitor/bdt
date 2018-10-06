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
          <Table :columns="columnsData" :data="rowsData"></Table>
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
      </i-col>
    </Row>
  </div>
</template>

<script>
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
    created() {
      console.log(process.env);
      this.getTableInfo();
      this.getTzSystemInfo();
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
      getTzSystemInfo() {
        let params = {tzxt: '1'}
        this.$api.getTzSystemInfo(params).then(res => {
          console.log(res);
          if (res.returnCode == 200) {
            this.tz1Data = '';
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
