import cookies from 'js-cookie'

export default {
  get(name) {
    let value = cookies.get(name)
    if (!value) {
      value = ''
    }
    return value
  },
  set(name, value) {
    cookies.set(name, value, {expires: 7})
  },
  remove(name) {
    cookies.set(name, '', {expires: -1})
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
