package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewStockRecord;

/**
 * åº“å­˜å˜åŠ¨Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewStockRecordMapper 
{
    /**
     * 查询åº“å­˜å˜åŠ¨
     * 
     * @param recordId åº“å­˜å˜åŠ¨主键
     * @return åº“å­˜å˜åŠ¨
     */
    public BrewStockRecord selectBrewStockRecordByRecordId(Long recordId);

    /**
     * 查询åº“å­˜å˜åŠ¨列表
     * 
     * @param brewStockRecord åº“å­˜å˜åŠ¨
     * @return åº“å­˜å˜åŠ¨集合
     */
    public List<BrewStockRecord> selectBrewStockRecordList(BrewStockRecord brewStockRecord);

    /**
     * 新增åº“å­˜å˜åŠ¨
     * 
     * @param brewStockRecord åº“å­˜å˜åŠ¨
     * @return 结果
     */
    public int insertBrewStockRecord(BrewStockRecord brewStockRecord);

    /**
     * 修改åº“å­˜å˜åŠ¨
     * 
     * @param brewStockRecord åº“å­˜å˜åŠ¨
     * @return 结果
     */
    public int updateBrewStockRecord(BrewStockRecord brewStockRecord);

    /**
     * 删除åº“å­˜å˜åŠ¨
     * 
     * @param recordId åº“å­˜å˜åŠ¨主键
     * @return 结果
     */
    public int deleteBrewStockRecordByRecordId(Long recordId);

    /**
     * 批量删除åº“å­˜å˜åŠ¨
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewStockRecordByRecordIds(Long[] recordIds);
}
