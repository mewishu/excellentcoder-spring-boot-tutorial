/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 投票响应对象
 * 
 * @author xbyan
 * @version $Id: PollResponseVO.java, v 0.1 2019-10-24 9:19 PM xbyan Exp $$
 */
@Getter
@Setter
public class PollResponseVO {
    /** 主键 */
    private Long                   id;

    /** 问题 */
    private String                 question;

    private List<ChoiceResponseVO> choices;

    private UserSummary            createdBy;

    /** 失效时间 */
    private Date                   gmtExpiration;

    /** 创建时间 */
    private Date                   gmtCreate;

    /** 修改时间 */
    private Date                   gmtModified;

    /** 是否失效 */
    private Boolean                expired;

    /** 选中的选项 */
    private Long                   selectedChoice;

    /** 总投票数 */
    private Long                   totalVotes;
}