package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewEquipment;

/**
 * 设备台账Service接口
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public interface IBrewEquipmentService 
{
    /**
     * 查询设备台账
     * 
     * @param equipmentId 设备台账主键
     * @return 设备台账
     */
    public BrewEquipment selectBrewEquipmentByEquipmentId(Long equipmentId);

    /**
     * 查询设备台账列表
     * 
     * @param brewEquipment 设备台账
     * @return 设备台账集合
     */
    public List<BrewEquipment> selectBrewEquipmentList(BrewEquipment brewEquipment);

    /**
     * 新增设备台账
     * 
     * @param brewEquipment 设备台账
     * @return 结果
     */
    public int insertBrewEquipment(BrewEquipment brewEquipment);

    /**
     * 修改设备台账
     * 
     * @param brewEquipment 设备台账
     * @return 结果
     */
    public int updateBrewEquipment(BrewEquipment brewEquipment);

    /**
     * 批量删除设备台账
     * 
     * @param equipmentIds 需要删除的设备台账主键集合
     * @return 结果
     */
    public int deleteBrewEquipmentByEquipmentIds(Long[] equipmentIds);

    /**
     * 删除设备台账信息
     * 
     * @param equipmentId 设备台账主键
     * @return 结果
     */
    public int deleteBrewEquipmentByEquipmentId(Long equipmentId);
}
