package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * é…’å“ç§ç±»对象 brew_wine_category
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewWineCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** å“ç±»ID */
    private Long categoryId;

    /** å“ç±»ç¼–ç  */
    @Excel(name = "å“ç±»ç¼–ç ")
    private String categoryCode;

    /** å“ç±»åç§° */
    @Excel(name = "å“ç±»åç§°")
    private String categoryName;

    /** é…’ç±»åž‹ï¼ˆ1ç™½é…’ 2å•¤é…’ 3é»„é…’ 4æžœé…’ï¼‰ */
    @Excel(name = "é…’ç±»åž‹ï¼ˆ1ç™½é…’ 2å•¤é…’ 3é»„é…’ 4æžœé…’ï¼‰")
    private String categoryType;

    /** é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰ */
    @Excel(name = "é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰")
    private BigDecimal alcoholRangeMin;

    /** é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰ */
    @Excel(name = "é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰")
    private BigDecimal alcoholRangeMax;

    /** å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•° */
    @Excel(name = "å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•°")
    private Long fermentationDaysMin;

    /** å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•° */
    @Excel(name = "å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•°")
    private Long fermentationDaysMax;

    /** æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰ */
    @Excel(name = "æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰")
    private BigDecimal optimalTemperature;

    /** æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰")
    private BigDecimal optimalHumidity;

    /** å“ç±»æè¿° */
    @Excel(name = "å“ç±»æè¿°")
    private String description;

    /** çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰ */
    @Excel(name = "çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰")
    private String status;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
    private String delFlag;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }

    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }

    public void setCategoryType(String categoryType) 
    {
        this.categoryType = categoryType;
    }

    public String getCategoryType() 
    {
        return categoryType;
    }

    public void setAlcoholRangeMin(BigDecimal alcoholRangeMin) 
    {
        this.alcoholRangeMin = alcoholRangeMin;
    }

    public BigDecimal getAlcoholRangeMin() 
    {
        return alcoholRangeMin;
    }

    public void setAlcoholRangeMax(BigDecimal alcoholRangeMax) 
    {
        this.alcoholRangeMax = alcoholRangeMax;
    }

    public BigDecimal getAlcoholRangeMax() 
    {
        return alcoholRangeMax;
    }

    public void setFermentationDaysMin(Long fermentationDaysMin) 
    {
        this.fermentationDaysMin = fermentationDaysMin;
    }

    public Long getFermentationDaysMin() 
    {
        return fermentationDaysMin;
    }

    public void setFermentationDaysMax(Long fermentationDaysMax) 
    {
        this.fermentationDaysMax = fermentationDaysMax;
    }

    public Long getFermentationDaysMax() 
    {
        return fermentationDaysMax;
    }

    public void setOptimalTemperature(BigDecimal optimalTemperature) 
    {
        this.optimalTemperature = optimalTemperature;
    }

    public BigDecimal getOptimalTemperature() 
    {
        return optimalTemperature;
    }

    public void setOptimalHumidity(BigDecimal optimalHumidity) 
    {
        this.optimalHumidity = optimalHumidity;
    }

    public BigDecimal getOptimalHumidity() 
    {
        return optimalHumidity;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
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
            .append("categoryId", getCategoryId())
            .append("categoryCode", getCategoryCode())
            .append("categoryName", getCategoryName())
            .append("categoryType", getCategoryType())
            .append("alcoholRangeMin", getAlcoholRangeMin())
            .append("alcoholRangeMax", getAlcoholRangeMax())
            .append("fermentationDaysMin", getFermentationDaysMin())
            .append("fermentationDaysMax", getFermentationDaysMax())
            .append("optimalTemperature", getOptimalTemperature())
            .append("optimalHumidity", getOptimalHumidity())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
