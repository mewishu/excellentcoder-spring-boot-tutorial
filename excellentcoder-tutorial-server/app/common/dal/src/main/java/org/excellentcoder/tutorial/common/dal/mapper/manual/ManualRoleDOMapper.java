package org.excellentcoder.tutorial.common.dal.mapper.manual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.excellentcoder.tutorial.common.dal.dataobject.RoleDO;
import org.excellentcoder.tutorial.common.dal.dataobject.RoleDOExample;
import org.excellentcoder.tutorial.common.dal.dataobject.UserRolesLinkDO;

@Mapper
public interface ManualRoleDOMapper {
    /**
     * 动态条件查询
     * 
     * @param example
     * @return
     */
    List<RoleDO> selectByExample(RoleDOExample example);

    /**
     * 根据主键id查询
     * 
     * @param id
     * @return
     */
    RoleDO selectByPrimaryKey(Long id);

    /**
     * 删除角色用户关系
     *
     * @param link
     * @return
     */
    int deleteRoleUserLink(UserRolesLinkDO link);

    /**
     * 插入角色用户关系
     * 
     * @param link
     * @return
     */
    int insertRoleUserLink(UserRolesLinkDO link);
}