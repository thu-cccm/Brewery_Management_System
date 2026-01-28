package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * æ‰¹æ¬¡åŽŸæ–™对象 brew_batch_material
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewBatchMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** è®°å½•ID */
    private Long recordId;

    /** æ‰¹æ¬¡å· */
    @Excel(name = "æ‰¹æ¬¡å·")
    private String batchNo;

    /** åŽŸæ–™ID */
    @Excel(name = "åŽŸæ–™ID")
    private Long materialId;

    /** åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰ */
    @Excel(name = "åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰")
    private String materialBatchNo;

    /** è®¡åˆ’ç”¨é‡ */
    @Excel(name = "è®¡åˆ’ç”¨é‡")
    private BigDecimal plannedQuantity;

    /** å®žé™…ç”¨é‡ */
    @Excel(name = "å®žé™…ç”¨é‡")
    private BigDecimal actualQuantity;

    /** åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal materialCost;

    /** ä½¿ç”¨æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "ä½¿ç”¨æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** æ“ä½œäºº */
    @Excel(name = "æ“ä½œäºº")
    private String operatorName;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
