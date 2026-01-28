#!/usr/bin/env python3
"""修复代码中的中文乱码问题 - 精确版本"""

import os
import re
import glob

# 表名到正确中文的映射（从数据库获取）
table_name_mapping = {
    'BrewMaterial': {
        'table_comment': '原料信息表',
        'function_name': '原料信息',
        'wrong_patterns': ['åŽŸæ–™ä¿¡æ¯', 'åŽŸæ–™']
    },
    'BrewWineCategory': {
        'table_comment': '酒品种类表',
        'function_name': '酒品种类',
        'wrong_patterns': ['é…\'å"ç§ç±»', 'é…\'å"']
    },
    'BrewFormulaTemplate': {
        'table_comment': '配方模板表',
        'function_name': '配方模板',
        'wrong_patterns': ['é…'æ–¹æ¨¡æ¿¿', 'é…'æ–¹']
    },
    'BrewFormulaDetail': {
        'table_comment': '配方明细表',
        'function_name': '配方明细',
        'wrong_patterns': ['é…'æ–¹æ˜Žç»†', 'é…'æ–¹']
    },
    'BrewStockRecord': {
        'table_comment': '库存变动记录表',
        'function_name': '库存变动',
        'wrong_patterns': ['åº"å˜å˜åŠ¨è®°å½•', 'åº"å˜']
    },
    'BrewProductionPlan': {
        'table_comment': '生产计划表',
        'function_name': '生产计划',
        'wrong_patterns': ['ç"Ÿäº§è®¡åˆ\'', 'ç"Ÿäº§']
    },
    'BrewProductionBatch': {
        'table_comment': '生产批次表',
        'function_name': '生产批次',
        'wrong_patterns': ['ç"Ÿäº§æ‰¹æ¬¡', 'ç"Ÿäº§']
    },
    'BrewBatchMaterial': {
        'table_comment': '批次原料使用记录表',
        'function_name': '批次原料',
        'wrong_patterns': ['æ‰¹æ¬¡åŽŸæ–™', 'æ‰¹æ¬¡']
    },
    'BrewProductionProcess': {
        'table_comment': '生产过程记录表',
        'function_name': '生产过程',
        'wrong_patterns': ['ç"Ÿäº§è¿‡ç¨‹', 'ç"Ÿäº§']
    },
    'BrewQualityStandard': {
        'table_comment': '质检标准表',
        'function_name': '质检标准',
        'wrong_patterns': ['è´¨æ£€æ ‡å‡†', 'è´¨æ£€']
    },
    'BrewQualityRecord': {
        'table_comment': '质检记录表',
        'function_name': '质检记录',
        'wrong_patterns': ['è´¨æ£€è®°å½•', 'è´¨æ£€']
    },
    'BrewQualityReport': {
        'table_comment': '质检报告表',
        'function_name': '质检报告',
        'wrong_patterns': ['è´¨æ£€æŠ¥å'', 'è´¨æ£€']
    },
    'BrewCostItem': {
        'table_comment': '成本项配置表',
        'function_name': '成本项',
        'wrong_patterns': ['æˆæœ¬é¡¹', 'æˆæœ¬']
    },
    'BrewBatchCost': {
        'table_comment': '批次成本明细表',
        'function_name': '批次成本',
        'wrong_patterns': ['æ‰¹æ¬¡æˆæœ¬', 'æˆæœ¬']
    },
    'BrewCostStatistics': {
        'table_comment': '成本统计表',
        'function_name': '成本统计',
        'wrong_patterns': ['æˆæœ¬ç»Ÿè®¡', 'æˆæœ¬']
    },
}

# 通用乱码替换
common_fixes = {
    'è¡¨': '表',
    'åˆ—è¡¨': '列表',
    'åŠ é"': '新增',
    'ä¿®æ"¹': '修改',
    'åˆ é"': '删除',
    'å¯¼å‡º': '导出',
    'æŸ¥è¯¢': '查询',
    'å…³è"': '关联',
    'ä½¿ç"¨': '使用',
    'è®°å½•': '记录',
    'æ˜Žç»†': '明细',
    'æ¨¡æ¿¿': '模板',
    'æ ‡å‡†': '标准',
    'æŠ¥å'': '报告',
    'ç»Ÿè®¡': '统计',
    'é¡¹': '项',
    'æ‰¹æ¬¡': '批次',
    'è®¡åˆ\'': '计划',
    'è¿‡ç¨‹': '过程',
}

def fix_file(file_path):
    """修复单个文件中的乱码"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        fixed_count = 0
        
        # 根据文件名确定是哪个表
        file_name = os.path.basename(file_path)
        for class_name, mapping in table_name_mapping.items():
            if class_name in file_name:
                # 替换该表相关的乱码
                for wrong in mapping['wrong_patterns']:
                    if wrong in content:
                        # 根据上下文判断应该替换成什么
                        if 'Controller' in file_name or 'Service' in file_name:
                            # 在Controller/Service中，通常用function_name
                            content = content.replace(wrong, mapping['function_name'])
                        else:
                            # 其他情况用table_comment
                            content = content.replace(wrong, mapping['table_comment'])
                        fixed_count += 1
        
        # 替换通用乱码
        for wrong, correct in common_fixes.items():
            if wrong in content:
                content = content.replace(wrong, correct)
                fixed_count += 1
        
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
    files_fixed = 0
    
    # 需要修复的目录
    base_dirs = [
        '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/java/com/jiuchang/system',
        '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-ui/src/views/system',
        '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-ui/src/api/system',
    ]
    
    for base_dir in base_dirs:
        if not os.path.exists(base_dir):
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
                        files_fixed += 1
    
    print(f"\n处理完成！")
    print(f"修复文件数: {files_fixed}")
    print(f"修复乱码数: {total_fixed}")

if __name__ == '__main__':
    main()

