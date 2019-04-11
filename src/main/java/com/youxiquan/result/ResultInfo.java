package com.youxiquan.result;

import com.alibaba.fastjson.JSONObject;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 返回json数据
 */
public class ResultInfo {


    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final int NOLOGIN=2; //未登录
    public static final int NOEXIST=3; //不存在
    public static final int UNREGISTER=4; //未注册
    public static final int EXPIRE=5; //过期
    public static final int BLACK=6; //已被屏蔽
    /**
     * 操作返回数据
     */
    private Map<String, Object> content = new HashMap<>();

    /**
     * 操作信息
     */
    private String msg;

    /**
     * 操作是否成功等标识
     */
    private int code;

    /**
     * 约定list存放变量标识
     */
    private static final String RESULT_DATA = "data";

    /**
     * 约定count存放变量标识
     */
    private static final String TOTAL_SIZE = "totalSize";

    /**
     * 约定pageCount存放变量标识
     */
    private static final String PAGE_COUNT = "pageCount";

    /**
     * 转成json后为 {"msg":msg,"code":code, "content":{}}
     */
    public static ResultInfo getInstance() {
        // 默认设置操作成功
        ResultInfo resultInfo = getInstance("success", 1);
        return resultInfo;
    }

    /**
     * 转成json后为 {"msg":msg,"code":code, "content":{}}
     */
    public static ResultInfo getInstance(String msg, int code) {
        return new ResultInfo(msg, code);
    }


    /**
     * 转成json后为 {"msg":msg,"code":code, "content":{"data":[T obj]}}
     */
    /*public static <T> ResultInfo getInstance(String msg, int code, T obj) {
        ResultInfo resultInfo = getInstance(msg, code);
        resultInfo.setContent(RESULT_DATA, obj);
        return resultInfo;
    }*/

    /**
     * 转成json后为 {"msg":msg,"code":code, "content":{"data":[T obj]}}
     */
    public static ResultInfo getInstance(String msg, int code, Object obj) {
        ResultInfo resultInfo = getInstance(msg, code);
        resultInfo.setContent(obj.getClass().getSimpleName(), obj);
        return resultInfo;
    }

    public static ResultInfo getInstance(String msg, int code, String key,Object obj) {
        ResultInfo resultInfo = getInstance(msg, code);
        resultInfo.setContent(key, obj);
        return resultInfo;
    }

    /**
     * 转成json后为 {"msg":msg,"code":code, "content":{"totalSize":null,"data":[list]}}
     */
    public static ResultInfo getInstance(String msg, int code, List<?> list) {
        ResultInfo resultInfo = getInstance(msg, code);
        resultInfo.setContent(TOTAL_SIZE, null);
        resultInfo.setContent(RESULT_DATA, list);
        return resultInfo;
    }

    /**
     * 构造有list的返回 转成json后为 {"msg":msg,"code":code, "content":{"totalSize":totalSize,"data":[list]}}
     */
    public static ResultInfo getInstance(String msg, int code, String totalSize, List<?> list) {
        ResultInfo resultInfo = getInstance(msg, code, list);
        resultInfo.setContent(TOTAL_SIZE, totalSize);
        return resultInfo;
    }

    /**
     * 构造有list的返回 转成json后为 {"msg":msg,"code":code, "content":{"pageCount":pageCount,"data":[list]}}
     */
    public static ResultInfo getInstance(String msg, int code, int pageCount, List<?> list) {
        ResultInfo resultInfo = getInstance(msg, code, list);
        resultInfo.setContent(PAGE_COUNT, pageCount);
        return resultInfo;
    }

    /**
     * 构造方法
     */
    private ResultInfo(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public void setResultData(Object obj) {
        setContent(RESULT_DATA, obj);
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 默认 传进来为中文等提示，非国际化常量。
     *
     * @param msg
     * @throws
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    /**
     * map类型变量data中放入数据 转成json后为 "content":{"key":value}
     *
     * @param key
     * @param value
     * @return void
     * @throws
     */
    public void setContent(String key, Object value) {
        if (content != null) {
            content.put(key, value);
        }
    }

    public void setTotalSize(String totalSize) {
        if (null != totalSize && !"".equals(totalSize)) {
            int t = Integer.parseInt(totalSize);
            setContent(TOTAL_SIZE, t);
        } else {
            setContent(TOTAL_SIZE, null);
        }
    }

    public String toString() {
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(this);
        return jsonObject1.toString();
    }

    public static ResultInfo success(String msg) {
        return ResultInfo.getInstance(msg, ResultInfo.SUCCESS);
    }


    /**
     * 成功
     * @param msg
     * @param o
     * @return
     */
    public static ResultInfo success(String msg,Object o) {
        return ResultInfo.getInstance(msg, ResultInfo.SUCCESS,o);
    }

    public static ResultInfo success(String msg,String key,Object o) {
        return ResultInfo.getInstance(msg, ResultInfo.SUCCESS,key,o);
    }

    /**
     * 失败
     * @param msg
     * @return
     */
    public static ResultInfo fail(String msg) {
        return new ResultInfo(msg, ResultInfo.FAIL);
    }

    /**
     * 追加数据
     * @param key
     * @param value
     */

    public ResultInfo addContent(String key,Object value){
        if (content != null) {
            content.put(key, value);
        }
        return this;
    }

}
