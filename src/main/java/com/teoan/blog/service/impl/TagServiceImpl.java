package com.teoan.blog.service.impl;

import com.teoan.blog.entity.Tag;
import com.teoan.blog.mapper.ArticleTagRefMapper;
import com.teoan.blog.mapper.TagMapper;
import com.teoan.blog.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 17:12
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired(required = false)
    private TagMapper tagMapper;

    @Autowired(required = false)
    private ArticleTagRefMapper articleTagRefMapper;

    private Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    public Integer countTag() {
        return tagMapper.countTag();
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有标签失败, cause:{}", e);
        }
        return tagList;
    }


    @Override
    public List<Tag> listTagWithCount() {
        List<Tag> tags = null;
        try {
            tags = tagMapper.listTag();
            for (Tag tag:tags){
                Integer count = articleTagRefMapper.countArticleByTagId(tag.getTagId());
                tag.setArticleCount(count);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("获得所有标签失败, cause:{}", e);
        }
        return tags;
    }

    @Override
    public Tag getTagById(Integer id) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagById(id);
        } catch (Exception e) {            e.printStackTrace();
            log.error("根据ID获得标签失败, id:{}, cause:{}", id, e);
        }
        return tag;
    }

    @Override
    public void insertTag(Tag tag) {
        try {
            tagMapper.insert(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加标签失败, tag:{}, cause:{}", tag, e);
        }
    }

    @Override
    public void updateTag(Tag tag) {
        try {
            tagMapper.update(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新标签失败, tag:{}, cause:{}", tag, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Integer id) {
        try {
            tagMapper.deleteById(id);
            articleTagRefMapper.deleteByTagId(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除标签失败, id:{}, cause:{}", id, e);
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    @Override
    public Tag getTagByName(String name) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据名称获得标签, name:{}, cause:{}", name, e);
        }
        return tag;
    }

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        List<Tag> tagList = null;
        try {
            tagList = articleTagRefMapper.listTagByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章ID获得标签失败，articleId:{}, cause:{}", articleId, e);
        }
        return tagList;
    }
}
