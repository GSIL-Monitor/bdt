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
    return response.data
}, error => {
  console.log(error);
  console.log(error.message);
  console.log(error.config);
  if (error.config.message != 'none') {
    if (error.message == 'Network Error') {
      //
    } else if (error.message.indexOf('timeout') > -1) {
      //
    } else {
      //
    }
  }
  return Promise.reject(error)
})

export default Axios
