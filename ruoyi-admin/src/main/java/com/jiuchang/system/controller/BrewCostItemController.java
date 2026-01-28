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
import com.jiuchang.system.domain.BrewCostItem;
import com.jiuchang.system.service.IBrewCostItemService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 成本项配置Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/costitem")
public class BrewCostItemController extends BaseController
{
    @Autowired
    private IBrewCostItemService brewCostItemService;

    /**
     * 查询æˆæœ¬é¡¹列表
     */
    @PreAuthorize("@ss.hasPermi('system:costitem:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewCostItem brewCostItem)
    {
        startPage();
        List<BrewCostItem> list = brewCostItemService.selectBrewCostItemList(brewCostItem);
        return getDataTable(list);
    }

    /**
     * 导出æˆæœ¬é¡¹列表
     */
    @PreAuthorize("@ss.hasPermi('system:costitem:export')")
    @Log(title = "æˆæœ¬é¡¹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewCostItem brewCostItem)
    {
        List<BrewCostItem> list = brewCostItemService.selectBrewCostItemList(brewCostItem);
        ExcelUtil<BrewCostItem> util = new ExcelUtil<BrewCostItem>(BrewCostItem.class);
        util.exportExcel(response, list, "æˆæœ¬é¡¹数据");
    }

    /**
     * 获取æˆæœ¬é¡¹详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:costitem:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return success(brewCostItemService.selectBrewCostItemByItemId(itemId));
    }

    /**
     * 新增æˆæœ¬é¡¹
     */
    @PreAuthorize("@ss.hasPermi('system:costitem:add')")
    @Log(title = "æˆæœ¬é¡¹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewCostItem brewCostItem)
    {
        return toAjax(brewCostItemService.insertBrewCostItem(brewCostItem));
    }

    /**
     * 修改æˆæœ¬é¡¹
     */
    @PreAuthorize("@ss.hasPermi('system:costitem:edit')")
    @Log(title = "æˆæœ¬é¡¹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewCostItem brewCostItem)
    {
        return toAjax(brewCostItemService.updateBrewCostItem(brewCostItem));
    }

    /**
     * 删除æˆæœ¬é¡¹
     */
    @PreAuthorize("@ss.hasPermi('system:costitem:remove')")
    @Log(title = "æˆæœ¬é¡¹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds)
    {
        return toAjax(brewCostItemService.deleteBrewCostItemByItemIds(itemIds));
    }
}
