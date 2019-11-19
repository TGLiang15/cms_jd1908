package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserRoleVM;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/baseUser")
public class BaseUserController {
	@Autowired
	private IBaseUserService baseUserService;
	
	@ApiOperation("查询所有")
	@GetMapping("findAll")
	public Message findAll() {
		List<BaseUser> list = baseUserService.findAll();
		return MessageUtil.success(list);
	}
	@ApiOperation("级联查询所有")
	@GetMapping("cascadeRoleFindAll")
	public Message cascadeRoleFindAll() {
		List<BaseUserExtend> list = baseUserService.cascadeRoleFindAll();
		return MessageUtil.success(list);
	}
	@ApiOperation("保存或更新")
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(BaseUser user) {
		baseUserService.saveOrUpdate(user);
		return MessageUtil.success("更新成功");
	}
	@ApiOperation("根据id删除")
	@GetMapping("deleteById")
    public Message delete(long id){
		//System.out.println(id);
    	baseUserService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
	@ApiOperation(value = "设置权限")
    @PostMapping(value = "setRoles")
    public Message setRoles(UserRoleVM userRoleVM){
        System.out.println(userRoleVM);
        baseUserService.setRoles(userRoleVM.getId(),userRoleVM.getRoles());
        return MessageUtil.success("设置成功");
    }
	
	
}
