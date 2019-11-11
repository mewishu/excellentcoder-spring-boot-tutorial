/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.model.poll;

import lombok.Data;

/**
 * 投票选择领域模型
 * 
 * @author xbyan
 * @version $Id: ChoiceBO.java, v 0.1 2019-10-10 11:28 AM xbyan Exp $$
 */
@Data
public class ChoiceBO {
    /** 数据库中相应记录的id */
    private Long   id;

    /** 投票选择内容 */
    private String text;

    /** 投票领域模型 */
    private PollBO pollBO;

    /**
     * 默认构造函数
     */
    public ChoiceBO() {

    }

    /**
     * 构造函数
     * 
     * @param text
     */
    public ChoiceBO(String text) {
        this.text = text;
    }
}