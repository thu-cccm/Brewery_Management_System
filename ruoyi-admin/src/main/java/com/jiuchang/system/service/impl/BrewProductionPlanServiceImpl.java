package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewProductionPlanMapper;
import com.jiuchang.system.domain.BrewProductionPlan;
import com.jiuchang.system.service.IBrewProductionPlanService;

/**
 * 生产计划Service?????
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
     * ??生产计划
     * 
     * @param planId 生产计划??
     * @return 生产计划
     */
    @Override
    public BrewProductionPlan selectBrewProductionPlanByPlanId(Long planId)
    {
        return brewProductionPlanMapper.selectBrewProductionPlanByPlanId(planId);
    }

    /**
     * ??生产计划??
     * 
     * @param brewProductionPlan 生产计划
     * @return 生产计划
     */
    @Override
    public List<BrewProductionPlan> selectBrewProductionPlanList(BrewProductionPlan brewProductionPlan)
    {
        return brewProductionPlanMapper.selectBrewProductionPlanList(brewProductionPlan);
    }

    /**
     * ??生产计划
     * 
     * @param brewProductionPlan 生产计划
     * @return ??
     */
    @Override
    public int insertBrewProductionPlan(BrewProductionPlan brewProductionPlan)
    {
        brewProductionPlan.setCreateTime(DateUtils.getNowDate());
        return brewProductionPlanMapper.insertBrewProductionPlan(brewProductionPlan);
    }

    /**
     * ??生产计划
     * 
     * @param brewProductionPlan 生产计划
     * @return ??
     */
    @Override
    public int updateBrewProductionPlan(BrewProductionPlan brewProductionPlan)
    {
        brewProductionPlan.setUpdateTime(DateUtils.getNowDate());
        return brewProductionPlanMapper.updateBrewProductionPlan(brewProductionPlan);
    }

    /**
     * ????生产计划
     * 
     * @param planIds ?????生产计划??
     * @return ??
     */
    @Override
    public int deleteBrewProductionPlanByPlanIds(Long[] planIds)
    {
        return brewProductionPlanMapper.deleteBrewProductionPlanByPlanIds(planIds);
    }

    /**
     * ??生产计划??
     * 
     * @param planId 生产计划??
     * @return ??
     */
    @Override
    public int deleteBrewProductionPlanByPlanId(Long planId)
    {
        return brewProductionPlanMapper.deleteBrewProductionPlanByPlanId(planId);
    }
}
