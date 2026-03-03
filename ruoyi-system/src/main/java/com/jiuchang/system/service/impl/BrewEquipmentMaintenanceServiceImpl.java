package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewEquipmentMaintenanceMapper;
import com.jiuchang.system.domain.BrewEquipmentMaintenance;
import com.jiuchang.system.service.IBrewEquipmentMaintenanceService;

/**
 * 维保记录Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@Service
public class BrewEquipmentMaintenanceServiceImpl implements IBrewEquipmentMaintenanceService 
{
    @Autowired
    private BrewEquipmentMaintenanceMapper brewEquipmentMaintenanceMapper;

    /**
     * 查询维保记录
     * 
     * @param maintenanceId 维保记录主键
     * @return 维保记录
     */
    @Override
    public BrewEquipmentMaintenance selectBrewEquipmentMaintenanceByMaintenanceId(Long maintenanceId)
    {
        return brewEquipmentMaintenanceMapper.selectBrewEquipmentMaintenanceByMaintenanceId(maintenanceId);
    }

    /**
     * 查询维保记录列表
     * 
     * @param brewEquipmentMaintenance 维保记录
     * @return 维保记录
     */
    @Override
    public List<BrewEquipmentMaintenance> selectBrewEquipmentMaintenanceList(BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        return brewEquipmentMaintenanceMapper.selectBrewEquipmentMaintenanceList(brewEquipmentMaintenance);
    }

    /**
     * 新增维保记录
     * 
     * @param brewEquipmentMaintenance 维保记录
     * @return 结果
     */
    @Override
    public int insertBrewEquipmentMaintenance(BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        brewEquipmentMaintenance.setCreateTime(DateUtils.getNowDate());
        return brewEquipmentMaintenanceMapper.insertBrewEquipmentMaintenance(brewEquipmentMaintenance);
    }

    /**
     * 修改维保记录
     * 
     * @param brewEquipmentMaintenance 维保记录
     * @return 结果
     */
    @Override
    public int updateBrewEquipmentMaintenance(BrewEquipmentMaintenance brewEquipmentMaintenance)
    {
        brewEquipmentMaintenance.setUpdateTime(DateUtils.getNowDate());
        return brewEquipmentMaintenanceMapper.updateBrewEquipmentMaintenance(brewEquipmentMaintenance);
    }

    /**
     * 批量删除维保记录
     * 
     * @param maintenanceIds 需要删除的维保记录主键
     * @return 结果
     */
    @Override
    public int deleteBrewEquipmentMaintenanceByMaintenanceIds(Long[] maintenanceIds)
    {
        return brewEquipmentMaintenanceMapper.deleteBrewEquipmentMaintenanceByMaintenanceIds(maintenanceIds);
    }

    /**
     * 删除维保记录信息
     * 
     * @param maintenanceId 维保记录主键
     * @return 结果
     */
    @Override
    public int deleteBrewEquipmentMaintenanceByMaintenanceId(Long maintenanceId)
    {
        return brewEquipmentMaintenanceMapper.deleteBrewEquipmentMaintenanceByMaintenanceId(maintenanceId);
    }
}
