package com.jiuchang.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewStatisticsMapper;
import com.jiuchang.system.service.IBrewStatisticsService;

@Service
public class BrewStatisticsServiceImpl implements IBrewStatisticsService
{
    @Autowired
    private BrewStatisticsMapper brewStatisticsMapper;

    @Override
    public Map<String, Object> getCostTrend(Integer months)
    {
        Map<String, Object> result = new HashMap<>();
        
        List<Map<String, Object>> costData = brewStatisticsMapper.selectCostTrendByMonths(months);
        
        List<String> xAxis = new ArrayList<>();
        List<BigDecimal> totalCostSeries = new ArrayList<>();
        List<BigDecimal> unitCostSeries = new ArrayList<>();
        
        for (Map<String, Object> item : costData) {
            xAxis.add((String) item.get("month"));
            totalCostSeries.add((BigDecimal) item.get("totalCost"));
            unitCostSeries.add((BigDecimal) item.get("unitCost"));
        }
        
        result.put("xAxis", xAxis);
        result.put("totalCostSeries", totalCostSeries);
        result.put("unitCostSeries", unitCostSeries);
        
        return result;
    }

    @Override
    public List<Map<String, Object>> getMaterialWarningList()
    {
        return brewStatisticsMapper.selectMaterialWarningList();
    }

    @Override
    public Map<String, Object> getProductionCompare(Integer months)
    {
        Map<String, Object> result = new HashMap<>();
        
        List<Map<String, Object>> productionData = brewStatisticsMapper.selectProductionCompareByMonths(months);
        
        List<String> xAxis = new ArrayList<>();
        List<BigDecimal> planSeries = new ArrayList<>();
        List<BigDecimal> actualSeries = new ArrayList<>();
        
        for (Map<String, Object> item : productionData) {
            xAxis.add((String) item.get("month"));
            planSeries.add((BigDecimal) item.get("planOutput"));
            actualSeries.add((BigDecimal) item.get("actualOutput"));
        }
        
        result.put("xAxis", xAxis);
        result.put("planSeries", planSeries);
        result.put("actualSeries", actualSeries);
        
        return result;
    }

    @Override
    public Map<String, Object> getDashboardData()
    {
        Map<String, Object> result = new HashMap<>();
        
        result.put("materialWarningList", getMaterialWarningList());
        result.put("costTrend", getCostTrend(6));
        result.put("productionCompare", getProductionCompare(6));
        
        Map<String, Object> summary = brewStatisticsMapper.selectDashboardSummary();
        result.put("summary", summary);
        
        return result;
    }
}
