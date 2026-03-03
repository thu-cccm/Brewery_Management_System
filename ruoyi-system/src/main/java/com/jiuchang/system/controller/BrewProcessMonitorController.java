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
import com.jiuchang.system.domain.BrewProcessMonitor;
import com.jiuchang.system.service.IBrewProcessMonitorService;
import com.jiuchang.common.utils.poi.ExcelUtil;
import com.jiuchang.common.core.page.TableDataInfo;

/**
 * 生产过程监控Controller
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@RestController
@RequestMapping("/brew/monitor")
public class BrewProcessMonitorController extends BaseController
{
    @Autowired
    private IBrewProcessMonitorService brewProcessMonitorService;

    /**
     * 查询生产过程监控列表
     */
    @PreAuthorize("@ss.hasPermi('brew:monitor:list')")
    @GetMapping("/list")
    public TableDataInfo list(BrewProcessMonitor brewProcessMonitor)
    {
        startPage();
        List<BrewProcessMonitor> list = brewProcessMonitorService.selectBrewProcessMonitorList(brewProcessMonitor);
        return getDataTable(list);
    }

    /**
     * 导出生产过程监控列表
     */
    @PreAuthorize("@ss.hasPermi('brew:monitor:export')")
    @Log(title = "生产过程监控", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrewProcessMonitor brewProcessMonitor)
    {
        List<BrewProcessMonitor> list = brewProcessMonitorService.selectBrewProcessMonitorList(brewProcessMonitor);
        ExcelUtil<BrewProcessMonitor> util = new ExcelUtil<BrewProcessMonitor>(BrewProcessMonitor.class);
        util.exportExcel(response, list, "生产过程监控数据");
    }

    /**
     * 获取生产过程监控详细信息
     */
    @PreAuthorize("@ss.hasPermi('brew:monitor:query')")
    @GetMapping(value = "/{monitorId}")
    public AjaxResult getInfo(@PathVariable("monitorId") Long monitorId)
    {
        return success(brewProcessMonitorService.selectBrewProcessMonitorByMonitorId(monitorId));
    }

    /**
     * 新增生产过程监控
     */
    @PreAuthorize("@ss.hasPermi('brew:monitor:add')")
    @Log(title = "生产过程监控", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrewProcessMonitor brewProcessMonitor)
    {
        return toAjax(brewProcessMonitorService.insertBrewProcessMonitor(brewProcessMonitor));
    }

    /**
     * 修改生产过程监控
     */
    @PreAuthorize("@ss.hasPermi('brew:monitor:edit')")
    @Log(title = "生产过程监控", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrewProcessMonitor brewProcessMonitor)
    {
        return toAjax(brewProcessMonitorService.updateBrewProcessMonitor(brewProcessMonitor));
    }

    /**
     * 删除生产过程监控
     */
    @PreAuthorize("@ss.hasPermi('brew:monitor:remove')")
    @Log(title = "生产过程监控", businessType = BusinessType.DELETE)
	@DeleteMapping("/{monitorIds}")
    public AjaxResult remove(@PathVariable Long[] monitorIds)
    {
        return toAjax(brewProcessMonitorService.deleteBrewProcessMonitorByMonitorIds(monitorIds));
    }
}
