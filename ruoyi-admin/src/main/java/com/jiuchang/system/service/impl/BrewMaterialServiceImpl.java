package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewMaterialMapper;
import com.jiuchang.system.domain.BrewMaterial;
import com.jiuchang.system.service.IBrewMaterialService;

/**
 * 原料信?Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewMaterialServiceImpl implements IBrewMaterialService 
{
    @Autowired
    private BrewMaterialMapper brewMaterialMapper;

    /**
     * ??原料信?
     * 
     * @param materialId 原料信???
     * @return 原料信?
     */
    @Override
    public BrewMaterial selectBrewMaterialByMaterialId(Long materialId)
    {
        return brewMaterialMapper.selectBrewMaterialByMaterialId(materialId);
    }

    /**
     * ??原料信???
     * 
     * @param brewMaterial 原料信?
     * @return 原料信?
     */
    @Override
    public List<BrewMaterial> selectBrewMaterialList(BrewMaterial brewMaterial)
    {
        return brewMaterialMapper.selectBrewMaterialList(brewMaterial);
    }

    /**
     * ??原料信?
     * 
     * @param brewMaterial 原料信?
     * @return ??
     */
    @Override
    public int insertBrewMaterial(BrewMaterial brewMaterial)
    {
        brewMaterial.setCreateTime(DateUtils.getNowDate());
        return brewMaterialMapper.insertBrewMaterial(brewMaterial);
    }

    /**
     * ??原料信?
     * 
     * @param brewMaterial 原料信?
     * @return ??
     */
    @Override
    public int updateBrewMaterial(BrewMaterial brewMaterial)
    {
        brewMaterial.setUpdateTime(DateUtils.getNowDate());
        return brewMaterialMapper.updateBrewMaterial(brewMaterial);
    }

    /**
     * ????原料信?
     * 
     * @param materialIds ?????原料信???
     * @return ??
     */
    @Override
    public int deleteBrewMaterialByMaterialIds(Long[] materialIds)
    {
        return brewMaterialMapper.deleteBrewMaterialByMaterialIds(materialIds);
    }

    /**
     * ??原料信???
     * 
     * @param materialId 原料信???
     * @return ??
     */
    @Override
    public int deleteBrewMaterialByMaterialId(Long materialId)
    {
        return brewMaterialMapper.deleteBrewMaterialByMaterialId(materialId);
    }
}
