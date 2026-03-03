package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewProcessMonitor;

/**
 * 生产过程监控Mapper接口
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public interface BrewProcessMonitorMapper 
{
    /**
     * 查询生产过程监控
     * 
     * @param monitorId 生产过程监控主键
     * @return 生产过程监控
     */
    public BrewProcessMonitor selectBrewProcessMonitorByMonitorId(Long monitorId);

    /**
     * 查询生产过程监控列表
     * 
     * @param brewProcessMonitor 生产过程监控
     * @return 生产过程监控集合
     */
    public List<BrewProcessMonitor> selectBrewProcessMonitorList(BrewProcessMonitor brewProcessMonitor);

    /**
     * 新增生产过程监控
     * 
     * @param brewProcessMonitor 生产过程监控
     * @return 结果
     */
    public int insertBrewProcessMonitor(BrewProcessMonitor brewProcessMonitor);

    /**
     * 修改生产过程监控
     * 
     * @param brewProcessMonitor 生产过程监控
     * @return 结果
     */
    public int updateBrewProcessMonitor(BrewProcessMonitor brewProcessMonitor);

    /**
     * 删除生产过程监控
     * 
     * @param monitorId 生产过程监控主键
     * @return 结果
     */
    public int deleteBrewProcessMonitorByMonitorId(Long monitorId);

    /**
     * 批量删除生产过程监控
     * 
     * @param monitorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewProcessMonitorByMonitorIds(Long[] monitorIds);
}
