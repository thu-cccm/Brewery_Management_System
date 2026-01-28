-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æ ‡å‡†', '2002', '1', 'standard', 'system/standard/index', 1, 0, 'C', '0', '0', 'system:standard:list', '#', 'admin', sysdate(), '', null, 'è´¨æ£€æ ‡å‡†菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æ ‡å‡†查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:standard:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æ ‡å‡†新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:standard:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æ ‡å‡†修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:standard:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æ ‡å‡†删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:standard:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æ ‡å‡†导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:standard:export',       '#', 'admin', sysdate(), '', null, '');