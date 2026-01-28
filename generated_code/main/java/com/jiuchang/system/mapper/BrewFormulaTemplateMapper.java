package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewFormulaTemplate;
import com.jiuchang.system.domain.BrewFormulaDetail;

/**
 * é…æ–¹æ¨¡æ¿Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewFormulaTemplateMapper 
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
     * 删除é…æ–¹æ¨¡æ¿
     * 
     * @param templateId é…æ–¹æ¨¡æ¿主键
     * @return 结果
     */
    public int deleteBrewFormulaTemplateByTemplateId(Long templateId);

    /**
     * 批量删除é…æ–¹æ¨¡æ¿
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewFormulaTemplateByTemplateIds(Long[] templateIds);

    /**
     * 批量删除é…æ–¹æ˜Žç»†
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewFormulaDetailByTemplateIds(Long[] templateIds);
    
    /**
     * 批量新增é…æ–¹æ˜Žç»†
     * 
     * @param brewFormulaDetailList é…æ–¹æ˜Žç»†列表
     * @return 结果
     */
    public int batchBrewFormulaDetail(List<BrewFormulaDetail> brewFormulaDetailList);
    

    /**
     * 通过é…æ–¹æ¨¡æ¿主键删除é…æ–¹æ˜Žç»†信息
     * 
     * @param templateId é…æ–¹æ¨¡æ¿ID
     * @return 结果
     */
    public int deleteBrewFormulaDetailByTemplateId(Long templateId);
}
