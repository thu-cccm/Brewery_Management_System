package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionBatch;
import com.jiuchang.system.domain.BrewBatchMaterial;

/**
 * ç”Ÿäº§æ‰¹æ¬¡Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewProductionBatchMapper 
{
    /**
     * 查询ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param batchId ç”Ÿäº§æ‰¹æ¬¡主键
     * @return ç”Ÿäº§æ‰¹æ¬¡
     */
    public BrewProductionBatch selectBrewProductionBatchByBatchId(Long batchId);

    /**
     * 查询ç”Ÿäº§æ‰¹æ¬¡列表
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡
     * @return ç”Ÿäº§æ‰¹æ¬¡集合
     */
    public List<BrewProductionBatch> selectBrewProductionBatchList(BrewProductionBatch brewProductionBatch);

    /**
     * 新增ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡
     * @return 结果
     */
    public int insertBrewProductionBatch(BrewProductionBatch brewProductionBatch);

    /**
     * 修改ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡
     * @return 结果
     */
    public int updateBrewProductionBatch(BrewProductionBatch brewProductionBatch);

    /**
     * 删除ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param batchId ç”Ÿäº§æ‰¹æ¬¡主键
     * @return 结果
     */
    public int deleteBrewProductionBatchByBatchId(Long batchId);

    /**
     * 批量删除ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param batchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewProductionBatchByBatchIds(Long[] batchIds);

    /**
     * 批量删除æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param batchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewBatchMaterialByBatchNos(Long[] batchIds);
    
    /**
     * 批量新增æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param brewBatchMaterialList æ‰¹æ¬¡åŽŸæ–™列表
     * @return 结果
     */
    public int batchBrewBatchMaterial(List<BrewBatchMaterial> brewBatchMaterialList);
    

    /**
     * 通过ç”Ÿäº§æ‰¹æ¬¡主键删除æ‰¹æ¬¡åŽŸæ–™信息
     * 
     * @param batchId ç”Ÿäº§æ‰¹æ¬¡ID
     * @return 结果
     */
    public int deleteBrewBatchMaterialByBatchNo(Long batchId);
}
