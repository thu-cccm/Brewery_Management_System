package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 批次原料搜索 brew_batch_material
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewBatchMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 批次? */
    @Excel(name = "批次?")
    private String batchNo;

    /** 原料ID */
    @Excel(name = "原料ID")
    private Long materialId;

    /** 原料批次?（供应商搜索供，?手工录入） */
    @Excel(name = "原料批次?（供应商搜索供，?手工录入）")
    private String materialBatchNo;

    /** 计划用? */
    @Excel(name = "计划用?")
    private BigDecimal plannedQuantity;

    /** 实际用? */
    @Excel(name = "实际用?")
    private BigDecimal actualQuantity;

    /** 原料?本（元） */
    @Excel(name = "原料?本（元）")
    private BigDecimal materialCost;

    /** 使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** ?作人 */
    @Excel(name = "?作人")
    private String operatorName;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialBatchNo(String materialBatchNo) 
    {
        this.materialBatchNo = materialBatchNo;
    }

    public String getMaterialBatchNo() 
    {
        return materialBatchNo;
    }
    public void setPlannedQuantity(BigDecimal plannedQuantity) 
    {
        this.plannedQuantity = plannedQuantity;
    }

    public BigDecimal getPlannedQuantity() 
    {
        return plannedQuantity;
    }
    public void setActualQuantity(BigDecimal actualQuantity) 
    {
        this.actualQuantity = actualQuantity;
    }

    public BigDecimal getActualQuantity() 
    {
        return actualQuantity;
    }
    public void setMaterialCost(BigDecimal materialCost) 
    {
        this.materialCost = materialCost;
    }

    public BigDecimal getMaterialCost() 
    {
        return materialCost;
    }
    public void setUseTime(Date useTime) 
    {
        this.useTime = useTime;
    }

    public Date getUseTime() 
    {
        return useTime;
    }
    public void setOperatorName(String operatorName) 
    {
        this.operatorName = operatorName;
    }

    public String getOperatorName() 
    {
        return operatorName;
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
            .append("recordId", getRecordId())
            .append("batchNo", getBatchNo())
            .append("materialId", getMaterialId())
            .append("materialBatchNo", getMaterialBatchNo())
            .append("plannedQuantity", getPlannedQuantity())
            .append("actualQuantity", getActualQuantity())
            .append("materialCost", getMaterialCost())
            .append("useTime", getUseTime())
            .append("operatorName", getOperatorName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
