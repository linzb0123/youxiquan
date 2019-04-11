package com.youxiquan.controller;

import com.youxiquan.pojo.Admin;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class UserController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Services services;

    @RequestMapping("/user/login")
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value="/user/doLogin",method = RequestMethod.POST)
    public ResultInfo doLogin(String username,String password, HttpServletRequest request){
        //MD5加密
//        String hashAlgorithmName = "MD5";//加密方式
//        String crdentials = password;//密码原值
//        String salt = username;//盐值
//        int hashIterations = 1024;//加密1024次
//        String md5Pass = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations).toString();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject() ;
        try {
            subject.login(token);
            return ResultInfo.getInstance("success",ResultInfo.SUCCESS);
        }catch (Exception e){
            return ResultInfo.getInstance("用户名或密码错误",ResultInfo.FAIL);
        }
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value="/user/info",method = RequestMethod.GET)
    public ResultInfo userInfo(){
        Subject subject = SecurityUtils.getSubject() ;
        String username = (String) subject.getPrincipal();
        Admin admin = services.adminService.getAdminByUsername(username);
        Set<String> roles = services.adminService.getRoles(username);
        admin.setPassword("");
        ResultInfo resultInfo = ResultInfo.success("success",admin);
        resultInfo.addContent("role",roles);
        return resultInfo;
    }

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserList(int draw, int start, int length, String searchKey,
                                            String minDate, String maxDate,
                                            @RequestParam("search[value]") String search,
                                            @RequestParam("order[0][column]") int orderCol,
                                            @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","username","phone","sex","headimgurl","location","info","create_time","status"};
        String orderColumn = cols[orderCol];

        //默认排序列
        if(orderColumn == null) {
            orderColumn = "create_time";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "asc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result=services.userService.getUserList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/user/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserCount()
    {
        return services.userService.getUserCount();
    }

    @RequestMapping(value = "/user/remove/{ids}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqUser> deleteYxqUser(@PathVariable Long[] ids){

        for(Long id:ids){
            services.userService.removeUser(id);
        }
        return new ResultUtil<YxqUser>().setData(null);
    }

    /* 屏蔽 */
    @RequestMapping(value = "/user/stop/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqUser> stopUser(@PathVariable Long id){
        YxqUser yxqUser =  services.userService.alertUserStatus(id,1);
        return new ResultUtil<YxqUser>().setData(yxqUser);
    }

    /* 启用 */
    @RequestMapping(value = "/user/start/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqUser> startUser(@PathVariable Long id){
        YxqUser yxqUser =  services.userService.alertUserStatus(id,0);
        return new ResultUtil<YxqUser>().setData(yxqUser);
    }

    @RequestMapping(value = "/user/getUser/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Result<YxqUser> getGameInfoById(@PathVariable long userId){

        YxqUser yxqUser = services.userService.getUserById(userId);
        return new ResultUtil<YxqUser>().setData(yxqUser);
    }
}
