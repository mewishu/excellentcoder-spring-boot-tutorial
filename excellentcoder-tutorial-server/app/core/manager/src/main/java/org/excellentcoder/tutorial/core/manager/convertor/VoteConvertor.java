/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.convertor;

import org.excellentcoder.tutorial.common.dal.dataobject.VoteDO;
import org.excellentcoder.tutorial.core.model.poll.VoteBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投票结果领域模型转换器
 * 
 * @author xbyan
 * @version $Id: VoteConvertor.java, v 0.1 2019-11-10 8:55 PM xbyan Exp $$
 */
public class VoteConvertor {
    /**
     * 领域模型转DO
     *
     * @param voteBOS 领域模型
     * @return      DO
     */
    public static List<VoteDO> convertBOToDO(List<VoteBO> voteBOS) {
        if (voteBOS == null) {
            return null;
        }

        List<VoteDO> voteDOS = new ArrayList<>();
        for (VoteBO bo : voteBOS) {
            VoteDO voteDO = convertBOToDO(bo);
            if (null == voteDO) {
                continue;
            }

            voteDOS.add(voteDO);
        }

        return voteDOS;
    }

    /**
     * 领域模型转DO
     *
     * @param bo 领域模型
     * @return      DO
     */
    public static VoteDO convertBOToDO(VoteBO bo) {
        if (bo == null) {
            return null;
        }

        VoteDO voteDO = new VoteDO();

        voteDO.setId(bo.getId());
        voteDO.setUserId(bo.getUser().getId());
        voteDO.setPollId(bo.getPoll().getId());
        voteDO.setChoiceId(bo.getChoice().getId());
        voteDO.setGmtCreate(new Date());
        voteDO.setGmtModified(new Date());

        return voteDO;
    }

    /**
     * DO批量转领域模型
     *
     * @param voteDOS DO列表
     * @return 领域模型列表
     */
    public static List<VoteBO> convertDOToBO(List<VoteDO> voteDOS) {
        if (voteDOS == null) {
            return null;
        }

        List<VoteBO> voteBOS = new ArrayList<>();

        for (VoteDO voteDO : voteDOS) {

            VoteBO voteBO = convertDOToBO(voteDO);
            if (null == voteBO) {
                continue;
            }

            voteBOS.add(voteBO);
        }

        return voteBOS;
    }

    /**
     * 将DO转化为领域配置模型
     *
     * @param voteDO
     * @return
     */
    public static VoteBO convertDOToBO(VoteDO voteDO) {
        if (voteDO == null) {
            return null;
        }

        VoteBO bo = new VoteBO();

        bo.setId(voteDO.getId());
        bo.setUser(UserConvertor.convertDOToBO(voteDO.getUser()));
        bo.setPoll(PollConvertor.convertDOToBO(voteDO.getPoll()));
        bo.setChoice(ChoiceConvertor.convertDOToBO(voteDO.getChoice()));
        bo.setGmtCreate(voteDO.getGmtCreate());
        bo.setGmtModified(voteDO.getGmtModified());

        return bo;
    }
}