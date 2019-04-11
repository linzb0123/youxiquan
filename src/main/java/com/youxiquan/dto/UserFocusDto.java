package com.youxiquan.dto;

import com.youxiquan.pojo.YxqGame;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
public class UserFocusDto implements Serializable {
    private Long id;

    private Long userId;

    private String username;

    private String gameName;

    public UserFocusDto() {
    }

    public UserFocusDto(Long id, Long userId, String username, String gameName, Long gameId) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.gameName = gameName;
        this.gameId = gameId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    private Long gameId;
}
