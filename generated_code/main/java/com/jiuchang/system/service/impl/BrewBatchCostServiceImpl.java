package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewBatchCostMapper;
import com.jiuchang.system.domain.BrewBatchCost;
import com.jiuchang.system.service.IBrewBatchCostService;

/**
 * æ‰¹æ¬¡æˆæœ¬Service业务层处理
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
     * 查询æ‰¹æ¬¡æˆæœ¬
     * 
     * @param costId æ‰¹æ¬¡æˆæœ¬主键
     * @return æ‰¹æ¬¡æˆæœ¬
     */
    @Override
    public BrewBatchCost selectBrewBatchCostByCostId(Long costId)
    {
        return brewBatchCostMapper.selectBrewBatchCostByCostId(costId);
    }

    /**
     * 查询æ‰¹æ¬¡æˆæœ¬列表
     * 
     * @param brewBatchCost æ‰¹æ¬¡æˆæœ¬
     * @return æ‰¹æ¬¡æˆæœ¬
     */
    @Override
    public List<BrewBatchCost> selectBrewBatchCostList(BrewBatchCost brewBatchCost)
    {
        return brewBatchCostMapper.selectBrewBatchCostList(brewBatchCost);
    }

    /**
     * 新增æ‰¹æ¬¡æˆæœ¬
     * 
     * @param brewBatchCost æ‰¹æ¬¡æˆæœ¬
     * @return 结果
     */
    @Override
    public int insertBrewBatchCost(BrewBatchCost brewBatchCost)
    {
        brewBatchCost.setCreateTime(DateUtils.getNowDate());
        return brewBatchCostMapper.insertBrewBatchCost(brewBatchCost);
    }

    /**
     * 修改æ‰¹æ¬¡æˆæœ¬
     * 
     * @param brewBatchCost æ‰¹æ¬¡æˆæœ¬
     * @return 结果
     */
    @Override
    public int updateBrewBatchCost(BrewBatchCost brewBatchCost)
    {
        brewBatchCost.setUpdateTime(DateUtils.getNowDate());
        return brewBatchCostMapper.updateBrewBatchCost(brewBatchCost);
    }

    /**
     * 批量删除æ‰¹æ¬¡æˆæœ¬
     * 
     * @param costIds 需要删除的æ‰¹æ¬¡æˆæœ¬主键
     * @return 结果
     */
    @Override
    public int deleteBrewBatchCostByCostIds(Long[] costIds)
    {
        return brewBatchCostMapper.deleteBrewBatchCostByCostIds(costIds);
    }

    /**
     * 删除æ‰¹æ¬¡æˆæœ¬信息
     * 
     * @param costId æ‰¹æ¬¡æˆæœ¬主键
     * @return 结果
     */
    @Override
    public int deleteBrewBatchCostByCostId(Long costId)
    {
        return brewBatchCostMapper.deleteBrewBatchCostByCostId(costId);
    }
}
