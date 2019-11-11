/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.model.poll;

import lombok.Getter;
import lombok.Setter;
import org.excellentcoder.tutorial.core.model.enums.RoleName;

/**
 * @author xbyan
 * @version $Id: RoleBO.java, v 0.1 2019-11-04 10:15 PM xbyan Exp $$
 */
@Getter
@Setter
public class RoleBO {
    /** 数据库中相应记录的id */
    private Long     id;

    /** 角色名称枚举 */
    private RoleName name;
}