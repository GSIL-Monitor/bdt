<template>
  <Card class="tzSystemInfo">
    <div slot="title">
      <div>
        <Row :gutter="0" type="flex" align="middle">
          <i-col span="5"><span style="font-size: 16px;font-weight: bold">投注子系统TZ2</span>
          </i-col>
          <i-col span="19" style="text-align: right">
            <span>状态：{{!disabledSet?'正常':'停止'}}运行</span>&ensp;
            <Button type="success" @click="startApp(true)" :disabled="!disabledSet">
              {{disabledSet?'启动':'启动中...'}}
            </Button>&ensp;
            <Button type="error" @click="startApp(false)" :disabled="disabledSet">停止</Button>
          </i-col>
        </Row>
      </div>
      <div style="display: flex;align-items: flex-end">
        <div style="flex: 1;text-align: left">
          <Form style="margin: 5px 0 0 0" ref="formInline" :model="formInline" inline>
            <FormItem label="FHA" prop="user" style="margin:0 5px">
              <Input type="text" v-model="formInline.fha" :disabled="!disabledSet"
                     placeholder="">
              <Icon type="ios-person-outline" slot="prepend"></Icon>
              </Input>
            </FormItem>
            <FormItem label="FHB" prop="password" style="margin:0 5px">
              <Input type="text" v-model="formInline.xh" :disabled="!disabledSet"
                     placeholder="">
              <Icon type="ios-lock-outline" slot="prepend"></Icon>
              </Input>
            </FormItem>
          </Form>
        </div>
        <div class="" style="flex: 1;text-align: right">
          <Button type="success" @click="saveApp(true)" :disabled="!disabledSave">
            {{'保存修改'}}
          </Button>&ensp;
          <Button type="error" @click="saveApp(false)" :disabled="!disabledSave">重置修改</Button>
        </div>
      </div>
      <div
        style="width:100%;color:#000000;border-radius:4px;font-size:15px;height:45px;padding:0 5px;margin:14px 0 0;display: flex;align-items: center;background-color: rgba(0,0,0,0.1)">
        <div style="margin-right: 10px;flex: 1;">投注总数&ensp;<span
          style="">{{tz2AllData.allTz}}</span></div>
        <div style="margin-right: 10px;flex: 1;">未投注&ensp;<span
          style="">{{tz2AllData.tzNone}}</span></div>
        <div style="margin-right: 10px;flex: 1;">投注失败数&ensp;<span
          style="">{{tz2AllData.tzFail}}</span></div>
        <div style="flex: 1;">重复投注数&ensp;<span style="">{{tz2AllData.tzRepeat}}</span></div>
      </div>
    </div>
    <div class="" style="position: relative;">
      <table class="bdt-table">
        <thead>
        <tr>
          <th width="10%" align="left">
            <div class="row">
              &emsp;<Checkbox @on-change="checkBoxAllChange" v-model="checkBoxAll">全选</Checkbox>
            </div>
          </th>
          <th width="8%" align="left">
            <div class="row">账号</div>
          </th>
          <th width="10%">
            <div class="row">投注金额</div>
          </th>
          <th width="30%" align="center">
            <div class="row">投注时间限制1</div>
          </th>
          <th width="17%" align="center">
            <div class="row">投注时间限制2</div>
          </th>
          <th width="15%">
            <div class="row">投注桌号</div>
          </th>
          <th width="15%">
            <div class="row">投注方向</div>
          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item, index) in tzListData" :key="index">
          <td class="name">
            <div class="row" @click="dataClick(index)">
              &emsp;<Checkbox @on-change="checkBoxChange" v-model="item.hasCheck"></Checkbox>
            </div>
          </td>
          <td class="name">
            <div class="row">
              {{item.account}}
            </div>
          </td>
          <td>
            <div class="row">
              <Select :key="index+Math.random()" v-model="item.tzje" style="width:100%">
                <Option v-for="opt in tabJinE" :value="opt" :key="opt">{{opt}}</Option>
              </Select>
            </div>
          </td>
          <td class="time">
            <div class="row">
              <Select :key="index+Math.random()" v-model="item.time" multiple clearable
                      style="width:100%">
                <Option v-for="opt in timelineDataFun" :disabled="opt.disabled" :value="opt.value"
                        :key="opt.value">{{opt.name }}
                </Option>
              </Select>
            </div>
          </td>
          <td class="time2">
            <div class="row">
              <Select :key="index+Math.random()" v-model="item.time2" multiple clearable
                      style="width:100%">
                <Option v-for="opt in tz2Option" :disabled="opt.disabled" :value="opt.value"
                        :key="opt.value">{{opt.name }}
                </Option>
              </Select>
            </div>
          </td>
          <td align="center">
            <div class="row">
              <span>
                <Select :key="index" @on-change="tableCodeChange" v-model="item.tableCode" multiple
                        clearable style="width:100%">
                  <Option v-for="opt in tablineData" :value="opt" :key="opt">{{opt}}</Option>
                </Select>
              </span>
            </div>
          </td>
          <td align="center">
            <div class="row">
              <span>
                <Select :key="index" v-model="item.tzfx" clearable style="width:100%">
                  <Option v-for="opt in tzfx2Option" :value="opt.value" :key="opt.value">{{opt.name}}</Option>
                </Select>
              </span>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <!---->
      <!--<Spin size="large" fix v-if="!disabledSet"></Spin>-->
    </div>
  </Card>
</template>

<script>
  const startTZXT = 2;
  export default {
    name: "tzSystemInfo",
    data() {
      return {
        tzfx2Option: [
          {name: '庄', value: '1'},
          {name: '闲', value: '2'},
          {name: '和', value: '3'}
        ],
        tz2Option: [
          {name: '00-20', value: '00-20'},
          {name: '20-40', value: '20-40'},
          {name: '40-60', value: '40-60'}
        ],
        tz2AllData: {},
        disabledSet: true,
        disabledSave: true,
        tabJinE: [50, 100, 200, 500, 1000, 5000, 10000],
        tablineData: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        timelineData: [
          {name: '00:00~01:00', value: '00:00~01:00', hours: '1'},
          {name: '01:00~02:00', value: '01:00~02:00', hours: '2'},
          {name: '02:00~03:00', value: '02:00~03:00', hours: '3'},
          {name: '03:00~04:00', value: '03:00~04:00', hours: '4'},
          {name: '04:00~05:00', value: '04:00~05:00', hours: '5'},
          {name: '05:00~06:00', value: '05:00~06:00', hours: '6'},
          {name: '06:00~07:00', value: '06:00~07:00', hours: '7'},
          {name: '07:00~08:00', value: '07:00~08:00', hours: '8'},
          {name: '08:00~09:00', value: '08:00~09:00', hours: '9'},
          {name: '09:00~10:00', value: '09:00~10:00', hours: '10'},
          {name: '10:00~11:00', value: '10:00~11:00', hours: '11'},
          {name: '11:00~12:00', value: '11:00~12:00', hours: '12'},
          {name: '12:00~13:00', value: '12:00~13:00', hours: '13'},
          {name: '13:00~14:00', value: '13:00~14:00', hours: '14'},
          {name: '14:00~15:00', value: '14:00~15:00', hours: '15'},
          {name: '15:00~16:00', value: '15:00~16:00', hours: '16'},
          {name: '16:00~17:00', value: '16:00~17:00', hours: '17'},
          {name: '17:00~18:00', value: '17:00~18:00', hours: '18'},
          {name: '18:00~19:00', value: '18:00~19:00', hours: '19'},
          {name: '19:00~20:00', value: '19:00~20:00', hours: '20'},
          {name: '20:00~21:00', value: '20:00~21:00', hours: '21'},
          {name: '21:00~22:00', value: '21:00~22:00', hours: '22'},
          {name: '22:00~23:00', value: '22:00~23:00', hours: '23'},
          {name: '23:00~24:00', value: '23:00~24:00', hours: '24'}
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
        formInline: {
          fha: '',
          xh: ''
        },
        checkBoxAll: false,
        tableData: [
          {
            name: '',
            time: '',
            jine: '',
            tab: '1'
          }
        ],
        tzListData: [],
        tzSystem: {}
      }
    },
    activated() {

    },
    computed: {
      timelineDataFun() {
        let xiaoshi = new Date().getHours();
        this.timelineData.forEach((e) => {
          if (parseInt(e.hours) <= parseInt(xiaoshi)) {
            // e.disabled = true;
          }
        })
        console.log(this.timelineData);
        return this.timelineData
      }
    },
    created() {
      this.getTzSystemInfo(startTZXT);
    },
    methods: {
      saveApp(type) {
        if (type) {
          this.$Notice.open({title: '提示', desc: '当前修改保存成功，从新启动投注系统可应用', duration: 2});
          this.updateTzCheck();
        } else {
          this.getTzSystemInfo(startTZXT);
          setTimeout(_ => {
            this.$Message.success({content: 'TZXT2更新成功', duration: 10, closable: true});
          }, 500)
        }
      },
      updateTzCheck() {
        this.tzListData.forEach((e) => {
          e.adminId = this.$cookie.get('token')
          e.tzsjSection1 = e.time.join(',');
          e.tzsjSection2 = e.time2.join(',');
          e.tableNo = e.tableCode.join(',');
        });
        // let params = {
        //   list: this.tzListData
        // };
        let params = this.tzListData
        this.$api.updateTzCheck(params).then(res => {
          if (res.data.returnCode == 200) {
            // this.$Message.success({content: '更新成功', duration: 10, closable: true});
          }
        }).catch(err => {

        })
      },
      setCheckBoxAll() {
        setTimeout(() => {
          let setBox = true;
          this.tzListData.forEach((e) => {
            if (e.hasCheck == false) {
              setBox = false;
            }
          })
          this.checkBoxAll = setBox;
          console.log(setBox);
        })
      },
      //
      dataClick(index) {
        //
        this.setCheckBoxAll();
        //
        console.log(this.tzListData[index]);
      },
      //
      tableCodeChange(val) {
        if (val.length > 6) {
          this.$Message.info({content: '最多选择6桌', duration: 10, closable: true});
        }
        console.log(val);
      },
      //
      checkBoxChange(val) {
        console.log(val);
      },
      //
      checkBoxAllChange(val) {
        console.log(val);
        this.tzListData.forEach((e) => {
          e.hasCheck = val;
        })
      },
      //
      startApp(start) {
        this.disabledSet = !start;
        this.tzSystemStarted(2);
      },
      //
      getTzSystemInfo(type) {
        let params = {tzxt: type}
        this.$api.getTzSystemInfo(params).then(res => {
          console.log(res);
          if (res.data.returnCode == 200) {
            this.tzListData = res.data.returnObject.list;
            this.tzListData.forEach((e) => {
              e.adminId = this.$cookie.get('token')
              if (e.tzsjSection1 == null) {
                e.tzsjSection1 = '';
              }
              if (e.tzsjSection2 == null) {
                e.tzsjSection2 = '';
              }
              if (e.tableNo == null) {
                e.tableNo = '';
              }
              if (e.tzje == null) {
                e.tzje = '50';
              }
              if (e.tzfx == null) {
                e.tzfx = '1';
              }
              e.tzfx = e.tzfx.toString()
              if (e.hasCheck == null) {
                e.hasCheck = false;
              }
              e.time = e.tzsjSection1.split(',');
              e.time2 = e.tzsjSection2.split(',');
              e.tableCode = e.tableNo.split(',');
              console.log(e);
            });
            this.setCheckBoxAll();
            this.tz2AllData = res.data.returnObject;
            this.tzSystem = res.data.returnObject.tzSystem;
            this.formInline = JSON.parse(JSON.stringify(this.tzSystem));
            this.disabledSet = !this.tzSystem.started;
          }
        }).catch(err => {

        })
      },
      //
      tzSystemStarted(tzxt) {
        //
        this.tzListData.forEach((e) => {
          e.adminId = this.$cookie.get('token')
          e.tzsjSection1 = e.time.join(',');
          e.tzsjSection2 = e.time2.join(',');
          e.tableNo = e.tableCode.join(',');
        });
        //
        //
        let data = Object.assign({}, this.formInline, {
          adminId: this.$cookie.get('token'),
          "list": this.tzListData,
          "started": !this.disabledSet,
          "tzxt": tzxt,
        });
        this.$api.tzSystemStarted(data).then((res) => {
          if (res.data.returnCode == 200) {
            if (!this.disabledSet) {
              this.getTzSystemInfo(startTZXT);
            }
          }
        }).catch(() => {

        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .tzSystemInfo {
    .bdt-table {
      width: 100%;
      border-radius: 4px;
      border: 1px solid #e3e3e3;

      thead {
        tr {
          background: #f5f7fa;
          border-bottom: 1px solid #f5f7fa;
          border-radius: 3px;
        }

        th {
          // text-align: left;
          .row {
            padding: 16px 5px;
            font-size: 15px;
            font-weight: bold;
          }
        }
      }

      tbody > tr:nth-child(even) {
        background-color: #f5f7fa;
      }

      tbody {
        td {
          text-align: left;
        }

        tr {
          td {
            padding: 5px 0;
          }

          border-bottom: 1px solid #dcdee2;

          &:hover {
            background-color: rgba(255, 177, 72, 0.2);
          }
        }
      }

      .row {
        padding: 5px;
      }
    }
  }

</style>
