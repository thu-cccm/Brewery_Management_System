package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewEquipmentMapper;
import com.jiuchang.system.domain.BrewEquipment;
import com.jiuchang.system.service.IBrewEquipmentService;

/**
 * 设备台账Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
@Service
public class BrewEquipmentServiceImpl implements IBrewEquipmentService 
{
    @Autowired
    private BrewEquipmentMapper brewEquipmentMapper;

    /**
     * 查询设备台账
     * 
     * @param equipmentId 设备台账主键
     * @return 设备台账
     */
    @Override
    public BrewEquipment selectBrewEquipmentByEquipmentId(Long equipmentId)
    {
        return brewEquipmentMapper.selectBrewEquipmentByEquipmentId(equipmentId);
    }

    /**
     * 查询设备台账列表
     * 
     * @param brewEquipment 设备台账
     * @return 设备台账
     */
    @Override
    public List<BrewEquipment> selectBrewEquipmentList(BrewEquipment brewEquipment)
    {
        return brewEquipmentMapper.selectBrewEquipmentList(brewEquipment);
    }

    /**
     * 新增设备台账
     * 
     * @param brewEquipment 设备台账
     * @return 结果
     */
    @Override
    public int insertBrewEquipment(BrewEquipment brewEquipment)
    {
        brewEquipment.setCreateTime(DateUtils.getNowDate());
        return brewEquipmentMapper.insertBrewEquipment(brewEquipment);
    }

    /**
     * 修改设备台账
     * 
     * @param brewEquipment 设备台账
     * @return 结果
     */
    @Override
    public int updateBrewEquipment(BrewEquipment brewEquipment)
    {
        brewEquipment.setUpdateTime(DateUtils.getNowDate());
        return brewEquipmentMapper.updateBrewEquipment(brewEquipment);
    }

    /**
     * 批量删除设备台账
     * 
     * @param equipmentIds 需要删除的设备台账主键
     * @return 结果
     */
    @Override
    public int deleteBrewEquipmentByEquipmentIds(Long[] equipmentIds)
    {
        return brewEquipmentMapper.deleteBrewEquipmentByEquipmentIds(equipmentIds);
    }

    /**
     * 删除设备台账信息
     * 
     * @param equipmentId 设备台账主键
     * @return 结果
     */
    @Override
    public int deleteBrewEquipmentByEquipmentId(Long equipmentId)
    {
        return brewEquipmentMapper.deleteBrewEquipmentByEquipmentId(equipmentId);
    }
}
