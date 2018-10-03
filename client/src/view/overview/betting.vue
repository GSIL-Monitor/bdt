<template>
  <div class="betting">
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
            <Select :key="'index'" v-model="desktopVal" clearable style="width:100px">
              <Option v-for="op in desktopOption" :value="op.value" :key="op.value">
                {{op.name }}
              </Option>
            </Select>
          </div>
          <div class="col page">
            <Page :total="100" show-sizer/>
          </div>
          <div class="col btn">
            <Button icon="md-download" :loading="exportLoading" @click="exportExcel">导出CVS</Button>
          </div>
        </div>
      </div>
      <div class="flex border-flex">
        <div class="col">总有效金额：88888888.88</div>
        <div class="col">总原始输赢：777777.77</div>
        <div class="col">总实际金额：666666.66</div>
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
        desktopVal: '',
        desktopOption: [
          {name: '1', value: '1'},
          {name: '2', value: '2'},
          {name: '3', value: '3'},
          {name: '4', value: '4'}
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
    mounted() {

    },
    created() {

    },
    methods: {
      exportExcel() {
        this.$refs.tables.exportCsv({
          filename: `table-${(new Date()).valueOf()}.csv`
        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .betting {
    min-width: 1200px;
    height: 1000px;
    .flex.border-flex {
      height: 40px;
      background-color: #BFBFBF;
      padding: 3px;
      border-radius: 4px;
      .col {
        margin-right: 30px;
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
