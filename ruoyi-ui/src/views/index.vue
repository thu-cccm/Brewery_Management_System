<template>
  <div class="app-container dashboard">
    <el-row :gutter="20" class="warning-row">
      <el-col :span="24">
        <el-card class="warning-card" :class="{ 'has-warning': warningList.length > 0 }">
          <div slot="header" class="warning-header">
            <span><i class="el-icon-warning-outline"></i> 原料库存预警</span>
            <el-tag v-if="warningList.length === 0" type="success" size="small">库存健康</el-tag>
            <el-tag v-else type="danger" size="small">{{ warningList.length }} 项预警</el-tag>
          </div>
          <div v-if="warningList.length > 0" class="warning-marquee">
            <div class="marquee-content">
              <span v-for="(item, index) in warningList" :key="index" class="warning-item">
                <el-tag type="danger" size="small">{{ item.materialName }}</el-tag>
                <span class="warning-text">库存: {{ item.stockQuantity }}{{ item.materialUnit }} / 预警值: {{ item.warningThreshold }}{{ item.materialUnit }}</span>
              </span>
            </div>
          </div>
          <div v-else class="healthy-status">
            <i class="el-icon-success"></i>
            <span>所有原料库存充足，系统运行正常</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="summary-row">
      <el-col :xs="24" :sm="12" :md="6">
        <el-card class="summary-card production">
          <div class="summary-icon"><i class="el-icon-s-goods"></i></div>
          <div class="summary-info">
            <div class="summary-value">{{ summary.activePlans || 0 }}</div>
            <div class="summary-label">进行中计划</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <el-card class="summary-card batch">
          <div class="summary-icon"><i class="el-icon-s-order"></i></div>
          <div class="summary-info">
            <div class="summary-value">{{ summary.activeBatches || 0 }}</div>
            <div class="summary-label">生产中批次</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <el-card class="summary-card output">
          <div class="summary-icon"><i class="el-icon-s-data"></i></div>
          <div class="summary-info">
            <div class="summary-value">{{ summary.monthlyOutput || 0 }} <small>L</small></div>
            <div class="summary-label">本月产量</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="6">
        <el-card class="summary-card cost">
          <div class="summary-icon"><i class="el-icon-money"></i></div>
          <div class="summary-info">
            <div class="summary-value">{{ formatMoney(summary.monthlyCost) }}</div>
            <div class="summary-label">本月成本</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="chart-row">
      <el-col :xs="24" :lg="12">
        <el-card>
          <div slot="header"><span><i class="el-icon-s-marketing"></i> 计划产量 vs 实际产量</span></div>
          <div ref="productionChart" class="chart-container"></div>
        </el-card>
      </el-col>
      <el-col :xs="24" :lg="12">
        <el-card>
          <div slot="header"><span><i class="el-icon-data-line"></i> 单位成本趋势</span></div>
          <div ref="costChart" class="chart-container"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="24">
        <el-card>
          <div slot="header"><span>系统信息</span></div>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="系统名称">酒厂生产质量管理系统</el-descriptions-item>
            <el-descriptions-item label="系统版本">{{ version }}</el-descriptions-item>
            <el-descriptions-item label="后端技术">Spring Boot + MyBatis + MySQL</el-descriptions-item>
            <el-descriptions-item label="前端技术">Vue2 + Element UI + ECharts</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import { getDashboardData } from "@/api/brew/stats"

export default {
  name: "Index",
  data() {
    return {
      version: "1.0.0",
      warningList: [],
      summary: {},
      productionData: { xAxis: [], planSeries: [], actualSeries: [] },
      costData: { xAxis: [], unitCostSeries: [] },
      productionChart: null,
      costChart: null
    }
  },
  mounted() {
    this.initCharts()
    this.loadDashboardData()
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
    if (this.productionChart) this.productionChart.dispose()
    if (this.costChart) this.costChart.dispose()
  },
  methods: {
    formatMoney(value) {
      if (!value) return '¥0'
      return '¥' + Number(value).toLocaleString('zh-CN', { minimumFractionDigits: 0, maximumFractionDigits: 0 })
    },
    initCharts() {
      this.productionChart = echarts.init(this.$refs.productionChart)
      this.costChart = echarts.init(this.$refs.costChart)
    },
    handleResize() {
      if (this.productionChart) this.productionChart.resize()
      if (this.costChart) this.costChart.resize()
    },
    loadDashboardData() {
      getDashboardData().then(response => {
        const data = response.data
        this.warningList = data.materialWarningList || []
        this.summary = data.summary || {}
        
        if (data.productionCompare) {
          this.productionData = data.productionCompare
        }
        if (data.costTrend) {
          this.costData = data.costTrend
        }
        
        this.renderProductionChart()
        this.renderCostChart()
      }).catch(() => {
        this.renderProductionChart()
        this.renderCostChart()
      })
    },
    renderProductionChart() {
      const option = {
        tooltip: { trigger: 'axis' },
        legend: { data: ['计划产量', '实际产量'], bottom: 0 },
        grid: { left: '3%', right: '4%', bottom: '15%', top: '10%', containLabel: true },
        xAxis: { type: 'category', data: this.productionData.xAxis || [] },
        yAxis: { type: 'value', name: '产量(L)' },
        series: [
          {
            name: '计划产量',
            type: 'bar',
            data: this.productionData.planSeries || [],
            itemStyle: { color: '#409EFF' }
          },
          {
            name: '实际产量',
            type: 'bar',
            data: this.productionData.actualSeries || [],
            itemStyle: { color: '#67C23A' }
          }
        ]
      }
      this.productionChart.setOption(option)
    },
    renderCostChart() {
      const option = {
        tooltip: { trigger: 'axis' },
        legend: { data: ['单位成本'], bottom: 0 },
        grid: { left: '3%', right: '4%', bottom: '15%', top: '10%', containLabel: true },
        xAxis: { type: 'category', data: this.costData.xAxis || [] },
        yAxis: { type: 'value', name: '成本(元/L)' },
        series: [
          {
            name: '单位成本',
            type: 'line',
            data: this.costData.unitCostSeries || [],
            smooth: true,
            itemStyle: { color: '#E6A23C' },
            areaStyle: { color: 'rgba(230, 162, 60, 0.2)' }
          }
        ]
      }
      this.costChart.setOption(option)
    }
  }
}
</script>

<style scoped lang="scss">
.dashboard {
  padding: 20px;

  .warning-row {
    margin-bottom: 20px;
  }

  .warning-card {
    &.has-warning {
      border-color: #F56C6C;
      .warning-header {
        color: #F56C6C;
      }
    }
    .warning-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      i { margin-right: 8px; }
    }
    .warning-marquee {
      overflow: hidden;
      white-space: nowrap;
      .marquee-content {
        display: inline-block;
        animation: marquee 20s linear infinite;
      }
      .warning-item {
        margin-right: 40px;
        .warning-text {
          margin-left: 8px;
          color: #F56C6C;
        }
      }
    }
    .healthy-status {
      text-align: center;
      padding: 10px;
      color: #67C23A;
      i { font-size: 24px; margin-right: 10px; }
    }
  }

  @keyframes marquee {
    0% { transform: translateX(100%); }
    100% { transform: translateX(-100%); }
  }

  .summary-row {
    margin-bottom: 20px;
  }

  .summary-card {
    display: flex;
    align-items: center;
    padding: 20px;
    margin-bottom: 20px;
    transition: all 0.3s;
    cursor: pointer;
    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    }
    .summary-icon {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 15px;
      i { font-size: 28px; color: #fff; }
    }
    .summary-info {
      .summary-value {
        font-size: 28px;
        font-weight: bold;
        color: #303133;
        small { font-size: 14px; }
      }
      .summary-label {
        font-size: 14px;
        color: #909399;
        margin-top: 5px;
      }
    }
    &.production .summary-icon { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }
    &.batch .summary-icon { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); }
    &.output .summary-icon { background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%); }
    &.cost .summary-icon { background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%); }
  }

  .chart-row {
    .chart-container {
      height: 350px;
    }
  }
}
</style>
