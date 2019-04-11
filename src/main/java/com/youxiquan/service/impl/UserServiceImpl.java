package com.youxiquan.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.GameDto;
import com.youxiquan.dto.OtherUserInfoDto;
import com.youxiquan.dto.UserInfoDto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.service.GameService;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.UserService;
import com.youxiquan.util.jedis.JConstants;
import com.youxiquan.util.jedis.JedisClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private Mappers mappers;
    @Autowired
    public JedisClient jedisClient;

    @Override
    public YxqUser getUserById(long id) {
        YxqUser yxqUser;
        try {
            String userJson = jedisClient.get("user"+id);
            if(StringUtils.isEmpty(userJson)){
                yxqUser = mappers.yxqUserMapper.selectByPrimaryKey((int)id);
                jedisClient.set("user"+id, JSON.toJSONString(yxqUser));
                jedisClient.expire("user"+id,JConstants.USEREXPIRETIME);
            }else{
                yxqUser = JSON.parseObject(userJson,YxqUser.class);
            }
        }catch (Exception e){
            throw new RestfulException("ID获得用户信息失败");
        }
        return yxqUser;
    }
    @Override
    public DataTablesResult getUserList(int draw, int start, int length, String search, String minDate, String maxDate, String orderCol, String orderDir) {
        com.youxiquan.pojo.DataTablesResult result=new com.youxiquan.pojo.DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<YxqUser> list = mappers.yxqUserMapper.selectByUserInfo("%"+search+"%",minDate,maxDate,orderCol,orderDir);
            PageInfo<YxqUser> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getUserCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RestfulException("加载用户信息失败");
        }

        return result;
    }

    @Override
    public DataTablesResult getUserCount() {
        DataTablesResult result=new DataTablesResult();
        YxqUserExample example=new YxqUserExample();
        try{
            result.setRecordsTotal((int)mappers.yxqUserMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计用户数失败");
        }

        return result;
    }

    @Override
    public YxqUser updateUser(Long id, YxqUser yxqUser) {
        if (mappers.yxqUserMapper.updateByPrimaryKey(yxqUser) == 0){
            throw new RestfulException("更新用户信息失败");
        }
        return getUserById(id);
    }

    @Override
    public YxqUser alertUserStatus(Long id, Integer status) {
        YxqUser yxqUser = mappers.yxqUserMapper.selectByPrimaryKey(new Long(id).intValue());
        yxqUser.setStatus(status);

        if (mappers.yxqUserMapper.updateByPrimaryKey(yxqUser) == 0){
            throw new RestfulException("修改帖子信息失败");
        }
        //todo
        jedisClient.del("user"+id);
        return getUserById(id);
    }

    @Override
    public int removeUser(Long id) {
        if(mappers.yxqUserMapper.deleteByPrimaryKey(new Long(id).intValue()) == 0){
            throw new RestfulException("删除用户信息失败");
        }
        return 0;
    }

    @Override
    public YxqUser getUserByOpenid(String openId){
        YxqUserExample example=new YxqUserExample();
        YxqUserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openId);
        List<YxqUser> list = mappers.yxqUserMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    @Override
    public YxqUser registerNewUser(WxMaUserInfo userInfo){
        YxqUser u = this.getUserByOpenid(userInfo.getOpenId());
        if(u!=null) return u;
        YxqUser user = new YxqUser();
        user.setNickname(userInfo.getNickName());
        user.setUsername(userInfo.getNickName());
        user.setHeadimgurl(userInfo.getAvatarUrl());
        if("".equals(userInfo.getCity())&&"".equals(userInfo.getProvince())){
            user.setLocation(userInfo.getCountry());
        }else{
            user.setLocation(userInfo.getProvince()+" "+userInfo.getCity());
        }
        user.setOpenid(userInfo.getOpenId());
        user.setSex(Integer.parseInt(userInfo.getGender()) );
        user.setStatus(0);
        user.setCreateTime(new Date());
        mappers.yxqUserMapper.insertSelective(user);
        return user;
    }

    @Override
    public YxqUser getUserByToken(String token){
        long userId = Long.parseLong(jedisClient.hget(token, JConstants.USERID));
        return getUserById(userId);
    }

    @Override
    public OtherUserInfoDto getUserInfoByUserId(Long userId) {
        OtherUserInfoDto otherUserInfoDto = null;
        try{
            otherUserInfoDto = mappers.yxqUserMapper.selectUserInfoByUserId(new Long(userId).intValue());
        }catch (Exception e){
            throw new RestfulException("获得用户信息失败");
        }
        return otherUserInfoDto;
    }

    @Override
    public int updateUserInfo(YxqUser user) {
        int i =0;
        try {
            i = mappers.yxqUserMapper.updateByPrimaryKey(user);
            jedisClient.del("user"+user.getId());
        }catch (Exception e){
            throw new RestfulException("更新失败");
        }
        return i;
    }
    @Override
    public long getUserId(String token){
        long userId = Long.parseLong(jedisClient.hget(token, JConstants.USERID));
        return userId;
    }
    public static void main(String s []){
        YxqUser u = new YxqUser();
        u.setId(10);
        u.setPassword("12300");
        u.setOpenid("1233");
        u.setStatus(1);
        u.setInfo("100");
        UserInfoDto dto = new UserInfoDto();
        BeanUtils.copyProperties(u,dto);
        System.out.println(JSON.toJSONString(u));
        System.out.println(JSON.toJSONString(dto));
    }
}
