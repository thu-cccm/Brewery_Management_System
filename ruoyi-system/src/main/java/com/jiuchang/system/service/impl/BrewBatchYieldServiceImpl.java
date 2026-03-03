package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewBatchYieldMapper;
import com.jiuchang.system.domain.BrewBatchYield;
import com.jiuchang.system.service.IBrewBatchYieldService;

/**
 * 批次损耗分析Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@Service
public class BrewBatchYieldServiceImpl implements IBrewBatchYieldService 
{
    @Autowired
    private BrewBatchYieldMapper brewBatchYieldMapper;

    /**
     * 查询批次损耗分析
     * 
     * @param yieldId 批次损耗分析主键
     * @return 批次损耗分析
     */
    @Override
    public BrewBatchYield selectBrewBatchYieldByYieldId(Long yieldId)
    {
        return brewBatchYieldMapper.selectBrewBatchYieldByYieldId(yieldId);
    }

    /**
     * 查询批次损耗分析列表
     * 
     * @param brewBatchYield 批次损耗分析
     * @return 批次损耗分析
     */
    @Override
    public List<BrewBatchYield> selectBrewBatchYieldList(BrewBatchYield brewBatchYield)
    {
        return brewBatchYieldMapper.selectBrewBatchYieldList(brewBatchYield);
    }

    /**
     * 新增批次损耗分析
     * 
     * @param brewBatchYield 批次损耗分析
     * @return 结果
     */
    @Override
    public int insertBrewBatchYield(BrewBatchYield brewBatchYield)
    {
        brewBatchYield.setCreateTime(DateUtils.getNowDate());
        return brewBatchYieldMapper.insertBrewBatchYield(brewBatchYield);
    }

    /**
     * 修改批次损耗分析
     * 
     * @param brewBatchYield 批次损耗分析
     * @return 结果
     */
    @Override
    public int updateBrewBatchYield(BrewBatchYield brewBatchYield)
    {
        brewBatchYield.setUpdateTime(DateUtils.getNowDate());
        return brewBatchYieldMapper.updateBrewBatchYield(brewBatchYield);
    }

    /**
     * 批量删除批次损耗分析
     * 
     * @param yieldIds 需要删除的批次损耗分析主键
     * @return 结果
     */
    @Override
    public int deleteBrewBatchYieldByYieldIds(Long[] yieldIds)
    {
        return brewBatchYieldMapper.deleteBrewBatchYieldByYieldIds(yieldIds);
    }

    /**
     * 删除批次损耗分析信息
     * 
     * @param yieldId 批次损耗分析主键
     * @return 结果
     */
    @Override
    public int deleteBrewBatchYieldByYieldId(Long yieldId)
    {
        return brewBatchYieldMapper.deleteBrewBatchYieldByYieldId(yieldId);
    }
}
