window.WANGZHANURL = 'https://www.00rfd.com/';
var BDTURL = 'https://test.bdt1314.xyz/';

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
  getUserAccount();
  setTimeout(function () {
    chrome_login();
  }, 3000)
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
      url: BDTURL + 'bdt/account/getUserAccount?' + $.param(obj),
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
            // return
          } else {
            window.localStorage.setItem('chrome_UserName', $.trim(returnObj.account));
            window.localStorage.setItem('chrome_UserPass', $.trim(returnObj.password));
            setTimeout(function () {
              // _parent
              window.open(window.WANGZHANURL, "_self", '', true);
            }, 300)
          }
          //
          if (window.localStorage.getItem('chrome_user_remark')) {
            // $.trim(returnObj.remark)
            var re = window.localStorage.getItem('chrome_user_remark');
            if (re != returnObj.remark) {
              window.localStorage.setItem('chrome_user_remark', returnObj.remark);
              window.open(window.WANGZHANURL, "_self", '', true);
            }
            // console.log("chrome_user_remark", true);
          } else {
            window.localStorage.setItem('chrome_user_remark', returnObj.remark);
            // console.log("chrome_user_remark", false);
          }
        }
      },
      error: function (XmlHttpRequest, textStatus, errorThrown) {

      }
    })
  }, 1000 * 5)
}

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

var imgCloseFlg = true;

function injectClose() {
  if (imgCloseFlg) {
    $('.inject-panel').addClass('isHeight');
    imgCloseFlg = false;
  } else {
    $('.inject-panel').removeClass('isHeight');
    imgCloseFlg = true;
  }
}

function editUserAccount() {
  let obj = {
    id: window.localStorage.getItem('Chrome_Inner_User_Id'),
    effectiveAmount: $('.FiO94._1oc5d').find('._3XN4m').text().replace(/,/g, '')
  };
  $.ajax({
    type: 'post',
    url: BDTURL + 'bdt/account/editUserAccount?' + $.param(obj),
    dataType: 'json',
    //  数据必须转换为字符串
    success: function (result) {
      // console.log(100);
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {

    }
  })
}

function chrome_login() {
  let base64Img = document.querySelector('._3IDPG ._1CgIs._1I2Om img').src; // 验证码
  if (!!!base64Img) {
    return false
  }
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
      setTimeout(function () {
        $('._3IR2e ._3MSiK').click();
        setTimeout(function () {
          //
          $('.inject-from #innerUserId').val(window.localStorage.getItem('Chrome_Inner_User_Id'))
          $('.inject-from #chrome_UserName').val(window.localStorage.getItem('chrome_UserName'))
          $('.inject-from #chrome_UserPass').val(window.localStorage.getItem('chrome_UserPass'))
          getUserAccount();
          //
          clearInterval(window.editUserAccountInterval);
          window.editUserAccountInterval = setInterval(() => {
            editUserAccount();
            //
          }, 1000 * 3);
          //
          $('._1h40X ._2kLct').eq(1).click();
          document.querySelectorAll('._1h40X ._2kLct')[1].click();
          //
          // setTimeout(function () {
          //   // 每过6小时就会从新登陆
          //   window.close();
          //   // _parent
          //   window.open(window.WANGZHANURL, "_blank", '', true);
          // }, 1000 * 60 * 60 * 6);
          //
          startListen();
          setInterval(function () {
            NEW_DATE_GET_DAY();
          }, 1000 * 30)
        }, 1000 * 6)
      }, 1000 * 3)
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {
      alert("操作失败!");
    }
  })
}

function NEW_DATE_GET_DAY() {
  function getDays() {
    // 获取当前周的 next prev
    var now = new Date;
    var day = now.getDay();
    var week = "7123456";
    var first = 0 - week.indexOf(day);
    var f = new Date;
    f.setDate(f.getDate() + first);
    var last = 6 - week.indexOf(day);
    var l = new Date;
    l.setDate(l.getDate() + last);
    return [f.getTime(), l.getTime()];
  }


  var myDate = new Date();
  var myDay = myDate.getDay();//获取存储当前日期
  var weekday = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
  var weekdayIndex = [0, 1, 2, 3, 4, 5, 6];
  var newWeekday = weekdayIndex[myDay];
  var getTime = myDate.getTime();
  if (myDay == 1) {
    // 是周一
    if (myDate.getHours() === 13) {
      // 且现在是中午1点
      if (myDate.getMinutes() == 1) {
        setTimeout(() => {
          window.open(window.WANGZHANURL, "_self", '', true);
        }, 1000 * 30)
      }
    } else if (myDate.getHours() > 13) {
      // 大于1点
    }
  }
}

var callback = function (mutationsList) {
  setTimeout(function () {
    currentTableMsgFun();
  }, 2800)
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

    if ($.trim(rtndata.name1) == '百家乐') {
      addTableData(rtndata);
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

function setStateTable(cls) {
  // $($("._6VpXo").find('.qMFBr').eq(2).children().get(0)).text()  '具體中文數字 和提升文字'
  var text = $($(cls).find('.qMFBr').children().get(0)).text(); // '具體中文數字 和提升文字'
  var kaipai = $(cls).find('.i6ChJ').find('._3jUwm').length;

  // 0"洗牌中";
  // 1"开始投注";
  // 2"停止投注";
  // 3"开牌";
  var state = -1;
  if (text == '洗牌中') {
    state = 0;
  } else if (text == '停止投注') {
    state = 2;
  } else if (text == '结算中' && kaipai > 0) {
    state = 3
  } else {
    state = 1;
  }
  return state
}

function currentTableMsgFun() {
  var targets = $(".qMFBr");
  var target12 = $("._6VpXo");
  let Type = {};
  var table12Arr = [];
  for (var i = 0; i < target12.length; i++) {
    // console.log($(target12[i]));
    var rtndata = analysisData($(target12[i]), setStateTable($(target12[i])))
    table12Arr.push(rtndata);
  }

  if (window.localStorage.getItem('CHROME_TABLE_DATA')) {
    var CHROME_TABLE_DATA = window.JSON.parse(window.localStorage.getItem('CHROME_TABLE_DATA'));
    CHROME_TABLE_DATA.forEach((e, i) => {
      if (e.count1 == table12Arr[i].count1 && e.count2 == table12Arr[i].count2 && e.state == table12Arr[i].state) {
        //   局号&&副号&&当前状态
      } else {
        e = table12Arr[i];
        addTableData(e);
      }
    })
    window.localStorage.setItem('CHROME_TABLE_DATA', window.JSON.stringify(table12Arr));
  } else {
    table12Arr.forEach((e, i) => {
      addTableData(e);
    })
    window.localStorage.setItem('CHROME_TABLE_DATA', window.JSON.stringify(table12Arr));
  }
  // console.log('$(target12[i]============================>', table12Arr);
  // if(t){
  //
  // }
  // _34Nqi ZUikl _67CnM #### _3jUwm
  // $("._6VpXo").eq(0).find('._3fFvD').text() 获取到的桌号（百家乐XX）
  // $("._6VpXo").eq(0).find('._1tFN6').text()  获取到的句号和副号
  // console.log(target12);
  // console.log(targets);
  // $($("._6VpXo").find('.qMFBr').eq(2).children().get(0)).text() 取到 狀態
  // $($("._6VpXo").find('.qMFBr').eq(5).children().get(0)).children().attr('class')
}

// http://localhost:8763/bdt/bjlTable/addTableData
function addTableData(rtndata) {
  let wads = {
    '庄': '1',
    '闲': '2',
    '和': '3',
  }
  let status = {
    '开始投注': 2,
    '开牌': 3,
    '停止投注': 0,
    '洗牌中': 1
  }
  let setResult = "";
  for (let i = 0; i < rtndata.windatas.length; i++) {
    // console.log('====>', rtndata.windatas[i]);
    if ($.trim(rtndata.windatas[i]) == '庄') {
      setResult = "1";
    } else if ($.trim(rtndata.windatas[i]) == '闲') {
      setResult = "2";
    } else if ($.trim(rtndata.windatas[i]) == '和') {
      setResult = "3";
    }
  }
  //console.log('result================>', setResult);
  let params = {
    createDate: new Date().getTime(),
    tableNo: parseInt(rtndata.name2),
    battleNo: rtndata.count1,
    fitNo: rtndata.count2,
    card: rtndata.right.join(''),
    xianCard: rtndata.left.join(''),
    result: setResult,
    status: status[rtndata.desc],
    remark: `${window.localStorage.getItem('Chrome_Inner_User_Id')}-${JSON.stringify(rtndata)}`,
    userId: window.localStorage.getItem('Chrome_Inner_User_Id')
  };
  if (status[rtndata.desc] == 0 || status[rtndata.desc] == '') {
    return false
  }
  //
  $.ajax({
    type: 'post',
    url: BDTURL + 'bdt/bjlTable/addTableData',
    dataType: 'json',
    data: params,
    success: function (result) {
      // console.log(result);
    },
    error: function (XmlHttpRequest, textStatus, errorThrown) {
      //console.log("操作失败!");
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

function analysisData(deskNode, state) {

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
    var clazzL2 = $(d).attr("class");
    if (clazzL2) {
      var p = clazzL2.split(" ")[1];
      var data = datamap[p];
      arrleft.push(data);
      // console.log(data);
    }
  });
  var arrright = [];
  $(right[0]).find("div").each(function (i, d) {
    var clazzR = $(d).attr("class");
    if (clazzR) {
      var p = clazzR.split(" ")[1];
      var data = datamap[p];
      arrright.push(data);
      // console.log(data);
    }
  });
  $(right[1]).find("div").each(function (i, d) {
    var clazzR2 = $(d).attr("class");
    if (clazzR2) {
      var p = clazzR2.split(" ")[1];
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

