package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewQualityRecordMapper;
import com.jiuchang.system.domain.BrewQualityRecord;
import com.jiuchang.system.service.IBrewQualityRecordService;

/**
 * è´¨æ£€è®°å½•Service业务层处理
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
     * 查询è´¨æ£€è®°å½•
     * 
     * @param recordId è´¨æ£€è®°å½•主键
     * @return è´¨æ£€è®°å½•
     */
    @Override
    public BrewQualityRecord selectBrewQualityRecordByRecordId(Long recordId)
    {
        return brewQualityRecordMapper.selectBrewQualityRecordByRecordId(recordId);
    }

    /**
     * 查询è´¨æ£€è®°å½•列表
     * 
     * @param brewQualityRecord è´¨æ£€è®°å½•
     * @return è´¨æ£€è®°å½•
     */
    @Override
    public List<BrewQualityRecord> selectBrewQualityRecordList(BrewQualityRecord brewQualityRecord)
    {
        return brewQualityRecordMapper.selectBrewQualityRecordList(brewQualityRecord);
    }

    /**
     * 新增è´¨æ£€è®°å½•
     * 
     * @param brewQualityRecord è´¨æ£€è®°å½•
     * @return 结果
     */
    @Override
    public int insertBrewQualityRecord(BrewQualityRecord brewQualityRecord)
    {
        brewQualityRecord.setCreateTime(DateUtils.getNowDate());
        return brewQualityRecordMapper.insertBrewQualityRecord(brewQualityRecord);
    }

    /**
     * 修改è´¨æ£€è®°å½•
     * 
     * @param brewQualityRecord è´¨æ£€è®°å½•
     * @return 结果
     */
    @Override
    public int updateBrewQualityRecord(BrewQualityRecord brewQualityRecord)
    {
        brewQualityRecord.setUpdateTime(DateUtils.getNowDate());
        return brewQualityRecordMapper.updateBrewQualityRecord(brewQualityRecord);
    }

    /**
     * 批量删除è´¨æ£€è®°å½•
     * 
     * @param recordIds 需要删除的è´¨æ£€è®°å½•主键
     * @return 结果
     */
    @Override
    public int deleteBrewQualityRecordByRecordIds(Long[] recordIds)
    {
        return brewQualityRecordMapper.deleteBrewQualityRecordByRecordIds(recordIds);
    }

    /**
     * 删除è´¨æ£€è®°å½•信息
     * 
     * @param recordId è´¨æ£€è®°å½•主键
     * @return 结果
     */
    @Override
    public int deleteBrewQualityRecordByRecordId(Long recordId)
    {
        return brewQualityRecordMapper.deleteBrewQualityRecordByRecordId(recordId);
    }
}
