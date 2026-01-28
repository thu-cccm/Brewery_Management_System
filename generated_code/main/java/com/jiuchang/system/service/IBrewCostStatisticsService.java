package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewCostStatistics;

/**
 * æˆæœ¬ç»Ÿè®¡Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewCostStatisticsService 
{
    /**
     * 查询æˆæœ¬ç»Ÿè®¡
     * 
     * @param statId æˆæœ¬ç»Ÿè®¡主键
     * @return æˆæœ¬ç»Ÿè®¡
     */
    public BrewCostStatistics selectBrewCostStatisticsByStatId(Long statId);

    /**
     * 查询æˆæœ¬ç»Ÿè®¡列表
     * 
     * @param brewCostStatistics æˆæœ¬ç»Ÿè®¡
     * @return æˆæœ¬ç»Ÿè®¡集合
     */
    public List<BrewCostStatistics> selectBrewCostStatisticsList(BrewCostStatistics brewCostStatistics);

    /**
     * 新增æˆæœ¬ç»Ÿè®¡
     * 
     * @param brewCostStatistics æˆæœ¬ç»Ÿè®¡
     * @return 结果
     */
    public int insertBrewCostStatistics(BrewCostStatistics brewCostStatistics);

    /**
     * 修改æˆæœ¬ç»Ÿè®¡
     * 
     * @param brewCostStatistics æˆæœ¬ç»Ÿè®¡
     * @return 结果
     */
    public int updateBrewCostStatistics(BrewCostStatistics brewCostStatistics);

    /**
     * 批量删除æˆæœ¬ç»Ÿè®¡
     * 
     * @param statIds 需要删除的æˆæœ¬ç»Ÿè®¡主键集合
     * @return 结果
     */
    public int deleteBrewCostStatisticsByStatIds(Long[] statIds);

    /**
     * 删除æˆæœ¬ç»Ÿè®¡信息
     * 
     * @param statId æˆæœ¬ç»Ÿè®¡主键
     * @return 结果
     */
    public int deleteBrewCostStatisticsByStatId(Long statId);
}
