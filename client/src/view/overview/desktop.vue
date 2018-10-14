<template>
  <div class="desktop">
    <Card>
      <div slot="title">
        <div class="flex">
          <div class="col">
            <DatePicker type="date" placeholder="Select date" style="width: 200px"></DatePicker>
          </div>
          <div class="col">
            <Select :key="'index'" v-model="desktopVal" clearable style="width:100px">
              <Option v-for="op in desktopOption" :value="op.value" :key="op.value">
                {{op.name }}
              </Option>
            </Select>
          </div>
          <div class="col">
            <Select :key="'index'" v-model="desktopJVal" clearable style="width:100px">
              <Option v-for="op in desktopJOption" :value="op.value" :key="op.value">
                {{op.name }}
              </Option>
            </Select>
          </div>
          <div class="col page">
            <Page :total="page.total" show-sizer :page-size="page.size"
                  @on-change="pageChange" :current.sync="page.index"></Page>
          </div>
          <div class="col btn">
            <Button icon="md-download" :loading="exportLoading" @click="exportExcel">导出CVS</Button>
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
        page: {
          index: 1,
          size: 20,
          total: 100
        },
        desktopVal: '',
        desktopJVal: '',
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
          {name: '局号8', value: '8'}
        ],
        exportLoading: false,
        tableTitle: [
          {
            title: '一级分类',
            key: 'category1'
          },
          {
            title: '二级分类',
            key: 'category2'
          },
          {
            title: '三级分类',
            key: 'category3'
          }
        ],
        tableData: [
          {
            category1: 1,
            category2: 2,
            category3: 3
          },
          {
            category1: 4,
            category2: 5,
            category3: 6
          },
          {
            category1: 7,
            category2: 8,
            category3: 9
          }
        ]
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
      pageChange(index) {
        this.page.index = index;
        console.log(index);
        this.searchTableData();
      },
      exportExcel() {
        this.$refs.tables.exportCsv({
          filename: `table-${(new Date()).valueOf()}.csv`
        })
      },
      // POST /bjlTable/searchDopeData
      searchTableData() {
        let params = {
          pageNum: this.page.index,
          pageSize: this.page.size
        };
        this.$api.searchTableData(params).then((res) => {
          if (res.returnCode == 200) {
            this.tableData = res.returnObject;
          }
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
