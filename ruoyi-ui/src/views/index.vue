<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :span="24" style="margin-bottom: 20px;">
        <el-card class="welcome-card">
          <div class="welcome-header">
            <h1>欢迎使用酒厂生产质量管理系统</h1>
            <p class="welcome-subtitle">Brewery Production Quality Management System</p>
          </div>
          <el-divider></el-divider>
          <div class="welcome-content">
            <p>本系统致力于为酒厂提供全方位的生产质量管理解决方案，涵盖原料管理、生产流程监控、质量检测、仓储物流等核心业务模块。</p>
            <p>通过数字化、智能化的管理手段，帮助企业提升生产效率，保障产品质量，实现精细化运营。</p>
          </div>
        </el-card>
          </el-col>
        </el-row>

    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="12" :lg="6">
        <el-card class="feature-card">
          <div class="feature-icon production">
            <i class="el-icon-s-goods"></i>
          </div>
          <h3>生产管理</h3>
          <p>实时监控生产流程，记录关键工艺参数，确保生产过程可追溯</p>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="6">
        <el-card class="feature-card">
          <div class="feature-icon quality">
            <i class="el-icon-s-check"></i>
          </div>
          <h3>质量检测</h3>
          <p>建立完善的质检体系，记录检测数据，保障产品质量达标</p>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="6">
        <el-card class="feature-card">
          <div class="feature-icon warehouse">
            <i class="el-icon-s-home"></i>
          </div>
          <h3>仓储管理</h3>
          <p>智能化仓库管理，实时掌握库存状况，优化仓储资源配置</p>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="6">
        <el-card class="feature-card">
          <div class="feature-icon data">
            <i class="el-icon-s-data"></i>
          </div>
          <h3>数据分析</h3>
          <p>多维度数据统计分析，为管理决策提供科学依据</p>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="24">
        <el-card>
          <div slot="header">
            <span>系统信息</span>
          </div>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="系统名称">酒厂生产质量管理系统</el-descriptions-item>
            <el-descriptions-item label="系统版本">{{ version }}</el-descriptions-item>
            <el-descriptions-item label="后端技术">Spring Boot + MyBatis + MySQL</el-descriptions-item>
            <el-descriptions-item label="前端技术">Vue2 + Element UI</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      version: "1.0.0"
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  padding: 20px;

  .welcome-card {
    .welcome-header {
      text-align: center;
      padding: 20px 0;

      h1 {
        font-size: 32px;
        font-weight: 600;
        color: #303133;
        margin: 0 0 10px 0;
      }

      .welcome-subtitle {
        font-size: 16px;
        color: #909399;
    margin: 0;
      }
    }

    .welcome-content {
      padding: 20px;
      text-align: center;

      p {
        font-size: 15px;
        line-height: 1.8;
        color: #606266;
        margin: 10px 0;
      }
    }
  }

  .feature-card {
    text-align: center;
    margin-bottom: 20px;
    transition: all 0.3s;
    cursor: pointer;

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    }

    .feature-icon {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      margin: 20px auto;
      display: flex;
      align-items: center;
      justify-content: center;

      i {
        font-size: 30px;
        color: #fff;
      }

      &.production {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      }

      &.quality {
        background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
      }

      &.warehouse {
        background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
      }

      &.data {
        background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
      }
    }

    h3 {
      font-size: 18px;
      font-weight: 600;
      color: #303133;
      margin: 15px 0 10px 0;
    }

    p {
      font-size: 14px;
      color: #909399;
      line-height: 1.6;
      margin: 0;
      padding: 0 10px 20px 10px;
    }
  }
}
</style>
