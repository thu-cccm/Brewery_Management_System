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
import com.jiuchang.system.domain.BrewTechnologyStandard;
import com.jiuchang.system.service.IBrewTechnologyStandardService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 工艺参数标准Controller
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@RestController
@RequestMapping("/brew/standard")
public class BrewTechnologyStandardController extends BaseController
{
    @Autowired
    private IBrewTechnologyStandardService brewTechnologyStandardService;

    /**
     * 查询工艺参数标准列表
     */
    @PreAuthorize("@ss.hasPermi('brew:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewTechnologyStandard brewTechnologyStandard)
    {
        startPage();
        List<BrewTechnologyStandard> list = brewTechnologyStandardService.selectBrewTechnologyStandardList(brewTechnologyStandard);
        return getDataTable(list);
    }

    /**
     * 导出工艺参数标准列表
     */
    @PreAuthorize("@ss.hasPermi('brew:standard:export')")
    @Log(title = "工艺参数标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewTechnologyStandard brewTechnologyStandard)
    {
        List<BrewTechnologyStandard> list = brewTechnologyStandardService.selectBrewTechnologyStandardList(brewTechnologyStandard);
        ExcelUtil<BrewTechnologyStandard> util = new ExcelUtil<BrewTechnologyStandard>(BrewTechnologyStandard.class);
        util.exportExcel(response, list, "工艺参数标准数据");
    }

    /**
     * 获取工艺参数标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('brew:standard:query')")
    @GetMapping(value = "/{standardId}")
    public AjaxResult getInfo(@PathVariable("standardId") Long standardId)
    {
        return success(brewTechnologyStandardService.selectBrewTechnologyStandardByStandardId(standardId));
    }

    /**
     * 新增工艺参数标准
     */
    @PreAuthorize("@ss.hasPermi('brew:standard:add')")
    @Log(title = "工艺参数标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewTechnologyStandard brewTechnologyStandard)
    {
        return toAjax(brewTechnologyStandardService.insertBrewTechnologyStandard(brewTechnologyStandard));
    }

    /**
     * 修改工艺参数标准
     */
    @PreAuthorize("@ss.hasPermi('brew:standard:edit')")
    @Log(title = "工艺参数标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewTechnologyStandard brewTechnologyStandard)
    {
        return toAjax(brewTechnologyStandardService.updateBrewTechnologyStandard(brewTechnologyStandard));
    }

    /**
     * 删除工艺参数标准
     */
    @PreAuthorize("@ss.hasPermi('brew:standard:remove')")
    @Log(title = "工艺参数标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{standardIds}")
    public AjaxResult remove(@PathVariable Long[] standardIds)
    {
        return toAjax(brewTechnologyStandardService.deleteBrewTechnologyStandardByStandardIds(standardIds));
    }
}
