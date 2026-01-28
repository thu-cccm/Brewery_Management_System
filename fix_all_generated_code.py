#!/usr/bin/env python3
"""修复所有生成代码中的模板变量"""

import os
import re
import glob

# 定义表名和主键的映射
pk_map = {
    "BrewBatchCost": ("costId", "CostId"),
    "BrewBatchMaterial": ("recordId", "RecordId"),
    "BrewCostItem": ("itemId", "ItemId"),
    "BrewCostStatistics": ("statId", "StatId"),
    "BrewFormulaDetail": ("detailId", "DetailId"),
    "BrewFormulaTemplate": ("templateId", "TemplateId"),
    "BrewMaterial": ("materialId", "MaterialId"),
    "BrewProductionBatch": ("batchId", "BatchId"),
    "BrewProductionPlan": ("planId", "PlanId"),
    "BrewProductionProcess": ("processId", "ProcessId"),
    "BrewQualityRecord": ("recordId", "RecordId"),
    "BrewQualityReport": ("reportId", "ReportId"),
    "BrewQualityStandard": ("standardId", "StandardId"),
    "BrewStockRecord": ("recordId", "RecordId"),
    "BrewWineCategory": ("categoryId", "CategoryId"),
}

base_dir = "/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/java/com/jiuchang/system"

# 查找所有包含模板变量的Java文件
java_files = []
for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith('.java'):
            file_path = os.path.join(root, file)
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
                if '${' in content:
                    java_files.append(file_path)

print(f"找到 {len(java_files)} 个需要修复的Java文件")

fixed_count = 0
for file_path in java_files:
    # 从文件名提取类名
    file_name = os.path.basename(file_path)
    
    # 尝试匹配类名
    for class_name, (java_field, cap_java_field) in pk_map.items():
        if class_name in file_name:
            print(f"修复 {file_name}: {java_field} -> {cap_java_field}")
            
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            # 替换模板变量
            content = content.replace("${pkColumn.javaField}", java_field)
            content = content.replace("${pkColumn.capJavaField}", cap_java_field)
            content = content.replace("${pkColumn.javaType}", "Long")
            
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            
            fixed_count += 1
            break

print(f"\n✓ 共修复 {fixed_count} 个Java文件")

# 同时修复XML mapper文件
xml_dir = "/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/resources/mapper/system"
xml_files = glob.glob(os.path.join(xml_dir, "Brew*.xml"))

print(f"\n找到 {len(xml_files)} 个XML mapper文件")

xml_fixed = 0
for xml_file in xml_files:
    file_name = os.path.basename(xml_file)
    
    for class_name, (java_field, cap_java_field) in pk_map.items():
        if class_name in file_name:
            print(f"修复 {file_name}: {java_field} -> {cap_java_field}")
            
            with open(xml_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            if '${' in content:
                # 替换模板变量
                content = content.replace("${pkColumn.javaField}", java_field)
                content = content.replace("${pkColumn.capJavaField}", cap_java_field)
                content = content.replace("${pkColumn.javaType}", "Long")
                
                with open(xml_file, 'w', encoding='utf-8') as f:
                    f.write(content)
                
                xml_fixed += 1
            break

print(f"✓ 共修复 {xml_fixed} 个XML文件")

print("\n所有生成的代码已修复完成！")

