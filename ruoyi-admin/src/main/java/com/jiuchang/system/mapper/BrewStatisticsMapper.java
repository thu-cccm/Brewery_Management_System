package com.jiuchang.system.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface BrewStatisticsMapper 
{
    List<Map<String, Object>> selectCostTrendByMonths(@Param("months") Integer months);
    
    List<Map<String, Object>> selectMaterialWarningList();
    
    List<Map<String, Object>> selectProductionCompareByMonths(@Param("months") Integer months);
    
    Map<String, Object> selectDashboardSummary();
    
    int updateMaterialStock(@Param("materialId") Long materialId, 
                           @Param("quantity") java.math.BigDecimal quantity);
    
    String selectNextBatchSequence(@Param("datePrefix") String datePrefix);
}
