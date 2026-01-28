package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * æ‰¹æ¬¡æˆæœ¬对象 brew_batch_cost
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewBatchCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æˆæœ¬ID */
    private Long costId;

    /** æ‰¹æ¬¡å· */
    @Excel(name = "æ‰¹æ¬¡å·")
    private String batchNo;

    /** æˆæœ¬é¡¹ID */
    @Excel(name = "æˆæœ¬é¡¹ID")
    private Long costItemId;

    /** å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰ */
    @Excel(name = "å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰")
    private BigDecimal actualCost;

    /** æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰ */
    @Excel(name = "æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰")
    private BigDecimal costQuantity;

    /** è®¡ç®—æ˜Žç»† */
    @Excel(name = "è®¡ç®—æ˜Žç»†")
    private String calculationDetail;

    /** è®°å½•æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "è®°å½•æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
