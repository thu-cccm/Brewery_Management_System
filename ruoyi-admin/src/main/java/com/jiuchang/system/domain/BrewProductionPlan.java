package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 生产计划搜索 brew_production_plan
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewProductionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    private Long planId;

    /** 计划编号 */
    @Excel(name = "计划编号")
    private String planCode;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String planName;

    /** 酒搜索类ID */
    @Excel(name = "酒搜索类ID")
    private Long categoryId;

    /** ?方模?ID */
    @Excel(name = "?方模?ID")
    private Long templateId;

    /** 目标产?（L） */
    @Excel(name = "目标产?（L）")
    private BigDecimal targetOutput;

    /** 计划开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartDate;

    /** 计划结?日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结?日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndDate;

    /** 负责人 */
    @Excel(name = "负责人")
    private String responsiblePerson;

    /** 计划状?（1待审核 2已审核 3生产中 4已完? 5已?消） */
    @Excel(name = "计划状?（1待审核 2已审核 3生产中 4已完? 5已?消）")
    private String planStatus;

    /** 审核人 */
    @Excel(name = "审核人")
    private String approveBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** 删除标志（0代表存在 2代表删除） */
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
