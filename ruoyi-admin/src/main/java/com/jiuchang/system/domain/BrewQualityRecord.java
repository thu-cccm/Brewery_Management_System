package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 质检记录人? brew_quality_record
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewQualityRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 批次? */
    @Excel(name = "批次?")
    private String batchNo;

    /** 质检标准ID */
    @Excel(name = "质检标准ID")
    private Long standardId;

    /** 检测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectTime;

    /** 检测人 */
    @Excel(name = "检测人")
    private String inspectorName;

    /** 蒸搜索酒精度（%） */
    @Excel(name = "蒸搜索酒精度（%）")
    private BigDecimal alcoholBefore;

    /** 蒸搜索酒精度（%） */
    @Excel(name = "蒸搜索酒精度（%）")
    private BigDecimal alcoholAfter;

    /** 实际酒精度（%） */
    @Excel(name = "实际酒精度（%）")
    private BigDecimal alcoholActual;

    /** 酒精度误差（%） */
    @Excel(name = "酒精度误差（%）")
    private BigDecimal alcoholError;

    /** 产酒率（%） */
    @Excel(name = "产酒率（%）")
    private BigDecimal wineOutputRate;

    /** 色泽检测结果 */
    @Excel(name = "色泽检测结果")
    private String colorResult;

    /** 气味检测结果 */
    @Excel(name = "气味检测结果")
    private String smellResult;

    /** ?感检测结果 */
    @Excel(name = "?感检测结果")
    private String tasteResult;

    /** 质检结果（1?格 2搜索格 3待?检） */
    @Excel(name = "质检结果（1?格 2搜索格 3待?检）")
    private String qualityResult;

    /** 质?评分（0-100） */
    @Excel(name = "质?评分（0-100）")
    private BigDecimal qualityScore;

    /** 搜索格原因 */
    @Excel(name = "搜索格原因")
    private String unqualifiedReason;

    /** 处?建议 */
    @Excel(name = "处?建议")
    private String handleSuggestion;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }

    public void setStandardId(Long standardId) 
    {
        this.standardId = standardId;
    }

    public Long getStandardId() 
    {
        return standardId;
    }

    public void setInspectTime(Date inspectTime) 
    {
        this.inspectTime = inspectTime;
    }

    public Date getInspectTime() 
    {
        return inspectTime;
    }

    public void setInspectorName(String inspectorName) 
    {
        this.inspectorName = inspectorName;
    }

    public String getInspectorName() 
    {
        return inspectorName;
    }

    public void setAlcoholBefore(BigDecimal alcoholBefore) 
    {
        this.alcoholBefore = alcoholBefore;
    }

    public BigDecimal getAlcoholBefore() 
    {
        return alcoholBefore;
    }

    public void setAlcoholAfter(BigDecimal alcoholAfter) 
    {
        this.alcoholAfter = alcoholAfter;
    }

    public BigDecimal getAlcoholAfter() 
    {
        return alcoholAfter;
    }

    public void setAlcoholActual(BigDecimal alcoholActual) 
    {
        this.alcoholActual = alcoholActual;
    }

    public BigDecimal getAlcoholActual() 
    {
        return alcoholActual;
    }

    public void setAlcoholError(BigDecimal alcoholError) 
    {
        this.alcoholError = alcoholError;
    }

    public BigDecimal getAlcoholError() 
    {
        return alcoholError;
    }

    public void setWineOutputRate(BigDecimal wineOutputRate) 
    {
        this.wineOutputRate = wineOutputRate;
    }

    public BigDecimal getWineOutputRate() 
    {
        return wineOutputRate;
    }

    public void setColorResult(String colorResult) 
    {
        this.colorResult = colorResult;
    }

    public String getColorResult() 
    {
        return colorResult;
    }

    public void setSmellResult(String smellResult) 
    {
        this.smellResult = smellResult;
    }

    public String getSmellResult() 
    {
        return smellResult;
    }

    public void setTasteResult(String tasteResult) 
    {
        this.tasteResult = tasteResult;
    }

    public String getTasteResult() 
    {
        return tasteResult;
    }

    public void setQualityResult(String qualityResult) 
    {
        this.qualityResult = qualityResult;
    }

    public String getQualityResult() 
    {
        return qualityResult;
    }

    public void setQualityScore(BigDecimal qualityScore) 
    {
        this.qualityScore = qualityScore;
    }

    public BigDecimal getQualityScore() 
    {
        return qualityScore;
    }

    public void setUnqualifiedReason(String unqualifiedReason) 
    {
        this.unqualifiedReason = unqualifiedReason;
    }

    public String getUnqualifiedReason() 
    {
        return unqualifiedReason;
    }

    public void setHandleSuggestion(String handleSuggestion) 
    {
        this.handleSuggestion = handleSuggestion;
    }

    public String getHandleSuggestion() 
    {
        return handleSuggestion;
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
            .append("recordId", getRecordId())
            .append("batchNo", getBatchNo())
            .append("standardId", getStandardId())
            .append("inspectTime", getInspectTime())
            .append("inspectorName", getInspectorName())
            .append("alcoholBefore", getAlcoholBefore())
            .append("alcoholAfter", getAlcoholAfter())
            .append("alcoholActual", getAlcoholActual())
            .append("alcoholError", getAlcoholError())
            .append("wineOutputRate", getWineOutputRate())
            .append("colorResult", getColorResult())
            .append("smellResult", getSmellResult())
            .append("tasteResult", getTasteResult())
            .append("qualityResult", getQualityResult())
            .append("qualityScore", getQualityScore())
            .append("unqualifiedReason", getUnqualifiedReason())
            .append("handleSuggestion", getHandleSuggestion())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
