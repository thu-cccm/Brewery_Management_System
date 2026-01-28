package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * è´¨æ£€è®°å½•对象 brew_quality_record
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewQualityRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** è®°å½•ID */
    private Long recordId;

    /** æ‰¹æ¬¡å· */
    @Excel(name = "æ‰¹æ¬¡å·")
    private String batchNo;

    /** è´¨æ£€æ ‡å‡†ID */
    @Excel(name = "è´¨æ£€æ ‡å‡†ID")
    private Long standardId;

    /** æ£€æµ‹æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "æ£€æµ‹æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectTime;

    /** æ£€æµ‹äºº */
    @Excel(name = "æ£€æµ‹äºº")
    private String inspectorName;

    /** è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰")
    private BigDecimal alcoholBefore;

    /** è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰")
    private BigDecimal alcoholAfter;

    /** å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰")
    private BigDecimal alcoholActual;

    /** é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰ */
    @Excel(name = "é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰")
    private BigDecimal alcoholError;

    /** äº§é…’çŽ‡ï¼ˆ%ï¼‰ */
    @Excel(name = "äº§é…’çŽ‡ï¼ˆ%ï¼‰")
    private BigDecimal wineOutputRate;

    /** è‰²æ³½æ£€æµ‹ç»“æžœ */
    @Excel(name = "è‰²æ³½æ£€æµ‹ç»“æžœ")
    private String colorResult;

    /** æ°”å‘³æ£€æµ‹ç»“æžœ */
    @Excel(name = "æ°”å‘³æ£€æµ‹ç»“æžœ")
    private String smellResult;

    /** å£æ„Ÿæ£€æµ‹ç»“æžœ */
    @Excel(name = "å£æ„Ÿæ£€æµ‹ç»“æžœ")
    private String tasteResult;

    /** è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰ */
    @Excel(name = "è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰")
    private String qualityResult;

    /** è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰ */
    @Excel(name = "è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰")
    private BigDecimal qualityScore;

    /** ä¸åˆæ ¼åŽŸå›  */
    @Excel(name = "ä¸åˆæ ¼åŽŸå› ")
    private String unqualifiedReason;

    /** å¤„ç†å»ºè®® */
    @Excel(name = "å¤„ç†å»ºè®®")
    private String handleSuggestion;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
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
