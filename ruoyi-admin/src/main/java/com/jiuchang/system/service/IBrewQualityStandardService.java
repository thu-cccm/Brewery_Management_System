package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityStandard;

/**
 * 质检标准Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewQualityStandardService 
{
    /**
     * ??质检标准
     * 
     * @param standardId 质检标准??
     * @return 质检标准
     */
    public BrewQualityStandard selectBrewQualityStandardByStandardId(Long standardId);

    /**
     * ??质检标准??
     * 
     * @param brewQualityStandard 质检标准
     * @return 质检标准??
     */
    public List<BrewQualityStandard> selectBrewQualityStandardList(BrewQualityStandard brewQualityStandard);

    /**
     * ??质检标准
     * 
     * @param brewQualityStandard 质检标准
     * @return ??
     */
    public int insertBrewQualityStandard(BrewQualityStandard brewQualityStandard);

    /**
     * ??质检标准
     * 
     * @param brewQualityStandard 质检标准
     * @return ??
     */
    public int updateBrewQualityStandard(BrewQualityStandard brewQualityStandard);

    /**
     * ????质检标准
     * 
     * @param standardIds ?????质检标准????
     * @return ??
     */
    public int deleteBrewQualityStandardByStandardIds(Long[] standardIds);

    /**
     * ??质检标准??
     * 
     * @param standardId 质检标准??
     * @return ??
     */
    public int deleteBrewQualityStandardByStandardId(Long standardId);
}
