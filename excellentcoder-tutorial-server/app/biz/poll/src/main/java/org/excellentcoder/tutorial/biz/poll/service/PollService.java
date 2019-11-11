/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.biz.poll.service;

import org.excellentcoder.tutorial.common.service.facade.dto.request.PollQueryDTO;
import org.excellentcoder.tutorial.core.model.poll.PollBO;
import org.excellentcoder.tutorial.core.model.poll.PollVotedBO;
import org.excellentcoder.tutorial.core.model.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 投票服务接口
 * 
 * @author xbyan
 * @version $Id: PollController.java, v 0.1 2019-10-09 9:25 PM xbyan Exp $$
 */
public interface PollService {

    /**
     * 创建投票请求
     * 
     * @param pollBO
     * @return ID 插入的数据主键
     */
    Long createPoll(PollBO pollBO);

    /**
     * 获得所有的投票结果
     * 
     * @param pollQuery
     * @param pageable
     * @return
     */
    Page<PollVotedBO> queryDyn(UserPrincipal currentUser, PollQueryDTO pollQuery,
                               Pageable pageable);

    /**
     * 获得当前用户的投票结果
     * 
     * @param username
     * @param currentUser
     * @param page
     * @param size
     * @return
     */
    Page<PollVotedBO> getPollsCreatedBy(String username, UserPrincipal currentUser, int page,
                                        int size);

    /**
     * 获得被当前用户投票的结果
     *
     * @param username
     * @param currentUser
     * @param page
     * @param size
     * @return
     */
    Page<PollVotedBO> getPollsVotedBy(String username, UserPrincipal currentUser, int page,
                                      int size);

    /**
     * 根据投票id查询抽票领域模型
     * 
     * @param pollId
     * @return
     */
    PollVotedBO getPollById(Long pollId, UserPrincipal currentUser);

    /**
     * 强制投票
     * 
     * @param pollId
     * @param choiceId
     * @return
     */
    PollVotedBO castVoteAndGetUpdatedPoll(Long pollId, Long choiceId, UserPrincipal currentUser);

}