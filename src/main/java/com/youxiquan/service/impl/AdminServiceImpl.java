package com.youxiquan.service.impl;

import com.youxiquan.dao.AdminMapper;
import com.youxiquan.pojo.Admin;
import com.youxiquan.pojo.AdminExample;
import com.youxiquan.service.AdminService;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.Services;
import com.youxiquan.shiro.MyRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author lzb
 * 2018/11/29
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Override
    public Admin getAdminByUsername(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> list = mappers.adminMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Admin getAdminById(long id) {
        return null;
    }

    @Override
    public Set<String> getRoles(String username) {
        return mappers.adminMapper.getRoles(username);
    }
}
