#!/usr/bin/env python3
"""修复代码中的中文乱码问题"""

import os
import re
import glob

# 定义乱码到正确中文的映射
encoding_fixes = {
    # 原料相关
    'åŽŸæ–™ä¿¡æ¯': '原料信息',
    'åŽŸæ–™': '原料',
    
    # 酒品种类
    'é…\'å"ç§ç±»': '酒品种类',
    'é…\'å"': '酒品',
    
    # 生产相关
    'ç"Ÿäº§è®¡åˆ\'': '生产计划',
    'ç"Ÿäº§æ‰¹æ¬¡': '生产批次',
    'ç"Ÿäº§è¿‡ç¨‹': '生产过程',
    'ç"Ÿäº§': '生产',
    'è®¡åˆ\'': '计划',
    'æ‰¹æ¬¡': '批次',
    'è¿‡ç¨‹': '过程',
    
    # 质检相关
    'è´¨æ£€æ ‡å‡†': '质检标准',
    'è´¨æ£€è®°å½•': '质检记录',
    'è´¨æ£€æŠ¥å'': '质检报告',
    'è´¨æ£€': '质检',
    'æ ‡å‡†': '标准',
    'è®°å½•': '记录',
    'æŠ¥å'': '报告',
    
    # 成本相关
    'æˆæœ¬é¡¹': '成本项',
    'æˆæœ¬ç»Ÿè®¡': '成本统计',
    'æˆæœ¬': '成本',
    'é¡¹': '项',
    'ç»Ÿè®¡': '统计',
    
    # 配方相关
    'é…'æ–¹æ¨¡æ¿¿': '配方模板',
    'é…'æ–¹æ˜Žç»†': '配方明细',
    'é…'æ–¹': '配方',
    'æ¨¡æ¿¿': '模板',
    'æ˜Žç»†': '明细',
    
    # 库存相关
    'åº"å˜å˜åŠ¨è®°å½•': '库存变动记录',
    'åº"å˜': '库存',
    'å˜åŠ¨': '变动',
    
    # 其他
    'è¡¨': '表',
    'åˆ—è¡¨': '列表',
    'åŠ é"': '新增',
    'ä¿®æ"¹': '修改',
    'åˆ é"': '删除',
    'å¯¼å‡º': '导出',
    'æŸ¥è¯¢': '查询',
    'å…³è"': '关联',
    'ä½¿ç"¨': '使用',
    'é…„è®°': '记录',
}

# 需要修复的文件目录
base_dirs = [
    '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/java/com/jiuchang/system',
    '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-ui/src/views/system',
    '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-ui/src/api/system',
]

def fix_file(file_path):
    """修复单个文件中的乱码"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        fixed_count = 0
        
        # 替换所有乱码
        for wrong, correct in encoding_fixes.items():
            if wrong in content:
                content = content.replace(wrong, correct)
                fixed_count += content.count(correct) - original_content.count(correct)
        
        # 如果内容有变化，写回文件
        if content != original_content:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            return fixed_count
        
        return 0
    except Exception as e:
        print(f"处理文件 {file_path} 时出错: {e}")
        return 0

def main():
    """主函数"""
    total_fixed = 0
    files_processed = 0
    
    for base_dir in base_dirs:
        if not os.path.exists(base_dir):
            print(f"目录不存在: {base_dir}")
            continue
        
        # 查找所有相关文件
        patterns = ['**/*.java', '**/*.vue', '**/*.js']
        
        for pattern in patterns:
            for file_path in glob.glob(os.path.join(base_dir, pattern), recursive=True):
                # 只处理Brew开头的文件
                if 'Brew' in os.path.basename(file_path) or 'brew' in os.path.basename(file_path):
                    fixed = fix_file(file_path)
                    if fixed > 0:
                        print(f"✓ 修复 {os.path.basename(file_path)}: {fixed} 处")
                        total_fixed += fixed
                    files_processed += 1
    
    print(f"\n处理完成！")
    print(f"处理文件数: {files_processed}")
    print(f"修复乱码数: {total_fixed}")

if __name__ == '__main__':
    main()

