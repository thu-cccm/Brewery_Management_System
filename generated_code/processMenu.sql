-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è¿‡ç¨‹', '2001', '1', 'process', 'system/process/index', 1, 0, 'C', '0', '0', 'system:process:list', '#', 'admin', sysdate(), '', null, 'ç”Ÿäº§è¿‡ç¨‹菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è¿‡ç¨‹查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:process:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è¿‡ç¨‹新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:process:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è¿‡ç¨‹修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:process:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è¿‡ç¨‹删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:process:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è¿‡ç¨‹导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:process:export',       '#', 'admin', sysdate(), '', null, '');