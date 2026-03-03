package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewProcessMonitorMapper;
import com.jiuchang.system.domain.BrewProcessMonitor;
import com.jiuchang.system.service.IBrewProcessMonitorService;

/**
 * 生产过程监控Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@Service
public class BrewProcessMonitorServiceImpl implements IBrewProcessMonitorService 
{
    @Autowired
    private BrewProcessMonitorMapper brewProcessMonitorMapper;

    /**
     * 查询生产过程监控
     * 
     * @param monitorId 生产过程监控主键
     * @return 生产过程监控
     */
    @Override
    public BrewProcessMonitor selectBrewProcessMonitorByMonitorId(Long monitorId)
    {
        return brewProcessMonitorMapper.selectBrewProcessMonitorByMonitorId(monitorId);
    }

    /**
     * 查询生产过程监控列表
     * 
     * @param brewProcessMonitor 生产过程监控
     * @return 生产过程监控
     */
    @Override
    public List<BrewProcessMonitor> selectBrewProcessMonitorList(BrewProcessMonitor brewProcessMonitor)
    {
        return brewProcessMonitorMapper.selectBrewProcessMonitorList(brewProcessMonitor);
    }

    /**
     * 新增生产过程监控
     * 
     * @param brewProcessMonitor 生产过程监控
     * @return 结果
     */
    @Override
    public int insertBrewProcessMonitor(BrewProcessMonitor brewProcessMonitor)
    {
        brewProcessMonitor.setCreateTime(DateUtils.getNowDate());
        return brewProcessMonitorMapper.insertBrewProcessMonitor(brewProcessMonitor);
    }

    /**
     * 修改生产过程监控
     * 
     * @param brewProcessMonitor 生产过程监控
     * @return 结果
     */
    @Override
    public int updateBrewProcessMonitor(BrewProcessMonitor brewProcessMonitor)
    {
        return brewProcessMonitorMapper.updateBrewProcessMonitor(brewProcessMonitor);
    }

    /**
     * 批量删除生产过程监控
     * 
     * @param monitorIds 需要删除的生产过程监控主键
     * @return 结果
     */
    @Override
    public int deleteBrewProcessMonitorByMonitorIds(Long[] monitorIds)
    {
        return brewProcessMonitorMapper.deleteBrewProcessMonitorByMonitorIds(monitorIds);
    }

    /**
     * 删除生产过程监控信息
     * 
     * @param monitorId 生产过程监控主键
     * @return 结果
     */
    @Override
    public int deleteBrewProcessMonitorByMonitorId(Long monitorId)
    {
        return brewProcessMonitorMapper.deleteBrewProcessMonitorByMonitorId(monitorId);
    }
}
