var datamap = {};
datamap["_3m2-b"] = "A1";
datamap["_1mjUZ"] = "A2";
datamap["_2k309"] = "A3";
datamap["_3jhEf"] = "A4";
datamap["_15JYj"] = "A5";
datamap["_1YmG6"] = "A6";
datamap["_138Ip"] = "A7";
datamap["_19CK0"] = "A8";
datamap["_1FAb8"] = "A9";
datamap["_3bR2d"] = "A10";
datamap["_20aw3"] = "A11";
datamap["_2psWU"] = "A12";
datamap["O4fLT"] = "A13";
datamap["_2jmcJ"] = "B1";
datamap["_2i1TV"] = "B2";
datamap["_3E9yR"] = "B3";
datamap["cM8ah"] = "B4";
datamap["mjgRe"] = "B5";
datamap["_39Q6j"] = "B6";
datamap["_29X7a"] = "B7";
datamap["_37S_a"] = "B8";
datamap["_3_O7D"] = "B9";
datamap["_3Z457"] = "B10";
datamap["_3QJhk"] = "B11";
datamap["_13sJi"] = "B12";
datamap["_2LuRH"] = "B13";
datamap["-gItF"] = "C1";
datamap["_1wqeR"] = "C2";
datamap["_2L_yA"] = "C3";
datamap["_1jMhL"] = "C4";
datamap["wZugz"] = "C5";
datamap["NvSPk"] = "C6";
datamap["_1Nevk"] = "C7";
datamap["_2xxWN"] = "C8";
datamap["_17-7k"] = "C9";
datamap["_3InVY"] = "C10";
datamap["_2Q6Dz"] = "C11";
datamap["_1EdIj"] = "C12";
datamap["_2rErY"] = "C13";
datamap["_1EJ1e"] = "D1";
datamap["_2qr2W"] = "D2";
datamap["_13s0z"] = "D3";
datamap["_3FpIt"] = "D4";
datamap["_1k4p5"] = "D5";
datamap["_3axgC"] = "D6";
datamap["_3mtFZ"] = "D7";
datamap["_3GZUt"] = "D8";
datamap["_3CcEJ"] = "D9";
datamap["_2mlUQ"] = "D10";
datamap["_2zy_e"] = "D11";
datamap["_2UskY"] = "D12";
datamap["_3LInw"] = "D13";

var winmap = {};
winmap["_322Py"] = "闲对";
winmap["_7vTww"] = "闲";
winmap["_3xcd"] = "和";
winmap["ZUikl"] = "庄";
winmap["EUsyn"] = "庄对";
winmap["_2hqy3"] = "龙";
winmap["_3g4fx"] = "和";
winmap["_1xv2M"] = "虎";




window.chrome_login_User_Obj = {
    userName: 'nin23801',
    password: 'aabb123123',
}
//
window.onload = function () {
    console.log('页面加载完毕111111');
    var chrome_yzImg = $('._3IDPG ._1CgIs._1I2Om img');
    setTimeout(() => {
        chrome_login()
    }, 3000)
}

function queryDeskDetail() {
    console.log("234324start queryDeskDetail");
    var rtndata = [];
    var deskes = $("[class='_6VpXo']");
    for (var i = 0; i < deskes.length; i++) {
        var ps = $(deskes[i]).find(".e93on > ._2Qhx2 > ._1DTtZ._1Z5TM > ._3_Q04 > ._1I_B5");
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
        rtndata.push(desk);
    }

    console.log(JSON.stringify(rtndata));
    return rtndata;
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

function chrome_login() {
    let base64Img = document.querySelector('._3IDPG ._1CgIs._1I2Om img').src; // 验证码
    $.ajax({
        type: 'post',
        url: 'http://route.showapi.com/184-5',
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
            console.log('123', el1, el2, el3)
            $("._3IDPG button").click();
            setTimeout(() => {
                $('._3IR2e ._3MSiK').click();
            }, 3000)
        },
        error: function (XmlHttpRequest, textStatus, errorThrown) {
            alert("操作失败!");
        }
    })
}

var callback = function(mutationsList) { 
    for(var mutation of mutationsList) { 
        //console.log(mutation); 
        console.log("=====================");
        if (mutation.type == 'attributes') { 
            var classData = mutation.target.className;
            console.log("classData:" + classData);
            var t = $(mutation.target).parents("._6VpXo");

            var rtndata = analysisData(t);
            console.log(JSON.stringify(rtndata));
        }

        observer.observe(mutation.target, config);
    } 
}; 
// var config = { attributes: true}; 
var config = { attributes: true };
var observer = new MutationObserver(callback); 

function startListen() { 
    console.log("start listen");

    var targets = $(".qMFBr div[role!='button']:first-child");
    console.log(targets.length);

    for(var i=0;i<targets.length;i++) {
        console.log(targets[i]);
        observer.observe(targets[i], config); 
    }
}


function analysisData(deskNode) {

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
    if(deskNameData.length == 2){
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
    var winDatas = [];
    for(var i=0;i<winNodes.length;i++) {
        var index = $(winNodes[i]).attr("class").split(" ")[1];
        var data = winmap[index];
        winDatas.push(data);
    }
    desk.windatas = winDatas;

    return desk;
}
