<template>
  <div class="betting">
    <Card style="margin:0 0 50px 0">
      <div slot="title">
        <div class="flex">
          <div class="col">
            <DatePicker type="date" v-model="desktopTimeVal" @on-change="timeChange"
                        placeholder="Select date" style="width: 200px"></DatePicker>
          </div>
          <div class="col">
            <Select :key="'index'" v-model="desktopVal" @on-change="desktopChange"
                    style="width:150px">
              <Option v-for="op in desktopOption" :value="op.value" :key="op.value">
                {{op.name }}
              </Option>
            </Select>
          </div>
          <div class="col">
            <Select :key="'index'" v-model="desktopUserVal" @on-change="desktopAccountChange"
                    style="width:150px">
              <Option v-for="op in isLoginOverviewData" :value="op.id" :key="op.id">
                {{op.account }}
              </Option>
            </Select>
          </div>
          <div class="col" style="text-align: right">
            <Button type="warning" @click="searchDopeData">刷新</Button>
          </div>
          <div class="col page">
            <Page :total="page.total" show-sizer :page-size="page.size"
                  :page-size-opts="pageSizeOpt" @on-change="pageChange" :current.sync="page.index"
                  @on-page-size-change="pageSizeChange"></Page>
          </div>
          <div class="col btn">
            <Button icon="md-download" :loading="exportLoading" @click="exportExcel">导出CVS</Button>
          </div>
        </div>
      </div>
      <div class="flex border-flex">
        <div class="col">总有效金额：{{ztjData.yxje}}</div>
        <div class="col">总原始输赢：{{ztjData.yssy}}</div>
        <div class="col">总实际金额：{{ztjData.sjsy}}</div>
      </div>
      <div style="margin-top: 20px">
        <Table ref="tables" :columns="tableTitle" :data="tableData"></Table>
      </div>
    </Card>

  </div>
</template>

<script>
  import excel from '@/libs/excel'

  export default {
    name: 'betting',
    data() {
      return {
        pageSizeOpt: [30, 50, 100, 200, 500, 1000],
        page: {
          index: 1,
          size: 30,
          total: 100
        },
        desktopVal: '0',
        desktopTimeVal: '',
        desktopUserVal: '0',
        desktopOption: [
          {name: '全部投注系统', value: '0'},
          {name: 'TZXT1', value: '1'},
          {name: 'TZXT2', value: '2'},
        ],
        ztjData: {
          sjsy: 9867.98,
          yssy: 8928.5,
          yxje: 78290
        },
        exportLoading: false,
        tableTitle: [
          {
            title: '日期',
            key: 'created'
          },
          {
            title: '时间',
            key: 'createdTime'
          },
          {
            title: '有效金额',
            key: 'yxje'
          },
          {
            title: '原始输赢',
            key: 'yssy'
          },
          {
            title: '实际输赢',
            key: 'sjsy'
          },
          {
            title: '投注桌号',
            key: 'tableNo'
          },
          {
            title: '投注局号',
            key: 'battleNo'
          },
          {
            title: '投注副号',
            key: 'fitNo'
          },
          {
            title: '投注方式',
            key: 'tzxt'
          },
          {
            title: '投注方向',
            key: 'tzfx'
          },
          {
            title: '投注账号',
            key: 'account'
          },
          {
            title: '投注状态',
            key: 'tzzt'
          }
        ],
        wads: {
          '1': '庄',
          '2': '闲',
          '3': '和',
          'null': '--'
        },
        tzStatus: {
          'true': '成功',
          'false': '投注失败',
          'null': '投注超时'
        },
        tableData: [],
        isLoginOverviewData: []
      }
    },
    mounted() {
    },
    created() {
      this.getUserByAdmin();
      setTimeout(() => {
        this.searchDopeData();
      }, 300)
      console.log(process, process.env);
      // console.log(this.$api.getlogin({}));
    },
    methods: {
      getUserByAdmin() {
        let params = {adminId: this.$cookie.get('token')};
        this.$api.getUserByAdmin(params).then((res) => {
          if (res.data.returnCode == 200) {
            this.isLoginOverviewData = res.data.returnObject;
            this.isLoginOverviewData.forEach((e) => {
              e.value = e.account
            })
            this.isLoginOverviewData.unshift({account: '全部账户', id: '0'})
            console.log('1111=>', this.isLoginOverviewData);
          }
        }).catch((err) => {

        })
      },
      //
      desktopAccountChange(i) {
        this.searchDopeData();
      },
      desktopChange(i) {
        this.searchDopeData();
      },
      timeChange(val) {
        // console.warn(this.desktopTimeVal.getFullYear());
        console.warn(val);
        this.searchDopeData();
      },
      pageSizeChange(index) {
        this.page.size = index;
        console.log(index);
        this.searchDopeData();
      },
      pageChange(index) {
        this.page.index = index;
        console.log(index);
        this.searchDopeData();
      },
      formatDate(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '/' + m + '/' + d;
      },
      formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return h + ':' + minute + ':' + second;
      },
      searchDopeData() {
        let desktopTimeVal = '';
        if (this.desktopTimeVal != '' && this.desktopTimeVal != null) {
          desktopTimeVal = new Date(this.desktopTimeVal).getTime()
        }
        let desktopVal = '';
        if (this.desktopVal != 0) {
          desktopVal = this.desktopVal;
        }
        let desktopUserVal = '';
        if (this.desktopUserVal != 0) {
          desktopUserVal = this.desktopUserVal;
        }
        let params = {
          tzzh: desktopUserVal,
          tzxt: desktopVal,
          createTime: desktopTimeVal,
          pageNum: this.page.index,
          pageSize: this.page.size
        };

        this.$api.searchDopeData(params).then((res) => {
          if (res.data.returnCode == 200) {
            this.page.total = res.data.total;
            this.ztjData = {
              sjsy: res.data.returnObject.sjsy,
              yssy: res.data.returnObject.yssy,
              yxje: res.data.returnObject.yxje
            }
            this.tableData = res.data.returnObject.list;
            var timestamp = new Date().getTime();
            this.tableData.forEach((e, i) => {
              e.setResult = this.wads[e.result]
              e.created = this.formatDate(e.createTime)
              e.createdTime = this.formatDateTime(e.createTime)
              e.tzxt = '投注系统' + e.tzxt;
              e.tzzt = this.tzStatus[e.tzzt];
              // console.log(e.createTime - timestamp);
              if (e.tzzt == '投注超时' && timestamp - e.createTime < (2 * 60 * 1000)) {
                e.tzzt = '等待投注';
              }
              //
              e.tzfx = this.wads[e.tzfx];
              Object.keys(e).forEach((k) => {
                if (e[k] == null) {
                  e[k] = '--'
                }
              })
            })
          }
        })
      },
      getWeek(i) {
        let now = new Date();
        let firstDay = new Date(now - (now.getDay() - 1) * 86400000);
        firstDay.setDate(firstDay.getDate() + i);
        let mon = Number(firstDay.getMonth()) + 1;
        mon = mon < 10 ? ('0' + mon) : mon;
        let d = firstDay.getDate()
        d = d < 10 ? ('0' + d) : d;
        // return `${now.getFullYear()}-${mon}-${d}`;
        return new Date(`${now.getFullYear()}-${mon}-${d} 00:00:00`).getTime();
      },
      exportExcel() {
        let params = {
          startTime: this.getWeek(0),
          endTime: this.getWeek(7),
          type: 2
        };
        this.$api.getAllUploadFile(params).then(res => {
          console.log(res);
          if (res.data.returnCode == 200) {
            let data = res.data.returnObject.map(e => {
              return e.id
            });
            console.log(data);
            this.downZip(data.toString());
          }
        }).catch(err => {

        })
        // this.$refs.tables.exportCsv({
        //   filename: `table-${(new Date()).valueOf()}.csv`
        // })
      },
      downZip(id) {
        let params = {
          fileIds: id
        };
        this.$api.downZip(params).then(response => {
          console.log(response);
          var headers = response.headers();
          var filename = headers['content-disposition'].split(';')[1].split('=')[1];
          var contentType = headers['content-type'];

          var linkElement = document.createElement('a');
          try {
            var blob = new Blob([response.data], {
              type: contentType
            });
            var url = window.URL.createObjectURL(blob);
            linkElement.setAttribute('href', url);
            linkElement.setAttribute("download", filename);
            if (typeof (MouseEvent) == 'function') {
              var event = new MouseEvent("click", {
                "view": window,
                "bubbles": true,
                "cancelable": false
              });
              linkElement.dispatchEvent(event);
            } else if (navigator.appVersion.toString().indexOf('.NET') > 0) {
              window.navigator.msSaveBlob(blob, filename);
            }
          } catch (err) {
            console.log(err);
            //console.log(ex);
          }
        }).catch(err => {

        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .betting {
    min-width: 1300px;
    height: 1000px;

    .flex.border-flex {
      height: 40px;
      background-color: #e3e3e3;
      padding: 3px;
      border-radius: 4px;

      .col {
        margin-right: 30px;
        color: rgba(0, 0, 0, 0.7);
        font-weight: bold;
      }
    }

    .flex {
      display: flex;
      align-items: center;

      .col {
        margin-right: 10px;
        flex: inherit;

        &.btn {
          flex: inherit;
        }

        &.page {
          flex: 1;
          text-align: right;
        }

        &:last-child {
          margin-right: 0;
        }
      }
    }
  }
</style>
