package com.youxiquan.wxcontroller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.dto.UserFewInfoDto;
import com.youxiquan.dto.UserInfoDto;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.service.WxService;
import com.youxiquan.util.WebUtils;
import com.youxiquan.util.jedis.JConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author lzb
 * 2018/12/6
 */
@Api(description= "用户管理")
@RequestMapping("/api/wxUser")
@RestController
public class WxUserController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    private WxService wxService;
    @Autowired
    private Services services;

    @ApiOperation(value="用户登录", notes="用户登录获取token值")
    @NoRequiredLogin
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultInfo login(@RequestParam("code") String code){
        WxMaService  wxMaService = wxService.getWxService();
        try {
            //获取SessionKey 和 Openid
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            System.out.println(session.getSessionKey());
            System.out.println(session.getOpenid());
            YxqUser user = services.userService.getUserByOpenid(session.getOpenid());
            if(user==null){
                //保存sessionKey 用于注册
                services.jedisClient.set(code,session.getSessionKey());
                services.jedisClient.expire(code,300);//5分钟
                return ResultInfo.getInstance("未注册",ResultInfo.UNREGISTER);
            }
            String token  = generateToken();
            String oldToken = services.jedisClient.get(session.getOpenid());
            if(!StringUtils.isEmpty(oldToken)){
                services.jedisClient.del(oldToken);
            }
            services.jedisClient.hset(token,JConstants.USERID,user.getId().toString());
            services.jedisClient.hset(token,JConstants.SESSIONKEY,session.getSessionKey());
            services.jedisClient.expire(token,JConstants.TOKENEXPIRETIME);
            //openId--->token 用于删除重复记录
            services.jedisClient.set(session.getOpenid(),token);
            services.jedisClient.expire(session.getOpenid(),JConstants.TOKENEXPIRETIME);
            log.info("{}登录成功",user.getUsername());
            UserInfoDto userInfoDto = new UserInfoDto();
            BeanUtils.copyProperties(user,userInfoDto);
            ResultInfo res = ResultInfo.success("登录成功","token",token);
            res.addContent("userInfo",userInfoDto);
            return res;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return ResultInfo.fail("登录失败");
    }
    @ApiOperation(value="用户注册", notes="获取用户基本信息进行注册")
    @NoRequiredLogin
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultInfo  register(@RequestParam("code") String code,@RequestParam("signature")String signature,
                                @RequestParam("rawData")String rawData,@RequestParam("encryptedData")String encryptedData,
                                @RequestParam("iv")String iv){
        WxMaService wxMaService = wxService.getWxService();
        String sessionKey = services.jedisClient.get(code);
        if(StringUtils.isEmpty(sessionKey)){
            try {
                WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
                sessionKey=session.getSessionKey();
            } catch (WxErrorException e) {
                return ResultInfo.fail("注册失败");
            }
        }else{
            services.jedisClient.del(code);
        }
        //用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return ResultInfo.fail("user check failed");
        }
        //解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        YxqUser user = services.userService.registerNewUser(userInfo);
        String token  = generateToken();
        services.jedisClient.hset(token,JConstants.USERID,user.getId().toString());
        services.jedisClient.hset(token,JConstants.SESSIONKEY,sessionKey);
        services.jedisClient.expire(token,JConstants.TOKENEXPIRETIME);
        services.jedisClient.set(user.getOpenid(),token);
        services.jedisClient.expire(user.getOpenid(),JConstants.TOKENEXPIRETIME);
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(user,userInfoDto);
        log.info("{}注册成功",user.getUsername());
        ResultInfo res = ResultInfo.success("注册成功","token",token);
        res.addContent("userInfo",userInfoDto);
        return res;
    }

    @RequestMapping(value = "/checkLogin",method = RequestMethod.GET)
    @ApiOperation(value="判断登录是否过期了")
    @NoRequiredLogin
    public ResultInfo checkLogin(@RequestHeader("token") String token){
        String userId = services.jedisClient.hget(token,JConstants.USERID);
        if(StringUtils.isEmpty(userId)){
            //token过期 重新登录
            return ResultInfo.fail("token无效");
        }
        YxqUser user = services.userService.getUserByToken(token);
        if(user==null){
            return ResultInfo.fail("token无效");
        }
        if(user.getStatus()==1){
           return ResultInfo.getInstance("你涉嫌违规，已被拒绝访问！",ResultInfo.NOEXIST);
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(user,userInfoDto);
        return ResultInfo.success("token 有效","userInfo",userInfoDto);
    }

    @ApiOperation(value="获取用户基本信息", notes="获取用户基本信息")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public ResultInfo getUserInfo(@RequestHeader("token") String token){
        YxqUser user = services.userService.getUserByToken(token);
        if(user!=null){
            UserInfoDto userInfoDto = new UserInfoDto();
            BeanUtils.copyProperties(user,userInfoDto);
            return ResultInfo.success("获取成功","userInfo",userInfoDto);
        }
        return ResultInfo.fail("获取失败");
    }

    @ApiOperation(value="获取用户基本信息", notes="获取用户基本信息")
    @RequestMapping(value = "/getUserInfoById",method = RequestMethod.GET)
    public ResultInfo getUserInfoByUserId(@RequestParam("userId") Long userId){
        YxqUser user = services.userService.getUserById(userId);
        if(user!=null){
            UserFewInfoDto userFewInfoDto = new UserFewInfoDto();
            BeanUtils.copyProperties(user,userFewInfoDto);
            return ResultInfo.success("获取成功","userFewInfo",userFewInfoDto);
        }
        return ResultInfo.fail("获取失败");
    }

    @ApiOperation(value="批量获取获取用户基本信息")
    @RequestMapping(value = "/getUserInfoByIdList",method = RequestMethod.GET)
    public ResultInfo getUserInfoByUserIdList(@RequestParam("ids") String userId){
        String ids[] = userId.split("_");
        YxqUser user;
        List<UserFewInfoDto> list = new ArrayList<>();
        for (String id : ids) {
            UserFewInfoDto userFewInfoDto = new UserFewInfoDto();
            user = services.userService.getUserById(new Long(id));
            BeanUtils.copyProperties(user, userFewInfoDto);
            list.add(userFewInfoDto);
        }
        return ResultInfo.success("success","info",list);
    }

    @ApiOperation(value="更新个性签名")
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public ResultInfo updateInfo(@RequestHeader("token") String token,@RequestParam("info") String info){
        YxqUser user = services.userService.getUserByToken(token);
        user.setInfo(info);
        if(services.userService.updateUserInfo(user)==0)return ResultInfo.fail("fail");
        return ResultInfo.success("success");
    }

    public static void  main(String args[]){

    }
    public static String generateToken (){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
