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

    @Autowired
    private com.jiuchang.system.mapper.BrewProductionBatchMapper brewProductionBatchMapper;
    @Autowired
    private com.jiuchang.system.mapper.BrewBatchYieldMapper brewBatchYieldMapper;

    @Override
    public Map<String, Object> getProductionStats()
    {
        Map<String, Object> stats = new HashMap<>();
        com.jiuchang.system.domain.BrewProductionBatch query = new com.jiuchang.system.domain.BrewProductionBatch();
        List<com.jiuchang.system.domain.BrewProductionBatch> list = brewProductionBatchMapper.selectBrewProductionBatchList(query);
        
        for (com.jiuchang.system.domain.BrewProductionBatch batch : list) {
            String status = batch.getBatchStatus();
            if (status != null) {
                stats.put(status, (int)stats.getOrDefault(status, 0) + 1);
            }
        }
        return stats;
    }

    @Override
    public Map<String, Object> getYieldStats()
    {
        Map<String, Object> result = new HashMap<>();
        com.jiuchang.system.domain.BrewBatchYield query = new com.jiuchang.system.domain.BrewBatchYield();
        List<com.jiuchang.system.domain.BrewBatchYield> list = brewBatchYieldMapper.selectBrewBatchYieldList(query);
        
        // 计算异常率
        int total = list.size();
        int abnormalCount = 0;
        Map<String, List<BigDecimal>> stageLossMap = new HashMap<>();
        
        for (com.jiuchang.system.domain.BrewBatchYield yield : list) {
            if ("1".equals(yield.getIsAbnormal())) {
                abnormalCount++;
            }
            String stage = yield.getStageKey();
            if (stage != null && yield.getLossRate() != null) {
                stageLossMap.computeIfAbsent(stage, k -> new ArrayList<>()).add(yield.getLossRate());
            }
        }
        
        double abnormalRate = total > 0 ? (double) abnormalCount / total * 100 : 0;
        result.put("abnormalRate", abnormalRate);
        
        // 计算各阶段平均损耗率
        Map<String, Double> stageLossRate = new HashMap<>();
        for (Map.Entry<String, List<BigDecimal>> entry : stageLossMap.entrySet()) {
            List<BigDecimal> rates = entry.getValue();
            double avg = rates.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);
            stageLossRate.put(entry.getKey(), avg);
        }
        result.put("stageLossRate", stageLossRate);
        
        return result;
    }

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

    @Override
    public Map<String, Object> getQualityStability(String categoryCode)
    {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = brewStatisticsMapper.selectQualityStability(categoryCode);
        
        List<String> batchNos = new ArrayList<>();
        List<BigDecimal> alcoholErrors = new ArrayList<>();
        List<BigDecimal> qualityScores = new ArrayList<>();
        
        for (Map<String, Object> item : list) {
            batchNos.add((String) item.get("batch_no"));
            alcoholErrors.add((BigDecimal) item.get("alcohol_error"));
            qualityScores.add((BigDecimal) item.get("quality_score"));
        }
        
        result.put("batchNos", batchNos);
        result.put("alcoholErrors", alcoholErrors);
        result.put("qualityScores", qualityScores);
        return result;
    }

    @Override
    public List<Map<String, Object>> getProductionEfficiency()
    {
        return brewStatisticsMapper.selectProductionEfficiency();
    }

    @Override
    public List<Map<String, Object>> getProductionProgress()
    {
        return brewStatisticsMapper.selectProductionProgress();
    }
}
