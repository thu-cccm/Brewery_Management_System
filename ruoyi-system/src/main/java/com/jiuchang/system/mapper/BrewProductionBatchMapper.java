package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionBatch;
import com.jiuchang.system.domain.BrewBatchMaterial;

/**
 * 生产批次Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewProductionBatchMapper 
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
     * ??生产批次
     * 
     * @param batchId 生产批次??
     * @return ??
     */
    public int deleteBrewProductionBatchByBatchId(Long batchId);

    /**
     * 根据批次号查询生产批次
     */
    public BrewProductionBatch selectBrewProductionBatchByBatchNo(String batchNo);

    /**
     * 查询批次原料列表
     */
    public List<BrewBatchMaterial> selectBrewBatchMaterialList(BrewBatchMaterial brewBatchMaterial);

    /**
     * 批量删除生产批次
     * 
     * @param batchIds 需要删除的生产批次主键集合
     * @return 结果
     */
    public int deleteBrewProductionBatchByBatchIds(Long[] batchIds);

    /**
     * ????批次原料
     * 
     * @param batchIds ???????????
     * @return ??
     */
    public int deleteBrewBatchMaterialByBatchNos(Long[] batchIds);
    
    /**
     * ????批次原料
     * 
     * @param brewBatchMaterialList 批次原料??
     * @return ??
     */
    public int batchBrewBatchMaterial(List<BrewBatchMaterial> brewBatchMaterialList);
    

    /**
     * ??生产批次????批次原料??
     * 
     * @param batchId 生产批次ID
     * @return ??
     */
    public int deleteBrewBatchMaterialByBatchNo(Long batchId);
}
