package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * ?本统计搜索 brew_cost_statistics
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewCostStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计ID */
    private Long statId;

    /** 统计周期（202601/2026Q1） */
    @Excel(name = "统计周期（202601/2026Q1）")
    private String statPeriod;

    /** 统计类型（1月度 2季度 3年度） */
    @Excel(name = "统计类型（1月度 2季度 3年度）")
    private String statType;

    /** 总产?（L） */
    @Excel(name = "总产?（L）")
    private BigDecimal totalOutput;

    /** 总?本（元） */
    @Excel(name = "总?本（元）")
    private BigDecimal totalCost;

    /** 原料?本（元） */
    @Excel(name = "原料?本（元）")
    private BigDecimal materialCost;

    /** 能耗?本（元） */
    @Excel(name = "能耗?本（元）")
    private BigDecimal energyCost;

    /** 人工?本（元） */
    @Excel(name = "人工?本（元）")
    private BigDecimal laborCost;

    /** 其他?本（元） */
    @Excel(name = "其他?本（元）")
    private BigDecimal otherCost;

    /** 请输入本（元/L） */
    @Excel(name = "请输入本（元/L）")
    private BigDecimal unitCost;

    /** ?本趋势（1上? 2下? 3?平） */
    @Excel(name = "?本趋势（1上? 2下? 3?平）")
    private String costTrend;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setStatId(Long statId) 
    {
        this.statId = statId;
    }

    public Long getStatId() 
    {
        return statId;
    }

    public void setStatPeriod(String statPeriod) 
    {
        this.statPeriod = statPeriod;
    }

    public String getStatPeriod() 
    {
        return statPeriod;
    }

    public void setStatType(String statType) 
    {
        this.statType = statType;
    }

    public String getStatType() 
    {
        return statType;
    }

    public void setTotalOutput(BigDecimal totalOutput) 
    {
        this.totalOutput = totalOutput;
    }

    public BigDecimal getTotalOutput() 
    {
        return totalOutput;
    }

    public void setTotalCost(BigDecimal totalCost) 
    {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalCost() 
    {
        return totalCost;
    }

    public void setMaterialCost(BigDecimal materialCost) 
    {
        this.materialCost = materialCost;
    }

    public BigDecimal getMaterialCost() 
    {
        return materialCost;
    }

    public void setEnergyCost(BigDecimal energyCost) 
    {
        this.energyCost = energyCost;
    }

    public BigDecimal getEnergyCost() 
    {
        return energyCost;
    }

    public void setLaborCost(BigDecimal laborCost) 
    {
        this.laborCost = laborCost;
    }

    public BigDecimal getLaborCost() 
    {
        return laborCost;
    }

    public void setOtherCost(BigDecimal otherCost) 
    {
        this.otherCost = otherCost;
    }

    public BigDecimal getOtherCost() 
    {
        return otherCost;
    }

    public void setUnitCost(BigDecimal unitCost) 
    {
        this.unitCost = unitCost;
    }

    public BigDecimal getUnitCost() 
    {
        return unitCost;
    }

    public void setCostTrend(String costTrend) 
    {
        this.costTrend = costTrend;
    }

    public String getCostTrend() 
    {
        return costTrend;
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
            .append("statId", getStatId())
            .append("statPeriod", getStatPeriod())
            .append("statType", getStatType())
            .append("totalOutput", getTotalOutput())
            .append("totalCost", getTotalCost())
            .append("materialCost", getMaterialCost())
            .append("energyCost", getEnergyCost())
            .append("laborCost", getLaborCost())
            .append("otherCost", getOtherCost())
            .append("unitCost", getUnitCost())
            .append("costTrend", getCostTrend())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
