package com.youxiquan.dao;

import com.youxiquan.dto.UserFocusDto;
import com.youxiquan.pojo.YxqUserFocus;
import com.youxiquan.pojo.YxqUserFocusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqUserFocusMapper {
    long countByExample(YxqUserFocusExample example);

    int deleteByExample(YxqUserFocusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqUserFocus record);

    int insertSelective(YxqUserFocus record);

    List<YxqUserFocus> selectByExample(YxqUserFocusExample example);

    YxqUserFocus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqUserFocus record, @Param("example") YxqUserFocusExample example);

    int updateByExample(@Param("record") YxqUserFocus record, @Param("example") YxqUserFocusExample example);

    int updateByPrimaryKeySelective(YxqUserFocus record);

    int updateByPrimaryKey(YxqUserFocus record);

    List<UserFocusDto> selectByUserFocusInfo(@Param("search") String search, @Param("orderCol") String orderCol,
                                             @Param("orderDir") String orderDir);

    List<YxqUserFocus> selectListByUserId(Long userId);

    int deleteByUserIdAndGameId(@Param("userId") Long userId,@Param("gameId") Long gameId);

}