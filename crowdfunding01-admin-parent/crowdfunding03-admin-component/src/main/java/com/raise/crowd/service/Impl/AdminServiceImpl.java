package com.raise.crowd.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raise.crowd.constant.CrowdConstant;
import com.raise.crowd.entity.Admin;
import com.raise.crowd.entity.AdminExample;
import com.raise.crowd.exception.LoginAcctAlreadyInUseException;
import com.raise.crowd.exception.LoginFailedException;
import com.raise.crowd.mapper.AdminMapper;
import com.raise.crowd.service.api.AdminService;
import com.raise.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zmj
 * @date 2020/5/25 9:02
 * @Description
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public Integer saveAdmin(Admin admin) {
        // 设置密码盐值加密
        admin.setUserPswd(passwordEncoder.encode(admin.getUserPswd()));
        // 设置保存时间
        admin.setCreateTime(CrowdUtil.getDateTime());
        // 保存账号信息
        try {
            int insert = adminMapper.insert(admin);
            return insert;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常全类名="+e.getClass().getName());

            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin login(String username, String password) {
        // 1.通过用户名获取管理员信息
        List<Admin> admins = checkLoginAcct(username);
        // 2.用户名不存在返回抛出账号或者密码错误异常
        if (admins.isEmpty()) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 3.用户名存在对密码进行加密比对
        Admin admin = admins.get(0);
        if (!CrowdUtil.md5(password).equals(admin.getUserPswd())) {
            // 4.密码错误返回抛出账号或者密码错误异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 5.密码正确返回管理员信息
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.开启分页方法
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行分页查询
        List<Admin> admins = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<Admin>(admins);
    }

    @Override
    public void removeAdmin(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public List<Admin> checkLoginAcct(String LoginAcct) {
        // 1.通过用户名获取管理员信息
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(LoginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return admins;
    }

    @Override
    public void updateAdmin(Admin admin) {
        // 通过id有选择地进行字段更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常全类名="+e.getClass().getName());

            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public Admin getAdminByLoginAcct(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return admins.get(0);
    }


}
