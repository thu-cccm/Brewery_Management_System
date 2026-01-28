package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * æˆæœ¬é¡¹对象 brew_cost_item
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewCostItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æˆæœ¬é¡¹ID */
    private Long itemId;

    /** æˆæœ¬é¡¹ç¼–ç  */
    @Excel(name = "æˆæœ¬é¡¹ç¼–ç ")
    private String itemCode;

    /** æˆæœ¬é¡¹åç§° */
    @Excel(name = "æˆæœ¬é¡¹åç§°")
    private String itemName;

    /** æˆæœ¬ç±»åž‹ï¼ˆ1åŽŸæ–™æˆæœ¬ 2èƒ½è€—æˆæœ¬ 3äººå·¥æˆæœ¬ 4å…¶ä»–ï¼‰ */
    @Excel(name = "æˆæœ¬ç±»åž‹ï¼ˆ1åŽŸæ–™æˆæœ¬ 2èƒ½è€—æˆæœ¬ 3äººå·¥æˆæœ¬ 4å…¶ä»–ï¼‰")
    private String itemType;

    /** è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰ */
    @Excel(name = "è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰")
    private String calculationMethod;

    /** å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰ */
    @Excel(name = "å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰")
    private BigDecimal unitCost;

    /** çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰ */
    @Excel(name = "çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰")
    private String status;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
