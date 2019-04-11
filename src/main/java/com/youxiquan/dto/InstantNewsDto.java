package com.youxiquan.dto;

import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yzx
 * 2018/11/30
 */
public class InstantNewsDto implements Serializable,Comparable<InstantNewsDto> {
    private Long id;

    private Long userId;

    private Long gameId;

    private String message;

    private Date createTime;

    private Date endTime;

    private Integer replyNum;

    private Integer status;

    private YxqUser user;

    private YxqGame game;

    public InstantNewsDto() {
    }

    public InstantNewsDto(Long id, Long userId, Long gameId, String message, Date createTime, Date endTime, Integer replyNum, Integer status, YxqUser user, YxqGame game) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.message = message;
        this.createTime = createTime;
        this.endTime = endTime;
        this.replyNum = replyNum;
        this.status = status;
        this.user = user;
        this.game = game;
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

    public YxqGame getGame() {
        return game;
    }

    public void setGame(YxqGame game) {
        this.game = game;
    }

    @Override
    public int compareTo(InstantNewsDto o) {
        InstantNewsDto instantNewsDto = o ;
        if(this.getCreateTime().getTime() == instantNewsDto.createTime.getTime()){
            return 0;
        }else if(this.getCreateTime().getTime() > instantNewsDto.createTime.getTime()){
            return 1;
        }else{
            return -1;
        }
    }
}
