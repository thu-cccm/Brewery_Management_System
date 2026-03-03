package com.jiuchang.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiuchang.common.annotation.Excel;
import com.jiuchang.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 生产过程监控对象 brew_process_monitor
 * 
 * @author jiuchang
 * @date 2026-02-04
 */
public class BrewProcessMonitor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 监控ID */
    private Long monitorId;

    /** 生产批次ID */
    @Excel(name = "生产批次ID")
    private Long batchId;

    /** 生产阶段 */
    @Excel(name = "生产阶段")
    private String stageKey;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    /** 温度(℃) */
    @Excel(name = "温度(℃)")
    private BigDecimal temperature;

    /** 压力(MPa) */
    @Excel(name = "压力(MPa)")
    private BigDecimal pressure;

    /** 糖度(°P) */
    @Excel(name = "糖度(°P)")
    private BigDecimal sugarDegree;

    /** PH值 */
    @Excel(name = "PH值")
    private BigDecimal phValue;

    /** 酒精度(%vol) */
    @Excel(name = "酒精度(%vol)")
    private BigDecimal alcohol;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 是否异常(0否 1是) */
    @Excel(name = "是否异常", readConverterExp = "0=否,1=是")
    private String isAbnormal;

    public void setMonitorId(Long monitorId) 
    {
        this.monitorId = monitorId;
    }

    public Long getMonitorId() 
    {
        return monitorId;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setStageKey(String stageKey) 
    {
        this.stageKey = stageKey;
    }

    public String getStageKey() 
    {
        return stageKey;
    }
    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }
    public void setTemperature(BigDecimal temperature) 
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature() 
    {
        return temperature;
    }
    public void setPressure(BigDecimal pressure) 
    {
        this.pressure = pressure;
    }

    public BigDecimal getPressure() 
    {
        return pressure;
    }
    public void setSugarDegree(BigDecimal sugarDegree) 
    {
        this.sugarDegree = sugarDegree;
    }

    public BigDecimal getSugarDegree() 
    {
        return sugarDegree;
    }
    public void setPhValue(BigDecimal phValue) 
    {
        this.phValue = phValue;
    }

    public BigDecimal getPhValue() 
    {
        return phValue;
    }
    public void setAlcohol(BigDecimal alcohol) 
    {
        this.alcohol = alcohol;
    }

    public BigDecimal getAlcohol() 
    {
        return alcohol;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setIsAbnormal(String isAbnormal) 
    {
        this.isAbnormal = isAbnormal;
    }

    public String getIsAbnormal() 
    {
        return isAbnormal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("monitorId", getMonitorId())
            .append("batchId", getBatchId())
            .append("stageKey", getStageKey())
            .append("recordTime", getRecordTime())
            .append("temperature", getTemperature())
            .append("pressure", getPressure())
            .append("sugarDegree", getSugarDegree())
            .append("phValue", getPhValue())
            .append("alcohol", getAlcohol())
            .append("operator", getOperator())
            .append("isAbnormal", getIsAbnormal())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
