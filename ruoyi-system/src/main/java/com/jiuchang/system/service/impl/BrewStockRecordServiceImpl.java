package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewStockRecordMapper;
import com.jiuchang.system.domain.BrewStockRecord;
import com.jiuchang.system.service.IBrewStockRecordService;

/**
 * 库存?动Service?????
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
     * ??库存?动
     * 
     * @param recordId 库存?动??
     * @return 库存?动
     */
    @Override
    public BrewStockRecord selectBrewStockRecordByRecordId(Long recordId)
    {
        return brewStockRecordMapper.selectBrewStockRecordByRecordId(recordId);
    }

    /**
     * ??库存?动??
     * 
     * @param brewStockRecord 库存?动
     * @return 库存?动
     */
    @Override
    public List<BrewStockRecord> selectBrewStockRecordList(BrewStockRecord brewStockRecord)
    {
        return brewStockRecordMapper.selectBrewStockRecordList(brewStockRecord);
    }

    /**
     * ??库存?动
     * 
     * @param brewStockRecord 库存?动
     * @return ??
     */
    @Override
    public int insertBrewStockRecord(BrewStockRecord brewStockRecord)
    {
        brewStockRecord.setCreateTime(DateUtils.getNowDate());
        return brewStockRecordMapper.insertBrewStockRecord(brewStockRecord);
    }

    /**
     * ??库存?动
     * 
     * @param brewStockRecord 库存?动
     * @return ??
     */
    @Override
    public int updateBrewStockRecord(BrewStockRecord brewStockRecord)
    {
        brewStockRecord.setUpdateTime(DateUtils.getNowDate());
        return brewStockRecordMapper.updateBrewStockRecord(brewStockRecord);
    }

    /**
     * ????库存?动
     * 
     * @param recordIds ?????库存?动??
     * @return ??
     */
    @Override
    public int deleteBrewStockRecordByRecordIds(Long[] recordIds)
    {
        return brewStockRecordMapper.deleteBrewStockRecordByRecordIds(recordIds);
    }

    /**
     * ??库存?动??
     * 
     * @param recordId 库存?动??
     * @return ??
     */
    @Override
    public int deleteBrewStockRecordByRecordId(Long recordId)
    {
        return brewStockRecordMapper.deleteBrewStockRecordByRecordId(recordId);
    }
}
