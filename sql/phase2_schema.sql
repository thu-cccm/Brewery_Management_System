-- ===================================================================
-- 阶段二：核心业务增强数据库脚本
-- 包含：设备管理、生产监控、工艺标准、损耗分析
-- ===================================================================

-- ----------------------------
-- 1. 设备管理表 (brew_equipment)
-- ----------------------------
DROP TABLE IF EXISTS brew_equipment;
CREATE TABLE brew_equipment (
  equipment_id      bigint(20)      NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  equipment_name    varchar(64)     NOT NULL COMMENT '设备名称',
  equipment_code    varchar(64)     NOT NULL COMMENT '设备编号',
  equipment_type    varchar(2)      DEFAULT '0' COMMENT '设备类型(0发酵罐 1糖化锅 2过滤槽 3灌装机 9其他)',
  status            varchar(2)      DEFAULT '0' COMMENT '设备状态(0空闲 1运行中 2清洗中 3维修中)',
  location          varchar(128)    DEFAULT '' COMMENT '存放位置',
  capacity          decimal(10,2)   DEFAULT 0 COMMENT '容量(L)',
  manufacturer      varchar(128)    DEFAULT '' COMMENT '生产厂家',
  purchase_date     datetime        COMMENT '购买日期',
  last_maintenance_date datetime    COMMENT '上次维保日期',
  remark            varchar(500)    DEFAULT '' COMMENT '备注',
  del_flag          char(1)         DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     DEFAULT '' COMMENT '创建者',
  create_time       datetime        COMMENT '创建时间',
  update_by         varchar(64)     DEFAULT '' COMMENT '更新者',
  update_time       datetime        COMMENT '更新时间',
  PRIMARY KEY (equipment_id)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='设备台账表';

-- ----------------------------
-- 2. 设备维保记录表 (brew_equipment_maintenance)
-- ----------------------------
DROP TABLE IF EXISTS brew_equipment_maintenance;
CREATE TABLE brew_equipment_maintenance (
  maintenance_id    bigint(20)      NOT NULL AUTO_INCREMENT COMMENT '维保ID',
  equipment_id      bigint(20)      NOT NULL COMMENT '设备ID',
  maintenance_date  datetime        NOT NULL COMMENT '维保日期',
  type              varchar(2)      DEFAULT '0' COMMENT '维保类型(0日常保养 1故障维修 2定期检修)',
  content           varchar(500)    NOT NULL COMMENT '维保内容',
  performer         varchar(64)     DEFAULT '' COMMENT '执行人',
  cost              decimal(10,2)   DEFAULT 0 COMMENT '维保费用',
  result            varchar(200)    DEFAULT '' COMMENT '维保结果',
  remark            varchar(500)    DEFAULT '' COMMENT '备注',
  create_by         varchar(64)     DEFAULT '' COMMENT '创建者',
  create_time       datetime        COMMENT '创建时间',
  PRIMARY KEY (maintenance_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='设备维保记录表';

-- ----------------------------
-- 3. 工艺参数标准表 (brew_technology_standard)
-- 用于定义配方在不同阶段的标准参数
-- ----------------------------
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
  PRIMARY KEY (standard_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='工艺参数标准表';

-- ----------------------------
-- 4. 生产过程监控表 (brew_process_monitor)
-- 记录实际生产过程中的参数
-- ----------------------------
DROP TABLE IF EXISTS brew_process_monitor;
CREATE TABLE brew_process_monitor (
  monitor_id        bigint(20)      NOT NULL AUTO_INCREMENT COMMENT '监控ID',
  batch_id          bigint(20)      NOT NULL COMMENT '生产批次ID',
  stage_key         varchar(64)     NOT NULL COMMENT '生产阶段',
  record_time       datetime        NOT NULL COMMENT '记录时间',
  temperature       decimal(10,2)   DEFAULT NULL COMMENT '温度(℃)',
  pressure          decimal(10,2)   DEFAULT NULL COMMENT '压力(MPa)',
  sugar_degree      decimal(10,2)   DEFAULT NULL COMMENT '糖度(°P)',
  ph_value          decimal(10,2)   DEFAULT NULL COMMENT 'PH值',
  alcohol           decimal(10,2)   DEFAULT NULL COMMENT '酒精度(%vol)',
  operator          varchar(64)     DEFAULT '' COMMENT '操作员',
  is_abnormal       char(1)         DEFAULT '0' COMMENT '是否异常(0否 1是)',
  remark            varchar(500)    DEFAULT '' COMMENT '备注',
  create_time       datetime        COMMENT '创建时间',
  PRIMARY KEY (monitor_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='生产过程监控表';

-- ----------------------------
-- 5. 批次损耗分析表 (brew_batch_yield)
-- ----------------------------
DROP TABLE IF EXISTS brew_batch_yield;
CREATE TABLE brew_batch_yield (
  yield_id          bigint(20)      NOT NULL AUTO_INCREMENT COMMENT '损耗ID',
  batch_id          bigint(20)      NOT NULL COMMENT '生产批次ID',
  stage_key         varchar(64)     NOT NULL COMMENT '阶段',
  input_qty         decimal(10,2)   DEFAULT 0 COMMENT '投入数量',
  output_qty        decimal(10,2)   DEFAULT 0 COMMENT '产出数量',
  loss_qty          decimal(10,2)   DEFAULT 0 COMMENT '损耗数量',
  loss_rate         decimal(10,4)   DEFAULT 0 COMMENT '损耗率',
  unit              varchar(32)     DEFAULT '' COMMENT '单位',
  is_abnormal       char(1)         DEFAULT '0' COMMENT '是否异常(0否 1是)',
  remark            varchar(500)    DEFAULT '' COMMENT '备注',
  create_time       datetime        COMMENT '创建时间',
  PRIMARY KEY (yield_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='批次损耗分析表';

-- ----------------------------
-- 6. 插入菜单数据 (Phase 2 新增菜单)
-- ----------------------------
-- 设备管理子菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('设备台账', 2040, 1, 'list', 'brew/equipment/index', 'C', '0', '0', 'brew:equipment:list', 'server', 'admin', sysdate()),
('维保记录', 2040, 2, 'maintenance', 'brew/maintenance/index', 'C', '0', '0', 'brew:maintenance:list', 'tool', 'admin', sysdate());

-- 生产管理 -> 过程监控 (新增)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('过程监控', 2010, 5, 'monitor', 'brew/monitor/index', 'C', '0', '0', 'brew:monitor:list', 'monitor', 'admin', sysdate());

-- 统计分析 -> 损耗分析 (新增)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('损耗分析', 2050, 3, 'yield', 'brew/yield/index', 'C', '0', '0', 'brew:yield:list', 'chart', 'admin', sysdate());
