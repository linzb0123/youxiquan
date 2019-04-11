package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.UserConcernsDto;
import com.youxiquan.dto.UserConcernsShowDto;
import com.youxiquan.dto.UserFewInfoDto;
import com.youxiquan.dto.UserInfoDto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.pojo.YxqUserConcerns;
import com.youxiquan.pojo.YxqUserConcernsExample;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.UserConcornsService;
import com.youxiquan.util.jedis.JConstants;
import com.youxiquan.util.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yzx
 * 2018/12/20
 */
@Service
public class UserConcersServiceImpl implements UserConcornsService {

    private static final Logger log = LoggerFactory.getLogger(UserConcersServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Autowired
    public JedisClient jedisClient;

    @Override
    public List<YxqUserConcerns> getListByUserId(long userId) {
        List<YxqUserConcerns> list = null;
        try{
            list = mappers.yxqUserConcernsMapper.selectByFromId(userId);
        }catch (Exception e){
            throw new RestfulException("通过用户id查看用户关注的用户失败");
        }
        return list;
    }

    @Override
    public List<YxqUser> getInfoByUserId(long userId) {
        List<YxqUser> list = null;
        try{
            list = mappers.yxqUserConcernsMapper.getUserConcernsInfoByUserId(userId);
        }catch (Exception e){
            throw new RestfulException("通过用户id查看用户关注的用户信息失败");
        }
        return list;

    }

    @Override
    public int deleteConcernByFromIdAndToId(long fromId, long toId) {
        try{
            if( mappers.yxqUserConcernsMapper.deleteConcernsByFromIdAndToId(fromId,toId) == 0 ){
                jedisClient.del(JConstants.USERCONCERNCOUNT+fromId);
                jedisClient.del(JConstants.USERCONCERNCOUNT+toId);
                return 1;
            }
        }catch (Exception e){
            throw new RestfulException("取消关注失败");
        }
        return 0;
    }

    @Override
    public int insertConcern(long fromId, long toId) {
        YxqUserConcerns userConcerns = new YxqUserConcerns();
        userConcerns.setCreatetime(new Date());
        userConcerns.setToId(toId);
        userConcerns.setFromId(fromId);
        try{
            if( mappers.yxqUserConcernsMapper.insert(userConcerns) == 0 ){
                jedisClient.del(JConstants.USERCONCERNCOUNT+fromId);
                jedisClient.del(JConstants.USERCONCERNCOUNT+toId);
                return 1;
            }
        }catch (Exception e){
            throw new RestfulException("关注失败");
        }
        return 0;
    }

    @Override
    public int getCountByToId(long toId) {
        try{
           Integer result = 0;
           result = mappers.yxqUserConcernsMapper.getCountByToId(toId);
           if(result==null){
               return 0;
           }else{
               return result;
           }
        }catch (Exception e){
            throw new RestfulException("获得关注数失败");
        }
    }

    @Override
    public YxqUserConcerns getUserConcernById(long id) {
        YxqUserConcerns userConcerns;
        try {
            userConcerns = mappers.yxqUserConcernsMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得关注信息");
        }
        return userConcerns;
    }

    @Override
    public DataTablesResult getUserConcernsList(int draw, int start, int length, String search, String orderCol, String orderDir) {
        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<UserConcernsShowDto> list = mappers.yxqUserConcernsMapper.selectByUserConcernsInfo(search,"%"+search+"%",orderCol,orderDir);
            PageInfo<UserConcernsShowDto> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getUserConcernsCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);
        }
        catch (Exception e)
        {
            throw new RestfulException("加载关注信息失败");
        }

        return result;
    }

    @Override
    public DataTablesResult getUserConcernsCount() {
        DataTablesResult result=new DataTablesResult();
        YxqUserConcernsExample example=new YxqUserConcernsExample();
        try{
            result.setRecordsTotal((int)mappers.yxqUserConcernsMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计用户关注数失败");
        }
        return result;
    }

    @Override
    public long getConcernsCount(long userId) {
        String _count = jedisClient.get(JConstants.USERCONCERNCOUNT+userId);
        if(StringUtils.isEmpty(_count)){
            YxqUserConcernsExample example=new YxqUserConcernsExample();
            YxqUserConcernsExample.Criteria criteria =example.createCriteria();
            criteria.andToIdEqualTo(userId);
            long count=0;
            try{
                count = mappers.yxqUserConcernsMapper.countByExample(example);
                jedisClient.set(JConstants.USERCONCERNCOUNT+userId,count+"");
            }catch (Exception e){
                throw  new RestfulException("统计用户关注数失败");
            }
            return count;
        }else{
            return new Long(_count);
        }

    }


    @Override
    public List<UserInfoDto> getBeConcernUserBy(long userId) {
        List<UserInfoDto> list = null;
        try{
            list = mappers.yxqUserConcernsMapper.getBeConcernUserBy(userId);
        }catch (Exception e){
            throw new RestfulException("通过用户id查看关注人列表");
        }
        return list;

    }

}
