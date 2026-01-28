-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬é¡¹', '2003', '1', 'costitem', 'system/costitem/index', 1, 0, 'C', '0', '0', 'system:costitem:list', '#', 'admin', sysdate(), '', null, 'æˆæœ¬é¡¹菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬é¡¹查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:costitem:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬é¡¹新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:costitem:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬é¡¹修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:costitem:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬é¡¹删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:costitem:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æˆæœ¬é¡¹导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:costitem:export',       '#', 'admin', sysdate(), '', null, '');