package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewBatchCost;

/**
 * 批次?本Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewBatchCostService 
{
    /**
     * ??批次?本
     * 
     * @param costId 批次?本??
     * @return 批次?本
     */
    public BrewBatchCost selectBrewBatchCostByCostId(Long costId);

    /**
     * ??批次?本??
     * 
     * @param brewBatchCost 批次?本
     * @return 批次?本??
     */
    public List<BrewBatchCost> selectBrewBatchCostList(BrewBatchCost brewBatchCost);

    /**
     * ??批次?本
     * 
     * @param brewBatchCost 批次?本
     * @return ??
     */
    public int insertBrewBatchCost(BrewBatchCost brewBatchCost);

    /**
     * ??批次?本
     * 
     * @param brewBatchCost 批次?本
     * @return ??
     */
    public int updateBrewBatchCost(BrewBatchCost brewBatchCost);

    /**
     * ????批次?本
     * 
     * @param costIds ?????批次?本????
     * @return ??
     */
    public int deleteBrewBatchCostByCostIds(Long[] costIds);

    /**
     * ??批次?本??
     * 
     * @param costId 批次?本??
     * @return ??
     */
    public int deleteBrewBatchCostByCostId(Long costId);
}
