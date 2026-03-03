package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 批次损耗分析对象 brew_batch_yield
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public class BrewBatchYield extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 损耗ID */
    private Long yieldId;

    /** 生产批次ID */
    @Excel(name = "生产批次ID")
    private Long batchId;

    /** 阶段(mashing=糖化, fermentation=发酵, packaging=包装) */
    @Excel(name = "阶段", readConverterExp = "mashing=糖化,fermentation=发酵,packaging=包装")
    private String stageKey;

    /** 投入数量 */
    @Excel(name = "投入数量")
    private BigDecimal inputQty;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private BigDecimal outputQty;

    /** 损耗数量 */
    @Excel(name = "损耗数量")
    private BigDecimal lossQty;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 是否异常(0否 1是) */
    @Excel(name = "是否异常", readConverterExp = "0=否,1=是")
    private String isAbnormal;

    public void setYieldId(Long yieldId) 
    {
        this.yieldId = yieldId;
    }

    public Long getYieldId() 
    {
        return yieldId;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setStageKey(String stageKey) 
    {
        this.stageKey = stageKey;
    }

    public String getStageKey() 
    {
        return stageKey;
    }
    public void setInputQty(BigDecimal inputQty) 
    {
        this.inputQty = inputQty;
    }

    public BigDecimal getInputQty() 
    {
        return inputQty;
    }
    public void setOutputQty(BigDecimal outputQty) 
    {
        this.outputQty = outputQty;
    }

    public BigDecimal getOutputQty() 
    {
        return outputQty;
    }
    public void setLossQty(BigDecimal lossQty) 
    {
        this.lossQty = lossQty;
    }

    public BigDecimal getLossQty() 
    {
        return lossQty;
    }
    public void setLossRate(BigDecimal lossRate) 
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate() 
    {
        return lossRate;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setIsAbnormal(String isAbnormal) 
    {
        this.isAbnormal = isAbnormal;
    }

    public String getIsAbnormal() 
    {
        return isAbnormal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("yieldId", getYieldId())
            .append("batchId", getBatchId())
            .append("stageKey", getStageKey())
            .append("inputQty", getInputQty())
            .append("outputQty", getOutputQty())
            .append("lossQty", getLossQty())
            .append("lossRate", getLossRate())
            .append("unit", getUnit())
            .append("isAbnormal", getIsAbnormal())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
