package com.jiuchang.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 工艺参数标准对象 brew_technology_standard
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public class BrewTechnologyStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标准ID */
    private Long standardId;

    /** 关联配方ID */
    @Excel(name = "关联配方ID")
    private Long formulaId;

    /** 生产阶段(mashing=糖化, boiling=煮沸, fermentation=发酵) */
    @Excel(name = "生产阶段", readConverterExp = "mashing=糖化,boiling=煮沸,fermentation=发酵")
    private String stageKey;

    /** 参数名称 */
    @Excel(name = "参数名称")
    private String paramName;

    /** 参数代码 */
    @Excel(name = "参数代码")
    private String paramCode;

    /** 标准值 */
    @Excel(name = "标准值")
    private String standardValue;

    /** 允许下限 */
    @Excel(name = "允许下限")
    private BigDecimal minValue;

    /** 允许上限 */
    @Excel(name = "允许上限")
    private BigDecimal maxValue;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 排序号 */
    @Excel(name = "排序号")
    private Integer orderNum;

    public void setStandardId(Long standardId) 
    {
        this.standardId = standardId;
    }

    public Long getStandardId() 
    {
        return standardId;
    }
    public void setFormulaId(Long formulaId) 
    {
        this.formulaId = formulaId;
    }

    public Long getFormulaId() 
    {
        return formulaId;
    }
    public void setStageKey(String stageKey) 
    {
        this.stageKey = stageKey;
    }

    public String getStageKey() 
    {
        return stageKey;
    }
    public void setParamName(String paramName) 
    {
        this.paramName = paramName;
    }

    public String getParamName() 
    {
        return paramName;
    }
    public void setParamCode(String paramCode) 
    {
        this.paramCode = paramCode;
    }

    public String getParamCode() 
    {
        return paramCode;
    }
    public void setStandardValue(String standardValue) 
    {
        this.standardValue = standardValue;
    }

    public String getStandardValue() 
    {
        return standardValue;
    }
    public void setMinValue(BigDecimal minValue) 
    {
        this.minValue = minValue;
    }

    public BigDecimal getMinValue() 
    {
        return minValue;
    }
    public void setMaxValue(BigDecimal maxValue) 
    {
        this.maxValue = maxValue;
    }

    public BigDecimal getMaxValue() 
    {
        return maxValue;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("standardId", getStandardId())
            .append("formulaId", getFormulaId())
            .append("stageKey", getStageKey())
            .append("paramName", getParamName())
            .append("paramCode", getParamCode())
            .append("standardValue", getStandardValue())
            .append("minValue", getMinValue())
            .append("maxValue", getMaxValue())
            .append("unit", getUnit())
            .append("orderNum", getOrderNum())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
