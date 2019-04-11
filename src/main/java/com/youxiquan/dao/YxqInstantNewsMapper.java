package com.youxiquan.dao;

import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.dto.InstantNewsDto;
import com.youxiquan.pojo.YxqInstantNews;
import com.youxiquan.pojo.YxqInstantNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqInstantNewsMapper {
    long countByExample(YxqInstantNewsExample example);

    int deleteByExample(YxqInstantNewsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqInstantNews record);

    int insertSelective(YxqInstantNews record);

    List<YxqInstantNews> selectByExample(YxqInstantNewsExample example);

    YxqInstantNews selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqInstantNews record, @Param("example") YxqInstantNewsExample example);

    int updateByExample(@Param("record") YxqInstantNews record, @Param("example") YxqInstantNewsExample example);

    int updateByPrimaryKeySelective(YxqInstantNews record);

    int updateByPrimaryKey(YxqInstantNews record);

    List<InstantNewsDto> selectByInstantNewsInfo(@Param("search") String search, @Param("minDate") String minDate,
                                                 @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
                                                 @Param("orderDir") String orderDir);

    InstantNewsAndReplyDto selectInstantNewsDetailById(Long id);

    List<InstantNewsDto> selectListByGameId(Long gameId);

    List<InstantNewsDto> getListByUserId(Long userId);

    List<InstantNewsDto> getMyselfByUserId(Long userId);

    Integer getCountByUserId(Long userId);
}