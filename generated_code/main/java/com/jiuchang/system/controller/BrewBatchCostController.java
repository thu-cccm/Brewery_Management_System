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
import com.jiuchang.system.domain.BrewBatchCost;
import com.jiuchang.system.service.IBrewBatchCostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * æ‰¹æ¬¡æˆæœ¬Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/cost")
public class BrewBatchCostController extends BaseController
{
    @Autowired
    private IBrewBatchCostService brewBatchCostService;

    /**
     * 查询æ‰¹æ¬¡æˆæœ¬列表
     */
    @PreAuthorize("@ss.hasPermi('system:cost:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewBatchCost brewBatchCost)
    {
        startPage();
        List<BrewBatchCost> list = brewBatchCostService.selectBrewBatchCostList(brewBatchCost);
        return getDataTable(list);
    }

    /**
     * 导出æ‰¹æ¬¡æˆæœ¬列表
     */
    @PreAuthorize("@ss.hasPermi('system:cost:export')")
    @Log(title = "æ‰¹æ¬¡æˆæœ¬", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewBatchCost brewBatchCost)
    {
        List<BrewBatchCost> list = brewBatchCostService.selectBrewBatchCostList(brewBatchCost);
        ExcelUtil<BrewBatchCost> util = new ExcelUtil<BrewBatchCost>(BrewBatchCost.class);
        util.exportExcel(response, list, "æ‰¹æ¬¡æˆæœ¬数据");
    }

    /**
     * 获取æ‰¹æ¬¡æˆæœ¬详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cost:query')")
    @GetMapping(value = "/{costId}")
    public AjaxResult getInfo(@PathVariable("costId") Long costId)
    {
        return success(brewBatchCostService.selectBrewBatchCostByCostId(costId));
    }

    /**
     * 新增æ‰¹æ¬¡æˆæœ¬
     */
    @PreAuthorize("@ss.hasPermi('system:cost:add')")
    @Log(title = "æ‰¹æ¬¡æˆæœ¬", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewBatchCost brewBatchCost)
    {
        return toAjax(brewBatchCostService.insertBrewBatchCost(brewBatchCost));
    }

    /**
     * 修改æ‰¹æ¬¡æˆæœ¬
     */
    @PreAuthorize("@ss.hasPermi('system:cost:edit')")
    @Log(title = "æ‰¹æ¬¡æˆæœ¬", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewBatchCost brewBatchCost)
    {
        return toAjax(brewBatchCostService.updateBrewBatchCost(brewBatchCost));
    }

    /**
     * 删除æ‰¹æ¬¡æˆæœ¬
     */
    @PreAuthorize("@ss.hasPermi('system:cost:remove')")
    @Log(title = "æ‰¹æ¬¡æˆæœ¬", businessType = BusinessType.DELETE)
	@DeleteMapping("/{costIds}")
    public AjaxResult remove(@PathVariable Long[] costIds)
    {
        return toAjax(brewBatchCostService.deleteBrewBatchCostByCostIds(costIds));
    }
}
