#!/bin/bash

# 修复所有Mapper文件中的模板变量

cd /Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/java/com/jiuchang/system/mapper

# 定义表名和主键的映射
declare -A pk_map
pk_map["BrewBatchCost"]="costId:CostId"
pk_map["BrewBatchMaterial"]="recordId:RecordId"
pk_map["BrewCostItem"]="itemId:ItemId"
pk_map["BrewCostStatistics"]="statId:StatId"
pk_map["BrewFormulaDetail"]="detailId:DetailId"
pk_map["BrewFormulaTemplate"]="templateId:TemplateId"
pk_map["BrewMaterial"]="materialId:MaterialId"
pk_map["BrewProductionBatch"]="batchId:BatchId"
pk_map["BrewProductionPlan"]="planId:PlanId"
pk_map["BrewProductionProcess"]="processId:ProcessId"
pk_map["BrewQualityRecord"]="recordId:RecordId"
pk_map["BrewQualityReport"]="reportId:ReportId"
pk_map["BrewQualityStandard"]="standardId:StandardId"
pk_map["BrewStockRecord"]="recordId:RecordId"
pk_map["BrewWineCategory"]="categoryId:CategoryId"

for class_name in "${!pk_map[@]}"; do
    file="${class_name}Mapper.java"
    if [ -f "$file" ]; then
        IFS=':' read -r java_field cap_java_field <<< "${pk_map[$class_name]}"
        
        echo "修复 $file: $java_field -> $cap_java_field"
        
        # 替换 ${pkColumn.javaField} 为实际字段名（小写开头）
        sed -i '' "s/\${pkColumn.javaField}/$java_field/g" "$file"
        
        # 替换 ${pkColumn.capJavaField} 为实际字段名（大写开头）
        sed -i '' "s/\${pkColumn.capJavaField}/$cap_java_field/g" "$file"
        
        # 替换 ${pkColumn.javaType} 为 Long
        sed -i '' "s/\${pkColumn.javaType}/Long/g" "$file"
        
        echo "✓ $file 已修复"
    fi
done

echo ""
echo "所有Mapper文件已修复完成！"

