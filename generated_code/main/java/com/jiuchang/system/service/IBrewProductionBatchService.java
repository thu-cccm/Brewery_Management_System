package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionBatch;

/**
 * ç”Ÿäº§æ‰¹æ¬¡Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewProductionBatchService 
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
     * 批量删除ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param batchIds 需要删除的ç”Ÿäº§æ‰¹æ¬¡主键集合
     * @return 结果
     */
    public int deleteBrewProductionBatchByBatchIds(Long[] batchIds);

    /**
     * 删除ç”Ÿäº§æ‰¹æ¬¡信息
     * 
     * @param batchId ç”Ÿäº§æ‰¹æ¬¡主键
     * @return 结果
     */
    public int deleteBrewProductionBatchByBatchId(Long batchId);
}
