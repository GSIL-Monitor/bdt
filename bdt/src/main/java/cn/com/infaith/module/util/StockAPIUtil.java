package cn.com.infaith.module.util;

/**
 * Created by Administrator on 2017/4/25.
 */
public class StockAPIUtil {

    public static final String appid = "7fda1fd71f4511e7a69f0242ac1100d0";
    public static final String secret_key = "HUul0cr3Sxzx";
    public static final String com_url = "http://gw.yundzh.com/";

//    /**
//     * 获得token
//     *
//     * @return token值
//     */
//    public static String getToken() {
//        HashMap<String, Object> map = new HashMap<>();
//        String url = com_url + "token/access?appid=" + appid + "&secret_key=" + secret_key;
////        String str = HttpConnectionUtil.httpGet(url);
//        String str = HttpClientUtil.httpGetRequest(url);
//        try {
//            JSONObject json = JSONObject.parseObject(str);
//            str = json.getString("Data");
//            json = JSONObject.parseObject(str);
//            str = json.getString("RepDataToken");
//            str = str.substring(1, str.length() - 1);
//            json = JSONObject.parseObject(str);
//            str = json.getString("token");   //获得token
//        } catch (Exception e) {
//            LogUtil.error(StockAPIUtil.class, "" + e);
//        }
//        return str;
//    }
//
//    /**
//     * 获取关注信息
//     *
//     * @param companyFullCodes
//     * @return
//     */
////    public static JSONObject getAttention(List<String> companyFullCodes) {
////        StringBuffer companys = new StringBuffer();
////        for (String fullCode :
////                companyFullCodes) {
////            companys.append(fullCode + ",");
////        }
////        String newcompanys = companys.substring(0, companys.length() - 1);
////        String url = com_url + "stkdata?obj=" + newcompanys + "&field=ZhongWenJianCheng,ZuiXinJia,ZhangDie,ZhangFu&token=" + getToken();
//////        String str = HttpConnectionUtil.httpGet(url);
////        String str = HttpClientUtil.httpGetRequest(url);
////        JSONObject json = JSONObject.parseObject(str);
////
////        return json;
////    }
//
//    /**
//     * 搜索股票
//     *
//     * @param searchStr
//     * @return
//     */
//    public static JSONObject searchStock(String searchStr) {
//        String url = com_url + "kbspirit?count=500&type=0&input=" + searchStr + "&token=" + getToken();
////        String str = HttpConnectionUtil.httpGet(url);
//        String str = HttpClientUtil.httpGetRequest(url);
//        JSONObject json = JSONObject.parseObject(str);
//        return json;
//    }
//
//    /**
//     * 获取K线
//     *
//     * @param period
//     * @param obj
//     * @param beginTime
//     * @param endTime
//     * @return
//     */
//    public static JSONObject getKline(String period, String obj, String beginTime, String endTime) {
//        String url = com_url + "quote/kline?obj=" + obj + "&" + "period=" + period + "&begin_time=" + beginTime + "&end_time=" + endTime + "&token=" + getToken();
////        String str = HttpConnectionUtil.httpGet(url);
//        String str = HttpClientUtil.httpGetRequest(url);
//        JSONObject json = JSONObject.parseObject(str);
//        return json;
//    }
//
//    /**
//     * 获取公司信息
//     *
//     * @param obj
//     * @return
//     */
//    public static JSONObject getStockInfo(String obj) {
//        String url = com_url + "stkdata?obj=" + obj +
//                "&field=ZhongWenJianCheng,ZuiXinJia,ZhangDie,ZhangFu,KaiPanJia,ZuoShou,ZuiGaoJia,ZuiDiJia," +
//                "ChengJiaoLiang,HuanShou,ChengJiaoE,ShiFouTingPai,ShiJian&token=" + getToken();
////        String str = HttpConnectionUtil.httpGet(url);
//        String str = HttpClientUtil.httpGetRequest(url);
//        JSONObject json = JSONObject.parseObject(str);
//        return json;
//    }
//
//    public static void main(String[] args) {
//        String url = "http://gw.yundzh.com/token/access?appid=7fda1fd71f4511e7a69f0242ac1100d0&secret_key=HUul0cr3Sxzx";
////        String str = HttpConnectionUtil.httpGet(url);
//        String str = HttpClientUtil.httpGetRequest(url);
//        System.out.println(str);
//    }


}
