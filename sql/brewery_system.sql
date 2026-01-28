-- ========================================
-- 酒厂生产质量管理系统 - 数据库设计
-- ========================================

-- ----------------------------
-- 1. 基础数据中心
-- ----------------------------

-- 1.1 原料信息表
DROP TABLE IF EXISTS brew_material;
CREATE TABLE brew_material (
  material_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '原料ID',
  material_code         VARCHAR(50)     NOT NULL                   COMMENT '原料编码',
  material_name         VARCHAR(100)    NOT NULL                   COMMENT '原料名称',
  material_type         VARCHAR(20)     NOT NULL                   COMMENT '原料类型（1粮食类 2辅料类 3包装类）',
  material_unit         VARCHAR(20)     NOT NULL                   COMMENT '计量单位（kg/L/个）',
  material_spec         VARCHAR(200)    DEFAULT NULL               COMMENT '规格说明',
  unit_price            DECIMAL(10,2)   DEFAULT 0.00               COMMENT '单价（元）',
  supplier_name         VARCHAR(100)    DEFAULT NULL               COMMENT '供应商名称',
  supplier_contact      VARCHAR(50)     DEFAULT NULL               COMMENT '供应商联系方式',
  stock_quantity        DECIMAL(12,2)   DEFAULT 0.00               COMMENT '当前库存数量',
  stock_warning_value   DECIMAL(12,2)   DEFAULT 0.00               COMMENT '库存预警阈值',
  is_warning            CHAR(1)         DEFAULT '0'                COMMENT '是否预警（0否 1是）',
  status                CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (material_id),
  UNIQUE KEY uk_material_code (material_code),
  KEY idx_material_type (material_type),
  KEY idx_is_warning (is_warning)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '原料信息表';

-- 1.2 酒品种类表
DROP TABLE IF EXISTS brew_wine_category;
CREATE TABLE brew_wine_category (
  category_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '品类ID',
  category_code         VARCHAR(50)     NOT NULL                   COMMENT '品类编码',
  category_name         VARCHAR(100)    NOT NULL                   COMMENT '品类名称',
  category_type         VARCHAR(20)     NOT NULL                   COMMENT '酒类型（1白酒 2啤酒 3黄酒 4果酒）',
  alcohol_range_min     DECIMAL(5,2)    DEFAULT NULL               COMMENT '酒精度范围-最小值（%）',
  alcohol_range_max     DECIMAL(5,2)    DEFAULT NULL               COMMENT '酒精度范围-最大值（%）',
  fermentation_days_min INT(5)          DEFAULT NULL               COMMENT '发酵周期-最小天数',
  fermentation_days_max INT(5)          DEFAULT NULL               COMMENT '发酵周期-最大天数',
  optimal_temperature   DECIMAL(5,2)    DEFAULT NULL               COMMENT '最佳发酵温度（℃）',
  optimal_humidity      DECIMAL(5,2)    DEFAULT NULL               COMMENT '最佳发酵湿度（%）',
  description           VARCHAR(500)    DEFAULT NULL               COMMENT '品类描述',
  status                CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (category_id),
  UNIQUE KEY uk_category_code (category_code)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '酒品种类表';

-- 1.3 配方模板表
DROP TABLE IF EXISTS brew_formula_template;
CREATE TABLE brew_formula_template (
  template_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '模板ID',
  template_code         VARCHAR(50)     NOT NULL                   COMMENT '模板编码',
  template_name         VARCHAR(100)    NOT NULL                   COMMENT '模板名称',
  category_id           BIGINT(20)      NOT NULL                   COMMENT '酒品种类ID',
  base_output           DECIMAL(10,2)   NOT NULL                   COMMENT '基准产量（L）',
  expected_alcohol      DECIMAL(5,2)    DEFAULT NULL               COMMENT '预期酒精度（%）',
  formula_description   VARCHAR(500)    DEFAULT NULL               COMMENT '配方说明',
  is_default            CHAR(1)         DEFAULT '0'                COMMENT '是否默认配方（0否 1是）',
  status                CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (template_id),
  UNIQUE KEY uk_template_code (template_code),
  KEY idx_category_id (category_id)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '配方模板表';

-- 1.4 配方明细表
DROP TABLE IF EXISTS brew_formula_detail;
CREATE TABLE brew_formula_detail (
  detail_id             BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '明细ID',
  template_id           BIGINT(20)      NOT NULL                   COMMENT '模板ID',
  material_id           BIGINT(20)      NOT NULL                   COMMENT '原料ID',
  material_ratio        DECIMAL(10,4)   NOT NULL                   COMMENT '配比系数（原料用量/基准产量）',
  base_quantity         DECIMAL(10,2)   NOT NULL                   COMMENT '基准用量',
  is_main_material      CHAR(1)         DEFAULT '0'                COMMENT '是否主料（0否 1是）',
  sort_order            INT(4)          DEFAULT 0                  COMMENT '排序',
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (detail_id),
  KEY idx_template_id (template_id),
  KEY idx_material_id (material_id)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '配方明细表';

-- 1.5 库存变动记录表
DROP TABLE IF EXISTS brew_stock_record;
CREATE TABLE brew_stock_record (
  record_id             BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '记录ID',
  material_id           BIGINT(20)      NOT NULL                   COMMENT '原料ID',
  change_type           VARCHAR(20)     NOT NULL                   COMMENT '变动类型（1入库 2出库 3盘点 4报损）',
  change_quantity       DECIMAL(12,2)   NOT NULL                   COMMENT '变动数量（正数入库，负数出库）',
  before_quantity       DECIMAL(12,2)   NOT NULL                   COMMENT '变动前库存',
  after_quantity        DECIMAL(12,2)   NOT NULL                   COMMENT '变动后库存',
  related_batch_no      VARCHAR(50)     DEFAULT NULL               COMMENT '关联批次号（生产出库时填写）',
  operator_name         VARCHAR(64)     DEFAULT NULL               COMMENT '操作人',
  change_reason         VARCHAR(500)    DEFAULT NULL               COMMENT '变动原因',
  create_time           DATETIME                                   COMMENT '创建时间',
  PRIMARY KEY (record_id),
  KEY idx_material_id (material_id),
  KEY idx_related_batch (related_batch_no),
  KEY idx_create_time (create_time)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '库存变动记录表';

-- ----------------------------
-- 2. 生产管理模块
-- ----------------------------

-- 2.1 生产计划表
DROP TABLE IF EXISTS brew_production_plan;
CREATE TABLE brew_production_plan (
  plan_id               BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '计划ID',
  plan_code             VARCHAR(50)     NOT NULL                   COMMENT '计划编号',
  plan_name             VARCHAR(100)    NOT NULL                   COMMENT '计划名称',
  category_id           BIGINT(20)      NOT NULL                   COMMENT '酒品种类ID',
  template_id           BIGINT(20)      NOT NULL                   COMMENT '配方模板ID',
  target_output         DECIMAL(10,2)   NOT NULL                   COMMENT '目标产量（L）',
  plan_start_date       DATE            NOT NULL                   COMMENT '计划开始日期',
  plan_end_date         DATE            NOT NULL                   COMMENT '计划结束日期',
  responsible_person    VARCHAR(64)     DEFAULT NULL               COMMENT '负责人',
  plan_status           VARCHAR(20)     DEFAULT '1'                COMMENT '计划状态（1待审核 2已审核 3生产中 4已完成 5已取消）',
  approve_by            VARCHAR(64)     DEFAULT NULL               COMMENT '审核人',
  approve_time          DATETIME        DEFAULT NULL               COMMENT '审核时间',
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (plan_id),
  UNIQUE KEY uk_plan_code (plan_code),
  KEY idx_plan_status (plan_status),
  KEY idx_plan_date (plan_start_date, plan_end_date)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '生产计划表';

-- 2.2 生产批次表（核心追溯表）
DROP TABLE IF EXISTS brew_production_batch;
CREATE TABLE brew_production_batch (
  batch_id              BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '批次ID',
  batch_no              VARCHAR(50)     NOT NULL                   COMMENT '批次号（唯一追溯码）',
  plan_id               BIGINT(20)      NOT NULL                   COMMENT '生产计划ID',
  category_id           BIGINT(20)      NOT NULL                   COMMENT '酒品种类ID',
  template_id           BIGINT(20)      NOT NULL                   COMMENT '配方模板ID',
  actual_output         DECIMAL(10,2)   DEFAULT 0.00               COMMENT '实际产量（L）',
  production_date       DATE            NOT NULL                   COMMENT '生产日期',
  fermentation_start    DATETIME        DEFAULT NULL               COMMENT '发酵开始时间',
  fermentation_end      DATETIME        DEFAULT NULL               COMMENT '发酵结束时间',
  fermentation_days     INT(5)          DEFAULT NULL               COMMENT '实际发酵天数',
  environment_temp      DECIMAL(5,2)    DEFAULT NULL               COMMENT '环境温度（℃）',
  environment_humidity  DECIMAL(5,2)    DEFAULT NULL               COMMENT '环境湿度（%）',
  responsible_person    VARCHAR(64)     DEFAULT NULL               COMMENT '负责人',
  batch_status          VARCHAR(20)     DEFAULT '1'                COMMENT '批次状态（1备料中 2发酵中 3蒸馏中 4质检中 5已完成 6异常）',
  quality_status        VARCHAR(20)     DEFAULT '0'                COMMENT '质检状态（0未检 1合格 2不合格）',
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (batch_id),
  UNIQUE KEY uk_batch_no (batch_no),
  KEY idx_plan_id (plan_id),
  KEY idx_production_date (production_date),
  KEY idx_batch_status (batch_status)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '生产批次表';

-- 2.3 批次原料使用记录表（追溯链关键表）
DROP TABLE IF EXISTS brew_batch_material;
CREATE TABLE brew_batch_material (
  record_id             BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '记录ID',
  batch_no              VARCHAR(50)     NOT NULL                   COMMENT '批次号',
  material_id           BIGINT(20)      NOT NULL                   COMMENT '原料ID',
  material_batch_no     VARCHAR(50)     DEFAULT NULL               COMMENT '原料批次号（供应商提供）',
  planned_quantity      DECIMAL(10,2)   NOT NULL                   COMMENT '计划用量',
  actual_quantity       DECIMAL(10,2)   NOT NULL                   COMMENT '实际用量',
  material_cost         DECIMAL(10,2)   DEFAULT 0.00               COMMENT '原料成本（元）',
  use_time              DATETIME        DEFAULT NULL               COMMENT '使用时间',
  operator_name         VARCHAR(64)     DEFAULT NULL               COMMENT '操作人',
  PRIMARY KEY (record_id),
  KEY idx_batch_no (batch_no),
  KEY idx_material_id (material_id)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '批次原料使用记录表';

-- 2.4 生产过程记录表
DROP TABLE IF EXISTS brew_production_process;
CREATE TABLE brew_production_process (
  process_id            BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '记录ID',
  batch_no              VARCHAR(50)     NOT NULL                   COMMENT '批次号',
  process_stage         VARCHAR(20)     NOT NULL                   COMMENT '工序阶段（1备料 2发酵 3蒸馏 4灌装）',
  process_time          DATETIME        NOT NULL                   COMMENT '记录时间',
  temperature           DECIMAL(5,2)    DEFAULT NULL               COMMENT '温度（℃）',
  humidity              DECIMAL(5,2)    DEFAULT NULL               COMMENT '湿度（%）',
  process_data          VARCHAR(1000)   DEFAULT NULL               COMMENT '工序数据（JSON格式存储其他参数）',
  operator_name         VARCHAR(64)     DEFAULT NULL               COMMENT '操作人',
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_time           DATETIME                                   COMMENT '创建时间',
  PRIMARY KEY (process_id),
  KEY idx_batch_no (batch_no),
  KEY idx_process_stage (process_stage),
  KEY idx_process_time (process_time)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '生产过程记录表';

-- ----------------------------
-- 3. 质检管理模块
-- ----------------------------

-- 3.1 质检标准表
DROP TABLE IF EXISTS brew_quality_standard;
CREATE TABLE brew_quality_standard (
  standard_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '标准ID',
  category_id           BIGINT(20)      NOT NULL                   COMMENT '酒品种类ID',
  standard_name         VARCHAR(100)    NOT NULL                   COMMENT '标准名称',
  alcohol_min           DECIMAL(5,2)    DEFAULT NULL               COMMENT '酒精度最小值（%）',
  alcohol_max           DECIMAL(5,2)    DEFAULT NULL               COMMENT '酒精度最大值（%）',
  alcohol_tolerance     DECIMAL(5,2)    DEFAULT 1.00               COMMENT '酒精度允许误差（%）',
  color_standard        VARCHAR(100)    DEFAULT NULL               COMMENT '色泽标准',
  smell_standard        VARCHAR(100)    DEFAULT NULL               COMMENT '气味标准',
  taste_standard        VARCHAR(100)    DEFAULT NULL               COMMENT '口感标准',
  other_indicators      VARCHAR(1000)   DEFAULT NULL               COMMENT '其他指标（JSON格式）',
  is_default            CHAR(1)         DEFAULT '0'                COMMENT '是否默认标准（0否 1是）',
  status                CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (standard_id),
  KEY idx_category_id (category_id)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '质检标准表';

-- 3.2 质检记录表
DROP TABLE IF EXISTS brew_quality_record;
CREATE TABLE brew_quality_record (
  record_id             BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '记录ID',
  batch_no              VARCHAR(50)     NOT NULL                   COMMENT '批次号',
  standard_id           BIGINT(20)      NOT NULL                   COMMENT '质检标准ID',
  inspect_time          DATETIME        NOT NULL                   COMMENT '检测时间',
  inspector_name        VARCHAR(64)     NOT NULL                   COMMENT '检测人',
  
  -- 核心检测指标
  alcohol_before        DECIMAL(5,2)    DEFAULT NULL               COMMENT '蒸馏前酒精度（%）',
  alcohol_after         DECIMAL(5,2)    DEFAULT NULL               COMMENT '蒸馏后酒精度（%）',
  alcohol_actual        DECIMAL(5,2)    DEFAULT NULL               COMMENT '实际酒精度（%）',
  alcohol_error         DECIMAL(5,2)    DEFAULT NULL               COMMENT '酒精度误差（%）',
  wine_output_rate      DECIMAL(5,2)    DEFAULT NULL               COMMENT '产酒率（%）',
  
  -- 感官检测
  color_result          VARCHAR(50)     DEFAULT NULL               COMMENT '色泽检测结果',
  smell_result          VARCHAR(50)     DEFAULT NULL               COMMENT '气味检测结果',
  taste_result          VARCHAR(50)     DEFAULT NULL               COMMENT '口感检测结果',
  
  -- 综合判定
  quality_result        VARCHAR(20)     NOT NULL                   COMMENT '质检结果（1合格 2不合格 3待复检）',
  quality_score         DECIMAL(5,2)    DEFAULT NULL               COMMENT '质量评分（0-100）',
  unqualified_reason    VARCHAR(500)    DEFAULT NULL               COMMENT '不合格原因',
  handle_suggestion     VARCHAR(500)    DEFAULT NULL               COMMENT '处理建议',
  
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_time           DATETIME                                   COMMENT '创建时间',
  PRIMARY KEY (record_id),
  KEY idx_batch_no (batch_no),
  KEY idx_inspect_time (inspect_time),
  KEY idx_quality_result (quality_result)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '质检记录表';

-- 3.3 质检报告表（电子质检单）
DROP TABLE IF EXISTS brew_quality_report;
CREATE TABLE brew_quality_report (
  report_id             BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '报告ID',
  report_no             VARCHAR(50)     NOT NULL                   COMMENT '报告编号',
  batch_no              VARCHAR(50)     NOT NULL                   COMMENT '批次号',
  record_id             BIGINT(20)      NOT NULL                   COMMENT '质检记录ID',
  report_title          VARCHAR(200)    NOT NULL                   COMMENT '报告标题',
  report_content        TEXT            DEFAULT NULL               COMMENT '报告内容（HTML格式）',
  report_status         VARCHAR(20)     DEFAULT '1'                COMMENT '报告状态（1草稿 2已发布 3已归档）',
  approve_status        VARCHAR(20)     DEFAULT '0'                COMMENT '审批状态（0待审批 1已通过 2已驳回）',
  approve_by            VARCHAR(64)     DEFAULT NULL               COMMENT '审批人',
  approve_time          DATETIME        DEFAULT NULL               COMMENT '审批时间',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (report_id),
  UNIQUE KEY uk_report_no (report_no),
  KEY idx_batch_no (batch_no)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '质检报告表';

-- ----------------------------
-- 4. 成本核算模块
-- ----------------------------

-- 4.1 成本项配置表
DROP TABLE IF EXISTS brew_cost_item;
CREATE TABLE brew_cost_item (
  item_id               BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '成本项ID',
  item_code             VARCHAR(50)     NOT NULL                   COMMENT '成本项编码',
  item_name             VARCHAR(100)    NOT NULL                   COMMENT '成本项名称',
  item_type             VARCHAR(20)     NOT NULL                   COMMENT '成本类型（1原料成本 2能耗成本 3人工成本 4其他）',
  calculation_method    VARCHAR(20)     DEFAULT '1'                COMMENT '计算方式（1固定值 2按产量 3按工时）',
  unit_cost             DECIMAL(10,2)   DEFAULT 0.00               COMMENT '单位成本（元）',
  status                CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  remark                VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time           DATETIME                                   COMMENT '创建时间',
  update_by             VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time           DATETIME                                   COMMENT '更新时间',
  PRIMARY KEY (item_id),
  UNIQUE KEY uk_item_code (item_code)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '成本项配置表';

-- 4.2 批次成本明细表
DROP TABLE IF EXISTS brew_batch_cost;
CREATE TABLE brew_batch_cost (
  cost_id               BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '成本ID',
  batch_no              VARCHAR(50)     NOT NULL                   COMMENT '批次号',
  item_id               BIGINT(20)      NOT NULL                   COMMENT '成本项ID',
  cost_amount           DECIMAL(10,2)   NOT NULL                   COMMENT '成本金额（元）',
  cost_quantity         DECIMAL(10,2)   DEFAULT NULL               COMMENT '成本数量（如工时、用电量）',
  calculation_detail    VARCHAR(500)    DEFAULT NULL               COMMENT '计算明细',
  record_time           DATETIME        NOT NULL                   COMMENT '记录时间',
  PRIMARY KEY (cost_id),
  KEY idx_batch_no (batch_no),
  KEY idx_item_id (item_id)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '批次成本明细表';

-- 4.3 成本统计表（月度/季度汇总）
DROP TABLE IF EXISTS brew_cost_statistics;
CREATE TABLE brew_cost_statistics (
  stat_id               BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '统计ID',
  stat_period           VARCHAR(20)     NOT NULL                   COMMENT '统计周期（202601/2026Q1）',
  stat_type             VARCHAR(20)     NOT NULL                   COMMENT '统计类型（1月度 2季度 3年度）',
  total_output          DECIMAL(12,2)   DEFAULT 0.00               COMMENT '总产量（L）',
  total_cost            DECIMAL(12,2)   DEFAULT 0.00               COMMENT '总成本（元）',
  material_cost         DECIMAL(12,2)   DEFAULT 0.00               COMMENT '原料成本（元）',
  energy_cost           DECIMAL(12,2)   DEFAULT 0.00               COMMENT '能耗成本（元）',
  labor_cost            DECIMAL(12,2)   DEFAULT 0.00               COMMENT '人工成本（元）',
  other_cost            DECIMAL(12,2)   DEFAULT 0.00               COMMENT '其他成本（元）',
  unit_cost             DECIMAL(10,2)   DEFAULT 0.00               COMMENT '单位成本（元/L）',
  cost_trend            VARCHAR(20)     DEFAULT NULL               COMMENT '成本趋势（1上升 2下降 3持平）',
  create_time           DATETIME                                   COMMENT '创建时间',
  PRIMARY KEY (stat_id),
  UNIQUE KEY uk_stat_period (stat_period, stat_type),
  KEY idx_stat_type (stat_type)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '成本统计表';

-- ----------------------------
-- 5. 系统辅助表
-- ----------------------------

-- 5.1 数据字典扩展表（酒厂专用）
INSERT INTO sys_dict_type VALUES (100, '原料类型', 'brew_material_type', '0', 'admin', SYSDATE(), '', NULL, '原料类型列表');
INSERT INTO sys_dict_type VALUES (101, '酒类型', 'brew_wine_type', '0', 'admin', SYSDATE(), '', NULL, '酒类型列表');
INSERT INTO sys_dict_type VALUES (102, '生产状态', 'brew_batch_status', '0', 'admin', SYSDATE(), '', NULL, '生产批次状态');
INSERT INTO sys_dict_type VALUES (103, '质检结果', 'brew_quality_result', '0', 'admin', SYSDATE(), '', NULL, '质检结果类型');
INSERT INTO sys_dict_type VALUES (104, '成本类型', 'brew_cost_type', '0', 'admin', SYSDATE(), '', NULL, '成本类型列表');

-- 原料类型字典
INSERT INTO sys_dict_data VALUES (100, 1, '粮食类', '1', 'brew_material_type', '', 'primary', 'N', '0', 'admin', SYSDATE(), '', NULL, '高粱、小麦等主料');
INSERT INTO sys_dict_data VALUES (101, 2, '辅料类', '2', 'brew_material_type', '', 'success', 'N', '0', 'admin', SYSDATE(), '', NULL, '酒曲、水等辅料');
INSERT INTO sys_dict_data VALUES (102, 3, '包装类', '3', 'brew_material_type', '', 'info', 'N', '0', 'admin', SYSDATE(), '', NULL, '酒瓶、包装盒等');

-- 酒类型字典
INSERT INTO sys_dict_data VALUES (110, 1, '白酒', '1', 'brew_wine_type', '', 'danger', 'N', '0', 'admin', SYSDATE(), '', NULL, '蒸馏酒');
INSERT INTO sys_dict_data VALUES (111, 2, '啤酒', '2', 'brew_wine_type', '', 'warning', 'N', '0', 'admin', SYSDATE(), '', NULL, '发酵酒');
INSERT INTO sys_dict_data VALUES (112, 3, '黄酒', '3', 'brew_wine_type', '', 'primary', 'N', '0', 'admin', SYSDATE(), '', NULL, '酿造酒');
INSERT INTO sys_dict_data VALUES (113, 4, '果酒', '4', 'brew_wine_type', '', 'success', 'N', '0', 'admin', SYSDATE(), '', NULL, '果酒类');

-- 生产状态字典
INSERT INTO sys_dict_data VALUES (120, 1, '备料中', '1', 'brew_batch_status', '', 'info', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (121, 2, '发酵中', '2', 'brew_batch_status', '', 'primary', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (122, 3, '蒸馏中', '3', 'brew_batch_status', '', 'warning', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (123, 4, '质检中', '4', 'brew_batch_status', '', 'default', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (124, 5, '已完成', '5', 'brew_batch_status', '', 'success', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (125, 6, '异常', '6', 'brew_batch_status', '', 'danger', 'N', '0', 'admin', SYSDATE(), '', NULL, '');

-- 质检结果字典
INSERT INTO sys_dict_data VALUES (130, 1, '合格', '1', 'brew_quality_result', '', 'success', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (131, 2, '不合格', '2', 'brew_quality_result', '', 'danger', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (132, 3, '待复检', '3', 'brew_quality_result', '', 'warning', 'N', '0', 'admin', SYSDATE(), '', NULL, '');

-- 成本类型字典
INSERT INTO sys_dict_data VALUES (140, 1, '原料成本', '1', 'brew_cost_type', '', 'primary', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (141, 2, '能耗成本', '2', 'brew_cost_type', '', 'warning', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (142, 3, '人工成本', '3', 'brew_cost_type', '', 'success', 'N', '0', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_dict_data VALUES (143, 4, '其他成本', '4', 'brew_cost_type', '', 'info', 'N', '0', 'admin', SYSDATE(), '', NULL, '');

-- ----------------------------
-- 6. 初始化测试数据（可选）
-- ----------------------------

-- 初始化原料数据
INSERT INTO brew_material VALUES (1, 'MAT001', '高粱', '1', 'kg', '东北红高粱', 5.50, '东北粮食供应商', '13800138000', 5000.00, 1000.00, '0', '0', '优质酿酒原料', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_material VALUES (2, 'MAT002', '小麦', '1', 'kg', '优质小麦', 4.20, '东北粮食供应商', '13800138000', 3000.00, 800.00, '0', '0', '', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_material VALUES (3, 'MAT003', '大米', '1', 'kg', '优质大米', 6.00, '南方粮食供应商', '13900139000', 2000.00, 500.00, '0', '0', '', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_material VALUES (4, 'MAT004', '酒曲', '2', 'kg', '大曲', 25.00, '酒曲厂', '13700137000', 200.00, 50.00, '0', '0', '发酵必需品', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_material VALUES (5, 'MAT005', '山泉水', '2', 'L', '天然山泉水', 0.50, '本地水源', '', 50000.00, 10000.00, '0', '0', '', 'admin', SYSDATE(), '', NULL);

-- 初始化酒品种类
INSERT INTO brew_wine_category VALUES (1, 'WINE001', '浓香型白酒', '1', 52.00, 56.00, 60, 90, 28.00, 70.00, '传统浓香型白酒', '0', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_wine_category VALUES (2, 'WINE002', '清香型白酒', '1', 50.00, 53.00, 45, 60, 20.00, 65.00, '清香型白酒', '0', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_wine_category VALUES (3, 'WINE003', '精酿啤酒', '2', 4.00, 6.00, 7, 14, 12.00, 75.00, '精酿啤酒', '0', 'admin', SYSDATE(), '', NULL);

-- 初始化配方模板
INSERT INTO brew_formula_template VALUES (1, 'TPL001', '浓香型白酒标准配方', 1, 1000.00, 53.00, '传统浓香型白酒配方', '1', '0', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_formula_template VALUES (2, 'TPL002', '清香型白酒标准配方', 2, 1000.00, 52.00, '清香型白酒配方', '1', '0', 'admin', SYSDATE(), '', NULL);

-- 初始化配方明细
INSERT INTO brew_formula_detail VALUES (1, 1, 1, 0.5000, 500.00, '1', 1, '主料');
INSERT INTO brew_formula_detail VALUES (2, 1, 2, 0.3000, 300.00, '1', 2, '主料');
INSERT INTO brew_formula_detail VALUES (3, 1, 3, 0.2000, 200.00, '0', 3, '辅料');
INSERT INTO brew_formula_detail VALUES (4, 1, 4, 0.0200, 20.00, '0', 4, '酒曲');
INSERT INTO brew_formula_detail VALUES (5, 1, 5, 3.0000, 3000.00, '0', 5, '水');

-- 初始化成本项
INSERT INTO brew_cost_item VALUES (1, 'COST001', '原料采购成本', '1', '1', 0.00, '0', '按实际采购价计算', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_cost_item VALUES (2, 'COST002', '电费', '2', '2', 0.80, '0', '每升产量0.8元', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_cost_item VALUES (3, 'COST003', '燃气费', '2', '2', 1.20, '0', '每升产量1.2元', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_cost_item VALUES (4, 'COST004', '人工工资', '3', '3', 150.00, '0', '每工时150元', 'admin', SYSDATE(), '', NULL);

-- 初始化质检标准
INSERT INTO brew_quality_standard VALUES (1, 1, '浓香型白酒国标GB/T 10781.1', 52.00, 56.00, 1.00, '无色透明', '窖香浓郁', '绵甜爽净', '{"total_acid":"≥0.4g/L","total_ester":"≥2.0g/L"}', '1', '0', 'admin', SYSDATE(), '', NULL);
INSERT INTO brew_quality_standard VALUES (2, 2, '清香型白酒国标GB/T 10781.2', 50.00, 53.00, 1.00, '无色透明', '清香纯正', '清爽干净', '{"total_acid":"≥0.3g/L","total_ester":"≥1.5g/L"}', '1', '0', 'admin', SYSDATE(), '', NULL);

