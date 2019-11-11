/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户摘要
 *
 * @author xbyan
 * @version $Id: UserSummary.java, v 0.1 2019-10-24 9:23 PM xbyan Exp $$
 */
@Getter
@Setter
public class UserSummary {
    /** 主键 */
    private Long   id;

    /** 用户名 */
    private String username;

    /** 姓名 */
    private String name;

    public UserSummary(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

}