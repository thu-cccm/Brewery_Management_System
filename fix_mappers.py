#!/usr/bin/env python3
"""修复Mapper文件中的模板变量"""

import os
import re

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

mapper_dir = "/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/java/com/jiuchang/system/mapper"

for class_name, (java_field, cap_java_field) in pk_map.items():
    file_path = os.path.join(mapper_dir, f"{class_name}Mapper.java")
    
    if os.path.exists(file_path):
        print(f"修复 {class_name}Mapper.java: {java_field} -> {cap_java_field}")
        
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # 替换模板变量
        content = content.replace("${pkColumn.javaField}", java_field)
        content = content.replace("${pkColumn.capJavaField}", cap_java_field)
        content = content.replace("${pkColumn.javaType}", "Long")
        
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(content)
        
        print(f"✓ {class_name}Mapper.java 已修复")
    else:
        print(f"✗ 文件不存在: {file_path}")

print("\n所有Mapper文件已修复完成！")

