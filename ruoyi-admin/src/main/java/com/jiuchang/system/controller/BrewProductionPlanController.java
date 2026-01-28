package com.jiuchang.system.controller;

import java.util.List;
import java.util.Map;
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
import com.jiuchang.common.utils.SecurityUtils;
import com.jiuchang.system.domain.BrewProductionPlan;
import com.jiuchang.system.service.IBrewProductionPlanService;
import com.jiuchang.system.service.IBrewProductionBatchService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 生产计划Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/plan")
public class BrewProductionPlanController extends BaseController
{
    @Autowired
    private IBrewProductionPlanService brewProductionPlanService;

    @Autowired
    private IBrewProductionBatchService brewProductionBatchService;

    /**
     * 查询ç”Ÿäº§è®¡åˆ’列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewProductionPlan brewProductionPlan)
    {
        startPage();
        List<BrewProductionPlan> list = brewProductionPlanService.selectBrewProductionPlanList(brewProductionPlan);
        return getDataTable(list);
    }

    /**
     * 导出ç”Ÿäº§è®¡åˆ’列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:export')")
    @Log(title = "ç”Ÿäº§è®¡åˆ’", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewProductionPlan brewProductionPlan)
    {
        List<BrewProductionPlan> list = brewProductionPlanService.selectBrewProductionPlanList(brewProductionPlan);
        ExcelUtil<BrewProductionPlan> util = new ExcelUtil<BrewProductionPlan>(BrewProductionPlan.class);
        util.exportExcel(response, list, "ç”Ÿäº§è®¡åˆ’数据");
    }

    /**
     * 获取ç”Ÿäº§è®¡åˆ’详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:plan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return success(brewProductionPlanService.selectBrewProductionPlanByPlanId(planId));
    }

    /**
     * 新增ç”Ÿäº§è®¡åˆ’
     */
    @PreAuthorize("@ss.hasPermi('system:plan:add')")
    @Log(title = "ç”Ÿäº§è®¡åˆ’", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewProductionPlan brewProductionPlan)
    {
        return toAjax(brewProductionPlanService.insertBrewProductionPlan(brewProductionPlan));
    }

    /**
     * 修改ç”Ÿäº§è®¡åˆ’
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @Log(title = "ç”Ÿäº§è®¡åˆ’", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewProductionPlan brewProductionPlan)
    {
        return toAjax(brewProductionPlanService.updateBrewProductionPlan(brewProductionPlan));
    }

    /**
     * 删除ç”Ÿäº§è®¡åˆ’
     */
    @PreAuthorize("@ss.hasPermi('system:plan:remove')")
    @Log(title = "ç”Ÿäº§è®¡åˆ’", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(brewProductionPlanService.deleteBrewProductionPlanByPlanIds(planIds));
    }

    /**
     * 启动生产
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @Log(title = "启动生产", businessType = BusinessType.UPDATE)
    @PostMapping("/startProduction")
    public AjaxResult startProduction(@RequestBody Map<String, Object> params)
    {
        Long planId = Long.valueOf(params.get("planId").toString());
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> materialList = (List<Map<String, Object>>) params.get("materialList");
        String operatorName = SecurityUtils.getUsername();
        
        String batchNo = brewProductionBatchService.startProduction(planId, materialList, operatorName);
        return success(batchNo);
    }
}
