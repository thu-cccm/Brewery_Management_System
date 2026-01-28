#!/bin/bash
# 修复所有Vue文件中的语法错误

BASE="/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-ui/src/views/system"

echo "开始修复Vue文件语法错误..."

for file in "$BASE"/*/index.vue; do
    if [ -f "$file" ]; then
        echo "处理: $(basename $(dirname $file))"
        
        # 取消注释 queryParams, form, rules
        sed -i '' 's|^      // queryParams: {$|      queryParams: {|' "$file"
        sed -i '' 's|^      // form: {},$|      form: {},|' "$file"
        sed -i '' 's|^      // rules: {$|      rules: {|' "$file"
    fi
done

echo "✓ 所有Vue文件已修复"

