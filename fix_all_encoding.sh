#!/bin/bash
# 修复所有生成代码中的中文乱码

BASE_DIR="/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System"

echo "开始修复中文乱码..."

# 修复Java文件
echo "修复Java文件..."
find "$BASE_DIR/ruoyi-admin/src/main/java/com/jiuchang/system" -name "*Brew*.java" -type f | while read file; do
    # 原料信息
    sed -i '' 's/åŽŸæ–™ä¿¡æ¯/原料信息/g' "$file"
    
    # 酒品种类
    sed -i '' 's/é…'\''å"ç§ç±»/酒品种类/g' "$file"
    
    # 配方模板
    sed -i '' 's/é…'\''æ–¹æ¨¡æ¿¿/配方模板/g' "$file"
    
    # 配方明细
    sed -i '' 's/é…'\''æ–¹æ˜Žç»†/配方明细/g' "$file"
    
    # 库存变动记录
    sed -i '' 's/åº"å˜å˜åŠ¨è®°å½•/库存变动记录/g' "$file"
    
    # 生产计划
    sed -i '' 's/ç"Ÿäº§è®¡å'\''/生产计划/g' "$file"
    
    # 生产批次
    sed -i '' 's/ç"Ÿäº§æ‰¹æ¬¡/生产批次/g' "$file"
    
    # 批次原料使用记录
    sed -i '' 's/æ‰¹æ¬¡åŽŸæ–™ä½¿ç"¨è®°å½•/批次原料使用记录/g' "$file"
    
    # 生产过程记录
    sed -i '' 's/ç"Ÿäº§è¿‡ç¨‹è®°å½•/生产过程记录/g' "$file"
    
    # 质检标准
    sed -i '' 's/è´¨æ£€æ ‡å‡†/质检标准/g' "$file"
    
    # 质检记录
    sed -i '' 's/è´¨æ£€è®°å½•/质检记录/g' "$file"
    
    # 质检报告
    sed -i '' 's/è´¨æ£€æŠ¥å'\''/质检报告/g' "$file"
    
    # 成本项配置
    sed -i '' 's/æˆæœ¬é¡¹é…„ç½®/成本项配置/g' "$file"
    
    # 批次成本明细
    sed -i '' 's/æ‰¹æ¬¡æˆæœ¬æ˜Žç»†/批次成本明细/g' "$file"
    
    # 成本统计
    sed -i '' 's/æˆæœ¬ç»Ÿè®¡/成本统计/g' "$file"
    
    # 通用替换
    sed -i '' 's/åˆ—è¡¨/列表/g' "$file"
    sed -i '' 's/åŠ é"/新增/g' "$file"
    sed -i '' 's/ä¿®æ"¹/修改/g' "$file"
    sed -i '' 's/åˆ é"/删除/g' "$file"
    sed -i '' 's/å¯¼å‡º/导出/g' "$file"
    sed -i '' 's/æŸ¥è¯¢/查询/g' "$file"
    sed -i '' 's/è¡¨/表/g' "$file"
    sed -i '' 's/æ•°æ®/数据/g' "$file"
done

echo "✓ Java文件修复完成"

# 修复Vue文件
echo "修复Vue文件..."
find "$BASE_DIR/ruoyi-ui/src/views/system" -name "*.vue" -type f | while read file; do
    # 同样的替换
    sed -i '' 's/åŽŸæ–™ä¿¡æ¯/原料信息/g' "$file"
    sed -i '' 's/é…'\''å"ç§ç±»/酒品种类/g' "$file"
    sed -i '' 's/é…'\''æ–¹æ¨¡æ¿¿/配方模板/g' "$file"
    sed -i '' 's/é…'\''æ–¹æ˜Žç»†/配方明细/g' "$file"
    sed -i '' 's/åº"å˜å˜åŠ¨è®°å½•/库存变动记录/g' "$file"
    sed -i '' 's/ç"Ÿäº§è®¡å'\''/生产计划/g' "$file"
    sed -i '' 's/ç"Ÿäº§æ‰¹æ¬¡/生产批次/g' "$file"
    sed -i '' 's/æ‰¹æ¬¡åŽŸæ–™ä½¿ç"¨è®°å½•/批次原料使用记录/g' "$file"
    sed -i '' 's/ç"Ÿäº§è¿‡ç¨‹è®°å½•/生产过程记录/g' "$file"
    sed -i '' 's/è´¨æ£€æ ‡å‡†/质检标准/g' "$file"
    sed -i '' 's/è´¨æ£€è®°å½•/质检记录/g' "$file"
    sed -i '' 's/è´¨æ£€æŠ¥å'\''/质检报告/g' "$file"
    sed -i '' 's/æˆæœ¬é¡¹é…„ç½®/成本项配置/g' "$file"
    sed -i '' 's/æ‰¹æ¬¡æˆæœ¬æ˜Žç»†/批次成本明细/g' "$file"
    sed -i '' 's/æˆæœ¬ç»Ÿè®¡/成本统计/g' "$file"
done

echo "✓ Vue文件修复完成"

# 修复JS文件
echo "修复JS文件..."
find "$BASE_DIR/ruoyi-ui/src/api/system" -name "*.js" -type f | while read file; do
    # 同样的替换
    sed -i '' 's/åŽŸæ–™ä¿¡æ¯/原料信息/g' "$file"
    sed -i '' 's/é…'\''å"ç§ç±»/酒品种类/g' "$file"
    sed -i '' 's/é…'\''æ–¹æ¨¡æ¿¿/配方模板/g' "$file"
    sed -i '' 's/é…'\''æ–¹æ˜Žç»†/配方明细/g' "$file"
    sed -i '' 's/åº"å˜å˜åŠ¨è®°å½•/库存变动记录/g' "$file"
    sed -i '' 's/ç"Ÿäº§è®¡å'\''/生产计划/g' "$file"
    sed -i '' 's/ç"Ÿäº§æ‰¹æ¬¡/生产批次/g' "$file"
    sed -i '' 's/æ‰¹æ¬¡åŽŸæ–™ä½¿ç"¨è®°å½•/批次原料使用记录/g' "$file"
    sed -i '' 's/ç"Ÿäº§è¿‡ç¨‹è®°å½•/生产过程记录/g' "$file"
    sed -i '' 's/è´¨æ£€æ ‡å‡†/质检标准/g' "$file"
    sed -i '' 's/è´¨æ£€è®°å½•/质检记录/g' "$file"
    sed -i '' 's/è´¨æ£€æŠ¥å'\''/质检报告/g' "$file"
    sed -i '' 's/æˆæœ¬é¡¹é…„ç½®/成本项配置/g' "$file"
    sed -i '' 's/æ‰¹æ¬¡æˆæœ¬æ˜Žç»†/批次成本明细/g' "$file"
    sed -i '' 's/æˆæœ¬ç»Ÿè®¡/成本统计/g' "$file"
done

echo "✓ JS文件修复完成"

echo ""
echo "所有文件修复完成！"

