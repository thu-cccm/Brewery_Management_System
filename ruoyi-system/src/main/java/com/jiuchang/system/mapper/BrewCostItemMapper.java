package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewCostItem;

/**
 * ?本项Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewCostItemMapper 
{
    /**
     * ???本项
     * 
     * @param itemId ?本项??
     * @return ?本项
     */
    public BrewCostItem selectBrewCostItemByItemId(Long itemId);

    /**
     * ???本项??
     * 
     * @param brewCostItem ?本项
     * @return ?本项??
     */
    public List<BrewCostItem> selectBrewCostItemList(BrewCostItem brewCostItem);

    /**
     * ???本项
     * 
     * @param brewCostItem ?本项
     * @return ??
     */
    public int insertBrewCostItem(BrewCostItem brewCostItem);

    /**
     * ???本项
     * 
     * @param brewCostItem ?本项
     * @return ??
     */
    public int updateBrewCostItem(BrewCostItem brewCostItem);

    /**
     * ???本项
     * 
     * @param itemId ?本项??
     * @return ??
     */
    public int deleteBrewCostItemByItemId(Long itemId);

    /**
     * ?????本项
     * 
     * @param itemIds ???????????
     * @return ??
     */
    public int deleteBrewCostItemByItemIds(Long[] itemIds);
}
