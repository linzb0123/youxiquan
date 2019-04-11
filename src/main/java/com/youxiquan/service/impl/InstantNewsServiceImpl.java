package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.dto.InstantNewsDto;
import com.youxiquan.dto.InstantNewsReply2Dto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.service.InstantNewsService;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author yzx
 * 2018/11/30
 */
@Service
public class InstantNewsServiceImpl implements InstantNewsService {

    private static final Logger log = LoggerFactory.getLogger(InstantNewsServiceImpl.class);

    @Autowired
    private Mappers mappers;
    @Autowired
    private Services services;

    @Override
    public YxqInstantNews getInstantNewsById(long id) {
        YxqInstantNews yxqInstantNews;
        try {
            yxqInstantNews = mappers.yxqInstantNewsMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得即时信息失败");
        }
        return yxqInstantNews;
    }

    @Override
    public DataTablesResult getInstantNewsList(int draw, int start, int length, String search, String minDate, String maxDate, String orderCol, String orderDir) {
        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<InstantNewsDto> list = mappers.yxqInstantNewsMapper.selectByInstantNewsInfo("%"+search+"%",minDate,maxDate,orderCol,orderDir);
            PageInfo<InstantNewsDto> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getInstantNewsCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);
        }
        catch (Exception e)
        {
            throw new RestfulException("加载即时信息失败");
        }

        return result;
    }

    @Override
    public DataTablesResult getInstantNewsCount() {
        DataTablesResult result=new DataTablesResult();
        YxqInstantNewsExample example=new YxqInstantNewsExample();
        try{
            result.setRecordsTotal((int)mappers.yxqInstantNewsMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计即时信息数失败");
        }
        return result;
    }

    @Override
    public YxqInstantNews updateInstantNews(Long id, YxqInstantNews yxqInstantNews) {
        if (mappers.yxqInstantNewsMapper.updateByPrimaryKey(yxqInstantNews) == 0){
            throw new RestfulException("更新即时帖子信息失败");
        }
        return getInstantNewsById(id);
    }

    @Override
    public YxqInstantNews alertInstantNewsStatus(Long id, Integer status) {
        YxqInstantNews yxqInstantNews = mappers.yxqInstantNewsMapper.selectByPrimaryKey(id);
        yxqInstantNews.setStatus(status);

        if (mappers.yxqInstantNewsMapper.updateByPrimaryKey(yxqInstantNews) == 0){
            throw new RestfulException("修改帖子信息失败");
        }
        return getInstantNewsById(id);
    }

    @Override
    public int removeInstantNews(Long id) {
        if(mappers.yxqInstantNewsMapper.deleteByPrimaryKey(id) == 0){
            throw new RestfulException("删除帖子信息失败");
        }
        return 0;
    }

    @Override
    public InstantNewsAndReplyDto getInstantNewsDetail(Long id) {
        InstantNewsAndReplyDto instantNewsAndReplyDto;
        try{
            instantNewsAndReplyDto = mappers.yxqInstantNewsMapper.selectInstantNewsDetailById(id);
            if(instantNewsAndReplyDto == null){
                YxqInstantNews instantNews = getInstantNewsById(id);
                instantNewsAndReplyDto = new InstantNewsAndReplyDto();
                YxqUser user =  services.userService.getUserById(instantNews.getUserId());
                instantNewsAndReplyDto.setCreateTime(instantNews.getCreateTime());
                instantNewsAndReplyDto.setEndTime(instantNews.getEndTime());
                instantNewsAndReplyDto.setMessage(instantNews.getMessage());
                instantNewsAndReplyDto.setReplyNum(instantNews.getReplyNum());
                instantNewsAndReplyDto.setUser(user);
            }else{
                for(InstantNewsReply2Dto instantNewsReply2Dto : instantNewsAndReplyDto.getInstantNewsReply2DtoList()){
                    YxqUser user = services.userService.getUserById(instantNewsReply2Dto.getUserId());
                    instantNewsReply2Dto.setReplyUserName(user.getUsername());
                    instantNewsReply2Dto.setHead(user.getHeadimgurl());
                }
            }
        }catch(Exception e){
            throw new RestfulException("查找帖子信息失败");
        }
        return instantNewsAndReplyDto;

    }

    @Override
    public List<InstantNewsDto> getListByGameId(Long gameId) {
        List<InstantNewsDto> list = null;
        try{
            list = mappers.yxqInstantNewsMapper.selectListByGameId(gameId);
        }catch (Exception e){
            throw new RestfulException("通过游戏Id来查找游戏帖子失败");
        }
        return list;
    }

    @Override
    public List<InstantNewsDto> getList(Long userId) {
        List<InstantNewsDto> list = new ArrayList<InstantNewsDto>();
        try{
            List<YxqUserConcerns> userConcernsList = services.userConcornsService.getListByUserId(userId);
            for( YxqUserConcerns userConcerns:userConcernsList){
                List<InstantNewsDto> term = mappers.yxqInstantNewsMapper.getListByUserId(userConcerns.getToId());
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
    public List<InstantNewsDto> getMyselfByUserId(Long userId) {
        List<InstantNewsDto> list;
        try{
            list = mappers.yxqInstantNewsMapper.getMyselfByUserId(userId);
        }catch (Exception e){
            throw new RestfulException("通过用户Id来查找游戏帖子失败");
        }
        return list;
    }

    @Override
    public int insertInstantsNews(YxqInstantNews yxqInstantNews) {
        try{
            if( mappers.yxqInstantNewsMapper.insert(yxqInstantNews) ==0 ){
                return 1;
            }
        }catch (Exception e){
            throw new RestfulException("添加即时帖子失败");
        }
        return 0;
    }

    @Override
    public int getCountById(long userId) {
        try{
            Integer result = 0;
            result = mappers.yxqInstantNewsMapper.getCountByUserId(userId);
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
