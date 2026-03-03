-- =================================================================
-- 酒厂生产质量管理系统 - 演示数据种子脚本 (Seed Data)
-- 特点：包含完整业务链路、真实业务逻辑、近6个月时间跨度
-- =================================================================

-- 清理旧演示数据 (仅清理 brew_ 开头的业务表)
DELETE FROM brew_batch_cost;
DELETE FROM brew_cost_statistics;
DELETE FROM brew_quality_report;
DELETE FROM brew_quality_record;
DELETE FROM brew_production_process;
DELETE FROM brew_batch_material;
DELETE FROM brew_production_batch;
DELETE FROM brew_production_plan;
DELETE FROM brew_formula_detail;
DELETE FROM brew_formula_template;
DELETE FROM brew_quality_standard;
DELETE FROM brew_wine_category;
DELETE FROM brew_stock_record;
DELETE FROM brew_material;


INSERT INTO brew_material (material_id, material_code, material_name, material_type, material_unit, unit_price, stock_quantity, stock_warning_value, is_warning, create_by, create_time) VALUES
(1, 'MAT001', '优质高粱', '1', 'kg', 5.50, 5000.00, 1000.00, '0', 'admin', SYSDATE()),
(2, 'MAT002', '精选小麦', '1', 'kg', 3.20, 3000.00, 500.00, '0', 'admin', SYSDATE()),
(3, 'MAT003', '纯净酿造水', '2', 'L', 0.50, 10000.00, 2000.00, '0', 'admin', SYSDATE()),
(4, 'MAT004', '酒曲', '2', 'kg', 15.00, 200.00, 300.00, '1', 'admin', SYSDATE()), 
(5, 'MAT005', '大米', '1', 'kg', 4.50, 2000.00, 500.00, '0', 'admin', SYSDATE());

INSERT INTO brew_wine_category (category_id, category_code, category_name, alcohol_range_min, alcohol_range_max, fermentation_days_min, fermentation_days_max, optimal_temperature, optimal_humidity, create_by, create_time) VALUES
(1, 'BJ001', '浓香型白酒', 52.00, 55.00, 30, 45, 25.00, 80.00, 'admin', SYSDATE()),
(2, 'HJ001', '手工黄酒', 14.00, 18.00, 60, 90, 18.00, 75.00, 'admin', SYSDATE()),
(3, 'BR001', '精酿原浆啤酒', 3.50, 5.00, 14, 21, 12.00, 60.00, 'admin', SYSDATE());

INSERT INTO brew_quality_standard (standard_id, category_id, standard_name, alcohol_min, alcohol_max, alcohol_tolerance, is_default, status, create_by, create_time) VALUES
(1, 1, '浓香白酒标准-国标', 52.00, 55.00, 0.50, '1', '0', 'admin', SYSDATE()),
(2, 2, '黄酒标准-优等品', 14.00, 18.00, 0.30, '1', '0', 'admin', SYSDATE());

INSERT INTO brew_formula_template (template_id, category_id, template_name, base_output, expected_alcohol, status, create_by, create_time) VALUES
(1, 1, '传统浓香配方', 1000.00, 53.00, '0', 'admin', SYSDATE()),
(2, 2, '三年陈酿黄酒配方', 500.00, 16.00, '0', 'admin', SYSDATE());

INSERT INTO brew_formula_detail (template_id, material_id, material_ratio, base_quantity, is_main_material) VALUES
(1, 1, 0.60, 600.00, '1'), -- 高粱
(1, 2, 0.20, 200.00, '0'), -- 小麦
(1, 3, 1.20, 1200.00, '0'), -- 水
(2, 5, 0.80, 400.00, '1'), -- 大米
(2, 3, 1.50, 750.00, '0'); -- 水

INSERT INTO brew_production_plan (plan_id, plan_no, category_id, template_id, target_output, plan_start_date, plan_end_date, plan_status, create_by, create_time) VALUES
(1, 'PLAN20251101', 1, 1, 2000.00, '2025-11-01', '2025-12-15', '3', 'admin', '2025-11-01'),
(2, 'PLAN20251201', 2, 2, 1000.00, '2025-12-01', '2026-03-01', '3', 'admin', '2025-12-01'),
(3, 'PLAN20260115', 1, 1, 1500.00, '2026-01-15', '2026-02-28', '1', 'admin', '2026-01-15');



INSERT INTO brew_production_batch (batch_id, batch_no, plan_id, category_id, production_date, fermentation_start, fermentation_end, actual_output, batch_status, quality_status, responsible_person, create_time) VALUES
(1, 'BJ20251101001', 1, 1, '2025-11-01', '2025-11-02', '2025-12-05', 1980.00, '5', '1', '张工', '2025-11-01');

INSERT INTO brew_production_batch (batch_id, batch_no, plan_id, category_id, production_date, fermentation_start, fermentation_end, actual_output, batch_status, quality_status, responsible_person, create_time) VALUES
(2, 'BJ20251205001', 1, 1, '2025-12-05', '2025-12-06', '2026-01-10', 2050.00, '5', '1', '李工', '2025-12-05');

INSERT INTO brew_production_batch (batch_id, batch_no, plan_id, category_id, production_date, fermentation_start, fermentation_end, batch_status, quality_status, responsible_person, create_time) VALUES
(3, 'HJ20251201001', 2, 2, '2025-12-01', '2025-12-02', '2026-01-28', '2', '0', '王工', '2025-12-01');

INSERT INTO brew_production_batch (batch_id, batch_no, plan_id, category_id, production_date, fermentation_start, fermentation_end, batch_status, quality_status, responsible_person, create_time) VALUES
(4, 'BJ20260110001', 1, 1, '2026-01-10', '2026-01-11', '2026-02-15', '4', '0', '赵工', '2026-01-10');

INSERT INTO brew_production_process (batch_no, process_stage, process_time, avg_temperature, avg_humidity, operator_name, remark, create_time) VALUES
('HJ20251201001', '主发酵期', '2025-12-15 10:00:00', 28.50, 85.00, '王工', '【环境预警】温度偏差过大(28.50℃); 湿度偏差过大(85.00%); ', '2025-12-15'),
('BJ20251101001', '发酵初期', '2025-11-05 09:00:00', 24.80, 79.50, '张工', '一切正常', '2025-11-05');

INSERT INTO brew_quality_record (batch_no, standard_id, inspect_time, inspector_name, alcohol_before, alcohol_after, alcohol_actual, alcohol_error, wine_output_rate, quality_result, quality_score, create_time) VALUES
('BJ20251101001', 1, '2025-12-06', '刘质检', 58.00, 53.50, 53.20, -0.30, 49.50, '1', 92, '2025-12-06'),
('BJ20251205001', 1, '2026-01-11', '刘质检', 57.50, 54.00, 53.80, 0.30, 51.20, '1', 95, '2026-01-11');

INSERT INTO brew_quality_record (batch_no, standard_id, inspect_time, inspector_name, alcohol_before, alcohol_after, alcohol_actual, alcohol_error, wine_output_rate, quality_result, quality_score, unqualified_reason, create_time) VALUES
('TEST_BAD_BATCH', 1, SYSDATE(), '系统自动', 60.00, 45.00, 45.00, -8.00, 35.00, '2', 60, '酒精度严重偏低', SYSDATE());

INSERT INTO brew_cost_statistics (stat_period, total_cost, material_cost, energy_cost, unit_cost, create_time) VALUES
('202509', 150000.00, 120000.00, 20000.00, 45.50, '2025-09-30'),
('202510', 165000.00, 135000.00, 22000.00, 46.20, '2025-10-31'),
('202511', 180000.00, 150000.00, 21000.00, 44.80, '2025-11-30'),
('202512', 175000.00, 140000.00, 23000.00, 45.10, '2025-12-31'),
('202601', 190000.00, 160000.00, 20000.00, 43.50, '2026-01-25');

INSERT INTO brew_batch_material (batch_no, material_id, actual_quantity, create_time) VALUES
('BJ20251101001', 1, 1200.00, '2025-11-02'),
('BJ20251101001', 2, 400.00, '2025-11-02'),
('BJ20251101001', 3, 2400.00, '2025-11-02'),
('HJ20251201001', 5, 800.00, '2025-12-02');

INSERT INTO brew_batch_cost (batch_no, item_name, actual_amount, create_time) VALUES
('BJ20251101001', '高粱成本', 6600.00, '2025-12-05'),
('BJ20251101001', '人工成本', 2000.00, '2025-12-05'),
('BJ20251101001', '能源成本', 800.00, '2025-12-05');

COMMIT;
