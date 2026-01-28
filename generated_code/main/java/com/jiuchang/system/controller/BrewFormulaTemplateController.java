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
import com.jiuchang.system.domain.BrewFormulaTemplate;
import com.jiuchang.system.service.IBrewFormulaTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * é…æ–¹æ¨¡æ¿Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/template")
public class BrewFormulaTemplateController extends BaseController
{
    @Autowired
    private IBrewFormulaTemplateService brewFormulaTemplateService;

    /**
     * 查询é…æ–¹æ¨¡æ¿列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewFormulaTemplate brewFormulaTemplate)
    {
        startPage();
        List<BrewFormulaTemplate> list = brewFormulaTemplateService.selectBrewFormulaTemplateList(brewFormulaTemplate);
        return getDataTable(list);
    }

    /**
     * 导出é…æ–¹æ¨¡æ¿列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:export')")
    @Log(title = "é…æ–¹æ¨¡æ¿", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewFormulaTemplate brewFormulaTemplate)
    {
        List<BrewFormulaTemplate> list = brewFormulaTemplateService.selectBrewFormulaTemplateList(brewFormulaTemplate);
        ExcelUtil<BrewFormulaTemplate> util = new ExcelUtil<BrewFormulaTemplate>(BrewFormulaTemplate.class);
        util.exportExcel(response, list, "é…æ–¹æ¨¡æ¿数据");
    }

    /**
     * 获取é…æ–¹æ¨¡æ¿详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:template:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId)
    {
        return success(brewFormulaTemplateService.selectBrewFormulaTemplateByTemplateId(templateId));
    }

    /**
     * 新增é…æ–¹æ¨¡æ¿
     */
    @PreAuthorize("@ss.hasPermi('system:template:add')")
    @Log(title = "é…æ–¹æ¨¡æ¿", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewFormulaTemplate brewFormulaTemplate)
    {
        return toAjax(brewFormulaTemplateService.insertBrewFormulaTemplate(brewFormulaTemplate));
    }

    /**
     * 修改é…æ–¹æ¨¡æ¿
     */
    @PreAuthorize("@ss.hasPermi('system:template:edit')")
    @Log(title = "é…æ–¹æ¨¡æ¿", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewFormulaTemplate brewFormulaTemplate)
    {
        return toAjax(brewFormulaTemplateService.updateBrewFormulaTemplate(brewFormulaTemplate));
    }

    /**
     * 删除é…æ–¹æ¨¡æ¿
     */
    @PreAuthorize("@ss.hasPermi('system:template:remove')")
    @Log(title = "é…æ–¹æ¨¡æ¿", businessType = BusinessType.DELETE)
	@DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds)
    {
        return toAjax(brewFormulaTemplateService.deleteBrewFormulaTemplateByTemplateIds(templateIds));
    }
}
