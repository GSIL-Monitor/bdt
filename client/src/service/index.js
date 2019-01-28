import Axios from '@/service/axios/index'
import config from '@/config/index'
import Cookie from 'js-cookie'

const protocol = document.location.protocol == 'https:' ? 'https:' : 'http:'
const HOST = config.baseUrl[process.env.NODE_ENV]
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
  deleteUserAccount(params = {}) {
    // 查看管理员所管理的用户 params = {adminId:'##'}
    return Axios({method: 'delete', url: HOST + 'account/deleteUserAccount', params: params})
  },
  editUserAccount(params = {}) {
    //
    return Axios({
      method: 'POST',
      url: HOST + 'account/editUserAccount',
      params: Object.assign({adminId: Cookie.get('token')}, params)
    })
  },
  editAdminAccount(params = {}) {
    return Axios({
      method: 'POST',
      url: HOST + 'account/editAdminAccount',
      params: Object.assign({adminId: Cookie.get('token')}, params)
    })
  },
  bdtSystemStarted(params = {}) {
    //
    return Axios({
      method: 'POST',
      url: HOST + 'bjlTable/bdtSystemStarted',
      params: Object.assign({adminId: Cookie.get('token')}, params)
    })
  },
  getBdtSystemInfo(params = {}) {
    //
    return Axios({method: 'get', url: HOST + 'bjlTable/getBdtSystemInfo', params: params})
  },
  addUserAccount(params = {}) {
    //
    return Axios({
      method: 'POST',
      url: HOST + 'account/addUserAccount',
      params: Object.assign({adminId: Cookie.get('token')}, params)
    })
  },
  addAdminAccount(params = {}) {
    //
    return Axios({
      method: 'POST',
      url: HOST + 'account/addAdminAccount',
      params: Object.assign({adminId: Cookie.get('token')}, params)
    })
  },
  getLJInfo(params = {}) {
    //
    return Axios({method: 'get', url: HOST + 'bjlTable/getLJInfo', params: params})
  },
  searchDopeData(params = {}) {
    //
    return Axios({method: 'get', url: HOST + 'bjlTable/searchDopeData', params: params})
  },
  searchTableData(params = {}) {
    //
    return Axios({method: 'get', url: HOST + 'bjlTable/searchTableData', params: params})
  },
  updateTzCheck(params) {
    //
    return Axios({method: 'post', url: HOST + 'bjlTable/updateTzCheck', data: params})
  },
  selectAllAdmin(params = {}) {
    // GET /account/selectAllAdmin
    return Axios({method: 'get', url: HOST + 'account/selectAllAdmin', params: params})
  },
  deleteAdminAccount(params = {}) {
    // DELETE /account/deleteAdminAccount
    return Axios({method: 'delete', url: HOST + 'account/deleteAdminAccount', params: params})
  },
  getAllUploadFile(params = {}) {
    // DELETE /account/deleteAdminAccount
    return Axios({method: 'get', url: HOST + 'bjlTable/getAllUploadFile', params: params})
  },
  downZip(params = {}) {
    // DELETE /account/deleteAdminAccount
    let api = Object.assign({}, params, {
      method: 'POST',
      headers: {"Content-Type": "multipart/form-data"},
      url: HOST + 'bjlTable/downZip',
      responseType: 'blob'
    })
    return Axios(api)
  }
}
