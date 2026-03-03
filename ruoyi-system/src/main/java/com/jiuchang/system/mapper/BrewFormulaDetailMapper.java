package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaDetail;

/**
 * ?方明细Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewFormulaDetailMapper 
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
     * ???方明细
     * 
     * @param detailId ?方明细??
     * @return ??
     */
    public int deleteBrewFormulaDetailByDetailId(Long detailId);

    /**
     * ?????方明细
     * 
     * @param detailIds ???????????
     * @return ??
     */
    public int deleteBrewFormulaDetailByDetailIds(Long[] detailIds);
}
