package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 质检标准搜索 brew_quality_standard
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewQualityStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标准ID */
    private Long standardId;

    /** 酒搜索类ID */
    @Excel(name = "酒搜索类ID")
    private Long categoryId;

    /** 标准名称 */
    @Excel(name = "标准名称")
    private String standardName;

    /** 酒精度最?值（%） */
    @Excel(name = "酒精度最?值（%）")
    private BigDecimal alcoholMin;

    /** 酒精度最大值（%） */
    @Excel(name = "酒精度最大值（%）")
    private BigDecimal alcoholMax;

    /** 酒精度?许误差（%） */
    @Excel(name = "酒精度?许误差（%）")
    private BigDecimal alcoholTolerance;

    /** 色泽标准 */
    @Excel(name = "色泽标准")
    private String colorStandard;

    /** 气味标准 */
    @Excel(name = "气味标准")
    private String smellStandard;

    /** ?感标准 */
    @Excel(name = "?感标准")
    private String tasteStandard;

    /** 其他指标（JSON格?） */
    @Excel(name = "其他指标（JSON格?）")
    private String otherIndicators;

    /** 是?默认标准（0? 1是） */
    @Excel(name = "是?默认标准（0? 1是）")
    private String isDefault;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态（0正常 1停用）")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setStandardId(Long standardId) 
    {
        this.standardId = standardId;
    }

    public Long getStandardId() 
    {
        return standardId;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setStandardName(String standardName) 
    {
        this.standardName = standardName;
    }

    public String getStandardName() 
    {
        return standardName;
    }

    public void setAlcoholMin(BigDecimal alcoholMin) 
    {
        this.alcoholMin = alcoholMin;
    }

    public BigDecimal getAlcoholMin() 
    {
        return alcoholMin;
    }

    public void setAlcoholMax(BigDecimal alcoholMax) 
    {
        this.alcoholMax = alcoholMax;
    }

    public BigDecimal getAlcoholMax() 
    {
        return alcoholMax;
    }

    public void setAlcoholTolerance(BigDecimal alcoholTolerance) 
    {
        this.alcoholTolerance = alcoholTolerance;
    }

    public BigDecimal getAlcoholTolerance() 
    {
        return alcoholTolerance;
    }

    public void setColorStandard(String colorStandard) 
    {
        this.colorStandard = colorStandard;
    }

    public String getColorStandard() 
    {
        return colorStandard;
    }

    public void setSmellStandard(String smellStandard) 
    {
        this.smellStandard = smellStandard;
    }

    public String getSmellStandard() 
    {
        return smellStandard;
    }

    public void setTasteStandard(String tasteStandard) 
    {
        this.tasteStandard = tasteStandard;
    }

    public String getTasteStandard() 
    {
        return tasteStandard;
    }

    public void setOtherIndicators(String otherIndicators) 
    {
        this.otherIndicators = otherIndicators;
    }

    public String getOtherIndicators() 
    {
        return otherIndicators;
    }

    public void setIsDefault(String isDefault) 
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault() 
    {
        return isDefault;
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
            .append("standardId", getStandardId())
            .append("categoryId", getCategoryId())
            .append("standardName", getStandardName())
            .append("alcoholMin", getAlcoholMin())
            .append("alcoholMax", getAlcoholMax())
            .append("alcoholTolerance", getAlcoholTolerance())
            .append("colorStandard", getColorStandard())
            .append("smellStandard", getSmellStandard())
            .append("tasteStandard", getTasteStandard())
            .append("otherIndicators", getOtherIndicators())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
