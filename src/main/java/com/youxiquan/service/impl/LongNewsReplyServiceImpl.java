package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.InstantNewsReplyDto;
import com.youxiquan.dto.LongNewsReplyDto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.service.InstantNewsReplyService;
import com.youxiquan.service.LongNewsReplyService;
import com.youxiquan.service.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author yzx
 * 2018/12/2
 */
@Service
public class LongNewsReplyServiceImpl implements LongNewsReplyService {

    private static final Logger log = LoggerFactory.getLogger(LongNewsReplyServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Override
    public YxqLongNewsReply getLongNewsReplyById(long id) {
        YxqLongNewsReply yxqLongNewsReply;
        try {
            yxqLongNewsReply = mappers.yxqLongNewsReplyMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得即时信息回帖失败");
        }
        return yxqLongNewsReply;
    }

    @Override
    public DataTablesResult getLongNewsReplyList(int draw, int start, int length, String search, String minDate, String maxDate, String orderCol, String orderDir) {
        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<LongNewsReplyDto> list = mappers.yxqLongNewsReplyMapper.selectByLongNewsReplyInfo("%"+search+"%",minDate,maxDate,orderCol,orderDir);
            PageInfo<LongNewsReplyDto> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getLongNewsReplyCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);
        }
        catch (Exception e)
        {
            throw new RestfulException("加载即时信息帖子失败");
        }

        return result;
    }

    @Override
    public DataTablesResult getLongNewsReplyCount() {
        DataTablesResult result=new DataTablesResult();
        YxqLongNewsReplyExample example=new YxqLongNewsReplyExample();
        try{
            result.setRecordsTotal((int)mappers.yxqLongNewsReplyMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计即时信息回帖数失败");
        }

        return result;
    }

    @Override
    public YxqLongNewsReply updateLongNewsReply(Long id, YxqLongNewsReply yxqLongNewsReply) {
        if (mappers.yxqLongNewsReplyMapper.updateByPrimaryKey(yxqLongNewsReply) == 0){
            throw new RestfulException("更新即时帖子回帖信息失败");
        }
        return getLongNewsReplyById(id);
    }

    @Override
    public YxqLongNewsReply alertLongNewsReplyStatus(Long id, Integer status) {
        YxqLongNewsReply yxqLongNewsReply = mappers.yxqLongNewsReplyMapper.selectByPrimaryKey(id);
        yxqLongNewsReply.setStatus(status);

        if (mappers.yxqLongNewsReplyMapper.updateByPrimaryKey(yxqLongNewsReply) == 0){
            throw new RestfulException("修改帖子回帖信息失败");
        }
        return getLongNewsReplyById(id);
    }

    @Override
    public int removeLongNewsReply(Long id) {
        if(mappers.yxqLongNewsReplyMapper.deleteByPrimaryKey(id) == 0){
            throw new RestfulException("删除帖子回帖信息失败");
        }
        return 0;
    }

    @Override
    public int insertLongNewsReply(YxqLongNewsReply yxqLongNewsReply) {
        if(mappers.yxqLongNewsReplyMapper.insert(yxqLongNewsReply) == 0){
            throw new RestfulException("插入帖子回帖信息失败");
        }
        return 0;
    }

}
