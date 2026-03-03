package com.jiuchang.system.domain.vo;

import com.jiuchang.system.domain.*;
import java.util.List;

/**
 * 全链路追溯 VO
 */
public class BrewTraceVo {
    private BrewProductionPlan plan;
    private BrewProductionBatch batch;
    private List<BrewProcessMonitor> processes;
    private List<BrewQualityRecord> qualityChecks;
    private List<BrewBatchYield> yields;

    public BrewProductionPlan getPlan() {
        return plan;
    }

    public void setPlan(BrewProductionPlan plan) {
        this.plan = plan;
    }

    public BrewProductionBatch getBatch() {
        return batch;
    }

    public void setBatch(BrewProductionBatch batch) {
        this.batch = batch;
    }

    public List<BrewProcessMonitor> getProcesses() {
        return processes;
    }

    public void setProcesses(List<BrewProcessMonitor> processes) {
        this.processes = processes;
    }

    public List<BrewQualityRecord> getQualityChecks() {
        return qualityChecks;
    }

    public void setQualityChecks(List<BrewQualityRecord> qualityChecks) {
        this.qualityChecks = qualityChecks;
    }

    public List<BrewBatchYield> getYields() {
        return yields;
    }

    public void setYields(List<BrewBatchYield> yields) {
        this.yields = yields;
    }
}
