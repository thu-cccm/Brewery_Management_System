package com.jiuchang.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jiuchang.common.core.controller.BaseController;
import com.jiuchang.common.core.domain.AjaxResult;
import com.jiuchang.system.domain.vo.BrewTraceVo;
import com.jiuchang.system.service.*;
import com.jiuchang.system.domain.*;

@RestController
@RequestMapping("/brew/trace")
public class BrewTraceController extends BaseController {

    @Autowired
    private IBrewProductionBatchService batchService;
    @Autowired
    private IBrewProductionPlanService planService;
    @Autowired
    private IBrewProcessMonitorService monitorService;
    @Autowired
    private IBrewQualityRecordService qualityService;
    @Autowired
    private IBrewBatchYieldService yieldService;

    /**
     * 获取全链路追溯数据
     */
    @GetMapping("/{batchId}")
    public AjaxResult getTraceData(@PathVariable("batchId") Long batchId) {
        BrewTraceVo traceVo = new BrewTraceVo();

        // 1. 获取批次信息
        BrewProductionBatch batch = batchService.selectBrewProductionBatchByBatchId(batchId);
        traceVo.setBatch(batch);

        if (batch != null) {
            // 2. 获取关联计划
            if (batch.getPlanId() != null) {
                BrewProductionPlan plan = planService.selectBrewProductionPlanByPlanId(batch.getPlanId());
                traceVo.setPlan(plan);
            }

            // 3. 获取过程监控数据
            BrewProcessMonitor monitorQuery = new BrewProcessMonitor();
            monitorQuery.setBatchId(batchId);
            traceVo.setProcesses(monitorService.selectBrewProcessMonitorList(monitorQuery));

            // 4. 获取质检记录
            BrewQualityRecord qualityQuery = new BrewQualityRecord();
            qualityQuery.setBatchNo(batch.getBatchNo());
            traceVo.setQualityChecks(qualityService.selectBrewQualityRecordList(qualityQuery));

            // 5. 获取损耗分析
            BrewBatchYield yieldQuery = new BrewBatchYield();
            yieldQuery.setBatchId(batchId);
            traceVo.setYields(yieldService.selectBrewBatchYieldList(yieldQuery));
        }

        return success(traceVo);
    }
}
