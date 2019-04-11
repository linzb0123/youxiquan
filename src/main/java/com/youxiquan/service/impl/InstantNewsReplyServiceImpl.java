package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.InstantNewsReplyDto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.service.InstantNewsReplyService;
import com.youxiquan.service.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author yzx
 * 2018/11/31
 */
@Service
public class InstantNewsReplyServiceImpl implements InstantNewsReplyService {

    private static final Logger log = LoggerFactory.getLogger(InstantNewsReplyServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Override
    public YxqInstantNewsReply getInstantNewsReplyById(long id) {
        YxqInstantNewsReply yxqInstantNewsReply;
        try {
            yxqInstantNewsReply = mappers.yxqInstantNewsReplyMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得即时信息回帖失败");
        }
        return yxqInstantNewsReply;
    }

    @Override
    public DataTablesResult getInstantNewsReplyList(int draw, int start, int length, String search, String minDate, String maxDate, String orderCol, String orderDir) {
        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<InstantNewsReplyDto> list = mappers.yxqInstantNewsReplyMapper.selectByInstantNewsReplyInfo("%"+search+"%",minDate,maxDate,orderCol,orderDir);
            PageInfo<InstantNewsReplyDto> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getInstantNewsReplyCount().getRecordsTotal());

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
    public DataTablesResult getInstantNewsReplyCount() {
        DataTablesResult result=new DataTablesResult();
        YxqInstantNewsReplyExample example=new YxqInstantNewsReplyExample();
        try{
            result.setRecordsTotal((int)mappers.yxqInstantNewsReplyMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计即时信息回帖数失败");
        }

        return result;
    }

    @Override
    public YxqInstantNewsReply updateInstantNewsReply(Long id, YxqInstantNewsReply yxqInstantNewsReply) {
        if (mappers.yxqInstantNewsReplyMapper.updateByPrimaryKey(yxqInstantNewsReply) == 0){
            throw new RestfulException("更新即时帖子回帖信息失败");
        }
        return getInstantNewsReplyById(id);
    }

    @Override
    public YxqInstantNewsReply alertInstantNewsReplyStatus(Long id, Integer status) {
        YxqInstantNewsReply yxqInstantNewsReply = mappers.yxqInstantNewsReplyMapper.selectByPrimaryKey(id);
        yxqInstantNewsReply.setStatus(status);

        if (mappers.yxqInstantNewsReplyMapper.updateByPrimaryKey(yxqInstantNewsReply) == 0){
            throw new RestfulException("修改帖子回帖信息失败");
        }
        return getInstantNewsReplyById(id);
    }

    @Override
    public int removeInstantNewsReply(Long id) {
        if(mappers.yxqInstantNewsReplyMapper.deleteByPrimaryKey(id) == 0){
            throw new RestfulException("删除帖子回帖信息失败");
        }
        return 0;
    }

    @Override
    public int insertInstantNewsReply(YxqInstantNewsReply yxqInstantNewsReply) {
        if(mappers.yxqInstantNewsReplyMapper.insert(yxqInstantNewsReply) == 0){
            throw new RestfulException("插入帖子回帖信息失败");
        }
        return 0;
    }

}
