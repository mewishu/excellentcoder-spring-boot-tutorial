/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager;

import org.excellentcoder.tutorial.core.model.enums.RoleName;
import org.excellentcoder.tutorial.core.model.poll.RoleBO;

import java.util.Optional;

/**
 * 角色管理器接口
 * 
 * @author xbyan
 * @version $Id: RoleManager.java, v 0.1 2019-11-08 8:42 PM xbyan Exp $$
 */
public interface RoleManager {
    /**
     * 根据角色名查找角色
     *
     * @param name
     * @return
     */
    Optional<RoleBO> queryByName(RoleName name);
}