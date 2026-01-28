package com.jiuchang.system.service.impl;

import java.util.List;
import com.jiuchang.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewWineCategoryMapper;
import com.jiuchang.system.domain.BrewWineCategory;
import com.jiuchang.system.service.IBrewWineCategoryService;

/**
 * 酒??类Service?????
 * 
 * @author jiuchang
 * @date 2026-01-26
 */
@Service
public class BrewWineCategoryServiceImpl implements IBrewWineCategoryService 
{
    @Autowired
    private BrewWineCategoryMapper brewWineCategoryMapper;

    /**
     * ??酒??类
     * 
     * @param categoryId 酒??类??
     * @return 酒??类
     */
    @Override
    public BrewWineCategory selectBrewWineCategoryByCategoryId(Long categoryId)
    {
        return brewWineCategoryMapper.selectBrewWineCategoryByCategoryId(categoryId);
    }

    /**
     * ??酒??类??
     * 
     * @param brewWineCategory 酒??类
     * @return 酒??类
     */
    @Override
    public List<BrewWineCategory> selectBrewWineCategoryList(BrewWineCategory brewWineCategory)
    {
        return brewWineCategoryMapper.selectBrewWineCategoryList(brewWineCategory);
    }

    /**
     * ??酒??类
     * 
     * @param brewWineCategory 酒??类
     * @return ??
     */
    @Override
    public int insertBrewWineCategory(BrewWineCategory brewWineCategory)
    {
        brewWineCategory.setCreateTime(DateUtils.getNowDate());
        return brewWineCategoryMapper.insertBrewWineCategory(brewWineCategory);
    }

    /**
     * ??酒??类
     * 
     * @param brewWineCategory 酒??类
     * @return ??
     */
    @Override
    public int updateBrewWineCategory(BrewWineCategory brewWineCategory)
    {
        brewWineCategory.setUpdateTime(DateUtils.getNowDate());
        return brewWineCategoryMapper.updateBrewWineCategory(brewWineCategory);
    }

    /**
     * ????酒??类
     * 
     * @param categoryIds ?????酒??类??
     * @return ??
     */
    @Override
    public int deleteBrewWineCategoryByCategoryIds(Long[] categoryIds)
    {
        return brewWineCategoryMapper.deleteBrewWineCategoryByCategoryIds(categoryIds);
    }

    /**
     * ??酒??类??
     * 
     * @param categoryId 酒??类??
     * @return ??
     */
    @Override
    public int deleteBrewWineCategoryByCategoryId(Long categoryId)
    {
        return brewWineCategoryMapper.deleteBrewWineCategoryByCategoryId(categoryId);
    }
}
