package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityRecord;

/**
 * 质检记录Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewQualityRecordMapper 
{
    /**
     * ??质检记录
     * 
     * @param recordId 质检记录??
     * @return 质检记录
     */
    public BrewQualityRecord selectBrewQualityRecordByRecordId(Long recordId);

    /**
     * ??质检记录??
     * 
     * @param brewQualityRecord 质检记录
     * @return 质检记录??
     */
    public List<BrewQualityRecord> selectBrewQualityRecordList(BrewQualityRecord brewQualityRecord);

    /**
     * ??质检记录
     * 
     * @param brewQualityRecord 质检记录
     * @return ??
     */
    public int insertBrewQualityRecord(BrewQualityRecord brewQualityRecord);

    /**
     * ??质检记录
     * 
     * @param brewQualityRecord 质检记录
     * @return ??
     */
    public int updateBrewQualityRecord(BrewQualityRecord brewQualityRecord);

    /**
     * ??质检记录
     * 
     * @param recordId 质检记录??
     * @return ??
     */
    public int deleteBrewQualityRecordByRecordId(Long recordId);

    /**
     * ????质检记录
     * 
     * @param recordIds ???????????
     * @return ??
     */
    public int deleteBrewQualityRecordByRecordIds(Long[] recordIds);
}
