package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewQualityStandardMapper;
import com.jiuchang.system.domain.BrewQualityStandard;
import com.jiuchang.system.service.IBrewQualityStandardService;

/**
 * è´¨æ£€æ ‡å‡†Service业务层处理
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
     * 查询è´¨æ£€æ ‡å‡†
     * 
     * @param standardId è´¨æ£€æ ‡å‡†主键
     * @return è´¨æ£€æ ‡å‡†
     */
    @Override
    public BrewQualityStandard selectBrewQualityStandardByStandardId(Long standardId)
    {
        return brewQualityStandardMapper.selectBrewQualityStandardByStandardId(standardId);
    }

    /**
     * 查询è´¨æ£€æ ‡å‡†列表
     * 
     * @param brewQualityStandard è´¨æ£€æ ‡å‡†
     * @return è´¨æ£€æ ‡å‡†
     */
    @Override
    public List<BrewQualityStandard> selectBrewQualityStandardList(BrewQualityStandard brewQualityStandard)
    {
        return brewQualityStandardMapper.selectBrewQualityStandardList(brewQualityStandard);
    }

    /**
     * 新增è´¨æ£€æ ‡å‡†
     * 
     * @param brewQualityStandard è´¨æ£€æ ‡å‡†
     * @return 结果
     */
    @Override
    public int insertBrewQualityStandard(BrewQualityStandard brewQualityStandard)
    {
        brewQualityStandard.setCreateTime(DateUtils.getNowDate());
        return brewQualityStandardMapper.insertBrewQualityStandard(brewQualityStandard);
    }

    /**
     * 修改è´¨æ£€æ ‡å‡†
     * 
     * @param brewQualityStandard è´¨æ£€æ ‡å‡†
     * @return 结果
     */
    @Override
    public int updateBrewQualityStandard(BrewQualityStandard brewQualityStandard)
    {
        brewQualityStandard.setUpdateTime(DateUtils.getNowDate());
        return brewQualityStandardMapper.updateBrewQualityStandard(brewQualityStandard);
    }

    /**
     * 批量删除è´¨æ£€æ ‡å‡†
     * 
     * @param standardIds 需要删除的è´¨æ£€æ ‡å‡†主键
     * @return 结果
     */
    @Override
    public int deleteBrewQualityStandardByStandardIds(Long[] standardIds)
    {
        return brewQualityStandardMapper.deleteBrewQualityStandardByStandardIds(standardIds);
    }

    /**
     * 删除è´¨æ£€æ ‡å‡†信息
     * 
     * @param standardId è´¨æ£€æ ‡å‡†主键
     * @return 结果
     */
    @Override
    public int deleteBrewQualityStandardByStandardId(Long standardId)
    {
        return brewQualityStandardMapper.deleteBrewQualityStandardByStandardId(standardId);
    }
}
