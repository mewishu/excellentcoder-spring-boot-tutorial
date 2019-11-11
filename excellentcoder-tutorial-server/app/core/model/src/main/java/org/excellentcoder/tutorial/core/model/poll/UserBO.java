/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.model.poll;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户领域模型
 * 
 * @author xbyan
 * @version $Id: UserBO.java, v 0.1 2019-11-04 10:06 PM xbyan Exp $$
 */
@Getter
@Setter
public class UserBO {
    /** 数据库中相应记录的id */
    private Long           id;

    /** 姓名 */
    @NotBlank
    @Size(max = 40)
    private String         name;

    /** 用户姓名 */
    @NotBlank
    @Size(max = 15)
    private String         username;

    /** 电子邮件 */
    @NotBlank
    @Size(max = 40)
    @Email
    private String         email;

    /** 密码 */
    @NotBlank
    @Size(max = 100)
    private String         password;

    /** 创建时间 */
    private Date           gmtCreate;

    /** 修改时间 */
    private Date           gmtModified;

    /** 角色列表 */
    private Set<RoleBO> roles = new HashSet<>();

    /**
     * 默认构造函数
     */
    public UserBO() {

    }

    /**
     * 构造函数
     * 
     * @param name
     * @param username
     * @param email
     * @param password
     */
    public UserBO(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}