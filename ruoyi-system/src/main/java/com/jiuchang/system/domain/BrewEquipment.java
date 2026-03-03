package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备台账对象 brew_equipment
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public class BrewEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private Long equipmentId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String equipmentCode;

    /** 设备类型(0发酵罐 1糖化锅 2过滤槽 3灌装机 9其他) */
    @Excel(name = "设备类型", readConverterExp = "0=发酵罐,1=糖化锅,2=过滤槽,3=灌装机,9=其他")
    private String equipmentType;

    /** 设备状态(0空闲 1运行中 2清洗中 3维修中) */
    @Excel(name = "设备状态", readConverterExp = "0=空闲,1=运行中,2=清洗中,3=维修中")
    private String status;

    /** 存放位置 */
    @Excel(name = "存放位置")
    private String location;

    /** 容量(L) */
    @Excel(name = "容量(L)")
    private BigDecimal capacity;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String manufacturer;

    /** 购买日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 上次维保日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上次维保日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastMaintenanceDate;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setEquipmentName(String equipmentName) 
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName() 
    {
        return equipmentName;
    }
    public void setEquipmentCode(String equipmentCode) 
    {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentCode() 
    {
        return equipmentCode;
    }
    public void setEquipmentType(String equipmentType) 
    {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType() 
    {
        return equipmentType;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setCapacity(BigDecimal capacity) 
    {
        this.capacity = capacity;
    }

    public BigDecimal getCapacity() 
    {
        return capacity;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setPurchaseDate(Date purchaseDate) 
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() 
    {
        return purchaseDate;
    }
    public void setLastMaintenanceDate(Date lastMaintenanceDate) 
    {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public Date getLastMaintenanceDate() 
    {
        return lastMaintenanceDate;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentId", getEquipmentId())
            .append("equipmentName", getEquipmentName())
            .append("equipmentCode", getEquipmentCode())
            .append("equipmentType", getEquipmentType())
            .append("status", getStatus())
            .append("location", getLocation())
            .append("capacity", getCapacity())
            .append("manufacturer", getManufacturer())
            .append("purchaseDate", getPurchaseDate())
            .append("lastMaintenanceDate", getLastMaintenanceDate())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
