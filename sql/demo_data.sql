-- ===================================================================
-- 演示数据脚本 (Demo Data)
-- 用于展示全链路追溯、决策看板等功能
-- ===================================================================

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1. 基础数据：酒品分类
-- ----------------------------
TRUNCATE TABLE brew_wine_category;
INSERT INTO brew_wine_category (category_id, category_code, category_name, category_type, create_by, create_time) VALUES
(100, 'WHEAT', '德式小麦啤', '1', 'admin', NOW()),
(101, 'IPA', '美式IPA', '1', 'admin', NOW()),
(102, 'STOUT', '世涛黑啤', '1', 'admin', NOW()),
(103, 'SOUR', '水果酸啤', '1', 'admin', NOW());

-- ----------------------------
-- 2. 基础数据：原料
-- ----------------------------
TRUNCATE TABLE brew_material;
INSERT INTO brew_material (material_id, material_code, material_name, material_type, material_unit, stock_quantity, create_by, create_time) VALUES
(100, 'M001', '澳洲大麦芽', '1', 'kg', 5000.00, 'admin', NOW()),
(101, 'M002', '焦香麦芽', '1', 'kg', 1000.00, 'admin', NOW()),
(102, 'H001', '萨兹酒花', '2', 'kg', 200.00, 'admin', NOW()),
(103, 'H002', '卡斯卡特酒花', '2', 'kg', 150.00, 'admin', NOW()),
(104, 'Y001', 'WB-06酵母', '3', 'kg', 50.00, 'admin', NOW()),
(105, 'Y002', 'US-05酵母', '3', 'kg', 50.00, 'admin', NOW());

-- ----------------------------
-- 3. 基础数据：设备
-- ----------------------------
TRUNCATE TABLE brew_equipment;
INSERT INTO brew_equipment (equipment_id, equipment_name, equipment_code, equipment_type, status, capacity, location, create_by, create_time) VALUES
(100, '1# 糖化锅', 'MASH-001', '1', '1', 2000.00, '车间A区', 'admin', NOW()),
(101, '2# 过滤槽', 'FILT-001', '2', '0', 2000.00, '车间A区', 'admin', NOW()),
(102, '1# 发酵罐', 'FERM-001', '0', '1', 2000.00, '车间B区', 'admin', NOW()),
(103, '2# 发酵罐', 'FERM-002', '0', '0', 2000.00, '车间B区', 'admin', NOW()),
(104, '3# 发酵罐', 'FERM-003', '0', '2', 2000.00, '车间B区', 'admin', NOW()),
(105, '1# 灌装机', 'PACK-001', '3', '0', 0.00, '车间C区', 'admin', NOW());

-- ----------------------------
-- 4. 基础数据：配方模板
-- ----------------------------
TRUNCATE TABLE brew_formula_template;
INSERT INTO brew_formula_template (template_id, template_code, template_name, category_id, base_output, expected_alcohol, status, create_by, create_time) VALUES
(100, 'FT-WHEAT-01', '经典德式小麦配方', 100, 2000.00, 4.8, '1', 'admin', NOW()),
(101, 'FT-IPA-02', '西海岸IPA配方', 101, 2000.00, 6.5, '1', 'admin', NOW());

-- ----------------------------
-- 5. 基础数据：工艺标准 (关联配方100)
-- ----------------------------
TRUNCATE TABLE brew_technology_standard;
INSERT INTO brew_technology_standard (formula_id, stage_key, param_name, param_code, standard_value, min_value, max_value, unit, order_num) VALUES
(100, 'mashing', '糖化温度', 'temp', '65', 63, 67, '℃', 1),
(100, 'mashing', '糖化时间', 'time', '60', 55, 65, 'min', 2),
(100, 'boiling', '煮沸时间', 'time', '90', 85, 95, 'min', 3),
(100, 'fermentation', '主发酵温度', 'temp', '20', 18, 22, '℃', 4),
(100, 'fermentation', '发酵压力', 'pressure', '0.1', 0.08, 0.12, 'MPa', 5);

-- ----------------------------
-- 6. 业务数据：生产计划
-- ----------------------------
TRUNCATE TABLE brew_production_plan;
INSERT INTO brew_production_plan (plan_id, plan_code, plan_name, category_id, template_id, target_output, plan_start_date, plan_end_date, plan_status, responsible_person, create_time) VALUES
(100, 'PLAN20231001', '十月首批德式小麦', 100, 100, 2000.00, DATE_SUB(NOW(), INTERVAL 30 DAY), DATE_SUB(NOW(), INTERVAL 15 DAY), '3', '张三', NOW()),
(101, 'PLAN20231015', '十月中旬IPA', 101, 101, 2000.00, DATE_SUB(NOW(), INTERVAL 15 DAY), NOW(), '3', '李四', NOW()),
(102, 'PLAN20231101', '十一月新品尝鲜', 102, 100, 1000.00, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), '2', '王五', NOW());

-- ----------------------------
-- 7. 业务数据：生产批次 (重要！关联全链路)
-- ----------------------------
TRUNCATE TABLE brew_production_batch;
INSERT INTO brew_production_batch (batch_id, batch_no, plan_id, category_id, template_id, actual_output, production_date, fermentation_start, fermentation_end, fermentation_days, batch_status, quality_status, responsible_person, create_time) VALUES
-- 批次1：已完成，合格 (Golden Batch)
(100, 'JC20231001001', 100, 100, 100, 1950.00, DATE_SUB(NOW(), INTERVAL 30 DAY), DATE_SUB(NOW(), INTERVAL 29 DAY), DATE_SUB(NOW(), INTERVAL 15 DAY), 14, '5', '1', '张三', NOW()),
-- 批次2：发酵中 (Active Batch)
(101, 'JC20231015002', 101, 101, 101, null, DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_SUB(NOW(), INTERVAL 9 DAY), null, 9, '2', '0', '李四', NOW()),
-- 批次3：已完成，异常 (Problem Batch)
(102, 'JC20230901003', 100, 100, 100, 1800.00, DATE_SUB(NOW(), INTERVAL 60 DAY), DATE_SUB(NOW(), INTERVAL 59 DAY), DATE_SUB(NOW(), INTERVAL 45 DAY), 14, '6', '2', '赵六', NOW());

-- ----------------------------
-- 8. 业务数据：过程监控 (为批次1和2生成数据)
-- ----------------------------
TRUNCATE TABLE brew_process_monitor;
INSERT INTO brew_process_monitor (batch_id, stage_key, record_time, temperature, pressure, sugar_degree, ph_value, alcohol, operator, is_abnormal, create_time) VALUES
-- 批次1：正常数据
(100, 'mashing', DATE_SUB(NOW(), INTERVAL 30 DAY), 65.5, 0.0, 12.0, 5.2, 0.0, '操作员A', '0', NOW()),
(100, 'boiling', DATE_SUB(NOW(), INTERVAL 30 DAY), 100.0, 0.0, 12.5, 5.1, 0.0, '操作员A', '0', NOW()),
(100, 'fermentation', DATE_SUB(NOW(), INTERVAL 25 DAY), 20.0, 0.1, 8.0, 4.5, 2.5, '操作员B', '0', NOW()),
(100, 'fermentation', DATE_SUB(NOW(), INTERVAL 20 DAY), 19.5, 0.1, 4.0, 4.2, 4.0, '操作员B', '0', NOW()),
(100, 'fermentation', DATE_SUB(NOW(), INTERVAL 15 DAY), 19.0, 0.1, 2.5, 4.1, 4.8, '操作员B', '0', NOW()),

-- 批次2：发酵中数据
(101, 'mashing', DATE_SUB(NOW(), INTERVAL 10 DAY), 66.0, 0.0, 13.0, 5.3, 0.0, '操作员C', '0', NOW()),
(101, 'fermentation', DATE_SUB(NOW(), INTERVAL 5 DAY), 21.0, 0.12, 10.0, 4.8, 1.5, '操作员C', '0', NOW()),
(101, 'fermentation', NOW(), 20.5, 0.11, 7.0, 4.6, 3.0, '操作员C', '0', NOW()),

-- 批次3：异常数据 (温度过高)
(102, 'fermentation', DATE_SUB(NOW(), INTERVAL 50 DAY), 28.0, 0.15, 9.0, 4.0, 2.0, '操作员D', '1', NOW());

-- ----------------------------
-- 9. 业务数据：质检记录
-- ----------------------------
TRUNCATE TABLE brew_quality_record;
INSERT INTO brew_quality_record (batch_no, standard_id, inspect_time, inspector_name, alcohol_actual, color_result, smell_result, taste_result, quality_result, quality_score, create_time) VALUES
('JC20231001001', 100, DATE_SUB(NOW(), INTERVAL 14 DAY), '质检员X', 4.8, '金黄透亮', '浓郁麦香', '口感醇厚', '1', 95.0, NOW()),
('JC20230901003', 100, DATE_SUB(NOW(), INTERVAL 44 DAY), '质检员Y', 4.2, '略浑浊', '有酸味', '口感偏酸', '2', 55.0, NOW());

-- ----------------------------
-- 10. 业务数据：损耗分析
-- ----------------------------
TRUNCATE TABLE brew_batch_yield;
INSERT INTO brew_batch_yield (batch_id, stage_key, input_qty, output_qty, loss_qty, loss_rate, unit, is_abnormal, create_time) VALUES
-- 批次1：正常损耗
(100, 'mashing', 500.00, 2000.00, 0.00, 0.00, 'L', '0', NOW()), -- 麦芽转麦汁，单位不同暂不计损耗率
(100, 'fermentation', 2000.00, 1950.00, 50.00, 2.50, 'L', '0', NOW()), -- 发酵损耗 2.5%

-- 批次3：高损耗
(102, 'fermentation', 2000.00, 1800.00, 200.00, 10.00, 'L', '1', NOW()); -- 发酵损耗 10% (异常)

SET FOREIGN_KEY_CHECKS = 1;
