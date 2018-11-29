<template>
  <div class="desktop">
    <Card>
      <div slot="title">
        <div class="flex">
          <div class="col">
            <DatePicker type="date" v-model="desktopTimeVal" @on-change="timeChange"
                        placeholder="Select date" style="width: 200px"></DatePicker>
          </div>
          <div class="col">
            <Select :key="'index'" @on-change="desktopChange" v-model="desktopVal"
                    placeholder="Select date" style="width:100px">
              <Option v-for="op in desktopOption" :value="op.value" :key="op.value">
                {{op.name }}
              </Option>
            </Select>
          </div>
          <div class="col">
            <Select :key="'index'" @on-change="desktopJChange" v-model="desktopJVal"
                    placeholder="Select date" style="width:100px">
              <Option v-for="op in desktopJOption" :value="op.value" :key="op.value">
                {{op.name }}
              </Option>
            </Select>
          </div>
          <div class="col page">
            <Page :total="page.total" show-sizer :page-size="page.size"
                  :page-size-opts="pageSizeOpt"
                  @on-change="pageChange" :current.sync="page.index"
                  @on-page-size-change="pageSizeChange"></Page>
          </div>
          <div class="col btn">
            <Button icon="md-download" :loading="exportLoading" @click="exportExcel">导出数据</Button>
          </div>
        </div>
      </div>
      <div>
        <Table ref="tables" :columns="tableTitle" :data="tableData"></Table>
      </div>
    </Card>

  </div>
</template>

<script>
  import excel from '@/libs/excel'

  export default {
    name: 'desktop',
    data() {
      return {
        pageSizeOpt: [30, 50, 100, 200, 500, 1000],
        page: {
          index: 1,
          size: 30,
          total: 100
        },
        desktopTimeVal: '',
        desktopVal: '0',
        desktopJVal: '0',
        desktopOption: [
          {name: '全部桌号', value: '0'},
          {name: '桌号1', value: '1'},
          {name: '桌号2', value: '2'},
          {name: '桌号3', value: '3'},
          {name: '桌号4', value: '4'},
          {name: '桌号5', value: '5'},
          {name: '桌号6', value: '6'},
          {name: '桌号7', value: '7'},
          {name: '桌号8', value: '8'},
          {name: '桌号9', value: '9'},
          {name: '桌号10', value: '10'},
          {name: '桌号11', value: '11'},
          {name: '桌号12', value: '12'}
        ],
        desktopJOption: [
          {name: '全部局号', value: '0'},
          {name: '局号1', value: '1'},
          {name: '局号2', value: '2'},
          {name: '局号3', value: '3'},
          {name: '局号4', value: '4'},
          {name: '局号5', value: '5'},
          {name: '局号6', value: '6'},
          {name: '局号7', value: '7'},
          {name: '局号8', value: '8'},
          {name: '局号9', value: '9'},
          {name: '局号10', value: '10'},
          {name: '局号11', value: '11'},
          {name: '局号12', value: '12'},
          {name: '局号13', value: '13'},
          {name: '局号14', value: '14'},
          {name: '局号15', value: '15'},
          {name: '局号16', value: '16'},
          {name: '局号17', value: '17'},
          {name: '局号18', value: '18'}
        ],
        exportLoading: false,
        tableTitle: [
          {
            title: '日期',
            key: 'created'
          },
          {
            title: '时间',
            key: 'createTime'
          },
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
            title: '庄',
            key: 'card'
          },
          {
            title: '闲',
            key: 'xianCard'
          },
          {
            title: '结果',
            key: 'setResult'
          }
        ],
        tableData: [],
        wads: {
          '1': '庄',
          '2': '闲',
          '3': '和',
          'null': '--'
        },
        status: {
          '2': '可投注',
          '3': '开牌',
          '1': '新局准备',
          'null': '--',
          '0': '停止投注'
        },
      }
    },
    activated() {
      console.log('activated', 1111);
    },
    mounted() {
      console.log('mounted', 1111);
    },
    created() {
      this.searchTableData();
      console.log('created', 1111);
    },
    methods: {
      desktopJChange(i) {
        this.searchTableData();
      },
      desktopChange(i) {
        this.searchTableData();
      },
      timeChange(val) {
        // console.warn(this.desktopTimeVal.getFullYear());
        console.warn(val);
        this.searchTableData();
      },
      pageSizeChange(index) {
        this.page.size = index;
        console.log(index);
        this.searchTableData();
      },
      pageChange(index) {
        this.page.index = index;
        console.log(index);
        this.searchTableData();
      },
      //
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
      //
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
      // POST /bjlTable/searchDopeData
      searchTableData() {
        let desktopTimeVal = '';
        if (this.desktopTimeVal != '' && this.desktopTimeVal != null) {
          desktopTimeVal = new Date(this.desktopTimeVal).getTime()
        }
        let desktopJVal = '';
        if (this.desktopJVal != 0) {
          desktopJVal = this.desktopJVal;
        }
        let desktopVal = '';
        if (this.desktopVal != 0) {
          desktopVal = this.desktopVal;
        }
        let params = {
          battleNo: desktopJVal,
          tableNo: desktopVal,
          createTime: desktopTimeVal,
          pageNum: this.page.index,
          pageSize: this.page.size
        };
        this.$api.searchTableData(params).then((res) => {
          if (res.data.returnCode == 200) {
            this.page.total = res.data.total
            this.tableData = res.data.returnObject;
            this.tableData.forEach((e, i) => {
              e.setResult = this.wads[e.result]
              e.created = this.formatDate(e.created)
              e.createTime = this.formatDateTime(e.createTime)
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
          type: 1
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
          var headers = response.headers;
          console.log(headers);
          // var filename = headers['content-disposition'].split(';')[1].split('=')[1];
          var contentType = headers['content-type'];
          var linkElement = document.createElement('a');
          try {
            console.log(12312)
            var blob = new Blob([response.data], {
              type: contentType
            });
            var url = window.URL.createObjectURL(blob);
            linkElement.setAttribute('href', url);
            linkElement.setAttribute("download", "桌面数据.zip");
            if (typeof (MouseEvent) == 'function') {
              var event = new MouseEvent("click", {
                "view": window,
                "bubbles": true,
                "cancelable": false
              });
              linkElement.dispatchEvent(event);
              console.log('MouseEvent')
            } else if (navigator.appVersion.toString().indexOf('.NET') > 0) {
              window.navigator.msSaveBlob(blob, "桌面数据.zip");
              console.log('NET')
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
  .desktop {
    min-width: 1300px;
    height: 1000px;

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
