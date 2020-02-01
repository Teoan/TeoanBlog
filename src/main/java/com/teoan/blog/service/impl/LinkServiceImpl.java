package com.teoan.blog.service.impl;

import com.teoan.blog.entity.Link;
import com.teoan.blog.mapper.LinkMapper;
import com.teoan.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/1 16:42
 */
public class LinkServiceImpl implements LinkService {

    @Autowired(required = false)
    private LinkMapper linkMapper;

    @Override
    public Integer countLink(Integer status) {
        return linkMapper.countLink(status);
    }

    @Override
    public List<Link> listLink(Integer status) {
        return linkMapper.listLink(status);
    }

    @Override
    public void insertLink(Link link) {
        linkMapper.insert(link);
    }

    @Override
    public void deleteLink(Integer id) {
        linkMapper.deleteById(id);
    }

    @Override
    public void updateLink(Link link) {
        linkMapper.update(link);
    }

    @Override
    public Link getLinkById(Integer id) {
        return linkMapper.getLinkById(id);
    }
}
