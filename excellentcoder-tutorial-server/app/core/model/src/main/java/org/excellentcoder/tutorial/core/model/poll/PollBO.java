/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.model.poll;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投票领域模型
 * 
 * @author xbyan
 * @version $Id: PollBO.java, v 0.1 2019-10-09 10:18 PM xbyan Exp $$
 */
@Data
public class PollBO {
    /** 数据库中相应记录的id */
    private Long           id;

    /** 问题 */
    private String         question;

    private List<ChoiceBO> choices = new ArrayList<>();

    /** 创建人信息 */
    private UserBO         createdBy;

    /** 修改人信息 */
    private UserBO         updatedBy;

    /** 失效时间 */
    private Date           gmtExpiration;

    /** 创建时间 */
    private Date           gmtCreate;

    /** 修改时间 */
    private Date           gmtModified;
}