package com.youxiquan.dao;

import com.youxiquan.dto.LongNewsAndReplyDto;
import com.youxiquan.dto.LongNewsDto;
import com.youxiquan.pojo.YxqLongNews;
import com.youxiquan.pojo.YxqLongNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqLongNewsMapper {
    long countByExample(YxqLongNewsExample example);

    int deleteByExample(YxqLongNewsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqLongNews record);

    int insertSelective(YxqLongNews record);

    List<YxqLongNews> selectByExample(YxqLongNewsExample example);

    YxqLongNews selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqLongNews record, @Param("example") YxqLongNewsExample example);

    int updateByExample(@Param("record") YxqLongNews record, @Param("example") YxqLongNewsExample example);

    int updateByPrimaryKeySelective(YxqLongNews record);

    int updateByPrimaryKey(YxqLongNews record);

    List<LongNewsDto> selectByLongNewsInfo(@Param("search") String search, @Param("minDate") String minDate,
                                           @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
                                           @Param("orderDir") String orderDir);

    LongNewsAndReplyDto selectLongNewsDetailById(Long id);

    List<LongNewsDto> selectListByGameId(Long gameId);

    List<LongNewsDto> getListByUserId(Long userId);

    Integer getCountByUserId(Long userId);
}