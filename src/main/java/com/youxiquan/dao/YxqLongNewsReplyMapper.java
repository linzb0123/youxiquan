package com.youxiquan.dao;

import com.youxiquan.dto.LongNewsReplyDto;
import com.youxiquan.pojo.YxqLongNewsReply;
import com.youxiquan.pojo.YxqLongNewsReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YxqLongNewsReplyMapper {
    long countByExample(YxqLongNewsReplyExample example);

    int deleteByExample(YxqLongNewsReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YxqLongNewsReply record);

    int insertSelective(YxqLongNewsReply record);

    List<YxqLongNewsReply> selectByExample(YxqLongNewsReplyExample example);

    YxqLongNewsReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YxqLongNewsReply record, @Param("example") YxqLongNewsReplyExample example);

    int updateByExample(@Param("record") YxqLongNewsReply record, @Param("example") YxqLongNewsReplyExample example);

    int updateByPrimaryKeySelective(YxqLongNewsReply record);

    int updateByPrimaryKey(YxqLongNewsReply record);

    List<LongNewsReplyDto> selectByLongNewsReplyInfo(@Param("search") String search, @Param("minDate") String minDate,
                                                        @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
                                                        @Param("orderDir") String orderDir);

}