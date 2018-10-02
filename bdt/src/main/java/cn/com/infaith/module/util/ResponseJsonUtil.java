package cn.com.infaith.module.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/4/18.
 */
public class ResponseJsonUtil {

    public static JSONObject getResponseJson(int returnCode, String returnMsg, Object returnObject) {
        JSONObject responseJson = new JSONObject();
        responseJson.put("returnCode", returnCode);
        responseJson.put("returnMsg", returnMsg);
        responseJson.put("returnObject", returnObject);
        //responseJson.toJSONString();
        return responseJson;
    }

    public static JSONObject getResponseJson(int returnCode, String returnMsg, Object returnObject, int page, int rows, long total) {
        JSONObject responseJson = new JSONObject();

        responseJson.put("returnCode", returnCode);
        responseJson.put("returnMsg", returnMsg);
        responseJson.put("returnObject", returnObject);
        responseJson.put("page", page);
        responseJson.put("rows", rows);
        responseJson.put("total", total);
        return responseJson;
    }


    public static JSONObject getResponseJson(int returnCode, String returnMsg, Object returnObject, Boolean isUpdate) {
        JSONObject responseJson = new JSONObject();

        responseJson.put("returnCode", returnCode);
        responseJson.put("returnMsg", returnMsg);
        responseJson.put("returnObject", returnObject);
        responseJson.put("isUpdate", isUpdate);
        return responseJson;
    }

}
