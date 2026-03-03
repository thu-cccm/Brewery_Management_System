package com.jiuchang.system.service;

import java.util.List;
import java.util.Map;

public interface IBrewStatisticsService 
{
    Map<String, Object> getCostTrend(Integer months);
    
    List<Map<String, Object>> getMaterialWarningList();
    
    Map<String, Object> getProductionCompare(Integer months);
    
    Map<String, Object> getDashboardData();

    /** 获取质量稳定性分析 */
    Map<String, Object> getQualityStability(String categoryCode);

    /** 获取生产效率分析（产酒率对比） */
    List<Map<String, Object>> getProductionEfficiency();

    /** 获取实时生产进度监控 */
    List<Map<String, Object>> getProductionProgress();

    /** 获取生产概览统计（批次状态分布） */
    Map<String, Object> getProductionStats();

    /** 获取损耗分析统计 */
    Map<String, Object> getYieldStats();
}
