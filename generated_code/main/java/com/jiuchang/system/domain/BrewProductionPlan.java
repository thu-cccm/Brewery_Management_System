package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ç”Ÿäº§è®¡åˆ’对象 brew_production_plan
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewProductionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** è®¡åˆ’ID */
    private Long planId;

    /** è®¡åˆ’ç¼–å· */
    @Excel(name = "è®¡åˆ’ç¼–å·")
    private String planCode;

    /** è®¡åˆ’åç§° */
    @Excel(name = "è®¡åˆ’åç§°")
    private String planName;

    /** é…’å“ç§ç±»ID */
    @Excel(name = "é…’å“ç§ç±»ID")
    private Long categoryId;

    /** é…æ–¹æ¨¡æ¿ID */
    @Excel(name = "é…æ–¹æ¨¡æ¿ID")
    private Long templateId;

    /** ç›®æ ‡äº§é‡ï¼ˆLï¼‰ */
    @Excel(name = "ç›®æ ‡äº§é‡ï¼ˆLï¼‰")
    private BigDecimal targetOutput;

    /** è®¡åˆ’å¼€å§‹æ—¥æœŸ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "è®¡åˆ’å¼€å§‹æ—¥æœŸ", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartDate;

    /** è®¡åˆ’ç»“æŸæ—¥æœŸ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "è®¡åˆ’ç»“æŸæ—¥æœŸ", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndDate;

    /** è´Ÿè´£äºº */
    @Excel(name = "è´Ÿè´£äºº")
    private String responsiblePerson;

    /** è®¡åˆ’çŠ¶æ€ï¼ˆ1å¾…å®¡æ ¸ 2å·²å®¡æ ¸ 3ç”Ÿäº§ä¸­ 4å·²å®Œæˆ 5å·²å–æ¶ˆï¼‰ */
    @Excel(name = "è®¡åˆ’çŠ¶æ€ï¼ˆ1å¾…å®¡æ ¸ 2å·²å®¡æ ¸ 3ç”Ÿäº§ä¸­ 4å·²å®Œæˆ 5å·²å–æ¶ˆï¼‰")
    private String planStatus;

    /** å®¡æ ¸äºº */
    @Excel(name = "å®¡æ ¸äºº")
    private String approveBy;

    /** å®¡æ ¸æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "å®¡æ ¸æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
    private String delFlag;

    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }

    public void setPlanCode(String planCode) 
    {
        this.planCode = planCode;
    }

    public String getPlanCode() 
    {
        return planCode;
    }

    public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
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

    public void setTargetOutput(BigDecimal targetOutput) 
    {
        this.targetOutput = targetOutput;
    }

    public BigDecimal getTargetOutput() 
    {
        return targetOutput;
    }

    public void setPlanStartDate(Date planStartDate) 
    {
        this.planStartDate = planStartDate;
    }

    public Date getPlanStartDate() 
    {
        return planStartDate;
    }

    public void setPlanEndDate(Date planEndDate) 
    {
        this.planEndDate = planEndDate;
    }

    public Date getPlanEndDate() 
    {
        return planEndDate;
    }

    public void setResponsiblePerson(String responsiblePerson) 
    {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson() 
    {
        return responsiblePerson;
    }

    public void setPlanStatus(String planStatus) 
    {
        this.planStatus = planStatus;
    }

    public String getPlanStatus() 
    {
        return planStatus;
    }

    public void setApproveBy(String approveBy) 
    {
        this.approveBy = approveBy;
    }

    public String getApproveBy() 
    {
        return approveBy;
    }

    public void setApproveTime(Date approveTime) 
    {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() 
    {
        return approveTime;
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
            .append("planId", getPlanId())
            .append("planCode", getPlanCode())
            .append("planName", getPlanName())
            .append("categoryId", getCategoryId())
            .append("templateId", getTemplateId())
            .append("targetOutput", getTargetOutput())
            .append("planStartDate", getPlanStartDate())
            .append("planEndDate", getPlanEndDate())
            .append("responsiblePerson", getResponsiblePerson())
            .append("planStatus", getPlanStatus())
            .append("approveBy", getApproveBy())
            .append("approveTime", getApproveTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
