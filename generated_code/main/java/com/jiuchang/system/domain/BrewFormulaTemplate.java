package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * é…æ–¹æ¨¡æ¿对象 brew_formula_template
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewFormulaTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æ¨¡æ¿ID */
    private Long templateId;

    /** æ¨¡æ¿ç¼–ç  */
    @Excel(name = "æ¨¡æ¿ç¼–ç ")
    private String templateCode;

    /** æ¨¡æ¿åç§° */
    @Excel(name = "æ¨¡æ¿åç§°")
    private String templateName;

    /** é…’å“ç§ç±»ID */
    @Excel(name = "é…’å“ç§ç±»ID")
    private Long categoryId;

    /** åŸºå‡†äº§é‡ï¼ˆLï¼‰ */
    @Excel(name = "åŸºå‡†äº§é‡ï¼ˆLï¼‰")
    private BigDecimal baseOutput;

    /** é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰")
    private BigDecimal expectedAlcohol;

    /** é…æ–¹è¯´æ˜Ž */
    @Excel(name = "é…æ–¹è¯´æ˜Ž")
    private String formulaDescription;

    /** æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰ */
    @Excel(name = "æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰")
    private String isDefault;

    /** çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰ */
    @Excel(name = "çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰")
    private String status;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
    private String delFlag;

    /** é…æ–¹æ˜Žç»†信息 */
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
