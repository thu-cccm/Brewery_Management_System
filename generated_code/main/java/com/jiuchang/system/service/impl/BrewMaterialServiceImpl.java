package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewMaterialMapper;
import com.jiuchang.system.domain.BrewMaterial;
import com.jiuchang.system.service.IBrewMaterialService;

/**
 * åŽŸæ–™ä¿¡æ¯Service业务层处理
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
     * 查询åŽŸæ–™ä¿¡æ¯
     * 
     * @param materialId åŽŸæ–™ä¿¡æ¯主键
     * @return åŽŸæ–™ä¿¡æ¯
     */
    @Override
    public BrewMaterial selectBrewMaterialByMaterialId(Long materialId)
    {
        return brewMaterialMapper.selectBrewMaterialByMaterialId(materialId);
    }

    /**
     * 查询åŽŸæ–™ä¿¡æ¯列表
     * 
     * @param brewMaterial åŽŸæ–™ä¿¡æ¯
     * @return åŽŸæ–™ä¿¡æ¯
     */
    @Override
    public List<BrewMaterial> selectBrewMaterialList(BrewMaterial brewMaterial)
    {
        return brewMaterialMapper.selectBrewMaterialList(brewMaterial);
    }

    /**
     * 新增åŽŸæ–™ä¿¡æ¯
     * 
     * @param brewMaterial åŽŸæ–™ä¿¡æ¯
     * @return 结果
     */
    @Override
    public int insertBrewMaterial(BrewMaterial brewMaterial)
    {
        brewMaterial.setCreateTime(DateUtils.getNowDate());
        return brewMaterialMapper.insertBrewMaterial(brewMaterial);
    }

    /**
     * 修改åŽŸæ–™ä¿¡æ¯
     * 
     * @param brewMaterial åŽŸæ–™ä¿¡æ¯
     * @return 结果
     */
    @Override
    public int updateBrewMaterial(BrewMaterial brewMaterial)
    {
        brewMaterial.setUpdateTime(DateUtils.getNowDate());
        return brewMaterialMapper.updateBrewMaterial(brewMaterial);
    }

    /**
     * 批量删除åŽŸæ–™ä¿¡æ¯
     * 
     * @param materialIds 需要删除的åŽŸæ–™ä¿¡æ¯主键
     * @return 结果
     */
    @Override
    public int deleteBrewMaterialByMaterialIds(Long[] materialIds)
    {
        return brewMaterialMapper.deleteBrewMaterialByMaterialIds(materialIds);
    }

    /**
     * 删除åŽŸæ–™ä¿¡æ¯信息
     * 
     * @param materialId åŽŸæ–™ä¿¡æ¯主键
     * @return 结果
     */
    @Override
    public int deleteBrewMaterialByMaterialId(Long materialId)
    {
        return brewMaterialMapper.deleteBrewMaterialByMaterialId(materialId);
    }
}
