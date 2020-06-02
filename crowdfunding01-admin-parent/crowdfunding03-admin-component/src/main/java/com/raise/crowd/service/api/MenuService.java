package com.raise.crowd.service.api;

import com.raise.crowd.entity.Admin;
import com.raise.crowd.entity.Menu;

import java.util.List;

/**
 * @author zmj
 * @date 2020/5/29 15:15
 * @Description
 */
public interface MenuService {
    /**
     * 查询全部的menu对象
     *
     * @return
     */
    List<Menu> getAll();

    /**
     * 保存子节点
     *
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 更新menu信息
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 通过id删除menu
     * @param id
     */
    void removeMenuById(Integer id);
}
