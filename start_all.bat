@echo off
chcp 65001 >nul
title 酒厂生产质量管理系统 - 启动所有服务
echo ========================================
echo 启动前后端服务
echo ========================================
echo.

echo 提示：将打开两个窗口分别运行前端和后端
echo 请勿关闭这两个窗口
echo.
pause

echo 启动后端服务...
start "后端服务 - 端口8080" cmd /k "%~dp0start_backend.bat"

timeout /t 5 /nobreak >nul

echo 启动前端服务...
start "前端服务 - 端口8081" cmd /k "%~dp0start_frontend.bat"

echo.
echo ========================================
echo 服务启动完成！
echo ========================================
echo.
echo 后端地址: http://localhost:8080
echo 前端地址: http://localhost:8081
echo.
echo 请保持这两个窗口打开，关闭窗口将停止服务
echo.
pause
