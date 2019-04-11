package com.youxiquan.dao;

import com.youxiquan.dto.UserConcernsShowDto;
import com.youxiquan.dto.UserFewInfoDto;
import com.youxiquan.dto.UserInfoDto;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.pojo.YxqUserConcerns;
import com.youxiquan.pojo.YxqUserConcernsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqUserConcernsMapper {
    long countByExample(YxqUserConcernsExample example);

    int deleteByExample(YxqUserConcernsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqUserConcerns record);

    int insertSelective(YxqUserConcerns record);

    List<YxqUserConcerns> selectByExample(YxqUserConcernsExample example);

    YxqUserConcerns selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqUserConcerns record, @Param("example") YxqUserConcernsExample example);

    int updateByExample(@Param("record") YxqUserConcerns record, @Param("example") YxqUserConcernsExample example);

    int updateByPrimaryKeySelective(YxqUserConcerns record);

    int updateByPrimaryKey(YxqUserConcerns record);

    List<YxqUserConcerns> selectByFromId(Long fromId);

    List<YxqUser> getUserConcernsInfoByUserId(Long fromId);

    int deleteConcernsByFromIdAndToId(@Param("fromId")Long fromId,@Param("toId")Long toId);

    /* 获得别人的关注数目  */
    Integer getCountByToId(Long toId);

    List<UserConcernsShowDto> selectByUserConcernsInfo(@Param("searchId") String searchId,@Param("search") String search, @Param("orderCol") String orderCol,
                                                      @Param("orderDir") String orderDir);

    List<UserInfoDto> getBeConcernUserBy(@Param("userId")Long userId);
}