/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户登陆请求对象
 * 
 * @author xbyan
 * @version $Id: LoginRequestVO.java, v 0.1 2019-11-08 3:07 PM xbyan Exp $$
 */
@Getter
@Setter
public class LoginRequestVO {
    /** 用户姓名或者电子邮箱 */
    @NotBlank
    private String usernameOrEmail;

    /** 密码 */
    @NotBlank
    private String password;
}