package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ç”Ÿäº§æ‰¹æ¬¡对象 brew_production_batch
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewProductionBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æ‰¹æ¬¡ID */
    private Long batchId;

    /** æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰ */
    @Excel(name = "æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰")
    private String batchNo;

    /** ç”Ÿäº§è®¡åˆ’ID */
    @Excel(name = "ç”Ÿäº§è®¡åˆ’ID")
    private Long planId;

    /** é…’å“ç§ç±»ID */
    @Excel(name = "é…’å“ç§ç±»ID")
    private Long categoryId;

    /** é…æ–¹æ¨¡æ¿ID */
    @Excel(name = "é…æ–¹æ¨¡æ¿ID")
    private Long templateId;

    /** å®žé™…äº§é‡ï¼ˆLï¼‰ */
    @Excel(name = "å®žé™…äº§é‡ï¼ˆLï¼‰")
    private BigDecimal actualOutput;

    /** ç”Ÿäº§æ—¥æœŸ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "ç”Ÿäº§æ—¥æœŸ", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionDate;

    /** å‘é…µå¼€å§‹æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "å‘é…µå¼€å§‹æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fermentationStart;

    /** å‘é…µç»“æŸæ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "å‘é…µç»“æŸæ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fermentationEnd;

    /** å®žé™…å‘é…µå¤©æ•° */
    @Excel(name = "å®žé™…å‘é…µå¤©æ•°")
    private Long fermentationDays;

    /** çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰ */
    @Excel(name = "çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰")
    private BigDecimal environmentTemp;

    /** çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰")
    private BigDecimal environmentHumidity;

    /** è´Ÿè´£äºº */
    @Excel(name = "è´Ÿè´£äºº")
    private String responsiblePerson;

    /** æ‰¹æ¬¡çŠ¶æ€ï¼ˆ1å¤‡æ–™ä¸­ 2å‘é…µä¸­ 3è’¸é¦ä¸­ 4è´¨æ£€ä¸­ 5å·²å®Œæˆ 6å¼‚å¸¸ï¼‰ */
    @Excel(name = "æ‰¹æ¬¡çŠ¶æ€ï¼ˆ1å¤‡æ–™ä¸­ 2å‘é…µä¸­ 3è’¸é¦ä¸­ 4è´¨æ£€ä¸­ 5å·²å®Œæˆ 6å¼‚å¸¸ï¼‰")
    private String batchStatus;

    /** è´¨æ£€çŠ¶æ€ï¼ˆ0æœªæ£€ 1åˆæ ¼ 2ä¸åˆæ ¼ï¼‰ */
    @Excel(name = "è´¨æ£€çŠ¶æ€ï¼ˆ0æœªæ£€ 1åˆæ ¼ 2ä¸åˆæ ¼ï¼‰")
    private String qualityStatus;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
    private String delFlag;

    /** æ‰¹æ¬¡åŽŸæ–™信息 */
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
