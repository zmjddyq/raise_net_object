package com.raise.crowd.mvc.controller;


import com.raise.crowd.entity.Menu;
import com.raise.crowd.service.api.MenuService;
import com.raise.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zmj
 * @date 2020/5/29 15:14
 * @Description
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("admin/removeMenu.json")
    public ResultEntity<String> removeMenu(Integer id){
        menuService.removeMenuById(id);
        return ResultEntity.successWithoutData();
    }
    /**
     * 更新menu信息
     * @param menu
     * @return
     */
    @PostMapping("admin/updateMenu.json")
    public ResultEntity<String> updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }
    /**
     * 保存子节点
     * @param menu
     * @return
     */
    @PostMapping("admin/saveMenu.json")
    public ResultEntity<String> saveMenu(Menu menu){
        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }
    /**初始化FatherMenuMap容量*/
    static final int FATHER_MENU_MAP_INITIAL_CAPACITY = (int)(30 / 0.75 + 1);
    /**
     * 获取目录树结构(性能改良)
     * @return
     */
    @PostMapping("/admin/getWholeMenuTree.json")
    public ResultEntity<Menu> getWholeTree(){

        List<Menu> menuList = menuService.getAll();
        Menu root = null;
        Map<Integer,Menu> fatherMenuMap = new HashMap<>(FATHER_MENU_MAP_INITIAL_CAPACITY);

        for (Menu menu : menuList) {
            fatherMenuMap.put(menu.getId(),menu);
        }

        for (Menu childrenMenu : menuList) {
            Integer pid = childrenMenu.getPid();
            if (pid == null){
                root = childrenMenu;
                continue;
            }
            Menu father = fatherMenuMap.get(pid);
            father.getChildren().add(childrenMenu);
        }
        return ResultEntity.successWithData(root);

    }

    /**
     * 获取目录树结构
     * @return
     */
    @Deprecated
    public ResultEntity<Menu> getWholeTreeOld() {

        // 1.查询全部的menu对象
        List<Menu> menuList = menuService.getAll();

        // 2.声明一个变量用来储存找到的根节点
        Menu root = null;

        // 3.遍历menulist
        for (Menu menu : menuList) {
            // 4.获取当前menu对象的pid属性值
            Integer pid = menu.getPid();

            // 5.检查是否为null
            if (pid == null) {

                // 6.把当前正在遍历的这个menu对赋值给root
                root = menu;

                // 7.停止本次循环
                continue;

            }

            // 8.不为根节点
            for (Menu father : menuList) {
                // 9.获取疑似父节点的id
                Integer id = father.getId();
                // 10.id比对
                if (Objects.equals(id, pid)) {
                    // 11.给父节点添加子
                    father.getChildren().add(menu);
                    // 12.跳出循环
                    break;
                }

            }

        }
        return ResultEntity.successWithData(root);
    }

}
