package com.teoan.blog.service.impl;

import com.teoan.blog.entity.Page;
import com.teoan.blog.mapper.PageMapper;
import com.teoan.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 16:59
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired(required = false)
    private PageMapper pageMapper;

    @Override
    public List<Page> listPage(Integer status) {
        return pageMapper.listPage(status);
    }

    @Override
    public Page getPageByKey(Integer status, String key) {
        return pageMapper.getPageByKey(status,key);
    }

    @Override
    public Page getPageById(Integer id) {
        return pageMapper.getPageById(id);
    }

    @Override
    public void insertPage(Page page) {
        pageMapper.insert(page);
    }

    @Override
    public void deletePage(Integer id) {
        pageMapper.deleteById(id);
    }

    @Override
    public void updatePage(Page page) {
        pageMapper.update(page);
    }
}
