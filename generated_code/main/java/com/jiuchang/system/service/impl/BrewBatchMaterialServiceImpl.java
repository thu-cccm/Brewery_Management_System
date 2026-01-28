package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewBatchMaterialMapper;
import com.jiuchang.system.domain.BrewBatchMaterial;
import com.jiuchang.system.service.IBrewBatchMaterialService;

/**
 * æ‰¹æ¬¡åŽŸæ–™Service业务层处理
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
     * 查询æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param recordId æ‰¹æ¬¡åŽŸæ–™主键
     * @return æ‰¹æ¬¡åŽŸæ–™
     */
    @Override
    public BrewBatchMaterial selectBrewBatchMaterialByRecordId(Long recordId)
    {
        return brewBatchMaterialMapper.selectBrewBatchMaterialByRecordId(recordId);
    }

    /**
     * 查询æ‰¹æ¬¡åŽŸæ–™列表
     * 
     * @param brewBatchMaterial æ‰¹æ¬¡åŽŸæ–™
     * @return æ‰¹æ¬¡åŽŸæ–™
     */
    @Override
    public List<BrewBatchMaterial> selectBrewBatchMaterialList(BrewBatchMaterial brewBatchMaterial)
    {
        return brewBatchMaterialMapper.selectBrewBatchMaterialList(brewBatchMaterial);
    }

    /**
     * 新增æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param brewBatchMaterial æ‰¹æ¬¡åŽŸæ–™
     * @return 结果
     */
    @Override
    public int insertBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial)
    {
        brewBatchMaterial.setCreateTime(DateUtils.getNowDate());
        return brewBatchMaterialMapper.insertBrewBatchMaterial(brewBatchMaterial);
    }

    /**
     * 修改æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param brewBatchMaterial æ‰¹æ¬¡åŽŸæ–™
     * @return 结果
     */
    @Override
    public int updateBrewBatchMaterial(BrewBatchMaterial brewBatchMaterial)
    {
        brewBatchMaterial.setUpdateTime(DateUtils.getNowDate());
        return brewBatchMaterialMapper.updateBrewBatchMaterial(brewBatchMaterial);
    }

    /**
     * 批量删除æ‰¹æ¬¡åŽŸæ–™
     * 
     * @param recordIds 需要删除的æ‰¹æ¬¡åŽŸæ–™主键
     * @return 结果
     */
    @Override
    public int deleteBrewBatchMaterialByRecordIds(Long[] recordIds)
    {
        return brewBatchMaterialMapper.deleteBrewBatchMaterialByRecordIds(recordIds);
    }

    /**
     * 删除æ‰¹æ¬¡åŽŸæ–™信息
     * 
     * @param recordId æ‰¹æ¬¡åŽŸæ–™主键
     * @return 结果
     */
    @Override
    public int deleteBrewBatchMaterialByRecordId(Long recordId)
    {
        return brewBatchMaterialMapper.deleteBrewBatchMaterialByRecordId(recordId);
    }
}
