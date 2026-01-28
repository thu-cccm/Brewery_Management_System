#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""修复中文乱码 - 简单版本"""

import os
import glob

# 修复映射：从数据库获取的正确中文
fixes = {
    'åŽŸæ–™ä¿¡æ¯': '原料信息',
    'é…\'å"ç§ç±»': '酒品种类',
    'é…'æ–¹æ¨¡æ¿¿': '配方模板',
    'é…'æ–¹æ˜Žç»†': '配方明细',
    'åº"å˜å˜åŠ¨è®°å½•': '库存变动记录',
    'ç"Ÿäº§è®¡åˆ\'': '生产计划',
    'ç"Ÿäº§æ‰¹æ¬¡': '生产批次',
    'æ‰¹æ¬¡åŽŸæ–™ä½¿ç"¨è®°å½•': '批次原料使用记录',
    'ç"Ÿäº§è¿‡ç¨‹è®°å½•': '生产过程记录',
    'è´¨æ£€æ ‡å‡†': '质检标准',
    'è´¨æ£€è®°å½•': '质检记录',
    'è´¨æ£€æŠ¥å'': '质检报告',
    'æˆæœ¬é¡¹é…„ç½®': '成本项配置',
    'æ‰¹æ¬¡æˆæœ¬æ˜Žç»†': '批次成本明细',
    'æˆæœ¬ç»Ÿè®¡': '成本统计',
    'åˆ—è¡¨': '列表',
    'åŠ é"': '新增',
    'ä¿®æ"¹': '修改',
    'åˆ é"': '删除',
    'å¯¼å‡º': '导出',
    'æŸ¥è¯¢': '查询',
    'è¡¨': '表',
    'æ•°æ®': '数据',
}

def fix_file(filepath):
    """修复单个文件"""
    try:
        # 读取文件
        with open(filepath, 'rb') as f:
            content = f.read()
        
        # 尝试解码为UTF-8
        try:
            text = content.decode('utf-8')
        except:
            # 如果失败，尝试其他编码
            text = content.decode('latin1')
        
        original = text
        fixed_count = 0
        
        # 执行替换
        for wrong, correct in fixes.items():
            if wrong in text:
                text = text.replace(wrong, correct)
                fixed_count += 1
        
        # 如果有修改，写回文件
        if text != original:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(text)
            return fixed_count
        return 0
    except Exception as e:
        print(f"错误 {filepath}: {e}")
        return 0

def main():
    """主函数"""
    base = '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System'
    
    # 查找所有需要修复的文件
    patterns = [
        f'{base}/ruoyi-admin/src/main/java/com/jiuchang/system/**/*Brew*.java',
        f'{base}/ruoyi-ui/src/views/system/**/*.vue',
        f'{base}/ruoyi-ui/src/api/system/*.js',
    ]
    
    total_fixed = 0
    files_fixed = 0
    
    for pattern in patterns:
        for filepath in glob.glob(pattern, recursive=True):
            fixed = fix_file(filepath)
            if fixed > 0:
                print(f"✓ {os.path.basename(filepath)}: {fixed} 处")
                total_fixed += fixed
                files_fixed += 1
    
    print(f"\n完成！修复 {files_fixed} 个文件，共 {total_fixed} 处乱码")

if __name__ == '__main__':
    main()

