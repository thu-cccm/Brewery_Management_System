package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewWineCategory;

/**
 * é…’å“ç§ç±»Mapper接口
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewWineCategoryMapper 
{
    /**
     * 查询é…’å“ç§ç±»
     * 
     * @param categoryId é…’å“ç§ç±»主键
     * @return é…’å“ç§ç±»
     */
    public BrewWineCategory selectBrewWineCategoryByCategoryId(Long categoryId);

    /**
     * 查询é…’å“ç§ç±»列表
     * 
     * @param brewWineCategory é…’å“ç§ç±»
     * @return é…’å“ç§ç±»集合
     */
    public List<BrewWineCategory> selectBrewWineCategoryList(BrewWineCategory brewWineCategory);

    /**
     * 新增é…’å“ç§ç±»
     * 
     * @param brewWineCategory é…’å“ç§ç±»
     * @return 结果
     */
    public int insertBrewWineCategory(BrewWineCategory brewWineCategory);

    /**
     * 修改é…’å“ç§ç±»
     * 
     * @param brewWineCategory é…’å“ç§ç±»
     * @return 结果
     */
    public int updateBrewWineCategory(BrewWineCategory brewWineCategory);

    /**
     * 删除é…’å“ç§ç±»
     * 
     * @param categoryId é…’å“ç§ç±»主键
     * @return 结果
     */
    public int deleteBrewWineCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除é…’å“ç§ç±»
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrewWineCategoryByCategoryIds(Long[] categoryIds);
}
