<template>
  <Card class="login-out">
    <template slot="title">
      <div class="header-box">
        <div class="col">账号登录情况</div>
        <!--<div class="col" style="text-align: right;flex: inherit">-->
          <!--<Button type="primary" ghost size="small" @click="addModal = true">新建账户</Button>-->
        <!--</div>-->
      </div>
    </template>
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
        <!--<div class="header-box" style="height: 20px"><span class="col">{{item.ipRegion}}/{{item.ipAddress}}</span>-->
        <div class="header-box" style="height: 20px"><span class="col">账号：{{item.account}}</span>
        </div>
      </template>
      <div class="header-box">
        <div class="col">有效金额：{{item.effectiveAmount}}</div>
        <div class="col" style="text-align: right"><a href="">查看统计</a></div>
      </div>
    </Card>
    <!--//-->
    <Modal title="Title" v-model="addModal" class-name="vertical-center-modal">
      <p slot="header" style="text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>添加账户</span>
      </p>
      <div style="text-align:center">
        <Form ref="loginForm" :model="formRight" :rules="rules" label-position="right"
              :label-width="100">
          <FormItem prop="account" label="账号名">
            <Input v-model="formRight.account"></Input>
          </FormItem>
          <FormItem prop="password" label="登陆密码">
            <Input v-model="formRight.password"></Input>
          </FormItem>
          <FormItem label="是否登陆">
            <Select disabled v-model="formRight.loginStatus">
              <Option value="true">true</Option>
              <Option value="false">false</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" size="large" long :loading="modal_loading" @click="addUser">添加账户
        </Button>
      </div>
    </Modal>
  </Card>
</template>

<script>
  export default {
    name: "loginOut",
    data() {
      return {
        isLoginOverviewData: [],
        addModal: false,
        modal_loading: false,
        formRight: {
          name: '百家乐01',
          account: '',
          password: '',
          loginStatus: 'true'
        }
      }
    },
    computed: {
      rules() {
        return {
          account: this.userNameRules,
          password: this.passwordRules
        }
      }
    },
    props: {
      userNameRules: {
        type: Array,
        default: _ => {
          return [{required: true, message: '账号不能为空', trigger: 'blur'}]
        }
      },
      passwordRules: {
        type: Array,
        default: _ => {
          return [{required: true, message: '密码不能为空', trigger: 'blur'}]
        }
      }
    },
    created() {
      this.getUserByAdmin();
    },
    mounted() {
      this.isInv = setInterval(_ => {
        // this.getUserByAdmin();
      }, 5000)
    },
    methods: {
      addUser() {
        this.$refs.loginForm.validate(valid => {
          console.log(valid);
          if (valid) {
            let params = Object.assign({}, this.formRight);
            this.$api.addUserAccount(params).then(res => {

            })
          }
        })
      },
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

<style lang="scss" scoped>
  .login-out {
    .header-box {
      display: flex;
      align-items: center;
      .col {
        flex: 1;
      }
    }
  }
</style>
