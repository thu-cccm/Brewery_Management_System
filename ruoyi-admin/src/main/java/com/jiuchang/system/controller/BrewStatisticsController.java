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
import com.jiuchang.system.service.IBrewStatisticsService;

@RestController
@RequestMapping("/brew/stats")
public class BrewStatisticsController extends BaseController
{
    @Autowired
    private IBrewStatisticsService brewStatisticsService;

    @GetMapping("/costTrend")
    public AjaxResult getCostTrend(@RequestParam(value = "months", defaultValue = "6") Integer months)
    {
        Map<String, Object> result = brewStatisticsService.getCostTrend(months);
        return success(result);
    }

    @GetMapping("/materialWarning")
    public AjaxResult getMaterialWarningList()
    {
        List<Map<String, Object>> result = brewStatisticsService.getMaterialWarningList();
        return success(result);
    }

    @GetMapping("/productionCompare")
    public AjaxResult getProductionCompare(@RequestParam(value = "months", defaultValue = "6") Integer months)
    {
        Map<String, Object> result = brewStatisticsService.getProductionCompare(months);
        return success(result);
    }

    @GetMapping("/dashboard")
    public AjaxResult getDashboardData()
    {
        Map<String, Object> result = brewStatisticsService.getDashboardData();
        return success(result);
    }
}
