package com.youxiquan.dto;

import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzx
 * 2018/12/2
 */
public class LongNewsReplyDto implements Serializable {
    private Long id;

    private Long longId;

    private String message;

    private Long userId;

    private Date createTime;

    private Integer status;

    private YxqUser user;

    public LongNewsReplyDto() {
    }

    public LongNewsReplyDto(Long id, Long longId, String message, Long userId, Date createTime, Integer status, YxqUser user) {
        this.id = id;
        this.longId = longId;
        this.message = message;
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
        this.user = user;
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

    public YxqUser getUser() {
        return user;
    }

    public void setUser(YxqUser user) {
        this.user = user;
    }
}
