package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaTemplate;

/**
 * é…æ–¹æ¨¡æ¿Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewFormulaTemplateService 
{
    /**
     * 查询é…æ–¹æ¨¡æ¿
     * 
     * @param templateId é…æ–¹æ¨¡æ¿主键
     * @return é…æ–¹æ¨¡æ¿
     */
    public BrewFormulaTemplate selectBrewFormulaTemplateByTemplateId(Long templateId);

    /**
     * 查询é…æ–¹æ¨¡æ¿列表
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿
     * @return é…æ–¹æ¨¡æ¿集合
     */
    public List<BrewFormulaTemplate> selectBrewFormulaTemplateList(BrewFormulaTemplate brewFormulaTemplate);

    /**
     * 新增é…æ–¹æ¨¡æ¿
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿
     * @return 结果
     */
    public int insertBrewFormulaTemplate(BrewFormulaTemplate brewFormulaTemplate);

    /**
     * 修改é…æ–¹æ¨¡æ¿
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿
     * @return 结果
     */
    public int updateBrewFormulaTemplate(BrewFormulaTemplate brewFormulaTemplate);

    /**
     * 批量删除é…æ–¹æ¨¡æ¿
     * 
     * @param templateIds 需要删除的é…æ–¹æ¨¡æ¿主键集合
     * @return 结果
     */
    public int deleteBrewFormulaTemplateByTemplateIds(Long[] templateIds);

    /**
     * 删除é…æ–¹æ¨¡æ¿信息
     * 
     * @param templateId é…æ–¹æ¨¡æ¿主键
     * @return 结果
     */
    public int deleteBrewFormulaTemplateByTemplateId(Long templateId);
}
