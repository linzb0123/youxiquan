package com.youxiquan.util.jedis;

/**
 * @author lzb
 * 2018/12/6
 */
public final class JConstants {
    private JConstants(){}
    public  static final String  SESSIONKEY = "sessionKey";
    public  static final String  USERID = "userId";
    public  static final String  USERCONCERNCOUNT = "userConcernCount";
    public  static final int  TOKENEXPIRETIME = 259200; //token过期时间3天
    public  static final int  USEREXPIRETIME = 10800; //userInfo过期时间3小时
}
