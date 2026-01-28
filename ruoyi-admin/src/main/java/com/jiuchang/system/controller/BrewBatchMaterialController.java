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
import com.jiuchang.system.domain.BrewBatchMaterial;
import com.jiuchang.system.service.IBrewBatchMaterialService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 批次原料使用记录Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/batchmaterial")
public class BrewBatchMaterialController extends BaseController
{
    @Autowired
    private IBrewBatchMaterialService brewBatchMaterialService;

    /**
     * 查询æ‰¹æ¬¡åŽŸæ–™列表
     */
    @PreAuthorize("@ss.hasPermi('system:batchmaterial:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewBatchMaterial brewBatchMaterial)
    {
        startPage();
        List<BrewBatchMaterial> list = brewBatchMaterialService.selectBrewBatchMaterialList(brewBatchMaterial);
        return getDataTable(list);
    }

    /**
     * 导出æ‰¹æ¬¡åŽŸæ–™列表
     */
    @PreAuthorize("@ss.hasPermi('system:batchmaterial:export')")
    @Log(title = "æ‰¹æ¬¡åŽŸæ–™", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewBatchMaterial brewBatchMaterial)
    {
        List<BrewBatchMaterial> list = brewBatchMaterialService.selectBrewBatchMaterialList(brewBatchMaterial);
        ExcelUtil<BrewBatchMaterial> util = new ExcelUtil<BrewBatchMaterial>(BrewBatchMaterial.class);
        util.exportExcel(response, list, "æ‰¹æ¬¡åŽŸæ–™数据");
    }

    /**
     * 获取æ‰¹æ¬¡åŽŸæ–™详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:batchmaterial:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(brewBatchMaterialService.selectBrewBatchMaterialByRecordId(recordId));
    }

    /**
     * 新增æ‰¹æ¬¡åŽŸæ–™
     */
    @PreAuthorize("@ss.hasPermi('system:batchmaterial:add')")
    @Log(title = "æ‰¹æ¬¡åŽŸæ–™", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewBatchMaterial brewBatchMaterial)
    {
        return toAjax(brewBatchMaterialService.insertBrewBatchMaterial(brewBatchMaterial));
    }

    /**
     * 修改æ‰¹æ¬¡åŽŸæ–™
     */
    @PreAuthorize("@ss.hasPermi('system:batchmaterial:edit')")
    @Log(title = "æ‰¹æ¬¡åŽŸæ–™", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewBatchMaterial brewBatchMaterial)
    {
        return toAjax(brewBatchMaterialService.updateBrewBatchMaterial(brewBatchMaterial));
    }

    /**
     * 删除æ‰¹æ¬¡åŽŸæ–™
     */
    @PreAuthorize("@ss.hasPermi('system:batchmaterial:remove')")
    @Log(title = "æ‰¹æ¬¡åŽŸæ–™", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(brewBatchMaterialService.deleteBrewBatchMaterialByRecordIds(recordIds));
    }
}
