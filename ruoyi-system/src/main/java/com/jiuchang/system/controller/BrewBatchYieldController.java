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
import com.jiuchang.system.domain.BrewBatchYield;
import com.jiuchang.system.service.IBrewBatchYieldService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 批次损耗分析Controller
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@RestController
@RequestMapping("/brew/yield")
public class BrewBatchYieldController extends BaseController
{
    @Autowired
    private IBrewBatchYieldService brewBatchYieldService;

    /**
     * 查询批次损耗分析列表
     */
    @PreAuthorize("@ss.hasPermi('brew:yield:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewBatchYield brewBatchYield)
    {
        startPage();
        List<BrewBatchYield> list = brewBatchYieldService.selectBrewBatchYieldList(brewBatchYield);
        return getDataTable(list);
    }

    /**
     * 导出批次损耗分析列表
     */
    @PreAuthorize("@ss.hasPermi('brew:yield:export')")
    @Log(title = "批次损耗分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewBatchYield brewBatchYield)
    {
        List<BrewBatchYield> list = brewBatchYieldService.selectBrewBatchYieldList(brewBatchYield);
        ExcelUtil<BrewBatchYield> util = new ExcelUtil<BrewBatchYield>(BrewBatchYield.class);
        util.exportExcel(response, list, "批次损耗分析数据");
    }

    /**
     * 获取批次损耗分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('brew:yield:query')")
    @GetMapping(value = "/{yieldId}")
    public AjaxResult getInfo(@PathVariable("yieldId") Long yieldId)
    {
        return success(brewBatchYieldService.selectBrewBatchYieldByYieldId(yieldId));
    }

    /**
     * 新增批次损耗分析
     */
    @PreAuthorize("@ss.hasPermi('brew:yield:add')")
    @Log(title = "批次损耗分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewBatchYield brewBatchYield)
    {
        return toAjax(brewBatchYieldService.insertBrewBatchYield(brewBatchYield));
    }

    /**
     * 修改批次损耗分析
     */
    @PreAuthorize("@ss.hasPermi('brew:yield:edit')")
    @Log(title = "批次损耗分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewBatchYield brewBatchYield)
    {
        return toAjax(brewBatchYieldService.updateBrewBatchYield(brewBatchYield));
    }

    /**
     * 删除批次损耗分析
     */
    @PreAuthorize("@ss.hasPermi('brew:yield:remove')")
    @Log(title = "批次损耗分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{yieldIds}")
    public AjaxResult remove(@PathVariable Long[] yieldIds)
    {
        return toAjax(brewBatchYieldService.deleteBrewBatchYieldByYieldIds(yieldIds));
    }
}
