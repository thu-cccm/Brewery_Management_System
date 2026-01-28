package com.jiuchang.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuchang.system.mapper.BrewWineCategoryMapper;
import com.jiuchang.system.domain.BrewWineCategory;
import com.jiuchang.system.service.IBrewWineCategoryService;

/**
 * é…’å“ç§ç±»Service业务层处理
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
     * 查询é…’å“ç§ç±»
     * 
     * @param categoryId é…’å“ç§ç±»主键
     * @return é…’å“ç§ç±»
     */
    @Override
    public BrewWineCategory selectBrewWineCategoryByCategoryId(Long categoryId)
    {
        return brewWineCategoryMapper.selectBrewWineCategoryByCategoryId(categoryId);
    }

    /**
     * 查询é…’å“ç§ç±»列表
     * 
     * @param brewWineCategory é…’å“ç§ç±»
     * @return é…’å“ç§ç±»
     */
    @Override
    public List<BrewWineCategory> selectBrewWineCategoryList(BrewWineCategory brewWineCategory)
    {
        return brewWineCategoryMapper.selectBrewWineCategoryList(brewWineCategory);
    }

    /**
     * 新增é…’å“ç§ç±»
     * 
     * @param brewWineCategory é…’å“ç§ç±»
     * @return 结果
     */
    @Override
    public int insertBrewWineCategory(BrewWineCategory brewWineCategory)
    {
        brewWineCategory.setCreateTime(DateUtils.getNowDate());
        return brewWineCategoryMapper.insertBrewWineCategory(brewWineCategory);
    }

    /**
     * 修改é…’å“ç§ç±»
     * 
     * @param brewWineCategory é…’å“ç§ç±»
     * @return 结果
     */
    @Override
    public int updateBrewWineCategory(BrewWineCategory brewWineCategory)
    {
        brewWineCategory.setUpdateTime(DateUtils.getNowDate());
        return brewWineCategoryMapper.updateBrewWineCategory(brewWineCategory);
    }

    /**
     * 批量删除é…’å“ç§ç±»
     * 
     * @param categoryIds 需要删除的é…’å“ç§ç±»主键
     * @return 结果
     */
    @Override
    public int deleteBrewWineCategoryByCategoryIds(Long[] categoryIds)
    {
        return brewWineCategoryMapper.deleteBrewWineCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除é…’å“ç§ç±»信息
     * 
     * @param categoryId é…’å“ç§ç±»主键
     * @return 结果
     */
    @Override
    public int deleteBrewWineCategoryByCategoryId(Long categoryId)
    {
        return brewWineCategoryMapper.deleteBrewWineCategoryByCategoryId(categoryId);
    }
}
