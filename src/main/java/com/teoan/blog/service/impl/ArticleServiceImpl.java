package com.teoan.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.*;
import com.teoan.blog.enums.ArticleCommentStatus;
import com.teoan.blog.mapper.ArticleCategoryRefMapper;
import com.teoan.blog.mapper.ArticleMapper;
import com.teoan.blog.mapper.ArticleTagRefMapper;
import com.teoan.blog.service.ArticleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/21 12:23
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired(required = false)
    private ArticleTagRefMapper articleTagRefMapper;


    private Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        try{
            count = articleMapper.countArticle(status);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据状态统计文章数, status:{}, cause:{}", status, e);
        }
        return  count;
    }

    @Override
    public Integer countArticleComment() {
        Integer count = 0;
        try{
            count = articleMapper.countArticleComment();
        }catch (Exception e){
            e.printStackTrace();
            log.error("统计文章评论数失败，cause:{}",e);
        }
        return  count;
    }

    @Override
    public Integer countArticleView() {
        Integer count = 0;
        try {
            count = articleMapper.countArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计文章访问量失败, cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count = 0;
        try{
            count = articleCategoryRefMapper.countArticleByCategoryId(categoryId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据分类统计文章数量失败, categoryId:{}, cause:{}", categoryId, e);
        }
        return count;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        Integer count = 0;
        try{
            count = articleTagRefMapper.countArticleByTagId(tagId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据标签统计文章数量失败, categoryId:{}, cause:{}", tagId, e);
        }
        return count;
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        List<Article> articles=null;
        try{
            articles = articleMapper.findAll(criteria);
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取文章列表失败，cause:{}",e);
        }
        return articles;
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        List<Article> articles=null;
        try{
            articles = articleMapper.listArticleByLimit(limit);
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取文章列表失败，cause:{}",e);
        }
        return articles;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleMapper.update(article);


        if(article.getCategoryList()!=null){
            //删除标签文章的关联
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
            //添加标签文章的关联
            for(int i=0;i<article.getCategoryList().size();i++){
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefMapper.inster(articleCategoryRef);
            }
        }

        if(article.getTagList()!=null){
//            删除文章标签关联
            articleTagRefMapper.deleteByArticleId(article.getArticleId());
//            添加文章标签关联
            for(int i =0;i<article.getTagList().size();i++){
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(),article.getTagList().get(i).getTagId());
                articleTagRefMapper.insert(articleTagRef);
            }
        }
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        articleMapper.deleteBatch(ids);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteById(id);
    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Article> articles = articleMapper.findAll(criteria);

        for(Article article:articles){
            List<Category> categories = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            if (categories==null||categories.size()==0){
                categories = new ArrayList<>();
                categories.add(Category.Default());
            }
            article.setCategoryList(categories);
        }
        return new PageInfo<>(articles);
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        return articleMapper.getArticleByStatusAndId(status, id);
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleMapper.listArticleByViewCount(limit);
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return articleMapper.getAfterArticle(id);
    }

    @Override
    public Article getPreArticle(Integer id) {
        return articleMapper.getPreArticle(id);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleMapper.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleMapper.listArticleByCommentCount(limit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
//        添加文章
        article.setArticleUpdateTime(new Date());
        article.setArticleCreateTime(new Date());
        article.setArticleStatus(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        articleMapper.insert(article);
//        添加分类文章关联
        for(Category category:article.getCategoryList()){
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),category.getCategoryId());
            articleCategoryRefMapper.inster(articleCategoryRef);
        }

//        添加标签文章关联
        for(Tag tag:article.getTagList()){
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(),tag.getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }


    }

    @Override
    public void updateCommentCount(Integer articleId) {
        articleMapper.updateCommentCount(articleId);
    }

    @Override
    public Article getLastUpdateArticle() {
        return articleMapper.getLastUpdateArticle();
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return articleMapper.findArticleByCategoryId(cateId, limit);
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if (cateIds==null||cateIds.size()==0)
            return null;
        return articleMapper.findArticleByCategoryIds(cateIds, limit);
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return articleCategoryRefMapper.selectCategoryIdByArticleId(articleId);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return articleMapper.lisAllNotWithConten();
    }
}
