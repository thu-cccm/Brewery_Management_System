@echo off
chcp 65001 >nul
title 酒厂生产质量管理系统 - 前端服务
echo ========================================
echo 启动前端服务...
echo ========================================
echo.

cd /d %~dp0ruoyi-ui

echo 检查依赖是否已安装...
if not exist "node_modules" (
    echo 正在安装前端依赖，请稍候...
    call npm install --legacy-peer-deps
    echo.
)

echo 正在启动前端服务，请稍候...
echo 服务地址: http://localhost:8081
echo 按 Ctrl+C 可停止服务
echo.

call npm run dev

pause
