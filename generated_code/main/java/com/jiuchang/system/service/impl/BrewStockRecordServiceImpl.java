package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewStockRecordMapper;
import com.jiuchang.system.domain.BrewStockRecord;
import com.jiuchang.system.service.IBrewStockRecordService;

/**
 * åº“å­˜å˜åŠ¨Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewStockRecordServiceImpl implements IBrewStockRecordService 
{
    @Autowired
    private BrewStockRecordMapper brewStockRecordMapper;

    /**
     * 查询åº“å­˜å˜åŠ¨
     * 
     * @param recordId åº“å­˜å˜åŠ¨主键
     * @return åº“å­˜å˜åŠ¨
     */
    @Override
    public BrewStockRecord selectBrewStockRecordByRecordId(Long recordId)
    {
        return brewStockRecordMapper.selectBrewStockRecordByRecordId(recordId);
    }

    /**
     * 查询åº“å­˜å˜åŠ¨列表
     * 
     * @param brewStockRecord åº“å­˜å˜åŠ¨
     * @return åº“å­˜å˜åŠ¨
     */
    @Override
    public List<BrewStockRecord> selectBrewStockRecordList(BrewStockRecord brewStockRecord)
    {
        return brewStockRecordMapper.selectBrewStockRecordList(brewStockRecord);
    }

    /**
     * 新增åº“å­˜å˜åŠ¨
     * 
     * @param brewStockRecord åº“å­˜å˜åŠ¨
     * @return 结果
     */
    @Override
    public int insertBrewStockRecord(BrewStockRecord brewStockRecord)
    {
        brewStockRecord.setCreateTime(DateUtils.getNowDate());
        return brewStockRecordMapper.insertBrewStockRecord(brewStockRecord);
    }

    /**
     * 修改åº“å­˜å˜åŠ¨
     * 
     * @param brewStockRecord åº“å­˜å˜åŠ¨
     * @return 结果
     */
    @Override
    public int updateBrewStockRecord(BrewStockRecord brewStockRecord)
    {
        brewStockRecord.setUpdateTime(DateUtils.getNowDate());
        return brewStockRecordMapper.updateBrewStockRecord(brewStockRecord);
    }

    /**
     * 批量删除åº“å­˜å˜åŠ¨
     * 
     * @param recordIds 需要删除的åº“å­˜å˜åŠ¨主键
     * @return 结果
     */
    @Override
    public int deleteBrewStockRecordByRecordIds(Long[] recordIds)
    {
        return brewStockRecordMapper.deleteBrewStockRecordByRecordIds(recordIds);
    }

    /**
     * 删除åº“å­˜å˜åŠ¨信息
     * 
     * @param recordId åº“å­˜å˜åŠ¨主键
     * @return 结果
     */
    @Override
    public int deleteBrewStockRecordByRecordId(Long recordId)
    {
        return brewStockRecordMapper.deleteBrewStockRecordByRecordId(recordId);
    }
}
