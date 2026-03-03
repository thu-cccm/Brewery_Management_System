package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaDetail;

/**
 * ?方明细Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewFormulaDetailService 
{
    /**
     * ???方明细
     * 
     * @param detailId ?方明细??
     * @return ?方明细
     */
    public BrewFormulaDetail selectBrewFormulaDetailByDetailId(Long detailId);

    /**
     * ???方明细??
     * 
     * @param brewFormulaDetail ?方明细
     * @return ?方明细??
     */
    public List<BrewFormulaDetail> selectBrewFormulaDetailList(BrewFormulaDetail brewFormulaDetail);

    /**
     * ???方明细
     * 
     * @param brewFormulaDetail ?方明细
     * @return ??
     */
    public int insertBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail);

    /**
     * ???方明细
     * 
     * @param brewFormulaDetail ?方明细
     * @return ??
     */
    public int updateBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail);

    /**
     * ?????方明细
     * 
     * @param detailIds ??????方明细????
     * @return ??
     */
    public int deleteBrewFormulaDetailByDetailIds(Long[] detailIds);

    /**
     * ???方明细??
     * 
     * @param detailId ?方明细??
     * @return ??
     */
    public int deleteBrewFormulaDetailByDetailId(Long detailId);
}
