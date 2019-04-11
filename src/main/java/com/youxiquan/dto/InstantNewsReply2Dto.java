package com.youxiquan.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzx
 * 2018/12/5
 */
public class InstantNewsReply2Dto implements Serializable {
    private Long id;

    private Long instantId;

    private String message;

    private Long userId;

    private Date createTime;

    private Integer status;

    private String replyUserName;

    private String head;  //用户头像

    public InstantNewsReply2Dto() {
    }

    public InstantNewsReply2Dto(Long id, Long instantId, String message, Long userId, Date createTime, Integer status, String replyUserName, String head) {
        this.id = id;
        this.instantId = instantId;
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

    public Long getInstantId() {
        return instantId;
    }

    public void setInstantId(Long instantId) {
        this.instantId = instantId;
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
