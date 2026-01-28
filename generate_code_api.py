#!/usr/bin/env python3
"""
若依代码生成器 API 调用脚本
通过后端API自动生成所有业务表的代码
"""

import urllib.request
import urllib.parse
import urllib.error
import json
import sys
import time
import os
from typing import Optional
from http.cookiejar import CookieJar

# 配置
BASE_URL = "http://localhost:8080"
USERNAME = "admin"
PASSWORD = "admin123"

# 需要生成代码的表名列表
BREW_TABLES = [
    "brew_production_plan",      # 生产计划
    "brew_batch",                 # 生产批次
    "brew_batch_material_usage",  # 批次原料使用
    "brew_process_record",        # 生产过程记录
    "brew_quality_inspection",    # 质量检测
    "brew_warehouse",             # 仓库
    "brew_inventory",             # 库存
    "brew_inventory_transaction", # 库存事务
    "brew_supplier",              # 供应商
    "brew_material_procurement",  # 原料采购
    "brew_cost_item",             # 成本项目
    "brew_batch_cost",            # 批次成本
    "brew_product_sale",          # 产品销售
    "brew_customer",              # 客户
    "brew_equipment"              # 生产设备
]


class RuoYiClient:
    """若依框架API客户端"""
    
    def __init__(self, base_url: str, username: str, password: str):
        self.base_url = base_url.rstrip('/')
        self.username = username
        self.password = password
        self.cookie_jar = CookieJar()
        self.opener = urllib.request.build_opener(urllib.request.HTTPCookieProcessor(self.cookie_jar))
        self.token: Optional[str] = None
        self.headers = {}
        
    def _request(self, url: str, method: str = "GET", data: dict = None, is_json: bool = True):
        """发送HTTP请求"""
        headers = {'Content-Type': 'application/json'} if is_json else {}
        if self.token:
            headers['Authorization'] = f'Bearer {self.token}'
        
        if data and is_json:
            data = json.dumps(data).encode('utf-8')
        elif data:
            data = urllib.parse.urlencode(data).encode('utf-8')
        
        req = urllib.request.Request(url, data=data, headers=headers, method=method)
        
        try:
            with self.opener.open(req) as response:
                return response.read().decode('utf-8'), response.status
        except urllib.error.HTTPError as e:
            return e.read().decode('utf-8'), e.code
        except Exception as e:
            return str(e), 0
    
    def login(self) -> bool:
        """登录获取token"""
        print(f"正在登录... 用户名: {self.username}")
        print(f"\n提示: 若依框架默认启用验证码，需要先在后台关闭验证码功能")
        print(f"      或者使用前端页面手动登录后，在浏览器开发者工具中获取token")
        print(f"\n尝试方法1: 不使用验证码直接登录...")
        
        # 方法1: 尝试不带验证码登录
        login_url = f"{self.base_url}/login"
        login_data = {
            "username": self.username,
            "password": self.password
        }
        
        try:
            resp_text, status = self._request(login_url, "POST", login_data, is_json=True)
            if status == 200:
                result = json.loads(resp_text)
                if result.get("code") == 200:
                    self.token = result.get("token")
                    print(f"✓ 登录成功！Token: {self.token[:20]}...")
                    return True
                else:
                    msg = result.get('msg', '')
                    print(f"✗ 方法1失败: {msg}")
                    
                    # 如果提示验证码相关错误
                    if "验证码" in msg or "captcha" in msg.lower():
                        print(f"\n尝试方法2: 获取验证码...")
                        return self._login_with_captcha()
                    return False
            else:
                print(f"✗ 登录请求失败: HTTP {status}")
                return False
        except Exception as e:
            print(f"✗ 登录异常: {e}")
            return False
    
    def _login_with_captcha(self) -> bool:
        """使用验证码登录"""
        # 获取验证码
        captcha_url = f"{self.base_url}/captchaImage"
        try:
            resp_text, status = self._request(captcha_url, "GET")
            if status == 200:
                captcha_data = json.loads(resp_text)
                captcha_enabled = captcha_data.get("captchaEnabled", False)
                uuid = captcha_data.get("uuid", "")
                img_data = captcha_data.get("img", "")
                
                if not captcha_enabled:
                    print("✗ 验证码功能未启用，但登录失败")
                    return False
                
                print(f"\n验证码UUID: {uuid}")
                print(f"验证码图片数据长度: {len(img_data)}")
                print(f"\n⚠️  无法自动识别验证码，请手动完成以下步骤：")
                print(f"1. 在浏览器中打开: {self.base_url}")
                print(f"2. 使用 admin/admin123 登录")
                print(f"3. 打开浏览器开发者工具 (F12)")
                print(f"4. 在 Application -> Local Storage 中找到 token")
                print(f"5. 将token设置为环境变量: export RUOYI_TOKEN='你的token'")
                print(f"6. 重新运行本脚本")
                
                return False
            else:
                print(f"✗ 获取验证码失败: HTTP {status}")
                return False
        except Exception as e:
            print(f"✗ 获取验证码异常: {e}")
            return False
    
    def get_table_list(self) -> list:
        """获取代码生成表列表"""
        params = urllib.parse.urlencode({"pageNum": 1, "pageSize": 100})
        url = f"{self.base_url}/tool/gen/list?{params}"
        
        try:
            resp_text, status = self._request(url, "GET")
            if status == 200:
                result = json.loads(resp_text)
                if result.get("code") == 200:
                    rows = result.get("rows", [])
                    print(f"✓ 获取到 {len(rows)} 个表")
                    return rows
                else:
                    print(f"✗ 获取表列表失败: {result.get('msg')}")
                    return []
            else:
                print(f"✗ 获取表列表请求失败: HTTP {status}")
                return []
        except Exception as e:
            print(f"✗ 获取表列表异常: {e}")
            return []
    
    def update_table_config(self, table_id: int, config: dict) -> bool:
        """更新表配置"""
        url = f"{self.base_url}/tool/gen"
        
        try:
            resp_text, status = self._request(url, "PUT", config, is_json=True)
            if status == 200:
                result = json.loads(resp_text)
                if result.get("code") == 200:
                    return True
                else:
                    print(f"✗ 更新表配置失败: {result.get('msg')}")
                    return False
            else:
                print(f"✗ 更新表配置请求失败: HTTP {status}")
                return False
        except Exception as e:
            print(f"✗ 更新表配置异常: {e}")
            return False
    
    def generate_code(self, table_name: str) -> bool:
        """生成单个表的代码（生成到自定义路径）"""
        url = f"{self.base_url}/tool/gen/genCode/{table_name}"
        
        try:
            resp_text, status = self._request(url, "GET")
            if status == 200:
                result = json.loads(resp_text)
                if result.get("code") == 200:
                    print(f"  ✓ 代码已生成到自定义路径")
                    return True
                else:
                    print(f"  ✗ 生成代码失败: {result.get('msg')}")
                    return False
            else:
                print(f"  ✗ 生成代码请求失败: HTTP {status}")
                return False
        except Exception as e:
            print(f"  ✗ 生成代码异常: {e}")
            return False
    
    def batch_generate_code(self, table_names: list) -> bool:
        """批量生成代码（下载zip）"""
        tables_str = ",".join(table_names)
        params = urllib.parse.urlencode({"tables": tables_str})
        url = f"{self.base_url}/tool/gen/batchGenCode?{params}"
        
        try:
            # 对于下载文件，需要直接读取二进制内容
            headers = {}
            if self.token:
                headers['Authorization'] = f'Bearer {self.token}'
            
            req = urllib.request.Request(url, headers=headers, method="GET")
            
            with self.opener.open(req) as response:
                if response.status == 200:
                    # 保存zip文件
                    filename = "generated_code.zip"
                    with open(filename, "wb") as f:
                        f.write(response.read())
                    print(f"✓ 代码已批量生成并保存到: {filename}")
                    return True
                else:
                    print(f"✗ 批量生成代码失败: HTTP {response.status}")
                    return False
        except Exception as e:
            print(f"✗ 批量生成代码异常: {e}")
            return False


def main():
    """主函数"""
    print("=" * 80)
    print("若依代码生成器 - API自动化脚本")
    print("=" * 80)
    
    # 1. 创建客户端并登录
    client = RuoYiClient(BASE_URL, USERNAME, PASSWORD)
    
    # 检查是否有环境变量中的token
    env_token = os.environ.get('RUOYI_TOKEN', '')
    if env_token:
        print(f"\n✓ 从环境变量获取到Token: {env_token[:20]}...")
        client.token = env_token
    else:
        if not client.login():
            print("\n✗ 登录失败")
            print("\n解决方案：")
            print("方案1: 在数据库中临时关闭验证码功能")
            print("  UPDATE sys_config SET config_value='false' WHERE config_key='sys.account.captchaEnabled';")
            print("\n方案2: 手动获取token")
            print(f"  1. 浏览器访问: {BASE_URL}")
            print(f"  2. 使用 admin/admin123 登录")
            print(f"  3. F12打开开发者工具，在Console中执行: localStorage.getItem('Admin-Token')")
            print(f"  4. 设置环境变量: export RUOYI_TOKEN='获取到的token'")
            print(f"  5. 重新运行: python3 generate_code_api.py")
            sys.exit(1)
    
    print("\n" + "=" * 80)
    print("步骤1: 获取已导入的表列表")
    print("=" * 80)
    
    # 2. 获取表列表
    tables = client.get_table_list()
    if not tables:
        print("\n✗ 未找到已导入的表，请先确保表已经导入到代码生成器")
        sys.exit(1)
    
    # 3. 筛选出需要生成的brew表
    brew_table_map = {}
    for table in tables:
        table_name = table.get("tableName", "")
        if table_name in BREW_TABLES:
            brew_table_map[table_name] = table
            print(f"  ✓ {table_name} - {table.get('tableComment', '')}")
    
    if not brew_table_map:
        print("\n✗ 未找到brew业务表，请先执行SQL导入脚本")
        sys.exit(1)
    
    print(f"\n找到 {len(brew_table_map)}/{len(BREW_TABLES)} 个业务表")
    
    # 4. 确认是否继续
    print("\n" + "=" * 80)
    print("步骤2: 准备生成代码")
    print("=" * 80)
    print(f"将为以下 {len(brew_table_map)} 个表生成代码：")
    for table_name in brew_table_map.keys():
        print(f"  - {table_name}")
    
    # 自动继续
    print("\n开始生成代码...")
    time.sleep(1)
    
    # 5. 批量生成代码
    print("\n" + "=" * 80)
    print("步骤3: 批量生成代码")
    print("=" * 80)
    
    table_names_to_gen = list(brew_table_map.keys())
    success = client.batch_generate_code(table_names_to_gen)
    
    if success:
        print("\n" + "=" * 80)
        print("✓ 代码生成完成！")
        print("=" * 80)
        print("\n生成的代码已保存为 generated_code.zip")
        print("\n后续步骤：")
        print("1. 解压 generated_code.zip")
        print("2. 将解压后的代码复制到项目对应目录")
        print("3. 重启后端服务")
        print("4. 重启前端服务")
        print("5. 验证功能是否正常")
    else:
        print("\n✗ 代码生成失败")
        sys.exit(1)


if __name__ == "__main__":
    main()

