package com.jiuchang.system.service;

import java.util.List;
import java.util.Map;

public interface IBrewCalculationService 
{
    List<Map<String, Object>> calculateMaterial(Long templateId, Double targetOutput);
    
    Integer recommendCycle(Double temperature, Double humidity, String wineType);
}
