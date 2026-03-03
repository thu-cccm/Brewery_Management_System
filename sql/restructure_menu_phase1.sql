-- ===================================================================
-- 阶段一：菜单重构与功能隐藏脚本
-- 目标：弱化 OA 属性，建立以生产、质量为核心的菜单体系
-- ===================================================================

-- 1. 清理可能存在的冲突 ID (2000-2100 为预留的新菜单段)
DELETE FROM sys_menu WHERE menu_id BETWEEN 2000 AND 2100;
-- 清理之前的临时菜单 (3000+)
DELETE FROM sys_menu WHERE menu_id >= 3000;

-- 2. 创建新的顶层菜单 (Top-Level Menus)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark) VALUES
(2000, '驾驶舱', 0, 1, 'dashboard-brew', 'dashboard/index', 1, 0, 'M', '0', '0', '', 'dashboard', 'admin', sysdate(), '驾驶舱'),
(2010, '生产管理', 0, 2, 'production', NULL, 1, 0, 'M', '0', '0', '', 'build', 'admin', sysdate(), '生产管理'),
(2020, '质量管理', 0, 3, 'quality', NULL, 1, 0, 'M', '0', '0', '', 'validCode', 'admin', sysdate(), '质量管理'),
(2030, '仓储物流', 0, 4, 'inventory', NULL, 1, 0, 'M', '0', '0', '', 'shopping', 'admin', sysdate(), '仓储物流'),
(2040, '设备管理', 0, 5, 'equipment', NULL, 1, 0, 'M', '0', '0', '', 'server', 'admin', sysdate(), '设备管理'),
(2050, '统计分析', 0, 6, 'stats', NULL, 1, 0, 'M', '0', '0', '', 'chart', 'admin', sysdate(), '统计分析'),
(2060, '系统设置', 0, 99, 'settings', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', sysdate(), '系统设置');

-- 3. 隐藏/降级原有 OA 菜单
-- 隐藏：系统管理(1), 系统监控(2), 系统工具(3), 若依官网(4)
-- 注意：系统管理(1) 先隐藏，后面会把它下面的子菜单移走
UPDATE sys_menu SET visible = '1', status = '1' WHERE menu_id IN (1, 2, 3, 4);

-- 4. 迁移“系统管理”下的核心功能到“系统设置”(2060)
-- 用户(100), 角色(101), 菜单(102), 部门(103), 岗位(104), 字典(105), 参数(106), 日志(108)
UPDATE sys_menu SET parent_id = 2060, visible = '0', status = '0' WHERE menu_id IN (100, 101, 102, 103, 104, 105, 106, 108);

-- 5. 改造“通知公告”(107) 为 “生产消息中心” 并移至 “驾驶舱”(2000) 下
UPDATE sys_menu SET menu_name = '生产消息中心', parent_id = 2000, order_num = 10, visible = '0', status = '0' WHERE menu_id = 107;

-- 6. 清理旧的业务菜单 (避免重复)
-- 根据 path 和 component 特征删除，防止之前生成代码时产生的散乱菜单
DELETE FROM sys_menu WHERE path IN ('plan', 'batch', 'process', 'template', 'standard', 'quality', 'report', 'material', 'stock', 'category', 'cost') AND menu_type = 'C';
DELETE FROM sys_menu WHERE path IN ('trace', 'analysis') AND menu_type = 'C';

-- 7. 重新插入业务菜单 (挂载到新分类下)

-- [生产管理 2010]
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('生产计划', 2010, 1, 'plan', 'system/plan/index', 'C', '0', '0', 'system:plan:list', 'form', 'admin', sysdate()),
('生产批次', 2010, 2, 'batch', 'system/batch/index', 'C', '0', '0', 'system:batch:list', 'list', 'admin', sysdate()),
('生产工序', 2010, 3, 'process', 'system/process/index', 'C', '0', '0', 'system:process:list', 'guide', 'admin', sysdate()),
('配方模板', 2010, 4, 'template', 'system/template/index', 'C', '0', '0', 'system:template:list', 'component', 'admin', sysdate());

-- [质量管理 2020]
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('质检标准', 2020, 1, 'standard', 'system/standard/index', 'C', '0', '0', 'system:standard:list', 'skill', 'admin', sysdate()),
('质检记录', 2020, 2, 'quality-record', 'system/quality/index', 'C', '0', '0', 'system:quality:list', 'clipboard', 'admin', sysdate()),
('质检报告', 2020, 3, 'report', 'system/report/index', 'C', '0', '0', 'system:report:list', 'documentation', 'admin', sysdate()),
('全链路追溯', 2020, 4, 'trace', 'brew/trace/index', 'C', '0', '0', 'brew:trace:list', 'search', 'admin', sysdate());

-- [仓储物流 2030]
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('原料管理', 2030, 1, 'material', 'system/material/index', 'C', '0', '0', 'system:material:list', 'component', 'admin', sysdate()),
('库存记录', 2030, 2, 'stock', 'system/stock/index', 'C', '0', '0', 'system:stock:list', 'list', 'admin', sysdate()),
('酒品分类', 2030, 3, 'category', 'system/category/index', 'C', '0', '0', 'system:category:list', 'tree', 'admin', sysdate());

-- [统计分析 2050]
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time) VALUES
('决策看板', 2050, 1, 'analysis', 'brew/analysis/index', 'C', '0', '0', 'brew:analysis:list', 'dashboard', 'admin', sysdate()),
('成本核算', 2050, 2, 'cost', 'system/cost/index', 'C', '0', '0', 'system:cost:list', 'money', 'admin', sysdate());

