/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.model.poll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 投票结果领域模型
 * 
 * @author xbyan
 * @version $Id: VoteBO.java, v 0.1 2019-11-10 8:07 PM xbyan Exp $$
 */
@Getter
@Setter
public class VoteBO {

    /** 数据库中相应记录的id */
    private Long     id;

    /** 用户领域模型 */
    private UserBO   user;

    /** 投票领域模型 */
    private PollBO   poll;

    /** 选项领域模型 */
    private ChoiceBO choice;

    /** 创建时间 */
    private Date     gmtCreate;

    /** 修改时间 */
    private Date     gmtModified;
}