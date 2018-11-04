var datamap = {};
datamap["_3m2-b"] = "1";
datamap["_1mjUZ"] = "2";
datamap["_2k309"] = "3";
datamap["_3jhEf"] = "4";
datamap["_15JYj"] = "5";
datamap["_1YmG6"] = "6";
datamap["_138Ip"] = "7";
datamap["_19CK0"] = "8";
datamap["_1FAb8"] = "9";
datamap["_3bR2d"] = "0";
datamap["_20aw3"] = "j";
datamap["_2psWU"] = "q";
datamap["O4fLT"] = "k";
datamap["_2jmcJ"] = "1";
datamap["_2i1TV"] = "2";
datamap["_3E9yR"] = "3";
datamap["cM8ah"] = "4";
datamap["mjgRe"] = "5";
datamap["_39Q6j"] = "6";
datamap["_29X7a"] = "7";
datamap["_37S_a"] = "8";
datamap["_3_O7D"] = "9";
datamap["_3Z457"] = "0";
datamap["_3QJhk"] = "j";
datamap["_13sJi"] = "q";
datamap["_2LuRH"] = "k";
datamap["-gItF"] = "1";
datamap["_1wqeR"] = "2";
datamap["_2L_yA"] = "3";
datamap["_1jMhL"] = "4";
datamap["wZugz"] = "5";
datamap["NvSPk"] = "6";
datamap["_1Nevk"] = "7";
datamap["_2xxWN"] = "8";
datamap["_17-7k"] = "9";
datamap["_3InVY"] = "0";
datamap["_2Q6Dz"] = "j";
datamap["_1EdIj"] = "q";
datamap["_2rErY"] = "k";
datamap["_1EJ1e"] = "1";
datamap["_2qr2W"] = "2";
datamap["_13s0z"] = "3";
datamap["_3FpIt"] = "4";
datamap["_1k4p5"] = "5";
datamap["_3axgC"] = "6";
datamap["_3mtFZ"] = "7";
datamap["_3GZUt"] = "8";
datamap["_3CcEJ"] = "9";
datamap["_2mlUQ"] = "0";
datamap["_2zy_e"] = "j";
datamap["_2UskY"] = "q";
datamap["_3LInw"] = "k";

var winmap = {};
winmap["_322Py"] = "闲对";
winmap["_7vTww"] = "闲";
winmap["_3xcd-"] = "和";
winmap["ZUikl"] = "庄";
winmap["EUsyn"] = "庄对";
winmap["_2hqy3"] = "龙";
winmap["_3g4fx"] = "和";
winmap["_1xv2M"] = "虎";

var deskstatemap = {};
deskstatemap["0"] = "洗牌中";
deskstatemap["1"] = "开始投注";
deskstatemap["2"] = "停止投注";
deskstatemap["3"] = "开牌";

var yuanOption = {
  '1': '._3JpJo',
  '2': '.n4JJI',
  '5': '.VxhGV',
  '10': '.TclIu',
  '20': '._1VJpW',
  '50': '._2kWbQ',
  '100': '._2-e_4',
  '200': '._2S7t-',
  '500': '._8d_yl',
  '1000': '._295TP',
  '2000': '.NJ9Lz'
};
var fxOption = {
  '1': '.ZUikl',
  '2': '._7vTww',
  '3': '._3xcd-'
}

var wads = {
  '庄': '1',
  '闲': '2',
  '和': '3',
}

var status = {
  '开始投注': 2,
  '开牌': 3,
  '停止投注': 0,
  '洗牌中': 1
}

// GET /account/getUserAccount
function getUserAccount() {
  clearInterval(window.chrome_userAccount);
  window.chrome_userAccount = setInterval(() => {
    let obj = {
      userId: window.localStorage.getItem('Chrome_Inner_User_Id')
    };
    $.ajax({
      type: 'post',
      url: 'http://139.198.177.39:8080/bdt/account/getUserAccount?' + $.param(obj),
      dataType: 'json',
      //  数据必须转换为字符串
      success: function (res) {
        // console.log(res);
        if (res.returnCode == 200) {
          let userCode = window.localStorage.getItem('chrome_UserName');
          let userPass = window.localStorage.getItem('chrome_UserPass');
          let returnObj = res.returnObject;
          $('._1h40X ._2kLct').eq(1).click();
          // console.log('===================>', res.returnObject);
          if ($.trim(userCode) == $.trim(returnObj.account) && $.trim(userPass) == $.trim(returnObj.password)) {
            return
          } else {
            window.localStorage.setItem('chrome_UserName', $.trim(returnObj.account));
            window.localStorage.setItem('chrome_UserPass', $.trim(returnObj.password));
            setTimeout(_ => {
              window.location.reload();
            }, 300)
          }
        }
      },
      error: function (XmlHttpRequest, textStatus, errorThrown) {

      }
    })
  }, 1000 * 5)
}

//
function setUserId() {
  // console.log($('.inject-from #innerUserId').val());
  window.localStorage.setItem('Chrome_Inner_User_Id', $.trim($('.inject-from #innerUserId').val()));
  getUserAccount();
}

function setUserName() {
  // $('.inject-from #chrome_UserName').val()
  // $('.inject-from #chrome_UserPass').val()
  window.localStorage.setItem('chrome_UserName', $.trim($('.inject-from #chrome_UserName').val()));
  window.localStorage.setItem('chrome_UserPass', $.trim($('.inject-from #chrome_UserPass').val()));
}

window.chrome_login_User_Obj = {
  // userName: 'nin23801',
  // password: 'k8u3f4qd',
  userName: window.localStorage.getItem('chrome_UserName'),
  password: window.localStorage.getItem('chrome_UserPass')
}
//
window.onload = function () {
  //console.log('页面加载完毕111111');
  var chrome_yzImg = $('._3IDPG ._1CgIs._1I2Om img');
  setTimeout(() => {
    chrome_login()
  }, 3000)
}

function getWaitTime(deskIndex) {
  //console.log("getWaitTime:deskIndex=" + deskIndex);
  var targets = $(".qMFBr");

  // console.log($(targets[deskIndex]).text());
  //console.log($(targets[deskIndex]).text().length);
  var textlength = $(targets[deskIndex]).text().length;
  var time = 0;
  if (2 == textlength) {
    time = $(targets[deskIndex]).text();
  }

  var countData = $(targets[deskIndex]).parents("._6VpXo").find("._1tFN6").text().split("-");
  var count1 = countData[0];
  var count2 = countData[1];
  // console.log(time + " " + count1 + " " + count2);
  return {time, count1, count2}
}

function formatterDateTime() {
  var date = new Date()
  var month = date.getMonth() + 1
  var datetime = date.getFullYear()
    + ""// "年"
    + (month >= 10 ? month : "0" + month)
    + ""// "月"
    + (date.getDate() < 10 ? "0" + date.getDate() : date
      .getDate())
    + ""
    + (date.getHours() < 10 ? "0" + date.getHours() : date
      .getHours())
    + ""
    + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
      .getMinutes())
    + ""
    + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
      .getSeconds());
  return datetime;
}

var imgCloseFlg = false;

function injectClose() {
  if (imgCloseFlg) {
    $('.inject-panel').addClass('isHeight');
    imgCloseFlg = false;
    return
  } else {
    $('.inject-panel').removeClass('isHeight');
    imgCloseFlg = true;
    return
  }
}

function editUserAccount() {
  let obj = {
    id: window.localStorage.getItem('Chrome_Inner_User_Id'),
    effectiveAmount: $('.FiO94._1oc5d').find('._3XN4m').text().replace(/,/g, '')
  };
  $.ajax({
    type: 'post',
    url: 'http://139.198.177.39:8080/bdt/account/editUserAccount?' + $.param(obj),
    dataType: 'json',
    //  数据必须转换为字符串
    success: function (result) {
      console.log(100);
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {

    }
  })
}

/*
* https://www.11gma.com/api/user/wallet?sid=454e487f-8b6a-4d31-be0d-81d535ed6777&t=1539193085792 // 获取金额当前用户的
*
*
* */

function chrome_login() {
  let base64Img = document.querySelector('._3IDPG ._1CgIs._1I2Om img').src; // 验证码
  $.ajax({
    type: 'post',
    url: 'https://route.showapi.com/184-5',
    dataType: 'json',
    data: {
      "showapi_timestamp": formatterDateTime(),
      "showapi_appid": '75499', //这里需要改成自己的appid
      "showapi_sign": '3be7fb2bd58b4dbca0c8c6a028fbcff0',  //这里需要改成自己的应用的密钥secret
      "img_base64": base64Img,
      "typeId": "34",
      "convert_to_jpg": "0",
      "needMorePrecise": "0"
    },
    success: function (result) {
      let resCode = result.showapi_res_body.Result;
      window.kv = new KeyboardEvent('input', {bubbles: true});
      window.el1 = $("._3IDPG input._3pu_i")[0];
      el1.value = window.chrome_login_User_Obj.userName;
      el1._valueTracker.setValue(el1);
      el1.dispatchEvent(kv);
      window.el2 = $("._3IDPG input._39jpe")[0];
      el2.value = window.chrome_login_User_Obj.password;
      el2._valueTracker.setValue(el2);
      el2.dispatchEvent(kv);
      window.el3 = $("._3IDPG input._2LWcS")[0];
      el3.value = resCode;
      el3._valueTracker.setValue(el3);
      el3.dispatchEvent(kv);
      // console.log('123', el1, el2, el3)
      $("._3IDPG button").click();

      setTimeout(() => {

        $('._3IR2e ._3MSiK').click();
        setTimeout(() => {
          $('.inject-from #innerUserId').val(window.localStorage.getItem('Chrome_Inner_User_Id'))
          $('.inject-from #chrome_UserName').val(window.localStorage.getItem('chrome_UserName'))
          $('.inject-from #chrome_UserPass').val(window.localStorage.getItem('chrome_UserPass'))
          var aa = setInterval(() => {
            editUserAccount();
          }, 1000 * 3);
          $('._1h40X ._2kLct').eq(1).click();
          document.querySelectorAll('._1h40X ._2kLct')[1].click();
          setTimeout(_ => {
            // 每过6小时就会从新登陆
            location.reload();
          }, 1000 * 60 * 60 * 6);
          startListen();
          // console.log(1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111)
          getUserAccount();
        }, 1000 * 6)
      }, 1000 * 3)
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {
      alert("操作失败!");
    }
  })
}

var callback = function (mutationsList) {
  for (var mutation of mutationsList) {
    //过滤无用变更（庄和数字显示）
    // if('i6ChJ _2T18P' === mutation.oldValue || 'i6ChJ' === mutation.oldValue){
    //     continue;
    // }
    var classData = mutation.target.className;
    var previousVal = mutation.oldValue;
    var text = $(mutation.target).text();

    //过滤10秒倒计时监听
    if ('eVjHq _1uWsG' === classData) {
      continue;
    }

    //过滤投注时的节点变化
    if ((classData && classData.indexOf('_2EW6q') != -1) || (previousVal && previousVal.indexOf('_2EW6q') != -1)) {
      continue;
    }

    //过滤倒计时
    if (2 == $(mutation.target).text().length) {
      continue;
    }


    // 0"洗牌中";
    // 1"开始投注";
    // 2"停止投注";
    // 3"开牌";
    var state = -1;
    if (classData && classData.indexOf('_3jUwm') > -1) {
      state = 3;
    } else if (previousVal && previousVal.indexOf('_3jUwm') > -1) {
      state = 1;
    } else if ('停止投注' === previousVal) {
      state = 2;
    } else if ('洗牌中' === text) {
      state = 0;
    }

    if (-1 == state) {
      continue;
    }

    // if(-1 == state){
    //     console.log("=====================");
    //     console.log('Mutation type: ' + mutation.type);
    //     console.log('Mutation target: ');
    //     console.log(mutation.target);
    //     console.log($(mutation.target).html());
    //     console.log('Previous attribute value: ' + previousVal);
    //     console.log("=====================");
    //     console.log("classData:" + classData);
    //     console.log("text:" + $(mutation.target).text());
    //     console.log("textlength:" + $(mutation.target).text().length);
    //     return;
    // }


    var t = $(mutation.target).parents("._6VpXo");

    var rtndata = analysisData(t, classData, state);
    console.log(rtndata.name1 + rtndata.name2 + '-' + rtndata.count1 + '-' + rtndata.count2 + 'state-' + state);
    console.log(JSON.stringify(rtndata));
    $('._1h40X ._2kLct').eq(1).click();
    document.querySelectorAll('._1h40X ._2kLct')[1].click();
    var wads = {
      '庄': '1',
      '闲': '2',
      '和': '3',
    }

    var status = {
      '开始投注': 2,
      '开牌': 3,
      '停止投注': 0,
      '洗牌中': 1
    }
    let result = "";
    for (let i = 0; i < rtndata.windatas.length; i++) {
      if ($.trim(rtndata.windatas[i]) == '庄') {
        result = "1";
        return
      }
      if ($.trim(rtndata.windatas[i]) == '闲') {
        result = "2";
        return
      }
      if ($.trim(rtndata.windatas[i]) == '和') {
        result = "3";
        return
      }
    }
    let params = {
      createDate: new Date().getTime(),
      tableNo: parseInt(rtndata.name2),
      battleNo: rtndata.count1,
      fitNo: rtndata.count2,
      card: rtndata.right.join(''),
      xianCard: rtndata.left.join(''),
      result: result,
      status: status[rtndata.desc]
    };
    if (status[rtndata.desc] == 0 || status[rtndata.desc] == '') {
      return false
    }
    if ($.trim(rtndata.name1) == '百家乐') {
      addTableData(params);
      //
      $("._1_GP_").css("display", "none");
      $("._9p7ST").css("display", "none");
      $("._3EV69").css("display", "none");
      $("._1QPxJ").css("display", "none");
      $("._1DaOE").css("display", "none");
      document.querySelector('.FlvPlayer__root video').pause();
    }
  }
};

//
function selectedYuan(list) {
  // console.log(list);
  // 选中金额
  let daskStatus = getWaitTime(list.tableNo - 1);
  // console.log(daskStatus)
  console.warn(list.tableNo, '在投注');
  // 获取到投注信息
  HQTZ()
  let yuan = list.tzje;
  let tableCode = list.tableNo;
  let fx = list.tzfx;
  if (daskStatus.count1 == list.battleNo && daskStatus.count2 == list.fitNo) {
    setTimeout(_ => {
      globalTableCell[list.id] = 0;
      TZZL(tableCode, yuan, fx, list);
    })
  }
}

function isTZ(lists) {
  let XX = HQTZ();
  let flag = true
  var flagType = [];
  for (let i = 0; i < XX.length; i++) {
    if (XX[i].tableNo == lists.tableNo) {
      flagType = XX[i].type;
    }
  }
  if (flagType.length > 0) {
    // 投注成功了，但不知道是不是这个投注系统的
    for (let j = 0; j < flagType.length; j++) {
      console.log('===============>', flagType[j].type, lists.tzfx)
      if (parseInt(flagType[j].type) == lists.tzfx) {
        flag = true
        updateTzztList(lists, true);
      }
    }
    return flag
  } else {
    // 没有投注成功
    console.log(flagType.length, '没有投注成功');
    flag = false;
  }
  let daskStatus = getWaitTime(lists.tableNo - 1);
  if (daskStatus.count1 == lists.battleNo && daskStatus.count2 == lists.fitNo) {
    if (daskStatus.time == 0) {
      flag = true;
      updateTzztList(lists, false);
    } else {
      flag = false
    }
  } else {
    updateTzztList(lists, false);
  }
  return flag
}

let globalTableCell = {};

//
function TZZL(tableCode, yuan, fx, list) {
  //
  setTimeout(_ => {
    $('._2oHIg ._2Eb76').find(yuanOption[yuan]).parent().click(); // 选择筹码
    // _1a9j- OBOwe ripple text-l center-block _10ZbI
    $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).click(); // 选择牌
    $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).find('._1a9j-.OBOwe').click(); // 确认下注
    $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).click(); // 选择牌
    $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).find('._1a9j-._1m_7V').click(); // 确认下注
  }, 1000)
  setTimeout(() => {
    console.log(1111111111111111111111, isTZ(list));
    if (!isTZ(list)) {
      globalTableCell[list.id]++;
      TZZL(tableCode, yuan, fx, list);
      if (globalTableCell[list.id] == 3) {
        updateTzztList(list, false);
      }
    } else {
      updateTzztList(list, true);
    }
  }, 2000)
}

function HQTZ() {
  /* // _3au09
  * $('.YjSrR').eq(0).find('._3au09').text();
  *
  * */
  let list = $('._2Gj3P .YjSrR');
  var lArr = [];
  for (var i = 0; i < list.length; i++) {
    let jtype = $('._2Gj3P .YjSrR').eq(i).find('._2T-ED').children();
    let typeList = [];
    for (let j = 0; j < jtype.length; j++) {
      typeList.push({type: wads[jtype.eq(j).text().substring(0, 1)]});
    }
    lArr.push({
      name: $('._2Gj3P .YjSrR').eq(i).find('._3au09').text().substring(0, 3),
      tableNo: parseInt($('._2Gj3P .YjSrR').eq(i).find('._3au09').text().substring(3, 6)),
      type: typeList,
      jine: parseInt($('._2Gj3P .YjSrR').eq(i).find('._2T-ED').text().substring(1, 10)),
    });
  }
  console.log(lArr);
  return lArr
}

// POST / bjlTable / updateTzztList

function updateTzztList(list, type) {
  list.tzzt = type;
  var array = [list];

  var jsonString = array;
  var listObj = {
    list: jsonString
  };
  var listObject = JSON.stringify(listObj);
  // console.log('1231231', listObj);
  $.ajax({
    type: 'post',
    url: 'http://139.198.177.39:8080/bdt/bjlTable/updateTzztList',
    contentType: 'application/json;charset=utf-8;',
    //  数据必须转换为字符串
    data: JSON.stringify(array),
    success: function (result) {
      // console.log('更新状态成功');
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {
      // console.log("操作失败!");
    }
  })
}

function getArrDifference(arr1, arr2) {
  let newArr = [];
  for (let i = 0; i < arr2.length; i++) {
    for (let j = 0; j < arr1.length; j++) {
      if (arr1[j] === arr2[i]) {
        newArr.push(arr1[j]);
      }
    }
  }
  // 返回 arr2不包含的 arr1
  return arr1.concat(newArr).filter(function (v, i, arr) {
    return arr.indexOf(v) === arr.lastIndexOf(v);
  });
}

//
function getNeedTzDataList() {
  clearInterval(window.getNeedTzDataListSetInv);
  $('._1h40X ._2kLct').eq(1).click();
  document.querySelectorAll('._1h40X ._2kLct')[1].click();
  window.getNeedTzDataListSetInv = setInterval(() => {
    $.ajax({
      type: 'get',
      url: 'http://139.198.177.39:8080/bdt/bjlTable/getNeedTzDataList?' + $.param({
        userId: window.localStorage.getItem('Chrome_Inner_User_Id')
      }),
      dataType: 'json',
      success: function (result) {
        // console.log(result);
        let getUserId = window.localStorage.getItem('Chrome_Inner_User_Id');
        if (result.returnCode == 200) {
          //
          let data = result.returnObject;
          if (data == null) {
            data = [];
          }
          // newData是 过滤出来的数据
          var newData = data.filter((e, i) => {
            return e.tzzh == getUserId;
          });
          // 投注系统1传过来的数据
          var newDataTZ = {
            'one': [],
            'two': []
          }
          newDataTZ.one = newData.filter((e, i) => {
            return e.tzxt == 1;
          });
          // 投注系统2传过来的数据
          newDataTZ.two = newData.filter((e, i) => {
            return e.tzxt == 2;
          });
          //
          var setLocalStorage = {
            'one': [],
            'two': []
          }
          /**
           * */
          if (window.localStorage) {

            if (window.localStorage.getItem('SETLocalStorage')) {
              setLocalStorage = window.JSON.parse(window.localStorage.getItem('SETLocalStorage'));
            } else {
              for (let i = 0; i < 12; i++) {
                //
                setLocalStorage.one.push({
                  battleNo: '',
                  tzfx: '',
                  fitNo: '',
                  tableNo: '',
                  tzje: '',
                  tzxt: '',
                  tzzh: '',
                  id: ''
                });
                //
                setLocalStorage.two.push({
                  battleNo: '',
                  tzfx: '',
                  fitNo: '',
                  tableNo: '',
                  tzje: '',
                  tzxt: '',
                  tzzh: '',
                  id: ''
                });
              }
            }
            //
            var currentTZ = [];
            newDataTZ.one.forEach((e, j) => {
              let newItem = setLocalStorage.one[e.tableNo - 1];
              if (!!!(e.tableNo == newItem.tableNo && e.id == newItem.id)) {
                //  数据不一样 需要投注
                let daskStatus = getWaitTime(e.tableNo - 1);
                if (daskStatus.count1 == e.battleNo && daskStatus.count2 == e.fitNo) {
                  if (daskStatus.time != 0) {
                    setLocalStorage.one[e.tableNo - 1] = e;
                    selectedYuan(e);
                  }
                }
              }
            });
            //
            newDataTZ.two.forEach((e, j) => {
              let newItem = setLocalStorage.two[e.tableNo - 1];
              if (!!!(e.tableNo == newItem.tableNo && e.id == newItem.id)) {
                //  数据不一样 需要投注
                let daskStatus = getWaitTime(e.tableNo - 1);
                if (daskStatus.count1 == e.battleNo && daskStatus.count2 == e.fitNo) {
                  if (daskStatus.time != 0) {
                    setLocalStorage.two[e.tableNo - 1] = e;
                    selectedYuan(e);
                  }
                }
              }
            });
            //
            // for (let i = 0; i < currentTZ.length; i++) {
            //   setTimeout(_ => {
            //     selectedYuan(currentTZ[i]);
            //   }, 0)
            // }
            // 设置 缓存
            window.localStorage.setItem('SETLocalStorage', window.JSON.stringify(setLocalStorage));
          }
        }
      },
      error: function (XmlHttpRequest, textStatus, errorThrown) {
        // console.log("操作失败!");
      }
    })
  }, 3333)
}

// http://localhost:8763/bdt/bjlTable/addTableData
function addTableData(params) {
  $.ajax({
    type: 'post',
    url: 'http://139.198.177.39:8080/bdt/bjlTable/addTableData?' + $.param(params),
    dataType: 'json',
    success: function (result) {
      // console.log(result);
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {
      console.log("操作失败!");
    }
  })
}

var configState = {
  'subtree': true,
  'attributes': true,
  'attributeOldValue': true,
  'characterDataOldValue': true,
  'attributeName': ['class']
};

var winState = {
  'subtree': true,
  'attributes': true,
  'attributeOldValue': true,
  'attributeName': ['class']
};

var observer = new MutationObserver(callback);

var flag = true;

function startListen() {
  // $(".inject-panel .startGetDisk").css(disabled, true);
  $(".inject-panel .startGetDisk").text('启动中');
  getNeedTzDataList();
  // console.log("start listen");
  // $("._2y2K9").css({display: 'none'})
  // $(".MlO78").css({display: 'none'})
  var targets = $(".qMFBr");

  // console.log(targets.length);

  for (var i = 0; i < targets.length; i++) {
    observer.observe(targets[i], configState);
    // if (12 == i) {
    //     break;
    // }

  }

  var winStateTargets = $(".i6ChJ");
  // console.log(winStateTargets.length);

  for (var j = 0; j < winStateTargets.length; j++) {
    observer.observe(winStateTargets[j], winState);
    // if (12 == i) {
    //     break;
    // }
  }
}

function analysisData(deskNode, classData, state) {

  //console.log("state:"+ statemap[classData]);

  var ps = $(deskNode).find(".e93on > ._2Qhx2 > ._1DTtZ._1Z5TM > ._3_Q04 > ._1I_B5");
  var left = $(ps).find("._5yJ9E > ._3QN0V > span");
  var right = $(ps).find(".Wa9oZ > ._3QN0V > span");

  var arrleft = [];
  $(left[0]).find("div").each(function (i, d) {
    var clazz = $(d).attr("class");
    if (clazz) {
      var p = clazz.split(" ")[1];
      var data = datamap[p];
      arrleft.push(data);
      //console.log(data);
    }
  });
  $(left[1]).find("div").each(function (i, d) {
    var clazz = $(d).attr("class");
    if (clazz) {
      var p = clazz.split(" ")[1];
      var data = datamap[p];
      arrleft.push(data);
      // console.log(data);
    }
  });
  var arrright = [];
  $(right[0]).find("div").each(function (i, d) {
    var clazz = $(d).attr("class");
    if (clazz) {
      var p = clazz.split(" ")[1];
      var data = datamap[p];
      arrright.push(data);
      // console.log(data);
    }
  });
  $(right[1]).find("div").each(function (i, d) {
    var clazz = $(d).attr("class");
    if (clazz) {
      var p = clazz.split(" ")[1];
      var data = datamap[p];
      arrright.push(data);
      // console.log(data);
    }
  });

  var desk = {};
  desk.left = arrleft;
  desk.right = arrright;

  var deskNameData = $(deskNode).find("._3fFvD span");
  if (deskNameData.length == 2) {
    desk.name1 = $(deskNameData[0]).text();
    desk.name2 = $(deskNameData[1]).text();
  } else {
    desk.name1 = $(deskNameData[0]).text();
    desk.name2 = "";
  }

  var countData = $(deskNode).find("._1tFN6").text().split("-");
  desk.count1 = countData[0];
  desk.count2 = countData[1];

  var winNodes = $(deskNode).find("div._3AxTi > div.i6ChJ > div._3jUwm");
  // console.log("winNodes:start");
  // console.log(winNodes);
  // console.log("winNodes:end");

  var winDatas = [];
  for (var i = 0; i < winNodes.length; i++) {
    //console.log($(winNodes[i]).attr("class"));
    var index = $(winNodes[i]).attr("class").split(" ")[1];
    var data = winmap[index];
    winDatas.push(data);
  }
  desk.windatas = winDatas;
  desk.state = state;
  desk.desc = deskstatemap[state + ''];

  // console.info(desk.name1 + desk.name2 + "  " + state + " " + deskstatemap[state + '']);
  return desk;
}

