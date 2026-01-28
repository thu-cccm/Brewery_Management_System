package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityStandard;

/**
 * è´¨æ£€æ ‡å‡†Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewQualityStandardService 
{
    /**
     * 查询è´¨æ£€æ ‡å‡†
     * 
     * @param standardId è´¨æ£€æ ‡å‡†主键
     * @return è´¨æ£€æ ‡å‡†
     */
    public BrewQualityStandard selectBrewQualityStandardByStandardId(Long standardId);

    /**
     * 查询è´¨æ£€æ ‡å‡†列表
     * 
     * @param brewQualityStandard è´¨æ£€æ ‡å‡†
     * @return è´¨æ£€æ ‡å‡†集合
     */
    public List<BrewQualityStandard> selectBrewQualityStandardList(BrewQualityStandard brewQualityStandard);

    /**
     * 新增è´¨æ£€æ ‡å‡†
     * 
     * @param brewQualityStandard è´¨æ£€æ ‡å‡†
     * @return 结果
     */
    public int insertBrewQualityStandard(BrewQualityStandard brewQualityStandard);

    /**
     * 修改è´¨æ£€æ ‡å‡†
     * 
     * @param brewQualityStandard è´¨æ£€æ ‡å‡†
     * @return 结果
     */
    public int updateBrewQualityStandard(BrewQualityStandard brewQualityStandard);

    /**
     * 批量删除è´¨æ£€æ ‡å‡†
     * 
     * @param standardIds 需要删除的è´¨æ£€æ ‡å‡†主键集合
     * @return 结果
     */
    public int deleteBrewQualityStandardByStandardIds(Long[] standardIds);

    /**
     * 删除è´¨æ£€æ ‡å‡†信息
     * 
     * @param standardId è´¨æ£€æ ‡å‡†主键
     * @return 结果
     */
    public int deleteBrewQualityStandardByStandardId(Long standardId);
}
