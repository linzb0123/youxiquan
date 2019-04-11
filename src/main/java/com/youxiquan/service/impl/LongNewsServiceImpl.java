package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.LongNewsAndReplyDto;
import com.youxiquan.dto.LongNewsDto;
import com.youxiquan.dto.LongNewsReply2Dto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.service.LongNewsService;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yzx
 * 2018/11/30
 */
@Service
public class LongNewsServiceImpl implements LongNewsService {

    private static final Logger log = LoggerFactory.getLogger(LongNewsServiceImpl.class);

    @Autowired
    private Mappers mappers;
    @Autowired
    private Services services;

    @Override
    public YxqLongNews getLongNewsById(long id) {
        YxqLongNews yxqLongNews;
        try {
            yxqLongNews = mappers.yxqLongNewsMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得长期信息失败");
        }
        return yxqLongNews;
    }

    @Override
    public DataTablesResult getLongNewsList(int draw, int start, int length, String search, String minDate, String maxDate, String orderCol, String orderDir) {
        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<LongNewsDto> list = mappers.yxqLongNewsMapper.selectByLongNewsInfo("%"+search+"%",minDate,maxDate,orderCol,orderDir);
            PageInfo<LongNewsDto> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getLongNewsCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RestfulException("加载长期信息失败");
        }

        return result;
    }

    @Override
    public DataTablesResult getLongNewsCount() {
        DataTablesResult result=new DataTablesResult();
        YxqLongNewsExample example=new YxqLongNewsExample();
        try{
            result.setRecordsTotal((int)mappers.yxqLongNewsMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计即时信息数失败");
        }

        return result;
    }

    @Override
    public YxqLongNews updateLongNews(Long id, YxqLongNews yxqLongNews) {
        if (mappers.yxqLongNewsMapper.updateByPrimaryKey(yxqLongNews) == 0){
            throw new RestfulException("更新长期帖子信息失败");
        }
        return getLongNewsById(id);
    }

    @Override
    public YxqLongNews alertLongNewsStatus(Long id, Integer status) {
        YxqLongNews yxqLongNews = mappers.yxqLongNewsMapper.selectByPrimaryKey(id);
        yxqLongNews.setStatus(status);

        if (mappers.yxqLongNewsMapper.updateByPrimaryKey(yxqLongNews) == 0){
            throw new RestfulException("修改帖子信息失败");
        }
        return getLongNewsById(id);
    }

    @Override
    public int removeLongNews(Long id) {
        if(mappers.yxqLongNewsMapper.deleteByPrimaryKey(id) == 0){
            throw new RestfulException("删除帖子信息失败");
        }
        return 0;
    }

    @Override
    public YxqLongNews updatePostStatus(Long id, Integer status) {
        YxqLongNews yxqLongNews = mappers.yxqLongNewsMapper.selectByPrimaryKey(id);
        yxqLongNews.setStatus(status);

        if (mappers.yxqLongNewsMapper.updateByPrimaryKey(yxqLongNews) == 0){
            throw new RestfulException("修改帖子状态失败");
        }
        return getLongNewsById(id);
    }

    @Override
    public YxqLongNews alertLongNewsOrder(Long id, Integer order) {
        YxqLongNews yxqLongNews = mappers.yxqLongNewsMapper.selectByPrimaryKey(id);
        yxqLongNews.setOrderNum(order);

        if (mappers.yxqLongNewsMapper.updateByPrimaryKey(yxqLongNews) == 0){
            throw new RestfulException("修改帖子排序失败");
        }
        return getLongNewsById(id);
    }

    @Override
    public LongNewsAndReplyDto getLongNewsDetail(Long id) {
        LongNewsAndReplyDto longNewsAndReplyDto;
        try{
            longNewsAndReplyDto = mappers.yxqLongNewsMapper.selectLongNewsDetailById(id);
            if(longNewsAndReplyDto == null){
                longNewsAndReplyDto = new LongNewsAndReplyDto();
                YxqLongNews longNews = getLongNewsById(id);
                YxqUser user = services.userService.getUserById(longNews.getUserId());
                longNewsAndReplyDto.setUser(user);
                longNewsAndReplyDto.setCreateTime(longNews.getCreateTime());
                longNewsAndReplyDto.setMessage(longNews.getMessage());
                longNewsAndReplyDto.setOrderNum(longNews.getOrderNum());
                longNewsAndReplyDto.setPraiseNum(longNews.getPraiseNum());
                longNewsAndReplyDto.setReplyNum(longNews.getReplyNum());
                longNewsAndReplyDto.setStatus(longNewsAndReplyDto.getStatus());
            }else{
                for(LongNewsReply2Dto longNewsReply2Dto : longNewsAndReplyDto.getLongNewsReply2Dtos()) {
                    YxqUser user = services.userService.getUserById(longNewsReply2Dto.getUserId());
                    longNewsReply2Dto.setReplyUserName(user.getUsername());
                    longNewsReply2Dto.setHead(user.getHeadimgurl());
                }
            }
        }catch(Exception e){
            throw new RestfulException("查找帖子信息失败");
        }
        return longNewsAndReplyDto;
    }

    @Override
    public List<LongNewsDto> getListByGameId(Long gameId) {
        List<LongNewsDto> list = null;
        try{
            list = mappers.yxqLongNewsMapper.selectListByGameId(gameId);
        }catch (Exception e){
            throw new RestfulException("通过游戏Id来查找游戏帖子失败");
        }
        return list;
    }

    @Override
    public List<LongNewsDto> getList(Long userId) {
        List<LongNewsDto> list = new ArrayList<LongNewsDto>();
        try{
            List<YxqUserConcerns> userConcernsList = services.userConcornsService.getListByUserId(userId);
            for( YxqUserConcerns userConcerns:userConcernsList){
                List<LongNewsDto> term = mappers.yxqLongNewsMapper.getListByUserId(userConcerns.getToId());
                if(!term.isEmpty()){
                    list.addAll(term);
                }
            }
        }catch (Exception e){
            throw new RestfulException("通过用户Id来查找游戏帖子失败");
        }
        return list;
    }

    @Override
    public List<LongNewsDto> getMyselfByUserId(Long userId) {
        List<LongNewsDto> list = null;
        try{
               list = mappers.yxqLongNewsMapper.getListByUserId(userId);
        }catch (Exception e){
            throw new RestfulException("通过用户Id来查找游戏帖子失败");
        }
        return list;
    }

    @Override
    public int insertLongNews(YxqLongNews longNews) {
        try{
            if( mappers.yxqLongNewsMapper.insert(longNews) == 0 ){
                return 1;
            }
        }catch (Exception e){
            throw new RestfulException("添加长期帖子失败");
        }
        return 0;
    }

    @Override
    public int getCountById(Long userId) {
        try{
            Integer result=0;
            result = mappers.yxqLongNewsMapper.getCountByUserId(userId);
            if(result == null){
                return 0;
            }else{
                return result;
            }
        }catch (Exception e){
            throw new RestfulException("获得用户发帖数失败");
        }

    }

}
