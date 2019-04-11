package com.youxiquan.dao;

import com.youxiquan.dto.InstantNewsReplyDto;
import com.youxiquan.pojo.YxqInstantNewsReply;
import com.youxiquan.pojo.YxqInstantNewsReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqInstantNewsReplyMapper {
    long countByExample(YxqInstantNewsReplyExample example);

    int deleteByExample(YxqInstantNewsReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqInstantNewsReply record);

    int insertSelective(YxqInstantNewsReply record);

    List<YxqInstantNewsReply> selectByExample(YxqInstantNewsReplyExample example);

    YxqInstantNewsReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqInstantNewsReply record, @Param("example") YxqInstantNewsReplyExample example);

    int updateByExample(@Param("record") YxqInstantNewsReply record, @Param("example") YxqInstantNewsReplyExample example);

    int updateByPrimaryKeySelective(YxqInstantNewsReply record);

    int updateByPrimaryKey(YxqInstantNewsReply record);

    List<InstantNewsReplyDto> selectByInstantNewsReplyInfo(@Param("search") String search, @Param("minDate") String minDate,
                                                           @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
                                                           @Param("orderDir") String orderDir);
}