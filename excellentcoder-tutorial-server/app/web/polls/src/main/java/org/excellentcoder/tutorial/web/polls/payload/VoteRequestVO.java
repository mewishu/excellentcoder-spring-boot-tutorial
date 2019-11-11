/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * 投票请求
 * 
 * @author xbyan
 * @version $Id: VoteRequestVO.java, v 0.1 2019-10-24 9:25 PM xbyan Exp $$
 */
@Getter
@Setter
public class VoteRequestVO {

    /** 主键 */
    @NotNull
    private Long choiceId;
}