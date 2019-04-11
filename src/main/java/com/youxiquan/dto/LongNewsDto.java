package com.youxiquan.dto;

import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzx
 * 2018/11/30
 */
public class LongNewsDto implements Serializable,Comparable<LongNewsDto> {
    private Long id;

    private Long userId;

    private String message;

    private Date createTime;

    private Integer status;

    private Integer orderNum;

    private Integer replyNum;

    private Long gameId;

    private Integer praiseNum;

    private YxqUser user;

    private YxqGame game;

    public LongNewsDto() {
    }

    public LongNewsDto(Long id, Long userId, String message, Date createTime, Integer status, Integer orderNum, Integer replyNum, Long gameId, Integer praiseNum, YxqUser user, YxqGame game) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createTime = createTime;
        this.status = status;
        this.orderNum = orderNum;
        this.replyNum = replyNum;
        this.gameId = gameId;
        this.praiseNum = praiseNum;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
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
    public int compareTo(LongNewsDto o) {
        LongNewsDto longNewsDto = o;
        if(this.getOrderNum() == longNewsDto.orderNum){
            if(this.getCreateTime().getTime() == longNewsDto.createTime.getTime()){
                return 0;
            }else if(this.getCreateTime().getTime() > longNewsDto.createTime.getTime()){
                return 1;
            }else{
                return -1;
            }
        }else if(this.getOrderNum() < longNewsDto.orderNum){
            return 1;
        }else {
            return -1;
        }

    }
}
