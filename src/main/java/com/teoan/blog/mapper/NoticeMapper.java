package com.teoan.blog.mapper;

import com.teoan.blog.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/19 18:03
 */
@Mapper
public interface NoticeMapper {

    /**
     * @description: 根据id删除
     * @param:  公告id
     * @return:  影响行数
     */
    int deleteById(Integer noticeId);
    /**
     * @description: 添加
     * @param:  公告
     * @return:  影响行数
     */
    int insert(Notice notice);

    /**
     * @description: 根据id查询
     * @param:  公告id
     * @return:  公告
     */
    Notice getNoticeById(Integer noticeId);

    /**
     * @description:  更新公告
     * @param:  公告
     * @return:  影响行数
     */
    int update(Notice notice);


    /**
     * @description: 获取公告列表
     * @param:
     * @return:  公告列表
     */
    List<Notice> listNotice(@Param(value = "status") Integer status);

}
