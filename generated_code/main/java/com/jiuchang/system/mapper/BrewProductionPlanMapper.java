package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionPlan;

/**
 * ç”Ÿäº§è®¡åˆ’Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewProductionPlanMapper 
{
    /**
     * 查询ç”Ÿäº§è®¡åˆ’
     * 
     * @param planId ç”Ÿäº§è®¡åˆ’主键
     * @return ç”Ÿäº§è®¡åˆ’
     */
    public BrewProductionPlan selectBrewProductionPlanByPlanId(Long planId);

    /**
     * 查询ç”Ÿäº§è®¡åˆ’列表
     * 
     * @param brewProductionPlan ç”Ÿäº§è®¡åˆ’
     * @return ç”Ÿäº§è®¡åˆ’集合
     */
    public List<BrewProductionPlan> selectBrewProductionPlanList(BrewProductionPlan brewProductionPlan);

    /**
     * 新增ç”Ÿäº§è®¡åˆ’
     * 
     * @param brewProductionPlan ç”Ÿäº§è®¡åˆ’
     * @return 结果
     */
    public int insertBrewProductionPlan(BrewProductionPlan brewProductionPlan);

    /**
     * 修改ç”Ÿäº§è®¡åˆ’
     * 
     * @param brewProductionPlan ç”Ÿäº§è®¡åˆ’
     * @return 结果
     */
    public int updateBrewProductionPlan(BrewProductionPlan brewProductionPlan);

    /**
     * 删除ç”Ÿäº§è®¡åˆ’
     * 
     * @param planId ç”Ÿäº§è®¡åˆ’主键
     * @return 结果
     */
    public int deleteBrewProductionPlanByPlanId(Long planId);

    /**
     * 批量删除ç”Ÿäº§è®¡åˆ’
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewProductionPlanByPlanIds(Long[] planIds);
}
