-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡æˆæœ¬', '2003', '1', 'cost', 'system/cost/index', 1, 0, 'C', '0', '0', 'system:cost:list', '#', 'admin', sysdate(), '', null, 'æ‰¹æ¬¡æˆæœ¬菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡æˆæœ¬查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:cost:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡æˆæœ¬新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:cost:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡æˆæœ¬修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:cost:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡æˆæœ¬删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:cost:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('æ‰¹æ¬¡æˆæœ¬导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:cost:export',       '#', 'admin', sysdate(), '', null, '');