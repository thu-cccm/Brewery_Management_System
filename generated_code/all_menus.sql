-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§æ‰¹æ¬¡', '2001', '1', 'batch', 'system/batch/index', 1, 0, 'C', '0', '0', 'system:batch:list', '#', 'admin', sysdate(), '', null, 'ç”Ÿäº§æ‰¹æ¬¡菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§æ‰¹æ¬¡查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:batch:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§æ‰¹æ¬¡新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:batch:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§æ‰¹æ¬¡修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:batch:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§æ‰¹æ¬¡删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:batch:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§æ‰¹æ¬¡导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:batch:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('æ‰¹æ¬¡åŽŸæ–™导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:batchmaterial:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…’å“ç§ç±»', '2000', '1', 'category', 'system/category/index', 1, 0, 'C', '0', '0', 'system:category:list', '#', 'admin', sysdate(), '', null, 'é…’å“ç§ç±»菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…’å“ç§ç±»查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:category:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…’å“ç§ç±»新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:category:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…’å“ç§ç±»修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:category:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…’å“ç§ç±»删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:category:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…’å“ç§ç±»导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:category:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('æ‰¹æ¬¡æˆæœ¬导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:cost:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('æˆæœ¬é¡¹导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:costitem:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ˜Žç»†', '2000', '1', 'detail', 'system/detail/index', 1, 0, 'C', '0', '0', 'system:detail:list', '#', 'admin', sysdate(), '', null, 'é…æ–¹æ˜Žç»†菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ˜Žç»†查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ˜Žç»†新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ˜Žç»†修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ˜Žç»†删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ˜Žç»†导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åŽŸæ–™ä¿¡æ¯', '2000', '1', 'material', 'system/material/index', 1, 0, 'C', '0', '0', 'system:material:list', '#', 'admin', sysdate(), '', null, 'åŽŸæ–™ä¿¡æ¯菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åŽŸæ–™ä¿¡æ¯查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:material:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åŽŸæ–™ä¿¡æ¯新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:material:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åŽŸæ–™ä¿¡æ¯修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:material:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åŽŸæ–™ä¿¡æ¯删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:material:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('åŽŸæ–™ä¿¡æ¯导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:material:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è®¡åˆ’', '2001', '1', 'plan', 'system/plan/index', 1, 0, 'C', '0', '0', 'system:plan:list', '#', 'admin', sysdate(), '', null, 'ç”Ÿäº§è®¡åˆ’菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è®¡åˆ’查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è®¡åˆ’新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è®¡åˆ’修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è®¡åˆ’删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('ç”Ÿäº§è®¡åˆ’导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('ç”Ÿäº§è¿‡ç¨‹导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:process:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('è´¨æ£€è®°å½•导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:quality:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æŠ¥å‘Š', '2002', '1', 'report', 'system/report/index', 1, 0, 'C', '0', '0', 'system:report:list', '#', 'admin', sysdate(), '', null, 'è´¨æ£€æŠ¥å‘Š菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æŠ¥å‘Š查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:report:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æŠ¥å‘Š新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:report:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æŠ¥å‘Š修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:report:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æŠ¥å‘Š删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:report:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('è´¨æ£€æŠ¥å‘Š导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:report:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('è´¨æ£€æ ‡å‡†导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:standard:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('æˆæœ¬ç»Ÿè®¡导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:statistics:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
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
values('åº“å­˜å˜åŠ¨导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:stock:export',       '#', 'admin', sysdate(), '', null, '');-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ¨¡æ¿', '2000', '1', 'template', 'system/template/index', 1, 0, 'C', '0', '0', 'system:template:list', '#', 'admin', sysdate(), '', null, 'é…æ–¹æ¨¡æ¿菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ¨¡æ¿查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:template:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ¨¡æ¿新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:template:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ¨¡æ¿修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:template:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ¨¡æ¿删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:template:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('é…æ–¹æ¨¡æ¿导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:template:export',       '#', 'admin', sysdate(), '', null, '');