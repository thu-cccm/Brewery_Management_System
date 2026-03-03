package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaTemplate;

/**
 * ?方模?Service??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewFormulaTemplateService 
{
    /**
     * ???方模?
     * 
     * @param templateId ?方模???
     * @return ?方模?
     */
    public BrewFormulaTemplate selectBrewFormulaTemplateByTemplateId(Long templateId);

    /**
     * ???方模???
     * 
     * @param brewFormulaTemplate ?方模?
     * @return ?方模???
     */
    public List<BrewFormulaTemplate> selectBrewFormulaTemplateList(BrewFormulaTemplate brewFormulaTemplate);

    /**
     * ???方模?
     * 
     * @param brewFormulaTemplate ?方模?
     * @return ??
     */
    public int insertBrewFormulaTemplate(BrewFormulaTemplate brewFormulaTemplate);

    /**
     * ???方模?
     * 
     * @param brewFormulaTemplate ?方模?
     * @return ??
     */
    public int updateBrewFormulaTemplate(BrewFormulaTemplate brewFormulaTemplate);

    /**
     * ?????方模?
     * 
     * @param templateIds ??????方模?????
     * @return ??
     */
    public int deleteBrewFormulaTemplateByTemplateIds(Long[] templateIds);

    /**
     * ???方模???
     * 
     * @param templateId ?方模???
     * @return ??
     */
    public int deleteBrewFormulaTemplateByTemplateId(Long templateId);
}
