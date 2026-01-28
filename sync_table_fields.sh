#!/bin/bash

# 同步所有brew表的字段信息

BASE_URL="http://localhost:8080"

# 登录获取token
echo "==> 登录获取token..."
LOGIN_RESPONSE=$(curl -s -X POST "${BASE_URL}/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}')

TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*"' | sed 's/"token":"\([^"]*\)"/\1/')

if [ -z "$TOKEN" ]; then
    echo "✗ 登录失败"
    echo "$LOGIN_RESPONSE"
    exit 1
fi

echo "✓ 登录成功！Token: ${TOKEN:0:20}..."

# 定义表名数组
TABLES=(
    "brew_material"
    "brew_wine_category"
    "brew_formula_template"
    "brew_formula_detail"
    "brew_stock_record"
    "brew_production_plan"
    "brew_production_batch"
    "brew_batch_material"
    "brew_production_process"
    "brew_quality_standard"
    "brew_quality_record"
    "brew_quality_report"
    "brew_cost_item"
    "brew_batch_cost"
    "brew_cost_statistics"
)

echo ""
echo "==> 开始同步表字段..."

for table in "${TABLES[@]}"; do
    echo "同步 $table ..."
    
    SYNC_RESPONSE=$(curl -s -X GET "${BASE_URL}/tool/gen/synchDb/${table}" \
      -H "Authorization: Bearer $TOKEN")
    
    CODE=$(echo $SYNC_RESPONSE | grep -o '"code":[0-9]*' | sed 's/"code"://')
    
    if [ "$CODE" = "200" ]; then
        echo "  ✓ $table 同步成功"
    else
        echo "  ✗ $table 同步失败: $SYNC_RESPONSE"
    fi
    
    sleep 0.5
done

echo ""
echo "==> 所有表字段同步完成！"
echo ""
echo "现在可以重新生成代码了："
echo "  bash generate_code_curl.sh"

