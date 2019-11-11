/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.service.facade.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 投票查询请求传输对象
 *
 * @author xbyan
 * @version $Id: PollQueryDTO.java, v 0.1 2019-11-06 11:17 AM xbyan Exp $$
 */
@Data
public class PollQueryDTO implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 2896283816661847254L;

    /** 数据库中相应记录的id */
    private Long              id;

    /** 用户id */
    private Long              userId;

    /**
     * 默认构造函数
     */
    public PollQueryDTO() {

    }

    /**
     * 构造函数
     */
    public PollQueryDTO(Long userId) {
        this.userId = userId;
    }
}