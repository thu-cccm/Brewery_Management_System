package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewEquipmentMaintenance;

/**
 * 维保记录Service接口
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public interface IBrewEquipmentMaintenanceService 
{
    /**
     * 查询维保记录
     * 
     * @param maintenanceId 维保记录主键
     * @return 维保记录
     */
    public BrewEquipmentMaintenance selectBrewEquipmentMaintenanceByMaintenanceId(Long maintenanceId);

    /**
     * 查询维保记录列表
     * 
     * @param brewEquipmentMaintenance 维保记录
     * @return 维保记录集合
     */
    public List<BrewEquipmentMaintenance> selectBrewEquipmentMaintenanceList(BrewEquipmentMaintenance brewEquipmentMaintenance);

    /**
     * 新增维保记录
     * 
     * @param brewEquipmentMaintenance 维保记录
     * @return 结果
     */
    public int insertBrewEquipmentMaintenance(BrewEquipmentMaintenance brewEquipmentMaintenance);

    /**
     * 修改维保记录
     * 
     * @param brewEquipmentMaintenance 维保记录
     * @return 结果
     */
    public int updateBrewEquipmentMaintenance(BrewEquipmentMaintenance brewEquipmentMaintenance);

    /**
     * 批量删除维保记录
     * 
     * @param maintenanceIds 需要删除的维保记录主键集合
     * @return 结果
     */
    public int deleteBrewEquipmentMaintenanceByMaintenanceIds(Long[] maintenanceIds);

    /**
     * 删除维保记录信息
     * 
     * @param maintenanceId 维保记录主键
     * @return 结果
     */
    public int deleteBrewEquipmentMaintenanceByMaintenanceId(Long maintenanceId);
}
