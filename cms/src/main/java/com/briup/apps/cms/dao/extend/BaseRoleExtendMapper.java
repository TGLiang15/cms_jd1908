package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;

import java.util.List;

/**
 * @program: cms
 * @description:
 * @create: 2019-11-15 15:13
 **/

public interface BaseRoleExtendMapper {
    List<BaseRole> selectByUserId(long id);

    List<BaseRoleExtend> selectAll();
}
