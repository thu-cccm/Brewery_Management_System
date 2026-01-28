package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewBatchCostMapper;
import com.jiuchang.system.domain.BrewBatchCost;
import com.jiuchang.system.service.IBrewBatchCostService;

/**
 * 批次?本Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewBatchCostServiceImpl implements IBrewBatchCostService 
{
    @Autowired
    private BrewBatchCostMapper brewBatchCostMapper;

    /**
     * ??批次?本
     * 
     * @param costId 批次?本??
     * @return 批次?本
     */
    @Override
    public BrewBatchCost selectBrewBatchCostByCostId(Long costId)
    {
        return brewBatchCostMapper.selectBrewBatchCostByCostId(costId);
    }

    /**
     * ??批次?本??
     * 
     * @param brewBatchCost 批次?本
     * @return 批次?本
     */
    @Override
    public List<BrewBatchCost> selectBrewBatchCostList(BrewBatchCost brewBatchCost)
    {
        return brewBatchCostMapper.selectBrewBatchCostList(brewBatchCost);
    }

    /**
     * ??批次?本
     * 
     * @param brewBatchCost 批次?本
     * @return ??
     */
    @Override
    public int insertBrewBatchCost(BrewBatchCost brewBatchCost)
    {
        brewBatchCost.setCreateTime(DateUtils.getNowDate());
        return brewBatchCostMapper.insertBrewBatchCost(brewBatchCost);
    }

    /**
     * ??批次?本
     * 
     * @param brewBatchCost 批次?本
     * @return ??
     */
    @Override
    public int updateBrewBatchCost(BrewBatchCost brewBatchCost)
    {
        brewBatchCost.setUpdateTime(DateUtils.getNowDate());
        return brewBatchCostMapper.updateBrewBatchCost(brewBatchCost);
    }

    /**
     * ????批次?本
     * 
     * @param costIds ?????批次?本??
     * @return ??
     */
    @Override
    public int deleteBrewBatchCostByCostIds(Long[] costIds)
    {
        return brewBatchCostMapper.deleteBrewBatchCostByCostIds(costIds);
    }

    /**
     * ??批次?本??
     * 
     * @param costId 批次?本??
     * @return ??
     */
    @Override
    public int deleteBrewBatchCostByCostId(Long costId)
    {
        return brewBatchCostMapper.deleteBrewBatchCostByCostId(costId);
    }
}
