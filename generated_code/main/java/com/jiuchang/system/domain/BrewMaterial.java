package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * åŽŸæ–™ä¿¡æ¯对象 brew_material
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** åŽŸæ–™ID */
    private Long materialId;

    /** åŽŸæ–™ç¼–ç  */
    @Excel(name = "åŽŸæ–™ç¼–ç ")
    private String materialCode;

    /** åŽŸæ–™åç§° */
    @Excel(name = "åŽŸæ–™åç§°")
    private String materialName;

    /** åŽŸæ–™ç±»åž‹ï¼ˆ1ç²®é£Ÿç±» 2è¾…æ–™ç±» 3åŒ…è£…ç±»ï¼‰ */
    @Excel(name = "åŽŸæ–™ç±»åž‹ï¼ˆ1ç²®é£Ÿç±» 2è¾…æ–™ç±» 3åŒ…è£…ç±»ï¼‰")
    private String materialType;

    /** è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰ */
    @Excel(name = "è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰")
    private String materialUnit;

    /** è§„æ ¼è¯´æ˜Ž */
    @Excel(name = "è§„æ ¼è¯´æ˜Ž")
    private String materialSpec;

    /** å•ä»·ï¼ˆå…ƒï¼‰ */
    @Excel(name = "å•ä»·ï¼ˆå…ƒï¼‰")
    private BigDecimal unitPrice;

    /** ä¾›åº”å•†åç§° */
    @Excel(name = "ä¾›åº”å•†åç§°")
    private String supplierName;

    /** ä¾›åº”å•†è”ç³»æ–¹å¼ */
    @Excel(name = "ä¾›åº”å•†è”ç³»æ–¹å¼")
    private String supplierContact;

    /** å½“å‰åº“å­˜æ•°é‡ */
    @Excel(name = "å½“å‰åº“å­˜æ•°é‡")
    private BigDecimal stockQuantity;

    /** åº“å­˜é¢„è­¦é˜ˆå€¼ */
    @Excel(name = "åº“å­˜é¢„è­¦é˜ˆå€¼")
    private BigDecimal stockWarningValue;

    /** æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰ */
    @Excel(name = "æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰")
    private String isWarning;

    /** çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰ */
    @Excel(name = "çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰")
    private String status;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
    private String delFlag;

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }

    public void setMaterialCode(String materialCode) 
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() 
    {
        return materialCode;
    }

    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }

    public void setMaterialType(String materialType) 
    {
        this.materialType = materialType;
    }

    public String getMaterialType() 
    {
        return materialType;
    }

    public void setMaterialUnit(String materialUnit) 
    {
        this.materialUnit = materialUnit;
    }

    public String getMaterialUnit() 
    {
        return materialUnit;
    }

    public void setMaterialSpec(String materialSpec) 
    {
        this.materialSpec = materialSpec;
    }

    public String getMaterialSpec() 
    {
        return materialSpec;
    }

    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }

    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }

    public void setSupplierContact(String supplierContact) 
    {
        this.supplierContact = supplierContact;
    }

    public String getSupplierContact() 
    {
        return supplierContact;
    }

    public void setStockQuantity(BigDecimal stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getStockQuantity() 
    {
        return stockQuantity;
    }

    public void setStockWarningValue(BigDecimal stockWarningValue) 
    {
        this.stockWarningValue = stockWarningValue;
    }

    public BigDecimal getStockWarningValue() 
    {
        return stockWarningValue;
    }

    public void setIsWarning(String isWarning) 
    {
        this.isWarning = isWarning;
    }

    public String getIsWarning() 
    {
        return isWarning;
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
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("materialType", getMaterialType())
            .append("materialUnit", getMaterialUnit())
            .append("materialSpec", getMaterialSpec())
            .append("unitPrice", getUnitPrice())
            .append("supplierName", getSupplierName())
            .append("supplierContact", getSupplierContact())
            .append("stockQuantity", getStockQuantity())
            .append("stockWarningValue", getStockWarningValue())
            .append("isWarning", getIsWarning())
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
