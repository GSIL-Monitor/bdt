import cookies from 'js-cookie'

export default {
  get(name) {
    name = process.env.XA_ENV + '_' + name
    let value = cookies.get(name)
    if (!value) {
      value = ''
    }
    return value
  },
  set(name, value) {
    name = process.env.XA_ENV + '_' + name
    if (process.env.XA_ENV === 'development') {
      cookies.set(name, value, {expires: 7})
    } else {
      cookies.set(name, value, {expires: 7})
    }
  },
  remove(name) {
    name = process.env.XA_ENV + '_' + name
    if (process.env.XA_ENV === 'development') {
      cookies.set(name, '', {expires: -1})
    } else {
      cookies.set(name, '', {expires: -1})
    }
  },
  isLogin() {
    let userId = this.get('TF_TOKEN')
    if (userId) {
      return true
    } else {
      return false
    }
  },
  // 获取用户信息
  getUserInfo() {
    return JSON.parse(this.get('userInfo'))
  }
}
