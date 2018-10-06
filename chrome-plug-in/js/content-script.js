// 'use strict';

// 注意，必须设置了run_at=document_start 此段代码才会生效
console.log('这是content script!');
console.log(window.location);

// let username = document.cookie.split(";")[0].split("=")[1];
//JS操作cookies方法!
//写cookies
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
}

function chrome_isLocation() {
    let chromeLocationHostArray = ['www.22gvb.com', 'www.00rfd.com'];
    let chrome_isActive = false;
    for (let i = 0; i < chromeLocationHostArray.length; i++) {
        if (window.location.host == chromeLocationHostArray[i]) {
            chrome_isActive = true;
            console.log('chrome——要监听的网站');
        }
    }
    return chrome_isActive
}

let chrome_isLocationS = chrome_isLocation();

window.onload = function () {
    if (chrome_isLocationS) {
        // chrome_login()
    }
    console.log(111);
    console.log(chrome.extension);
}

// DOMContentLoaded 页面加载完毕
window.addEventListener('DOMContentLoaded', function () {
    // 注入自定义JS
    if (chrome_isLocationS) {
        injectCustomJs();
        injectCustomJs("js/jquery-3.3.1.min.js");
        initCustomPanel();
        setTimeout(_ => {
            console.log('chrome——', window.document.cookie);
            console.log('chrome——', getCookie('__cfduid'));
            console.log('chrome——', getCookie('_ga'));
            console.log('chrome——', getCookie('_gat'));
            console.log('chrome——', getCookie('_gid'));
            console.log('chrome——', getCookie('sessionId'));
        }, 1200)
    }
    console.log('DOMContentLoaded');
});

function initCustomPanel() {
    var panel = document.createElement('div');
    panel.className = 'inject-panel';
    panel.innerHTML = `
		<h2>inject-panel：</h2>
		<div class="btn-area" style="margin-bottom: 60px;">
            <a href="javascript:queryDeskDetail()" style="color: #545454">queryDeskDetail</a>
            <a href="javascript:startListen()" style="color: #545454">startListen</a>
        </div>
        <button id="chromeLoginClick" type="button" onclick="chrome_login()" style="color: black">获取验证码并解析赋值</button>	
        <button type="button" onclick="qagetIndex()" style="color: black">同意的确认</button>	
        <button type="button" onclick="qasetCookie()" style="color: black">设置COOKIE</button>
        <button type="button" onclick="selectedYuan()" style="color: black">选择下注金额</button>	
`;
    document.body.appendChild(panel);
}

// 向页面注入CSS
function loadStyle(url) {
    var link = document.createElement('link');
    link.setAttribute('type', 'text/css');
    link.setAttribute('rel', 'stylesheet');
    link.href = chrome.extension.getURL(url);
    link.onload = function () {
        this.parentNode.removeChild(this);
    };
    document.body.appendChild(link);
}

// 向页面注入JS
function injectCustomJs(jsPath) {
    jsPath = jsPath || 'js/inject.js';
    var temp = document.createElement('script');
    temp.setAttribute('type', 'text/javascript');
    temp.src = chrome.extension.getURL(jsPath);
    temp.onload = function () {
        this.parentNode.removeChild(this);
    };
    document.body.appendChild(temp);
}

function selectedYuan() {
    //
    $($("._2oHIg ._2Eb76:not(._3BVsE)")[0]).children().click(); // 选择筹码
    //
    $($('._3Y07G').children()[0]).find('._34Nqi.ZUikl._67CnM').click(); // 选择牌
    //
    $($('._3Y07G').children()[0]).find('._34Nqi.ZUikl._67CnM').find('._1a9j-._1m_7V').click(); // 确认下注
}

