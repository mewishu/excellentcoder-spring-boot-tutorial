/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.convertor;

import org.excellentcoder.tutorial.common.dal.dataobject.UserDO;
import org.excellentcoder.tutorial.core.model.poll.UserBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户信息领域模型转换器
 * 
 * @author xbyan
 * @version $Id: UserConvertor.java, v 0.1 2019-11-07 4:23 PM xbyan Exp $$
 */
public class UserConvertor {
    /**
     * 领域模型转DO
     *
     * @param userBOS 领域模型
     * @return      DO
     */
    public static List<UserDO> convertBOToDO(List<UserBO> userBOS) {
        if (userBOS == null) {
            return null;
        }

        List<UserDO> userDOS = new ArrayList<>();
        for (UserBO bo : userBOS) {
            UserDO userDO = convertBOToDO(bo);
            if (null == userDO) {
                continue;
            }

            userDOS.add(userDO);
        }

        return userDOS;
    }

    /**
     * 领域模型转DO
     *
     * @param bo 领域模型
     * @return      DO
     */
    public static UserDO convertBOToDO(UserBO bo) {
        if (bo == null) {
            return null;
        }

        UserDO userDO = new UserDO();

        userDO.setId(bo.getId());
        userDO.setName(bo.getName());
        userDO.setUsername(bo.getUsername());
        userDO.setEmail(bo.getEmail());
        userDO.setPassword(bo.getPassword());
        userDO.setRoles(RoleConvertor.convertBOToDO(bo.getRoles()));
        userDO.setGmtCreate(new Date());
        userDO.setGmtModified(new Date());

        return userDO;
    }

    /**
     * DO批量转领域模型
     *
     * @param userDOS DO列表
     * @return 领域模型列表
     */
    public static List<UserBO> convertDOToBO(List<UserDO> userDOS) {
        if (userDOS == null) {
            return null;
        }

        List<UserBO> userBOS = new ArrayList<>();

        for (UserDO userDO : userDOS) {

            UserBO userBO = convertDOToBO(userDO);
            if (null == userBO) {
                continue;
            }

            userBOS.add(userBO);
        }

        return userBOS;
    }

    /**
     * 将DO转化为领域配置模型
     *
     * @param userDO
     * @return
     */
    public static UserBO convertDOToBO(UserDO userDO) {
        if (userDO == null) {
            return null;
        }

        UserBO bo = new UserBO();

        bo.setId(userDO.getId());
        bo.setName(userDO.getName());
        bo.setUsername(userDO.getUsername());
        bo.setEmail(userDO.getEmail());
        bo.setPassword(userDO.getPassword());
        bo.setRoles(RoleConvertor.convertDOToBO(userDO.getRoles()));
        bo.setGmtCreate(userDO.getGmtCreate());
        bo.setGmtModified(userDO.getGmtModified());

        return bo;
    }
}