# Windows 10 快速启动指南

## 📚 文档说明

本项目包含以下 Windows 环境相关文件：

| 文件名 | 说明 |
|--------|------|
| `WINDOWS10_ENVIRONMENT_SETUP.md` | **详细的环境配置文档**（必读） |
| `check_environment.bat` | 环境检查脚本 |
| `start_backend.bat` | 启动后端服务 |
| `start_frontend.bat` | 启动前端服务 |
| `start_all.bat` | 一键启动前后端服务 |

---

## 🚀 快速开始（5步）

### 第1步：检查环境
双击运行 `check_environment.bat`，确保所有 ✅ 标记都正常。

### 第2步：安装必需软件
如果检查脚本显示 ❌，请参考 `WINDOWS10_ENVIRONMENT_SETUP.md` 安装：
- JDK 1.8
- Maven 3.6+
- MySQL 8.0+
- Node.js 14.x-16.x

### 第3步：配置数据库
1. 启动 MySQL 服务
2. 创建数据库：`CREATE DATABASE maple-boot CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;`
3. 导入 SQL 脚本：`sql/ry_20250522.sql`
4. 执行编码修复：`sql/charset_fix.sql`
5. 修改配置文件：`ruoyi-admin/src/main/resources/application-druid.yml`（数据库用户名密码）

### 第4步：安装前端依赖
```bash
cd ruoyi-ui
npm install --legacy-peer-deps
```

### 第5步：启动服务
**方法一：使用脚本（推荐）**
- 双击 `start_all.bat`（自动启动前后端）

**方法二：手动启动**
- 双击 `start_backend.bat`（后端）
- 双击 `start_frontend.bat`（前端）

---

## 🌐 访问地址

启动成功后，访问：
- **前端系统：** http://localhost:8081
- **后端API文档：** http://localhost:8080/swagger-ui/index.html
- **数据库监控：** http://localhost:8080/druid/index.html

---

## 📝 默认账号

- **用户名：** `admin`
- **密码：** `admin123`（或查看数据库）

---

## ⚠️ 常见问题

### 端口被占用
```bash
# 查看端口占用
netstat -ano | findstr :8080
# 结束进程（替换 PID）
taskkill /PID <进程ID> /F
```

### MySQL 连接失败
1. 确认 MySQL 服务已启动
2. 检查 `application-druid.yml` 中的数据库配置
3. 确认数据库 `maple-boot` 已创建

### 前端依赖安装失败
```bash
cd ruoyi-ui
npm config set registry https://registry.npmmirror.com
npm install --legacy-peer-deps
```

---

## 📖 详细文档

遇到问题请查看：**`WINDOWS10_ENVIRONMENT_SETUP.md`**

---

**祝使用愉快！** 🎉
