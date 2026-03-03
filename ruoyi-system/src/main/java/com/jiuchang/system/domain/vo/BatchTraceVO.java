package com.jiuchang.system.domain.vo;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionBatch;
import com.jiuchang.system.domain.BrewProductionProcess;
import com.jiuchang.system.domain.BrewQualityRecord;
import com.jiuchang.system.domain.BrewBatchMaterial;
import com.jiuchang.system.domain.BrewBatchCost;

/**
 * 生产批次溯源视图对象
 */
public class BatchTraceVO {
    /** 批次基本信息 */
    private BrewProductionBatch batchInfo;
    
    /** 领料记录 */
    private List<BrewBatchMaterial> materials;
    
    /** 生产过程记录 */
    private List<BrewProductionProcess> processes;
    
    /** 质检记录 */
    private List<BrewQualityRecord> qualityRecords;
    
    /** 成本明细 */
    private List<BrewBatchCost> costs;

    public BrewProductionBatch getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BrewProductionBatch batchInfo) {
        this.batchInfo = batchInfo;
    }

    public List<BrewBatchMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<BrewBatchMaterial> materials) {
        this.materials = materials;
    }

    public List<BrewProductionProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(List<BrewProductionProcess> processes) {
        this.processes = processes;
    }

    public List<BrewQualityRecord> getQualityRecords() {
        return qualityRecords;
    }

    public void setQualityRecords(List<BrewQualityRecord> qualityRecords) {
        this.qualityRecords = qualityRecords;
    }

    public List<BrewBatchCost> getCosts() {
        return costs;
    }

    public void setCosts(List<BrewBatchCost> costs) {
        this.costs = costs;
    }
}
