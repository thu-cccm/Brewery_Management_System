package com.jiuchang.system.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.domain.BrewFormulaDetail;
import com.jiuchang.system.domain.BrewMaterial;
import com.jiuchang.system.mapper.BrewFormulaDetailMapper;
import com.jiuchang.system.mapper.BrewMaterialMapper;
import com.jiuchang.system.service.IBrewCalculationService;

@Service
public class BrewCalculationServiceImpl implements IBrewCalculationService
{
    @Autowired
    private BrewFormulaDetailMapper brewFormulaDetailMapper;

    @Autowired
    private BrewMaterialMapper brewMaterialMapper;

    @Override
    public List<Map<String, Object>> calculateMaterial(Long templateId, Double targetOutput)
    {
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        if (templateId == null || targetOutput == null || targetOutput <= 0) {
            return resultList;
        }

        BrewFormulaDetail query = new BrewFormulaDetail();
        query.setTemplateId(templateId);
        List<BrewFormulaDetail> detailList = brewFormulaDetailMapper.selectBrewFormulaDetailList(query);

        BigDecimal target = BigDecimal.valueOf(targetOutput);

        for (BrewFormulaDetail detail : detailList) {
            Map<String, Object> item = new HashMap<>();
            
            Long materialId = detail.getMaterialId();
            BrewMaterial material = brewMaterialMapper.selectBrewMaterialByMaterialId(materialId);
            
            if (material == null) {
                continue;
            }

            BigDecimal ratio = detail.getMaterialRatio();
            if (ratio == null) {
                ratio = BigDecimal.ZERO;
            }
            
            BigDecimal requiredQuantity = target.multiply(ratio).setScale(2, RoundingMode.HALF_UP);
            
            BigDecimal currentStock = material.getStockQuantity();
            if (currentStock == null) {
                currentStock = BigDecimal.ZERO;
            }

            String status = currentStock.compareTo(requiredQuantity) >= 0 ? "充足" : "缺货";

            item.put("materialId", materialId);
            item.put("materialName", material.getMaterialName());
            item.put("materialUnit", material.getMaterialUnit());
            item.put("requiredQuantity", requiredQuantity);
            item.put("currentStock", currentStock);
            item.put("status", status);
            item.put("isMainMaterial", detail.getIsMainMaterial());
            
            resultList.add(item);
        }

        return resultList;
    }

    @Override
    public Integer recommendCycle(Double temperature, Double humidity, String wineType)
    {
        int baseCycle;
        double standardTemp;
        double tempAdjustFactor;
        double humidityAdjustFactor;
        
        switch (wineType != null ? wineType.toLowerCase() : "baijiu") {
            case "baijiu":
            case "白酒":
                baseCycle = 30;
                standardTemp = 25.0;
                tempAdjustFactor = 0.5;
                humidityAdjustFactor = 0.1;
                break;
            case "huangjiu":
            case "黄酒":
                baseCycle = 45;
                standardTemp = 22.0;
                tempAdjustFactor = 0.6;
                humidityAdjustFactor = 0.15;
                break;
            case "wine":
            case "葡萄酒":
                baseCycle = 60;
                standardTemp = 18.0;
                tempAdjustFactor = 0.8;
                humidityAdjustFactor = 0.2;
                break;
            case "beer":
            case "啤酒":
                baseCycle = 14;
                standardTemp = 12.0;
                tempAdjustFactor = 0.3;
                humidityAdjustFactor = 0.05;
                break;
            default:
                baseCycle = 30;
                standardTemp = 25.0;
                tempAdjustFactor = 0.5;
                humidityAdjustFactor = 0.1;
        }

        double tempDeviation = Math.abs(temperature - standardTemp);
        double tempAdjustment = tempDeviation * tempAdjustFactor;
        
        if (temperature > standardTemp) {
            tempAdjustment = -tempAdjustment * 0.8;
        }

        double standardHumidity = 65.0;
        double humidityDeviation = Math.abs(humidity - standardHumidity);
        double humidityAdjustment = humidityDeviation * humidityAdjustFactor;
        
        if (humidity < standardHumidity) {
            humidityAdjustment = humidityAdjustment * 0.5;
        }

        double totalAdjustment = tempAdjustment + humidityAdjustment;
        
        int recommendedDays = (int) Math.round(baseCycle + totalAdjustment);
        
        int minDays = (int) (baseCycle * 0.5);
        int maxDays = (int) (baseCycle * 2.0);
        recommendedDays = Math.max(minDays, Math.min(maxDays, recommendedDays));

        return recommendedDays;
    }
}
