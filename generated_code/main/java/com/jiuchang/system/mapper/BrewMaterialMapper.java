package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewMaterial;

/**
 * åŽŸæ–™ä¿¡æ¯Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewMaterialMapper 
{
    /**
     * 查询åŽŸæ–™ä¿¡æ¯
     * 
     * @param materialId åŽŸæ–™ä¿¡æ¯主键
     * @return åŽŸæ–™ä¿¡æ¯
     */
    public BrewMaterial selectBrewMaterialByMaterialId(Long materialId);

    /**
     * 查询åŽŸæ–™ä¿¡æ¯列表
     * 
     * @param brewMaterial åŽŸæ–™ä¿¡æ¯
     * @return åŽŸæ–™ä¿¡æ¯集合
     */
    public List<BrewMaterial> selectBrewMaterialList(BrewMaterial brewMaterial);

    /**
     * 新增åŽŸæ–™ä¿¡æ¯
     * 
     * @param brewMaterial åŽŸæ–™ä¿¡æ¯
     * @return 结果
     */
    public int insertBrewMaterial(BrewMaterial brewMaterial);

    /**
     * 修改åŽŸæ–™ä¿¡æ¯
     * 
     * @param brewMaterial åŽŸæ–™ä¿¡æ¯
     * @return 结果
     */
    public int updateBrewMaterial(BrewMaterial brewMaterial);

    /**
     * 删除åŽŸæ–™ä¿¡æ¯
     * 
     * @param materialId åŽŸæ–™ä¿¡æ¯主键
     * @return 结果
     */
    public int deleteBrewMaterialByMaterialId(Long materialId);

    /**
     * 批量删除åŽŸæ–™ä¿¡æ¯
     * 
     * @param materialIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewMaterialByMaterialIds(Long[] materialIds);
}
