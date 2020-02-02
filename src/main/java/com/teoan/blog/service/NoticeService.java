package com.teoan.blog.service;

import com.teoan.blog.entity.Notice;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 11:43
 */

public interface NoticeService {


    /**
     * @Description:  获得公告列表
     * @Param:  状态
     * @return:  公告列表
     **/
    List<Notice> listNotice(Integer status);



    /**
     * @Description: 添加公告
     * @Param:  公告
     * @return:
     **/
    void insertNotice(Notice notice);



    /**
     * @Description: 删除公告
     * @Param:  公告id
     * @return:
     **/
    void deleteNotice(Integer id);


    /**
     * @Description:  更新公告
     * @Param:  公告
     * @return:
     **/
    void updateNotice(Notice notice);


    /**
     * @Description:  根据id查询公告
     * @Param:  id
     * @return:  公告
     **/
    Notice getNoticeById(Integer id);
}
