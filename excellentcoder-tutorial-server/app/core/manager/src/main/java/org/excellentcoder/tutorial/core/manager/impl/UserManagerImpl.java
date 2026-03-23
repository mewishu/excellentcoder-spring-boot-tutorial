/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.gtg.tutorial.core.manager.impl;

import org.gtg.tutorial.common.dal.dataobject.RoleDO;
import org.gtg.tutorial.common.dal.dataobject.UserDO;
import org.gtg.tutorial.common.dal.dataobject.UserDOExample;
import org.gtg.tutorial.common.dal.dataobject.UserRolesLinkDO;
import org.gtg.tutorial.common.dal.mapper.auto.UserDOMapper;
import org.gtg.tutorial.common.dal.mapper.manual.ManualUserDOMapper;
import org.gtg.tutorial.core.manager.UserManager;
import org.gtg.tutorial.core.manager.convertor.UserConvertor;
import org.gtg.tutorial.core.model.poll.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 用户管理接口实现类
 * 
 * @author xbyan
 * @version $Id: UserManagerImpl.java, v 0.1 2019-11-07 4:21 PM xbyan Exp $$
 */
@Component
public class UserManagerImpl implements UserManager {
    /** 用户管理Mapper */
    @Autowired
    private UserDOMapper       userDOMapper;

    /** 手工用户管理Mapper */
    @Autowired
    private ManualUserDOMapper manualUserDOMapper;

    /**
     * @see org.gtg.tutorial.core.manager.UserManager#queryById(Long)
     */
    @Override
    public UserBO insert(UserBO userBO) {
        // 1. 插入用户信息
        UserDO userDO = UserConvertor.convertBOToDO(userBO);
        int id = userDOMapper.insert(userDO);
        userBO.setId(Long.valueOf(id));

        // 2. 插入关联关系
        for (RoleDO roleDO : userDO.getRoles()) {
            UserRolesLinkDO userRolesLinkDO = new UserRolesLinkDO();

            userRolesLinkDO.setUserDO(userDO);
            userRolesLinkDO.setRoleDO(roleDO);

            manualUserDOMapper.insertUserRoleLink(userRolesLinkDO);
        }

        return userBO;
    }

    /**
     * @see org.gtg.tutorial.core.manager.UserManager#queryById(Long)
     */
    @Override
    public Optional<UserBO> queryById(Long id) {
        UserDO userDO = manualUserDOMapper.selectByPrimaryKey(id);
        return Optional.ofNullable(UserConvertor.convertDOToBO(userDO));
    }

    /**
     * @see org.gtg.tutorial.core.manager.UserManager#queryByUsername(String)
     */
    @Override
    public Optional<UserBO> queryByUsername(String username) {
        UserDOExample userDOExample = new UserDOExample();

        userDOExample.createCriteria().andUsernameEqualTo(username);

        List<UserDO> userDOS = manualUserDOMapper.selectByExample(userDOExample);
        UserDO userDO = userDOS.stream().findFirst().get();
        return Optional.ofNullable(UserConvertor.convertDOToBO(userDO));
    }

    /**
     * @see org.gtg.tutorial.core.manager.UserManager#queryByUsernameOrEmail(java.lang.String, java.lang.String)
     */
    @Override
    public Optional<UserBO> queryByUsernameOrEmail(String username, String email) {
        UserDOExample userDOExample = new UserDOExample();

        userDOExample.or().andUsernameEqualTo(username);
        userDOExample.or().andEmailEqualTo(email);

        List<UserDO> userDOS = manualUserDOMapper.selectByExample(userDOExample);
        UserDO userDO = userDOS.stream().findFirst().get();

        return Optional.ofNullable((UserConvertor.convertDOToBO(userDO)));
    }

    /**
     * @see org.gtg.tutorial.core.manager.UserManager#existsByUsername(java.lang.String)
     */
    @Override
    public Boolean existsByUsername(String username) {
        UserDOExample userDOExample = new UserDOExample();

        userDOExample.createCriteria().andUsernameEqualTo(username);

        List<UserDO> userDOS = manualUserDOMapper.selectByExample(userDOExample);
        return !CollectionUtils.isEmpty(userDOS);
    }

    @Override
    public Boolean existsByEmail(String email) {
        UserDOExample userDOExample = new UserDOExample();

        userDOExample.createCriteria().andEmailEqualTo(email);

        List<UserDO> userDOS = manualUserDOMapper.selectByExample(userDOExample);
        return !CollectionUtils.isEmpty(userDOS);
    }
}