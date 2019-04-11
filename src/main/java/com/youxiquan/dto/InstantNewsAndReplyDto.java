package com.youxiquan.dto;

import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yzx
 * 2018/12/5
 */
public class InstantNewsAndReplyDto implements Serializable {
    private Long id;

    private Long userId;

    private Long gameId;

    private String message;

    private Date createTime;

    private Date endTime;

    private Integer replyNum;

    private Integer status;

    private YxqUser user;

    private List<InstantNewsReply2Dto> instantNewsReply2DtoList;

    public InstantNewsAndReplyDto() {
    }

    public InstantNewsAndReplyDto(Long id, Long userId, Long gameId, String message, Date createTime, Date endTime, Integer replyNum, Integer status, YxqUser user, List<InstantNewsReply2Dto> instantNewsReply2DtoList) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.message = message;
        this.createTime = createTime;
        this.endTime = endTime;
        this.replyNum = replyNum;
        this.status = status;
        this.user = user;
        this.instantNewsReply2DtoList = instantNewsReply2DtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
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

    public List<InstantNewsReply2Dto> getInstantNewsReply2DtoList() {
        return instantNewsReply2DtoList;
    }

    public void setInstantNewsReply2DtoList(List<InstantNewsReply2Dto> instantNewsReply2DtoList) {
        this.instantNewsReply2DtoList = instantNewsReply2DtoList;
    }
}
