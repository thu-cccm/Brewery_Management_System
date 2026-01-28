@echo off
chcp 65001 >nul
title 酒厂生产质量管理系统 - 后端服务
echo ========================================
echo 启动后端服务...
echo ========================================
echo.

cd /d %~dp0ruoyi-admin

echo 正在启动后端服务，请稍候...
echo 服务地址: http://localhost:8080
echo 按 Ctrl+C 可停止服务
echo.

mvn spring-boot:run

pause
