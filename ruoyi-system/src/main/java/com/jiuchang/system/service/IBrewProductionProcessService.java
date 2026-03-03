package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionProcess;

/**
 * 生产过程Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewProductionProcessService 
{
    /**
     * ??生产过程
     * 
     * @param processId 生产过程??
     * @return 生产过程
     */
    public BrewProductionProcess selectBrewProductionProcessByProcessId(Long processId);

    /**
     * ??生产过程??
     * 
     * @param brewProductionProcess 生产过程
     * @return 生产过程??
     */
    public List<BrewProductionProcess> selectBrewProductionProcessList(BrewProductionProcess brewProductionProcess);

    /**
     * ??生产过程
     * 
     * @param brewProductionProcess 生产过程
     * @return ??
     */
    public int insertBrewProductionProcess(BrewProductionProcess brewProductionProcess);

    /**
     * ??生产过程
     * 
     * @param brewProductionProcess 生产过程
     * @return ??
     */
    public int updateBrewProductionProcess(BrewProductionProcess brewProductionProcess);

    /**
     * ????生产过程
     * 
     * @param processIds ?????生产过程????
     * @return ??
     */
    public int deleteBrewProductionProcessByProcessIds(Long[] processIds);

    /**
     * ??生产过程??
     * 
     * @param processId 生产过程??
     * @return ??
     */
    public int deleteBrewProductionProcessByProcessId(Long processId);
}
