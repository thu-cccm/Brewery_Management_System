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
import com.jiuchang.system.domain.BrewQualityRecord;
import com.jiuchang.system.service.IBrewQualityRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * è´¨æ£€è®°å½•Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/quality")
public class BrewQualityRecordController extends BaseController
{
    @Autowired
    private IBrewQualityRecordService brewQualityRecordService;

    /**
     * 查询è´¨æ£€è®°å½•列表
     */
    @PreAuthorize("@ss.hasPermi('system:quality:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewQualityRecord brewQualityRecord)
    {
        startPage();
        List<BrewQualityRecord> list = brewQualityRecordService.selectBrewQualityRecordList(brewQualityRecord);
        return getDataTable(list);
    }

    /**
     * 导出è´¨æ£€è®°å½•列表
     */
    @PreAuthorize("@ss.hasPermi('system:quality:export')")
    @Log(title = "è´¨æ£€è®°å½•", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewQualityRecord brewQualityRecord)
    {
        List<BrewQualityRecord> list = brewQualityRecordService.selectBrewQualityRecordList(brewQualityRecord);
        ExcelUtil<BrewQualityRecord> util = new ExcelUtil<BrewQualityRecord>(BrewQualityRecord.class);
        util.exportExcel(response, list, "è´¨æ£€è®°å½•数据");
    }

    /**
     * 获取è´¨æ£€è®°å½•详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:quality:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(brewQualityRecordService.selectBrewQualityRecordByRecordId(recordId));
    }

    /**
     * 新增è´¨æ£€è®°å½•
     */
    @PreAuthorize("@ss.hasPermi('system:quality:add')")
    @Log(title = "è´¨æ£€è®°å½•", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewQualityRecord brewQualityRecord)
    {
        return toAjax(brewQualityRecordService.insertBrewQualityRecord(brewQualityRecord));
    }

    /**
     * 修改è´¨æ£€è®°å½•
     */
    @PreAuthorize("@ss.hasPermi('system:quality:edit')")
    @Log(title = "è´¨æ£€è®°å½•", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewQualityRecord brewQualityRecord)
    {
        return toAjax(brewQualityRecordService.updateBrewQualityRecord(brewQualityRecord));
    }

    /**
     * 删除è´¨æ£€è®°å½•
     */
    @PreAuthorize("@ss.hasPermi('system:quality:remove')")
    @Log(title = "è´¨æ£€è®°å½•", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(brewQualityRecordService.deleteBrewQualityRecordByRecordIds(recordIds));
    }
}
