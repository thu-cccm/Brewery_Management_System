package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionBatch;

/**
 * 生产批次Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewProductionBatchService 
{
    /**
     * ??生产批次
     * 
     * @param batchId 生产批次??
     * @return 生产批次
     */
    public BrewProductionBatch selectBrewProductionBatchByBatchId(Long batchId);

    /**
     * ??生产批次??
     * 
     * @param brewProductionBatch 生产批次
     * @return 生产批次??
     */
    public List<BrewProductionBatch> selectBrewProductionBatchList(BrewProductionBatch brewProductionBatch);

    /**
     * ??生产批次
     * 
     * @param brewProductionBatch 生产批次
     * @return ??
     */
    public int insertBrewProductionBatch(BrewProductionBatch brewProductionBatch);

    /**
     * ??生产批次
     * 
     * @param brewProductionBatch 生产批次
     * @return ??
     */
    public int updateBrewProductionBatch(BrewProductionBatch brewProductionBatch);

    /**
     * ????生产批次
     * 
     * @param batchIds ?????生产批次????
     * @return ??
     */
    public int deleteBrewProductionBatchByBatchIds(Long[] batchIds);

    /**
     * ??生产批次??
     * 
     * @param batchId 生产批次??
     * @return ??
     */
    public int deleteBrewProductionBatchByBatchId(Long batchId);

    /**
     * 启动生产（生成批次号、扣减库存、记录库存流水）
     * 
     * @param planId 生产计划ID
     * @param materialList 原料用量列表
     * @param operatorName 操作人
     * @return 生成的批次号
     */
    public String startProduction(Long planId, java.util.List<java.util.Map<String, Object>> materialList, String operatorName);
}
