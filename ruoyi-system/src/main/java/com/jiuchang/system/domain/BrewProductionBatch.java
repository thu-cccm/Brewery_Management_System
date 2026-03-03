package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 生产批次搜索 brew_production_batch
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewProductionBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 批次ID */
    private Long batchId;

    /** 批次?（唯一追溯?） */
    @Excel(name = "批次?（唯一追溯?）")
    private String batchNo;

    /** 生产计划ID */
    @Excel(name = "生产计划ID")
    private Long planId;

    /** 酒搜索类ID */
    @Excel(name = "酒搜索类ID")
    private Long categoryId;

    /** ?方模?ID */
    @Excel(name = "?方模?ID")
    private Long templateId;

    /** 实际产?（L） */
    @Excel(name = "实际产?（L）")
    private BigDecimal actualOutput;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionDate;

    /** ?酵开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "?酵开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fermentationStart;

    /** ?酵结?时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "?酵结?时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fermentationEnd;

    /** 实际?酵天数 */
    @Excel(name = "实际?酵天数")
    private Long fermentationDays;

    /** 环境温度（℃） */
    @Excel(name = "环境温度（℃）")
    private BigDecimal environmentTemp;

    /** 环境湿度（%） */
    @Excel(name = "环境湿度（%）")
    private BigDecimal environmentHumidity;

    /** 负责人 */
    @Excel(name = "负责人")
    private String responsiblePerson;

    /** 批次状?（1备料中 2?酵中 3蒸?中 4质检中 5已完? 6异常） */
    @Excel(name = "批次状?（1备料中 2?酵中 3蒸?中 4质检中 5已完? 6异常）")
    private String batchStatus;

    /** 质检状?（0未检 1?格 2搜索格） */
    @Excel(name = "质检状?（0未检 1?格 2搜索格）")
    private String qualityStatus;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 批次原料搜索 */
    private List<BrewBatchMaterial> brewBatchMaterialList;

    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }

    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }

    public void setActualOutput(BigDecimal actualOutput) 
    {
        this.actualOutput = actualOutput;
    }

    public BigDecimal getActualOutput() 
    {
        return actualOutput;
    }

    public void setProductionDate(Date productionDate) 
    {
        this.productionDate = productionDate;
    }

    public Date getProductionDate() 
    {
        return productionDate;
    }

    public void setFermentationStart(Date fermentationStart) 
    {
        this.fermentationStart = fermentationStart;
    }

    public Date getFermentationStart() 
    {
        return fermentationStart;
    }

    public void setFermentationEnd(Date fermentationEnd) 
    {
        this.fermentationEnd = fermentationEnd;
    }

    public Date getFermentationEnd() 
    {
        return fermentationEnd;
    }

    public void setFermentationDays(Long fermentationDays) 
    {
        this.fermentationDays = fermentationDays;
    }

    public Long getFermentationDays() 
    {
        return fermentationDays;
    }

    public void setEnvironmentTemp(BigDecimal environmentTemp) 
    {
        this.environmentTemp = environmentTemp;
    }

    public BigDecimal getEnvironmentTemp() 
    {
        return environmentTemp;
    }

    public void setEnvironmentHumidity(BigDecimal environmentHumidity) 
    {
        this.environmentHumidity = environmentHumidity;
    }

    public BigDecimal getEnvironmentHumidity() 
    {
        return environmentHumidity;
    }

    public void setResponsiblePerson(String responsiblePerson) 
    {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson() 
    {
        return responsiblePerson;
    }

    public void setBatchStatus(String batchStatus) 
    {
        this.batchStatus = batchStatus;
    }

    public String getBatchStatus() 
    {
        return batchStatus;
    }

    public void setQualityStatus(String qualityStatus) 
    {
        this.qualityStatus = qualityStatus;
    }

    public String getQualityStatus() 
    {
        return qualityStatus;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public List<BrewBatchMaterial> getBrewBatchMaterialList()
    {
        return brewBatchMaterialList;
    }

    public void setBrewBatchMaterialList(List<BrewBatchMaterial> brewBatchMaterialList)
    {
        this.brewBatchMaterialList = brewBatchMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("batchId", getBatchId())
            .append("batchNo", getBatchNo())
            .append("planId", getPlanId())
            .append("categoryId", getCategoryId())
            .append("templateId", getTemplateId())
            .append("actualOutput", getActualOutput())
            .append("productionDate", getProductionDate())
            .append("fermentationStart", getFermentationStart())
            .append("fermentationEnd", getFermentationEnd())
            .append("fermentationDays", getFermentationDays())
            .append("environmentTemp", getEnvironmentTemp())
            .append("environmentHumidity", getEnvironmentHumidity())
            .append("responsiblePerson", getResponsiblePerson())
            .append("batchStatus", getBatchStatus())
            .append("qualityStatus", getQualityStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("brewBatchMaterialList", getBrewBatchMaterialList())
            .toString();
    }
}
