package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaDetail;

/**
 * é…æ–¹æ˜Žç»†Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewFormulaDetailService 
{
    /**
     * 查询é…æ–¹æ˜Žç»†
     * 
     * @param detailId é…æ–¹æ˜Žç»†主键
     * @return é…æ–¹æ˜Žç»†
     */
    public BrewFormulaDetail selectBrewFormulaDetailByDetailId(Long detailId);

    /**
     * 查询é…æ–¹æ˜Žç»†列表
     * 
     * @param brewFormulaDetail é…æ–¹æ˜Žç»†
     * @return é…æ–¹æ˜Žç»†集合
     */
    public List<BrewFormulaDetail> selectBrewFormulaDetailList(BrewFormulaDetail brewFormulaDetail);

    /**
     * 新增é…æ–¹æ˜Žç»†
     * 
     * @param brewFormulaDetail é…æ–¹æ˜Žç»†
     * @return 结果
     */
    public int insertBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail);

    /**
     * 修改é…æ–¹æ˜Žç»†
     * 
     * @param brewFormulaDetail é…æ–¹æ˜Žç»†
     * @return 结果
     */
    public int updateBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail);

    /**
     * 批量删除é…æ–¹æ˜Žç»†
     * 
     * @param detailIds 需要删除的é…æ–¹æ˜Žç»†主键集合
     * @return 结果
     */
    public int deleteBrewFormulaDetailByDetailIds(Long[] detailIds);

    /**
     * 删除é…æ–¹æ˜Žç»†信息
     * 
     * @param detailId é…æ–¹æ˜Žç»†主键
     * @return 结果
     */
    public int deleteBrewFormulaDetailByDetailId(Long detailId);
}
