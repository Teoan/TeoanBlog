package com.teoan.blog.service;

import com.teoan.blog.entity.Options;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 12:06
 */
public interface OptionsService {

    /**
     * @Description:  获得基本信息
     * @Param:
     * @return:  系统设置
     **/
    Options getOptions();


    /**
     * @Description:  新建基本信息
     * @Param:  系统设置
     * @return:
     **/
    void insertOptions(Options options);


    /**
     * @Description:  更新基本信息
     * @Param:  系统设置
     * @return:
     **/
    void updateOptions(Options options);
}
