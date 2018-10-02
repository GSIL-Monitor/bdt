package cn.com.infaith.container.filter;

import cn.com.infaith.container.request.ParameterRequestWrapper;
import cn.com.infaith.module.util.LogUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Configuration
@Component
@ComponentScan
public class UserAuthorizeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);
        String url = requestWrapper.getRequestURI();
        String token = requestWrapper.getParameter("token");
        String id = null;
        StringBuilder keySb = new StringBuilder();
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            String[] valueArray = map.get(key);
            keySb.append(key).append(":").append("  ");
            StringBuilder valueSb = new StringBuilder();
            for (int i = 0; i < valueArray.length; i++) {
                valueSb.append(valueArray[i]).append(",");
            }
            keySb.append(valueSb.length() > 1 ? valueSb.deleteCharAt(valueSb.length() - 1) : "").append("  ");
        }
        LogUtil.info(this.getClass(), "用户正在请求" + url + "参数为：" + keySb.toString());
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }


}
