package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * åº“å­˜å˜åŠ¨对象 brew_stock_record
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewStockRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** è®°å½•ID */
    private Long recordId;

    /** åŽŸæ–™ID */
    @Excel(name = "åŽŸæ–™ID")
    private Long materialId;

    /** å˜åŠ¨ç±»åž‹ï¼ˆ1å…¥åº“ 2å‡ºåº“ 3ç›˜ç‚¹ 4æŠ¥æŸï¼‰ */
    @Excel(name = "å˜åŠ¨ç±»åž‹ï¼ˆ1å…¥åº“ 2å‡ºåº“ 3ç›˜ç‚¹ 4æŠ¥æŸï¼‰")
    private String changeType;

    /** å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰ */
    @Excel(name = "å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰")
    private BigDecimal changeQuantity;

    /** å˜åŠ¨å‰åº“å­˜ */
    @Excel(name = "å˜åŠ¨å‰åº“å­˜")
    private BigDecimal beforeQuantity;

    /** å˜åŠ¨åŽåº“å­˜ */
    @Excel(name = "å˜åŠ¨åŽåº“å­˜")
    private BigDecimal afterQuantity;

    /** å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰ */
    @Excel(name = "å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰")
    private String relatedBatchNo;

    /** æ“ä½œäºº */
    @Excel(name = "æ“ä½œäºº")
    private String operatorName;

    /** å˜åŠ¨åŽŸå›  */
    @Excel(name = "å˜åŠ¨åŽŸå› ")
    private String changeReason;

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

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }

    public void setChangeType(String changeType) 
    {
        this.changeType = changeType;
    }

    public String getChangeType() 
    {
        return changeType;
    }

    public void setChangeQuantity(BigDecimal changeQuantity) 
    {
        this.changeQuantity = changeQuantity;
    }

    public BigDecimal getChangeQuantity() 
    {
        return changeQuantity;
    }

    public void setBeforeQuantity(BigDecimal beforeQuantity) 
    {
        this.beforeQuantity = beforeQuantity;
    }

    public BigDecimal getBeforeQuantity() 
    {
        return beforeQuantity;
    }

    public void setAfterQuantity(BigDecimal afterQuantity) 
    {
        this.afterQuantity = afterQuantity;
    }

    public BigDecimal getAfterQuantity() 
    {
        return afterQuantity;
    }

    public void setRelatedBatchNo(String relatedBatchNo) 
    {
        this.relatedBatchNo = relatedBatchNo;
    }

    public String getRelatedBatchNo() 
    {
        return relatedBatchNo;
    }

    public void setOperatorName(String operatorName) 
    {
        this.operatorName = operatorName;
    }

    public String getOperatorName() 
    {
        return operatorName;
    }

    public void setChangeReason(String changeReason) 
    {
        this.changeReason = changeReason;
    }

    public String getChangeReason() 
    {
        return changeReason;
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
            .append("materialId", getMaterialId())
            .append("changeType", getChangeType())
            .append("changeQuantity", getChangeQuantity())
            .append("beforeQuantity", getBeforeQuantity())
            .append("afterQuantity", getAfterQuantity())
            .append("relatedBatchNo", getRelatedBatchNo())
            .append("operatorName", getOperatorName())
            .append("changeReason", getChangeReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
