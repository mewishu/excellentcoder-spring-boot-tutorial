/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 投票选择请求对象
 * 
 * @author xbyan
 * @version $Id: ChoiceRequestVO.java, v 0.1 2019-10-09 9:58 PM xbyan Exp $$
 */
@Getter
@Setter
public class ChoiceRequestVO {

    /** 内容 */
    @NotBlank
    @Size(max = 40)
    private String text;
}