package com.youxiquan.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.youxiquan.dto.OtherUserInfoDto;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqUser;

/**
 * @author yzx
 * 2018/12/2
 */
public interface UserService {


    /**
     * 根据ID获取该id的用户信息
     */
    YxqUser getUserById(long id);

    /**
     * 分页获得用户列表
     */
    DataTablesResult getUserList(int draw, int start, int length, String search,
                                                        String minDate, String maxDate, String orderCol, String orderDir);

    /**
     * 获得用户总数
     * @return
     */
    DataTablesResult getUserCount();

    /**
     * 更新用户信息
     */
    YxqUser updateUser(Long id, YxqUser yxqUser);


    /**
     * 修改用户的状态
     */
    YxqUser alertUserStatus(Long id, Integer status);


    /**
     * 删除用户
     */
    int removeUser(Long id);

    YxqUser getUserByOpenid(String openId);

    YxqUser registerNewUser(WxMaUserInfo userInfo);

    YxqUser getUserByToken(String token);

    /**
     * 获得用户的爱好信息
     */
    OtherUserInfoDto getUserInfoByUserId(Long userId);

    int updateUserInfo(YxqUser user);

    long getUserId(String token);
}
