package org.excellentcoder.tutorial.common.dal.mapper.manual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.excellentcoder.tutorial.common.dal.dataobject.UserDO;
import org.excellentcoder.tutorial.common.dal.dataobject.UserDOExample;
import org.excellentcoder.tutorial.common.dal.dataobject.UserRolesLinkDO;

@Mapper
public interface ManualUserDOMapper {
    /**
     * 动态查询
     * 
     * @param example
     * @return
     */
    List<UserDO> selectByExample(UserDOExample example);

    /**
     * 根据主键id查询
     * 
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 删除用户角色关系
     *
     * @param link
     * @return
     */
    int deleteUserRoleLink(UserRolesLinkDO link);

    /**
     * 删除角色用户关系
     *
     * @param link
     * @return
     */
    int insertUserRoleLink(UserRolesLinkDO link);
}