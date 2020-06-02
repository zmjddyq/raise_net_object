package com.raise.crowd.mapper;

import com.raise.crowd.entity.Auth;
import com.raise.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> getAssignedAuthIdByRoleId(@Param("roleId") Integer roleId);

    void deleteRoleAssignAuthOldByRoleId(Integer roleId);

    void insertRoleAssignAuthByRoleId(@Param("roleId") Integer roleId,@Param("authIdList") List<Integer> authIdList);

    List<String> selectAssignedAuthNameByAdminId(Integer adminId);
}