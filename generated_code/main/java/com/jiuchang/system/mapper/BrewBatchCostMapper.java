package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewBatchCost;

/**
 * æ‰¹æ¬¡æˆæœ¬Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewBatchCostMapper 
{
    /**
     * 查询æ‰¹æ¬¡æˆæœ¬
     * 
     * @param costId æ‰¹æ¬¡æˆæœ¬主键
     * @return æ‰¹æ¬¡æˆæœ¬
     */
    public BrewBatchCost selectBrewBatchCostByCostId(Long costId);

    /**
     * 查询æ‰¹æ¬¡æˆæœ¬列表
     * 
     * @param brewBatchCost æ‰¹æ¬¡æˆæœ¬
     * @return æ‰¹æ¬¡æˆæœ¬集合
     */
    public List<BrewBatchCost> selectBrewBatchCostList(BrewBatchCost brewBatchCost);

    /**
     * 新增æ‰¹æ¬¡æˆæœ¬
     * 
     * @param brewBatchCost æ‰¹æ¬¡æˆæœ¬
     * @return 结果
     */
    public int insertBrewBatchCost(BrewBatchCost brewBatchCost);

    /**
     * 修改æ‰¹æ¬¡æˆæœ¬
     * 
     * @param brewBatchCost æ‰¹æ¬¡æˆæœ¬
     * @return 结果
     */
    public int updateBrewBatchCost(BrewBatchCost brewBatchCost);

    /**
     * 删除æ‰¹æ¬¡æˆæœ¬
     * 
     * @param costId æ‰¹æ¬¡æˆæœ¬主键
     * @return 结果
     */
    public int deleteBrewBatchCostByCostId(Long costId);

    /**
     * 批量删除æ‰¹æ¬¡æˆæœ¬
     * 
     * @param costIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewBatchCostByCostIds(Long[] costIds);
}
