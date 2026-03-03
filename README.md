# Brewery Management System (JiuChang)

Based on the [RuoYi](http://www.ruoyi.vip) framework, this is a Brewery Management System.

## Project Structure

*   `ruoyi-admin`: Web application entry point (contains `JiuChangApplication`).
*   `ruoyi-common`: Common utilities.
*   `ruoyi-framework`: Core framework configuration.
*   `ruoyi-system`: System business logic and domain models.
*   `ruoyi-generator`: Code generation module.
*   `sql`: Database initialization scripts.

## Prerequisites

*   **Java**: JDK 1.8
*   **Maven**: 3.x
*   **Database**: MySQL 8.0+
*   **Cache**: Redis

## Configuration

### Database
Configure your MySQL connection in `ruoyi-admin/src/main/resources/application-druid.yml`.
*   Default URL: `jdbc:mysql://127.0.0.1:3306/maple-boot`
*   Default User: `admin`
*   Default Password: `admin@120`

### Redis
Configure Redis in `ruoyi-admin/src/main/resources/application.yml`.
*   Default Host: `localhost`
*   Default Port: `6379`

## Build and Run

1.  **Build the project**:
    ```bash
    mvn clean install -DskipTests
    ```

2.  **Initialize Database**:
    Execute the SQL scripts found in the `sql/` directory to create the database schema and initial data. 
    *   Likely start with `ry_20250522.sql` or `brewery_system.sql`.

3.  **Run the application**:
    Run the main class `com.jiuchang.JiuChangApplication` in the `ruoyi-admin` module.
    Or via Maven:
    ```bash
    cd ruoyi-admin
    mvn spring-boot:run
    ```

## Notes

*   **Tests**: The project currently does not have unit tests in the standard `src/test` directories.
*   **Port**: The application runs on port `8092` by default (see `application.yml`).
