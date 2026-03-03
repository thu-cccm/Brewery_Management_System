package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaTemplate;
import com.jiuchang.system.domain.BrewFormulaDetail;

/**
 * ?方模?Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewFormulaTemplateMapper 
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
     * ???方模?
     * 
     * @param templateId ?方模???
     * @return ??
     */
    public int deleteBrewFormulaTemplateByTemplateId(Long templateId);

    /**
     * ?????方模?
     * 
     * @param templateIds ???????????
     * @return ??
     */
    public int deleteBrewFormulaTemplateByTemplateIds(Long[] templateIds);

    /**
     * ?????方明细
     * 
     * @param templateIds ???????????
     * @return ??
     */
    public int deleteBrewFormulaDetailByTemplateIds(Long[] templateIds);
    
    /**
     * ?????方明细
     * 
     * @param brewFormulaDetailList ?方明细??
     * @return ??
     */
    public int batchBrewFormulaDetail(List<BrewFormulaDetail> brewFormulaDetailList);
    

    /**
     * ???方模??????方明细??
     * 
     * @param templateId ?方模?ID
     * @return ??
     */
    public int deleteBrewFormulaDetailByTemplateId(Long templateId);
}
