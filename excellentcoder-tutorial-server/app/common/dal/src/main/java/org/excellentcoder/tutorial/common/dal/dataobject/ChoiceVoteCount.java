/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.dal.dataobject;

import lombok.Getter;
import lombok.Setter;

/**
 * 每个choice的投票个数
 * 
 * @author xbyan
 * @version $Id: ChoiceVoteCount.java, v 0.1 2019-11-10 9:59 PM xbyan Exp $$
 */
@Getter
@Setter
public class ChoiceVoteCount {

    /** choiceId */
    private Long choiceId;

    /** 投票个数 */
    private Long voteCount;
}