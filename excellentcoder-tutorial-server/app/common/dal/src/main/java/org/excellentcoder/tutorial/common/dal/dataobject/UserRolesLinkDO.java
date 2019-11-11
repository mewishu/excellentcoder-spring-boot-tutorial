package org.excellentcoder.tutorial.common.dal.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class UserRolesLinkDO {
    /**
     * 用户信息
     */
    private UserDO userDO;

    /**
     * 角色信息
     */
    private RoleDO roleDO;

    /**
     * 创建时间
     */
    private Date   gmtCreate;

    /**
     * 修改时间
     */
    private Date   gmtModified;

}