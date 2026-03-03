package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * ?本项搜索 brew_cost_item
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewCostItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ?本项ID */
    private Long itemId;

    /** ?本项编辑 */
    @Excel(name = "?本项编辑")
    private String itemCode;

    /** ?本项搜索称 */
    @Excel(name = "?本项搜索称")
    private String itemName;

    /** ?本类型（1原料?本 2能耗?本 3人工?本 4其他） */
    @Excel(name = "?本类型（1原料?本 2能耗?本 3人工?本 4其他）")
    private String itemType;

    /** 计算方?（1固定值 2按产? 3按工时） */
    @Excel(name = "计算方?（1固定值 2按产? 3按工时）")
    private String calculationMethod;

    /** 请输入本（元） */
    @Excel(name = "请输入本（元）")
    private BigDecimal unitCost;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态（0正常 1停用）")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }

    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public void setItemType(String itemType) 
    {
        this.itemType = itemType;
    }

    public String getItemType() 
    {
        return itemType;
    }

    public void setCalculationMethod(String calculationMethod) 
    {
        this.calculationMethod = calculationMethod;
    }

    public String getCalculationMethod() 
    {
        return calculationMethod;
    }

    public void setUnitCost(BigDecimal unitCost) 
    {
        this.unitCost = unitCost;
    }

    public BigDecimal getUnitCost() 
    {
        return unitCost;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("itemType", getItemType())
            .append("calculationMethod", getCalculationMethod())
            .append("unitCost", getUnitCost())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
