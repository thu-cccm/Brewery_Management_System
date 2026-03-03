package com.jiuchang.system.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.jiuchang.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.jiuchang.common.exception.ServiceException;
import com.jiuchang.system.domain.BrewBatchMaterial;
import com.jiuchang.system.domain.BrewMaterial;
import com.jiuchang.system.domain.BrewProductionPlan;
import com.jiuchang.system.domain.BrewStockRecord;
import com.jiuchang.system.mapper.BrewProductionBatchMapper;
import com.jiuchang.system.mapper.BrewMaterialMapper;
import com.jiuchang.system.mapper.BrewProductionPlanMapper;
import com.jiuchang.system.mapper.BrewStockRecordMapper;
import com.jiuchang.system.mapper.BrewStatisticsMapper;
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

    @Autowired
    private BrewMaterialMapper brewMaterialMapper;

    @Autowired
    private BrewProductionPlanMapper brewProductionPlanMapper;

    @Autowired
    private BrewStockRecordMapper brewStockRecordMapper;

    @Autowired
    private BrewStatisticsMapper brewStatisticsMapper;

    @Autowired
    private com.jiuchang.system.mapper.BrewProductionProcessMapper brewProductionProcessMapper;

    @Autowired
    private com.jiuchang.system.mapper.BrewQualityRecordMapper brewQualityRecordMapper;

    @Autowired
    private com.jiuchang.system.mapper.BrewBatchCostMapper brewBatchCostMapper;

    /**
     * 获取批次全生命周期溯源数据
     */
    @Override
    public com.jiuchang.system.domain.vo.BatchTraceVO getTraceability(String batchNo)
    {
        com.jiuchang.system.domain.vo.BatchTraceVO trace = new com.jiuchang.system.domain.vo.BatchTraceVO();
        
        // 1. 获取批次基本信息
        BrewProductionBatch batch = brewProductionBatchMapper.selectBrewProductionBatchByBatchNo(batchNo);
        trace.setBatchInfo(batch);
        
        if (batch != null) {
            // 2. 获取领料记录
            BrewBatchMaterial bmSearch = new BrewBatchMaterial();
            bmSearch.setBatchNo(batchNo);
            trace.setMaterials(brewProductionBatchMapper.selectBrewBatchMaterialList(bmSearch));
            
            // 3. 获取生产过程记录
            com.jiuchang.system.domain.BrewProductionProcess processSearch = new com.jiuchang.system.domain.BrewProductionProcess();
            processSearch.setBatchNo(batchNo);
            trace.setProcesses(brewProductionProcessMapper.selectBrewProductionProcessList(processSearch));
            
            // 4. 获取质检记录
            com.jiuchang.system.domain.BrewQualityRecord qualitySearch = new com.jiuchang.system.domain.BrewQualityRecord();
            qualitySearch.setBatchNo(batchNo);
            trace.setQualityRecords(brewQualityRecordMapper.selectBrewQualityRecordList(qualitySearch));
            
            // 5. 获取成本明细
            com.jiuchang.system.domain.BrewBatchCost costSearch = new com.jiuchang.system.domain.BrewBatchCost();
            costSearch.setBatchNo(batchNo);
            trace.setCosts(brewBatchCostMapper.selectBrewBatchCostList(costSearch));
        }
        
        return trace;
    }

    /**
     * 查询生产批次
     * 
     * @param batchId 生产批次主键
     * @return 生产批次
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
            // 获取批次编号
            BrewProductionBatch batch = selectBrewProductionBatchByBatchId(batchId);
            for (BrewBatchMaterial brewBatchMaterial : brewBatchMaterialList)
            {
                brewBatchMaterial.setBatchNo(batch.getBatchNo());
                list.add(brewBatchMaterial);
            }
            if (list.size() > 0)
            {
                brewProductionBatchMapper.batchBrewBatchMaterial(list);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String startProduction(Long planId, List<Map<String, Object>> materialList, String operatorName)
    {
        if (planId == null || planId <= 0) {
            throw new ServiceException("生产计划ID不能为空且必须大于0");
        }
        
        if (materialList == null || materialList.isEmpty()) {
            throw new ServiceException("原料列表不能为空");
        }
        
        if (operatorName == null || operatorName.trim().isEmpty()) {
            throw new ServiceException("操作人不能为空");
        }
        
        BrewProductionPlan plan = brewProductionPlanMapper.selectBrewProductionPlanByPlanId(planId);
        if (plan == null) {
            throw new ServiceException("生产计划不存在");
        }
        
        if (!"2".equals(plan.getPlanStatus())) {
            throw new ServiceException("只有已审核状态的生产计划才能执行");
        }

        String datePrefix = "JC" + new SimpleDateFormat("yyyyMMdd").format(new Date());
        String sequence = brewStatisticsMapper.selectNextBatchSequence(datePrefix);
        String batchNo = datePrefix + sequence;

        BrewProductionBatch batch = new BrewProductionBatch();
        batch.setBatchNo(batchNo);
        batch.setPlanId(planId);
        batch.setCategoryId(plan.getCategoryId());
        batch.setTemplateId(plan.getTemplateId());
        batch.setProductionDate(new Date());
        batch.setResponsiblePerson(plan.getResponsiblePerson());
        batch.setBatchStatus("1");
        batch.setQualityStatus("0");
        batch.setCreateTime(DateUtils.getNowDate());
        
        int rows = brewProductionBatchMapper.insertBrewProductionBatch(batch);
        if (rows <= 0) {
            throw new ServiceException("创建生产批次失败");
        }

        if (materialList != null && !materialList.isEmpty()) {
            List<BrewBatchMaterial> batchMaterialList = new ArrayList<>();
            
            for (Map<String, Object> item : materialList) {
                if (item.get("materialId") == null || item.get("requiredQuantity") == null) {
                    throw new ServiceException("原料信息不完整，缺少materialId或requiredQuantity");
                }
                
                Long materialId;
                BigDecimal quantity;
                try {
                    materialId = Long.valueOf(item.get("materialId").toString());
                    quantity = new BigDecimal(item.get("requiredQuantity").toString());
                } catch (NumberFormatException e) {
                    throw new ServiceException("原料ID或数量格式错误");
                }
                
                if (materialId <= 0) {
                    throw new ServiceException("原料ID必须大于0");
                }
                
                if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ServiceException("原料数量必须大于0");
                }

                BrewMaterial material = brewMaterialMapper.selectBrewMaterialByMaterialId(materialId);
                if (material == null) {
                    throw new ServiceException("原料不存在: ID=" + materialId);
                }

                BigDecimal beforeQuantity = material.getStockQuantity();
                if (beforeQuantity == null) {
                    beforeQuantity = BigDecimal.ZERO;
                }
                
                if (beforeQuantity.compareTo(quantity) < 0) {
                    throw new ServiceException("原料库存不足: " + material.getMaterialName() + 
                            ", 需要: " + quantity + ", 当前库存: " + beforeQuantity);
                }

                int updateRows = brewStatisticsMapper.updateMaterialStock(materialId, quantity);
                if (updateRows <= 0) {
                    throw new ServiceException("扣减库存失败: " + material.getMaterialName() + 
                            ", 可能存在并发操作导致库存不足");
                }

                // 检查库存预警并发送通知
                BrewMaterial updatedMaterial = brewMaterialMapper.selectBrewMaterialByMaterialId(materialId);
                if (updatedMaterial != null && "1".equals(updatedMaterial.getIsWarning())) {
                    com.jiuchang.system.domain.SysNotice notice = new com.jiuchang.system.domain.SysNotice();
                    notice.setNoticeTitle("库存预警通知");
                    notice.setNoticeContent("原料【" + updatedMaterial.getMaterialName() + "】当前库存为 " + 
                            updatedMaterial.getStockQuantity() + updatedMaterial.getMaterialUnit() + 
                            "，已低于预警阈值 " + updatedMaterial.getStockWarningValue() + 
                            "，请及时采购补充。");
                    notice.setNoticeType("2"); // 公告
                    notice.setStatus("0"); // 正常
                    notice.setCreateBy("系统自动预警");
                    // 假设 noticeService 已经注入，或者手动获取
                    try {
                        com.jiuchang.system.service.ISysNoticeService noticeService = com.jiuchang.common.utils.spring.SpringUtils.getBean(com.jiuchang.system.service.ISysNoticeService.class);
                        noticeService.insertNotice(notice);
                    } catch (Exception e) {
                        // 记录日志但不中断业务
                        System.err.println("发送库存预警通知失败: " + e.getMessage());
                    }
                }

                BrewStockRecord stockRecord = new BrewStockRecord();
                stockRecord.setMaterialId(materialId);
                stockRecord.setChangeType("2");
                stockRecord.setChangeQuantity(quantity.negate());
                stockRecord.setBeforeQuantity(beforeQuantity);
                stockRecord.setAfterQuantity(beforeQuantity.subtract(quantity));
                stockRecord.setRelatedBatchNo(batchNo);
                stockRecord.setOperatorName(operatorName);
                stockRecord.setChangeReason("生产领料出库");
                stockRecord.setCreateTime(DateUtils.getNowDate());
                brewStockRecordMapper.insertBrewStockRecord(stockRecord);

                BrewBatchMaterial batchMaterial = new BrewBatchMaterial();
                batchMaterial.setBatchNo(batchNo);
                batchMaterial.setMaterialId(materialId);
                batchMaterial.setActualQuantity(quantity);
                batchMaterialList.add(batchMaterial);
            }

            if (!batchMaterialList.isEmpty()) {
                brewProductionBatchMapper.batchBrewBatchMaterial(batchMaterialList);
            }
        }

        plan.setPlanStatus("3");
        plan.setUpdateTime(DateUtils.getNowDate());
        brewProductionPlanMapper.updateBrewProductionPlan(plan);

        return batchNo;
    }
}
