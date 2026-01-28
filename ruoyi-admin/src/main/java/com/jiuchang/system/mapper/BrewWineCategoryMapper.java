package com.jiuchang.system.mapper;

import java.util.List;
import com.jiuchang.system.domain.BrewWineCategory;

/**
 * 酒??类Mapper??
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
public interface BrewWineCategoryMapper 
{
    /**
     * ??酒??类
     * 
     * @param categoryId 酒??类??
     * @return 酒??类
     */
    public BrewWineCategory selectBrewWineCategoryByCategoryId(Long categoryId);

    /**
     * ??酒??类??
     * 
     * @param brewWineCategory 酒??类
     * @return 酒??类??
     */
    public List<BrewWineCategory> selectBrewWineCategoryList(BrewWineCategory brewWineCategory);

    /**
     * ??酒??类
     * 
     * @param brewWineCategory 酒??类
     * @return ??
     */
    public int insertBrewWineCategory(BrewWineCategory brewWineCategory);

    /**
     * ??酒??类
     * 
     * @param brewWineCategory 酒??类
     * @return ??
     */
    public int updateBrewWineCategory(BrewWineCategory brewWineCategory);

    /**
     * ??酒??类
     * 
     * @param categoryId 酒??类??
     * @return ??
     */
    public int deleteBrewWineCategoryByCategoryId(Long categoryId);

    /**
     * ????酒??类
     * 
     * @param categoryIds ???????????
     * @return ??
     */
    public int deleteBrewWineCategoryByCategoryIds(Long[] categoryIds);
}
