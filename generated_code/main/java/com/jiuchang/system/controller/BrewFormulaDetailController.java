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
import com.jiuchang.system.domain.BrewFormulaDetail;
import com.jiuchang.system.service.IBrewFormulaDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * é…æ–¹æ˜Žç»†Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/detail")
public class BrewFormulaDetailController extends BaseController
{
    @Autowired
    private IBrewFormulaDetailService brewFormulaDetailService;

    /**
     * 查询é…æ–¹æ˜Žç»†列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewFormulaDetail brewFormulaDetail)
    {
        startPage();
        List<BrewFormulaDetail> list = brewFormulaDetailService.selectBrewFormulaDetailList(brewFormulaDetail);
        return getDataTable(list);
    }

    /**
     * 导出é…æ–¹æ˜Žç»†列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "é…æ–¹æ˜Žç»†", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewFormulaDetail brewFormulaDetail)
    {
        List<BrewFormulaDetail> list = brewFormulaDetailService.selectBrewFormulaDetailList(brewFormulaDetail);
        ExcelUtil<BrewFormulaDetail> util = new ExcelUtil<BrewFormulaDetail>(BrewFormulaDetail.class);
        util.exportExcel(response, list, "é…æ–¹æ˜Žç»†数据");
    }

    /**
     * 获取é…æ–¹æ˜Žç»†详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(brewFormulaDetailService.selectBrewFormulaDetailByDetailId(detailId));
    }

    /**
     * 新增é…æ–¹æ˜Žç»†
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "é…æ–¹æ˜Žç»†", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewFormulaDetail brewFormulaDetail)
    {
        return toAjax(brewFormulaDetailService.insertBrewFormulaDetail(brewFormulaDetail));
    }

    /**
     * 修改é…æ–¹æ˜Žç»†
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "é…æ–¹æ˜Žç»†", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewFormulaDetail brewFormulaDetail)
    {
        return toAjax(brewFormulaDetailService.updateBrewFormulaDetail(brewFormulaDetail));
    }

    /**
     * 删除é…æ–¹æ˜Žç»†
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "é…æ–¹æ˜Žç»†", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(brewFormulaDetailService.deleteBrewFormulaDetailByDetailIds(detailIds));
    }
}
