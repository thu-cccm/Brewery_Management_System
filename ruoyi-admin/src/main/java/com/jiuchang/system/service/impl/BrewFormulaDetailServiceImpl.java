package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewFormulaDetailMapper;
import com.jiuchang.system.domain.BrewFormulaDetail;
import com.jiuchang.system.service.IBrewFormulaDetailService;

/**
 * ?方明细Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewFormulaDetailServiceImpl implements IBrewFormulaDetailService 
{
    @Autowired
    private BrewFormulaDetailMapper brewFormulaDetailMapper;

    /**
     * ???方明细
     * 
     * @param detailId ?方明细??
     * @return ?方明细
     */
    @Override
    public BrewFormulaDetail selectBrewFormulaDetailByDetailId(Long detailId)
    {
        return brewFormulaDetailMapper.selectBrewFormulaDetailByDetailId(detailId);
    }

    /**
     * ???方明细??
     * 
     * @param brewFormulaDetail ?方明细
     * @return ?方明细
     */
    @Override
    public List<BrewFormulaDetail> selectBrewFormulaDetailList(BrewFormulaDetail brewFormulaDetail)
    {
        return brewFormulaDetailMapper.selectBrewFormulaDetailList(brewFormulaDetail);
    }

    /**
     * ???方明细
     * 
     * @param brewFormulaDetail ?方明细
     * @return ??
     */
    @Override
    public int insertBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail)
    {
        brewFormulaDetail.setCreateTime(DateUtils.getNowDate());
        return brewFormulaDetailMapper.insertBrewFormulaDetail(brewFormulaDetail);
    }

    /**
     * ???方明细
     * 
     * @param brewFormulaDetail ?方明细
     * @return ??
     */
    @Override
    public int updateBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail)
    {
        brewFormulaDetail.setUpdateTime(DateUtils.getNowDate());
        return brewFormulaDetailMapper.updateBrewFormulaDetail(brewFormulaDetail);
    }

    /**
     * ?????方明细
     * 
     * @param detailIds ??????方明细??
     * @return ??
     */
    @Override
    public int deleteBrewFormulaDetailByDetailIds(Long[] detailIds)
    {
        return brewFormulaDetailMapper.deleteBrewFormulaDetailByDetailIds(detailIds);
    }

    /**
     * ???方明细??
     * 
     * @param detailId ?方明细??
     * @return ??
     */
    @Override
    public int deleteBrewFormulaDetailByDetailId(Long detailId)
    {
        return brewFormulaDetailMapper.deleteBrewFormulaDetailByDetailId(detailId);
    }
}
