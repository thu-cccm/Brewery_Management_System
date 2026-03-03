package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 原料信息对象 brew_material
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 原料ID */
    private Long materialId;

    /** 原料编码 */
    @Excel(name = "原料编码")
    private String materialCode;

    /** 原料名称 */
    @Excel(name = "原料名称")
    private String materialName;

    /** 原料类型（1粮食类 2辅料类 3包装类） */
    @Excel(name = "原料类型（1粮食类 2辅料类 3包装类）")
    private String materialType;

    /** 计请输入（kg/L/个） */
    @Excel(name = "计请输入（kg/L/个）")
    private String materialUnit;

    /** 规格说明 */
    @Excel(name = "规格说明")
    private String materialSpec;

    /** 单价（元） */
    @Excel(name = "单价（元）")
    private BigDecimal unitPrice;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商联系方式 */
    @Excel(name = "供应商联系方式")
    private String supplierContact;

    /** 当前库存数量 */
    @Excel(name = "当前库存数量")
    private BigDecimal stockQuantity;

    /** 库存预警阈值 */
    @Excel(name = "库存预警阈值")
    private BigDecimal stockWarningValue;

    /** 是否预警（0否 1是） */
    @Excel(name = "是否预警（0否 1是）")
    private String isWarning;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态（0正常 1停用）")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
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
