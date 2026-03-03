package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewCostStatisticsMapper;
import com.jiuchang.system.domain.BrewCostStatistics;
import com.jiuchang.system.service.IBrewCostStatisticsService;

/**
 * ?本统计Service?????
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
     * ???本统计
     * 
     * @param statId ?本统计??
     * @return ?本统计
     */
    @Override
    public BrewCostStatistics selectBrewCostStatisticsByStatId(Long statId)
    {
        return brewCostStatisticsMapper.selectBrewCostStatisticsByStatId(statId);
    }

    /**
     * ???本统计??
     * 
     * @param brewCostStatistics ?本统计
     * @return ?本统计
     */
    @Override
    public List<BrewCostStatistics> selectBrewCostStatisticsList(BrewCostStatistics brewCostStatistics)
    {
        return brewCostStatisticsMapper.selectBrewCostStatisticsList(brewCostStatistics);
    }

    /**
     * ???本统计
     * 
     * @param brewCostStatistics ?本统计
     * @return ??
     */
    @Override
    public int insertBrewCostStatistics(BrewCostStatistics brewCostStatistics)
    {
        brewCostStatistics.setCreateTime(DateUtils.getNowDate());
        return brewCostStatisticsMapper.insertBrewCostStatistics(brewCostStatistics);
    }

    /**
     * ???本统计
     * 
     * @param brewCostStatistics ?本统计
     * @return ??
     */
    @Override
    public int updateBrewCostStatistics(BrewCostStatistics brewCostStatistics)
    {
        brewCostStatistics.setUpdateTime(DateUtils.getNowDate());
        return brewCostStatisticsMapper.updateBrewCostStatistics(brewCostStatistics);
    }

    /**
     * ?????本统计
     * 
     * @param statIds ??????本统计??
     * @return ??
     */
    @Override
    public int deleteBrewCostStatisticsByStatIds(Long[] statIds)
    {
        return brewCostStatisticsMapper.deleteBrewCostStatisticsByStatIds(statIds);
    }

    /**
     * ???本统计??
     * 
     * @param statId ?本统计??
     * @return ??
     */
    @Override
    public int deleteBrewCostStatisticsByStatId(Long statId)
    {
        return brewCostStatisticsMapper.deleteBrewCostStatisticsByStatId(statId);
    }
}
