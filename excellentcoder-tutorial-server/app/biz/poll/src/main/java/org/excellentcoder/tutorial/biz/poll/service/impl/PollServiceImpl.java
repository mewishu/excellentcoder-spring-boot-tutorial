/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.biz.poll.service.impl;

import org.excellentcoder.tutorial.biz.poll.service.PollService;
import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceVoteCount;
import org.excellentcoder.tutorial.common.service.facade.dto.request.PollQueryDTO;
import org.excellentcoder.tutorial.common.util.DateUtil;
import org.excellentcoder.tutorial.common.util.exception.BadRequestException;
import org.excellentcoder.tutorial.common.util.exception.ResourceNotFoundException;
import org.excellentcoder.tutorial.common.util.logger.LoggerUtil;
import org.excellentcoder.tutorial.core.manager.PollManager;
import org.excellentcoder.tutorial.core.manager.UserManager;
import org.excellentcoder.tutorial.core.manager.VoteManager;
import org.excellentcoder.tutorial.core.model.poll.ChoiceBO;
import org.excellentcoder.tutorial.core.model.poll.PollBO;
import org.excellentcoder.tutorial.core.model.poll.PollVotedBO;
import org.excellentcoder.tutorial.core.model.poll.UserBO;
import org.excellentcoder.tutorial.core.model.poll.VoteBO;
import org.excellentcoder.tutorial.core.model.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 投票服务接口实现类
 * 
 * @author xbyan
 * @version $Id: PollServiceImpl.java, v 0.1 2019-10-09 10:09 PM xbyan Exp $$
 */
@Service
public class PollServiceImpl implements PollService {
    /**
     * 日志打印
     */
    private static final Logger logger = LoggerFactory.getLogger(PollServiceImpl.class);

    /** 用户管理器接口 */
    @Autowired
    private UserManager         userManager;

    /** 投票管理类 */
    @Autowired
    private PollManager         pollManager;

    /** 投票结果管理类 */
    @Autowired
    private VoteManager         voteManager;

    /**
     * @see org.excellentcoder.tutorial.biz.poll.service.PollService#createPoll(PollBO)
     */
    @Override
    public Long createPoll(PollBO pollBO) {
        return pollManager.insert(pollBO);
    }

    /**
     * @see org.excellentcoder.tutorial.biz.poll.service.PollService#queryDyn(UserPrincipal, PollQueryDTO, Pageable)
     */
    @Override
    public Page<PollVotedBO> queryDyn(UserPrincipal currentUser, PollQueryDTO pollQuery,
                                      Pageable pageable) {
        Page<PollBO> polls = pollManager.queryDyn(pollQuery, pageable);

        // Map Polls to PollResponses containing vote counts and poll creator details
        List<Long> pollIds = polls.map(PollBO::getId).getContent();
        Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
        Map<Long, ChoiceBO> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);

        List<PollVotedBO> pollVotedBOS = polls
            .map(poll -> mapPollToPollVoted(poll, choiceVoteCountMap, poll.getCreatedBy(),
                pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null)))
            .getContent();
        return new PageImpl<>(pollVotedBOS, polls.getPageable(), polls.getSize());
    }

    /**
     * @see org.excellentcoder.tutorial.biz.poll.service.PollService#getPollsCreatedBy(String, UserPrincipal, int, int)
     */
    @Override
    public Page<PollVotedBO> getPollsCreatedBy(String username, UserPrincipal currentUser, int page,
                                               int size) {
        // 1. 根据username查找user
        UserBO user = userManager.queryByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // 2. 根据user查找所有此用户创建的投票poll
        Page<PollBO> polls = pollManager.queryByCreatedBy(user.getId(),
            PageRequest.of(page, size, Sort.Direction.DESC, "gmtCreate"));

        // 3. 查找投票结果
        List<Long> pollIds = polls.map(PollBO::getId).getContent();
        Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
        Map<Long, ChoiceBO> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);

        List<PollVotedBO> pollVotedBOS = polls
            .map(poll -> mapPollToPollVoted(poll, choiceVoteCountMap, user,
                pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null)))
            .getContent();
        return new PageImpl<>(pollVotedBOS, polls.getPageable(), polls.getSize());
    }

    /**
     * @see org.excellentcoder.tutorial.biz.poll.service.PollService#getPollsVotedBy(String, UserPrincipal, int, int)
     */
    @Override
    public Page<PollVotedBO> getPollsVotedBy(String username, UserPrincipal currentUser, int page,
                                             int size) {
        // 1. 根据username查找user
        UserBO user = userManager.queryByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // 2. 根据user查找所有此用户创建的投票poll
        Page<VoteBO> userVoted = voteManager.queryByUserId(user.getId(),
            PageRequest.of(page, size, Sort.Direction.DESC, "gmtCreate"));
        Page<Long> userVotedPollIds = userVoted.map(voteBO -> voteBO.getPoll().getId());

        // Retrieve all poll details from the voted pollIds.
        List<Long> pollIds = userVotedPollIds.getContent();
        List<PollBO> polls = pollManager.queryByIdIn(pollIds);

        // 3. 查找投票结果
        Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
        Map<Long, ChoiceBO> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);

        List<PollVotedBO> pollVotedBOS = polls.stream()
            .map(poll -> mapPollToPollVoted(poll, choiceVoteCountMap, poll.getCreatedBy(),
                pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null)))
            .collect(Collectors.toList());
        return new PageImpl<>(pollVotedBOS, userVotedPollIds.getPageable(),
            userVotedPollIds.getSize());
    }

    /**
     * 根据pollId获得每个choice对应的投票数
     * 
     * @param pollIds
     * @return
     */
    private Map<Long, Long> getChoiceVoteCountMap(List<Long> pollIds) {
        // Retrieve Vote Counts of every Choice belonging to the given pollIds
        List<ChoiceVoteCount> votes = voteManager.countByPollIdInGroupByChoiceId(pollIds);

        Map<Long, Long> choiceVotesMap = votes.stream()
            .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

        return choiceVotesMap;
    }

    /**
     * 根据当前用户和投票列表查找对应投票的选项
     * 
     * @param currentUser
     * @param pollIds
     * @return
     */
    private Map<Long, ChoiceBO> getPollUserVoteMap(UserPrincipal currentUser, List<Long> pollIds) {
        // Retrieve Votes done by the logged in user to the given pollIds
        Map<Long, ChoiceBO> pollUserVoteMap = null;
        if (currentUser != null) {
            List<VoteBO> userVotes = voteManager.queryByUserIdAndPollIdIn(currentUser.getId(),
                pollIds);

            pollUserVoteMap = userVotes.stream()
                .collect(Collectors.toMap(vote -> vote.getPoll().getId(), VoteBO::getChoice));
        }
        return pollUserVoteMap;
    }

    /**
     * 把投票转换成用户投票领域模型
     * 
     * @param poll
     * @param choiceVotesMap
     * @param creator
     * @param selectedChoice
     * @return
     */
    private PollVotedBO mapPollToPollVoted(PollBO poll, Map<Long, Long> choiceVotesMap,
                                           UserBO creator, ChoiceBO selectedChoice) {
        PollVotedBO pollVotedBO = new PollVotedBO();

        pollVotedBO.setPoll(poll);
        pollVotedBO.setChoiceVotesMap(choiceVotesMap);
        pollVotedBO.setUser(creator);
        pollVotedBO.setChoice(selectedChoice);

        return pollVotedBO;
    }

    /**
     * @see org.excellentcoder.tutorial.biz.poll.service.PollService#getPollById(Long, UserPrincipal)
     */
    @Override
    public PollVotedBO getPollById(Long pollId, UserPrincipal currentUser) {
        PollBO poll = pollManager.queryById(pollId)
            .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", pollId));

        // Retrieve Vote Counts of every choice belonging to the current poll
        List<ChoiceVoteCount> votes = voteManager.countByPollIdGroupByChoiceId(pollId);

        Map<Long, Long> choiceVotesMap = votes.stream()
            .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

        // Retrieve poll creator details
        UserBO creator = userManager.queryById(poll.getCreatedBy().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", poll.getCreatedBy()));

        // Retrieve vote done by logged in user
        VoteBO userVote = null;
        if (currentUser != null) {
            userVote = voteManager.queryByUserIdAndPollId(currentUser.getId(), pollId);
        }

        return mapPollToPollVoted(poll, choiceVotesMap, creator,
            userVote != null ? userVote.getChoice() : null);
    }

    /**
     * @see org.excellentcoder.tutorial.biz.poll.service.PollService#castVoteAndGetUpdatedPoll(Long, Long, UserPrincipal)
     */
    @Override
    public PollVotedBO castVoteAndGetUpdatedPoll(Long pollId, Long choiceId,
                                                 UserPrincipal currentUser) {
        PollBO poll = pollManager.queryById(pollId)
            .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", pollId));
        if (DateUtil.isBeforeNow(poll.getGmtExpiration())) {
            throw new BadRequestException("Sorry! This Poll has already expired");
        }

        UserBO user = userManager.queryById(currentUser.getId()).get();

        ChoiceBO selectedChoice = poll.getChoices().stream()
            .filter(choice -> choice.getId().equals(choiceId)).findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Choice", "id", choiceId));

        VoteBO vote = new VoteBO();
        vote.setPoll(poll);
        vote.setUser(user);
        vote.setChoice(selectedChoice);

        try {
            vote = voteManager.insert(vote);
        } catch (DataIntegrityViolationException ex) {
            LoggerUtil.info(logger, "User {0} has already voted in Poll {1}", currentUser.getId(),
                pollId);
            throw new BadRequestException("Sorry! You have already cast your vote in this poll");
        }

        //-- Vote Saved, Return the updated Poll Response now --

        // Retrieve Vote Counts of every choice belonging to the current poll
        List<ChoiceVoteCount> votes = voteManager.countByPollIdGroupByChoiceId(pollId);

        Map<Long, Long> choiceVotesMap = votes.stream()
            .collect(Collectors.toMap(ChoiceVoteCount::getChoiceId, ChoiceVoteCount::getVoteCount));

        // Retrieve poll creator details
        UserBO creator = userManager.queryById(poll.getCreatedBy().getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", poll.getCreatedBy()));

        return mapPollToPollVoted(poll, choiceVotesMap, creator, vote.getChoice());
    }
}