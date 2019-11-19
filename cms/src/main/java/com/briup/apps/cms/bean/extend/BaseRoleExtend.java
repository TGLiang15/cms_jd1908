package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.BasePrivilege;
import com.briup.apps.cms.bean.BaseRole;

public class BaseRoleExtend extends BaseRole{
	private List<BasePrivilege> privilege;

	public List<BasePrivilege> getPrivileges() {
		return privilege;
	}

	public void setPrivileges(List<BasePrivilege> BasePrivilege) {
		this.privilege = BasePrivilege;
	}
	
}
