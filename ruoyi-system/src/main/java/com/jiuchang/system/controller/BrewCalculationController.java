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
        try {
            List<Map<String, Object>> result = brewCalculationService.calculateMaterial(templateId, targetOutput);
            return success(result);
        } catch (IllegalArgumentException e) {
            return error(e.getMessage());
        } catch (Exception e) {
            return error("计算原料配比失败：" + e.getMessage());
        }
    }

    @GetMapping("/process/recommendCycle")
    public AjaxResult recommendCycle(
            @RequestParam("temperature") Double temperature,
            @RequestParam("humidity") Double humidity,
            @RequestParam(value = "wineType", required = false, defaultValue = "baijiu") String wineType)
    {
        try {
            Integer recommendedDays = brewCalculationService.recommendCycle(temperature, humidity, wineType);
            return success(recommendedDays);
        } catch (IllegalArgumentException e) {
            return error(e.getMessage());
        } catch (Exception e) {
            return error("计算推荐周期失败：" + e.getMessage());
        }
    }
}
