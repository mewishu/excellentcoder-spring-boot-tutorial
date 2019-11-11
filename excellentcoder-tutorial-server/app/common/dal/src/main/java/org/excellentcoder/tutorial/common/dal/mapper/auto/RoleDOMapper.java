package org.excellentcoder.tutorial.common.dal.mapper.auto;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.excellentcoder.tutorial.common.dal.dataobject.RoleDO;
import org.excellentcoder.tutorial.common.dal.dataobject.RoleDOExample;

@Mapper
public interface RoleDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    int insert(RoleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    int insertSelective(RoleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    List<RoleDO> selectByExampleWithRowbounds(RoleDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    List<RoleDO> selectByExample(RoleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    RoleDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RoleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleDO record);
}