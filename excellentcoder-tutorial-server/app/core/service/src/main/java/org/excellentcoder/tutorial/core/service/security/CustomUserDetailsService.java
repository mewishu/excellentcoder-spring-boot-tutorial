/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.service.security;

import org.excellentcoder.tutorial.common.util.exception.ResourceNotFoundException;
import org.excellentcoder.tutorial.core.manager.UserManager;
import org.excellentcoder.tutorial.core.model.poll.UserBO;
import org.excellentcoder.tutorial.core.model.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 定制用户密码服务
 * 
 * @author xbyan
 * @version $Id: CustomUserDetailsService.java, v 0.1 2019-11-08 6:39 PM xbyan Exp $$
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** 用户管理器接口 */
    @Autowired
    private UserManager userManager;

    /**
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let people login with either username or email
        UserBO user = userManager.queryByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException(
                "User not found with username or email : " + usernameOrEmail));

        return UserPrincipal.create(user);
    }

    /**
     * 加载用户信息
     * 
     * @param id
     * @return
     */
    public UserDetails loadUserById(Long id) {
        UserBO user = userManager.queryById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return UserPrincipal.create(user);
    }
}