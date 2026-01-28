@echo off
chcp 65001 >nul
echo ========================================
echo 酒厂生产质量管理系统 - 环境检查脚本
echo ========================================
echo.

echo [1/6] 检查 Java 环境...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java 未安装或未配置到 PATH
    echo    请安装 JDK 1.8 并配置 JAVA_HOME 环境变量
) else (
    echo ✅ Java 已安装
    java -version 2>&1 | findstr "version"
)

echo.
echo [2/6] 检查 Maven 环境...
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Maven 未安装或未配置到 PATH
    echo    请安装 Maven 3.6+ 并配置 MAVEN_HOME 环境变量
) else (
    echo ✅ Maven 已安装
    mvn -version 2>&1 | findstr "Apache Maven"
)

echo.
echo [3/6] 检查 Node.js 环境...
node -v >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Node.js 未安装或未配置到 PATH
    echo    请安装 Node.js 14.x-16.x
) else (
    echo ✅ Node.js 已安装
    node -v
    npm -v
)

echo.
echo [4/6] 检查 MySQL 环境...
mysql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  MySQL 命令行工具未找到（MySQL 可能已安装但未配置到 PATH）
    echo    请确认 MySQL 服务已启动
) else (
    echo ✅ MySQL 已安装
    mysql --version
)

echo.
echo [5/6] 检查端口占用情况...
netstat -ano | findstr ":8080" >nul 2>&1
if %errorlevel% equ 0 (
    echo ⚠️  端口 8080 已被占用（后端端口）
) else (
    echo ✅ 端口 8080 可用（后端）
)

netstat -ano | findstr ":8081" >nul 2>&1
if %errorlevel% equ 0 (
    echo ⚠️  端口 8081 已被占用（前端端口）
) else (
    echo ✅ 端口 8081 可用（前端）
)

netstat -ano | findstr ":3306" >nul 2>&1
if %errorlevel% equ 0 (
    echo ✅ MySQL 服务正在运行（端口 3306）
) else (
    echo ❌ MySQL 服务未运行，请启动 MySQL 服务
)

echo.
echo [6/6] 检查环境变量...
if defined JAVA_HOME (
    echo ✅ JAVA_HOME = %JAVA_HOME%
) else (
    echo ❌ JAVA_HOME 未设置
)

if defined MAVEN_HOME (
    echo ✅ MAVEN_HOME = %MAVEN_HOME%
) else (
    echo ❌ MAVEN_HOME 未设置
)

echo.
echo ========================================
echo 环境检查完成！
echo ========================================
echo.
echo 如果发现 ❌ 标记，请参考 WINDOWS10_ENVIRONMENT_SETUP.md 文档进行配置
echo.
pause
