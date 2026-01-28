package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionProcess;

/**
 * ç”Ÿäº§è¿‡ç¨‹Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewProductionProcessService 
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
     * 批量删除ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param processIds 需要删除的ç”Ÿäº§è¿‡ç¨‹主键集合
     * @return 结果
     */
    public int deleteBrewProductionProcessByProcessIds(Long[] processIds);

    /**
     * 删除ç”Ÿäº§è¿‡ç¨‹信息
     * 
     * @param processId ç”Ÿäº§è¿‡ç¨‹主键
     * @return 结果
     */
    public int deleteBrewProductionProcessByProcessId(Long processId);
}
