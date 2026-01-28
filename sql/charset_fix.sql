-- Fix UTF-8 encoding for existing data.
-- Make sure the database name matches your environment.
SET NAMES utf8mb4;

ALTER DATABASE `maple-boot` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

ALTER TABLE sys_user CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_dict_type CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_dict_data CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE sys_dept CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
