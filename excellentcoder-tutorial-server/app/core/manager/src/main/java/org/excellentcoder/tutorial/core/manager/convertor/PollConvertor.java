/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.convertor;

import org.excellentcoder.tutorial.common.dal.dataobject.PollDO;
import org.excellentcoder.tutorial.common.dal.dataobject.PollDOExample;
import org.excellentcoder.tutorial.common.service.facade.dto.request.PollQueryDTO;
import org.excellentcoder.tutorial.core.model.poll.PollBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投票领域模型转换器
 * 
 * @author xbyan
 * @version $Id: PollConvertor.java, v 0.1 2019-10-27 3:14 PM xbyan Exp $$
 */
public class PollConvertor {

    /**
     * 领域模型转DO
     *
     * @param pollBOS 领域模型
     * @return      DO
     */
    public static List<PollDO> convertBOToDO(List<PollBO> pollBOS) {
        if (pollBOS == null) {
            return null;
        }

        List<PollDO> pollDOS = new ArrayList<>();
        for (PollBO model : pollBOS) {
            PollDO pollDO = convertBOToDO(model);
            if (null == pollDO) {
                continue;
            }

            pollDOS.add(pollDO);
        }

        return pollDOS;
    }

    /**
     * 领域模型转DO
     *
     * @param bo 领域模型
     * @return      DO
     */
    public static PollDO convertBOToDO(PollBO bo) {
        if (bo == null) {
            return null;
        }

        PollDO pollDO = new PollDO();

        pollDO.setQuestion(bo.getQuestion());
        pollDO.setGmtExpiration(bo.getGmtExpiration());
        pollDO.setGmtCreate(new Date());
        pollDO.setGmtModified(new Date());

        return pollDO;
    }

    /**
     * 数据查询对象转DOExample
     *
     * @param query 领域模型
     * @return      DO
     */
    public static PollDOExample convertQueryToDynamicDOExample(PollQueryDTO query) {
        if (query == null) {
            return null;
        }

        PollDOExample pollDOExample = new PollDOExample();

        if (query.getId() != null) {
            pollDOExample.createCriteria().andIdEqualTo(query.getId());
        }

        return pollDOExample;
    }

    /**
     * DO批量转领域模型
     *
     * @param pollDOS DO列表
     * @return 领域模型列表
     */
    public static List<PollBO> convertDOToBO(List<PollDO> pollDOS) {
        if (pollDOS == null) {
            return null;
        }

        List<PollBO> pollBOS = new ArrayList<>();

        for (PollDO pollDO : pollDOS) {

            PollBO pollBO = convertDOToBO(pollDO);
            if (null == pollBO) {
                continue;
            }

            pollBOS.add(pollBO);
        }

        return pollBOS;
    }

    /**
     * 将DO转化为错误码配置模型
     *
     * @param pollDO
     * @return
     */
    public static PollBO convertDOToBO(PollDO pollDO) {
        if (pollDO == null) {
            return null;
        }

        PollBO bo = new PollBO();

        bo.setId(pollDO.getId());
        bo.setQuestion(pollDO.getQuestion());
        bo.setGmtExpiration(pollDO.getGmtExpiration());
        bo.setGmtCreate(pollDO.getGmtCreate());
        bo.setGmtModified(pollDO.getGmtModified());

        return bo;
    }

}