package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewProductionProcessMapper;
import com.jiuchang.system.domain.BrewProductionProcess;
import com.jiuchang.system.service.IBrewProductionProcessService;

/**
 * ç”Ÿäº§è¿‡ç¨‹Service业务层处理
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
     * 查询ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param processId ç”Ÿäº§è¿‡ç¨‹主键
     * @return ç”Ÿäº§è¿‡ç¨‹
     */
    @Override
    public BrewProductionProcess selectBrewProductionProcessByProcessId(Long processId)
    {
        return brewProductionProcessMapper.selectBrewProductionProcessByProcessId(processId);
    }

    /**
     * 查询ç”Ÿäº§è¿‡ç¨‹列表
     * 
     * @param brewProductionProcess ç”Ÿäº§è¿‡ç¨‹
     * @return ç”Ÿäº§è¿‡ç¨‹
     */
    @Override
    public List<BrewProductionProcess> selectBrewProductionProcessList(BrewProductionProcess brewProductionProcess)
    {
        return brewProductionProcessMapper.selectBrewProductionProcessList(brewProductionProcess);
    }

    /**
     * 新增ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param brewProductionProcess ç”Ÿäº§è¿‡ç¨‹
     * @return 结果
     */
    @Override
    public int insertBrewProductionProcess(BrewProductionProcess brewProductionProcess)
    {
        brewProductionProcess.setCreateTime(DateUtils.getNowDate());
        return brewProductionProcessMapper.insertBrewProductionProcess(brewProductionProcess);
    }

    /**
     * 修改ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param brewProductionProcess ç”Ÿäº§è¿‡ç¨‹
     * @return 结果
     */
    @Override
    public int updateBrewProductionProcess(BrewProductionProcess brewProductionProcess)
    {
        brewProductionProcess.setUpdateTime(DateUtils.getNowDate());
        return brewProductionProcessMapper.updateBrewProductionProcess(brewProductionProcess);
    }

    /**
     * 批量删除ç”Ÿäº§è¿‡ç¨‹
     * 
     * @param processIds 需要删除的ç”Ÿäº§è¿‡ç¨‹主键
     * @return 结果
     */
    @Override
    public int deleteBrewProductionProcessByProcessIds(Long[] processIds)
    {
        return brewProductionProcessMapper.deleteBrewProductionProcessByProcessIds(processIds);
    }

    /**
     * 删除ç”Ÿäº§è¿‡ç¨‹信息
     * 
     * @param processId ç”Ÿäº§è¿‡ç¨‹主键
     * @return 结果
     */
    @Override
    public int deleteBrewProductionProcessByProcessId(Long processId)
    {
        return brewProductionProcessMapper.deleteBrewProductionProcessByProcessId(processId);
    }
}
