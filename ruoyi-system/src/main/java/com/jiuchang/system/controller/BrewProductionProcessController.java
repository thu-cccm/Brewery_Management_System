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
import com.jiuchang.system.domain.BrewProductionProcess;
import com.jiuchang.system.service.IBrewProductionProcessService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 生产过程记录Controller
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@RestController
@RequestMapping("/system/process")
public class BrewProductionProcessController extends BaseController
{
    @Autowired
    private IBrewProductionProcessService brewProductionProcessService;

    /**
     * 查询ç”Ÿäº§è¿‡ç¨‹列表
     */
    @PreAuthorize("@ss.hasPermi('system:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewProductionProcess brewProductionProcess)
    {
        startPage();
        List<BrewProductionProcess> list = brewProductionProcessService.selectBrewProductionProcessList(brewProductionProcess);
        return getDataTable(list);
    }

    /**
     * 导出ç”Ÿäº§è¿‡ç¨‹列表
     */
    @PreAuthorize("@ss.hasPermi('system:process:export')")
    @Log(title = "ç”Ÿäº§è¿‡ç¨‹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewProductionProcess brewProductionProcess)
    {
        List<BrewProductionProcess> list = brewProductionProcessService.selectBrewProductionProcessList(brewProductionProcess);
        ExcelUtil<BrewProductionProcess> util = new ExcelUtil<BrewProductionProcess>(BrewProductionProcess.class);
        util.exportExcel(response, list, "ç”Ÿäº§è¿‡ç¨‹数据");
    }

    /**
     * 获取ç”Ÿäº§è¿‡ç¨‹详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:process:query')")
    @GetMapping(value = "/{processId}")
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        return success(brewProductionProcessService.selectBrewProductionProcessByProcessId(processId));
    }

    /**
     * 新增ç”Ÿäº§è¿‡ç¨‹
     */
    @PreAuthorize("@ss.hasPermi('system:process:add')")
    @Log(title = "ç”Ÿäº§è¿‡ç¨‹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewProductionProcess brewProductionProcess)
    {
        return toAjax(brewProductionProcessService.insertBrewProductionProcess(brewProductionProcess));
    }

    /**
     * 修改ç”Ÿäº§è¿‡ç¨‹
     */
    @PreAuthorize("@ss.hasPermi('system:process:edit')")
    @Log(title = "ç”Ÿäº§è¿‡ç¨‹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewProductionProcess brewProductionProcess)
    {
        return toAjax(brewProductionProcessService.updateBrewProductionProcess(brewProductionProcess));
    }

    /**
     * 删除ç”Ÿäº§è¿‡ç¨‹
     */
    @PreAuthorize("@ss.hasPermi('system:process:remove')")
    @Log(title = "ç”Ÿäº§è¿‡ç¨‹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{processIds}")
    public AjaxResult remove(@PathVariable Long[] processIds)
    {
        return toAjax(brewProductionProcessService.deleteBrewProductionProcessByProcessIds(processIds));
    }
}
