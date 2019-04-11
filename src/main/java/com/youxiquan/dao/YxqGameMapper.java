package com.youxiquan.dao;

import com.youxiquan.dto.GameDto;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqGameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqGameMapper {
    long countByExample(YxqGameExample example);

    int deleteByExample(YxqGameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqGame record);

    int insertSelective(YxqGame record);

    List<YxqGame> selectByExample(YxqGameExample example);

    YxqGame selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqGame record, @Param("example") YxqGameExample example);

    int updateByExample(@Param("record") YxqGame record, @Param("example") YxqGameExample example);

    int updateByPrimaryKeySelective(YxqGame record);

    int updateByPrimaryKey(YxqGame record);

    List<GameDto> selectGameList(@Param("search") String search, @Param("orderCol") String orderCol, @Param("orderDir") String orderDir,@Param("start") int start,@Param("length") int length);

    long countGameList(@Param("search") String search);

    List<YxqGame> getFocusGame(@Param("userId") Long userId);

    List<YxqGame> getList();


}