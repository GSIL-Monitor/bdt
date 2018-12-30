<template>
  <div id="super">
    <Card :bordered="false">
      <!--<Button type="info">j</Button>-->
      <Button type="primary" ghost size="small" @click="addChange">新建账户
      </Button>
    </Card>
    <!---->
    <div style="height: 20px"></div>
    <Table ref="tables" :columns="tableTitle" :data="tableData"></Table>
    <div>
      <Modal title="Title" v-model="addModal" class-name="vertical-center-modal">
        <p slot="header" style="text-align:center">
          <Icon type="ios-information-circle"></Icon>
          <span>添加账户</span>
        </p>
        <div style="text-align:center">
          <Form ref="loginForm" :model="formRight" :rules="rules" label-position="right"
                :label-width="100">
            <FormItem prop="name" label="用户名">
              <Input v-model="formRight.name"></Input>
            </FormItem>
            <FormItem prop="account" label="账号">
              <Input v-model="formRight.account"></Input>
            </FormItem>
            <FormItem prop="password" label="登陆密码">
              <Input v-model="formRight.password"></Input>
            </FormItem>
            <!---->
            <FormItem label="是否登陆">
              <Select disabled v-model="formRight.superAdmin">
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
      <!---->
      <Modal title="Title" v-model="editModal" class-name="vertical-center-modal">
        <p slot="header" style="text-align:center">
          <Icon type="ios-information-circle"></Icon>
          <span>编辑账户</span>
        </p>
        <div style="text-align:center">
          <Form ref="loginForms" :model="formDate" :rules="rules" label-position="right"
                :label-width="100">
            <FormItem prop="name" label="用户名">
              <Input v-model="formDate.name"></Input>
            </FormItem>
            <FormItem prop="account" label="账号">
              <Input v-model="formDate.account"></Input>
            </FormItem>
            <FormItem prop="password" label="登陆密码">
              <Input v-model="formDate.password"></Input>
            </FormItem>
            <!---->
            <FormItem label="是否登陆">
              <Select disabled v-model="formDate.superAdmin">
                <Option value="true">是</Option>
                <Option value="false">否</Option>
              </Select>
            </FormItem>
          </Form>
        </div>
        <div slot="footer" style="display: flex;align-items: center">
          <Button type="primary" size="large" long :loading="modal_loading" @click="editUser">编辑账户
          </Button>
        </div>
      </Modal>
    </div>
  </div>
</template>

<script>
  import {getToken} from '../../libs/util'

  export default {
    name: "super",
    data() {
      return {
        addModal: false,
        editModal: false,
        modal_loading: false,
        formRight: {
          name: '',
          account: '',
          password: '',
          superAdmin: 'false'
        },
        formDate: {
          name: '',
          account: '',
          password: '',
          superAdmin: 'false'
        },
        tableTitle: [
          {
            title: '名称',
            key: 'name'
          },
          {
            title: '账号',
            key: 'account'
          },
          {
            title: '密码',
            key: 'password'
          },
          {
            title: '操作',
            key: '',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'info',
                    size: 'small',
                    disabled: params.row.superAdmin
                  },
                  style: {
                    marginRight: '10px'
                  },
                  on: {
                    click: () => {
                      this.edit(params)
                    }
                  }
                }, '编辑'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small',
                    disabled: params.row.superAdmin
                  },
                  on: {
                    click: () => {
                      this.remove(params.index)
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        tableData: [],
        ipRegionOption: ['香港', '北京', '上海', '广州'],
        userInfo: []
      }
    },
    props: {
      userNameRules: {
        type: Array,
        default: _ => {
          return [{required: true, message: '账户名不能为空', trigger: 'blur'}]
        }
      },
      accountRules: {
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
    watch: {
      editModal: function (val) {
        if (!val) {
          this.selectAllAdmin();
        }
      }
    },
    computed: {
      rules() {
        return {
          name: this.userNameRules,
          account: this.accountRules,
          password: this.passwordRules,
        }
      }
    },
    activated() {
      this.userInfo = window.JSON.parse(this.$cookie.get('USER_INFO'));
      console.log(this.userInfo.superAdmin);
      if (this.userInfo.superAdmin) {
        this.$Notice.open({title: '管理员', desc: '欢迎管理员视察', duration: 2});
      } else {
        this.$router.replace({path: '/overview'});
      }
    },
    created() {
      this.userInfo = window.JSON.parse(this.$cookie.get('USER_INFO'));
      console.log(this.userInfo.superAdmin);
      //
      if (this.userInfo.superAdmin) {
        this.$Notice.open({title: '管理员', desc: '欢迎管理员视察', duration: 2});
      } else {
        this.$router.replace({path: '/overview'});
      }
      this.selectAllAdmin();
    },
    mounted() {

    },
    methods: {
      addChange() {
        this.addModal = true;
        this.formRight.name = '';
        this.formRight.account = '';
        this.formRight.password = '';
      },
      delEditUser() {
        let delId = this.formRight.id;
        this.deleteAdminAccount(delId);
      },
      editUser() {
        // this.editModal = true;
        this.$refs.loginForms.validate(valid => {
          console.log(valid);
          if (valid) {
            let params = this.formDate;
            this.$api.editAdminAccount(params).then((res) => {
              if (res.data.returnCode == 200) {
                this.editModal = false;
                this.selectAllAdmin();
              }
            })
          } else {
            this.$Notice.open({title: '提示', desc: '请填写完整信息再提交', duration: 2});
          }
        })
      },
      addUser() {
        //
        this.$refs.loginForm.validate(valid => {
          console.log(valid);
          if (valid) {
            this.formRight.name = this.formRight.account;
            let params = Object.assign({}, this.formRight);
            this.$api.addAdminAccount(params).then(res => {
              if (res.data.returnCode == 200) {
                this.$Message.info(res.data.returnMsg);
                this.addModal = false;
                this.formRight.name = '';
                this.formRight.account = '';
                this.formRight.password = '';
                this.selectAllAdmin();
              } else if (res.data.returnCode == -2) {
                this.$Message.info(res.data.returnMsg);
              }
            })
          } else {
            this.$Notice.open({title: '提示', desc: '请填写完整信息再提交', duration: 2});
          }
        })
      },
      //
      deleteAdminAccount(userId) {
        let params = {
          userId: userId
        };
        this.$api.deleteAdminAccount(params).then(res => {
          if (res.data.returnCode == 200) {
            this.selectAllAdmin();
          }
        }).catch(err => {

        })
      },
      //
      selectAllAdmin() {
        let params = {};
        this.$api.selectAllAdmin(params).then((res) => {
          if (res.data.returnCode == 200) {
            this.tableData = res.data.returnObject;
            let token = getToken()
            this.tableData.forEach((e) => {
              this.$set(e, 'isMe', false)
              if (e.id == token) {
                this.$set(e, 'isMe', true)
              }
            })
            this.tableData = this.tableData.filter((e, index) => {
              return e.superAdmin != true;
            })
          }
        })
      },
      show(index) {
        // let userId = this.tableData[index].id;
        // this.deleteAdminAccount(userId);
      },
      remove(index) {
        this.$Modal.confirm({
          title: '提示',
          content: '是否需要删除？删除后数据无法恢复！',
          onOk: () => {
            // this.tableData.splice(index, 1);
            let userId = this.tableData[index].id;
            this.deleteAdminAccount(userId);
          },
          onCancel: () => {
            this.$Message.info('Clicked cancel');
          }
        });
      },
      edit(params) {
        this.editModal = true;
        this.formDate = params.row
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
