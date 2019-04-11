package com.youxiquan.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @Desc Web相关操作类
 */
public class WebUtils {
    /**
     * 获取Session
     * @return
     */
    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * 获取Request
     * @return
     */
    public static HttpServletRequest getRequest(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Response
     * @return
     */
    public static HttpServletResponse getResponse(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    /**
     * 从Session获取当前登录账号
     * @param t
     * @param <T>
     * @return
     */
//    public static <T> T getCurrentAccount(Class<T> t) {
//        Object sessionAttribute = getSession().getAttribute(t.getName());
//        return sessionAttribute==null?null:t.cast(sessionAttribute);
//    }
    /**
     * 设置当前登录账号到Session
     * @param t
     * @return
     */
//    public static <T> void setCurrentAccount(Class<T> t,Object o) {
//        getSession().setAttribute(t.getName(),o);
//    }

    public static void clearSession() {
        HttpSession httpSession = getSession();
        Enumeration<String> sessionAttributeNames = httpSession.getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            httpSession.removeAttribute(sessionAttributeNames.nextElement());
        }
    }

    public static ServletContext getApplication(){
        //return webApplicationContext.getServletContext();
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static void removeSessionAttribute(String key){
        getSession().removeAttribute(key);
    }

    /**
     * 获取当前用户IP
     * @return
     */
    public static String getIpAddrAdvanced() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.indexOf(",") != -1) {
            String[] ipWithMultiProxy = ip.split(",");

            for (int i = 0; i < ipWithMultiProxy.length; ++i) {
                String eachIpSegement = ipWithMultiProxy[i];
                if (!"unknown".equalsIgnoreCase(eachIpSegement)) {
                    ip = eachIpSegement;
                    break;
                }
            }
        }

        return ip;
    }


    /**
     * 将某个对象转换成json格式并发送到客户端
     * @param response
     * @param obj
     * @throws Exception
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        writer.close();
        response.flushBuffer();
    }
}
