package org.excellentcoder.tutorial.common.dal.mapper.manual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.excellentcoder.tutorial.common.dal.dataobject.ChoiceVoteCount;
import org.excellentcoder.tutorial.common.dal.dataobject.VoteDO;

@Mapper
public interface ManualVoteDOMapper {

    /**
     * 根据userId查找投票结果
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM `votes` where user_id = #{userId} limit #{offset}, #{pageSize}")
    @Results({ @Result(property = "user", column = "user_id", one = @One(select = "org.excellentcoder.tutorial.common.dal.mapper.auto.UserDOMapper.selectByPrimaryKey")),
               @Result(property = "poll", column = "poll_id", one = @One(select = "org.excellentcoder.tutorial.common.dal.mapper.auto.PollDOMapper.selectByPrimaryKey")),
               @Result(property = "choice", column = "choice_id", one = @One(select = "org.excellentcoder.tutorial.common.dal.mapper.auto.ChoiceDOMapper.selectByPrimaryKey")), })
    List<VoteDO> queryByUserId(@Param("userId") Long userId, @Param("offset") long offset,
                               @Param("pageSize") int pageSize);

    /**
     * 根据userId和pollId查找投票结果
     * 
     * @param userId
     * @param pollId
     * @return
     */
    @Select("SELECT * FROM `votes` where user_id = #{userId} and poll_id = #{pollId}")
    @Results({ @Result(property = "user", column = "user_id", one = @One(select = "org.excellentcoder.tutorial.common.dal.mapper.auto.UserDOMapper.selectByPrimaryKey")),
               @Result(property = "poll", column = "poll_id", one = @One(select = "org.excellentcoder.tutorial.common.dal.mapper.auto.PollDOMapper.selectByPrimaryKey")),
               @Result(property = "choice", column = "choice_id", one = @One(select = "org.excellentcoder.tutorial.common.dal.mapper.auto.ChoiceDOMapper.selectByPrimaryKey")), })
    VoteDO queryByUserIdAndPollId(@Param("userId") Long userId, @Param("pollId") Long pollId);

    /**
     * 根据userId和pollIdList查找投票结果
     *
     * @param userId
     * @param pollIds
     * @return
     */
    List<VoteDO> queryByUserIdAndPollIdIn(@Param("userId") Long userId,
                                          @Param("pollIds") List<Long> pollIds);

    /**
     * 根据pollId统计每个choice的个数
     *
     * @param pollId
     * @return
     */
    List<ChoiceVoteCount> countByPollIdGroupByChoiceId(@Param("pollId") Long pollId);

    /**
     * 根据pollIdList统计每个choice的个数
     *
     * @param pollIds
     * @return
     */
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);

}