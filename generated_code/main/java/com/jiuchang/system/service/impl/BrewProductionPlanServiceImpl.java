package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewProductionPlanMapper;
import com.jiuchang.system.domain.BrewProductionPlan;
import com.jiuchang.system.service.IBrewProductionPlanService;

/**
 * ç”Ÿäº§è®¡åˆ’Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewProductionPlanServiceImpl implements IBrewProductionPlanService 
{
    @Autowired
    private BrewProductionPlanMapper brewProductionPlanMapper;

    /**
     * 查询ç”Ÿäº§è®¡åˆ’
     * 
     * @param planId ç”Ÿäº§è®¡åˆ’主键
     * @return ç”Ÿäº§è®¡åˆ’
     */
    @Override
    public BrewProductionPlan selectBrewProductionPlanByPlanId(Long planId)
    {
        return brewProductionPlanMapper.selectBrewProductionPlanByPlanId(planId);
    }

    /**
     * 查询ç”Ÿäº§è®¡åˆ’列表
     * 
     * @param brewProductionPlan ç”Ÿäº§è®¡åˆ’
     * @return ç”Ÿäº§è®¡åˆ’
     */
    @Override
    public List<BrewProductionPlan> selectBrewProductionPlanList(BrewProductionPlan brewProductionPlan)
    {
        return brewProductionPlanMapper.selectBrewProductionPlanList(brewProductionPlan);
    }

    /**
     * 新增ç”Ÿäº§è®¡åˆ’
     * 
     * @param brewProductionPlan ç”Ÿäº§è®¡åˆ’
     * @return 结果
     */
    @Override
    public int insertBrewProductionPlan(BrewProductionPlan brewProductionPlan)
    {
        brewProductionPlan.setCreateTime(DateUtils.getNowDate());
        return brewProductionPlanMapper.insertBrewProductionPlan(brewProductionPlan);
    }

    /**
     * 修改ç”Ÿäº§è®¡åˆ’
     * 
     * @param brewProductionPlan ç”Ÿäº§è®¡åˆ’
     * @return 结果
     */
    @Override
    public int updateBrewProductionPlan(BrewProductionPlan brewProductionPlan)
    {
        brewProductionPlan.setUpdateTime(DateUtils.getNowDate());
        return brewProductionPlanMapper.updateBrewProductionPlan(brewProductionPlan);
    }

    /**
     * 批量删除ç”Ÿäº§è®¡åˆ’
     * 
     * @param planIds 需要删除的ç”Ÿäº§è®¡åˆ’主键
     * @return 结果
     */
    @Override
    public int deleteBrewProductionPlanByPlanIds(Long[] planIds)
    {
        return brewProductionPlanMapper.deleteBrewProductionPlanByPlanIds(planIds);
    }

    /**
     * 删除ç”Ÿäº§è®¡åˆ’信息
     * 
     * @param planId ç”Ÿäº§è®¡åˆ’主键
     * @return 结果
     */
    @Override
    public int deleteBrewProductionPlanByPlanId(Long planId)
    {
        return brewProductionPlanMapper.deleteBrewProductionPlanByPlanId(planId);
    }
}
