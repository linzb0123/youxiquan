package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.UserFocusDto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.UserFocusService;
import com.youxiquan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
@Service
public class UserFocusServiceImpl implements UserFocusService {

    private static final Logger log = LoggerFactory.getLogger(UserFocusServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Override
    public YxqUserFocus getUserFocusById(long id) {

        YxqUserFocus yxqUserFocus;
        try {
            yxqUserFocus = mappers.yxqUserFocusMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得用户信息失败");
        }
        return yxqUserFocus;
    }
    @Override
    public DataTablesResult getUserFocusList(int draw, int start, int length, String search,  String orderCol, String orderDir) {
        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<UserFocusDto> list = mappers.yxqUserFocusMapper.selectByUserFocusInfo("%"+search+"%",orderCol,orderDir);
            PageInfo<UserFocusDto> pageInfo=new PageInfo<>(list);

//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getUserFocusCount().getRecordsTotal());

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
    public DataTablesResult getUserFocusCount() {
        DataTablesResult result=new DataTablesResult();
        YxqUserFocusExample example=new YxqUserFocusExample();
        try{
            result.setRecordsTotal((int)mappers.yxqUserFocusMapper.countByExample(example));
        }catch (Exception e){
            throw new RestfulException("统计用户数失败");
        }

        return result;
    }




    @Override
    public long removeUserFocus(Long id) {
        if(mappers.yxqUserFocusMapper.deleteByPrimaryKey(id) == 0){
            throw new RestfulException("删除用户信息失败");
        }
        return 0;
    }

    @Override
    public List<YxqUserFocus> getListByUserId(long userId) {
        List<YxqUserFocus> list = null;
        try{
            list = mappers.yxqUserFocusMapper.selectListByUserId(userId);
        }catch(Exception e){
            throw new RestfulException("通过用户id查看用户关注的游戏id失败");
        }
        return list;
    }

    @Override
    public int delelteUserFocus(long userId, long gameId) {
        try{
            if( mappers.yxqUserFocusMapper.deleteByUserIdAndGameId(userId,gameId)  == 0 ){
                return 1;
            }
        }catch(Exception e){
            throw new RestfulException("删除用户关注失败");
        }
        return 0;
    }

    @Override
    public int addUserFocus(YxqUserFocus UserFocus) {
        try{
            if( mappers.yxqUserFocusMapper.insert(UserFocus)  == 0 ){
                return 1;
            }
        }catch(Exception e){
            throw new RestfulException("添加用户关注失败");
        }
        return 0;
    }
}
