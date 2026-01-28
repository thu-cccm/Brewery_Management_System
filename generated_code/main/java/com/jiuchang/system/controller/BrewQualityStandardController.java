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
import com.jiuchang.system.domain.BrewQualityStandard;
import com.jiuchang.system.service.IBrewQualityStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * è´¨æ£€æ ‡å‡†Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/standard")
public class BrewQualityStandardController extends BaseController
{
    @Autowired
    private IBrewQualityStandardService brewQualityStandardService;

    /**
     * 查询è´¨æ£€æ ‡å‡†列表
     */
    @PreAuthorize("@ss.hasPermi('system:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewQualityStandard brewQualityStandard)
    {
        startPage();
        List<BrewQualityStandard> list = brewQualityStandardService.selectBrewQualityStandardList(brewQualityStandard);
        return getDataTable(list);
    }

    /**
     * 导出è´¨æ£€æ ‡å‡†列表
     */
    @PreAuthorize("@ss.hasPermi('system:standard:export')")
    @Log(title = "è´¨æ£€æ ‡å‡†", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewQualityStandard brewQualityStandard)
    {
        List<BrewQualityStandard> list = brewQualityStandardService.selectBrewQualityStandardList(brewQualityStandard);
        ExcelUtil<BrewQualityStandard> util = new ExcelUtil<BrewQualityStandard>(BrewQualityStandard.class);
        util.exportExcel(response, list, "è´¨æ£€æ ‡å‡†数据");
    }

    /**
     * 获取è´¨æ£€æ ‡å‡†详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:standard:query')")
    @GetMapping(value = "/{standardId}")
    public AjaxResult getInfo(@PathVariable("standardId") Long standardId)
    {
        return success(brewQualityStandardService.selectBrewQualityStandardByStandardId(standardId));
    }

    /**
     * 新增è´¨æ£€æ ‡å‡†
     */
    @PreAuthorize("@ss.hasPermi('system:standard:add')")
    @Log(title = "è´¨æ£€æ ‡å‡†", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewQualityStandard brewQualityStandard)
    {
        return toAjax(brewQualityStandardService.insertBrewQualityStandard(brewQualityStandard));
    }

    /**
     * 修改è´¨æ£€æ ‡å‡†
     */
    @PreAuthorize("@ss.hasPermi('system:standard:edit')")
    @Log(title = "è´¨æ£€æ ‡å‡†", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewQualityStandard brewQualityStandard)
    {
        return toAjax(brewQualityStandardService.updateBrewQualityStandard(brewQualityStandard));
    }

    /**
     * 删除è´¨æ£€æ ‡å‡†
     */
    @PreAuthorize("@ss.hasPermi('system:standard:remove')")
    @Log(title = "è´¨æ£€æ ‡å‡†", businessType = BusinessType.DELETE)
	@DeleteMapping("/{standardIds}")
    public AjaxResult remove(@PathVariable Long[] standardIds)
    {
        return toAjax(brewQualityStandardService.deleteBrewQualityStandardByStandardIds(standardIds));
    }
}
