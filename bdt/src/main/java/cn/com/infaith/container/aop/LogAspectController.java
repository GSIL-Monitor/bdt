package cn.com.infaith.container.aop;

import cn.com.infaith.module.util.IPUtil;
import cn.com.infaith.module.util.LogUtil;
import cn.com.infaith.module.util.ResponseJsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
@Component
@Aspect
public class LogAspectController {
    @Pointcut("execution(* cn.com.infaith.module.*.controller.*.*(..))")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        if (RequestContextHolder.getRequestAttributes() == null) {
            try {
                Object result = joinPoint.proceed();
                System.out.println("xjwnb");
                return result;
            } catch (Throwable e) {
                e.printStackTrace();
                return null;
            }
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPUtil.getIpAddr(request);
        String methodName = this.getMethodName(joinPoint);
        String params = this.getParamsJson(joinPoint);
        String userAgent = request.getHeader("user-agent");
        try {
            LogUtil.info(this.getClass(), "Started request  method " + methodName + " params " + params + " userAgent " + userAgent + "" + " IP " + ip);
            Object result = joinPoint.proceed();
            LogUtil.info(this.getClass(), "Ended request  method " + methodName + " params " + params + " response is " + result + " cost " + (System.currentTimeMillis() - start) + " millis " + " IP " + ip);
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.error(this.getClass(), "Error request  method " + methodName + " params " + params + " cost " + (System.currentTimeMillis() - start) + " millis " + " IP " + ip);
            return ResponseJsonUtil.getResponseJson(500, "系统错误！", null);
        }
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String SHORT_METHOD_NAME_SUFFIX = "(..)";
        if (methodName.endsWith(SHORT_METHOD_NAME_SUFFIX)) {
            methodName = methodName.substring(0, methodName.length() - SHORT_METHOD_NAME_SUFFIX.length());
        }
        return methodName;
    }

    private String getParamsJson(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            //移除敏感内容
            String paramStr;
            if (arg instanceof HttpServletResponse) {
                paramStr = HttpServletResponse.class.getSimpleName();
            } else if (arg instanceof HttpServletRequest) {
                paramStr = HttpServletRequest.class.getSimpleName();
            } else if (arg instanceof MultipartFile) {
                long size = ((MultipartFile) arg).getSize();
                paramStr = MultipartFile.class.getSimpleName() + " size:" + size;
            } else {
                paramStr = this.deleteSensitiveContent(arg);
            }
            sb.append(paramStr).append(",");
        }
        return sb.length() > 1 ? sb.deleteCharAt(sb.length() - 1).toString() : "";
    }

    private String deleteSensitiveContent(Object obj) {
        JSONObject jsonObject = new JSONObject();
        if (obj == null || obj instanceof Exception) {
            return jsonObject.toJSONString();
        }

        try {
            String param = JSON.toJSONString(obj);
            jsonObject = JSONObject.parseObject(param);
            List<String> sensitiveFieldList = this.getSensitiveFieldList();
            for (String sensitiveField : sensitiveFieldList) {
                if (jsonObject.containsKey(sensitiveField)) {
                    jsonObject.put(sensitiveField, "******");
                }
            }
        } catch (ClassCastException e) {
            return String.valueOf(obj);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 敏感字段列表（当然这里你可以更改为可配置的）
     */
    private List<String> getSensitiveFieldList() {
        List<String> sensitiveFieldList = Lists.newArrayList();
        sensitiveFieldList.add("pwd");
        sensitiveFieldList.add("password");
        return sensitiveFieldList;
    }

}
