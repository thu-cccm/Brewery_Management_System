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
import com.jiuchang.system.domain.BrewWineCategory;
import com.jiuchang.system.service.IBrewWineCategoryService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 酒品种类Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/category")
public class BrewWineCategoryController extends BaseController
{
    @Autowired
    private IBrewWineCategoryService brewWineCategoryService;

    /**
     * 查询é…’å“ç§ç±»列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewWineCategory brewWineCategory)
    {
        startPage();
        List<BrewWineCategory> list = brewWineCategoryService.selectBrewWineCategoryList(brewWineCategory);
        return getDataTable(list);
    }

    /**
     * 导出é…’å“ç§ç±»列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "é…’å“ç§ç±»", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewWineCategory brewWineCategory)
    {
        List<BrewWineCategory> list = brewWineCategoryService.selectBrewWineCategoryList(brewWineCategory);
        ExcelUtil<BrewWineCategory> util = new ExcelUtil<BrewWineCategory>(BrewWineCategory.class);
        util.exportExcel(response, list, "é…’å“ç§ç±»数据");
    }

    /**
     * 获取é…’å“ç§ç±»详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(brewWineCategoryService.selectBrewWineCategoryByCategoryId(categoryId));
    }

    /**
     * 新增é…’å“ç§ç±»
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "é…’å“ç§ç±»", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewWineCategory brewWineCategory)
    {
        return toAjax(brewWineCategoryService.insertBrewWineCategory(brewWineCategory));
    }

    /**
     * 修改é…’å“ç§ç±»
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "é…’å“ç§ç±»", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewWineCategory brewWineCategory)
    {
        return toAjax(brewWineCategoryService.updateBrewWineCategory(brewWineCategory));
    }

    /**
     * 删除é…’å“ç§ç±»
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "é…’å“ç§ç±»", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(brewWineCategoryService.deleteBrewWineCategoryByCategoryIds(categoryIds));
    }
}
