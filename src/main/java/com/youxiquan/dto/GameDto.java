package com.youxiquan.dto;

import com.youxiquan.pojo.YxqGame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
public class GameDto implements Serializable {
    private Long id;

    private String gameName;

    private String info;

    private String photo;

    private Date createTime;

    private Integer orderNum;

    private Integer number;

    private Integer status;

    private String type;

    private List<String> types;

    public GameDto(){}

    public GameDto(YxqGame game) {
        this.id = game.getId();
        this.gameName = game.getGameName();
        this.info = game.getInfo();
        this.photo = game.getPhoto();
        this.createTime = game.getCreateTime();
        this.orderNum = game.getOrderNum();
        this.number = game.getNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer order) {
        this.orderNum = order;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
