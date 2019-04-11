package com.youxiquan.interceptor;

import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.WebUtils;
import com.youxiquan.util.jedis.JConstants;
import com.youxiquan.util.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzb
 * 微信小程序无法携带cookie
 * 在header里携带token字段来拦截身份校验
 * 2018/12/6
 */
public class WxInterceptor implements HandlerInterceptor {
    @Autowired
    private Services services;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        NoRequiredLogin noRequiredLogin = ((HandlerMethod)handler).getMethodAnnotation(NoRequiredLogin.class);
        //不校验，如登录
        if(noRequiredLogin!=null){
            return true;
        }
        //校验头部的token的token
        String token = request.getHeader("token");
        //token为空则没登录
        if(StringUtils.isEmpty(token)){
            //未登录返回2
            WebUtils.sendJsonMessage(response, ResultInfo.getInstance("未登录",ResultInfo.NOLOGIN));
            return false;
        }
        String userId = services.jedisClient.hget(token,JConstants.USERID);
        if(StringUtils.isEmpty(userId)){
            //token过期 重新登录
            WebUtils.sendJsonMessage(response, ResultInfo.getInstance("token已经过期",ResultInfo.EXPIRE));
            return false;
        }
        YxqUser user = services.userService.getUserById(new Long(userId));
        if(user==null){
            WebUtils.sendJsonMessage(response, ResultInfo.getInstance("用户不存在",ResultInfo.NOEXIST));
            return false;
        }
        if(user.getStatus()==1){
            WebUtils.sendJsonMessage(response, ResultInfo.getInstance("你涉嫌违规，已被拒绝访问！",ResultInfo.NOEXIST));
            return false;
        }
        services.jedisClient.expire(token,JConstants.TOKENEXPIRETIME);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
