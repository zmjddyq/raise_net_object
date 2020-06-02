package com.raise.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.raise.crowd.entity.Admin;

import java.util.List;

/**
 * @author zmj
 * @date 2020/5/25 9:02
 * @Description
 */
public interface AdminService {

    /**
     *保存管理员信息
     * @param admin
     * @return Integer影响行数
     */
    Integer saveAdmin(Admin admin);

    /**
     * 获取所有员工
     * @return List<Admin>
     */
    List<Admin> getAllAdmin();

    /**
     * 管理员登入认证
     * @param username
     * @param password
     * @return Admin
     */
    Admin login(String username,String password);

    /**
     * 管理员分页显示
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 通过id删除管理员信息
     * @param adminId
     */
    void removeAdmin(Integer adminId);

    /**
     * 通过id获取管理员信息
     * @param id
     * @return
     */
    Admin getAdminById(Integer id);

    /**
     * 后端校验账号，用户名
     * @param loginAcct
     * @return
     */
    List<Admin> checkLoginAcct(String loginAcct);

    /**
     * 修改管理员信息
     * @param admin
     */
    void updateAdmin(Admin admin);

    /**
     * 根据账号名称查询Admin对象
     * @param username
     * @return
     */
    Admin getAdminByLoginAcct(String username);
}
