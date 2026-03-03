package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.SysNotice;

/**
 * 通知公告表 服务层
 * 
 * @author jiuchang
 */
public interface ISysNoticeService
{
    /**
     * 查询通知公告信息
     * 
     * @param noticeId 通知公告ID
     * @return 通知公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询通知公告列表
     * 
     * @param notice 通知公告信息
     * @return 通知公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增通知公告
     * 
     * @param notice 通知公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改通知公告
     * 
     * @param notice 通知公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 删除通知公告信息
     * 
     * @param noticeId 通知公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除通知公告信息
     * 
     * @param noticeIds 需要删除的通知公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}
