/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.impl;

import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceVoteCount;
import org.excellentcoder.tutorial.common.dal.dataobject.VoteDO;
import org.excellentcoder.tutorial.common.dal.dataobject.VoteDOExample;
import org.excellentcoder.tutorial.common.dal.mapper.auto.VoteDOMapper;
import org.excellentcoder.tutorial.common.dal.mapper.manual.ManualVoteDOMapper;
import org.excellentcoder.tutorial.core.manager.VoteManager;
import org.excellentcoder.tutorial.core.manager.convertor.VoteConvertor;
import org.excellentcoder.tutorial.core.model.poll.VoteBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 执行投票管理器接口实现类
 * 
 * @author xbyan
 * @version $Id: VoteManagerImpl.java, v 0.1 2019-11-10 6:28 PM xbyan Exp $$
 */
@Component
public class VoteManagerImpl implements VoteManager {
    /** 投票结果管理Mapper */
    @Autowired
    private VoteDOMapper       voteDOMapper;

    /** 手工投票结果管理Mapper */
    @Autowired
    private ManualVoteDOMapper manualVoteDOMapper;

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#insert(VoteBO)
     */
    @Override
    public VoteBO insert(VoteBO voteBO) {
        VoteDO voteDO = VoteConvertor.convertBOToDO(voteBO);

        int id = voteDOMapper.insert(voteDO);

        voteBO.setId(Long.valueOf(id));
        return voteBO;
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#queryByUserId(Long, Pageable)
     */
    @Override
    public Page<VoteBO> queryByUserId(Long userId, Pageable pageable) {
        List<VoteDO> voteDOS = manualVoteDOMapper.queryByUserId(userId, pageable.getOffset(),
            pageable.getPageSize());
        List<VoteBO> voteBOS = VoteConvertor.convertDOToBO(voteDOS);

        long totalElements = countByUserId(userId);
        return new PageImpl<>(voteBOS, pageable, totalElements);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#queryByUserIdAndPollId(Long, Long)
     */
    @Override
    public VoteBO queryByUserIdAndPollId(Long userId, Long pollId) {
        VoteDO voteDO = manualVoteDOMapper.queryByUserIdAndPollId(userId, pollId);
        return VoteConvertor.convertDOToBO(voteDO);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#queryByUserIdAndPollIdIn(Long, List)
     */
    @Override
    public List<VoteBO> queryByUserIdAndPollIdIn(Long userId, List<Long> pollIds) {
        List<VoteDO> voteDOS = manualVoteDOMapper.queryByUserIdAndPollIdIn(userId, pollIds);
        return VoteConvertor.convertDOToBO(voteDOS);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#countByUserId(Long)
     */
    @Override
    public long countByUserId(Long userId) {
        VoteDOExample voteDOExample = new VoteDOExample();

        voteDOExample.createCriteria().andUserIdEqualTo(userId);

        return voteDOMapper.countByExample(voteDOExample);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#countByPollIdGroupByChoiceId(Long)
     */
    @Override
    public List<ChoiceVoteCount> countByPollIdGroupByChoiceId(Long pollId) {
        return manualVoteDOMapper.countByPollIdGroupByChoiceId(pollId);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.VoteManager#countByPollIdInGroupByChoiceId(List)
     */
    @Override
    public List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(List<Long> pollIds) {
        return manualVoteDOMapper.countByPollIdInGroupByChoiceId(pollIds);
    }
}