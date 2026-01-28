package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewCostStatistics;

/**
 * ?本统计Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewCostStatisticsMapper 
{
    /**
     * ???本统计
     * 
     * @param statId ?本统计??
     * @return ?本统计
     */
    public BrewCostStatistics selectBrewCostStatisticsByStatId(Long statId);

    /**
     * ???本统计??
     * 
     * @param brewCostStatistics ?本统计
     * @return ?本统计??
     */
    public List<BrewCostStatistics> selectBrewCostStatisticsList(BrewCostStatistics brewCostStatistics);

    /**
     * ???本统计
     * 
     * @param brewCostStatistics ?本统计
     * @return ??
     */
    public int insertBrewCostStatistics(BrewCostStatistics brewCostStatistics);

    /**
     * ???本统计
     * 
     * @param brewCostStatistics ?本统计
     * @return ??
     */
    public int updateBrewCostStatistics(BrewCostStatistics brewCostStatistics);

    /**
     * ???本统计
     * 
     * @param statId ?本统计??
     * @return ??
     */
    public int deleteBrewCostStatisticsByStatId(Long statId);

    /**
     * ?????本统计
     * 
     * @param statIds ???????????
     * @return ??
     */
    public int deleteBrewCostStatisticsByStatIds(Long[] statIds);
}
