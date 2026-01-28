-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡åŽŸæ–™', '2001', '1', 'batchmaterial', 'system/batchmaterial/index', 1, 0, 'C', '0', '0', 'system:batchmaterial:list', '#', 'admin', sysdate(), '', null, 'æ‰¹æ¬¡åŽŸæ–™菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡åŽŸæ–™查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:batchmaterial:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡åŽŸæ–™新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:batchmaterial:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡åŽŸæ–™修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:batchmaterial:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡åŽŸæ–™删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:batchmaterial:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡åŽŸæ–™导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:batchmaterial:export',       '#', 'admin', sysdate(), '', null, '');