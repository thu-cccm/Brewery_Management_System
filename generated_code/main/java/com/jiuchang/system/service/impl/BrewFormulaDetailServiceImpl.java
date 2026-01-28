package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewFormulaDetailMapper;
import com.jiuchang.system.domain.BrewFormulaDetail;
import com.jiuchang.system.service.IBrewFormulaDetailService;

/**
 * é…æ–¹æ˜Žç»†Service业务层处理
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
     * 查询é…æ–¹æ˜Žç»†
     * 
     * @param detailId é…æ–¹æ˜Žç»†主键
     * @return é…æ–¹æ˜Žç»†
     */
    @Override
    public BrewFormulaDetail selectBrewFormulaDetailByDetailId(Long detailId)
    {
        return brewFormulaDetailMapper.selectBrewFormulaDetailByDetailId(detailId);
    }

    /**
     * 查询é…æ–¹æ˜Žç»†列表
     * 
     * @param brewFormulaDetail é…æ–¹æ˜Žç»†
     * @return é…æ–¹æ˜Žç»†
     */
    @Override
    public List<BrewFormulaDetail> selectBrewFormulaDetailList(BrewFormulaDetail brewFormulaDetail)
    {
        return brewFormulaDetailMapper.selectBrewFormulaDetailList(brewFormulaDetail);
    }

    /**
     * 新增é…æ–¹æ˜Žç»†
     * 
     * @param brewFormulaDetail é…æ–¹æ˜Žç»†
     * @return 结果
     */
    @Override
    public int insertBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail)
    {
        brewFormulaDetail.setCreateTime(DateUtils.getNowDate());
        return brewFormulaDetailMapper.insertBrewFormulaDetail(brewFormulaDetail);
    }

    /**
     * 修改é…æ–¹æ˜Žç»†
     * 
     * @param brewFormulaDetail é…æ–¹æ˜Žç»†
     * @return 结果
     */
    @Override
    public int updateBrewFormulaDetail(BrewFormulaDetail brewFormulaDetail)
    {
        brewFormulaDetail.setUpdateTime(DateUtils.getNowDate());
        return brewFormulaDetailMapper.updateBrewFormulaDetail(brewFormulaDetail);
    }

    /**
     * 批量删除é…æ–¹æ˜Žç»†
     * 
     * @param detailIds 需要删除的é…æ–¹æ˜Žç»†主键
     * @return 结果
     */
    @Override
    public int deleteBrewFormulaDetailByDetailIds(Long[] detailIds)
    {
        return brewFormulaDetailMapper.deleteBrewFormulaDetailByDetailIds(detailIds);
    }

    /**
     * 删除é…æ–¹æ˜Žç»†信息
     * 
     * @param detailId é…æ–¹æ˜Žç»†主键
     * @return 结果
     */
    @Override
    public int deleteBrewFormulaDetailByDetailId(Long detailId)
    {
        return brewFormulaDetailMapper.deleteBrewFormulaDetailByDetailId(detailId);
    }
}
