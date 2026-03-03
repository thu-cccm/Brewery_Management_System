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

    @Autowired
    private com.jiuchang.system.mapper.BrewProductionBatchMapper brewProductionBatchMapper;
    
    @Autowired
    private com.jiuchang.system.mapper.BrewWineCategoryMapper brewWineCategoryMapper;

    /**
     * 检查环境异常并记录
     */
    private void checkEnvironmentAnomaly(BrewProductionProcess process)
    {
        if (process.getBatchNo() != null) {
            com.jiuchang.system.domain.BrewProductionBatch batch = brewProductionBatchMapper.selectBrewProductionBatchByBatchNo(process.getBatchNo());
            if (batch != null && batch.getCategoryId() != null) {
                com.jiuchang.system.domain.BrewWineCategory category = brewWineCategoryMapper.selectBrewWineCategoryByCategoryId(batch.getCategoryId());
                if (category != null) {
                    StringBuilder anomalyMsg = new StringBuilder();
                    // 检查温度
                    if (process.getAvgTemperature() != null && category.getOptimalTemperature() != null) {
                        double diff = Math.abs(process.getAvgTemperature().doubleValue() - category.getOptimalTemperature().doubleValue());
                        if (diff > 5.0) { // 偏差超过5度视为异常
                            anomalyMsg.append("温度偏差过大(").append(process.getAvgTemperature()).append("℃); ");
                        }
                    }
                    // 检查湿度
                    if (process.getAvgHumidity() != null && category.getOptimalHumidity() != null) {
                        double diff = Math.abs(process.getAvgHumidity().doubleValue() - category.getOptimalHumidity().doubleValue());
                        if (diff > 10.0) { // 偏差超过10%视为异常
                            anomalyMsg.append("湿度偏差过大(").append(process.getAvgHumidity()).append("%); ");
                        }
                    }
                    
                    if (anomalyMsg.length() > 0) {
                        process.setRemark("【环境预警】" + anomalyMsg.toString() + (process.getRemark() != null ? process.getRemark() : ""));
                    }
                }
            }
        }
    }

    /**
     * 新增生产过程
     */
    @Override
    public int insertBrewProductionProcess(BrewProductionProcess brewProductionProcess)
    {
        checkEnvironmentAnomaly(brewProductionProcess);
        brewProductionProcess.setCreateTime(DateUtils.getNowDate());
        return brewProductionProcessMapper.insertBrewProductionProcess(brewProductionProcess);
    }

    /**
     * 修改生产过程
     */
    @Override
    public int updateBrewProductionProcess(BrewProductionProcess brewProductionProcess)
    {
        checkEnvironmentAnomaly(brewProductionProcess);
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
