-- 1. 创建顶层目录：数据分析 (menu_id: 3000)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES (3000, '数据分析', 0, 4, 'analysis', NULL, 1, 0, 'M', '0', '0', NULL, 'chart', 'admin', sysdate(), '数据分析顶层目录');

-- 2. 创建子菜单：决策看板 (menu_id: 3001)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES (3001, '决策看板', 3000, 1, 'dashboard', 'brew/analysis/index', 1, 0, 'C', '0', '0', 'brew:analysis:list', 'dashboard', 'admin', sysdate(), '智能决策看板');

-- 3. 创建子菜单：溯源中心 (menu_id: 3002)
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES (3002, '溯源中心', 3000, 2, 'trace', 'brew/trace/index', 1, 0, 'C', '0', '0', 'brew:trace:list', 'search', 'admin', sysdate(), '批次溯源中心');

-- 4. 授权 (假设 admin 角色拥有所有权限，通常若依 admin 拥有 *:* 权限，不需要显式授权，但为了保险可以手动加一下菜单权限)
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 3000);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 3001);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 3002);
