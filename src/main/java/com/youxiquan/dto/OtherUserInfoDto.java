package com.youxiquan.dto;

import com.youxiquan.pojo.YxqGame;

import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/12/12
 */
public class OtherUserInfoDto {
    private Integer id;

    private String username;

    private String password;

    private String openid;

    private String nickname;

    private String phone;

    private Integer sex;

    private String headimgurl;

    private String location;

    private Integer status;

    private Date createTime;

    private String info;

    private List<YxqGame> games;

    public OtherUserInfoDto() {
    }

    public OtherUserInfoDto(Integer id, String username, String password, String openid, String nickname, String phone, Integer sex, String headimgurl, String location, Integer status, Date createTime, String info, List<YxqGame> games) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.openid = openid;
        this.nickname = nickname;
        this.phone = phone;
        this.sex = sex;
        this.headimgurl = headimgurl;
        this.location = location;
        this.status = status;
        this.createTime = createTime;
        this.info = info;
        this.games = games;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<YxqGame> getGames() {
        return games;
    }

    public void setGames(List<YxqGame> games) {
        this.games = games;
    }
}
