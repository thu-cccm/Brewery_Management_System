package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * è´¨æ£€æ ‡å‡†对象 brew_quality_standard
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewQualityStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æ ‡å‡†ID */
    private Long standardId;

    /** é…’å“ç§ç±»ID */
    @Excel(name = "é…’å“ç§ç±»ID")
    private Long categoryId;

    /** æ ‡å‡†åç§° */
    @Excel(name = "æ ‡å‡†åç§°")
    private String standardName;

    /** é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰ */
    @Excel(name = "é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰")
    private BigDecimal alcoholMin;

    /** é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰ */
    @Excel(name = "é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰")
    private BigDecimal alcoholMax;

    /** é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰ */
    @Excel(name = "é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰")
    private BigDecimal alcoholTolerance;

    /** è‰²æ³½æ ‡å‡† */
    @Excel(name = "è‰²æ³½æ ‡å‡†")
    private String colorStandard;

    /** æ°”å‘³æ ‡å‡† */
    @Excel(name = "æ°”å‘³æ ‡å‡†")
    private String smellStandard;

    /** å£æ„Ÿæ ‡å‡† */
    @Excel(name = "å£æ„Ÿæ ‡å‡†")
    private String tasteStandard;

    /** å…¶ä»–æŒ‡æ ‡ï¼ˆJSONæ ¼å¼ï¼‰ */
    @Excel(name = "å…¶ä»–æŒ‡æ ‡ï¼ˆJSONæ ¼å¼ï¼‰")
    private String otherIndicators;

    /** æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰ */
    @Excel(name = "æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰")
    private String isDefault;

    /** çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰ */
    @Excel(name = "çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰")
    private String status;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
