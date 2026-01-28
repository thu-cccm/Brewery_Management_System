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
import com.jiuchang.system.domain.BrewStockRecord;
import com.jiuchang.system.service.IBrewStockRecordService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 库存变动记录Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/stock")
public class BrewStockRecordController extends BaseController
{
    @Autowired
    private IBrewStockRecordService brewStockRecordService;

    /**
     * 查询åº“å­˜å˜åŠ¨列表
     */
    @PreAuthorize("@ss.hasPermi('system:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewStockRecord brewStockRecord)
    {
        startPage();
        List<BrewStockRecord> list = brewStockRecordService.selectBrewStockRecordList(brewStockRecord);
        return getDataTable(list);
    }

    /**
     * 导出åº“å­˜å˜åŠ¨列表
     */
    @PreAuthorize("@ss.hasPermi('system:stock:export')")
    @Log(title = "åº“å­˜å˜åŠ¨", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewStockRecord brewStockRecord)
    {
        List<BrewStockRecord> list = brewStockRecordService.selectBrewStockRecordList(brewStockRecord);
        ExcelUtil<BrewStockRecord> util = new ExcelUtil<BrewStockRecord>(BrewStockRecord.class);
        util.exportExcel(response, list, "åº“å­˜å˜åŠ¨数据");
    }

    /**
     * 获取åº“å­˜å˜åŠ¨详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stock:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(brewStockRecordService.selectBrewStockRecordByRecordId(recordId));
    }

    /**
     * 新增åº“å­˜å˜åŠ¨
     */
    @PreAuthorize("@ss.hasPermi('system:stock:add')")
    @Log(title = "åº“å­˜å˜åŠ¨", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewStockRecord brewStockRecord)
    {
        return toAjax(brewStockRecordService.insertBrewStockRecord(brewStockRecord));
    }

    /**
     * 修改åº“å­˜å˜åŠ¨
     */
    @PreAuthorize("@ss.hasPermi('system:stock:edit')")
    @Log(title = "åº“å­˜å˜åŠ¨", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewStockRecord brewStockRecord)
    {
        return toAjax(brewStockRecordService.updateBrewStockRecord(brewStockRecord));
    }

    /**
     * 删除åº“å­˜å˜åŠ¨
     */
    @PreAuthorize("@ss.hasPermi('system:stock:remove')")
    @Log(title = "åº“å­˜å˜åŠ¨", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(brewStockRecordService.deleteBrewStockRecordByRecordIds(recordIds));
    }
}
