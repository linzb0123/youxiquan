package com.youxiquan.dao;

import com.youxiquan.dto.OtherUserInfoDto;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.pojo.YxqUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqUserMapper {
    long countByExample(YxqUserExample example);

    int deleteByExample(YxqUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YxqUser record);

    int insertSelective(YxqUser record);

    List<YxqUser> selectByExample(YxqUserExample example);

    YxqUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YxqUser record, @Param("example") YxqUserExample example);

    int updateByExample(@Param("record") YxqUser record, @Param("example") YxqUserExample example);

    int updateByPrimaryKeySelective(YxqUser record);

    int updateByPrimaryKey(YxqUser record);

    List<YxqUser> selectByUserInfo(@Param("search") String search, @Param("minDate") String minDate,
                                   @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
                                   @Param("orderDir") String orderDir);

    OtherUserInfoDto selectUserInfoByUserId(Integer id);
}