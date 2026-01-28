package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * ?方明细搜索 brew_formula_detail
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewFormulaDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long detailId;

    /** 模?ID */
    @Excel(name = "模?ID")
    private Long templateId;

    /** 原料ID */
    @Excel(name = "原料ID")
    private Long materialId;

    /** ?比系数（原料用?/基准产?） */
    @Excel(name = "?比系数（原料用?/基准产?）")
    private BigDecimal materialRatio;

    /** 基准用? */
    @Excel(name = "基准用?")
    private BigDecimal baseQuantity;

    /** 是?主料（0? 1是） */
    @Excel(name = "是?主料（0? 1是）")
    private String isMainMaterial;

    /** 排? */
    @Excel(name = "排?")
    private Long sortOrder;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialRatio(BigDecimal materialRatio) 
    {
        this.materialRatio = materialRatio;
    }

    public BigDecimal getMaterialRatio() 
    {
        return materialRatio;
    }
    public void setBaseQuantity(BigDecimal baseQuantity) 
    {
        this.baseQuantity = baseQuantity;
    }

    public BigDecimal getBaseQuantity() 
    {
        return baseQuantity;
    }
    public void setIsMainMaterial(String isMainMaterial) 
    {
        this.isMainMaterial = isMainMaterial;
    }

    public String getIsMainMaterial() 
    {
        return isMainMaterial;
    }
    public void setSortOrder(Long sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder() 
    {
        return sortOrder;
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
            .append("detailId", getDetailId())
            .append("templateId", getTemplateId())
            .append("materialId", getMaterialId())
            .append("materialRatio", getMaterialRatio())
            .append("baseQuantity", getBaseQuantity())
            .append("isMainMaterial", getIsMainMaterial())
            .append("sortOrder", getSortOrder())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
