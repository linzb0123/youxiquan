package com.youxiquan.util;

/**
 * @author lzb
 * 2018/12/8
 */
public class WxUserUtils {

    public static String getToken() {
       return  WebUtils.getRequest().getHeader("token");
    }

}
