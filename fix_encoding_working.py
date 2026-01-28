#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""修复中文乱码 - 工作版本"""

import os
import glob
import subprocess

# 从数据库获取映射
def get_mapping():
    """从数据库获取表名到中文的映射"""
    cmd = [
        'mysql', '-uadmin', '-padmin@120', '-h127.0.0.1', 'maple-boot', '-N',
        '-e', "SELECT CONCAT('Brew', UPPER(SUBSTRING(table_name, 6, 1)), SUBSTRING(table_name, 7)), function_name FROM gen_table WHERE table_name LIKE 'brew_%' ORDER BY table_name;"
    ]
    
    result = subprocess.run(cmd, capture_output=True, text=True, encoding='utf-8')
    mapping = {}
    for line in result.stdout.strip().split('\n'):
        if '\t' in line:
            class_name, chinese = line.split('\t', 1)
            # 规范化类名（处理下划线）
            class_name = ''.join(word.capitalize() for word in class_name.replace('Brew', '').lower().split('_'))
            class_name = 'Brew' + class_name
            mapping[class_name] = chinese
    return mapping

# 已知的乱码到正确中文的映射（通过分析文件得到）
# 这些是实际文件中出现的乱码字节序列
KNOWN_FIXES = {
    # 原料信息 - 通过读取文件得到的实际乱码
    b'\xc3\xa5\xc2\x90\xc2\xb7\xc3\xa6\xe2\x80\x93\xc3\xa2\xc2\xb5\xc2\xa0\xc3\xa4\xc2\xbf\xc2\xa8\xc3\xa6\xc2\x81\xc2\xaf': '原料信息',
    # 这里需要添加更多...
}

def fix_file_bytewise(filepath, mapping):
    """使用字节方式修复文件"""
    try:
        with open(filepath, 'rb') as f:
            raw = f.read()
        
        # 解码为UTF-8
        try:
            text = raw.decode('utf-8')
        except:
            return False
        
        original = text
        basename = os.path.basename(filepath)
        
        # 根据文件名确定应该替换成什么
        for class_name, correct_chinese in mapping.items():
            if class_name in basename:
                # 查找所有可能的乱码模式并替换
                # 由于乱码是UTF-8被错误解释产生的，我们需要尝试不同的模式
                
                # 方法：查找包含该表相关文字的注释，替换为正确的中文
                import re
                
                # 替换类注释：* 乱码Controller -> * 正确中文Controller
                pattern1 = r'\* [^*]*Controller'
                def replace_comment(match):
                    s = match.group(0)
                    if correct_chinese not in s:  # 如果还没有正确的中文
                        # 尝试替换
                        return f'* {correct_chinese}Controller'
                    return s
                text = re.sub(pattern1, replace_comment, text)
                
                # 替换方法注释中的表名
                text = text.replace(f'查询{correct_chinese}列表', f'查询{correct_chinese}列表')  # 确保正确
                text = text.replace(f'导出{correct_chinese}列表', f'导出{correct_chinese}列表')
                text = text.replace(f'获取{correct_chinese}详细信息', f'获取{correct_chinese}详细信息')
                text = text.replace(f'新增{correct_chinese}', f'新增{correct_chinese}')
                text = text.replace(f'修改{correct_chinese}', f'修改{correct_chinese}')
                text = text.replace(f'删除{correct_chinese}', f'删除{correct_chinese}')
                
                # 查找并替换乱码（需要知道实际的乱码字节）
                # 由于我们不知道确切的乱码，我们使用一个更通用的方法：
                # 查找所有包含非ASCII字符的注释，如果它们看起来像乱码，就替换
                
                # 更直接的方法：使用已知的乱码模式
                # 这些模式需要从实际文件中提取
                
        # 通用替换
        common_fixes = {
            '列表': '列表',
            '新增': '新增', 
            '修改': '修改',
            '删除': '删除',
            '导出': '导出',
            '查询': '查询',
            '数据': '数据',
        }
        
        if text != original:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(text)
            return True
        return False
    except Exception as e:
        print(f"错误 {filepath}: {e}")
        return False

# 更实用的方法：直接读取一个示例文件，提取乱码模式
def extract_garbled_patterns():
    """从示例文件中提取乱码模式"""
    sample = '/Users/4paradidm/Desktop/WORK/bbs/wine/Brewery_Management_System/ruoyi-admin/src/main/java/com/jiuchang/system/controller/BrewMaterialController.java'
    
    with open(sample, 'rb') as f:
        raw = f.read()
    
    text = raw.decode('utf-8')
    
    # 查找包含非ASCII的注释行
    import re
    comments = re.findall(r'/\*\*.*?\*/', text, re.DOTALL)
    for comment in comments:
        if 'å' in comment or 'æ' in comment or 'é' in comment:
            print(f"找到乱码注释: {comment[:50]}...")

if __name__ == '__main__':
    print("提取乱码模式...")
    extract_garbled_patterns()
    
    print("\n获取数据库映射...")
    mapping = get_mapping()
    print(f"获取到 {len(mapping)} 个映射")
    for k, v in mapping.items():
        print(f"  {k} -> {v}")

