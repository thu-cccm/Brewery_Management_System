package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewBatchMaterialMapper;
import com.jiuchang.system.domain.BrewBatchMaterial;
import com.jiuchang.system.service.IBrewBatchMaterialService;

/**
 * 批次原料Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewBatchMaterialServiceImpl implements IBrewBatchMaterialService 
{
    @Autowired
    private BrewBatchMaterialMapper brewBatchMaterialMapper;

    /**
     * ??批次原料
     * 
     * @param recordId 批次原料??
     * @return 批次原料
     */
    @Override
    public BrewBatchMaterial selectBrewBatchMaterialByRecordId(Long recordId)
    {
        return brewBatchMaterialMapper.selectBrewBatchMaterialByRecordId(recordId);
    }

    /**
     * ??批次原料??
     * 
     * @param brewBatchMaterial 批次原料
     * @return 批次原料
     */
    @Override
    public List<BrewBatchMaterial> selectBrewBatchMaterialList(BrewBatchMaterial brewBatchMaterial)
    {
        return brewBatchMaterialMapper.selectBrewBatchMaterialList(brewBatchMaterial);
    }

    /**
     * ??批次原料
     * 
     * @param brewBatchMaterial 批次原料
     * @return ??
     */
    @Override
    public int insertBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial)
    {
        brewBatchMaterial.setCreateTime(DateUtils.getNowDate());
        return brewBatchMaterialMapper.insertBrewBatchMaterial(brewBatchMaterial);
    }

    /**
     * ??批次原料
     * 
     * @param brewBatchMaterial 批次原料
     * @return ??
     */
    @Override
    public int updateBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial)
    {
        brewBatchMaterial.setUpdateTime(DateUtils.getNowDate());
        return brewBatchMaterialMapper.updateBrewBatchMaterial(brewBatchMaterial);
    }

    /**
     * ????批次原料
     * 
     * @param recordIds ?????批次原料??
     * @return ??
     */
    @Override
    public int deleteBrewBatchMaterialByRecordIds(Long[] recordIds)
    {
        return brewBatchMaterialMapper.deleteBrewBatchMaterialByRecordIds(recordIds);
    }

    /**
     * ??批次原料??
     * 
     * @param recordId 批次原料??
     * @return ??
     */
    @Override
    public int deleteBrewBatchMaterialByRecordId(Long recordId)
    {
        return brewBatchMaterialMapper.deleteBrewBatchMaterialByRecordId(recordId);
    }
}
