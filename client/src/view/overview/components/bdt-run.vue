<template>
  <Card>
    <p slot="title">BDT运行状态</p>
    <Form :model="formItem" :label-width="80">
      <FormItem label="状态">
        <div style="display: flex;align-items: center">
          <span style="flex: 1">{{'正常运行'}}</span>
          <span style="flex: inherit;text-align: right">
            <Button size="small" type="success">启动</Button>&ensp;
          <Button size="small" type="error" disabled>停止</Button>
          </span>
        </div>
      </FormItem>
      <FormItem label="PS">
        <Input v-model="formItem.ps" placeholder="warning"></Input>
      </FormItem>
      <FormItem label="PHXS">
        <Input v-model="formItem.phxs" placeholder="warning"></Input>
      </FormItem>
    </Form>
  </Card>
</template>

<script>
  export default {
    name: "bdt-run",
    data() {
      return {
        formItem: {
          ps: '',
          phxs: ''
        }
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
