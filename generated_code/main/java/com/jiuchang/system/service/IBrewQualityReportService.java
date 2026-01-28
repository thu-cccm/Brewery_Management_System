package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityReport;

/**
 * è´¨æ£€æŠ¥å‘ŠService接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewQualityReportService 
{
    /**
     * 查询è´¨æ£€æŠ¥å‘Š
     * 
     * @param reportId è´¨æ£€æŠ¥å‘Š主键
     * @return è´¨æ£€æŠ¥å‘Š
     */
    public BrewQualityReport selectBrewQualityReportByReportId(Long reportId);

    /**
     * 查询è´¨æ£€æŠ¥å‘Š列表
     * 
     * @param brewQualityReport è´¨æ£€æŠ¥å‘Š
     * @return è´¨æ£€æŠ¥å‘Š集合
     */
    public List<BrewQualityReport> selectBrewQualityReportList(BrewQualityReport brewQualityReport);

    /**
     * 新增è´¨æ£€æŠ¥å‘Š
     * 
     * @param brewQualityReport è´¨æ£€æŠ¥å‘Š
     * @return 结果
     */
    public int insertBrewQualityReport(BrewQualityReport brewQualityReport);

    /**
     * 修改è´¨æ£€æŠ¥å‘Š
     * 
     * @param brewQualityReport è´¨æ£€æŠ¥å‘Š
     * @return 结果
     */
    public int updateBrewQualityReport(BrewQualityReport brewQualityReport);

    /**
     * 批量删除è´¨æ£€æŠ¥å‘Š
     * 
     * @param reportIds 需要删除的è´¨æ£€æŠ¥å‘Š主键集合
     * @return 结果
     */
    public int deleteBrewQualityReportByReportIds(Long[] reportIds);

    /**
     * 删除è´¨æ£€æŠ¥å‘Š信息
     * 
     * @param reportId è´¨æ£€æŠ¥å‘Š主键
     * @return 结果
     */
    public int deleteBrewQualityReportByReportId(Long reportId);
}
