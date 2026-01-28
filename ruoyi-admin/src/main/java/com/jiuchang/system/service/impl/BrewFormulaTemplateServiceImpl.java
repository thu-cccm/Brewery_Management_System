package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.jiuchang.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.jiuchang.system.domain.BrewFormulaDetail;
import com.jiuchang.system.mapper.BrewFormulaTemplateMapper;
import com.jiuchang.system.domain.BrewFormulaTemplate;
import com.jiuchang.system.service.IBrewFormulaTemplateService;

/**
 * ?方模?Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewFormulaTemplateServiceImpl implements IBrewFormulaTemplateService 
{
    @Autowired
    private BrewFormulaTemplateMapper brewFormulaTemplateMapper;

    /**
     * ???方模?
     * 
     * @param templateId ?方模???
     * @return ?方模?
     */
    @Override
    public BrewFormulaTemplate selectBrewFormulaTemplateByTemplateId(Long templateId)
    {
        return brewFormulaTemplateMapper.selectBrewFormulaTemplateByTemplateId(templateId);
    }

    /**
     * ???方模???
     * 
     * @param brewFormulaTemplate ?方模?
     * @return ?方模?
     */
    @Override
    public List<BrewFormulaTemplate> selectBrewFormulaTemplateList(BrewFormulaTemplate brewFormulaTemplate)
    {
        return brewFormulaTemplateMapper.selectBrewFormulaTemplateList(brewFormulaTemplate);
    }

    /**
     * ???方模?
     * 
     * @param brewFormulaTemplate ?方模?
     * @return ??
     */
    @Transactional
    @Override
    public int insertBrewFormulaTemplate(BrewFormulaTemplate brewFormulaTemplate)
    {
        brewFormulaTemplate.setCreateTime(DateUtils.getNowDate());
        int rows = brewFormulaTemplateMapper.insertBrewFormulaTemplate(brewFormulaTemplate);
        insertBrewFormulaDetail(brewFormulaTemplate);
        return rows;
    }

    /**
     * ???方模?
     * 
     * @param brewFormulaTemplate ?方模?
     * @return ??
     */
    @Transactional
    @Override
    public int updateBrewFormulaTemplate(BrewFormulaTemplate brewFormulaTemplate)
    {
        brewFormulaTemplate.setUpdateTime(DateUtils.getNowDate());
        brewFormulaTemplateMapper.deleteBrewFormulaDetailByTemplateId(brewFormulaTemplate.getTemplateId());
        insertBrewFormulaDetail(brewFormulaTemplate);
        return brewFormulaTemplateMapper.updateBrewFormulaTemplate(brewFormulaTemplate);
    }

    /**
     * ?????方模?
     * 
     * @param templateIds ??????方模???
     * @return ??
     */
    @Transactional
    @Override
    public int deleteBrewFormulaTemplateByTemplateIds(Long[] templateIds)
    {
        brewFormulaTemplateMapper.deleteBrewFormulaDetailByTemplateIds(templateIds);
        return brewFormulaTemplateMapper.deleteBrewFormulaTemplateByTemplateIds(templateIds);
    }

    /**
     * ???方模???
     * 
     * @param templateId ?方模???
     * @return ??
     */
    @Transactional
    @Override
    public int deleteBrewFormulaTemplateByTemplateId(Long templateId)
    {
        brewFormulaTemplateMapper.deleteBrewFormulaDetailByTemplateId(templateId);
        return brewFormulaTemplateMapper.deleteBrewFormulaTemplateByTemplateId(templateId);
    }

    /**
     * ???方明细??
     * 
     * @param brewFormulaTemplate ?方模???
     */
    public void insertBrewFormulaDetail(BrewFormulaTemplate brewFormulaTemplate)
    {
        List<BrewFormulaDetail> brewFormulaDetailList = brewFormulaTemplate.getBrewFormulaDetailList();
        Long templateId = brewFormulaTemplate.getTemplateId();
        if (StringUtils.isNotNull(brewFormulaDetailList))
        {
            List<BrewFormulaDetail> list = new ArrayList<BrewFormulaDetail>();
            for (BrewFormulaDetail brewFormulaDetail : brewFormulaDetailList)
            {
                brewFormulaDetail.setTemplateId(templateId);
                list.add(brewFormulaDetail);
            }
            if (list.size() > 0)
            {
                brewFormulaTemplateMapper.batchBrewFormulaDetail(list);
            }
        }
    }
}
