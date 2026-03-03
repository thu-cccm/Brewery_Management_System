package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewTechnologyStandard;

/**
 * 工艺参数标准Mapper接口
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public interface BrewTechnologyStandardMapper 
{
    /**
     * 查询工艺参数标准
     * 
     * @param standardId 工艺参数标准主键
     * @return 工艺参数标准
     */
    public BrewTechnologyStandard selectBrewTechnologyStandardByStandardId(Long standardId);

    /**
     * 查询工艺参数标准列表
     * 
     * @param brewTechnologyStandard 工艺参数标准
     * @return 工艺参数标准集合
     */
    public List<BrewTechnologyStandard> selectBrewTechnologyStandardList(BrewTechnologyStandard brewTechnologyStandard);

    /**
     * 新增工艺参数标准
     * 
     * @param brewTechnologyStandard 工艺参数标准
     * @return 结果
     */
    public int insertBrewTechnologyStandard(BrewTechnologyStandard brewTechnologyStandard);

    /**
     * 修改工艺参数标准
     * 
     * @param brewTechnologyStandard 工艺参数标准
     * @return 结果
     */
    public int updateBrewTechnologyStandard(BrewTechnologyStandard brewTechnologyStandard);

    /**
     * 删除工艺参数标准
     * 
     * @param standardId 工艺参数标准主键
     * @return 结果
     */
    public int deleteBrewTechnologyStandardByStandardId(Long standardId);

    /**
     * 批量删除工艺参数标准
     * 
     * @param standardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewTechnologyStandardByStandardIds(Long[] standardIds);
}
