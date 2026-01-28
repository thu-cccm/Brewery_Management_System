-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬ç»Ÿè®¡', '2003', '1', 'statistics', 'system/statistics/index', 1, 0, 'C', '0', '0', 'system:statistics:list', '#', 'admin', sysdate(), '', null, 'æˆæœ¬ç»Ÿè®¡菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬ç»Ÿè®¡查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:statistics:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬ç»Ÿè®¡新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:statistics:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬ç»Ÿè®¡修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:statistics:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬ç»Ÿè®¡删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:statistics:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬ç»Ÿè®¡导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:statistics:export',       '#', 'admin', sysdate(), '', null, '');