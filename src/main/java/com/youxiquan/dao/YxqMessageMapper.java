package com.youxiquan.dao;

import com.youxiquan.pojo.YxqMessage;
import com.youxiquan.pojo.YxqMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqMessageMapper {
    long countByExample(YxqMessageExample example);

    int deleteByExample(YxqMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqMessage record);

    int insertSelective(YxqMessage record);

    List<YxqMessage> selectByExample(YxqMessageExample example);

    YxqMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqMessage record, @Param("example") YxqMessageExample example);

    int updateByExample(@Param("record") YxqMessage record, @Param("example") YxqMessageExample example);

    int updateByPrimaryKeySelective(YxqMessage record);

    int updateByPrimaryKey(YxqMessage record);
}