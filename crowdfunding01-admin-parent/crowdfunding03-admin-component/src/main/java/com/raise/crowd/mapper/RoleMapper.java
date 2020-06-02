package com.raise.crowd.mapper;

import com.raise.crowd.entity.Role;
import com.raise.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByKeyword(String keyword);

    List<Role> getAssignedRoleListByAdminId(Integer adminId);

    List<Role> getUnassignedRoleListByAdminId(Integer adminId);

    void deleteAssignedRole(Integer adminId);

    void insertAssignedRole(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
}