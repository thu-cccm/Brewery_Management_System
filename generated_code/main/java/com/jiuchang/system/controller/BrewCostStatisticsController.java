package com.jiuchang.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.jiuchang.system.domain.BrewCostStatistics;
import com.jiuchang.system.service.IBrewCostStatisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * æˆæœ¬ç»Ÿè®¡Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/statistics")
public class BrewCostStatisticsController extends BaseController
{
    @Autowired
    private IBrewCostStatisticsService brewCostStatisticsService;

    /**
     * 查询æˆæœ¬ç»Ÿè®¡列表
     */
    @PreAuthorize("@ss.hasPermi('system:statistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewCostStatistics brewCostStatistics)
    {
        startPage();
        List<BrewCostStatistics> list = brewCostStatisticsService.selectBrewCostStatisticsList(brewCostStatistics);
        return getDataTable(list);
    }

    /**
     * 导出æˆæœ¬ç»Ÿè®¡列表
     */
    @PreAuthorize("@ss.hasPermi('system:statistics:export')")
    @Log(title = "æˆæœ¬ç»Ÿè®¡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewCostStatistics brewCostStatistics)
    {
        List<BrewCostStatistics> list = brewCostStatisticsService.selectBrewCostStatisticsList(brewCostStatistics);
        ExcelUtil<BrewCostStatistics> util = new ExcelUtil<BrewCostStatistics>(BrewCostStatistics.class);
        util.exportExcel(response, list, "æˆæœ¬ç»Ÿè®¡数据");
    }

    /**
     * 获取æˆæœ¬ç»Ÿè®¡详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:statistics:query')")
    @GetMapping(value = "/{statId}")
    public AjaxResult getInfo(@PathVariable("statId") Long statId)
    {
        return success(brewCostStatisticsService.selectBrewCostStatisticsByStatId(statId));
    }

    /**
     * 新增æˆæœ¬ç»Ÿè®¡
     */
    @PreAuthorize("@ss.hasPermi('system:statistics:add')")
    @Log(title = "æˆæœ¬ç»Ÿè®¡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewCostStatistics brewCostStatistics)
    {
        return toAjax(brewCostStatisticsService.insertBrewCostStatistics(brewCostStatistics));
    }

    /**
     * 修改æˆæœ¬ç»Ÿè®¡
     */
    @PreAuthorize("@ss.hasPermi('system:statistics:edit')")
    @Log(title = "æˆæœ¬ç»Ÿè®¡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewCostStatistics brewCostStatistics)
    {
        return toAjax(brewCostStatisticsService.updateBrewCostStatistics(brewCostStatistics));
    }

    /**
     * 删除æˆæœ¬ç»Ÿè®¡
     */
    @PreAuthorize("@ss.hasPermi('system:statistics:remove')")
    @Log(title = "æˆæœ¬ç»Ÿè®¡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{statIds}")
    public AjaxResult remove(@PathVariable Long[] statIds)
    {
        return toAjax(brewCostStatisticsService.deleteBrewCostStatisticsByStatIds(statIds));
    }
}
