import Axios from '@/service/axios/index.js'
import config from '@/config/index.js'

const protocol = document.location.protocol == 'https:' ? 'https:' : 'http:'
const HOST = config.baseUrl.pro
export default {
  //
  getTableInfo(params = {}) {
    return Axios({method: 'get', url: HOST + 'bjlTable/getTableInfo', params: params})
  },
  getTzSystemInfo(params = {}) {
    return Axios({method: 'get', url: HOST + 'bjlTable/getTzSystemInfo', params: params})
  },
  tzSystemStarted(params = {}) {
    return Axios({method: 'POST', url: HOST + 'bjlTable/tzSystemStarted', data: params})
  },
  getUserAccount(params = {}) {
    return Axios({method: 'get', url: HOST + 'account/getUserAccount', params: params})
  },
  getAdminAccount(params = {}) {
    return Axios({method: 'get', url: HOST + 'account/getAdminAccount', params: params})
  },
  getUserByAdmin(params = {}) {
    // 查看管理员所管理的用户 params = {adminId:'##'}
    return Axios({method: 'get', url: HOST + 'account/getUserByAdmin', params: params})
  },
  editUserAccount(params = {}) {
    //
    return Axios({method: 'POST', url: HOST + 'account/editUserAccount', params: params})
  },
  bdtSystemStarted(params = {}) {
    //
    return Axios({method: 'POST', url: HOST + 'bjlTable/bdtSystemStarted', params: params})
  },
  getBdtSystemInfo(params = {}) {
    //
    return Axios({method: 'get', url: HOST + 'bjlTable/getBdtSystemInfo', params: params})
  },
  addUserAccount(params = {}) {
    //
    return Axios({method: 'POST', url: HOST + 'account/addUserAccount', params: params})
  },
  getLJInfo(params = {}) {
    //
    return Axios({method: 'get', url: HOST + 'bjlTable/getLJInfo', params: params})
  }
}
