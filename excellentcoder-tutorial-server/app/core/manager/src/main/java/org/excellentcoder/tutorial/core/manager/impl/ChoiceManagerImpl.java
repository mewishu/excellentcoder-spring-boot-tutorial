/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.impl;

import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceDO;
import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceDOExample;
import org.excellentcoder.tutorial.common.dal.mapper.auto.ChoiceDOMapper;
import org.excellentcoder.tutorial.core.manager.ChoiceManager;
import org.excellentcoder.tutorial.core.manager.convertor.ChoiceConvertor;
import org.excellentcoder.tutorial.core.model.poll.ChoiceBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 投票选项管理器接口实现类
 * 
 * @author xbyan
 * @version $Id: ChoiceManagerImpl.java, v 0.1 2019-11-06 6:55 PM xbyan Exp $$
 */
@Component
public class ChoiceManagerImpl implements ChoiceManager {

    /** 选项管理Mapper */
    @Autowired
    private ChoiceDOMapper choiceDOMapper;

    /**
     * @see org.excellentcoder.tutorial.core.manager.ChoiceManager#queryChoiceByPollId(Long)
     */
    @Override
    public List<ChoiceBO> queryChoiceByPollId(Long pollId) {
        ChoiceDOExample choiceDOExample = new ChoiceDOExample();

        choiceDOExample.createCriteria().andPollIdEqualTo(pollId);

        List<ChoiceDO> choiceDOS = choiceDOMapper.selectByExample(choiceDOExample);
        return ChoiceConvertor.convertDOToBO(choiceDOS);
    }
}