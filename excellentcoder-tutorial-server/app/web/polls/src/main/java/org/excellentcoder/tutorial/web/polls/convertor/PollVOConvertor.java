/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.convertor;

import org.excellentcoder.tutorial.common.util.DateUtil;
import org.excellentcoder.tutorial.core.model.poll.ChoiceBO;
import org.excellentcoder.tutorial.core.model.poll.PollBO;
import org.excellentcoder.tutorial.core.model.poll.PollVotedBO;
import org.excellentcoder.tutorial.core.model.poll.UserBO;
import org.excellentcoder.tutorial.web.polls.payload.ChoiceResponseVO;
import org.excellentcoder.tutorial.web.polls.payload.PollRequestVO;
import org.excellentcoder.tutorial.web.polls.payload.PollResponseVO;
import org.excellentcoder.tutorial.web.polls.payload.UserSummary;

import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 投票VO转换器
 * 
 * @author xbyan
 * @version $Id: PollVOConvertor.java, v 0.1 2019-10-11 8:26 PM xbyan Exp $$
 */
public class PollVOConvertor {

    /**
     * 把VO请求转换成领域模型
     *
     * @param pollRequestVO
     * @return
     */
    public static PollBO convertVOToBO(PollRequestVO pollRequestVO) {
        if (pollRequestVO == null) {
            return null;
        }

        PollBO pollBO = new PollBO();
        pollBO.setQuestion(pollRequestVO.getQuestion());

        pollRequestVO.getChoices().forEach(choiceRequest -> {
            pollBO.getChoices().add(new ChoiceBO(choiceRequest.getText()));
        });

        Instant now = Instant.now();
        Instant expirationDateTime = now
            .plus(Duration.ofDays(pollRequestVO.getPollLength().getDays()))
            .plus(Duration.ofHours(pollRequestVO.getPollLength().getHours()));

        pollBO.setGmtExpiration(Date.from(expirationDateTime));

        return pollBO;
    }

    /**
     * DO批量转领域模型
     *
     * @param pollBOS DO列表
     * @return 领域模型列表
     */
    public static List<PollResponseVO> convertBOToVO(List<PollVotedBO> pollBOS) {
        if (pollBOS == null) {
            return null;
        }

        List<PollResponseVO> pollResponseVOS = new ArrayList<>();

        for (PollVotedBO pollBO : pollBOS) {

            PollResponseVO vo = convertBOToVO(pollBO);
            if (null == vo) {
                continue;
            }

            pollResponseVOS.add(vo);
        }

        return pollResponseVOS;
    }

    /**
     * 把VO请求转换成领域模型
     *
     * @param pollVotedBO
     * @return
     */
    public static PollResponseVO convertBOToVO(PollVotedBO pollVotedBO) {
        if (pollVotedBO == null) {
            return null;
        }

        PollResponseVO pollResponseVO = new PollResponseVO();

        PollBO pollBO = pollVotedBO.getPoll();
        pollResponseVO.setId(pollBO.getId());
        pollResponseVO.setQuestion(pollBO.getQuestion());
        pollResponseVO.setGmtCreate(pollBO.getGmtCreate());
        pollResponseVO.setGmtExpiration(pollBO.getGmtExpiration());
        pollResponseVO.setExpired(DateUtil.isBeforeNow(pollBO.getGmtExpiration()));

        List<ChoiceResponseVO> choiceResponses = pollVotedBO.getPoll().getChoices().stream()
            .map(choice -> {
                ChoiceResponseVO choiceResponse = new ChoiceResponseVO();
                choiceResponse.setId(choice.getId());
                choiceResponse.setText(choice.getText());

                // 统计每一个选项投票数
                Map<Long, Long> choiceVotesMap = pollVotedBO.getChoiceVotesMap();
                if (choiceVotesMap.containsKey(choice.getId())) {
                    choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
                } else {
                    choiceResponse.setVoteCount(0);
                }
                return choiceResponse;

            }).collect(Collectors.toList());

        pollResponseVO.setChoices(choiceResponses);

        UserBO creator = pollVotedBO.getUser();
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(),
            creator.getName());
        pollResponseVO.setCreatedBy(creatorSummary);

        ChoiceBO choice = pollVotedBO.getChoice();
        if (choice != null) {
            pollResponseVO.setSelectedChoice(choice.getId());
        }

        // 总投票数
        long totalVotes = pollResponseVO.getChoices().stream()
            .mapToLong(ChoiceResponseVO::getVoteCount).sum();
        pollResponseVO.setTotalVotes(totalVotes);

        return pollResponseVO;
    }
}