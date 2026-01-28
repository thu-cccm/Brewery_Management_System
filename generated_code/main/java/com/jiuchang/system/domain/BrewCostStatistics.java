package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * æˆæœ¬ç»Ÿè®¡对象 brew_cost_statistics
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewCostStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ç»Ÿè®¡ID */
    private Long statId;

    /** ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰ */
    @Excel(name = "ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰")
    private String statPeriod;

    /** ç»Ÿè®¡ç±»åž‹ï¼ˆ1æœˆåº¦ 2å­£åº¦ 3å¹´åº¦ï¼‰ */
    @Excel(name = "ç»Ÿè®¡ç±»åž‹ï¼ˆ1æœˆåº¦ 2å­£åº¦ 3å¹´åº¦ï¼‰")
    private String statType;

    /** æ€»äº§é‡ï¼ˆLï¼‰ */
    @Excel(name = "æ€»äº§é‡ï¼ˆLï¼‰")
    private BigDecimal totalOutput;

    /** æ€»æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "æ€»æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal totalCost;

    /** åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal materialCost;

    /** èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal energyCost;

    /** äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal laborCost;

    /** å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal otherCost;

    /** å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰ */
    @Excel(name = "å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰")
    private BigDecimal unitCost;

    /** æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰ */
    @Excel(name = "æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰")
    private String costTrend;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
