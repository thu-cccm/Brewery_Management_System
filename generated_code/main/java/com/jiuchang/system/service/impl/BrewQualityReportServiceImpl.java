package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewQualityReportMapper;
import com.jiuchang.system.domain.BrewQualityReport;
import com.jiuchang.system.service.IBrewQualityReportService;

/**
 * è´¨æ£€æŠ¥å‘ŠService业务层处理
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewQualityReportServiceImpl implements IBrewQualityReportService 
{
    @Autowired
    private BrewQualityReportMapper brewQualityReportMapper;

    /**
     * 查询è´¨æ£€æŠ¥å‘Š
     * 
     * @param reportId è´¨æ£€æŠ¥å‘Š主键
     * @return è´¨æ£€æŠ¥å‘Š
     */
    @Override
    public BrewQualityReport selectBrewQualityReportByReportId(Long reportId)
    {
        return brewQualityReportMapper.selectBrewQualityReportByReportId(reportId);
    }

    /**
     * 查询è´¨æ£€æŠ¥å‘Š列表
     * 
     * @param brewQualityReport è´¨æ£€æŠ¥å‘Š
     * @return è´¨æ£€æŠ¥å‘Š
     */
    @Override
    public List<BrewQualityReport> selectBrewQualityReportList(BrewQualityReport brewQualityReport)
    {
        return brewQualityReportMapper.selectBrewQualityReportList(brewQualityReport);
    }

    /**
     * 新增è´¨æ£€æŠ¥å‘Š
     * 
     * @param brewQualityReport è´¨æ£€æŠ¥å‘Š
     * @return 结果
     */
    @Override
    public int insertBrewQualityReport(BrewQualityReport brewQualityReport)
    {
        brewQualityReport.setCreateTime(DateUtils.getNowDate());
        return brewQualityReportMapper.insertBrewQualityReport(brewQualityReport);
    }

    /**
     * 修改è´¨æ£€æŠ¥å‘Š
     * 
     * @param brewQualityReport è´¨æ£€æŠ¥å‘Š
     * @return 结果
     */
    @Override
    public int updateBrewQualityReport(BrewQualityReport brewQualityReport)
    {
        brewQualityReport.setUpdateTime(DateUtils.getNowDate());
        return brewQualityReportMapper.updateBrewQualityReport(brewQualityReport);
    }

    /**
     * 批量删除è´¨æ£€æŠ¥å‘Š
     * 
     * @param reportIds 需要删除的è´¨æ£€æŠ¥å‘Š主键
     * @return 结果
     */
    @Override
    public int deleteBrewQualityReportByReportIds(Long[] reportIds)
    {
        return brewQualityReportMapper.deleteBrewQualityReportByReportIds(reportIds);
    }

    /**
     * 删除è´¨æ£€æŠ¥å‘Š信息
     * 
     * @param reportId è´¨æ£€æŠ¥å‘Š主键
     * @return 结果
     */
    @Override
    public int deleteBrewQualityReportByReportId(Long reportId)
    {
        return brewQualityReportMapper.deleteBrewQualityReportByReportId(reportId);
    }
}
