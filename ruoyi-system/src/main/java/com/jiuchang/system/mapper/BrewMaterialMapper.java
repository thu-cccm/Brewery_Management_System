package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewMaterial;

/**
 * 原料信?Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewMaterialMapper 
{
    /**
     * ??原料信?
     * 
     * @param materialId 原料信???
     * @return 原料信?
     */
    public BrewMaterial selectBrewMaterialByMaterialId(Long materialId);

    /**
     * ??原料信???
     * 
     * @param brewMaterial 原料信?
     * @return 原料信???
     */
    public List<BrewMaterial> selectBrewMaterialList(BrewMaterial brewMaterial);

    /**
     * ??原料信?
     * 
     * @param brewMaterial 原料信?
     * @return ??
     */
    public int insertBrewMaterial(BrewMaterial brewMaterial);

    /**
     * ??原料信?
     * 
     * @param brewMaterial 原料信?
     * @return ??
     */
    public int updateBrewMaterial(BrewMaterial brewMaterial);

    /**
     * ??原料信?
     * 
     * @param materialId 原料信???
     * @return ??
     */
    public int deleteBrewMaterialByMaterialId(Long materialId);

    /**
     * ????原料信?
     * 
     * @param materialIds ???????????
     * @return ??
     */
    public int deleteBrewMaterialByMaterialIds(Long[] materialIds);
}
