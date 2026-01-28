package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityReport;

/**
 * 质检报告Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewQualityReportMapper 
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
     * ??质检报告
     * 
     * @param reportId 质检报告??
     * @return ??
     */
    public int deleteBrewQualityReportByReportId(Long reportId);

    /**
     * ????质检报告
     * 
     * @param reportIds ???????????
     * @return ??
     */
    public int deleteBrewQualityReportByReportIds(Long[] reportIds);
}
