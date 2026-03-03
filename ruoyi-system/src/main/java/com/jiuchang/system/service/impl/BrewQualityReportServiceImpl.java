package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewQualityReportMapper;
import com.jiuchang.system.domain.BrewQualityReport;
import com.jiuchang.system.service.IBrewQualityReportService;

/**
 * 质检报告Service?????
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
     * ??质检报告
     * 
     * @param reportId 质检报告??
     * @return 质检报告
     */
    @Override
    public BrewQualityReport selectBrewQualityReportByReportId(Long reportId)
    {
        return brewQualityReportMapper.selectBrewQualityReportByReportId(reportId);
    }

    /**
     * ??质检报告??
     * 
     * @param brewQualityReport 质检报告
     * @return 质检报告
     */
    @Override
    public List<BrewQualityReport> selectBrewQualityReportList(BrewQualityReport brewQualityReport)
    {
        return brewQualityReportMapper.selectBrewQualityReportList(brewQualityReport);
    }

    /**
     * ??质检报告
     * 
     * @param brewQualityReport 质检报告
     * @return ??
     */
    @Override
    public int insertBrewQualityReport(BrewQualityReport brewQualityReport)
    {
        brewQualityReport.setCreateTime(DateUtils.getNowDate());
        return brewQualityReportMapper.insertBrewQualityReport(brewQualityReport);
    }

    /**
     * ??质检报告
     * 
     * @param brewQualityReport 质检报告
     * @return ??
     */
    @Override
    public int updateBrewQualityReport(BrewQualityReport brewQualityReport)
    {
        brewQualityReport.setUpdateTime(DateUtils.getNowDate());
        return brewQualityReportMapper.updateBrewQualityReport(brewQualityReport);
    }

    /**
     * ????质检报告
     * 
     * @param reportIds ?????质检报告??
     * @return ??
     */
    @Override
    public int deleteBrewQualityReportByReportIds(Long[] reportIds)
    {
        return brewQualityReportMapper.deleteBrewQualityReportByReportIds(reportIds);
    }

    /**
     * ??质检报告??
     * 
     * @param reportId 质检报告??
     * @return ??
     */
    @Override
    public int deleteBrewQualityReportByReportId(Long reportId)
    {
        return brewQualityReportMapper.deleteBrewQualityReportByReportId(reportId);
    }
}
