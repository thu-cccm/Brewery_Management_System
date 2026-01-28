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

    /**
     * ??质检记录
     * 
     * @param brewQualityRecord 质检记录
     * @return ??
     */
    @Override
    public int insertBrewQualityRecord(BrewQualityRecord brewQualityRecord)
    {
        brewQualityRecord.setCreateTime(DateUtils.getNowDate());
        return brewQualityRecordMapper.insertBrewQualityRecord(brewQualityRecord);
    }

    /**
     * ??质检记录
     * 
     * @param brewQualityRecord 质检记录
     * @return ??
     */
    @Override
    public int updateBrewQualityRecord(BrewQualityRecord brewQualityRecord)
    {
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
