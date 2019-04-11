package com.youxiquan.dto;

import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzx
 * 2018/12/2
 */
public class LongNewsReply2Dto implements Serializable {
    private Long id;

    private Long longId;

    private String message;

    private Long userId;

    private Date createTime;

    private Integer status;

    private String replyUserName;

    private String head;  //用户头像

    public LongNewsReply2Dto() {
    }

    public LongNewsReply2Dto(Long id, Long longId, String message, Long userId, Date createTime, Integer status, String replyUserName, String head) {
        this.id = id;
        this.longId = longId;
        this.message = message;
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
        this.replyUserName = replyUserName;
        this.head = head;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
