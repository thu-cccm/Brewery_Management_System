package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;

/**
 * 生产过程搜索 brew_production_process
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public class BrewProductionProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long processId;

    /** 批次? */
    @Excel(name = "批次?")
    private String batchNo;

    /** 工?阶段（1备料 2?酵 3蒸? 4?装） */
    @Excel(name = "工?阶段（1备料 2?酵 3蒸? 4?装）")
    private String processStage;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date processTime;

    /** 平?温度（℃） */
    @Excel(name = "平?温度（℃）")
    private BigDecimal avgTemperature;

    /** 平?湿度（%） */
    @Excel(name = "平?湿度（%）")
    private BigDecimal avgHumidity;

    /** 工数量（JSON格?存储其他?数） */
    @Excel(name = "工数量（JSON格?存储其他?数）")
    private String processData;

    /** ?作人 */
    @Excel(name = "?作人")
    private String operatorName;

    /** 删除标志（0代表存在 2代表删除） */
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
