package cn.com.infaith.module.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class HttpConnectionUtil {

    private static DefaultHttpClient httpclient = new DefaultHttpClient();
    private static Logger log = Logger.getLogger(HttpConnectionUtil.class);

    /**
     * httppost
     *
     * @throws UnsupportedEncodingException
     */
    public static String httpPost(String url, String params) {
        HttpPost hp = null;
        try {
            StringEntity se = new StringEntity(params, "UTF-8");
            se.setContentType("application/json; charset=UTF-8");
            hp = new HttpPost(url);
            hp.setEntity(se);
            HttpResponse hr = httpclient.execute(hp);
            HttpEntity he = hr.getEntity();
            if (he != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(he.getContent(), "UTF-8"));
                String line = null;
                StringBuffer content = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    content.append(line + "\n");
                }
                if (br != null) {
                    br.close();
                }
                return content.toString();
            }
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LogUtil.error(HttpConnectionUtil.class, e);
            log.info(e.getMessage());
            return null;
        } finally {
            if (hp != null) {
                hp.abort();
                hp.releaseConnection();
            }
        }
    }

    public static String httpPostAndRelease(String url, String params) {
        HttpPost hp = null;
        try {
            StringEntity se = new StringEntity(params, "UTF-8");
            se.setContentType("application/json; charset=UTF-8");
            hp = new HttpPost(url);
            hp.setEntity(se);
            HttpResponse hr = httpclient.execute(hp);
            HttpEntity he = hr.getEntity();
            if (he != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(he.getContent(), "UTF-8"));
                String line = null;
                StringBuffer content = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    content.append(line + "\n");
                }
                if (br != null) {
                    br.close();
                }
                return content.toString();
            }
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LogUtil.error(HttpConnectionUtil.class, e);
            log.info(e.getMessage());
            return null;
        } finally {
            if (hp != null) {
                hp.releaseConnection();
            }
        }
    }

    public static String httpPostSendMap(String url, Map<String, Object> parameterMap) {
        HttpPost hp = null;
        HttpClient client = new HttpClient();
        try {
            PostMethod method = new PostMethod(url);
            for (String key : parameterMap.keySet()) {
                method.addParameter(key, parameterMap.get(key).toString());
            }
            HttpMethodParams param = method.getParams();
            param.setContentCharset("UTF-8");
            client.executeMethod(method);
            // 打印服务器返回的状态
            // 打印返回的信息
            InputStream stream = method.getResponseBodyAsStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuffer buf = new StringBuffer();
            String line;
            while (null != (line = br.readLine())) {
                buf.append(line).append("\n");
            }
            if (br != null) {
                br.close();
            }
            if (stream != null) {
                stream.close();
            }
            // 释放连接
            method.releaseConnection();
            return buf.toString();
        } catch (Exception e) {
            LogUtil.info(HttpConnectionUtil.class, e.getMessage());
            return null;
        } finally {
            if (hp != null) {
                hp.abort();
            }
        }
    }

    public static String httpDelete(String url) {
        HttpPost hp = null;
        HttpClient client = new HttpClient();
        try {
            DeleteMethod method = new DeleteMethod(url);
            HttpMethodParams param = method.getParams();
            param.setContentCharset("UTF-8");
            client.executeMethod(method);
            // 打印服务器返回的状态
            // 打印返回的信息
            InputStream stream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuffer buf = new StringBuffer();
            String line;
            while (null != (line = br.readLine())) {
                buf.append(line).append("\n");
            }
            if (br != null) {
                br.close();
            }
            if (stream != null) {
                stream.close();
            }
            // 释放连接
            method.releaseConnection();
            return buf.toString();
        } catch (Exception e) {
            LogUtil.info(HttpConnectionUtil.class, e.getMessage());
            return null;
        } finally {
            if (hp != null) {
                hp.abort();
            }
        }
    }

    public static String httpGet(String url) {
        HttpURLConnection connection = null;
        String content = "";
        try {
            URL address_url = new URL(url);
            connection = (HttpURLConnection) address_url.openConnection();
            // connection.setRequestMethod("GET");
            // 设置访问超时时间及读取网页流的超市时间,毫秒值
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");

            // after JDK 1.5
//             connection.setConnectTimeout(10000);
//            connection.setReadTimeout(10000);
            // 得到访问页面的返回值
            int response_code = connection.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                // InputStreamReader reader = new InputStreamReader(in,charSet);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content += line;
                }
                if (reader != null) {
                    reader.close();
                }
                if (in != null) {
                    in.close();
                }
                return content;
            }
        } catch (MalformedURLException e) {
            LogUtil.error(HttpConnectionUtil.class, e);
        } catch (IOException e) {
            LogUtil.error(HttpConnectionUtil.class, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "";
    }

}
