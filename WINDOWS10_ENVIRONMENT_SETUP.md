# Windows 10 环境配置指南
## 酒厂生产质量管理系统 - 运行环境要求

---

## 📋 目录
1. [系统要求](#系统要求)
2. [必需软件安装](#必需软件安装)
3. [数据库配置](#数据库配置)
4. [项目配置](#项目配置)
5. [启动步骤](#启动步骤)
6. [常见问题](#常见问题)

---

## 🖥️ 系统要求

### 操作系统
- **Windows 10** (64位) - 推荐版本 1903 或更高
- 至少 **8GB RAM** (推荐 16GB)
- 至少 **10GB** 可用磁盘空间

### 网络要求
- 需要访问互联网以下载依赖包（Maven、npm）
- 本地开发端口：`8080`（后端）、`8081`（前端）

---

## 📦 必需软件安装

### 1. JDK 1.8 (Java Development Kit)

#### 下载地址
- Oracle JDK: https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
- OpenJDK: https://adoptium.net/temurin/releases/?version=8

#### 安装步骤
1. 下载 Windows x64 版本的 JDK 1.8（如：`jdk-8u391-windows-x64.exe`）
2. 运行安装程序，**记住安装路径**（如：`C:\Program Files\Java\jdk1.8.0_391`）
3. 安装完成后配置环境变量：
   - 右键"此电脑" → "属性" → "高级系统设置" → "环境变量"
   - 在"系统变量"中新建：
     - 变量名：`JAVA_HOME`
     - 变量值：`C:\Program Files\Java\jdk1.8.0_391`（你的JDK安装路径）
   - 编辑"系统变量"中的 `Path`，添加：
     - `%JAVA_HOME%\bin`
     - `%JAVA_HOME%\jre\bin`

#### 验证安装
打开命令提示符（CMD）或 PowerShell，执行：
```bash
java -version
javac -version
```
应显示类似：
```
java version "1.8.0_391"
Java(TM) SE Runtime Environment (build 1.8.0_391-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.391-b13, mixed mode)
```

---

### 2. Maven 3.6+ (项目构建工具)

#### 下载地址
- Apache Maven: https://maven.apache.org/download.cgi
- 下载 `apache-maven-3.9.5-bin.zip`（或最新稳定版）

#### 安装步骤
1. 解压到目录（如：`C:\Program Files\Apache\maven`）
2. 配置环境变量：
   - 新建系统变量：
     - 变量名：`MAVEN_HOME`
     - 变量值：`C:\Program Files\Apache\maven`（你的Maven解压路径）
   - 编辑 `Path`，添加：
     - `%MAVEN_HOME%\bin`

#### 配置 Maven 镜像（加速下载）
编辑 `C:\Program Files\Apache\maven\conf\settings.xml`，在 `<mirrors>` 标签内添加：
```xml
<mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*</mirrorOf>
  <name>阿里云公共仓库</name>
  <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

#### 验证安装
```bash
mvn -version
```
应显示 Maven 版本和 Java 版本信息。

---

### 3. MySQL 8.0+ (数据库)

#### 下载地址
- MySQL Community Server: https://dev.mysql.com/downloads/mysql/
- 选择 Windows (x86, 64-bit) 版本
- 推荐下载 MySQL Installer（包含 MySQL Server 和 MySQL Workbench）

#### 安装步骤
1. 运行 MySQL Installer（如：`mysql-installer-community-8.0.xx.x.msi`）
2. 选择安装类型：**Developer Default**（包含 Server、Workbench、Shell 等）
3. 配置 MySQL Server：
   - 端口：`3306`（默认）
   - Root 密码：设置一个强密码（**记住这个密码**，后续配置需要）
   - 字符集：选择 **utf8mb4**
   - 排序规则：选择 **utf8mb4_general_ci**
4. 完成安装后，启动 MySQL 服务：
   - 打开"服务"（services.msc）
   - 找到 `MySQL80`，确保状态为"正在运行"

#### 创建数据库
打开 MySQL Workbench 或命令行，执行：
```sql
CREATE DATABASE `maple-boot` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

#### 验证安装
```bash
mysql --version
```
或登录 MySQL：
```bash
mysql -u root -p
```
输入 root 密码后应能成功连接。

---

### 4. Node.js 14.x - 16.x (前端运行环境)

**⚠️ 重要：** 项目要求 Node.js >= 8.9，但推荐使用 **14.x 或 16.x** 版本（避免兼容性问题）

#### 下载地址
- Node.js LTS: https://nodejs.org/zh-cn/download/
- 推荐下载：**Node.js 16.20.2 LTS** (Windows x64 Installer)

#### 安装步骤
1. 运行安装程序（如：`node-v16.20.2-x64.msi`）
2. 安装过程中：
   - ✅ 勾选 "Add to PATH"
   - ✅ 勾选 "Install npm package manager"
3. 完成安装

#### 配置 npm 镜像（加速下载）
打开命令提示符，执行：
```bash
npm config set registry https://registry.npmmirror.com
```

验证镜像：
```bash
npm config get registry
```

#### 验证安装
```bash
node -v
npm -v
```
应显示：
```
v16.20.2
8.19.4
```

---

### 5. Git (可选，用于版本控制)

#### 下载地址
- Git for Windows: https://git-scm.com/download/win

#### 安装步骤
1. 运行安装程序，使用默认选项即可
2. 验证安装：
```bash
git --version
```

---

## 🗄️ 数据库配置

### 1. 导入数据库结构

#### 方法一：使用 MySQL Workbench
1. 打开 MySQL Workbench
2. 连接到本地 MySQL 服务器（root 用户）
3. 打开 SQL 脚本文件：
   - `sql/ry_20250522.sql`（基础系统表）
   - `sql/brewery_system_v2.sql`（业务表，如果存在）
4. 执行脚本（点击 ⚡ 按钮）

#### 方法二：使用命令行
```bash
cd C:\path\to\Brewery_Management_System\sql
mysql -u root -p maple-boot < ry_20250522.sql
```
如果存在业务表脚本：
```bash
mysql -u root -p maple-boot < brewery_system_v2.sql
```

### 2. 修复数据库编码（重要！）

执行编码修复脚本：
```bash
mysql -u root -p maple-boot < sql/charset_fix.sql
```

或手动执行 SQL：
```sql
USE `maple-boot`;
SET NAMES utf8mb4;
ALTER DATABASE `maple-boot` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_user CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_dict_type CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_dict_data CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_dept CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

### 3. 配置数据库连接

编辑文件：`ruoyi-admin/src/main/resources/application-druid.yml`

修改以下配置：
```yaml
spring:
    datasource:
        druid:
            master:
                url: jdbc:mysql://localhost:3306/maple-boot?useUnicode=true&characterEncoding=utf8mb4&characterSetResults=utf8mb4&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
                username: admin          # 改为你的MySQL用户名
                password: admin@120      # 改为你的MySQL密码
```

---

## ⚙️ 项目配置

### 1. 后端配置

#### 检查配置文件
- `ruoyi-admin/src/main/resources/application.yml`
  - 确认端口：`8080`
  - 确认文件上传路径（Windows路径格式）：
    ```yaml
    jiuchang:
      profile: D:/jiuchang/uploadPath  # Windows路径格式
    ```

- `ruoyi-admin/src/main/resources/application-druid.yml`
  - 确认数据库连接信息

### 2. 前端配置

#### 检查环境变量文件
- `ruoyi-ui/.env.development`
  ```env
  VUE_APP_TITLE = 酒厂生产质量管理系统
  VUE_APP_BASE_API = /dev-api
  ```

#### 安装前端依赖
```bash
cd ruoyi-ui
npm install
```
**注意：** 如果安装失败，尝试：
```bash
npm install --legacy-peer-deps
```

---

## 🚀 启动步骤

### 1. 启动 MySQL 服务

#### 方法一：服务管理器
- 按 `Win + R`，输入 `services.msc`
- 找到 `MySQL80`，右键 → "启动"

#### 方法二：命令行
```bash
net start MySQL80
```

### 2. 启动后端服务

打开命令提示符或 PowerShell，执行：
```bash
cd C:\path\to\Brewery_Management_System\ruoyi-admin
mvn spring-boot:run
```

**或者使用 IDE（推荐）：**
- 使用 IntelliJ IDEA 或 Eclipse
- 打开项目根目录（包含 `pom.xml` 的目录）
- 找到 `ruoyi-admin/src/main/java/com/jiuchang/JiuChangApplication.java`
- 右键 → "Run 'JiuChangApplication.main()'"

**等待启动完成**，看到以下信息表示成功：
```
(♥◠‿◠)ﾉﾞ  酒厂生产质量管理系统启动成功   ლ(´ڡ`ლ)ﾞ
```

### 3. 启动前端服务

打开**新的**命令提示符窗口，执行：
```bash
cd C:\path\to\Brewery_Management_System\ruoyi-ui
npm run dev
```

等待编译完成，看到以下信息表示成功：
```
App running at:
- Local:   http://localhost:8081/
- Network: http://192.168.x.x:8081/
```

### 4. 访问系统

打开浏览器，访问：
- **前端地址：** http://localhost:8081
- **后端API文档：** http://localhost:8080/swagger-ui/index.html
- **Druid监控：** http://localhost:8080/druid/index.html
  - 用户名：`jiuchang`
  - 密码：`123456`

### 5. 默认登录账号

- **用户名：** `admin`
- **密码：** `admin123`（或查看数据库 `sys_user` 表中的实际密码）

---

## 🔧 常见问题

### Q1: Java 版本不匹配
**错误：** `UnsupportedClassVersionError`
**解决：** 确保使用 JDK 1.8，检查 `JAVA_HOME` 环境变量

### Q2: Maven 下载依赖失败
**解决：** 
1. 检查网络连接
2. 配置阿里云 Maven 镜像（见上文）
3. 清理 Maven 缓存：`mvn clean`

### Q3: MySQL 连接失败
**错误：** `Communications link failure`
**解决：**
1. 确认 MySQL 服务已启动
2. 检查端口 3306 是否被占用
3. 确认数据库用户名和密码正确
4. 检查防火墙设置

### Q4: 前端 npm install 失败
**解决：**
1. 删除 `node_modules` 文件夹和 `package-lock.json`
2. 使用国内镜像：`npm config set registry https://registry.npmmirror.com`
3. 重新安装：`npm install --legacy-peer-deps`

### Q5: 端口被占用
**错误：** `Port 8080 is already in use`
**解决：**
1. 查找占用端口的进程：
   ```bash
   netstat -ano | findstr :8080
   ```
2. 结束进程（替换 PID）：
   ```bash
   taskkill /PID <进程ID> /F
   ```
3. 或修改 `application.yml` 中的端口号

### Q6: 中文乱码问题
**解决：**
1. 确保数据库使用 `utf8mb4` 字符集
2. 执行 `sql/charset_fix.sql` 脚本
3. 检查 `application.yml` 中的编码配置
4. 重启后端服务

### Q7: 前端页面空白
**解决：**
1. 检查浏览器控制台错误（F12）
2. 确认后端服务已启动
3. 检查 `vue.config.js` 中的代理配置
4. 清除浏览器缓存

---

## 📝 环境变量检查清单

在 Windows 10 上，确保以下环境变量已正确配置：

| 变量名 | 示例值 | 说明 |
|--------|--------|------|
| `JAVA_HOME` | `C:\Program Files\Java\jdk1.8.0_391` | JDK 安装路径 |
| `MAVEN_HOME` | `C:\Program Files\Apache\maven` | Maven 安装路径 |
| `Path` | 包含 `%JAVA_HOME%\bin` 和 `%MAVEN_HOME%\bin` | 系统路径 |

**检查方法：**
```bash
echo %JAVA_HOME%
echo %MAVEN_HOME%
java -version
mvn -version
node -v
npm -v
mysql --version
```

---

## 📞 技术支持

如遇到问题，请检查：
1. 所有必需软件版本是否符合要求
2. 环境变量是否正确配置
3. 数据库连接配置是否正确
4. 端口是否被占用
5. 防火墙是否阻止了端口访问

---

## ✅ 安装验证清单

完成所有配置后，请验证：

- [ ] JDK 1.8 安装并配置环境变量
- [ ] Maven 3.6+ 安装并配置环境变量
- [ ] MySQL 8.0+ 安装并创建数据库
- [ ] Node.js 14.x-16.x 安装并配置 npm 镜像
- [ ] 数据库导入成功
- [ ] 数据库编码修复脚本已执行
- [ ] 后端配置文件已修改（数据库连接）
- [ ] 前端依赖已安装（npm install）
- [ ] MySQL 服务已启动
- [ ] 后端服务启动成功（端口 8080）
- [ ] 前端服务启动成功（端口 8081）
- [ ] 浏览器可以访问系统

---

**最后更新：** 2026-01-27
**适用版本：** 酒厂生产质量管理系统 v3.9.1
