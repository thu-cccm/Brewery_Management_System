#!/bin/bash

# 若依代码生成 - 使用curl直接调用API
# 首先需要登录获取token

BASE_URL="http://localhost:8080"

# 第1步：获取验证码信息
echo "==> 获取验证码..."
CAPTCHA_RESPONSE=$(curl -s -X GET "${BASE_URL}/captchaImage")
echo "验证码响应: $CAPTCHA_RESPONSE"

# 第2步：尝试登录（验证码已禁用）
echo ""
echo "==> 尝试登录..."
LOGIN_RESPONSE=$(curl -s -X POST "${BASE_URL}/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123","code":"","uuid":""}')

echo "登录响应: $LOGIN_RESPONSE"

# 提取token
TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*"' | sed 's/"token":"\([^"]*\)"/\1/')

if [ -z "$TOKEN" ]; then
    echo "✗ 登录失败，无法获取token"
    echo "请手动登录前端获取token，然后执行："
    echo "  export RUOYI_TOKEN='你的token'"
    echo "  $0"
    exit 1
fi

echo "✓ 登录成功！Token: ${TOKEN:0:20}..."

# 第3步：获取表列表
echo ""
echo "==> 获取代码生成表列表..."
TABLES_RESPONSE=$(curl -s -X GET "${BASE_URL}/tool/gen/list?pageNum=1&pageSize=100" \
  -H "Authorization: Bearer $TOKEN")

echo "表列表响应: $TABLES_RESPONSE"

# 第4步：批量生成代码
# 提取所有brew_开头的表名
TABLE_NAMES="brew_material,brew_wine_category,brew_formula_template,brew_formula_detail,brew_stock_record,brew_production_plan,brew_production_batch,brew_batch_material,brew_production_process,brew_quality_standard,brew_quality_record,brew_quality_report,brew_cost_item,brew_batch_cost,brew_cost_statistics"

echo ""
echo "==> 批量生成代码..."
echo "表名: $TABLE_NAMES"

curl -X GET "${BASE_URL}/tool/gen/batchGenCode?tables=${TABLE_NAMES}" \
  -H "Authorization: Bearer $TOKEN" \
  -o generated_code.zip

if [ -f "generated_code.zip" ]; then
    FILE_SIZE=$(wc -c < "generated_code.zip")
    if [ "$FILE_SIZE" -gt 1000 ]; then
        echo "✓ 代码生成成功！文件大小: $FILE_SIZE 字节"
        echo "✓ 代码已保存到: generated_code.zip"
        
        # 解压
        echo ""
        echo "==> 解压代码..."
        unzip -o generated_code.zip -d generated_code/
        
        echo "✓ 代码已解压到: generated_code/"
        echo ""
        echo "后续步骤："
        echo "1. 查看生成的代码: ls -la generated_code/"
        echo "2. 复制到项目目录"
        echo "3. 重启后端和前端服务"
    else
        echo "✗ 生成的文件太小，可能生成失败"
        cat generated_code.zip
    fi
else
    echo "✗ 代码生成失败"
fi

