import Axios from '@/service/axios/index.js'
import config from '@/config/index.js'

const protocol = document.location.protocol == 'https:' ? 'https:' : 'http:'
const HOST = process.env.BASE_URL
export default {
  //  登录
  getlogin(params = {}) {
    return Axios({method: 'POST', url: HOST + 'user/login', params: params})
  }
}
