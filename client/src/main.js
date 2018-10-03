// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import './assets/css/reset.css'
import './assets/css/element-variables.scss'
import './assets/css/common.scss'
import router from './router'
//
import common from './service/common'
import arrayFun from './service/array'
import Http from './service/http'
import Cookie from './service/cookie'
import Api from './service/api'

Vue.use(ElementUI)
//
Vue.config.productionTip = false
Vue.prototype.$api = Api
Vue.prototype.$http = Http
Vue.prototype.$cookie = Cookie
Vue.prototype.$common = common
Vue.prototype.$array = arrayFun
//
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
