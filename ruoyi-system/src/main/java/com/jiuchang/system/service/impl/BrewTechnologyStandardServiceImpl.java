package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewTechnologyStandardMapper;
import com.jiuchang.system.domain.BrewTechnologyStandard;
import com.jiuchang.system.service.IBrewTechnologyStandardService;

/**
 * 工艺参数标准Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@Service
public class BrewTechnologyStandardServiceImpl implements IBrewTechnologyStandardService 
{
    @Autowired
    private BrewTechnologyStandardMapper brewTechnologyStandardMapper;

    /**
     * 查询工艺参数标准
     * 
     * @param standardId 工艺参数标准主键
     * @return 工艺参数标准
     */
    @Override
    public BrewTechnologyStandard selectBrewTechnologyStandardByStandardId(Long standardId)
    {
        return brewTechnologyStandardMapper.selectBrewTechnologyStandardByStandardId(standardId);
    }

    /**
     * 查询工艺参数标准列表
     * 
     * @param brewTechnologyStandard 工艺参数标准
     * @return 工艺参数标准
     */
    @Override
    public List<BrewTechnologyStandard> selectBrewTechnologyStandardList(BrewTechnologyStandard brewTechnologyStandard)
    {
        return brewTechnologyStandardMapper.selectBrewTechnologyStandardList(brewTechnologyStandard);
    }

    /**
     * 新增工艺参数标准
     * 
     * @param brewTechnologyStandard 工艺参数标准
     * @return 结果
     */
    @Override
    public int insertBrewTechnologyStandard(BrewTechnologyStandard brewTechnologyStandard)
    {
        brewTechnologyStandard.setCreateTime(DateUtils.getNowDate());
        return brewTechnologyStandardMapper.insertBrewTechnologyStandard(brewTechnologyStandard);
    }

    /**
     * 修改工艺参数标准
     * 
     * @param brewTechnologyStandard 工艺参数标准
     * @return 结果
     */
    @Override
    public int updateBrewTechnologyStandard(BrewTechnologyStandard brewTechnologyStandard)
    {
        brewTechnologyStandard.setUpdateTime(DateUtils.getNowDate());
        return brewTechnologyStandardMapper.updateBrewTechnologyStandard(brewTechnologyStandard);
    }

    /**
     * 批量删除工艺参数标准
     * 
     * @param standardIds 需要删除的工艺参数标准主键
     * @return 结果
     */
    @Override
    public int deleteBrewTechnologyStandardByStandardIds(Long[] standardIds)
    {
        return brewTechnologyStandardMapper.deleteBrewTechnologyStandardByStandardIds(standardIds);
    }

    /**
     * 删除工艺参数标准信息
     * 
     * @param standardId 工艺参数标准主键
     * @return 结果
     */
    @Override
    public int deleteBrewTechnologyStandardByStandardId(Long standardId)
    {
        return brewTechnologyStandardMapper.deleteBrewTechnologyStandardByStandardId(standardId);
    }
}
