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
import com.jiuchang.common.annotation.Log;
import com.jiuchang.common.core.controller.BaseController;
import com.jiuchang.common.core.domain.AjaxResult;
import com.jiuchang.common.enums.BusinessType;
import com.jiuchang.system.domain.BrewQualityReport;
import com.jiuchang.system.service.IBrewQualityReportService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 质检报告Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/report")
public class BrewQualityReportController extends BaseController
{
    @Autowired
    private IBrewQualityReportService brewQualityReportService;

    /**
     * 查询è´¨æ£€æŠ¥å‘Š列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewQualityReport brewQualityReport)
    {
        startPage();
        List<BrewQualityReport> list = brewQualityReportService.selectBrewQualityReportList(brewQualityReport);
        return getDataTable(list);
    }

    /**
     * 导出è´¨æ£€æŠ¥å‘Š列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:export')")
    @Log(title = "è´¨æ£€æŠ¥å‘Š", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewQualityReport brewQualityReport)
    {
        List<BrewQualityReport> list = brewQualityReportService.selectBrewQualityReportList(brewQualityReport);
        ExcelUtil<BrewQualityReport> util = new ExcelUtil<BrewQualityReport>(BrewQualityReport.class);
        util.exportExcel(response, list, "è´¨æ£€æŠ¥å‘Š数据");
    }

    /**
     * 获取è´¨æ£€æŠ¥å‘Š详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(brewQualityReportService.selectBrewQualityReportByReportId(reportId));
    }

    /**
     * 新增è´¨æ£€æŠ¥å‘Š
     */
    @PreAuthorize("@ss.hasPermi('system:report:add')")
    @Log(title = "è´¨æ£€æŠ¥å‘Š", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewQualityReport brewQualityReport)
    {
        return toAjax(brewQualityReportService.insertBrewQualityReport(brewQualityReport));
    }

    /**
     * 修改è´¨æ£€æŠ¥å‘Š
     */
    @PreAuthorize("@ss.hasPermi('system:report:edit')")
    @Log(title = "è´¨æ£€æŠ¥å‘Š", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewQualityReport brewQualityReport)
    {
        return toAjax(brewQualityReportService.updateBrewQualityReport(brewQualityReport));
    }

    /**
     * 删除è´¨æ£€æŠ¥å‘Š
     */
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
    @Log(title = "è´¨æ£€æŠ¥å‘Š", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(brewQualityReportService.deleteBrewQualityReportByReportIds(reportIds));
    }
}
