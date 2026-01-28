package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewCostStatistics;

/**
 * æˆæœ¬ç»Ÿè®¡Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewCostStatisticsMapper 
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
     * 删除æˆæœ¬ç»Ÿè®¡
     * 
     * @param statId æˆæœ¬ç»Ÿè®¡主键
     * @return 结果
     */
    public int deleteBrewCostStatisticsByStatId(Long statId);

    /**
     * 批量删除æˆæœ¬ç»Ÿè®¡
     * 
     * @param statIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewCostStatisticsByStatIds(Long[] statIds);
}
