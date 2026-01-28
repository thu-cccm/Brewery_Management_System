package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.jiuchang.system.domain.BrewBatchMaterial;
import com.jiuchang.system.mapper.BrewProductionBatchMapper;
import com.jiuchang.system.domain.BrewProductionBatch;
import com.jiuchang.system.service.IBrewProductionBatchService;

/**
 * ç”Ÿäº§æ‰¹æ¬¡Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewProductionBatchServiceImpl implements IBrewProductionBatchService 
{
    @Autowired
    private BrewProductionBatchMapper brewProductionBatchMapper;

    /**
     * 查询ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param batchId ç”Ÿäº§æ‰¹æ¬¡主键
     * @return ç”Ÿäº§æ‰¹æ¬¡
     */
    @Override
    public BrewProductionBatch selectBrewProductionBatchByBatchId(Long batchId)
    {
        return brewProductionBatchMapper.selectBrewProductionBatchByBatchId(batchId);
    }

    /**
     * 查询ç”Ÿäº§æ‰¹æ¬¡列表
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡
     * @return ç”Ÿäº§æ‰¹æ¬¡
     */
    @Override
    public List<BrewProductionBatch> selectBrewProductionBatchList(BrewProductionBatch brewProductionBatch)
    {
        return brewProductionBatchMapper.selectBrewProductionBatchList(brewProductionBatch);
    }

    /**
     * 新增ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBrewProductionBatch(BrewProductionBatch brewProductionBatch)
    {
        brewProductionBatch.setCreateTime(DateUtils.getNowDate());
        int rows = brewProductionBatchMapper.insertBrewProductionBatch(brewProductionBatch);
        insertBrewBatchMaterial(brewProductionBatch);
        return rows;
    }

    /**
     * 修改ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBrewProductionBatch(BrewProductionBatch brewProductionBatch)
    {
        brewProductionBatch.setUpdateTime(DateUtils.getNowDate());
        brewProductionBatchMapper.deleteBrewBatchMaterialByBatchNo(brewProductionBatch.getBatchId());
        insertBrewBatchMaterial(brewProductionBatch);
        return brewProductionBatchMapper.updateBrewProductionBatch(brewProductionBatch);
    }

    /**
     * 批量删除ç”Ÿäº§æ‰¹æ¬¡
     * 
     * @param batchIds 需要删除的ç”Ÿäº§æ‰¹æ¬¡主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBrewProductionBatchByBatchIds(Long[] batchIds)
    {
        brewProductionBatchMapper.deleteBrewBatchMaterialByBatchNos(batchIds);
        return brewProductionBatchMapper.deleteBrewProductionBatchByBatchIds(batchIds);
    }

    /**
     * 删除ç”Ÿäº§æ‰¹æ¬¡信息
     * 
     * @param batchId ç”Ÿäº§æ‰¹æ¬¡主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBrewProductionBatchByBatchId(Long batchId)
    {
        brewProductionBatchMapper.deleteBrewBatchMaterialByBatchNo(batchId);
        return brewProductionBatchMapper.deleteBrewProductionBatchByBatchId(batchId);
    }

    /**
     * 新增æ‰¹æ¬¡åŽŸæ–™信息
     * 
     * @param brewProductionBatch ç”Ÿäº§æ‰¹æ¬¡对象
     */
    public void insertBrewBatchMaterial(BrewProductionBatch brewProductionBatch)
    {
        List<BrewBatchMaterial> brewBatchMaterialList = brewProductionBatch.getBrewBatchMaterialList();
        Long batchId = brewProductionBatch.getBatchId();
        if (StringUtils.isNotNull(brewBatchMaterialList))
        {
            List<BrewBatchMaterial> list = new ArrayList<BrewBatchMaterial>();
            for (BrewBatchMaterial brewBatchMaterial : brewBatchMaterialList)
            {
                brewBatchMaterial.setBatchNo(batchId);
                list.add(brewBatchMaterial);
            }
            if (list.size() > 0)
            {
                brewProductionBatchMapper.batchBrewBatchMaterial(list);
            }
        }
    }
}
