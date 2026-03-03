package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 酒搜索类搜索 brew_wine_category
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewWineCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ?类ID */
    private Long categoryId;

    /** ?类编辑 */
    @Excel(name = "?类编辑")
    private String categoryCode;

    /** ?类搜索称 */
    @Excel(name = "?类搜索称")
    private String categoryName;

    /** 酒类型（1白酒 2啤酒 3黄酒 4果酒） */
    @Excel(name = "酒类型（1白酒 2啤酒 3黄酒 4果酒）")
    private String categoryType;

    /** 酒精度范围-最?值（%） */
    @Excel(name = "酒精度范围-最?值（%）")
    private BigDecimal alcoholRangeMin;

    /** 酒精度范围-最大值（%） */
    @Excel(name = "酒精度范围-最大值（%）")
    private BigDecimal alcoholRangeMax;

    /** ?酵周期-最?天数 */
    @Excel(name = "?酵周期-最?天数")
    private Long fermentationDaysMin;

    /** ?酵周期-最大天数 */
    @Excel(name = "?酵周期-最大天数")
    private Long fermentationDaysMax;

    /** 最佳?酵温度（℃） */
    @Excel(name = "最佳?酵温度（℃）")
    private BigDecimal optimalTemperature;

    /** 最佳?酵湿度（%） */
    @Excel(name = "最佳?酵湿度（%）")
    private BigDecimal optimalHumidity;

    /** ?类搜索述 */
    @Excel(name = "?类搜索述")
    private String description;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态（0正常 1停用）")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
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
