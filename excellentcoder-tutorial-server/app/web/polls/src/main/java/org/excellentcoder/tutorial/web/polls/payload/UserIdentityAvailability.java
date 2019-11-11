/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户身份是否可用
 * 
 * @author xbyan
 * @version $Id: UserIdentityAvailability.java, v 0.1 2019-11-10 5:11 PM xbyan Exp $$
 */
@Getter
@Setter
public class UserIdentityAvailability {
    /** 是否可用 */
    private Boolean available;

    public UserIdentityAvailability(Boolean available) {
        this.available = available;
    }
}