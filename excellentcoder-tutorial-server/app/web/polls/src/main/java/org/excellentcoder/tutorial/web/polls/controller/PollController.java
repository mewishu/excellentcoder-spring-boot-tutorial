/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.controller;

import org.excellentcoder.tutorial.biz.poll.service.PollService;
import org.excellentcoder.tutorial.common.service.facade.dto.request.PollQueryDTO;
import org.excellentcoder.tutorial.common.util.constants.PollConstants;
import org.excellentcoder.tutorial.common.util.enums.ResultCodeEnum;
import org.excellentcoder.tutorial.common.util.exception.ApiException;
import org.excellentcoder.tutorial.core.model.poll.PollVotedBO;
import org.excellentcoder.tutorial.core.model.security.CurrentUser;
import org.excellentcoder.tutorial.core.model.security.UserPrincipal;
import org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBack;
import org.excellentcoder.tutorial.web.home.result.WebPagedResult;
import org.excellentcoder.tutorial.web.home.result.WebResult;
import org.excellentcoder.tutorial.web.home.template.WebOperateTemplate;
import org.excellentcoder.tutorial.web.home.template.WebResultUtil;
import org.excellentcoder.tutorial.web.polls.convertor.PollVOConvertor;
import org.excellentcoder.tutorial.web.polls.payload.PollRequestVO;
import org.excellentcoder.tutorial.web.polls.payload.PollResponseVO;
import org.excellentcoder.tutorial.web.polls.payload.VoteRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

/**
 * 投票controller: rest风格
 * 
 * @author xbyan
 * @version $Id: PollController.java, v 0.1 2019-10-09 9:25 PM xbyan Exp $$
 */
@RestController
@RequestMapping("/api/polls")
public class PollController {
    /**
     * 日志打印
     */
    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

    /**
     * 投票服务
     */
    @Autowired
    private PollService         pollService;

    /**
     * 查询投票请求
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public WebPagedResult<PollResponseVO> getPolls(@CurrentUser UserPrincipal currentUser,
                                                   @RequestParam(value = "page", defaultValue = PollConstants.DEFAULT_PAGE_NUMBER) int page,
                                                   @RequestParam(value = "size", defaultValue = PollConstants.DEFAULT_PAGE_SIZE) int size) {
        final WebPagedResult<PollResponseVO> result = new WebPagedResult<>();

        WebOperateTemplate.operate(logger, "查询投票请求处理", result, new AbstractHandleCallBack() {
            /**
             * @see org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBack#checkAndFillParams()
             */
            @Override
            public void checkAndFillParams() {
                if (page < 0) {
                    throw new ApiException("Page number cannot be less than zero.");
                }

                if (size > PollConstants.MAX_PAGE_SIZE) {
                    throw new ApiException(
                        "Page size must not be greater than " + PollConstants.MAX_PAGE_SIZE);
                }
            }

            /**
             * @see org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBack#process()
             */
            @Override
            public void process() {
                Page<PollVotedBO> pollVotedBOS = pollService.queryDyn(currentUser,
                    new PollQueryDTO(),
                    PageRequest.of(page, size, Sort.Direction.DESC, "gmtCreate"));

                result.setSuccess(true);
                result.setResult(PollVOConvertor.convertBOToVO(pollVotedBOS.getContent()));
                result.setPage(page);
                result.setSize(size);
                result.setTotalElements(pollVotedBOS.getTotalElements());
                result.setTotalPages(pollVotedBOS.getTotalPages());
                result.setLast(pollVotedBOS.isLast());
            }
        });

        return result;
    }

    /**
     * 创建投票请求处理
     * 
     * @RequestBody将请求的Http Body和参数messge进行了映射(@RequestBody --> 提交的请求头部要带上"Content-Type:application/json")
     * @ModelAttribute将请求的Http Body中的表单数据和参数messge进行了映射(提交的请求头部要带上"Content-Type:application/x-www-form-urlencoded"，表明请求体是表单数据，格式是"key1=value1&key2=value2&key3=value3")
     * 
     * @param pollRequestVO
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequestVO pollRequestVO) {
        // 1. 执行创建投票逻辑
        final WebResult<Long> result = new WebResult<>();

        WebOperateTemplate.operate(logger, "创建投票请求处理", result, new AbstractHandleCallBack() {
            /**
             * @see org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBackWithResult#process()
             */
            @Override
            public void process() {
                Long pollId = pollService.createPoll(PollVOConvertor.convertVOToBO(pollRequestVO));
                WebResultUtil.generateResult(result, ResultCodeEnum.SUCCESS, pollId);
            }
        });

        // 2. 组装返回结果
        if (result.isSuccess()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(result.getResult()).toUri();
            return ResponseEntity.created(location).body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 根据投票id查询投票响应
     * 
     * @param pollId
     * @return
     */
    @GetMapping("/{pollId}")
    public PollResponseVO getPollById(@CurrentUser UserPrincipal currentUser,
                                      @PathVariable Long pollId) {
        PollVotedBO pollVotedBO = pollService.getPollById(pollId, currentUser);
        return PollVOConvertor.convertBOToVO(pollVotedBO);
    }

    /**
     * 强制投票
     * 
     * @param pollId
     * @param voteRequest
     * @return
     */
    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponseVO castVote(@CurrentUser UserPrincipal currentUser,
                                   @PathVariable Long pollId,
                                   @Valid @RequestBody VoteRequestVO voteRequest) {
        PollVotedBO pollVotedBO = pollService.castVoteAndGetUpdatedPoll(pollId,
            voteRequest.getChoiceId(), currentUser);
        return PollVOConvertor.convertBOToVO(pollVotedBO);
    }
}