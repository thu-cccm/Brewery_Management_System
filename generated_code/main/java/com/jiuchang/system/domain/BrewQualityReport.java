package com.jiuchang.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * è´¨æ£€æŠ¥å‘Š对象 brew_quality_report
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewQualityReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** æŠ¥å‘ŠID */
    private Long reportId;

    /** æŠ¥å‘Šç¼–å· */
    @Excel(name = "æŠ¥å‘Šç¼–å·")
    private String reportNo;

    /** æ‰¹æ¬¡å· */
    @Excel(name = "æ‰¹æ¬¡å·")
    private String batchNo;

    /** è´¨æ£€è®°å½•ID */
    @Excel(name = "è´¨æ£€è®°å½•ID")
    private Long recordId;

    /** æŠ¥å‘Šæ ‡é¢˜ */
    @Excel(name = "æŠ¥å‘Šæ ‡é¢˜")
    private String reportTitle;

    /** æŠ¥å‘Šå†…å®¹ï¼ˆHTMLæ ¼å¼ï¼‰ */
    @Excel(name = "æŠ¥å‘Šå†…å®¹ï¼ˆHTMLæ ¼å¼ï¼‰")
    private String reportContent;

    /** æŠ¥å‘ŠçŠ¶æ€ï¼ˆ1è‰ç¨¿ 2å·²å‘å¸ƒ 3å·²å½’æ¡£ï¼‰ */
    @Excel(name = "æŠ¥å‘ŠçŠ¶æ€ï¼ˆ1è‰ç¨¿ 2å·²å‘å¸ƒ 3å·²å½’æ¡£ï¼‰")
    private String reportStatus;

    /** å®¡æ‰¹çŠ¶æ€ï¼ˆ0å¾…å®¡æ‰¹ 1å·²é€šè¿‡ 2å·²é©³å›žï¼‰ */
    @Excel(name = "å®¡æ‰¹çŠ¶æ€ï¼ˆ0å¾…å®¡æ‰¹ 1å·²é€šè¿‡ 2å·²é©³å›žï¼‰")
    private String approveStatus;

    /** å®¡æ‰¹äºº */
    @Excel(name = "å®¡æ‰¹äºº")
    private String approveBy;

    /** å®¡æ‰¹æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "å®¡æ‰¹æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
