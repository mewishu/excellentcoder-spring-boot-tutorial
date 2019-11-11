/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.impl;

import org.excellentcoder.tutorial.common.dal.dataobject.RoleDO;
import org.excellentcoder.tutorial.common.dal.dataobject.RoleDOExample;
import org.excellentcoder.tutorial.common.dal.mapper.auto.RoleDOMapper;
import org.excellentcoder.tutorial.core.manager.RoleManager;
import org.excellentcoder.tutorial.core.manager.convertor.RoleConvertor;
import org.excellentcoder.tutorial.core.model.enums.RoleName;
import org.excellentcoder.tutorial.core.model.poll.RoleBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 角色管理器接口实现类
 * 
 * @author xbyan
 * @version $Id: RoleManagerImpl.java, v 0.1 2019-11-10 3:41 PM xbyan Exp $$
 */
@Component
public class RoleManagerImpl implements RoleManager {
    /** 角色管理Mapper */
    @Autowired
    private RoleDOMapper roleDOMapper;

    /**
     * @see org.excellentcoder.tutorial.core.manager.RoleManager#queryByName(RoleName)
     */
    @Override
    public Optional<RoleBO> queryByName(RoleName name) {
        RoleDOExample roleDOExample = new RoleDOExample();

        roleDOExample.createCriteria().andNameEqualTo(name.name());

        List<RoleDO> roleDOS = roleDOMapper.selectByExample(roleDOExample);
        return Optional.ofNullable(RoleConvertor.convertDOToBO(roleDOS.stream().findFirst().get()));
    }
}