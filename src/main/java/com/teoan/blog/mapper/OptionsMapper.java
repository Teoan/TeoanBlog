package com.teoan.blog.mapper;

import com.teoan.blog.entity.Options;

/**
 * @author Teoan
 * @description
 * @date 2020/1/20 12:14
 */
public interface OptionsMapper {

    /**
     * @description: 根据id删除
     * @param:  系统设置id
     * @return:  影响行数
     */
    int deleteById(Integer optionId);


    /**
     * @description: 添加
     * @param:  系统设置
     * @return:  影响行数
     */
    int insert(Options options);

    /**
     * @description: 根据id查询
     * @param:  系统设置id
     * @return:  系统设置
     */
    Options getOptionsById(Integer optionId);

    /**
     * @description: 更新
     * @param:  系统信息
     * @return:  影响行数
     */
    int update(Options options);

    /**
     * @description: 获取记录
     * @param:
     * @return:  系统信息
     */
    Options getOptions();
}
