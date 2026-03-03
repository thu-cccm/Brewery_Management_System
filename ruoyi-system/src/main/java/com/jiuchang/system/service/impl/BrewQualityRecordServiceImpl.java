package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewQualityRecordMapper;
import com.jiuchang.system.domain.BrewQualityRecord;
import com.jiuchang.system.service.IBrewQualityRecordService;

/**
 * 质检记录Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewQualityRecordServiceImpl implements IBrewQualityRecordService 
{
    @Autowired
    private BrewQualityRecordMapper brewQualityRecordMapper;

    /**
     * ??质检记录
     * 
     * @param recordId 质检记录??
     * @return 质检记录
     */
    @Override
    public BrewQualityRecord selectBrewQualityRecordByRecordId(Long recordId)
    {
        return brewQualityRecordMapper.selectBrewQualityRecordByRecordId(recordId);
    }

    /**
     * ??质检记录??
     * 
     * @param brewQualityRecord 质检记录
     * @return 质检记录
     */
    @Override
    public List<BrewQualityRecord> selectBrewQualityRecordList(BrewQualityRecord brewQualityRecord)
    {
        return brewQualityRecordMapper.selectBrewQualityRecordList(brewQualityRecord);
    }

    @Autowired
    private com.jiuchang.system.mapper.BrewQualityStandardMapper brewQualityStandardMapper;

    /**
     * 计算并填充质检相关指标
     */
    private void calculateQualityMetrics(BrewQualityRecord brewQualityRecord)
    {
        // 1. 获取对应的质检标准
        if (brewQualityRecord.getStandardId() != null) {
            com.jiuchang.system.domain.BrewQualityStandard standard = brewQualityStandardMapper.selectBrewQualityStandardByStandardId(brewQualityRecord.getStandardId());
            if (standard != null && brewQualityRecord.getAlcoholActual() != null) {
                // 计算与标准平均值的偏差
                java.math.BigDecimal targetAlcohol = standard.getAlcoholMin().add(standard.getAlcoholMax()).divide(new java.math.BigDecimal(2), 2, java.math.RoundingMode.HALF_UP);
                brewQualityRecord.setAlcoholError(brewQualityRecord.getAlcoholActual().subtract(targetAlcohol));
                
                // 自动判断合格状态
                boolean isAlcoholOk = brewQualityRecord.getAlcoholActual().compareTo(standard.getAlcoholMin()) >= 0 
                                   && brewQualityRecord.getAlcoholActual().compareTo(standard.getAlcoholMax()) <= 0;
                brewQualityRecord.setQualityResult(isAlcoholOk ? "1" : "2"); // 1-合格, 2-不合格
            }
        }

        // 2. 计算出酒率 (简化逻辑：使用 alcoholAfter / alcoholBefore 作为示意计算，实际应结合产量)
        if (brewQualityRecord.getAlcoholBefore() != null && brewQualityRecord.getAlcoholAfter() != null 
            && brewQualityRecord.getAlcoholBefore().doubleValue() > 0) {
            java.math.BigDecimal rate = brewQualityRecord.getAlcoholAfter()
                .divide(brewQualityRecord.getAlcoholBefore(), 4, java.math.RoundingMode.HALF_UP)
                .multiply(new java.math.BigDecimal(100));
            brewQualityRecord.setWineOutputRate(rate);
        }
    }

    /**
     * 新增质检记录
     */
    @Override
    public int insertBrewQualityRecord(BrewQualityRecord brewQualityRecord)
    {
        calculateQualityMetrics(brewQualityRecord);
        brewQualityRecord.setCreateTime(DateUtils.getNowDate());
        return brewQualityRecordMapper.insertBrewQualityRecord(brewQualityRecord);
    }

    /**
     * 修改质检记录
     */
    @Override
    public int updateBrewQualityRecord(BrewQualityRecord brewQualityRecord)
    {
        calculateQualityMetrics(brewQualityRecord);
        brewQualityRecord.setUpdateTime(DateUtils.getNowDate());
        return brewQualityRecordMapper.updateBrewQualityRecord(brewQualityRecord);
    }

    /**
     * ????质检记录
     * 
     * @param recordIds ?????质检记录??
     * @return ??
     */
    @Override
    public int deleteBrewQualityRecordByRecordIds(Long[] recordIds)
    {
        return brewQualityRecordMapper.deleteBrewQualityRecordByRecordIds(recordIds);
    }

    /**
     * ??质检记录??
     * 
     * @param recordId 质检记录??
     * @return ??
     */
    @Override
    public int deleteBrewQualityRecordByRecordId(Long recordId)
    {
        return brewQualityRecordMapper.deleteBrewQualityRecordByRecordId(recordId);
    }
}
