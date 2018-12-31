<template>
  <Card>
    <p slot="title">BDT运行状态</p>
    <Form :model="formItem" :label-width="40">
      <FormItem label="状态">
        <div style="display: flex;align-items: center">
          <span style="flex: 1">{{start?'正常运行':'停止'}}</span>
          <span style="flex: inherit;text-align: right">
            <Button @click="bdtSystemStarted" size="small" type="success"
                    :disabled="start">启动</Button>&ensp;
            <Button @click="bdtSystemStarted" size="small" type="error"
                    :disabled="!start">停止</Button>
          </span>
        </div>
      </FormItem>
      <FormItem label="PS">
        <Input v-model="formItem.ps" placeholder="warning" :disabled="start"></Input>
      </FormItem>
      <FormItem label="TXXS">
        <Input v-model="formItem.txxs" placeholder="warning" :disabled="start"></Input>
      </FormItem>
      <FormItem label="PHXS">
        <Input v-model="formItem.phxs" placeholder="warning" :disabled="start"></Input>
      </FormItem>
    </Form>
  </Card>
</template>

<script>
  export default {
    name: "bdt-run",
    data() {
      return {
        start: false,
        formItem: {
          ps: '',
          phxs: '',
          txxs: ''
        }
      }
    },
    activated() {

    },
    methods: {
      bdtSystemStarted() {
        this.start = !this.start;
        let params = Object.assign({}, {started: this.start}, this.formItem);
        console.log(params);
        this.$api.bdtSystemStarted(params).then(res => {

        })
      },
      getBdtSystemInfo() {
        let params = {
          tableNo: 0
        };
        this.$api.getBdtSystemInfo(params).then(res => {
          if (res.data.returnCode == 200) {
            console.log('===================>', res)
            this.start = res.data.returnObject.started;
            this.formItem.ps = res.data.returnObject.ps;
            this.formItem.phxs = res.data.returnObject.phxs;
          }
        })
      }
    },
    created() {
      this.getBdtSystemInfo();
    }
  }
</script>

<style lang="less" scoped>

</style>
