package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ç”Ÿäº§è¿‡ç¨‹对象 brew_production_process
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewProductionProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** è®°å½•ID */
    private Long processId;

    /** æ‰¹æ¬¡å· */
    @Excel(name = "æ‰¹æ¬¡å·")
    private String batchNo;

    /** å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰ */
    @Excel(name = "å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰")
    private String processStage;

    /** è®°å½•æ—¶é—´ */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "è®°å½•æ—¶é—´", width = 30, dateFormat = "yyyy-MM-dd")
    private Date processTime;

    /** å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰ */
    @Excel(name = "å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰")
    private BigDecimal avgTemperature;

    /** å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰ */
    @Excel(name = "å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰")
    private BigDecimal avgHumidity;

    /** å·¥åºæ•°æ®ï¼ˆJSONæ ¼å¼å­˜å‚¨å…¶ä»–å‚æ•°ï¼‰ */
    @Excel(name = "å·¥åºæ•°æ®ï¼ˆJSONæ ¼å¼å­˜å‚¨å…¶ä»–å‚æ•°ï¼‰")
    private String processData;

    /** æ“ä½œäºº */
    @Excel(name = "æ“ä½œäºº")
    private String operatorName;

    /** åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰ */
    private String delFlag;

    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }

    public void setProcessStage(String processStage) 
    {
        this.processStage = processStage;
    }

    public String getProcessStage() 
    {
        return processStage;
    }

    public void setProcessTime(Date processTime) 
    {
        this.processTime = processTime;
    }

    public Date getProcessTime() 
    {
        return processTime;
    }

    public void setAvgTemperature(BigDecimal avgTemperature) 
    {
        this.avgTemperature = avgTemperature;
    }

    public BigDecimal getAvgTemperature() 
    {
        return avgTemperature;
    }

    public void setAvgHumidity(BigDecimal avgHumidity) 
    {
        this.avgHumidity = avgHumidity;
    }

    public BigDecimal getAvgHumidity() 
    {
        return avgHumidity;
    }

    public void setProcessData(String processData) 
    {
        this.processData = processData;
    }

    public String getProcessData() 
    {
        return processData;
    }

    public void setOperatorName(String operatorName) 
    {
        this.operatorName = operatorName;
    }

    public String getOperatorName() 
    {
        return operatorName;
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
            .append("processId", getProcessId())
            .append("batchNo", getBatchNo())
            .append("processStage", getProcessStage())
            .append("processTime", getProcessTime())
            .append("avgTemperature", getAvgTemperature())
            .append("avgHumidity", getAvgHumidity())
            .append("processData", getProcessData())
            .append("operatorName", getOperatorName())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
