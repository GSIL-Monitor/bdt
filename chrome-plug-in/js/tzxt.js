window.globalTableCell = {};
window.status = {
  '开始投注': 2,
  '开牌': 3,
  '停止投注': 0,
  '洗牌中': 1
}
window.wads = {
  '庄': '1',
  '闲': '2',
  '和': '3',
}
var yuanOption = {}, fxOption = {};
yuanOption = {
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
  '2000': '.NJ9Lz',
  '5000': '._2NB-M',
  '10000': '.E7Gaa'
};
fxOption = {
  '1': '.ZUikl',
  '2': '._7vTww',
  '3': '._3xcd-'
}
//
var BDTURL = 'https://www.bdt1314.xyz/';

/*
* todo
*
*
* */
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

function selectedYuan(list) {
  // console.log(list);
  // 选中金额
  let daskStatus = getWaitTime(list.tableNo - 1);
  // console.log(daskStatus)
  console.warn(list.tableNo, '在投注');
  // 获取到投注信息
  let yuan = list.tzje;
  let tableCode = list.tableNo;
  let fx = list.tzfx;
  if (daskStatus.time != 0 && daskStatus.count1 == list.battleNo && daskStatus.count2 == list.fitNo) {
    window.globalTableCell[list.id] = 0;
    TZZL(tableCode, yuan, fx, list);
  }
}

function isTZ(lists) {
  let XX = HQTZ();
  console.log('====================>XX', XX);
  let flag = false
  let flagType = [];
  for (let i = 0; i < XX.length; i++) {
    if (parseInt(XX[i].tableNo) == parseInt(lists.tableNo)) {
      flagType = XX[i].type;
    }
  }
  console.log('====================>flagType', flagType);
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
    flag = true;
  }
  //
  if (flagType.length != 0) {
    // 投注成功了，但不知道是不是这个投注系统的
    for (let j = 0; j < flagType.length; j++) {
      console.log('===============>', flagType[j].type, lists.tzfx)
      if (parseInt(flagType[j].type) == parseInt(lists.tzfx)) {
        updateTzztList(lists, true);
        flag = true;
        break
      }
    }
  } else {
    // 没有投注成功
    console.log(flagType.length, '没有投注成功');
    flag = false;
  }
  console.log('====================>flag', flag);
  return flag
}

function TZZL(tableCode, yuan, fx, list) {
  $('._2oHIg ._2Eb76').find(yuanOption[yuan]).parent().click(); // 选择筹码
  // _1a9j- OBOwe ripple text-l center-block _10ZbI
  $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).click(); // 选择牌
  $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).find('._1a9j-.OBOwe').click(); // 确认下注
  $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).click(); // 选择牌
  //
  $($('._3Y07G').children().eq(tableCode - 1)).find(fxOption[fx]).find('._1a9j-._1m_7V').click(); // 确认下注
  //
  console.warn(1111111111111111111111, isTZ(list));
  setTimeout(function () {
    if (!!!isTZ(list)) {
      // 没投注 isTZ(list)== false 代表还可以投注
      if (isTZ(list)) {
        return
      }
      window.globalTableCell[list.id] += 1;
      console.warn('==============>list.id', window.globalTableCell[list.id]);
      if (window.globalTableCell[list.id] > 3) {
        updateTzztList(list, false);
      } else {
        TZZL(tableCode, yuan, fx, list);
      }
    }
  }, 3500)
}

function HQTZ() {
  /* // _3au09
  * $('.YjSrR').eq(0).find('._3au09').text();
  *
  * */
  let list = $('._2Gj3P .YjSrR');
  let lArr = [];
  for (let i = 0; i < list.length; i++) {
    let jtype = $('._2Gj3P .YjSrR').eq(i).find('._2T-ED').children();
    let typeList = [];
    for (let j = 0; j < jtype.length; j++) {
      typeList.push({type: window.wads[jtype.eq(j).text().substring(0, 1)]});
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
    url: BDTURL + 'bdt/bjlTable/updateTzztList',
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
      url: BDTURL + 'bdt/bjlTable/getNeedTzDataList?' + $.param({
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
                  if (daskStatus.time > 3) {
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
                  if (daskStatus.time > 4) {
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
