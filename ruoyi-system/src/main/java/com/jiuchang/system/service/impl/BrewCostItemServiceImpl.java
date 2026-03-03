package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewCostItemMapper;
import com.jiuchang.system.domain.BrewCostItem;
import com.jiuchang.system.service.IBrewCostItemService;

/**
 * ?本项Service?????
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
     * ???本项
     * 
     * @param itemId ?本项??
     * @return ?本项
     */
    @Override
    public BrewCostItem selectBrewCostItemByItemId(Long itemId)
    {
        return brewCostItemMapper.selectBrewCostItemByItemId(itemId);
    }

    /**
     * ???本项??
     * 
     * @param brewCostItem ?本项
     * @return ?本项
     */
    @Override
    public List<BrewCostItem> selectBrewCostItemList(BrewCostItem brewCostItem)
    {
        return brewCostItemMapper.selectBrewCostItemList(brewCostItem);
    }

    /**
     * ???本项
     * 
     * @param brewCostItem ?本项
     * @return ??
     */
    @Override
    public int insertBrewCostItem(BrewCostItem brewCostItem)
    {
        brewCostItem.setCreateTime(DateUtils.getNowDate());
        return brewCostItemMapper.insertBrewCostItem(brewCostItem);
    }

    /**
     * ???本项
     * 
     * @param brewCostItem ?本项
     * @return ??
     */
    @Override
    public int updateBrewCostItem(BrewCostItem brewCostItem)
    {
        brewCostItem.setUpdateTime(DateUtils.getNowDate());
        return brewCostItemMapper.updateBrewCostItem(brewCostItem);
    }

    /**
     * ?????本项
     * 
     * @param itemIds ??????本项??
     * @return ??
     */
    @Override
    public int deleteBrewCostItemByItemIds(Long[] itemIds)
    {
        return brewCostItemMapper.deleteBrewCostItemByItemIds(itemIds);
    }

    /**
     * ???本项??
     * 
     * @param itemId ?本项??
     * @return ??
     */
    @Override
    public int deleteBrewCostItemByItemId(Long itemId)
    {
        return brewCostItemMapper.deleteBrewCostItemByItemId(itemId);
    }
}
