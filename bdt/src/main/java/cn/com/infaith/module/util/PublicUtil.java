package cn.com.infaith.module.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.SimplifiedObjectMeta;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/18.
 */
@Controller
@Configuration
@PropertySource("classpath:application.properties")
public class PublicUtil {
    public static final String str = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String intStr = "0123456789";
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
    private static final String regEx_style_tag = "style=\"(.*?)\"";
    private static final String regEx_style_attr = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    private static final String regEx_img = "<img[\\w\\W]*?src=[\"|\']?([\\w\\W]*?)(.jpg|.png|.bmp)[\\w\\W]*?/>";//定义图片img标签
    //    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";

    @Value("${new.oss.url}")
    private static String ossUrl;

    public static String getRandomStr() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    public static void uploadSingleFile(String key, File file) {
        OSSClient client = new OSSClient("http://oss-cn-shanghai.aliyuncs.com", "LTAIvBk8HHPSsw0c", "LodXWDLUoj8YxGbBEyu2zoxO4WY5Nk");
        client.putObject("an-announcement", key, file);
        client.shutdown();
    }

    public static String uploadFileToOss(String key, File file) {
        OSSClient client = new OSSClient("http://oss-cn-shanghai.aliyuncs.com", "LTAIvBk8HHPSsw0c", "LodXWDLUoj8YxGbBEyu2zoxO4WY5Nk");
        client.putObject("an-announcement", key, file);
        client.shutdown();
        return "http://oss.in-hope.cn/" + key;
    }

    public static void uploadSingleFile(String key, InputStream inputStream, ObjectMetadata metadata) {
        OSSClient client = new OSSClient("http://oss-cn-shanghai.aliyuncs.com", "LTAIvBk8HHPSsw0c", "LodXWDLUoj8YxGbBEyu2zoxO4WY5Nk");
        client.putObject("an-announcement", key, inputStream, metadata);
        client.shutdown();
    }


    public static boolean checkFileExists(String key) {
        OSSClient client = new OSSClient("http://oss-cn-shanghai.aliyuncs.com", "LTAIvBk8HHPSsw0c", "LodXWDLUoj8YxGbBEyu2zoxO4WY5Nk");
        boolean result = client.doesObjectExist("an-announcement", key);
        client.shutdown();
        return result;
    }

    public static Long getFileLength(String key) {
        OSSClient client = new OSSClient("http://oss-cn-shanghai.aliyuncs.com", "LTAIvBk8HHPSsw0c", "LodXWDLUoj8YxGbBEyu2zoxO4WY5Nk");
        boolean result = client.doesObjectExist("an-announcement", key);
        if (result) {
            SimplifiedObjectMeta objectMeta = client.getSimplifiedObjectMeta("an-announcement", key);
            return objectMeta.getSize();
        }
        client.shutdown();
        return 0L;
    }

    public static String getRandomInt() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String inputFilter(String name) {
        name = name.replaceAll("[\ue000-\uefff]", "").replaceAll("[\uD000-\uDfff]", "");
        return name;
    }

    public static String delHTMLTag(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return null;
        }
//        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
//        Matcher m_script = p_script.matcher(htmlStr);
//        htmlStr = m_script.replaceAll(""); // 过滤script标签
//
//        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
//        Matcher m_style = p_style.matcher(htmlStr);
//        htmlStr = m_style.replaceAll(""); // 过滤style标签
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }

    public static String delStyleTag(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return null;
        }
        Pattern p_style = Pattern.compile(regEx_style_tag, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
        return htmlStr;
    }

    public static String delStyleAttr(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return null;
        }
        Pattern p_style = Pattern.compile(regEx_style_attr, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
        return htmlStr.trim();
    }


    public static String trimStyle(String content) {
        String regEx = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content.toLowerCase());
        String result = content;
        if (m.find()) {
            result = m.replaceAll("");
        }
        return result;
    }

    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(String s) {
        String str = s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        return str;
    }

    public static Long convertRondomDate(Date date) {
        long time = date.getTime();
        String stringTime = time + "";
        String str = stringTime.substring(0, stringTime.length() - 5);
        Random random = new Random();
        String sub = "";
        for (int i = 0; i < 5; i++) {
            sub = sub + random.nextInt(9);
        }
        String result = str + sub;
        Long aLong = new Long(result);
        return aLong;
    }


    public static String FormetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static void main(String[] args) {
//        String str_file = uploadFileToOss("data/image/68f4383d191b4691af91f034370a8d02.png", new File("/Users/tangqi/Desktop/管理员头像/信公技术部@3x.png"));
//        System.out.println(str_file);
        String uuid = getUUID();
        System.out.println(uuid);
//        boolean integer = isInteger("33333ss3");
//        System.out.println(integer);
//        File file = new File("/Users/tangqi/Desktop/头像/WechatIMG20.jpeg");
//        String name = file.getName();
//        String suffix = name.substring(name.indexOf("."));
//        String imgUrl = uploadFileToOss("data/avatar/image/" + getUUID() + suffix, file);
//        System.out.println(imgUrl);
    }

    /**
     * 判断是否为乱码
     *
     * @param str
     * @return
     */
    public static boolean isMessyCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
            //从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
            if ((int) c == 0xfffd) {
                // 存在乱码
                return true;
            }
        }
        return false;
    }
    public static boolean isInteger(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
