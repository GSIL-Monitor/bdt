import {login, logout, getUserInfo} from '@/api/user'
import {setToken, getToken} from '@/libs/util'
import Vue from 'vue'
import Cookie from 'js-cookie'

export default {
  state: {
    userName: '',
    userId: '',
    avatorImgPath: '',
    token: getToken(),
    access: '',
    hasGetInfo: false
  },
  mutations: {
    setAvator(state, avatorPath) {
      state.avatorImgPath = avatorPath
    },
    setUserId(state, id) {
      state.userId = id
    },
    setUserName(state, name) {
      state.userName = name
    },
    setAccess(state, access) {
      state.access = access
    },
    setToken(state, token) {
      state.token = token
      setToken(token)
    },
    setHasGetInfo(state, status) {
      state.hasGetInfo = status
    }
  },
  actions: {
    // 登录
    handleLogin({commit}, {userName, password}) {
      userName = userName.trim();
      console.log(1234343243, '==============>');
      return new Promise((resolve, reject) => {
        login({userName, password}).then(res => {
          console.log(res);
          const data = res.data
          console.log('data', data);
          if (data.returnCode == 200) {
            Cookie.set('USER_INFO', window.JSON.stringify(data.returnObject));
            commit('setToken', data.returnObject.id)
            commit('setAvator', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png')
            commit('setUserName', data.returnObject.name)
            commit('setUserId', data.returnObject.id)
            commit('setAccess', data.returnObject.account)
            commit('setHasGetInfo', true)
          } else {
            Vue.prototype.$Message.warning({
              content: data.returnMsg,
              duration: 10,
              closable: true
            });
          }
          resolve(data)
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 退出登录
    handleLogOut({state, commit}) {
      return new Promise((resolve, reject) => {
        // 如果你的退出登录无需请求接口，则可以直接使用下面三行代码而无需使用logout调用接口
        commit('setToken', '')
        commit('setAccess', [])
        Cookie.remove('USER_INFO');
        Cookie.remove('token');
        resolve()
      })
    },
    // 获取用户相关信息
    getUserInfo({state, commit}) {
      return new Promise((resolve, reject) => {
        try {
          const data = window.JSON.parse(Cookie.get('USER_INFO'));
          console.log(data);
          commit('setAvator', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png')
          commit('setUserName', data.name)
          commit('setUserId', data.id)
          commit('setAccess', data.account)
          commit('setHasGetInfo', true)
          resolve(data)
        } catch (error) {
          reject(error)
        }
      })
    }
  }
}
