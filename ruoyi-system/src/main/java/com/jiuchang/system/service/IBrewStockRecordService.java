package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewStockRecord;

/**
 * 库存?动Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewStockRecordService 
{
    /**
     * ??库存?动
     * 
     * @param recordId 库存?动??
     * @return 库存?动
     */
    public BrewStockRecord selectBrewStockRecordByRecordId(Long recordId);

    /**
     * ??库存?动??
     * 
     * @param brewStockRecord 库存?动
     * @return 库存?动??
     */
    public List<BrewStockRecord> selectBrewStockRecordList(BrewStockRecord brewStockRecord);

    /**
     * ??库存?动
     * 
     * @param brewStockRecord 库存?动
     * @return ??
     */
    public int insertBrewStockRecord(BrewStockRecord brewStockRecord);

    /**
     * ??库存?动
     * 
     * @param brewStockRecord 库存?动
     * @return ??
     */
    public int updateBrewStockRecord(BrewStockRecord brewStockRecord);

    /**
     * ????库存?动
     * 
     * @param recordIds ?????库存?动????
     * @return ??
     */
    public int deleteBrewStockRecordByRecordIds(Long[] recordIds);

    /**
     * ??库存?动??
     * 
     * @param recordId 库存?动??
     * @return ??
     */
    public int deleteBrewStockRecordByRecordId(Long recordId);
}
