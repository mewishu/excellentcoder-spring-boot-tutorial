/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager;

import org.excellentcoder.tutorial.core.model.poll.ChoiceBO;

import java.util.List;

/**
 * 投票选项管理器接口
 * 
 * @author xbyan
 * @version $Id: ChoiceManager.java, v 0.1 2019-11-06 6:53 PM xbyan Exp $$
 */
public interface ChoiceManager {

    /**
     * 根据投票id查找对应的选项列表
     * 
     * @param pollId
     * @return
     */
    List<ChoiceBO> queryChoiceByPollId(Long pollId);
}