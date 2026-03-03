<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>全链路追溯查询</span>
      </div>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="生产批次ID">
          <el-input v-model="batchId" placeholder="请输入生产批次ID"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <div v-if="traceData" style="margin-top: 20px;">
      <!-- 1. 计划阶段 -->
      <el-card class="box-card" shadow="hover">
        <div slot="header">
          <span><i class="el-icon-date"></i> 生产计划</span>
        </div>
        <div v-if="traceData.plan">
           <el-descriptions :column="3" border>
             <el-descriptions-item label="计划编号">{{ traceData.plan.planNo }}</el-descriptions-item>
             <el-descriptions-item label="计划名称">{{ traceData.plan.planName }}</el-descriptions-item>
             <el-descriptions-item label="计划产量">{{ traceData.plan.planOutput }} L</el-descriptions-item>
             <el-descriptions-item label="开始日期">{{ parseTime(traceData.plan.planStartDate) }}</el-descriptions-item>
             <el-descriptions-item label="结束日期">{{ parseTime(traceData.plan.planEndDate) }}</el-descriptions-item>
           </el-descriptions>
        </div>
        <div v-else>暂无计划信息</div>
      </el-card>

      <!-- 2. 生产批次 -->
      <el-card class="box-card" shadow="hover" style="margin-top: 20px;">
        <div slot="header">
          <span><i class="el-icon-s-operation"></i> 生产批次</span>
        </div>
        <div v-if="traceData.batch">
           <el-steps :active="activeStep" finish-status="success" align-center>
             <el-step title="备料" description="原料准备"></el-step>
             <el-step title="糖化" description="麦芽糖化"></el-step>
             <el-step title="发酵" description="恒温发酵"></el-step>
             <el-step title="质检" description="品质检测"></el-step>
             <el-step title="完成" description="成品入库"></el-step>
           </el-steps>
           <el-divider></el-divider>
           <el-descriptions :column="3" border>
             <el-descriptions-item label="批次编号">{{ traceData.batch.batchNo }}</el-descriptions-item>
             <el-descriptions-item label="实际产量">{{ traceData.batch.actualOutput }} L</el-descriptions-item>
             <el-descriptions-item label="状态">
                <el-tag>{{ statusFormat(traceData.batch.batchStatus) }}</el-tag>
             </el-descriptions-item>
             <el-descriptions-item label="发酵天数">{{ traceData.batch.fermentationDays }} 天</el-descriptions-item>
             <el-descriptions-item label="负责人">{{ traceData.batch.responsiblePerson }}</el-descriptions-item>
           </el-descriptions>
        </div>
      </el-card>

      <!-- 3. 过程监控 -->
      <el-card class="box-card" shadow="hover" style="margin-top: 20px;">
        <div slot="header">
          <span><i class="el-icon-monitor"></i> 过程监控记录</span>
        </div>
        <el-timeline>
          <el-timeline-item
            v-for="(activity, index) in traceData.processes"
            :key="index"
            :timestamp="parseTime(activity.recordTime)"
            placement="top">
            <el-card>
              <h4>{{ activity.stageKey }} 阶段监控</h4>
              <p>温度: {{ activity.temperature }}℃, 压力: {{ activity.pressure }}MPa, 糖度: {{ activity.sugarDegree }}°P</p>
              <p>操作员: {{ activity.operator }}</p>
              <el-tag type="danger" v-if="activity.isAbnormal == '1'">异常</el-tag>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>

      <!-- 4. 质检报告 -->
      <el-card class="box-card" shadow="hover" style="margin-top: 20px;">
        <div slot="header">
          <span><i class="el-icon-circle-check"></i> 质检报告</span>
        </div>
        <el-table :data="traceData.qualityChecks" border style="width: 100%">
          <el-table-column prop="inspectTime" label="检测时间" width="180">
            <template slot-scope="scope">{{ parseTime(scope.row.inspectTime, '{y}-{m}-{d}') }}</template>
          </el-table-column>
          <el-table-column prop="inspectorName" label="检测人" width="120" />
          <el-table-column prop="alcoholActual" label="实际酒精度" />
          <el-table-column prop="qualityResult" label="结果">
             <template slot-scope="scope">
                <el-tag type="success" v-if="scope.row.qualityResult == '1'">合格</el-tag>
                <el-tag type="danger" v-else>不合格</el-tag>
             </template>
          </el-table-column>
          <el-table-column prop="qualityScore" label="评分" />
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
import { getTraceData } from "@/api/brew/stats";

export default {
  name: "Trace",
  data() {
    return {
      batchId: '',
      traceData: null,
      activeStep: 0
    };
  },
  methods: {
    handleSearch() {
      if (!this.batchId) {
        this.$message.warning("请输入批次ID");
        return;
      }
      getTraceData(this.batchId).then(response => {
        this.traceData = response.data;
        if (this.traceData && this.traceData.batch) {
            // 简单映射状态到步骤
            const statusMap = {
                '1': 0, // 备料
                '2': 2, // 发酵 (跳过糖化显示)
                '3': 1, // 蒸煮/糖化
                '4': 3, // 质检
                '5': 5  // 完成
            };
            this.activeStep = statusMap[this.traceData.batch.batchStatus] || 0;
        }
      });
    },
    statusFormat(status) {
        const map = {
            '1': '备料中',
            '2': '发酵中',
            '3': '蒸馏中',
            '4': '质检中',
            '5': '已完成',
            '6': '异常'
        };
        return map[status] || status;
    }
  }
};
</script>
