package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionProcess;

/**
 * ç”Ÿäº§è¿‡ç¨‹Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewProductionProcessMapper 
{
    /**
     * 查询ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param processId ç”Ÿäº§è¿‡ç¨‹主键
     * @return ç”Ÿäº§è¿‡ç¨‹
     */
    public BrewProductionProcess selectBrewProductionProcessByProcessId(Long processId);

    /**
     * 查询ç”Ÿäº§è¿‡ç¨‹列表
     * 
     * @param brewProductionProcess ç”Ÿäº§è¿‡ç¨‹
     * @return ç”Ÿäº§è¿‡ç¨‹集合
     */
    public List<BrewProductionProcess> selectBrewProductionProcessList(BrewProductionProcess brewProductionProcess);

    /**
     * 新增ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param brewProductionProcess ç”Ÿäº§è¿‡ç¨‹
     * @return 结果
     */
    public int insertBrewProductionProcess(BrewProductionProcess brewProductionProcess);

    /**
     * 修改ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param brewProductionProcess ç”Ÿäº§è¿‡ç¨‹
     * @return 结果
     */
    public int updateBrewProductionProcess(BrewProductionProcess brewProductionProcess);

    /**
     * 删除ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param processId ç”Ÿäº§è¿‡ç¨‹主键
     * @return 结果
     */
    public int deleteBrewProductionProcessByProcessId(Long processId);

    /**
     * 批量删除ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param processIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewProductionProcessByProcessIds(Long[] processIds);
}
