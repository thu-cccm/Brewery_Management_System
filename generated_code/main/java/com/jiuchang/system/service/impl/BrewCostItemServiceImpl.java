package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewCostItemMapper;
import com.jiuchang.system.domain.BrewCostItem;
import com.jiuchang.system.service.IBrewCostItemService;

/**
 * æˆæœ¬é¡¹Service业务层处理
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewCostItemServiceImpl implements IBrewCostItemService 
{
    @Autowired
    private BrewCostItemMapper brewCostItemMapper;

    /**
     * 查询æˆæœ¬é¡¹
     * 
     * @param itemId æˆæœ¬é¡¹主键
     * @return æˆæœ¬é¡¹
     */
    @Override
    public BrewCostItem selectBrewCostItemByItemId(Long itemId)
    {
        return brewCostItemMapper.selectBrewCostItemByItemId(itemId);
    }

    /**
     * 查询æˆæœ¬é¡¹列表
     * 
     * @param brewCostItem æˆæœ¬é¡¹
     * @return æˆæœ¬é¡¹
     */
    @Override
    public List<BrewCostItem> selectBrewCostItemList(BrewCostItem brewCostItem)
    {
        return brewCostItemMapper.selectBrewCostItemList(brewCostItem);
    }

    /**
     * 新增æˆæœ¬é¡¹
     * 
     * @param brewCostItem æˆæœ¬é¡¹
     * @return 结果
     */
    @Override
    public int insertBrewCostItem(BrewCostItem brewCostItem)
    {
        brewCostItem.setCreateTime(DateUtils.getNowDate());
        return brewCostItemMapper.insertBrewCostItem(brewCostItem);
    }

    /**
     * 修改æˆæœ¬é¡¹
     * 
     * @param brewCostItem æˆæœ¬é¡¹
     * @return 结果
     */
    @Override
    public int updateBrewCostItem(BrewCostItem brewCostItem)
    {
        brewCostItem.setUpdateTime(DateUtils.getNowDate());
        return brewCostItemMapper.updateBrewCostItem(brewCostItem);
    }

    /**
     * 批量删除æˆæœ¬é¡¹
     * 
     * @param itemIds 需要删除的æˆæœ¬é¡¹主键
     * @return 结果
     */
    @Override
    public int deleteBrewCostItemByItemIds(Long[] itemIds)
    {
        return brewCostItemMapper.deleteBrewCostItemByItemIds(itemIds);
    }

    /**
     * 删除æˆæœ¬é¡¹信息
     * 
     * @param itemId æˆæœ¬é¡¹主键
     * @return 结果
     */
    @Override
    public int deleteBrewCostItemByItemId(Long itemId)
    {
        return brewCostItemMapper.deleteBrewCostItemByItemId(itemId);
    }
}
