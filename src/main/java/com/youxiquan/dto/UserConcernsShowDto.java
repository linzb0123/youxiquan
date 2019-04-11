package com.youxiquan.dto;

import com.youxiquan.pojo.YxqUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
public class UserConcernsShowDto implements Serializable {
    private Long id;

    private YxqUser fromUser;

    private YxqUser toUser;

    private Date createtime;

    public UserConcernsShowDto() {
    }

    public UserConcernsShowDto(Long id, YxqUser fromUser, YxqUser toUser, Date createtime) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YxqUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(YxqUser fromUser) {
        this.fromUser = fromUser;
    }

    public YxqUser getToUser() {
        return toUser;
    }

    public void setToUser(YxqUser toUser) {
        this.toUser = toUser;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
