package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * ?方模请输入 brew_formula_template
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewFormulaTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模?ID */
    private Long templateId;

    /** 模?编辑 */
    @Excel(name = "模?编辑")
    private String templateCode;

    /** 模请输入称 */
    @Excel(name = "模请输入称")
    private String templateName;

    /** 酒搜索类ID */
    @Excel(name = "酒搜索类ID")
    private Long categoryId;

    /** 基准产?（L） */
    @Excel(name = "基准产?（L）")
    private BigDecimal baseOutput;

    /** 预期酒精度（%） */
    @Excel(name = "预期酒精度（%）")
    private BigDecimal expectedAlcohol;

    /** ?方说明 */
    @Excel(name = "?方说明")
    private String formulaDescription;

    /** 是?默认?方（0? 1是） */
    @Excel(name = "是?默认?方（0? 1是）")
    private String isDefault;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态（0正常 1停用）")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** ?方明细搜索 */
    private List<BrewFormulaDetail> brewFormulaDetailList;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setTemplateCode(String templateCode) 
    {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() 
    {
        return templateCode;
    }

    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setBaseOutput(BigDecimal baseOutput) 
    {
        this.baseOutput = baseOutput;
    }

    public BigDecimal getBaseOutput() 
    {
        return baseOutput;
    }

    public void setExpectedAlcohol(BigDecimal expectedAlcohol) 
    {
        this.expectedAlcohol = expectedAlcohol;
    }

    public BigDecimal getExpectedAlcohol() 
    {
        return expectedAlcohol;
    }

    public void setFormulaDescription(String formulaDescription) 
    {
        this.formulaDescription = formulaDescription;
    }

    public String getFormulaDescription() 
    {
        return formulaDescription;
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

    public List<BrewFormulaDetail> getBrewFormulaDetailList()
    {
        return brewFormulaDetailList;
    }

    public void setBrewFormulaDetailList(List<BrewFormulaDetail> brewFormulaDetailList)
    {
        this.brewFormulaDetailList = brewFormulaDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateCode", getTemplateCode())
            .append("templateName", getTemplateName())
            .append("categoryId", getCategoryId())
            .append("baseOutput", getBaseOutput())
            .append("expectedAlcohol", getExpectedAlcohol())
            .append("formulaDescription", getFormulaDescription())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("brewFormulaDetailList", getBrewFormulaDetailList())
            .toString();
    }
}
