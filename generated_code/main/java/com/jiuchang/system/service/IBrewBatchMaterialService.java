package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewBatchMaterial;

/**
 * æ‰¹æ¬¡åŽŸæ–™Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewBatchMaterialService 
{
    /**
     * 查询æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param recordId æ‰¹æ¬¡åŽŸæ–™主键
     * @return æ‰¹æ¬¡åŽŸæ–™
     */
    public BrewBatchMaterial selectBrewBatchMaterialByRecordId(Long recordId);

    /**
     * 查询æ‰¹æ¬¡åŽŸæ–™列表
     * 
     * @param brewBatchMaterial æ‰¹æ¬¡åŽŸæ–™
     * @return æ‰¹æ¬¡åŽŸæ–™集合
     */
    public List<BrewBatchMaterial> selectBrewBatchMaterialList(BrewBatchMaterial brewBatchMaterial);

    /**
     * 新增æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param brewBatchMaterial æ‰¹æ¬¡åŽŸæ–™
     * @return 结果
     */
    public int insertBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial);

    /**
     * 修改æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param brewBatchMaterial æ‰¹æ¬¡åŽŸæ–™
     * @return 结果
     */
    public int updateBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial);

    /**
     * 批量删除æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param recordIds 需要删除的æ‰¹æ¬¡åŽŸæ–™主键集合
     * @return 结果
     */
    public int deleteBrewBatchMaterialByRecordIds(Long[] recordIds);

    /**
     * 删除æ‰¹æ¬¡åŽŸæ–™信息
     * 
     * @param recordId æ‰¹æ¬¡åŽŸæ–™主键
     * @return 结果
     */
    public int deleteBrewBatchMaterialByRecordId(Long recordId);
}
