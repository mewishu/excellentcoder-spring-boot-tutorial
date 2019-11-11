/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.model.poll;

import lombok.Data;

import java.util.Map;

/**
 * 用户投票选中: Poll维度
 * 
 * @author xbyan
 * @version $Id: PollVotedBO.java, v 0.1 2019-11-11 2:50 PM xbyan Exp $$
 */
@Data
public class PollVotedBO {

    /** 投票领域模型 */
    private PollBO          poll;

    /** 每一个选项投票数 */
    private Map<Long, Long> choiceVotesMap;

    /** 用户领域模型 */
    private UserBO          user;

    /** 当前用户选中的选项 */
    private ChoiceBO        choice;
}