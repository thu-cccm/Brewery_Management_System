package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 维保记录对象 brew_equipment_maintenance
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public class BrewEquipmentMaintenance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维保ID */
    private Long maintenanceId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long equipmentId;

    /** 维保日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维保日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date maintenanceDate;

    /** 维保类型(1日常 2专项 3大修) */
    @Excel(name = "维保类型", readConverterExp = "1=日常,2=专项,3=大修")
    private String maintenanceType;

    /** 维保内容 */
    @Excel(name = "维保内容")
    private String content;

    /** 维保费用(元) */
    @Excel(name = "维保费用(元)")
    private BigDecimal cost;

    /** 维保人员 */
    @Excel(name = "维保人员")
    private String performer;

    /** 维保结果 */
    @Excel(name = "维保结果")
    private String result;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setMaintenanceId(Long maintenanceId) 
    {
        this.maintenanceId = maintenanceId;
    }

    public Long getMaintenanceId() 
    {
        return maintenanceId;
    }
    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setMaintenanceDate(Date maintenanceDate) 
    {
        this.maintenanceDate = maintenanceDate;
    }

    public Date getMaintenanceDate() 
    {
        return maintenanceDate;
    }
    public void setMaintenanceType(String maintenanceType) 
    {
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceType() 
    {
        return maintenanceType;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
    }

    public BigDecimal getCost() 
    {
        return cost;
    }
    public void setPerformer(String performer) 
    {
        this.performer = performer;
    }

    public String getPerformer() 
    {
        return performer;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
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
            .append("maintenanceId", getMaintenanceId())
            .append("equipmentId", getEquipmentId())
            .append("maintenanceDate", getMaintenanceDate())
            .append("maintenanceType", getMaintenanceType())
            .append("content", getContent())
            .append("cost", getCost())
            .append("performer", getPerformer())
            .append("result", getResult())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
