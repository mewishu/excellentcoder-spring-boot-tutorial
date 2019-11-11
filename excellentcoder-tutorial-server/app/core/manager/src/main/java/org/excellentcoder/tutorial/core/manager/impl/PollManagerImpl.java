/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.impl;

import org.apache.ibatis.session.RowBounds;
import org.excellentcoder.tutorial.common.dal.dataobject.PollDO;
import org.excellentcoder.tutorial.common.dal.dataobject.PollDOExample;
import org.excellentcoder.tutorial.common.dal.mapper.auto.PollDOMapper;
import org.excellentcoder.tutorial.common.service.facade.dto.request.PollQueryDTO;
import org.excellentcoder.tutorial.core.manager.ChoiceManager;
import org.excellentcoder.tutorial.core.manager.PollManager;
import org.excellentcoder.tutorial.core.manager.UserManager;
import org.excellentcoder.tutorial.core.model.poll.PollBO;
import org.excellentcoder.tutorial.core.manager.convertor.PollConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 投票管理实现类
 * 
 * @author xbyan
 * @version $Id: PollManagerImpl.java, v 0.1 2019-10-11 8:52 PM xbyan Exp $$
 */
@Component
public class PollManagerImpl implements PollManager {
    /** 投票接口DAO层 */
    @Autowired
    private PollDOMapper  pollDOMapper;

    /** 投票选项管理器接口 */
    @Autowired
    private ChoiceManager choiceManager;

    /** 用户管理器接口 */
    @Autowired
    private UserManager   userManager;

    /**
     * @see org.excellentcoder.tutorial.core.manager.PollManager#countByCreatedBy(Long)
     */
    @Override
    public long countByCreatedBy(Long userId) {
        PollDOExample pollDOExample = new PollDOExample();

        pollDOExample.createCriteria().andCreatedByEqualTo(userId);

        return pollDOMapper.countByExample(pollDOExample);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.PollManager#insert(PollBO)
     */
    @Override
    public Long insert(PollBO pollBO) {
        PollDO pollDO = PollConvertor.convertBOToDO(pollBO);
        pollDOMapper.insert(pollDO);
        return pollDO.getId();
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.PollManager#queryById(Long)
     */
    @Override
    public Optional<PollBO> queryById(Long pollId) {
        PollDO pollDO = pollDOMapper.selectByPrimaryKey(pollId);
        return Optional.ofNullable(mapDOToBO(pollDO));
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.PollManager#queryDyn(PollQueryDTO, Pageable)
     */
    @Override
    public Page<PollBO> queryDyn(PollQueryDTO query, Pageable pageable) {
        // 1. 查找投票方案
        PollDOExample pollDOExample = PollConvertor.convertQueryToDynamicDOExample(query);

        List<PollDO> pollDOS = pollDOMapper.selectByExampleWithRowbounds(pollDOExample,
            new RowBounds(Math.toIntExact(pageable.getOffset()), pageable.getPageSize()));
        List<PollBO> pollBOS = pollDOS.stream().map(poll -> mapDOToBO(poll))
            .collect(Collectors.toList());

        // 注: mybatis暂时没有spring-data-jpa的直接返回Page<PollBO>的功能，进行二次查询
        long totalElements = pollDOMapper.countByExample(pollDOExample);
        return new PageImpl<>(pollBOS, pageable, totalElements);
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.PollManager#queryByIdIn(List)
     */
    @Override
    public List<PollBO> queryByIdIn(List<Long> pollIds) {
        PollDOExample pollDOExample = new PollDOExample();

        pollDOExample.createCriteria().andIdIn(pollIds);
        pollDOExample.setOrderByClause("gmt_create desc");

        List<PollDO> pollDOS = pollDOMapper.selectByExample(pollDOExample);

        List<PollBO> pollBOS = pollDOS.stream().map(poll -> mapDOToBO(poll))
            .collect(Collectors.toList());
        return pollBOS;
    }

    /**
     * DO映射成BO对象
     * 
     * @param pollDO
     * @return
     */
    private PollBO mapDOToBO(PollDO pollDO) {
        PollBO pollBO = PollConvertor.convertDOToBO(pollDO);

        // 1. 填充投票对应的选项
        pollBO.setChoices(choiceManager.queryChoiceByPollId(pollDO.getId()));

        // 2. 填充创建人/修改人等用户信息
        pollBO.setCreatedBy(userManager.queryById(pollDO.getCreatedBy()).get());

        return pollBO;
    }

    /**
     * @see org.excellentcoder.tutorial.core.manager.PollManager#queryByCreatedBy(Long, Pageable)
     */
    @Override
    public Page<PollBO> queryByCreatedBy(Long userId, Pageable pageable) {
        // 1. 查找投票方案
        PollDOExample pollDOExample = new PollDOExample();
        pollDOExample.createCriteria().andCreatedByEqualTo(userId);

        List<PollDO> pollDOS = pollDOMapper.selectByExampleWithRowbounds(pollDOExample,
            new RowBounds(Math.toIntExact(pageable.getOffset()), pageable.getPageSize()));
        List<PollBO> pollBOS = pollDOS.stream().map(poll -> mapDOToBO(poll))
            .collect(Collectors.toList());

        // 注: mybatis暂时没有spring-data-jpa的直接返回Page<PollBO>的功能，进行二次查询
        long totalElements = pollDOMapper.countByExample(pollDOExample);
        return new PageImpl<>(pollBOS, pageable, totalElements);
    }
}