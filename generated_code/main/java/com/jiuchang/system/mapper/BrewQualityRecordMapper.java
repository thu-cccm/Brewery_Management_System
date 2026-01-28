package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityRecord;

/**
 * è´¨æ£€è®°å½•Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewQualityRecordMapper 
{
    /**
     * 查询è´¨æ£€è®°å½•
     * 
     * @param recordId è´¨æ£€è®°å½•主键
     * @return è´¨æ£€è®°å½•
     */
    public BrewQualityRecord selectBrewQualityRecordByRecordId(Long recordId);

    /**
     * 查询è´¨æ£€è®°å½•列表
     * 
     * @param brewQualityRecord è´¨æ£€è®°å½•
     * @return è´¨æ£€è®°å½•集合
     */
    public List<BrewQualityRecord> selectBrewQualityRecordList(BrewQualityRecord brewQualityRecord);

    /**
     * 新增è´¨æ£€è®°å½•
     * 
     * @param brewQualityRecord è´¨æ£€è®°å½•
     * @return 结果
     */
    public int insertBrewQualityRecord(BrewQualityRecord brewQualityRecord);

    /**
     * 修改è´¨æ£€è®°å½•
     * 
     * @param brewQualityRecord è´¨æ£€è®°å½•
     * @return 结果
     */
    public int updateBrewQualityRecord(BrewQualityRecord brewQualityRecord);

    /**
     * 删除è´¨æ£€è®°å½•
     * 
     * @param recordId è´¨æ£€è®°å½•主键
     * @return 结果
     */
    public int deleteBrewQualityRecordByRecordId(Long recordId);

    /**
     * 批量删除è´¨æ£€è®°å½•
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewQualityRecordByRecordIds(Long[] recordIds);
}
