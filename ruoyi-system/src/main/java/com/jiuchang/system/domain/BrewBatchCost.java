package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 批次?本搜索 brew_batch_cost
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewBatchCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ?本ID */
    private Long costId;

    /** 批次? */
    @Excel(name = "批次?")
    private String batchNo;

    /** ?本项ID */
    @Excel(name = "?本项ID")
    private Long costItemId;

    /** 实际?本金?（元） */
    @Excel(name = "实际?本金?（元）")
    private BigDecimal actualCost;

    /** ?本数?（如工时停用电?） */
    @Excel(name = "?本数?（如工时停用电?）")
    private BigDecimal costQuantity;

    /** 计算明细 */
    @Excel(name = "计算明细")
    private String calculationDetail;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setCostId(Long costId) 
    {
        this.costId = costId;
    }

    public Long getCostId() 
    {
        return costId;
    }

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }

    public void setCostItemId(Long costItemId) 
    {
        this.costItemId = costItemId;
    }

    public Long getCostItemId() 
    {
        return costItemId;
    }

    public void setActualCost(BigDecimal actualCost) 
    {
        this.actualCost = actualCost;
    }

    public BigDecimal getActualCost() 
    {
        return actualCost;
    }

    public void setCostQuantity(BigDecimal costQuantity) 
    {
        this.costQuantity = costQuantity;
    }

    public BigDecimal getCostQuantity() 
    {
        return costQuantity;
    }

    public void setCalculationDetail(String calculationDetail) 
    {
        this.calculationDetail = calculationDetail;
    }

    public String getCalculationDetail() 
    {
        return calculationDetail;
    }

    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
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
            .append("costId", getCostId())
            .append("batchNo", getBatchNo())
            .append("costItemId", getCostItemId())
            .append("actualCost", getActualCost())
            .append("costQuantity", getCostQuantity())
            .append("calculationDetail", getCalculationDetail())
            .append("recordTime", getRecordTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
