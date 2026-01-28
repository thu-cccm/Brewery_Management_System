<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="报告编号" prop="reportNo">
        <el-input v-model="queryParams.reportNo" placeholder="请输入报告编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="批次编号" prop="batchNo">
        <el-input v-model="queryParams.batchNo" placeholder="请输入批次编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="报告标题" prop="reportTitle">
        <el-input v-model="queryParams.reportTitle" placeholder="请输入报告标题" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:report:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:report:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:report:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报告ID" align="center" prop="reportId" width="80"/>
      <el-table-column label="报告编号" align="center" prop="reportNo" width="130"/>
      <el-table-column label="批次编号" align="center" prop="batchNo" width="160"/>
      <el-table-column label="报告标题" align="center" prop="reportTitle" min-width="150"/>
      <el-table-column label="报告状态" align="center" prop="reportStatus" width="90">
        <template slot-scope="scope">
          <el-tag :type="getReportStatusType(scope.row.reportStatus)">{{ getReportStatusLabel(scope.row.reportStatus) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审批状态" align="center" prop="approveStatus" width="90">
        <template slot-scope="scope">
          <el-tag :type="getApproveStatusType(scope.row.approveStatus)">{{ getApproveStatusLabel(scope.row.approveStatus) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审批人" align="center" prop="approveBy" width="100"/>
      <el-table-column label="审批时间" align="center" prop="approveTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.approveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:report:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:report:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="报告编号" prop="reportNo">
          <el-input v-model="form.reportNo" placeholder="请输入报告编号" />
        </el-form-item>
        <el-form-item label="批次编号" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入批次编号" />
        </el-form-item>
        <el-form-item label="质检记录ID" prop="recordId">
          <el-input v-model="form.recordId" placeholder="请输入质检记录ID" />
        </el-form-item>
        <el-form-item label="报告标题" prop="reportTitle">
          <el-input v-model="form.reportTitle" placeholder="请输入报告标题" />
        </el-form-item>
        <el-form-item label="报告内容">
          <el-input v-model="form.reportContent" type="textarea" :rows="6" placeholder="请输入报告内容"/>
        </el-form-item>
        <el-form-item label="报告状态" prop="reportStatus">
          <el-select v-model="form.reportStatus" placeholder="请选择" style="width: 100%">
            <el-option label="草稿" value="1"/>
            <el-option label="已发布" value="2"/>
            <el-option label="已归档" value="3"/>
          </el-select>
        </el-form-item>
        <el-form-item label="审批状态" prop="approveStatus">
          <el-select v-model="form.approveStatus" placeholder="请选择" style="width: 100%">
            <el-option label="待审批" value="0"/>
            <el-option label="已通过" value="1"/>
            <el-option label="已驳回" value="2"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="质检报告详情" :visible.sync="viewOpen" width="800px" append-to-body custom-class="report-view-dialog">
      <div class="report-view-container">
        <div class="print-btn no-print">
          <el-button type="primary" icon="el-icon-printer" @click="handlePrint">打印报告</el-button>
        </div>
        <div class="a4-paper" ref="reportContent">
          <div class="report-header">
            <h1>质量检验报告</h1>
            <h2>QUALITY INSPECTION REPORT</h2>
          </div>
          <div class="report-info">
            <table class="info-table">
              <tr>
                <td class="label">报告编号</td>
                <td>{{ viewData.reportNo }}</td>
                <td class="label">批次编号</td>
                <td>{{ viewData.batchNo }}</td>
              </tr>
              <tr>
                <td class="label">报告标题</td>
                <td colspan="3">{{ viewData.reportTitle }}</td>
              </tr>
              <tr>
                <td class="label">审批人</td>
                <td>{{ viewData.approveBy || '-' }}</td>
                <td class="label">审批时间</td>
                <td>{{ parseTime(viewData.approveTime, '{y}-{m}-{d}') || '-' }}</td>
              </tr>
            </table>
          </div>
          <div class="report-content">
            <h3>检验内容</h3>
            <div class="content-text" v-html="viewData.reportContent || '暂无内容'"></div>
          </div>
          <div class="report-footer">
            <div class="signature-area">
              <div class="signature-item">
                <span class="signature-label">检验员签字：</span>
                <span class="signature-line"></span>
              </div>
              <div class="signature-item">
                <span class="signature-label">审批签字：</span>
                <span class="signature-line"></span>
              </div>
            </div>
            <div class="stamp" :class="{ 'approved': viewData.approveStatus === '1', 'rejected': viewData.approveStatus === '2' }">
              <span v-if="viewData.approveStatus === '1'">合格</span>
              <span v-else-if="viewData.approveStatus === '2'">不合格</span>
              <span v-else>待审</span>
            </div>
          </div>
          <div class="report-date">
            报告日期：{{ parseTime(viewData.createTime, '{y}年{m}月{d}日') || '-' }}
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/system/report"

export default {
  name: "Report",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      reportList: [],
      title: "",
      open: false,
      viewOpen: false,
      viewData: {},
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportNo: null,
        batchNo: null,
        reportTitle: null,
      },
      form: {},
      rules: {
        reportNo: [{ required: true, message: "报告编号不能为空", trigger: "blur" }],
        batchNo: [{ required: true, message: "批次编号不能为空", trigger: "blur" }],
        recordId: [{ required: true, message: "质检记录ID不能为空", trigger: "blur" }],
        reportTitle: [{ required: true, message: "报告标题不能为空", trigger: "blur" }],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getReportStatusType(status) {
      const types = { '1': 'info', '2': 'success', '3': '' }
      return types[status] || 'info'
    },
    getReportStatusLabel(status) {
      const labels = { '1': '草稿', '2': '已发布', '3': '已归档' }
      return labels[status] || '未知'
    },
    getApproveStatusType(status) {
      const types = { '0': 'warning', '1': 'success', '2': 'danger' }
      return types[status] || 'info'
    },
    getApproveStatusLabel(status) {
      const labels = { '0': '待审批', '1': '已通过', '2': '已驳回' }
      return labels[status] || '未知'
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        reportId: null, reportNo: null, batchNo: null, recordId: null, reportTitle: null,
        reportContent: null, reportStatus: '1', approveStatus: '0', approveBy: null, approveTime: null
      }
      this.resetForm("form")
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.reportId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "新增质检报告"
    },
    handleUpdate(row) {
      this.reset()
      const reportId = row.reportId || this.ids
      getReport(reportId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改质检报告"
      })
    },
    handleView(row) {
      getReport(row.reportId).then(response => {
        this.viewData = response.data
        this.viewOpen = true
      })
    },
    handlePrint() {
      const printContent = this.$refs.reportContent.innerHTML
      const printWindow = window.open('', '_blank')
      printWindow.document.write(`
        <html>
        <head>
          <title>质检报告打印</title>
          <style>
            body { font-family: 'SimSun', serif; }
            .a4-paper { width: 210mm; min-height: 297mm; padding: 20mm; margin: 0 auto; background: #fff; }
            .report-header { text-align: center; margin-bottom: 30px; }
            .report-header h1 { font-size: 24px; margin: 0; }
            .report-header h2 { font-size: 14px; color: #666; margin: 5px 0 0 0; font-weight: normal; }
            .info-table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
            .info-table td { border: 1px solid #333; padding: 8px; }
            .info-table .label { width: 100px; background: #f5f5f5; font-weight: bold; }
            .report-content { margin: 20px 0; }
            .report-content h3 { border-bottom: 2px solid #333; padding-bottom: 5px; }
            .content-text { min-height: 200px; line-height: 1.8; }
            .report-footer { display: flex; justify-content: space-between; align-items: flex-end; margin-top: 50px; }
            .signature-area { display: flex; gap: 50px; }
            .signature-item { display: flex; align-items: center; }
            .signature-line { width: 100px; border-bottom: 1px solid #333; margin-left: 10px; }
            .stamp { width: 80px; height: 80px; border: 3px solid; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 20px; font-weight: bold; transform: rotate(-15deg); }
            .stamp.approved { color: #67C23A; border-color: #67C23A; }
            .stamp.rejected { color: #F56C6C; border-color: #F56C6C; }
            .report-date { text-align: right; margin-top: 30px; }
            @media print { .no-print { display: none !important; } }
          </style>
        </head>
        <body>${printContent}</body>
        </html>
      `)
      printWindow.document.close()
      printWindow.print()
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reportId != null) {
            updateReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addReport(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleDelete(row) {
      const reportIds = row.reportId || this.ids
      this.$modal.confirm('是否确认删除质检报告编号为"' + reportIds + '"的数据项？').then(function() {
        return delReport(reportIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    }
  }
}
</script>

<style scoped lang="scss">
.report-view-container {
  position: relative;
  .print-btn {
    position: absolute;
    top: -50px;
    right: 0;
  }
}

.a4-paper {
  width: 100%;
  max-width: 210mm;
  min-height: 297mm;
  padding: 20mm;
  margin: 0 auto;
  background: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  font-family: 'SimSun', serif;

  .report-header {
    text-align: center;
    margin-bottom: 30px;
    h1 { font-size: 24px; margin: 0; color: #333; }
    h2 { font-size: 14px; color: #666; margin: 5px 0 0 0; font-weight: normal; }
  }

  .info-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    td {
      border: 1px solid #333;
      padding: 8px;
      &.label {
        width: 100px;
        background: #f5f5f5;
        font-weight: bold;
      }
    }
  }

  .report-content {
    margin: 20px 0;
    h3 {
      border-bottom: 2px solid #333;
      padding-bottom: 5px;
    }
    .content-text {
      min-height: 200px;
      line-height: 1.8;
    }
  }

  .report-footer {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    margin-top: 50px;

    .signature-area {
      display: flex;
      gap: 50px;
      .signature-item {
        display: flex;
        align-items: center;
        .signature-line {
          width: 100px;
          border-bottom: 1px solid #333;
          margin-left: 10px;
        }
      }
    }

    .stamp {
      width: 80px;
      height: 80px;
      border: 3px solid #999;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 20px;
      font-weight: bold;
      color: #999;
      transform: rotate(-15deg);
      &.approved {
        color: #67C23A;
        border-color: #67C23A;
      }
      &.rejected {
        color: #F56C6C;
        border-color: #F56C6C;
      }
    }
  }

  .report-date {
    text-align: right;
    margin-top: 30px;
  }
}

@media print {
  .no-print { display: none !important; }
}
</style>
