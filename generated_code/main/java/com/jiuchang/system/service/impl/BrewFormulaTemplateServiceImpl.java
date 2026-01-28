package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.jiuchang.system.domain.BrewFormulaDetail;
import com.jiuchang.system.mapper.BrewFormulaTemplateMapper;
import com.jiuchang.system.domain.BrewFormulaTemplate;
import com.jiuchang.system.service.IBrewFormulaTemplateService;

/**
 * é…æ–¹æ¨¡æ¿Service业务层处理
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
     * 查询é…æ–¹æ¨¡æ¿
     * 
     * @param templateId é…æ–¹æ¨¡æ¿主键
     * @return é…æ–¹æ¨¡æ¿
     */
    @Override
    public BrewFormulaTemplate selectBrewFormulaTemplateByTemplateId(Long templateId)
    {
        return brewFormulaTemplateMapper.selectBrewFormulaTemplateByTemplateId(templateId);
    }

    /**
     * 查询é…æ–¹æ¨¡æ¿列表
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿
     * @return é…æ–¹æ¨¡æ¿
     */
    @Override
    public List<BrewFormulaTemplate> selectBrewFormulaTemplateList(BrewFormulaTemplate brewFormulaTemplate)
    {
        return brewFormulaTemplateMapper.selectBrewFormulaTemplateList(brewFormulaTemplate);
    }

    /**
     * 新增é…æ–¹æ¨¡æ¿
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿
     * @return 结果
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
     * 修改é…æ–¹æ¨¡æ¿
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿
     * @return 结果
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
     * 批量删除é…æ–¹æ¨¡æ¿
     * 
     * @param templateIds 需要删除的é…æ–¹æ¨¡æ¿主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBrewFormulaTemplateByTemplateIds(Long[] templateIds)
    {
        brewFormulaTemplateMapper.deleteBrewFormulaDetailByTemplateIds(templateIds);
        return brewFormulaTemplateMapper.deleteBrewFormulaTemplateByTemplateIds(templateIds);
    }

    /**
     * 删除é…æ–¹æ¨¡æ¿信息
     * 
     * @param templateId é…æ–¹æ¨¡æ¿主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBrewFormulaTemplateByTemplateId(Long templateId)
    {
        brewFormulaTemplateMapper.deleteBrewFormulaDetailByTemplateId(templateId);
        return brewFormulaTemplateMapper.deleteBrewFormulaTemplateByTemplateId(templateId);
    }

    /**
     * 新增é…æ–¹æ˜Žç»†信息
     * 
     * @param brewFormulaTemplate é…æ–¹æ¨¡æ¿对象
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
