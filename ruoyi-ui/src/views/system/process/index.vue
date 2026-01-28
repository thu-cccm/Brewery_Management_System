<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="批次编号" prop="batchNo">
        <el-input v-model="queryParams.batchNo" placeholder="请输入批次编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="工艺阶段" prop="processStage">
        <el-select v-model="queryParams.processStage" placeholder="请选择" clearable>
          <el-option label="备料" value="1"/>
          <el-option label="发酵" value="2"/>
          <el-option label="蒸馏" value="3"/>
          <el-option label="装瓶" value="4"/>
        </el-select>
      </el-form-item>
      <el-form-item label="操作人" prop="operatorName">
        <el-input v-model="queryParams.operatorName" placeholder="请输入操作人" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:process:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:process:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:process:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['system:process:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="processId" width="80"/>
      <el-table-column label="批次编号" align="center" prop="batchNo" width="160"/>
      <el-table-column label="工艺阶段" align="center" prop="processStage" width="90">
        <template slot-scope="scope">
          <el-tag :type="getStageType(scope.row.processStage)">{{ getStageLabel(scope.row.processStage) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="记录时间" align="center" prop="processTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.processTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="平均温度(℃)" align="center" prop="avgTemperature" width="100"/>
      <el-table-column label="平均湿度(%)" align="center" prop="avgHumidity" width="100"/>
      <el-table-column label="操作人" align="center" prop="operatorName" width="100"/>
      <el-table-column label="备注" align="center" prop="remark" min-width="150" show-overflow-tooltip/>
      <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:process:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:process:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="批次编号" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入批次编号" />
        </el-form-item>
        <el-form-item label="工艺阶段" prop="processStage">
          <el-select v-model="form.processStage" placeholder="请选择工艺阶段" style="width: 100%">
            <el-option label="备料" value="1"/>
            <el-option label="发酵" value="2"/>
            <el-option label="蒸馏" value="3"/>
            <el-option label="装瓶" value="4"/>
          </el-select>
        </el-form-item>
        <el-form-item label="记录时间" prop="processTime">
          <el-date-picker clearable v-model="form.processTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择记录时间" style="width: 100%"/>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="平均温度(℃)" prop="avgTemperature">
              <el-input-number v-model="form.avgTemperature" :precision="1" :step="0.5" :min="-10" :max="100" @blur="handleEnvBlur" style="width: 100%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="平均湿度(%)" prop="avgHumidity">
              <el-input-number v-model="form.avgHumidity" :precision="1" :step="1" :min="0" :max="100" @blur="handleEnvBlur" style="width: 100%"/>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-form-item label="推荐发酵天数" v-if="form.processStage === '2'">
          <el-input v-model="form.recommendedDays" :disabled="true">
            <template slot="append">天</template>
          </el-input>
          <div class="recommend-hint" v-if="recommendHint">*系统已根据环境参数优化发酵周期</div>
        </el-form-item>
        
        <el-form-item label="操作人" prop="operatorName">
          <el-input v-model="form.operatorName" placeholder="请输入操作人" />
        </el-form-item>
        <el-form-item label="工艺参数" prop="processData">
          <el-input v-model="form.processData" type="textarea" :rows="3" placeholder="请输入工艺参数(JSON格式)" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProcess, getProcess, delProcess, addProcess, updateProcess } from "@/api/system/process"
import { recommendCycle } from "@/api/brew/calculation"

export default {
  name: "Process",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      processList: [],
      title: "",
      open: false,
      recommendHint: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        processStage: null,
        operatorName: null,
      },
      form: {},
      rules: {
        batchNo: [{ required: true, message: "批次编号不能为空", trigger: "blur" }],
        processStage: [{ required: true, message: "工艺阶段不能为空", trigger: "change" }],
        processTime: [{ required: true, message: "记录时间不能为空", trigger: "blur" }],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listProcess(this.queryParams).then(response => {
        this.processList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getStageType(stage) {
      const types = { '1': '', '2': 'warning', '3': 'danger', '4': 'success' }
      return types[stage] || 'info'
    },
    getStageLabel(stage) {
      const labels = { '1': '备料', '2': '发酵', '3': '蒸馏', '4': '装瓶' }
      return labels[stage] || '未知'
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        processId: null, batchNo: null, processStage: null, processTime: null,
        avgTemperature: null, avgHumidity: null, processData: null, operatorName: null,
        remark: null, recommendedDays: null
      }
      this.recommendHint = false
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
      this.ids = selection.map(item => item.processId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "新增生产过程记录"
    },
    handleUpdate(row) {
      this.reset()
      const processId = row.processId || this.ids
      getProcess(processId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改生产过程记录"
      })
    },
    handleEnvBlur() {
      if (this.form.processStage === '2' && this.form.avgTemperature != null && this.form.avgHumidity != null) {
        recommendCycle(this.form.avgTemperature, this.form.avgHumidity, 'baijiu').then(response => {
          this.form.recommendedDays = response.data
          this.recommendHint = true
        }).catch(() => {})
      }
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.processId != null) {
            updateProcess(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addProcess(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleDelete(row) {
      const processIds = row.processId || this.ids
      this.$modal.confirm('是否确认删除生产过程记录编号为"' + processIds + '"的数据项？').then(function() {
        return delProcess(processIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('system/process/export', { ...this.queryParams }, `process_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>

<style scoped>
.recommend-hint {
  font-size: 12px;
  color: #67C23A;
  margin-top: 5px;
}
</style>
