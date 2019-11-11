/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.convertor;

import org.excellentcoder.tutorial.common.dal.dataobject.RoleDO;
import org.excellentcoder.tutorial.core.model.enums.RoleName;
import org.excellentcoder.tutorial.core.model.poll.RoleBO;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色领域模型转换器
 * 
 * @author xbyan
 * @version $Id: RoleConvertor.java, v 0.1 2019-11-10 2:48 PM xbyan Exp $$
 */
public class RoleConvertor {
    /**
     * 领域模型转DO
     *
     * @param roleBOS 领域模型
     * @return      DO
     */
    public static Set<RoleDO> convertBOToDO(Set<RoleBO> roleBOS) {
        if (roleBOS == null) {
            return null;
        }

        Set<RoleDO> roleDOS = new HashSet<>();
        for (RoleBO bo : roleBOS) {
            RoleDO roleDO = convertBOToDO(bo);
            if (null == roleDO) {
                continue;
            }

            roleDOS.add(roleDO);
        }

        return roleDOS;
    }

    /**
     * 领域模型转DO
     *
     * @param bo 领域模型
     * @return      DO
     */
    public static RoleDO convertBOToDO(RoleBO bo) {
        if (bo == null) {
            return null;
        }

        RoleDO roleDO = new RoleDO();

        roleDO.setId(bo.getId());
        roleDO.setName(bo.getName().name());

        return roleDO;
    }

    /**
     * DO批量转领域模型
     *
     * @param roleDOS DO列表
     * @return 领域模型列表
     */
    public static Set<RoleBO> convertDOToBO(Set<RoleDO> roleDOS) {
        if (roleDOS == null) {
            return null;
        }

        Set<RoleBO> roleBOS = new HashSet<>();

        for (RoleDO roleDO : roleDOS) {

            RoleBO roleBO = convertDOToBO(roleDO);
            if (null == roleBO) {
                continue;
            }

            roleBOS.add(roleBO);
        }

        return roleBOS;
    }

    /**
     * 将DO转化为错误码配置模型
     *
     * @param pollDO
     * @return
     */
    public static RoleBO convertDOToBO(RoleDO pollDO) {
        if (pollDO == null) {
            return null;
        }

        RoleBO bo = new RoleBO();

        bo.setId(pollDO.getId());
        bo.setName(RoleName.valueOf(pollDO.getName()));

        return bo;
    }
}