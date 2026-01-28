package com.jiuchang.system.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jiuchang.common.core.controller.BaseController;
import com.jiuchang.common.core.domain.AjaxResult;
import com.jiuchang.system.service.IBrewCalculationService;

@RestController
@RequestMapping("/brew")
public class BrewCalculationController extends BaseController
{
    @Autowired
    private IBrewCalculationService brewCalculationService;

    @GetMapping("/plan/calculateMaterial")
    public AjaxResult calculateMaterial(
            @RequestParam("templateId") Long templateId,
            @RequestParam("targetOutput") Double targetOutput)
    {
        List<Map<String, Object>> result = brewCalculationService.calculateMaterial(templateId, targetOutput);
        return success(result);
    }

    @GetMapping("/process/recommendCycle")
    public AjaxResult recommendCycle(
            @RequestParam("temperature") Double temperature,
            @RequestParam("humidity") Double humidity,
            @RequestParam(value = "wineType", required = false, defaultValue = "baijiu") String wineType)
    {
        Integer recommendedDays = brewCalculationService.recommendCycle(temperature, humidity, wineType);
        return success(recommendedDays);
    }
}
