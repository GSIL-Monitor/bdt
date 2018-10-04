import Vue from 'vue'
import Axios from 'axios'
import Cookie from 'js-cookie'
import router from '@/router/index'

require('es6-promise').polyfill()

// var AUTH_TOKEN = Cookie.get('TF_TOKEN')
// `headers` 是即将被发送的自定义请求头
// Axios.defaults.headers.common["Authorization"] = 'Bearer ' + AUTH_TOKEN;
Axios.defaults.timeout = 60000
Axios.defaults.baseURL = '';
Axios.defaults.withCredentials = false //
// 请求拦截器
Axios.interceptors.request.use(config => {
  if (config.method.toLocaleLowerCase() == 'get') {
    if (!config.params) {
      config.params = {}
    }
    config.params.t = new Date().getTime()
  }
  return config
}, error => {
  return Promise.reject(error)
})

// 响应拦截器
Axios.interceptors.response.use(response => {
  // 8001 token 失效
  if (response.config.url.indexOf('/user/login') > -1 && response.data.returnCode == 1) {
    Cookie.set('TF_TOKEN', response.data.returnObject.token, Vue.$config.cookieExpires);
    // Axios.defaults.headers.common["Authorization"] = 'Bearer ' + response.data.returnObject.token;
  }
  //
  if (response.data.returnCode == 8001) {
    setTimeout(() => {
      Cookie.remove('TF_TOKEN');
      router.push({path: '/login', query: {redirect_url: router.fullPath}});
    }, 2500)
  } else {
    return response.data
  }

}, error => {
  console.log(error);
  console.log(error.message);
  console.log(error.config);
  if (error.config.message != 'none') {
    if (error.message == 'Network Error') {
      Vue.prototype.$message({
        message: '网络异常，请稍后再试',
        showClose: true,
        type: 'error',
        duration: 3000
      })
    } else if (error.message.indexOf('timeout') > -1) {
      Vue.prototype.$message({
        message: '请求超时，请稍后重试',
        showClose: true,
        type: 'error',
        duration: 3000
      })
    } else {
      Vue.prototype.$message({
        message: error.message,
        showClose: true,
        type: 'error',
        duration: 3000
      })
    }
  }
  return Promise.reject(error)
})

export default Axios
