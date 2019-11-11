/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.controller;

import org.excellentcoder.tutorial.biz.poll.service.PollService;
import org.excellentcoder.tutorial.common.util.constants.PollConstants;
import org.excellentcoder.tutorial.common.util.exception.ResourceNotFoundException;
import org.excellentcoder.tutorial.core.manager.PollManager;
import org.excellentcoder.tutorial.core.manager.UserManager;
import org.excellentcoder.tutorial.core.manager.VoteManager;
import org.excellentcoder.tutorial.core.model.poll.PollVotedBO;
import org.excellentcoder.tutorial.core.model.poll.UserBO;
import org.excellentcoder.tutorial.core.model.security.CurrentUser;
import org.excellentcoder.tutorial.core.model.security.UserPrincipal;
import org.excellentcoder.tutorial.web.home.result.WebPagedResult;
import org.excellentcoder.tutorial.web.polls.convertor.PollVOConvertor;
import org.excellentcoder.tutorial.web.polls.payload.PollResponseVO;
import org.excellentcoder.tutorial.web.polls.payload.UserIdentityAvailability;
import org.excellentcoder.tutorial.web.polls.payload.UserProfileResponseVO;
import org.excellentcoder.tutorial.web.polls.payload.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息controller: rest风格
 *
 * @author xbyan
 * @version $Id: UserController.java, v 0.1 2019-11-10 4:45 PM xbyan Exp $$
 */

@RestController
@RequestMapping("/api")
public class UserController {
    /**
     * 日志打印
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /** 用户管理器接口 */
    @Autowired
    private UserManager         userManager;

    /** 投票仓储类 */
    @Autowired
    private PollManager         pollManager;

    /** 投票结果仓储类 */
    @Autowired
    private VoteManager         voteManager;

    /**
     * 投票服务
     */
    @Autowired
    private PollService         pollService;

    /**
     * 加载当前用户信息
     *
     * @param currentUser
     * @return
     */
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
            currentUser.getName());
        return userSummary;
    }

    /**
     * 校验用户姓名是否可用
     *
     * @param username
     * @return
     */
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userManager.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    /**
     * 校验电子邮箱是否可用
     *
     * @param email
     * @return
     */
    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userManager.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    /**
     * 获取个人资料信息
     * 
     * @param username
     * @return
     */
    @GetMapping("/users/{username}")
    public UserProfileResponseVO getUserProfile(@PathVariable(value = "username") String username) {
        UserBO user = userManager.queryByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollManager.countByCreatedBy(user.getId());
        long voteCount = voteManager.countByUserId(user.getId());

        UserProfileResponseVO userProfile = new UserProfileResponseVO(user.getId(),
            user.getUsername(), user.getName(), user.getGmtCreate().toInstant(), pollCount,
            voteCount);

        return userProfile;
    }

    /**
     * 获取对应用户的投票响应
     *
     * @param username
     * @param currentUser
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/users/{username}/polls")
    public WebPagedResult<PollResponseVO> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                            @CurrentUser UserPrincipal currentUser,
                                                            @RequestParam(value = "page", defaultValue = PollConstants.DEFAULT_PAGE_NUMBER) int page,
                                                            @RequestParam(value = "size", defaultValue = PollConstants.DEFAULT_PAGE_SIZE) int size) {
        Page<PollVotedBO> votedBOPage = pollService.getPollsCreatedBy(username, currentUser, page,
            size);

        return new WebPagedResult<>(true, PollVOConvertor.convertBOToVO(votedBOPage.getContent()),
            page, size, votedBOPage.getTotalElements(), votedBOPage.getTotalPages(),
            votedBOPage.isLast());
    }

    /**
     * 获取被投票用户的响应
     *
     * @param username
     * @param currentUser
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/users/{username}/votes")
    public WebPagedResult<PollResponseVO> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                          @CurrentUser UserPrincipal currentUser,
                                                          @RequestParam(value = "page", defaultValue = PollConstants.DEFAULT_PAGE_NUMBER) int page,
                                                          @RequestParam(value = "size", defaultValue = PollConstants.DEFAULT_PAGE_SIZE) int size) {
        Page<PollVotedBO> votedBOPage = pollService.getPollsVotedBy(username, currentUser, page,
            size);

        return new WebPagedResult<>(true, PollVOConvertor.convertBOToVO(votedBOPage.getContent()),
            page, size, votedBOPage.getTotalElements(), votedBOPage.getTotalPages(),
            votedBOPage.isLast());
    }
}