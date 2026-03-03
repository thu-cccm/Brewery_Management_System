package com.jiuchang.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 质检报告人? brew_quality_report
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewQualityReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    private Long reportId;

    /** 报告编辑 */
    @Excel(name = "报告编辑")
    private String reportNo;

    /** 批次? */
    @Excel(name = "批次?")
    private String batchNo;

    /** 质检记录ID */
    @Excel(name = "质检记录ID")
    private Long recordId;

    /** 报告标题 */
    @Excel(name = "报告标题")
    private String reportTitle;

    /** 报告内容（HTML格?） */
    @Excel(name = "报告内容（HTML格?）")
    private String reportContent;

    /** 报告状?（1?稿 2已?布 3已归档） */
    @Excel(name = "报告状?（1?稿 2已?布 3已归档）")
    private String reportStatus;

    /** 审批状?（0待审批 1已通过 2已驳回） */
    @Excel(name = "审批状?（0待审批 1已通过 2已驳回）")
    private String approveStatus;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approveBy;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setReportId(Long reportId) 
    {
        this.reportId = reportId;
    }

    public Long getReportId() 
    {
        return reportId;
    }

    public void setReportNo(String reportNo) 
    {
        this.reportNo = reportNo;
    }

    public String getReportNo() 
    {
        return reportNo;
    }

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    public String getReportTitle() 
    {
        return reportTitle;
    }

    public void setReportContent(String reportContent) 
    {
        this.reportContent = reportContent;
    }

    public String getReportContent() 
    {
        return reportContent;
    }

    public void setReportStatus(String reportStatus) 
    {
        this.reportStatus = reportStatus;
    }

    public String getReportStatus() 
    {
        return reportStatus;
    }

    public void setApproveStatus(String approveStatus) 
    {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatus() 
    {
        return approveStatus;
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
            .append("reportId", getReportId())
            .append("reportNo", getReportNo())
            .append("batchNo", getBatchNo())
            .append("recordId", getRecordId())
            .append("reportTitle", getReportTitle())
            .append("reportContent", getReportContent())
            .append("reportStatus", getReportStatus())
            .append("approveStatus", getApproveStatus())
            .append("approveBy", getApproveBy())
            .append("approveTime", getApproveTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
