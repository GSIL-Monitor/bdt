import Axios from '../service/http'

let protocol = document.location.protocol == 'https:' ? 'https:' : 'http:'

const HOSTS = {
  dev: {
    api: protocol + '//tianfeng.api.in-hope.com.cn/' // 接口地址
  },
  development: {
    // api: protocol + '//192.168.150.238:8081/'
    api: protocol + '//tianfeng.api.in-hope.com.cn/'
  },
  testing: {
    api: protocol + '//tianfeng.api.in-hope.com.cn/'
  },
  production: {
    api: protocol + '//tf.in-hope.cn/'
  }
}
const HOST = HOSTS[process.env.XA_ENV].api
const IP = HOSTS[process.env.XA_ENV].host
export default {
  // origin: protocol + '//' + document.location.host + '/',
  host: IP,
  //  登录
  login: function (params = {}) {
    return Axios({method: 'POST', url: HOST + 'user/login', params: params})
  },
  // // 倾向时间线
  chartTendencyTimeLine: (params = {}) => {
    return Axios({method: 'GET', url: HOST + 'es/chartTendencyTimeLine', params: params})
  },
  // // 关键词
  getWordCloud: (params = {}) => {
    return Axios({method: 'GET', url: HOST + 'wordcloud/getWordCloud', params: params})
  },
  //  // 声量时间线
  chartVolumeTimeLine: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'es/chartVolumeTimeLine', params: params})
  },
  // //
  getPropagationPath: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'es/getPropagationPath', params: params})
  },
  // // 新闻舆情列表
  listSentimentNews: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'es/listSentimentNews', params: params})
  },
  // // top10负面新闻
  top10NegativeNews: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'es/top10NegativeNews', params: params})
  },
  // 舆情合计
  countSentimentByDate: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'es/countSentimentByDate', params: params})
  },
  // 研报舆情合计
  countSentimentReportByDate: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'report/countSentimentReportByDate', params: params})
  },
  // 研报合计
  countReportByDate: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'report/countReportByDate', params: params})
  },
  // 研报搜索
  reportSearch: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'report/search', params: params})
  },
  // 研报舆情
  searchSentimentReport: function (params = {}) {
    return Axios({method: 'GET', url: HOST + 'report/searchSentimentReport', params: params})
  },
  //
  // // 查询关键词
  selectKeyWords: (params = {}) => {
    return Axios({method: 'GET', url: HOST + 'keyword/selectKeyWords', params: params})
  },
  // // 删除关键词
  delKeyWord: (params = {}) => {
    return Axios({method: 'POST', url: HOST + 'keyword/delKeyWord', params: params})
  },
  // // addKeyWordType // 添加关键词类型
  addKeyWordType: (params = {}) => {
    return Axios({method: 'POST', url: HOST + 'keyword/addKeyWordType', params: params})
  },
  // addKeyWord // 添加关键词
  addKeyWord: (params = {}) => {
    return Axios({method: 'POST', url: HOST + 'keyword/addKeyWord', params: params})
  },
  // delKeyWordType // 删除关键词类型
  delKeyWordType: (params = {}) => {
    return Axios({method: 'POST', url: HOST + 'keyword/delKeyWordType', params: params})
  },
  // editKeyWordType // 修改关键词类型
  editKeyWordType: (params = {}) => {
    return Axios({method: 'POST', url: HOST + 'keyword/editKeyWordType', params: params})
  }
}
