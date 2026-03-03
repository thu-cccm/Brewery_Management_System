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
import com.jiuchang.system.domain.BrewEquipment;
import com.jiuchang.system.service.IBrewEquipmentService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 设备台账Controller
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@RestController
@RequestMapping("/brew/equipment")
public class BrewEquipmentController extends BaseController
{
    @Autowired
    private IBrewEquipmentService brewEquipmentService;

    /**
     * 查询设备台账列表
     */
    @PreAuthorize("@ss.hasPermi('brew:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewEquipment brewEquipment)
    {
        startPage();
        List<BrewEquipment> list = brewEquipmentService.selectBrewEquipmentList(brewEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备台账列表
     */
    @PreAuthorize("@ss.hasPermi('brew:equipment:export')")
    @Log(title = "设备台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewEquipment brewEquipment)
    {
        List<BrewEquipment> list = brewEquipmentService.selectBrewEquipmentList(brewEquipment);
        ExcelUtil<BrewEquipment> util = new ExcelUtil<BrewEquipment>(BrewEquipment.class);
        util.exportExcel(response, list, "设备台账数据");
    }

    /**
     * 获取设备台账详细信息
     */
    @PreAuthorize("@ss.hasPermi('brew:equipment:query')")
    @GetMapping(value = "/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId)
    {
        return success(brewEquipmentService.selectBrewEquipmentByEquipmentId(equipmentId));
    }

    /**
     * 新增设备台账
     */
    @PreAuthorize("@ss.hasPermi('brew:equipment:add')")
    @Log(title = "设备台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewEquipment brewEquipment)
    {
        return toAjax(brewEquipmentService.insertBrewEquipment(brewEquipment));
    }

    /**
     * 修改设备台账
     */
    @PreAuthorize("@ss.hasPermi('brew:equipment:edit')")
    @Log(title = "设备台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewEquipment brewEquipment)
    {
        return toAjax(brewEquipmentService.updateBrewEquipment(brewEquipment));
    }

    /**
     * 删除设备台账
     */
    @PreAuthorize("@ss.hasPermi('brew:equipment:remove')")
    @Log(title = "设备台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{equipmentIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentIds)
    {
        return toAjax(brewEquipmentService.deleteBrewEquipmentByEquipmentIds(equipmentIds));
    }
}
