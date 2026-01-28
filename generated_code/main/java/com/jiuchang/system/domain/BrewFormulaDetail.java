package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * é…æ–¹æ˜Žç»†对象 brew_formula_detail
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewFormulaDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æ˜Žç»†ID */
    private Long detailId;

    /** æ¨¡æ¿ID */
    @Excel(name = "æ¨¡æ¿ID")
    private Long templateId;

    /** åŽŸæ–™ID */
    @Excel(name = "åŽŸæ–™ID")
    private Long materialId;

    /** é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰ */
    @Excel(name = "é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰")
    private BigDecimal materialRatio;

    /** åŸºå‡†ç”¨é‡ */
    @Excel(name = "åŸºå‡†ç”¨é‡")
    private BigDecimal baseQuantity;

    /** æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰ */
    @Excel(name = "æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰")
    private String isMainMaterial;

    /** æŽ’åº */
    @Excel(name = "æŽ’åº")
    private Long sortOrder;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
