package com.jiuchang.system.service;

import java.util.List;
import com.jiuchang.system.domain.BrewCostItem;

/**
 * æˆæœ¬é¡¹Service接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface IBrewCostItemService 
{
    /**
     * 查询æˆæœ¬é¡¹
     * 
     * @param itemId æˆæœ¬é¡¹主键
     * @return æˆæœ¬é¡¹
     */
    public BrewCostItem selectBrewCostItemByItemId(Long itemId);

    /**
     * 查询æˆæœ¬é¡¹列表
     * 
     * @param brewCostItem æˆæœ¬é¡¹
     * @return æˆæœ¬é¡¹集合
     */
    public List<BrewCostItem> selectBrewCostItemList(BrewCostItem brewCostItem);

    /**
     * 新增æˆæœ¬é¡¹
     * 
     * @param brewCostItem æˆæœ¬é¡¹
     * @return 结果
     */
    public int insertBrewCostItem(BrewCostItem brewCostItem);

    /**
     * 修改æˆæœ¬é¡¹
     * 
     * @param brewCostItem æˆæœ¬é¡¹
     * @return 结果
     */
    public int updateBrewCostItem(BrewCostItem brewCostItem);

    /**
     * 批量删除æˆæœ¬é¡¹
     * 
     * @param itemIds 需要删除的æˆæœ¬é¡¹主键集合
     * @return 结果
     */
    public int deleteBrewCostItemByItemIds(Long[] itemIds);

    /**
     * 删除æˆæœ¬é¡¹信息
     * 
     * @param itemId æˆæœ¬é¡¹主键
     * @return 结果
     */
    public int deleteBrewCostItemByItemId(Long itemId);
}
