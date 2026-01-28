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
}
