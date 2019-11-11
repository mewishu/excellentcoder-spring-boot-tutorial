/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager;

import org.excellentcoder.tutorial.common.service.facade.dto.request.PollQueryDTO;
import org.excellentcoder.tutorial.core.model.poll.PollBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 投票管理类接口
 * 
 * @author xbyan
 * @version $Id: PollManager.java, v 0.1 2019-10-11 8:52 PM xbyan Exp $$
 */
public interface PollManager {
    /**
     * 按创建人统计投票数
     * 
     * @param userId
     * @return
     */
    long countByCreatedBy(Long userId);

    /**
     *  新增投票
     *
     * @param pollBO 投票领域模型
     * @return ID 插入的数据主键
     */
    Long insert(PollBO pollBO);

    /**
     * 根据id查询投票
     * 
     * @param pollId
     * @return
     */
    Optional<PollBO> queryById(Long pollId);

    /**
     *  动态查询投票
     *
     * @param pollQuery 投票领域模型
     * @return
     */
    Page<PollBO> queryDyn(PollQueryDTO pollQuery, Pageable pageable);

    /**
     *  动态查询投票
     *
     * @param pollIds 用户Id
     * @return
     */
    List<PollBO> queryByIdIn(List<Long> pollIds);

    /**
     *  动态查询投票
     *
     * @param userId 用户Id
     * @return 
     */
    Page<PollBO> queryByCreatedBy(Long userId, Pageable pageable);
}