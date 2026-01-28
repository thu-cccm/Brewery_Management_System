package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewQualityStandardMapper;
import com.jiuchang.system.domain.BrewQualityStandard;
import com.jiuchang.system.service.IBrewQualityStandardService;

/**
 * 质检标准Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewQualityStandardServiceImpl implements IBrewQualityStandardService 
{
    @Autowired
    private BrewQualityStandardMapper brewQualityStandardMapper;

    /**
     * ??质检标准
     * 
     * @param standardId 质检标准??
     * @return 质检标准
     */
    @Override
    public BrewQualityStandard selectBrewQualityStandardByStandardId(Long standardId)
    {
        return brewQualityStandardMapper.selectBrewQualityStandardByStandardId(standardId);
    }

    /**
     * ??质检标准??
     * 
     * @param brewQualityStandard 质检标准
     * @return 质检标准
     */
    @Override
    public List<BrewQualityStandard> selectBrewQualityStandardList(BrewQualityStandard brewQualityStandard)
    {
        return brewQualityStandardMapper.selectBrewQualityStandardList(brewQualityStandard);
    }

    /**
     * ??质检标准
     * 
     * @param brewQualityStandard 质检标准
     * @return ??
     */
    @Override
    public int insertBrewQualityStandard(BrewQualityStandard brewQualityStandard)
    {
        brewQualityStandard.setCreateTime(DateUtils.getNowDate());
        return brewQualityStandardMapper.insertBrewQualityStandard(brewQualityStandard);
    }

    /**
     * ??质检标准
     * 
     * @param brewQualityStandard 质检标准
     * @return ??
     */
    @Override
    public int updateBrewQualityStandard(BrewQualityStandard brewQualityStandard)
    {
        brewQualityStandard.setUpdateTime(DateUtils.getNowDate());
        return brewQualityStandardMapper.updateBrewQualityStandard(brewQualityStandard);
    }

    /**
     * ????质检标准
     * 
     * @param standardIds ?????质检标准??
     * @return ??
     */
    @Override
    public int deleteBrewQualityStandardByStandardIds(Long[] standardIds)
    {
        return brewQualityStandardMapper.deleteBrewQualityStandardByStandardIds(standardIds);
    }

    /**
     * ??质检标准??
     * 
     * @param standardId 质检标准??
     * @return ??
     */
    @Override
    public int deleteBrewQualityStandardByStandardId(Long standardId)
    {
        return brewQualityStandardMapper.deleteBrewQualityStandardByStandardId(standardId);
    }
}
