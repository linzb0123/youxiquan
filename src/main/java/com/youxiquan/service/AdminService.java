package com.youxiquan.service;


import com.youxiquan.pojo.Admin;

import java.util.Set;

/**
 * @author lzb
 */
public interface AdminService {

    /**
     * 通过用户名获取
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    Admin getAdminById(long id);

    /**
     * 通过用户名获取角色
     * @param username
     * @return
     */
    Set<String> getRoles(String username);

}
