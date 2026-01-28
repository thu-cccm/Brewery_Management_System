-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åº“å­˜å˜åŠ¨', '2000', '1', 'stock', 'system/stock/index', 1, 0, 'C', '0', '0', 'system:stock:list', '#', 'admin', sysdate(), '', null, 'åº“å­˜å˜åŠ¨菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åº“å­˜å˜åŠ¨查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:stock:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åº“å­˜å˜åŠ¨新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:stock:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åº“å­˜å˜åŠ¨修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:stock:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åº“å­˜å˜åŠ¨删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:stock:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åº“å­˜å˜åŠ¨导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:stock:export',       '#', 'admin', sysdate(), '', null, '');