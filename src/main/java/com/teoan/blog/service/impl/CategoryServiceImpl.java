package com.teoan.blog.service.impl;

import com.teoan.blog.entity.Category;
import com.teoan.blog.mapper.ArticleCategoryRefMapper;
import com.teoan.blog.mapper.ArticleTagRefMapper;
import com.teoan.blog.mapper.CategoryMapper;
import com.teoan.blog.service.CategoryService;
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
 * @date 2020/1/23 21:26
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    private Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    public Integer countCategory() {
        Integer count = 0;
        try{
            count = categoryMapper.countCategory();
        }catch (Exception e){
            e.printStackTrace();
            log.error("统计分类失败,cause:{}",e);
        }
        return count ;
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categories = null;
       try{
           categories =categoryMapper.listCategort();
       }catch (Exception e){
           e.printStackTrace();
           log.error("获取文章列表失败,cause:{}",e);
       }
        return categories ;
    }

    @Override
    public List<Category> listCategoryWithCount() {
        List<Category> categories = null;
        try{
            categories = categoryMapper.listCategort();
            for(Category category:categories){
                int count = articleCategoryRefMapper.countArticleByCategoryId(category.getCategoryId());
                category.setArticleCount(count);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据文章获得分类列表失败, cause:{}", e);
        }
        return categories;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Integer id) {
        try {
            categoryMapper.deleteCategory(id);
            articleCategoryRefMapper.deleteByCategoryId(id);
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除分类失败, id:{}, cause:{}", id, e);
//            设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = null;
        try {
            category = categoryMapper.getCategoryById(id);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据分类ID获得分类, id:{}, cause:{}", id, e);
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryMapper.update(category);
        }catch (Exception e){
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }

    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            category = categoryMapper.getCategpryByName(name);
        }catch (Exception e){
            e.printStackTrace();
            log.error("更新分类失败, category:{}, cause:{}", category, e);
        }
        return category;
    }

    @Override
    public void insertCategory(Category category) {
        try{
            categoryMapper.insert(category);
        }catch (Exception e){
            e.printStackTrace();
            log.error("创建分类失败, category:{}, cause:{}", category, e);
        }

    }
}
