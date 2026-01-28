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
import com.jiuchang.system.domain.BrewQualityRecord;
import com.jiuchang.system.service.IBrewQualityRecordService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 质检记录Controller
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
     * 查询质检记录列表
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
     * 导出质检记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:quality:export')")
    @Log(title = "质检记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewQualityRecord brewQualityRecord)
    {
        List<BrewQualityRecord> list = brewQualityRecordService.selectBrewQualityRecordList(brewQualityRecord);
        ExcelUtil<BrewQualityRecord> util = new ExcelUtil<BrewQualityRecord>(BrewQualityRecord.class);
        util.exportExcel(response, list, "质检记录数据");
    }

    /**
     * 获取质检记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:quality:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(brewQualityRecordService.selectBrewQualityRecordByRecordId(recordId));
    }

    /**
     * 新增质检记录
     */
    @PreAuthorize("@ss.hasPermi('system:quality:add')")
    @Log(title = "质检记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewQualityRecord brewQualityRecord)
    {
        return toAjax(brewQualityRecordService.insertBrewQualityRecord(brewQualityRecord));
    }

    /**
     * 修改质检记录
     */
    @PreAuthorize("@ss.hasPermi('system:quality:edit')")
    @Log(title = "质检记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewQualityRecord brewQualityRecord)
    {
        return toAjax(brewQualityRecordService.updateBrewQualityRecord(brewQualityRecord));
    }

    /**
     * 删除质检记录
     */
    @PreAuthorize("@ss.hasPermi('system:quality:remove')")
    @Log(title = "质检记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(brewQualityRecordService.deleteBrewQualityRecordByRecordIds(recordIds));
    }
}
