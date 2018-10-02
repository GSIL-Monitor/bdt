package cn.com.infaith.module.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: poe
 * Date: 2018/3/6
 * Time: 下午2:12
 */
public class Base64Util {


    /**
     * @param inputStr
     * @return
     */
    public static String Encrypt(String inputStr) {
        String resultStr = "";
        try {
            resultStr = Base64.getEncoder().encodeToString(inputStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }


    /**
     * @param inputStr
     * @return
     */
    public static String Decode(String inputStr) {
        String resultStr = "";
        try {
            resultStr =new String(Base64.getDecoder().decode(inputStr),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
