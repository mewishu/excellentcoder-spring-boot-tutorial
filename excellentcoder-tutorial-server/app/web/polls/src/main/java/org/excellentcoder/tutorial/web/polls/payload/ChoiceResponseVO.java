/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * 选择响应
 *
 * @author xbyan
 * @version $Id: ChoiceResponseVO.java, v 0.1 2019-10-24 9:22 PM xbyan Exp $$
 */
@Getter
@Setter
public class ChoiceResponseVO {
    /** 主键 */
    private long   id;

    /** 文本 */
    private String text;

    /** 投票数 */
    private long   voteCount;
}