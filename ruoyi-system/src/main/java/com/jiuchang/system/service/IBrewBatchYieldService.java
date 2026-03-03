package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewBatchYield;

/**
 * 批次损耗分析Service接口
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public interface IBrewBatchYieldService 
{
    /**
     * 查询批次损耗分析
     * 
     * @param yieldId 批次损耗分析主键
     * @return 批次损耗分析
     */
    public BrewBatchYield selectBrewBatchYieldByYieldId(Long yieldId);

    /**
     * 查询批次损耗分析列表
     * 
     * @param brewBatchYield 批次损耗分析
     * @return 批次损耗分析集合
     */
    public List<BrewBatchYield> selectBrewBatchYieldList(BrewBatchYield brewBatchYield);

    /**
     * 新增批次损耗分析
     * 
     * @param brewBatchYield 批次损耗分析
     * @return 结果
     */
    public int insertBrewBatchYield(BrewBatchYield brewBatchYield);

    /**
     * 修改批次损耗分析
     * 
     * @param brewBatchYield 批次损耗分析
     * @return 结果
     */
    public int updateBrewBatchYield(BrewBatchYield brewBatchYield);

    /**
     * 批量删除批次损耗分析
     * 
     * @param yieldIds 需要删除的批次损耗分析主键集合
     * @return 结果
     */
    public int deleteBrewBatchYieldByYieldIds(Long[] yieldIds);

    /**
     * 删除批次损耗分析信息
     * 
     * @param yieldId 批次损耗分析主键
     * @return 结果
     */
    public int deleteBrewBatchYieldByYieldId(Long yieldId);
}
