package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewQualityReport;

/**
 * è´¨æ£€æŠ¥å‘ŠMapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewQualityReportMapper 
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
     * 删除è´¨æ£€æŠ¥å‘Š
     * 
     * @param reportId è´¨æ£€æŠ¥å‘Š主键
     * @return 结果
     */
    public int deleteBrewQualityReportByReportId(Long reportId);

    /**
     * 批量删除è´¨æ£€æŠ¥å‘Š
     * 
     * @param reportIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewQualityReportByReportIds(Long[] reportIds);
}
