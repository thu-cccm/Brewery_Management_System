package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewCostStatisticsMapper;
import com.jiuchang.system.domain.BrewCostStatistics;
import com.jiuchang.system.service.IBrewCostStatisticsService;

/**
 * æˆæœ¬ç»Ÿè®¡Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewCostStatisticsServiceImpl implements IBrewCostStatisticsService 
{
    @Autowired
    private BrewCostStatisticsMapper brewCostStatisticsMapper;

    /**
     * 查询æˆæœ¬ç»Ÿè®¡
     * 
     * @param statId æˆæœ¬ç»Ÿè®¡主键
     * @return æˆæœ¬ç»Ÿè®¡
     */
    @Override
    public BrewCostStatistics selectBrewCostStatisticsByStatId(Long statId)
    {
        return brewCostStatisticsMapper.selectBrewCostStatisticsByStatId(statId);
    }

    /**
     * 查询æˆæœ¬ç»Ÿè®¡列表
     * 
     * @param brewCostStatistics æˆæœ¬ç»Ÿè®¡
     * @return æˆæœ¬ç»Ÿè®¡
     */
    @Override
    public List<BrewCostStatistics> selectBrewCostStatisticsList(BrewCostStatistics brewCostStatistics)
    {
        return brewCostStatisticsMapper.selectBrewCostStatisticsList(brewCostStatistics);
    }

    /**
     * 新增æˆæœ¬ç»Ÿè®¡
     * 
     * @param brewCostStatistics æˆæœ¬ç»Ÿè®¡
     * @return 结果
     */
    @Override
    public int insertBrewCostStatistics(BrewCostStatistics brewCostStatistics)
    {
        brewCostStatistics.setCreateTime(DateUtils.getNowDate());
        return brewCostStatisticsMapper.insertBrewCostStatistics(brewCostStatistics);
    }

    /**
     * 修改æˆæœ¬ç»Ÿè®¡
     * 
     * @param brewCostStatistics æˆæœ¬ç»Ÿè®¡
     * @return 结果
     */
    @Override
    public int updateBrewCostStatistics(BrewCostStatistics brewCostStatistics)
    {
        brewCostStatistics.setUpdateTime(DateUtils.getNowDate());
        return brewCostStatisticsMapper.updateBrewCostStatistics(brewCostStatistics);
    }

    /**
     * 批量删除æˆæœ¬ç»Ÿè®¡
     * 
     * @param statIds 需要删除的æˆæœ¬ç»Ÿè®¡主键
     * @return 结果
     */
    @Override
    public int deleteBrewCostStatisticsByStatIds(Long[] statIds)
    {
        return brewCostStatisticsMapper.deleteBrewCostStatisticsByStatIds(statIds);
    }

    /**
     * 删除æˆæœ¬ç»Ÿè®¡信息
     * 
     * @param statId æˆæœ¬ç»Ÿè®¡主键
     * @return 结果
     */
    @Override
    public int deleteBrewCostStatisticsByStatId(Long statId)
    {
        return brewCostStatisticsMapper.deleteBrewCostStatisticsByStatId(statId);
    }
}
