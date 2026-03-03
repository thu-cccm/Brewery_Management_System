<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!-- 生产概览 -->
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-pie-chart"></i> 生产状态分布</span>
          </div>
          <div id="productionChart" style="height: 300px;"></div>
        </el-card>
      </el-col>

      <!-- 损耗分析 -->
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-s-data"></i> 阶段平均损耗率 (%)</span>
          </div>
          <div id="lossChart" style="height: 300px;"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <!-- 产量对比 -->
      <el-col :span="24">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-data-line"></i> 计划产量 VS 实际产量 (近6个月)</span>
          </div>
          <div id="compareChart" style="height: 350px;"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <!-- 关键指标卡片 -->
      <el-col :span="6">
        <el-card shadow="always" :body-style="{ padding: '20px', textAlign: 'center' }">
          <div style="font-size: 14px; color: #909399;">总生产批次</div>
          <div style="font-size: 24px; font-weight: bold; margin-top: 10px;">{{ totalBatches }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always" :body-style="{ padding: '20px', textAlign: 'center' }">
          <div style="font-size: 14px; color: #909399;">在产批次</div>
          <div style="font-size: 24px; font-weight: bold; margin-top: 10px; color: #409EFF;">{{ activeBatches }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always" :body-style="{ padding: '20px', textAlign: 'center' }">
          <div style="font-size: 14px; color: #909399;">异常批次率</div>
          <div style="font-size: 24px; font-weight: bold; margin-top: 10px; color: #F56C6C;">{{ abnormalRate }}%</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always" :body-style="{ padding: '20px', textAlign: 'center' }">
          <div style="font-size: 14px; color: #909399;">平均损耗率</div>
          <div style="font-size: 24px; font-weight: bold; margin-top: 10px; color: #E6A23C;">{{ avgLossRate }}%</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { getYieldStats, getProductionStats, getProductionCompare } from "@/api/brew/stats";

export default {
  name: "Analysis",
  data() {
    return {
      productionChart: null,
      lossChart: null,
      compareChart: null,
      totalBatches: 0,
      activeBatches: 0,
      abnormalRate: 0,
      avgLossRate: 0
    };
  },
  mounted() {
    this.initCharts();
    this.fetchData();
    window.addEventListener('resize', this.resizeCharts);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.resizeCharts);
    if (this.productionChart) this.productionChart.dispose();
    if (this.lossChart) this.lossChart.dispose();
    if (this.compareChart) this.compareChart.dispose();
  },
  methods: {
    initCharts() {
      this.productionChart = echarts.init(document.getElementById('productionChart'));
      this.lossChart = echarts.init(document.getElementById('lossChart'));
      this.compareChart = echarts.init(document.getElementById('compareChart'));
    },
    resizeCharts() {
      if (this.productionChart) this.productionChart.resize();
      if (this.lossChart) this.lossChart.resize();
      if (this.compareChart) this.compareChart.resize();
    },
    fetchData() {
      // 1. 获取生产概览
      getProductionStats().then(response => {
        const data = response.data;
        const statusMap = {
            '1': '备料中', '2': '发酵中', '3': '蒸馏中', 
            '4': '质检中', '5': '已完成', '6': '异常'
        };
        
        let pieData = [];
        let total = 0;
        let active = 0;
        
        for (let key in data) {
            let count = data[key];
            total += count;
            if (['1', '2', '3', '4'].includes(key)) {
                active += count;
            }
            pieData.push({
                name: statusMap[key] || key,
                value: count
            });
        }
        
        this.totalBatches = total;
        this.activeBatches = active;

        this.productionChart.setOption({
            tooltip: { trigger: 'item' },
            legend: { top: '5%', left: 'center' },
            series: [{
                name: '批次状态',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: { show: false, position: 'center' },
                emphasis: {
                    label: { show: true, fontSize: '20', fontWeight: 'bold' }
                },
                labelLine: { show: false },
                data: pieData
            }]
        });
      });

      // 2. 获取损耗统计
      getYieldStats().then(response => {
          const data = response.data;
          this.abnormalRate = data.abnormalRate.toFixed(1);
          
          const stages = Object.keys(data.stageLossRate);
          const values = Object.values(data.stageLossRate).map(v => v.toFixed(2));
          
          // 计算平均损耗率
          if (values.length > 0) {
             let sum = values.reduce((a, b) => parseFloat(a) + parseFloat(b), 0);
             this.avgLossRate = (sum / values.length).toFixed(1);
          }

          this.lossChart.setOption({
              tooltip: { trigger: 'axis' },
              xAxis: { type: 'category', data: stages },
              yAxis: { type: 'value', name: '损耗率(%)' },
              series: [{
                  data: values,
                  type: 'bar',
                  showBackground: true,
                  backgroundStyle: { color: 'rgba(180, 180, 180, 0.2)' },
                  itemStyle: {
                      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                          { offset: 0, color: '#83bff6' },
                          { offset: 0.5, color: '#188df0' },
                          { offset: 1, color: '#188df0' }
                      ])
                  }
              }]
          });
      });

      // 3. 获取产量对比
      getProductionCompare().then(response => {
        const data = response.data;
        const xAxisData = data.xAxis;
        const planData = data.planSeries;
        const actualData = data.actualSeries;

        this.compareChart.setOption({
          tooltip: {
            trigger: 'axis',
            axisPointer: { type: 'shadow' }
          },
          legend: {
            data: ['计划产量', '实际产量']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            data: xAxisData
          },
          yAxis: {
            type: 'value',
            name: '产量(L)'
          },
          series: [
            {
              name: '计划产量',
              type: 'bar',
              data: planData,
              itemStyle: { color: '#409EFF' }
            },
            {
              name: '实际产量',
              type: 'bar',
              data: actualData,
              itemStyle: { color: '#67C23A' }
            }
          ]
        });
      });
    }
  }
};
</script>
