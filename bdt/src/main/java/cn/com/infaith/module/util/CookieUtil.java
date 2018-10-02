package cn.com.infaith.module.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Configuration
@PropertySource("classpath:application.properties")
public class CookieUtil {
    public static final int userIdExpires = 60 * 60 * 8760;
    public static final int tokenExpires = 60 * 10;
    public static final int refreshTokenExpires = 60 * 60 * 8760;
    @Value("${cookie.domain}")
    private String cookieDomain;

    public static String getCookieValue(String key, HttpServletRequest request) {
        Cookie[] cooks = request.getCookies();
        if (cooks != null) {
            for (Cookie coo : cooks) {
                if (coo.getName() != null && coo.getName().equals(key)) {
                    return coo.getValue();
                }
            }
        }
        return null;
    }


    public void clearCookie(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        try {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = new Cookie(cookies[i].getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setDomain(cookieDomain);
                response.addCookie(cookie);
                Cookie oldCookie = new Cookie(cookies[i].getName(), null);
                oldCookie.setMaxAge(0);
                oldCookie.setPath("/");
                response.addCookie(oldCookie);
            }
        } catch (Exception ex) {
            LogUtil.error(CookieUtil.class, "清空Cookies发生异常！" + ex);
        }
    }

    public static void clearOldCookie(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        try {
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie oldCookie = new Cookie(cookies[i].getName(), null);
                    oldCookie.setMaxAge(0);
                    oldCookie.setPath("/");
                    response.addCookie(oldCookie);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.error(CookieUtil.class, "清空Cookies发生异常！" + ex);
        }
    }

    public  void clearCookieByName(String name, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setDomain(cookieDomain);
        cookie.setPath("/");
        response.addCookie(cookie);
        Cookie oldCookie = new Cookie(name, null);
        oldCookie.setMaxAge(0);
        oldCookie.setPath("/");
        response.addCookie(oldCookie);
    }

    public  void saveCookie(String key, String value, int expires, HttpServletResponse response) {
        System.out.println(cookieDomain);
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expires);
        cookie.setDomain(cookieDomain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
