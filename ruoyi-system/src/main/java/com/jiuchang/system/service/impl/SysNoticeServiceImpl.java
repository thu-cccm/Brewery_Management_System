package com.jiuchang.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.domain.SysNotice;
import com.jiuchang.system.mapper.SysNoticeMapper;
import com.jiuchang.system.service.ISysNoticeService;

/**
 * 通知公告表 服务层实现
 * 
 * @author jiuchang
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询通知公告信息
     * 
     * @param noticeId 通知公告ID
     * @return 通知公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询通知公告列表
     * 
     * @param notice 通知公告信息
     * @return 通知公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增通知公告
     * 
     * @param notice 通知公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改通知公告
     * 
     * @param notice 通知公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除通知公告对象
     * 
     * @param noticeId 通知公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除通知公告信息
     * 
     * @param noticeIds 需要删除的通知公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }
}
