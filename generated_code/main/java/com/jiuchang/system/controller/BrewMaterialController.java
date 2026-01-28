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
import com.jiuchang.system.domain.BrewMaterial;
import com.jiuchang.system.service.IBrewMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * åŽŸæ–™ä¿¡æ¯Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/material")
public class BrewMaterialController extends BaseController
{
    @Autowired
    private IBrewMaterialService brewMaterialService;

    /**
     * 查询åŽŸæ–™ä¿¡æ¯列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewMaterial brewMaterial)
    {
        startPage();
        List<BrewMaterial> list = brewMaterialService.selectBrewMaterialList(brewMaterial);
        return getDataTable(list);
    }

    /**
     * 导出åŽŸæ–™ä¿¡æ¯列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:export')")
    @Log(title = "åŽŸæ–™ä¿¡æ¯", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewMaterial brewMaterial)
    {
        List<BrewMaterial> list = brewMaterialService.selectBrewMaterialList(brewMaterial);
        ExcelUtil<BrewMaterial> util = new ExcelUtil<BrewMaterial>(BrewMaterial.class);
        util.exportExcel(response, list, "åŽŸæ–™ä¿¡æ¯数据");
    }

    /**
     * 获取åŽŸæ–™ä¿¡æ¯详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:material:query')")
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return success(brewMaterialService.selectBrewMaterialByMaterialId(materialId));
    }

    /**
     * 新增åŽŸæ–™ä¿¡æ¯
     */
    @PreAuthorize("@ss.hasPermi('system:material:add')")
    @Log(title = "åŽŸæ–™ä¿¡æ¯", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewMaterial brewMaterial)
    {
        return toAjax(brewMaterialService.insertBrewMaterial(brewMaterial));
    }

    /**
     * 修改åŽŸæ–™ä¿¡æ¯
     */
    @PreAuthorize("@ss.hasPermi('system:material:edit')")
    @Log(title = "åŽŸæ–™ä¿¡æ¯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewMaterial brewMaterial)
    {
        return toAjax(brewMaterialService.updateBrewMaterial(brewMaterial));
    }

    /**
     * 删除åŽŸæ–™ä¿¡æ¯
     */
    @PreAuthorize("@ss.hasPermi('system:material:remove')")
    @Log(title = "åŽŸæ–™ä¿¡æ¯", businessType = BusinessType.DELETE)
	@DeleteMapping("/{materialIds}")
    public AjaxResult remove(@PathVariable Long[] materialIds)
    {
        return toAjax(brewMaterialService.deleteBrewMaterialByMaterialIds(materialIds));
    }
}
