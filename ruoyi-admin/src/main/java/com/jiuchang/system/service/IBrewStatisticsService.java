package com.jiuchang.system.service;

import java.util.List;
import java.util.Map;

public interface IBrewStatisticsService 
{
    Map<String, Object> getCostTrend(Integer months);
    
    List<Map<String, Object>> getMaterialWarningList();
    
    Map<String, Object> getProductionCompare(Integer months);
    
    Map<String, Object> getDashboardData();
}
