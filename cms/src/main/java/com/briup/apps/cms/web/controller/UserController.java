package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserVM;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cms
 * @description: 用户控制器类
 * @create: 2019-11-15 14:41
 **/
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IBaseUserService baseUserService;

    @PostMapping("login")
    public Message login(@RequestBody UserVM userVM){
        // 1. 认证用户的用户名和密码
    	//BaseUser user = baseUserService.Login(userVM);
        // 2. 如果登录成功产生token,将token缓存起来，返回
        // 3. 如果登录失败
        Map<String,String> map = new HashMap<>();
        map.put("token","admin-token");
        return MessageUtil.success(map);
    }

    @ApiOperation(value = "通过token获取用户的基本信息")
    @GetMapping("info")
    public Message info(String token){
        // 1. 通过token获取用户信息  {id,use,gender,roles:[]}
        BaseUserExtend baseUserExtend = baseUserService.findById(1l);
        return MessageUtil.success(baseUserExtend);
    }

    @PostMapping("logout")
    public Message logout(){
        // 1. 登录， token从缓存中移除掉
        return MessageUtil.success("退出成功");
    }


}
