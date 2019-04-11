package com.youxiquan.dto;

import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
public class UserConcernsDto implements Serializable {
    private Long id;

    private Long fromId;

    List<YxqUser> users;

    public UserConcernsDto() {
    }

    public UserConcernsDto(Long id, Long fromId, List<YxqUser> users) {
        this.id = id;
        this.fromId = fromId;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public List<YxqUser> getUsers() {
        return users;
    }

    public void setUsers(List<YxqUser> users) {
        this.users = users;
    }
}
