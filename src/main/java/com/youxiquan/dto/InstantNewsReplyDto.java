package com.youxiquan.dto;

import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzx
 * 2018/11/30
 */
public class InstantNewsReplyDto implements Serializable {
    private Long id;

    private Long instantId;

    private String message;

    private Long userId;

    private Date createTime;

    private Integer status;

    private YxqUser user;

    public InstantNewsReplyDto() {
    }

    public InstantNewsReplyDto(Long id, Long instantId, String message, Long userId, Date createTime, Integer status, YxqUser user) {
        this.id = id;
        this.instantId = instantId;
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

    public YxqUser getUser() {
        return user;
    }

    public void setUser(YxqUser user) {
        this.user = user;
    }
}
