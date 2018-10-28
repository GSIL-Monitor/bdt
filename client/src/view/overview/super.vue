<template>
  <div id="super">
    <Table ref="tables" :columns="tableTitle" :data="tableData"></Table>
  </div>
</template>

<script>
  export default {
    name: "super",
    data() {
      return {
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
                    type: 'error',
                    size: 'small'
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
        tableData: [{}, {}, {}],
        userInfo: []
      }
    },
    computed: {},
    activated() {
      this.userInfo = window.JSON.parse(this.$cookie.get('USER_INFO'));
      console.log(this.userInfo.superAdmin);
      if (this.userInfo.superAdmin) {
        this.$Notice.open({title: '管理员', desc: '欢迎管理员视察', duration: 2000});
      } else {
        this.$router.replace({path: '/overview'});
      }
    },
    created() {
      this.userInfo = window.JSON.parse(this.$cookie.get('USER_INFO'));
      console.log(this.userInfo.superAdmin);
      //
      if (this.userInfo.superAdmin) {
        this.$Notice.open({title: '管理员', desc: '欢迎管理员视察', duration: 2000});
      } else {
        this.$router.replace({path: '/overview'});
      }
      this.selectAllAdmin();
    },
    mounted() {

    },
    methods: {
      deleteAdminAccount(userId) {
        let params = {
          userId: userId
        };
        this.$api.deleteAdminAccount(params).then(res => {
          if (res.returnCode == 200) {
            this.selectAllAdmin();
          }
        }).catch(err => {

        })
      },
      selectAllAdmin() {
        let params = {};
        this.$api.selectAllAdmin(params).then((res) => {
          if (res.returnCode == 200) {
            this.tableData = res.returnObject;
          }
        })
      },
      show(index) {
        // let userId = this.tableData[index].id;
        // this.deleteAdminAccount(userId);
      },
      remove(index) {
        // this.tableData.splice(index, 1);
        let userId = this.tableData[index].id;
        this.deleteAdminAccount(userId);
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
