-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€è®°å½•', '2002', '1', 'quality', 'system/quality/index', 1, 0, 'C', '0', '0', 'system:quality:list', '#', 'admin', sysdate(), '', null, 'è´¨æ£€è®°å½•菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€è®°å½•查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:quality:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€è®°å½•新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:quality:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€è®°å½•修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:quality:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€è®°å½•删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:quality:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€è®°å½•导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:quality:export',       '#', 'admin', sysdate(), '', null, '');