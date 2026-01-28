package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewProductionProcessMapper;
import com.jiuchang.system.domain.BrewProductionProcess;
import com.jiuchang.system.service.IBrewProductionProcessService;

/**
 * 生产过程Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewProductionProcessServiceImpl implements IBrewProductionProcessService 
{
    @Autowired
    private BrewProductionProcessMapper brewProductionProcessMapper;

    /**
     * ??生产过程
     * 
     * @param processId 生产过程??
     * @return 生产过程
     */
    @Override
    public BrewProductionProcess selectBrewProductionProcessByProcessId(Long processId)
    {
        return brewProductionProcessMapper.selectBrewProductionProcessByProcessId(processId);
    }

    /**
     * ??生产过程??
     * 
     * @param brewProductionProcess 生产过程
     * @return 生产过程
     */
    @Override
    public List<BrewProductionProcess> selectBrewProductionProcessList(BrewProductionProcess brewProductionProcess)
    {
        return brewProductionProcessMapper.selectBrewProductionProcessList(brewProductionProcess);
    }

    /**
     * ??生产过程
     * 
     * @param brewProductionProcess 生产过程
     * @return ??
     */
    @Override
    public int insertBrewProductionProcess(BrewProductionProcess brewProductionProcess)
    {
        brewProductionProcess.setCreateTime(DateUtils.getNowDate());
        return brewProductionProcessMapper.insertBrewProductionProcess(brewProductionProcess);
    }

    /**
     * ??生产过程
     * 
     * @param brewProductionProcess 生产过程
     * @return ??
     */
    @Override
    public int updateBrewProductionProcess(BrewProductionProcess brewProductionProcess)
    {
        brewProductionProcess.setUpdateTime(DateUtils.getNowDate());
        return brewProductionProcessMapper.updateBrewProductionProcess(brewProductionProcess);
    }

    /**
     * ????生产过程
     * 
     * @param processIds ?????生产过程??
     * @return ??
     */
    @Override
    public int deleteBrewProductionProcessByProcessIds(Long[] processIds)
    {
        return brewProductionProcessMapper.deleteBrewProductionProcessByProcessIds(processIds);
    }

    /**
     * ??生产过程??
     * 
     * @param processId 生产过程??
     * @return ??
     */
    @Override
    public int deleteBrewProductionProcessByProcessId(Long processId)
    {
        return brewProductionProcessMapper.deleteBrewProductionProcessByProcessId(processId);
    }
}
