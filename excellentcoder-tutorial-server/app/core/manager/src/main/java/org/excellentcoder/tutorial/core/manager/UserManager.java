/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager;

import org.excellentcoder.tutorial.core.model.poll.UserBO;

import java.util.Optional;

/**
 * 用户管理接口
 *
 * @author xbyan
 * @version $Id: UserManager.java, v 0.1 2019-11-07 4:20 PM xbyan Exp $$
 */
public interface UserManager {

    /**
     * 插入用户信息
     * 
     * @return
     */
    UserBO insert(UserBO user);

    /**
     * 根据id查找用户领域模型
     *
     * @param id
     * @return
     */
    Optional<UserBO> queryById(Long id);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    Optional<UserBO> queryByUsername(String username);

    /**
     * 根据用户名或者邮箱查找用户
     * 
     * @param username
     * @param email
     * @return
     */
    Optional<UserBO> queryByUsernameOrEmail(String username, String email);

    /**
     * 是否存在指定的用户名
     *
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);

    /**
     * 是否存在指定的邮箱
     *
     * @param email
     * @return
     */
    Boolean existsByEmail(String email);
}