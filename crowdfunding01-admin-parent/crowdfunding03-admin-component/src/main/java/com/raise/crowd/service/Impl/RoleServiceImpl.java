package com.raise.crowd.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raise.crowd.entity.Role;
import com.raise.crowd.entity.RoleExample;
import com.raise.crowd.mapper.RoleMapper;
import com.raise.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zmj
 * @date 2020/5/28 20:15
 * @Description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.开启分页方法
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行分页查询
        List<Role> roles = roleMapper.selectByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<Role>(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteByRoleIdArray(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignedRoleListByAdminId(Integer adminId) {
        List<Role> assignedRoleListByAdminId = roleMapper.getAssignedRoleListByAdminId(adminId);
        return assignedRoleListByAdminId;
    }

    @Override
    public List<Role> getUnassignedRoleListByAdminId(Integer adminId) {
        List<Role> unassignedRoleListByAdminId = roleMapper.getUnassignedRoleListByAdminId(adminId);
        return unassignedRoleListByAdminId;
    }

    @Override
    public void saveAssignedRole(Integer adminId, List<Integer> roleIdList) {
        // 1.通过id删除原有的权限
        roleMapper.deleteAssignedRole(adminId);
        // 2.添加新的权限
        if (roleIdList != null && roleIdList.size() > 0) {
            roleMapper.insertAssignedRole(adminId,roleIdList);
        }
    }
}
