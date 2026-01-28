package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewProductionProcess;

/**
 * 生产过程Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewProductionProcessMapper 
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
     * ??生产过程
     * 
     * @param processId 生产过程??
     * @return ??
     */
    public int deleteBrewProductionProcessByProcessId(Long processId);

    /**
     * ????生产过程
     * 
     * @param processIds ???????????
     * @return ??
     */
    public int deleteBrewProductionProcessByProcessIds(Long[] processIds);
}
