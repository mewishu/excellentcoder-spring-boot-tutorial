/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 投票请求对象
 * 
 * @author xbyan
 * @version $Id: PollRequestVO.java, v 0.1 2019-10-09 9:54 PM xbyan Exp $$
 */
@Getter
@Setter
public class PollRequestVO {
    @NotBlank
    @Size(max = 140)
    private String            question;

    @NotNull
    @Size(min = 2, max = 6)
    @Valid
    private List<ChoiceRequestVO> choices;

    @NotNull
    @Valid
    private PollLength        pollLength;
}