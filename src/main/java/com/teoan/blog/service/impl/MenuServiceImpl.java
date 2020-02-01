package com.teoan.blog.service.impl;


import com.teoan.blog.entity.Menu;
import com.teoan.blog.mapper.MenuMapper;
import com.teoan.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/1 16:52
 */
public class MenuServiceImpl implements MenuService {

    @Autowired(required = false)
    private MenuMapper menuMapper;


    @Override
    public List<Menu> listMenu() {
        return menuMapper.listMenu();
    }

    @Override
    public void insertMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteById(id);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.update(menu);
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuMapper.getMenuById(id);
    }
}
