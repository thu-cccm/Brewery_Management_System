#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""从数据库读取正确注释并修复乱码"""

import os
import glob
import subprocess
import re

# 从数据库获取表名和注释的映射
def get_table_mapping():
    """从数据库获取表注释映射"""
    cmd = [
        'mysql', '-uadmin', '-padmin@120', '-h127.0.0.1', 'maple-boot',
        '-e', "SELECT table_name, table_comment, function_name FROM gen_table WHERE table_name LIKE 'brew_%' ORDER BY table_name;"
    ]
    
    try:
        result = subprocess.run(cmd, capture_output=True, text=True, encoding='utf-8')
        lines = result.stdout.strip().split('\n')[1:]  # 跳过标题行
        
        mapping = {}
        for line in lines:
            parts = line.split('\t')
            if len(parts) >= 3:
                table_name = parts[0]
                table_comment = parts[1]
                function_name = parts[2]
                
                # 转换为类名
                class_name = ''.join(word.capitalize() for word in table_name.replace('brew_', '').split('_'))
                class_name = 'Brew' + class_name
                
                mapping[class_name] = {
                    'table_comment': table_comment,
                    'function_name': function_name
                }
        
        return mapping
    except Exception as e:
        print(f"获取数据库映射失败: {e}")
        return {}

def fix_file_content(content, mapping):
    """修复文件内容中的乱码"""
    fixed = False
    
    # 常见的乱码模式（需要根据实际情况调整）
    # 这里我们使用正则表达式匹配可能的乱码模式
    # 由于乱码是UTF-8被错误解释为其他编码产生的，我们需要反向处理
    
    # 对于每个表，尝试替换
    for class_name, info in mapping.items():
        # 查找包含该类名的注释
        # 乱码通常是UTF-8编码的中文被错误解释
        # 我们需要找到并替换
        
        # 尝试匹配常见的乱码模式
        patterns = [
            (r'åŽŸæ–™ä¿¡æ¯', '原料信息'),
            (r'é…\'å"ç§ç±»', '酒品种类'),
            (r'ç"Ÿäº§è®¡å\'', '生产计划'),
            (r'ç"Ÿäº§æ‰¹æ¬¡', '生产批次'),
            (r'è´¨æ£€æ ‡å‡†', '质检标准'),
            (r'è´¨æ£€è®°å½•', '质检记录'),
            (r'è´¨æ£€æŠ¥å\'', '质检报告'),
            (r'æˆæœ¬é¡¹', '成本项'),
            (r'æˆæœ¬ç»Ÿè®¡', '成本统计'),
            (r'åˆ—è¡¨', '列表'),
            (r'åŠ é"', '新增'),
            (r'ä¿®æ"¹', '修改'),
            (r'åˆ é"', '删除'),
            (r'å¯¼å‡º', '导出'),
            (r'æŸ¥è¯¢', '查询'),
        ]
        
        for pattern, replacement in patterns:
            if re.search(pattern, content):
                content = re.sub(pattern, replacement, content)
                fixed = True
    
    return content, fixed

def fix_files(mapping):
    """修复所有文件"""
    base = '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System'
    
    patterns = [
        f'{base}/ruoyi-admin/src/main/java/com/jiuchang/system/**/*Brew*.java',
        f'{base}/ruoyi-ui/src/views/system/**/*.vue',
        f'{base}/ruoyi-ui/src/api/system/*.js',
    ]
    
    files_fixed = 0
    
    for pattern in patterns:
        for filepath in glob.glob(pattern, recursive=True):
            try:
                with open(filepath, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                new_content, fixed = fix_file_content(content, mapping)
                
                if fixed:
                    with open(filepath, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"✓ {os.path.basename(filepath)}")
                    files_fixed += 1
            except Exception as e:
                print(f"✗ {filepath}: {e}")
    
    print(f"\n修复完成！共修复 {files_fixed} 个文件")

if __name__ == '__main__':
    print("从数据库获取表注释映射...")
    mapping = get_table_mapping()
    print(f"获取到 {len(mapping)} 个表的映射")
    
    print("\n开始修复文件...")
    fix_files(mapping)

