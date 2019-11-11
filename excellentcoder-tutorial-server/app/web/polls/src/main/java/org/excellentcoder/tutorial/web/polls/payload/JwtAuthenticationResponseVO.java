/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Jwt认证响应对象
 * 
 * @author xbyan
 * @version $Id: JwtAuthenticationResponseVO.java, v 0.1 2019-11-08 5:43 PM xbyan Exp $$
 */
@Getter
@Setter
public class JwtAuthenticationResponseVO {
    /** accessToken */
    private String accessToken;

    /** token类型 */
    private String tokenType = "Bearer";

    /**
     * 构造函数
     * 
     * @param accessToken
     */
    public JwtAuthenticationResponseVO(String accessToken) {
        this.accessToken = accessToken;
    }
}