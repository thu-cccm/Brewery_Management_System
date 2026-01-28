package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewBatchMaterial;

/**
 * 批次原料Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewBatchMaterialService 
{
    /**
     * ??批次原料
     * 
     * @param recordId 批次原料??
     * @return 批次原料
     */
    public BrewBatchMaterial selectBrewBatchMaterialByRecordId(Long recordId);

    /**
     * ??批次原料??
     * 
     * @param brewBatchMaterial 批次原料
     * @return 批次原料??
     */
    public List<BrewBatchMaterial> selectBrewBatchMaterialList(BrewBatchMaterial brewBatchMaterial);

    /**
     * ??批次原料
     * 
     * @param brewBatchMaterial 批次原料
     * @return ??
     */
    public int insertBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial);

    /**
     * ??批次原料
     * 
     * @param brewBatchMaterial 批次原料
     * @return ??
     */
    public int updateBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial);

    /**
     * ????批次原料
     * 
     * @param recordIds ?????批次原料????
     * @return ??
     */
    public int deleteBrewBatchMaterialByRecordIds(Long[] recordIds);

    /**
     * ??批次原料??
     * 
     * @param recordId 批次原料??
     * @return ??
     */
    public int deleteBrewBatchMaterialByRecordId(Long recordId);
}
