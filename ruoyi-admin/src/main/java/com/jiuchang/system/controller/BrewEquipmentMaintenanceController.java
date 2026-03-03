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
import com.jiuchang.system.domain.BrewEquipmentMaintenance;
import com.jiuchang.system.service.IBrewEquipmentMaintenanceService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 维保记录Controller
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@RestController
@RequestMapping("/brew/maintenance")
public class BrewEquipmentMaintenanceController extends BaseController
{
    @Autowired
    private IBrewEquipmentMaintenanceService brewEquipmentMaintenanceService;

    /**
     * 查询维保记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:maintenance:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        startPage();
        List<BrewEquipmentMaintenance> list = brewEquipmentMaintenanceService.selectBrewEquipmentMaintenanceList(brewEquipmentMaintenance);
        return getDataTable(list);
    }

    /**
     * 导出维保记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:maintenance:export')")
    @Log(title = "维保记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        List<BrewEquipmentMaintenance> list = brewEquipmentMaintenanceService.selectBrewEquipmentMaintenanceList(brewEquipmentMaintenance);
        ExcelUtil<BrewEquipmentMaintenance> util = new ExcelUtil<BrewEquipmentMaintenance>(BrewEquipmentMaintenance.class);
        util.exportExcel(response, list, "维保记录数据");
    }

    /**
     * 获取维保记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:maintenance:query')")
    @GetMapping(value = "/{maintenanceId}")
    public AjaxResult getInfo(@PathVariable("maintenanceId") Long maintenanceId)
    {
        return success(brewEquipmentMaintenanceService.selectBrewEquipmentMaintenanceByMaintenanceId(maintenanceId));
    }

    /**
     * 新增维保记录
     */
    @PreAuthorize("@ss.hasPermi('system:maintenance:add')")
    @Log(title = "维保记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        return toAjax(brewEquipmentMaintenanceService.insertBrewEquipmentMaintenance(brewEquipmentMaintenance));
    }

    /**
     * 修改维保记录
     */
    @PreAuthorize("@ss.hasPermi('system:maintenance:edit')")
    @Log(title = "维保记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        return toAjax(brewEquipmentMaintenanceService.updateBrewEquipmentMaintenance(brewEquipmentMaintenance));
    }

    /**
     * 删除维保记录
     */
    @PreAuthorize("@ss.hasPermi('system:maintenance:remove')")
    @Log(title = "维保记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{maintenanceIds}")
    public AjaxResult remove(@PathVariable Long[] maintenanceIds)
    {
        return toAjax(brewEquipmentMaintenanceService.deleteBrewEquipmentMaintenanceByMaintenanceIds(maintenanceIds));
    }
}
