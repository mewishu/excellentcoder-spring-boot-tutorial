/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.manager.convertor;

import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceDO;
import org.excellentcoder.tutorial.core.model.poll.ChoiceBO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xbyan
 * @version $Id: ChoiceConvertor.java, v 0.1 2019-11-06 6:59 PM xbyan Exp $$
 */
public class ChoiceConvertor {

    /**
     * DO批量转领域模型
     *
     * @param choiceDOS DO列表
     * @return 领域模型列表
     */
    public static List<ChoiceBO> convertDOToBO(List<ChoiceDO> choiceDOS) {
        if (choiceDOS == null) {
            return null;
        }

        List<ChoiceBO> choiceBOS = new ArrayList<>();

        for (ChoiceDO choiceDO : choiceDOS) {

            ChoiceBO choiceBO = convertDOToBO(choiceDO);
            if (null == choiceBO) {
                continue;
            }

            choiceBOS.add(choiceBO);
        }

        return choiceBOS;
    }

    /**
     * 将DO转化为领域配置模型
     *
     * @param choiceDO
     * @return
     */
    public static ChoiceBO convertDOToBO(ChoiceDO choiceDO) {
        if (choiceDO == null) {
            return null;
        }

        ChoiceBO bo = new ChoiceBO();

        bo.setId(choiceDO.getId());
        bo.setText(choiceDO.getText());

        return bo;
    }
}