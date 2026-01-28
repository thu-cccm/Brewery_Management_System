package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityReport;

/**
 * 质检报告Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewQualityReportService 
{
    /**
     * ??质检报告
     * 
     * @param reportId 质检报告??
     * @return 质检报告
     */
    public BrewQualityReport selectBrewQualityReportByReportId(Long reportId);

    /**
     * ??质检报告??
     * 
     * @param brewQualityReport 质检报告
     * @return 质检报告??
     */
    public List<BrewQualityReport> selectBrewQualityReportList(BrewQualityReport brewQualityReport);

    /**
     * ??质检报告
     * 
     * @param brewQualityReport 质检报告
     * @return ??
     */
    public int insertBrewQualityReport(BrewQualityReport brewQualityReport);

    /**
     * ??质检报告
     * 
     * @param brewQualityReport 质检报告
     * @return ??
     */
    public int updateBrewQualityReport(BrewQualityReport brewQualityReport);

    /**
     * ????质检报告
     * 
     * @param reportIds ?????质检报告????
     * @return ??
     */
    public int deleteBrewQualityReportByReportIds(Long[] reportIds);

    /**
     * ??质检报告??
     * 
     * @param reportId 质检报告??
     * @return ??
     */
    public int deleteBrewQualityReportByReportId(Long reportId);
}
