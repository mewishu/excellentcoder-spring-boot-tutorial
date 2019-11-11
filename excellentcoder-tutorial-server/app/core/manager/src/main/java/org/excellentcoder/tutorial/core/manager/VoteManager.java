/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager;

import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceVoteCount;
import org.excellentcoder.tutorial.core.model.poll.VoteBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 执行投票管理器接口
 * 
 * @author xbyan
 * @version $Id: VoteManager.java, v 0.1 2019-11-10 6:28 PM xbyan Exp $$
 */
public interface VoteManager {
    /**
     * 插入投票结果
     *
     * @param voteBO
     * @return
     */
    VoteBO insert(VoteBO voteBO);

    /**
     * 根据id查找用户领域模型
     *
     * @param userId
     * @return
     */
    Page<VoteBO> queryByUserId(Long userId, Pageable pageable);

    /**
     * 根据userId和pollId查找投票结果
     *
     * @param userId
     * @param pollId
     * @return
     */
    VoteBO queryByUserIdAndPollId(Long userId, Long pollId);

    /**
     * 根据userId和pollIdList查找投票结果
     *
     * @param userId
     * @param pollIds
     * @return
     */
    List<VoteBO> queryByUserIdAndPollIdIn(Long userId, List<Long> pollIds);

    /**
     * 按创建人统计投票数
     *
     * @param userId
     * @return
     */
    long countByUserId(Long userId);

    /**
     * 根据pollId统计每个choice的个数
     *
     * @param pollId
     * @return
     */
    List<ChoiceVoteCount> countByPollIdGroupByChoiceId(Long pollId);

    /**
     * 根据pollIdList统计每个choice的个数
     *
     * @param pollIds
     * @return
     */
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(List<Long> pollIds);
}