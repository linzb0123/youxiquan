package com.youxiquan.service;

import com.youxiquan.dto.UserConcernsShowDto;
import com.youxiquan.dto.UserFewInfoDto;
import com.youxiquan.dto.UserInfoDto;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.pojo.YxqUserConcerns;

import java.util.List;

/**
 * @author yzx
 * 2018/12/20
 */
public interface UserConcornsService {


    /**
     * 小程序通过用户的ID查看用户关注了哪些用户
     */
    List<YxqUserConcerns> getListByUserId(long userId);

    /**
     * 通过用户Id获得该用户关注的其他人信息
     */
    List<YxqUser> getInfoByUserId(long userId);

    /**
     * 用户取消关注
     */
    int deleteConcernByFromIdAndToId(long fromId,long toId);

    /**
     * 用户关注
     */
    int insertConcern(long fromId,long toId);

    /**
     * 获得用户的关注数目
     */
    int getCountByToId(long toId);

    /**
     * 根据ID获得用户关注
     */
    YxqUserConcerns getUserConcernById(long id);

    /**
     * 分页获得用户关注列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getUserConcernsList(int draw, int start, int length, String search,
                                         String orderCol, String orderDir);


    /**
     * 获得关注的总上诉
     * @return
     */
    DataTablesResult getUserConcernsCount();

    long getConcernsCount(long userId);

    List<UserInfoDto> getBeConcernUserBy(long userId);
}
