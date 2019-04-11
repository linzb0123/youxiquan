package com.youxiquan.dao;

import com.youxiquan.pojo.YxqGameType;
import com.youxiquan.pojo.YxqGameTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqGameTypeMapper {
    long countByExample(YxqGameTypeExample example);

    int deleteByExample(YxqGameTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqGameType record);

    int insertSelective(YxqGameType record);

    List<YxqGameType> selectByExample(YxqGameTypeExample example);

    YxqGameType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqGameType record, @Param("example") YxqGameTypeExample example);

    int updateByExample(@Param("record") YxqGameType record, @Param("example") YxqGameTypeExample example);

    int updateByPrimaryKeySelective(YxqGameType record);

    int updateByPrimaryKey(YxqGameType record);

    List<YxqGameType> selectGameTypeList(@Param("search") String search, @Param("orderCol") String orderCol, @Param("orderDir") String orderDir);
}