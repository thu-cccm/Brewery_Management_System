package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 库存?动搜索 brew_stock_record
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewStockRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 原料ID */
    @Excel(name = "原料ID")
    private Long materialId;

    /** ?动类型（1入库 2出库 3盘点 4报?） */
    @Excel(name = "?动类型（1入库 2出库 3盘点 4报?）")
    private String changeType;

    /** ?动数?（正数入库，负数出库） */
    @Excel(name = "?动数?（正数入库，负数出库）")
    private BigDecimal changeQuantity;

    /** ?动?库存 */
    @Excel(name = "?动?库存")
    private BigDecimal beforeQuantity;

    /** ?动?库存 */
    @Excel(name = "?动?库存")
    private BigDecimal afterQuantity;

    /** 关?批次?（入库时填供应商批次，出库时填生产批次） */
    @Excel(name = "关?批次?（入库时填供应商批次，出库时填生产批次）")
    private String relatedBatchNo;

    /** ?作人 */
    @Excel(name = "?作人")
    private String operatorName;

    /** ?动原因 */
    @Excel(name = "?动原因")
    private String changeReason;

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
