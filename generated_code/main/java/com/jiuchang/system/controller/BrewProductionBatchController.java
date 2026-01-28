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
import com.jiuchang.system.domain.BrewProductionBatch;
import com.jiuchang.system.service.IBrewProductionBatchService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * ç”Ÿäº§æ‰¹æ¬¡Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/batch")
public class BrewProductionBatchController extends BaseController
{
    @Autowired
    private IBrewProductionBatchService brewProductionBatchService;

    /**
     * 查询ç”Ÿäº§æ‰¹æ¬¡列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewProductionBatch brewProductionBatch)
    {
        startPage();
        List<BrewProductionBatch> list = brewProductionBatchService.selectBrewProductionBatchList(brewProductionBatch);
        return getDataTable(list);
    }

    /**
     * 导出ç”Ÿäº§æ‰¹æ¬¡列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "ç”Ÿäº§æ‰¹æ¬¡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewProductionBatch brewProductionBatch)
    {
        List<BrewProductionBatch> list = brewProductionBatchService.selectBrewProductionBatchList(brewProductionBatch);
        ExcelUtil<BrewProductionBatch> util = new ExcelUtil<BrewProductionBatch>(BrewProductionBatch.class);
        util.exportExcel(response, list, "ç”Ÿäº§æ‰¹æ¬¡数据");
    }

    /**
     * 获取ç”Ÿäº§æ‰¹æ¬¡详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/{batchId}")
    public AjaxResult getInfo(@PathVariable("batchId") Long batchId)
    {
        return success(brewProductionBatchService.selectBrewProductionBatchByBatchId(batchId));
    }

    /**
     * 新增ç”Ÿäº§æ‰¹æ¬¡
     */
    @PreAuthorize("@ss.hasPermi('system:batch:add')")
    @Log(title = "ç”Ÿäº§æ‰¹æ¬¡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewProductionBatch brewProductionBatch)
    {
        return toAjax(brewProductionBatchService.insertBrewProductionBatch(brewProductionBatch));
    }

    /**
     * 修改ç”Ÿäº§æ‰¹æ¬¡
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "ç”Ÿäº§æ‰¹æ¬¡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewProductionBatch brewProductionBatch)
    {
        return toAjax(brewProductionBatchService.updateBrewProductionBatch(brewProductionBatch));
    }

    /**
     * 删除ç”Ÿäº§æ‰¹æ¬¡
     */
    @PreAuthorize("@ss.hasPermi('system:batch:remove')")
    @Log(title = "ç”Ÿäº§æ‰¹æ¬¡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{batchIds}")
    public AjaxResult remove(@PathVariable Long[] batchIds)
    {
        return toAjax(brewProductionBatchService.deleteBrewProductionBatchByBatchIds(batchIds));
    }
}
