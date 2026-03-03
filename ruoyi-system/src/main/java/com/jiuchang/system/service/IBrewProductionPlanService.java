package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionPlan;

/**
 * 生产计划Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewProductionPlanService 
{
    /**
     * ??生产计划
     * 
     * @param planId 生产计划??
     * @return 生产计划
     */
    public BrewProductionPlan selectBrewProductionPlanByPlanId(Long planId);

    /**
     * ??生产计划??
     * 
     * @param brewProductionPlan 生产计划
     * @return 生产计划??
     */
    public List<BrewProductionPlan> selectBrewProductionPlanList(BrewProductionPlan brewProductionPlan);

    /**
     * ??生产计划
     * 
     * @param brewProductionPlan 生产计划
     * @return ??
     */
    public int insertBrewProductionPlan(BrewProductionPlan brewProductionPlan);

    /**
     * ??生产计划
     * 
     * @param brewProductionPlan 生产计划
     * @return ??
     */
    public int updateBrewProductionPlan(BrewProductionPlan brewProductionPlan);

    /**
     * ????生产计划
     * 
     * @param planIds ?????生产计划????
     * @return ??
     */
    public int deleteBrewProductionPlanByPlanIds(Long[] planIds);

    /**
     * ??生产计划??
     * 
     * @param planId 生产计划??
     * @return ??
     */
    public int deleteBrewProductionPlanByPlanId(Long planId);
}
