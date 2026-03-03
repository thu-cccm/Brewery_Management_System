# =============================================================================
# 阶段一：Dockerfile 基础教学
# 这个文件就像一个“菜谱”，告诉 Docker 如何一步步“烹饪”出我们的应用镜像。
# =============================================================================

# 1. 基础镜像 (FROM)
# 想象我们在买一台新电脑，我们需要先装操作系统和Java环境。
# 既然您有VPN，且国内镜像源不稳定（403 Forbidden），我们使用更可靠的 Amazon Corretto JDK 镜像。
# 或者，如果您配置了Docker走VPN，可以直接使用 docker.io/library/openjdk:8-jdk-alpine
FROM amazoncorretto:8-alpine-jdk

# 2. 维护者信息 (LABEL) - 可选
# 告诉别人这个镜像是谁做的。
LABEL maintainer="BreweryLearner"

# 3. 设置工作目录 (WORKDIR)
# 相当于在终端执行了 `cd /app`。
# 接下来的所有命令都会在这个目录下执行。
WORKDIR /app

# 4. 复制文件 (COPY)
# 格式：COPY <本地文件路径> <镜像内路径>
# 我们把本地编译好的 jar 包复制到镜像里的 /app 目录下，并重命名为 app.jar。
# 注意：这里假设你已经在本地运行了 `mvn clean install` 生成了 target 目录。
COPY target/jiuchang-admin.jar app.jar

# 5. 暴露端口 (EXPOSE) - 仅作为声明
# 告诉使用者这个应用默认在 8092 端口提供服务。
# 这只是一个文档作用，真正开放端口需要在运行容器时使用 -p 参数。
EXPOSE 8092

# 6. 启动命令 (ENTRYPOINT)
# 容器启动时自动执行的命令。
# 相当于我们在终端输入：java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
