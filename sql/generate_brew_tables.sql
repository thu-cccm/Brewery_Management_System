-- ========================================
-- 酒厂系统代码生成配置
-- 直接插入到 gen_table 和 gen_table_column
-- ========================================

-- 清理可能存在的旧配置
DELETE FROM gen_table_column WHERE table_id IN (SELECT table_id FROM gen_table WHERE table_name LIKE 'brew_%');
DELETE FROM gen_table WHERE table_name LIKE 'brew_%';

-- 1. 原料信息表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_material', '原料信息表', NULL, NULL, 'BrewMaterial', 'crud', 'com.jiuchang.system', 'system', 'material', '原料信息', 'jiuchang', '0', '/', '{"parentMenuId":"2000"}', 'admin', SYSDATE(), '', NULL, '原料库存管理');

-- 2. 酒品种类表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_wine_category', '酒品种类表', NULL, NULL, 'BrewWineCategory', 'crud', 'com.jiuchang.system', 'system', 'category', '酒品种类', 'jiuchang', '0', '/', '{"parentMenuId":"2000"}', 'admin', SYSDATE(), '', NULL, '酒品种类管理');

-- 3. 配方模板表（主表）
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_formula_template', '配方模板表', 'brew_formula_detail', 'template_id', 'BrewFormulaTemplate', 'sub', 'com.jiuchang.system', 'system', 'template', '配方模板', 'jiuchang', '0', '/', '{"parentMenuId":"2000"}', 'admin', SYSDATE(), '', NULL, '配方模板管理（主子表）');

-- 4. 配方明细表（子表）
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_formula_detail', '配方明细表', NULL, NULL, 'BrewFormulaDetail', 'crud', 'com.jiuchang.system', 'system', 'detail', '配方明细', 'jiuchang', '0', '/', '{"parentMenuId":"2000"}', 'admin', SYSDATE(), '', NULL, '配方明细');

-- 5. 库存变动记录表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_stock_record', '库存变动记录表', NULL, NULL, 'BrewStockRecord', 'crud', 'com.jiuchang.system', 'system', 'stock', '库存变动', 'jiuchang', '0', '/', '{"parentMenuId":"2000"}', 'admin', SYSDATE(), '', NULL, '库存变动记录');

-- 6. 生产计划表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_production_plan', '生产计划表', NULL, NULL, 'BrewProductionPlan', 'crud', 'com.jiuchang.system', 'system', 'plan', '生产计划', 'jiuchang', '0', '/', '{"parentMenuId":"2001"}', 'admin', SYSDATE(), '', NULL, '生产计划管理');

-- 7. 生产批次表（核心追溯表）
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_production_batch', '生产批次表', 'brew_batch_material', 'batch_no', 'BrewProductionBatch', 'sub', 'com.jiuchang.system', 'system', 'batch', '生产批次', 'jiuchang', '0', '/', '{"parentMenuId":"2001"}', 'admin', SYSDATE(), '', NULL, '生产批次管理（核心追溯）');

-- 8. 批次原料使用记录表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_batch_material', '批次原料使用记录表', NULL, NULL, 'BrewBatchMaterial', 'crud', 'com.jiuchang.system', 'system', 'batchmaterial', '批次原料', 'jiuchang', '0', '/', '{"parentMenuId":"2001"}', 'admin', SYSDATE(), '', NULL, '批次原料使用记录');

-- 9. 生产过程记录表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_production_process', '生产过程记录表', NULL, NULL, 'BrewProductionProcess', 'crud', 'com.jiuchang.system', 'system', 'process', '生产过程', 'jiuchang', '0', '/', '{"parentMenuId":"2001"}', 'admin', SYSDATE(), '', NULL, '生产过程记录');

-- 10. 质检标准表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_quality_standard', '质检标准表', NULL, NULL, 'BrewQualityStandard', 'crud', 'com.jiuchang.system', 'system', 'standard', '质检标准', 'jiuchang', '0', '/', '{"parentMenuId":"2002"}', 'admin', SYSDATE(), '', NULL, '质检标准管理');

-- 11. 质检记录表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_quality_record', '质检记录表', NULL, NULL, 'BrewQualityRecord', 'crud', 'com.jiuchang.system', 'system', 'quality', '质检记录', 'jiuchang', '0', '/', '{"parentMenuId":"2002"}', 'admin', SYSDATE(), '', NULL, '质检记录管理');

-- 12. 质检报告表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_quality_report', '质检报告表', NULL, NULL, 'BrewQualityReport', 'crud', 'com.jiuchang.system', 'system', 'report', '质检报告', 'jiuchang', '0', '/', '{"parentMenuId":"2002"}', 'admin', SYSDATE(), '', NULL, '质检报告管理');

-- 13. 成本项配置表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_cost_item', '成本项配置表', NULL, NULL, 'BrewCostItem', 'crud', 'com.jiuchang.system', 'system', 'costitem', '成本项', 'jiuchang', '0', '/', '{"parentMenuId":"2003"}', 'admin', SYSDATE(), '', NULL, '成本项配置');

-- 14. 批次成本明细表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_batch_cost', '批次成本明细表', NULL, NULL, 'BrewBatchCost', 'crud', 'com.jiuchang.system', 'system', 'cost', '批次成本', 'jiuchang', '0', '/', '{"parentMenuId":"2003"}', 'admin', SYSDATE(), '', NULL, '批次成本明细');

-- 15. 成本统计表
INSERT INTO gen_table (table_name, table_comment, sub_table_name, sub_table_fk_name, class_name, tpl_category, package_name, module_name, business_name, function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark)
VALUES ('brew_cost_statistics', '成本统计表', NULL, NULL, 'BrewCostStatistics', 'crud', 'com.jiuchang.system', 'system', 'statistics', '成本统计', 'jiuchang', '0', '/', '{"parentMenuId":"2003"}', 'admin', SYSDATE(), '', NULL, '成本统计分析');

-- 提示：执行后需要在若依后台【系统工具】->【代码生成】中点击"同步"按钮，同步表字段信息
SELECT '代码生成配置已插入，共15张表' AS result;
SELECT table_name, function_name, tpl_category FROM gen_table WHERE table_name LIKE 'brew_%';

