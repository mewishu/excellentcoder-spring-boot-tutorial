package org.excellentcoder.tutorial.common.dal.mapper.auto;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.excellentcoder.tutorial.common.dal.dataobject.VoteDO;
import org.excellentcoder.tutorial.common.dal.dataobject.VoteDOExample;

@Mapper
public interface VoteDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    long countByExample(VoteDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    int insert(VoteDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    int insertSelective(VoteDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    List<VoteDO> selectByExampleWithRowbounds(VoteDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    List<VoteDO> selectByExample(VoteDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    VoteDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VoteDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table votes
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VoteDO record);
}