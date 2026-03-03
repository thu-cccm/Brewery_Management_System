-- ===================================================================
-- 阶段二补充：数字化工艺与损耗分析脚本
-- 包含：工艺标准表、损耗分析表及对应菜单
-- ===================================================================

-- 1. 工艺参数标准表 (brew_technology_standard)
-- 补充：其实在 phase2_schema.sql 中已经包含了这个表的创建语句，
-- 但为了确保完整性和避免重复，这里使用 IF NOT EXISTS 或先 DROP
DROP TABLE IF EXISTS brew_technology_standard;
CREATE TABLE brew_technology_standard (
  standard_id       bigint(20)      NOT NULL AUTO_INCREMENT COMMENT '标准ID',
  formula_id        bigint(20)      NOT NULL COMMENT '关联配方ID',
  stage_key         varchar(64)     NOT NULL COMMENT '生产阶段(mashing=糖化, boiling=煮沸, fermentation=发酵)',
  param_name        varchar(64)     NOT NULL COMMENT '参数名称(如: 温度, 压力, 糖度)',
  param_code        varchar(64)     NOT NULL COMMENT '参数代码(temp, pressure, brix)',
  standard_value    varchar(64)     NOT NULL COMMENT '标准值',
  min_value         decimal(10,2)   DEFAULT NULL COMMENT '允许下限',
  max_value         decimal(10,2)   DEFAULT NULL COMMENT '允许上限',
  unit              varchar(32)     DEFAULT '' COMMENT '单位',
  order_num         int(4)          DEFAULT 0 COMMENT '排序号',
  remark            varchar(500)    DEFAULT '' COMMENT '备注',
  create_by         varchar(64)     DEFAULT '' COMMENT '创建者',
  create_time       datetime        COMMENT '创建时间',
  update_by         varchar(64)     DEFAULT '' COMMENT '更新者',
  update_time       datetime        COMMENT '更新时间',
  PRIMARY KEY (standard_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='工艺参数标准表';

-- 2. 批次损耗分析表 (brew_batch_yield)
-- 同上，确保表结构存在
DROP TABLE IF EXISTS brew_batch_yield;
CREATE TABLE brew_batch_yield (
  yield_id          bigint(20)      NOT NULL AUTO_INCREMENT COMMENT '损耗ID',
  batch_id          bigint(20)      NOT NULL COMMENT '生产批次ID',
  stage_key         varchar(64)     NOT NULL COMMENT '阶段(mashing=糖化, fermentation=发酵, packaging=包装)',
  input_qty         decimal(10,2)   DEFAULT 0 COMMENT '投入数量',
  output_qty        decimal(10,2)   DEFAULT 0 COMMENT '产出数量',
  loss_qty          decimal(10,2)   DEFAULT 0 COMMENT '损耗数量',
  loss_rate         decimal(10,4)   DEFAULT 0 COMMENT '损耗率(%)',
  unit              varchar(32)     DEFAULT '' COMMENT '单位',
  is_abnormal       char(1)         DEFAULT '0' COMMENT '是否异常(0否 1是)',
  remark            varchar(500)    DEFAULT '' COMMENT '备注',
  create_by         varchar(64)     DEFAULT '' COMMENT '创建者',
  create_time       datetime        COMMENT '创建时间',
  update_by         varchar(64)     DEFAULT '' COMMENT '更新者',
  update_time       datetime        COMMENT '更新时间',
  PRIMARY KEY (yield_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='批次损耗分析表';

-- 3. 补充菜单 (如果 phase1 脚本中已删除并重新插入，这里再次确认插入)
-- 质量管理 -> 质检标准 (对应工艺标准)
-- 注意：这里使用 IGNORE 或 DELETE 后插入防止重复
DELETE FROM sys_menu WHERE path = 'standard' AND parent_id = 2020;
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('工艺标准', 2020, 1, 'standard', 'brew/standard/index', 'C', '0', '0', 'brew:standard:list', 'skill', 'admin', sysdate());

-- 统计分析 -> 损耗分析
DELETE FROM sys_menu WHERE path = 'yield' AND parent_id = 2050;
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('损耗分析', 2050, 3, 'yield', 'brew/yield/index', 'C', '0', '0', 'brew:yield:list', 'chart', 'admin', sysdate());
