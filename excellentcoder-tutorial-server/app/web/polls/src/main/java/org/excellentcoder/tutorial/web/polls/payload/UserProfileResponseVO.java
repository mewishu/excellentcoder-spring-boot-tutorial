/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 用户资料响应对象
 * 
 * @author xbyan
 * @version $Id: UserProfileResponseVO.java, v 0.1 2019-11-10 5:13 PM xbyan Exp $$
 */
@Getter
@Setter
public class UserProfileResponseVO {

    private Long    id;

    private String  username;

    private String  name;

    private Instant joinedAt;

    private Long    pollCount;

    private Long    voteCount;

    public UserProfileResponseVO(Long id, String username, String name, Instant joinedAt,
                                 Long pollCount, Long voteCount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
        this.pollCount = pollCount;
        this.voteCount = voteCount;
    }
}