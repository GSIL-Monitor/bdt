<template>
  <Card class="login-out">
    <template slot="title">
      <div class="header-box">
        <div class="col">账号登录情况</div>
        <div class="col" style="text-align: right;flex: inherit">
          <Button type="primary" ghost size="small" @click="addModal = true">新建账户</Button>
        </div>
      </div>
    </template>
    <div class="" style="position: relative">
      <Card v-for="(item, index) in isLoginOverviewData" :key="index" style="margin-bottom: 15px">
        <div slot="title" class="header-box" style="font-size: 16px">
          <div class="col" style="font-weight: bold;">{{item.account}}</div>
          <div class="col">
            <Button v-if="item.loginStatus" type="primary" ghost size="small">{{' 已登陆'}}</Button>
            <Button v-else type="error" ghost size="small">{{'未登录'}}</Button>
          </div>
          <div class="col">
            <Button v-if="item.requestStatus" type="primary" ghost size="small">{{'读牌中'}}</Button>
            <Button v-else type="error" ghost size="small">{{'未读牌'}}</Button>
          </div>
          <div class="col" style="text-align: right;flex: inherit">
            <Button type="primary" size="small" @click="editUserHome(index)">编辑</Button>
          </div>
        </div>
        <!--<div class="header-box" style="height: 20px"><span class="col">{{item.ipRegion}}/{{item.ipAddress}}</span>-->
        <div class="header-box" style="height: 20px">
          <span class="col">账号：{{item.account}}</span>
          <span class="col" style="text-align: right;flex: inherit">
            <Button type="error" size="small" @click="editUserLogin(index)">重新登陆</Button></span>
        </div>
        <div class="header-box">
          <b class="col">有效金额：{{item.effectiveAmount}}</b>
        </div>
        <div class="header-box">
          <div class="col">公网IP：{{item.ipAddressPublic}}</div>
        </div>
        <div class="header-box">
          <div class="col">内网IP：{{item.ipAddress}}</div>
        </div>
        <!--<div class="col" style="text-align: right"><a href="">查看统计</a></div>-->
      </Card>
      <!---->
      <div>
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
              <FormItem label="登陆密码">
                <Input v-model="formRight.password"></Input>
              </FormItem>
              <!---->
              <FormItem label="公网IP地址">
                <Input v-model="formRight.ipAddressPublic"></Input>
              </FormItem>
              <FormItem label="内网IP地址">
                <Input v-model="formRight.ipAddress"></Input>
              </FormItem>
              <FormItem label="区域">
                <Select v-model="formRight.ipRegion">
                  <Option :key="i" v-for="i in ipRegionOption" :value="i">{{i}}</Option>
                </Select>
              </FormItem>
              <!---->
              <FormItem label="是否登陆">
                <Select disabled v-model="formRight.loginStatus">
                  <Option value="true">是</Option>
                  <Option value="false">否</Option>
                </Select>
              </FormItem>
            </Form>
          </div>
          <div slot="footer">
            <Button type="primary" size="large" long :loading="modal_loading" @click="addUser">添加账户
            </Button>
          </div>
        </Modal>
        <Modal title="Title" v-model="editModal" class-name="vertical-center-modal">
          <p slot="header" style="text-align:center">
            <Icon type="ios-information-circle"></Icon>
            <span>编辑账户</span>
          </p>
          <div style="text-align:center">
            <Form ref="loginForm" :model="formEdit" label-position="right" :label-width="100">
              <FormItem label="ID">
                <Input disabled v-model="formEdit.id"></Input>
              </FormItem>
              <FormItem prop="account" label="账号名">
                <Input v-model="formEdit.account"></Input>
              </FormItem>
              <FormItem label="登陆密码">
                <Input v-model="formEdit.password"></Input>
              </FormItem>
              <!---->
              <FormItem label="公网IP地址">
                <Input v-model="formEdit.ipAddressPublic"></Input>
              </FormItem>
              <FormItem label="内网IP地址">
                <Input v-model="formEdit.ipAddress"></Input>
              </FormItem>
              <FormItem label="金额">
                <Input v-model="formEdit.effectiveAmount"></Input>
              </FormItem>
              <FormItem label="是否登陆">
                <Select disabled v-model="formEdit.loginStatus">
                  <Option value="true">是</Option>
                  <Option value="false">否</Option>
                </Select>
              </FormItem>
            </Form>
          </div>
          <div slot="footer" style="display: flex;align-items: center">
            <Button type="error" size="large" long :loading="modal_loading" @click="delEditUser">
              删除账户
            </Button>
            <Button type="primary" size="large" long :loading="modal_loading" @click="editUser">
              编辑账户
            </Button>
          </div>
        </Modal>
      </div>
      <!---->
    </div>
  </Card>
</template>

<script>
  export default {
    name: "loginOut",
    data() {
      return {
        ipRegionOption: ['香港', '北京', '上海', '广州'],
        isLoginOverviewData: [],
        addModal: false,
        editModal: false,
        modal_loading: false,
        formRight: {
          id: '',
          name: '',
          account: '',
          password: '',
          ipAddress: '',
          loginStatus: 'false',
          effectiveAmount: '',
          ipAddressPublic: '',
          remark: ''
        },
        formEdit: {
          id: '',
          name: '',
          account: '',
          password: '',
          ipAddress: '',
          loginStatus: 'true',
          effectiveAmount: '',
          ipAddressPublic: '',
          remark: ''
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
    activated() {

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
    },
    beforeDestroy() {
      clearInterval(window.isInvgetUserByAdmin)
      // clearInterval(window.isInvgetUserByAdmin)
    },
    mounted() {
      this.getUserByAdmin();
      clearInterval(window.isInvgetUserByAdmin)
      window.isInvgetUserByAdmin = setInterval(_ => {
        this.getUserByAdmin();
      }, 5000)
    },
    methods: {
      delEditUser() {
        // this.formEdit.id
        let params = {
          userId: this.formEdit.id,
        };
        this.$api.deleteUserAccount(params).then((res) => {
          if (res.returnCode == 200) {
            this.$Message.info(res.returnMsg);
            this.editModal = false;
            this.getUserByAdmin();
          }
        })
      },
      editUserLogin(index) {

        this.formEdit = this.isLoginOverviewData[index];
        this.formEdit.loginStatus = String(this.formEdit.loginStatus);
        console.log('3453534', this.isLoginOverviewData[index]);
        this.formEdit.remark = Math.random();
        //
        let _this = this;
        this.$Modal.confirm({
          title: '提示',
          content: "是否需要绑定账号重新登陆？",
          onOk: function () {
            _this.editUser();
            // console.log(123123123123123123123123);
          },
          onCancel: function () {

          }
        });
      },
      editUser() {
        let params = this.formEdit;
        this.$api.editUserAccount(params).then((res) => {
          if (res.returnCode == 200) {
            this.editModal = false;
            this.getUserByAdmin();
          }
        })
      },
      editUserHome(index) {
        this.formEdit = this.isLoginOverviewData[index];
        // this.formEdit.password = '';
        this.formEdit.loginStatus = String(this.formEdit.loginStatus);
        this.editModal = true;
        console.log('3453534', this.isLoginOverviewData[index]);
      },
      loginStatusLook(index, type) {
        let params = Object.assign({}, this.isLoginOverviewData[index], {loginStatus: type});
        this.$api.editUserAccount(params).then((res) => {
          if (res.returnCode == 200) {
            this.getUserByAdmin();
          }
        }).catch((err) => {

        })
      },
      addUser() {
        this.$refs.loginForm.validate(valid => {
          console.log(valid);
          if (valid) {
            this.formRight.name = this.formRight.account;
            let params = Object.assign({}, this.formRight);
            this.$api.addUserAccount(params).then(res => {
              if (res.returnCode == 200) {
                this.$Message.info(res.returnMsg);
                this.addModal = false;
                this.getUserByAdmin();
              } else if (res.returnCode == -2) {
                this.$Message.info(res.returnMsg);
              }
            })
          }
        })
      },
      getUserByAdmin() {
        let params = {adminId: this.$cookie.get('token')};
        this.$api.getUserByAdmin(params).then((res) => {
          if (res.returnCode == 200) {
            this.isLoginOverviewData = res.returnObject;
            console.log('1111=>', this.isLoginOverviewData);
          }
        }).catch((err) => {

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
