/*
 * todo
 * describe: common javascript file
 * ldk || cwd || zyl
 * */
export default {
  // 去除换行
  ClearBr(key) {
    if (key) {
      // key = key.replace(/<\/?.+?>/g, '')
      key = key.replace(/<br\/>/g, '')
      key = key.replace(/[\r\n]/g, '')
    } else {
      key = ''
    }
    return key
  },
  openWindow(url) {
    var linkA = document.createElement('a');
    linkA.setAttribute('target', '_blank');
    linkA.setAttribute('href', url);
    linkA.setAttribute('id', url);
    if (!document.getElementById(url)) {
      document.body.appendChild(linkA)
    }
    linkA.click();
    setTimeout(() => {
      linkA.parentNode.removeChild(linkA)
    }, 300);
    // var tempWindow = window.open('_blank'); // 先打开页面
    // tempWindow.location = location.host + url; // 后更改页面地址
  },
  // 返回用户使用浏览器类型
  browserSort() {
    var sort = ''
    var userAgent = navigator.userAgent // 取得浏览器的userAgent字符串
    // 判断是否Opera浏览器
    if (userAgent.indexOf('Opera') > -1) {
      sort = 'Opera'
      return sort
    }
    // 判断是否Firefox浏览器
    if (userAgent.indexOf('Firefox') > -1) {
      sort = 'FF'
      return sort
    }
    if (userAgent.indexOf('Chrome') > -1) {
      console.log('Chrome')
      sort = 'Chrome'
      return sort
    }
    // 判断是否Safari浏览器
    if (userAgent.indexOf('Safari') > -1) {
      console.log('Safari')
      sort = 'Safari'
      return sort
    }
    // 判断是否IE浏览器
    if (userAgent.indexOf('compatible') > -1 && userAgent.indexOf('MSIE') > -1) {
      console.log('IE')
      sort = 'IE'
      return sort
    }

  },
  /* 控制筛选的类的显示文字 */
  number(value, num) {
    if (num) {
      console.log('number', num)
      if (value.length >= num) {
        value = value.substring(0, num) + '...'
      }
      return value
    } else {
      if (value.length >= 10) {
        value = value.substring(0, 10) + '...'
      }
      return value
    }
  },
  /* 高亮 input的value, 文本text */
  highlight(value, content) {
    return content.replace(value, '<highlight class="HEGHLIGHT">' + value + '</highlight>')
  },
  //
  key(string, keyword) {
    //
    let starArray = [], star = '';
    starArray = keyword.split(" ");
    for (let i = 0; i < starArray.length; i++) {
      if (starArray[i] == '') {
        starArray.splice(i, 1);
        i = i - 1;
      }
    }
    if (starArray.length <= 0) {
      return string
    }
    let strings = string;
    for (let j = 0; j < starArray.length; j++) {
      strings = strings.replace(starArray[j], '<span style="color:#ffc051;">' + starArray[j] + '</span>');
      // console.log(star);
    }
    return strings;
  },
  /* 取字符串十位 */
  subString(value, length) {
    if (length) {
      if (value.length >= length) {
        value = value.substring(0, length) + '...'
      }
      return value
    }
    if (value.length >= 10) {
      value = value.substring(0, 10) + '...'
    }
    return value
  },
  unique: function (arr) {
    var result = [];
    var obj = {};
    for (let i = 0; i < arr.length; i++) {
      if (!obj.hasOwnProperty(arr[i])) {
        result.push(arr[i]);
        obj[arr[i]] = 0;
      }
    }
    return result
  },
  // 数组去重
  removal(item) {
    var arr = item,
      result = [],
      i,
      j,
      len = arr.length;
    for (i = 0; i < len; i++) {
      for (j = i + 1; j < len; j++) {
        if (arr[i] == arr[j]) {
          j = ++i;
        }
      }
      result.push(arr[i]);
    }
    //  console.log(result)
    return result;
  },

  // 截取时间段
  getLastOfMonth(index) {
    var end = new Date()
    var years = end.getFullYear()
    var dayDate = end.getDate()
    var months = Number(end.getMonth()) + 1
    // 打印某年某月的最后一天
    if (index == 0) {

    } else if (index == 1) {
      let lastDay = new Date().getTime() - 1000 * 3600 * 24 * 30
      months = new Date(lastDay).getMonth() + 1
      dayDate = new Date(lastDay).getDate()
    } else if (index == 2) {
      let lastDay = new Date().getTime() - 1000 * 3600 * 24 * 30 * 3
      months = new Date(lastDay).getMonth() + 1
      dayDate = new Date(lastDay).getDate()
    } else if (index == 3) {
      let lastDay = new Date().getTime() - 1000 * 3600 * 24 * 30 * 6
      months = new Date(lastDay).getMonth() + 1
      dayDate = new Date(lastDay).getDate()
    } else if (index == 4) {
      months = 1
      dayDate = 1
    } else if (index == 5) {
      years = years - 1
    } else if (index == 6) {
      years = years - 2
    } else if (index == 7) {
      years = years - 5
    } else if (index == 8) {
      years = years - 10
    }
    if (months < 10) {
      months = '0' + months
    }
    if (dayDate < 10) {
      dayDate = '0' + dayDate
    }
    var startDateTwo = years + '-' + months + '-' + (dayDate)
    return startDateTwo
  },
  // 时间-->距离之前时间
  getBeforeDate: function (num, timestamp) {
    var n = parseInt(num)
    var d = new Date()
    var year = d.getFullYear()
    var mon = d.getMonth() + 1
    var day = d.getDate()
    if (day <= n) {
      if (mon > 1) {
        mon = mon - 1
      } else {
        year = year - 1
        mon = 12
      }
    }
    d.setDate(d.getDate() - n)
    if (timestamp == true) {
      console.log(timestamp, 'd.setDate(d.getDate() - n)', d.setDate(d.getDate() - n))
      return d.setDate(d.getDate() - n)
    }
    year = d.getFullYear()
    mon = d.getMonth() + 1
    day = d.getDate()
    var s = year + '-' + (mon < 10 ? ('0' + mon) : mon) + '-' + (day < 10 ? ('0' + day) : day)
    return s
  },
  /* 处理时间戳 */
  add0: function (m) {
    return m < 10 ? '0' + m : m
  },
  /* 文本年月日显示 */
  transTime: function (shijianchuo) {
    if (shijianchuo == null || shijianchuo == '') {
      let date = ''
      return date
    } else {
      // shijianchuo是整数，否则要parseInt转换
      let time = new Date(parseInt(shijianchuo))
      let y = time.getFullYear()
      let m = time.getMonth() + 1
      let d = time.getDate()
      let h = time.getHours()
      let mm = time.getMinutes()
      let s = time.getSeconds()
      let date = y + '年' + this.add0(m) + '月' + this.add0(d) + '日'
      let date1 = y + '-' + this.add0(m) + '-' + this.add0(d)
      let date2 = y + '-' + this.add0(m) + '-' + this.add0(d) + ' ' + this.add0(h) + ':' + this.add0(mm) + ':' + this.add0(s)
      let date3 = this.add0(m) + '-' + this.add0(d)
      let date4 = y + '-' + this.add0(m) + '-' + this.add0(d) + ' ' + this.add0(h) + ':' + this.add0(mm)
      return ([date, date1, date2, date3, date4])
    }
  },
  /* 多关键字 空格替换逗号处理 */
  transStr: function (str) {
    if (str) {
      return this.ImpArr(str.split(' ')).toString()
    } else {
      return ''
    }
  },
  /* url上获取搜索关键字 逗号替换空格处理 */
  transComma: function (str) {
    if (str !== '') {
      return this.ImpArr(str.split(',')).join(' ')
    }
  },
  /*  */
  Trim: function (str) {
    return str.replace(/(^\s*)|(\s*$)/g, '')
  },
  /* 去除空元素 */
  ImpArr: function (array) {
    let arr = array
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] === '' || typeof (arr[i]) === 'undefined') {
        arr.splice(i, 1)
        i = i - 1
      }
    }
    return (arr)
  },
  // replace(/<(?!img|p|/p).*?>/g, "");  去除html里面的标签保留p 和img

  // 唯一标识uuid
  uuid: function () {
    function S4() {
      return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
    }

    return (S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4())
  },
  /* 获取当前时间（年月日） */
  getNowDate() {
    const date = new Date()
    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate()
    return (year + '-' + month + '-' + day)
  },
  /* 获取前一周时间 （年月日） */
  getLastDate() {
    var now = new Date()
    var date = new Date(now.getTime() - 7 * 24 * 3600 * 1000)
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var str = year + '-' + month + '-' + day
    return str
  },
  // 获取相比当前时间的之前多少天时间
  lastDate(days) {
    const now = new Date()
    let date = new Date(now.getTime() - days * 24 * 3600 * 1000)
    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate();
    return (year + '-' + month + '-' + day)
  },
  // 获取相比当前时间的之前多少天时间
  lastPrevDate(nowDate, days) {
    let date = new Date(parseInt(nowDate) - days * 24 * 3600 * 1000)
    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate();
    return (year + '-' + month + '-' + day)
  },
  ispc() {
    let system = {win: false, mac: false, xll: false}
    let p = navigator.platform
    system.win = p.indexOf('Win') == 0
    system.mac = p.indexOf('Mac') == 0
    system.x11 = (p == 'X11') || (p.indexOf('Linux') == 0)
    let iphone = navigator.userAgent.indexOf('iPhone') < 0
    let Android = navigator.userAgent.indexOf('Android') < 0
    if ((system.win || system.mac || system.xll) && iphone && Android) {
      return true
    } else {
      return false
    }
  },
  // 清除字符串内嵌样式
  clearStyle(style) {
    var reg = new RegExp(/style="((\S|\s)+)"/, 'gm')
    var text = style.replace(reg, '')
    return text
  },
  // 日期换算成时间戳 10位
  getTimestamp: function (date) {
    date = new Date(Date.parse(date.replace(/-/g, '/')))
    date = date.getTime() / 1000
    return date
  },
  // 日期换算成时间戳 13位
  getTimestampms: function (date) {
    date = new Date(Date.parse(date.replace(/-/g, '/')))
    return (date.getTime());
  },
  //
  abs(val, isParseInt) {
    let isParseInts = isParseInt ? 'true' : 'false'
    console.log('isParseInts', isParseInts);
    if (val == null) {
      return 0
    }
    if (val * 1) {
      val = val * 1
    } else {
      return parseFloat(val).toFixed(2)
    }
    // 金额转换 元 保留2位小数 并每隔3位用逗号分开 1,234.56
    var str = (val).toFixed(2) + '';
    var intSum = str.substring(0, str.indexOf(".")).replace(/\B(?=(?:\d{3})+$)/g, ','); // 取到整数部分
    var dot = str.substring(str.length, str.indexOf(".")); // 取到小数部分搜索
    var ret = intSum + dot;
    return ret;
  },
  timestampToTime(timestamp) {
    function change(t) {
      if (t < 10) {
        return "0" + t;
      } else {
        return t;
      }
    }

    var date = new Date(parseInt(timestamp)); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
    let Y = date.getFullYear();
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    let D = change(date.getDate());
    let h = change(date.getHours()) + ':';
    let m = change(date.getMinutes()) + ':';
    let s = change(date.getSeconds());
    return ({
      date: [Y, M, D],
      time: h + m + s
    });
  }

}
