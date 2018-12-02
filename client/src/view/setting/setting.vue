<template>
  <div style="background:#eee;padding: 20px">
    <Card :bordered="false">
      <p slot="title">修改密码</p>
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
        <FormItem label="用户名" prop="name">
          <Input v-model="formValidate.name" disabled placeholder="Enter your name"></Input>
        </FormItem>
        <FormItem label="账号" prop="user">
          <Input v-model="formValidate.user" disabled placeholder="Enter your e-mail"></Input>
        </FormItem>
        <FormItem label="旧密码" prop="password">
          <Input type="password" v-model="formValidate.password" disabled
                 placeholder="Enter your e-mail"></Input>
        </FormItem>
        <FormItem label="新密码" prop="checkPassword">
          <Input v-model="formValidate.checkPassword" placeholder="Enter your 新密码"></Input>
        </FormItem>
        <FormItem prop="edit">
          <Button type="primary" @click="editPsd">修改</Button>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
  import {getInfo} from '../../libs/util'
  import {mapActions} from 'vuex'

  export default {
    name: "setting",
    data() {
      return {
        formValidate: {
          name: '',
          user: '',
          password: '',
          checkPassword: ''
        },
        ruleValidate: {
          name: [{required: true, message: '请输入', trigger: 'blur'}],
          user: [{required: true, message: '请输入', trigger: 'blur'}],
          password: [{required: true, message: '请输入', trigger: 'blur'}],
          checkPassword: [{required: true, message: '请输入', trigger: 'blur'}]
        },
        userInfo: {},
        setUserInfo: {}
      }
    },
    created() {
      this.setUserInfo = JSON.parse(JSON.stringify(getInfo()));
      this.userInfo = JSON.parse(JSON.stringify(getInfo()));
      /*
      * account: "admin"
        delete: false
        id: "5e3463418a8b4b6a84af80b40c973087"
        name: "alex"
        password: "202cb962ac59075b964b07152d234b70"
        superAdmin: true
        */
      //  console.log(this.userInfo);
      this.formValidate = {
        name: this.userInfo.name,
        user: this.userInfo.account,
        password: this.userInfo.password,
        checkPassword: ''
      };
    },
    methods: {
      ...mapActions([
        'handleLogOut'
      ]),
      /*
      * POST /account/editAdminAccount*/
      editPsd() {
        this.$refs.formValidate.validate(valid => {
          console.log(valid);
          if (valid) {
            let params = {
              account: this.formValidate.user,
              id: this.userInfo.id,
              name: this.formValidate.name,
              password: this.formValidate.checkPassword
            };
            this.$api.editAdminAccount(params).then((res) => {
              if (res.data.returnCode == 200) {
                this.handleLogOut().then(() => {
                  this.$Notice.open({title: '提示', desc: '设置新密码成功请重新登陆', duration: 2});
                  this.$router.push({name: 'login'})
                })
              }
            })
          } else {
            this.$Notice.open({title: '提示', desc: '请填写完整信息再提交', duration: 2});
          }
        })
        console.log(this.setUserInfo);
        console.log(this.userInfo);
        console.log(this.formValidate);
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
