package com.youxiquan.dao;

import com.youxiquan.pojo.YxqGameCategory;
import com.youxiquan.pojo.YxqGameCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqGameCategoryMapper {
    long countByExample(YxqGameCategoryExample example);

    int deleteByExample(YxqGameCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqGameCategory record);

    int insertSelective(YxqGameCategory record);

    List<YxqGameCategory> selectByExample(YxqGameCategoryExample example);

    YxqGameCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqGameCategory record, @Param("example") YxqGameCategoryExample example);

    int updateByExample(@Param("record") YxqGameCategory record, @Param("example") YxqGameCategoryExample example);

    int updateByPrimaryKeySelective(YxqGameCategory record);

    int updateByPrimaryKey(YxqGameCategory record);

    List<String> selectTypeByGameId(Long gameId);
}