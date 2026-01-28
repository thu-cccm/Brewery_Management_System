<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计划编号" prop="planCode">
        <el-input v-model="queryParams.planCode" placeholder="请输入计划编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="计划名称" prop="planName">
        <el-input v-model="queryParams.planName" placeholder="请输入计划名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="计划状态" prop="planStatus">
        <el-select v-model="queryParams.planStatus" placeholder="请选择" clearable>
          <el-option label="待审核" value="1"/>
          <el-option label="已审核" value="2"/>
          <el-option label="生产中" value="3"/>
          <el-option label="已完成" value="4"/>
          <el-option label="已取消" value="5"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:plan:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:plan:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:plan:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['system:plan:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="计划ID" align="center" prop="planId" width="80"/>
      <el-table-column label="计划编号" align="center" prop="planCode" width="120"/>
      <el-table-column label="计划名称" align="center" prop="planName" min-width="150"/>
      <el-table-column label="配方模板ID" align="center" prop="templateId" width="100"/>
      <el-table-column label="目标产量(L)" align="center" prop="targetOutput" width="100"/>
      <el-table-column label="计划开始日期" align="center" prop="planStartDate" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划结束日期" align="center" prop="planEndDate" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人" align="center" prop="responsiblePerson" width="100"/>
      <el-table-column label="计划状态" align="center" prop="planStatus" width="90">
        <template slot-scope="scope">
          <el-tag :type="getStatusType(scope.row.planStatus)">{{ getStatusLabel(scope.row.planStatus) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:plan:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-video-play" @click="handleStartProduction(scope.row)" v-if="scope.row.planStatus === '2'" v-hasPermi="['system:plan:edit']">执行</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:plan:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="计划编号" prop="planCode">
              <el-input v-model="form.planCode" placeholder="请输入计划编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计划名称" prop="planName">
              <el-input v-model="form.planName" placeholder="请输入计划名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="酒类分类ID" prop="categoryId">
              <el-input v-model="form.categoryId" placeholder="请输入酒类分类ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="配方模板ID" prop="templateId">
              <el-input v-model="form.templateId" placeholder="请输入配方模板ID" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="目标产量(L)" prop="targetOutput">
              <el-input v-model="form.targetOutput" placeholder="请输入目标产量">
                <el-button slot="append" type="primary" :loading="calcLoading" @click="handleCalculateMaterial" :disabled="!form.templateId || !form.targetOutput">计算原料</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人" prop="responsiblePerson">
              <el-input v-model="form.responsiblePerson" placeholder="请输入负责人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开始日期" prop="planStartDate">
              <el-date-picker clearable v-model="form.planStartDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择开始日期" style="width: 100%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束日期" prop="planEndDate">
              <el-date-picker clearable v-model="form.planEndDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择结束日期" style="width: 100%"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
        
        <el-divider content-position="left" v-if="materialList.length > 0">原料配比计算结果</el-divider>
        <el-table v-if="materialList.length > 0" :data="materialList" border size="small" style="margin-bottom: 20px;">
          <el-table-column label="原料名称" align="center" prop="materialName"/>
          <el-table-column label="需用量" align="center">
            <template slot-scope="scope">
              {{ scope.row.requiredQuantity }} {{ scope.row.materialUnit }}
            </template>
          </el-table-column>
          <el-table-column label="当前库存" align="center">
            <template slot-scope="scope">
              {{ scope.row.currentStock }} {{ scope.row.materialUnit }}
            </template>
          </el-table-column>
          <el-table-column label="库存状态" align="center">
            <template slot-scope="scope">
              <span :style="{ color: scope.row.status === '缺货' ? '#F56C6C' : '#67C23A', fontWeight: 'bold' }">{{ scope.row.status }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="确认执行生产" :visible.sync="startProductionOpen" width="700px" append-to-body>
      <el-alert title="确认执行生产后，系统将自动扣减库存并生成生产批次号" type="warning" :closable="false" style="margin-bottom: 20px;"/>
      <el-descriptions border :column="2">
        <el-descriptions-item label="计划编号">{{ currentPlan.planCode }}</el-descriptions-item>
        <el-descriptions-item label="计划名称">{{ currentPlan.planName }}</el-descriptions-item>
        <el-descriptions-item label="目标产量">{{ currentPlan.targetOutput }} L</el-descriptions-item>
        <el-descriptions-item label="负责人">{{ currentPlan.responsiblePerson }}</el-descriptions-item>
      </el-descriptions>
      
      <el-divider content-position="left">原料用量清单</el-divider>
      <el-table v-loading="startCalcLoading" :data="startMaterialList" border size="small">
        <el-table-column label="原料名称" align="center" prop="materialName"/>
        <el-table-column label="需用量" align="center">
          <template slot-scope="scope">
            {{ scope.row.requiredQuantity }} {{ scope.row.materialUnit }}
          </template>
        </el-table-column>
        <el-table-column label="当前库存" align="center">
          <template slot-scope="scope">
            {{ scope.row.currentStock }} {{ scope.row.materialUnit }}
          </template>
        </el-table-column>
        <el-table-column label="库存状态" align="center">
          <template slot-scope="scope">
            <span :style="{ color: scope.row.status === '缺货' ? '#F56C6C' : '#67C23A', fontWeight: 'bold' }">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
      </el-table>
      
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmStartProduction" :loading="startLoading" :disabled="hasShortage">确认执行</el-button>
        <el-button @click="startProductionOpen = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/plan"
import { calculateMaterial, startProduction } from "@/api/brew/calculation"

export default {
  name: "Plan",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      planList: [],
      title: "",
      open: false,
      calcLoading: false,
      materialList: [],
      startProductionOpen: false,
      currentPlan: {},
      startMaterialList: [],
      startCalcLoading: false,
      startLoading: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planCode: null,
        planName: null,
        planStatus: null,
      },
      form: {},
      rules: {
        planCode: [{ required: true, message: "计划编号不能为空", trigger: "blur" }],
        planName: [{ required: true, message: "计划名称不能为空", trigger: "blur" }],
        categoryId: [{ required: true, message: "酒类分类ID不能为空", trigger: "blur" }],
        templateId: [{ required: true, message: "配方模板ID不能为空", trigger: "blur" }],
        targetOutput: [{ required: true, message: "目标产量不能为空", trigger: "blur" }],
        planStartDate: [{ required: true, message: "开始日期不能为空", trigger: "blur" }],
        planEndDate: [{ required: true, message: "结束日期不能为空", trigger: "blur" }],
      }
    }
  },
  computed: {
    hasShortage() {
      return this.startMaterialList.some(item => item.status === '缺货')
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listPlan(this.queryParams).then(response => {
        this.planList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getStatusType(status) {
      const types = { '1': 'info', '2': 'warning', '3': 'primary', '4': 'success', '5': 'danger' }
      return types[status] || 'info'
    },
    getStatusLabel(status) {
      const labels = { '1': '待审核', '2': '已审核', '3': '生产中', '4': '已完成', '5': '已取消' }
      return labels[status] || '未知'
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        planId: null, planCode: null, planName: null, categoryId: null, templateId: null,
        targetOutput: null, planStartDate: null, planEndDate: null, responsiblePerson: null,
        planStatus: null, approveBy: null, approveTime: null, remark: null
      }
      this.materialList = []
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
      this.ids = selection.map(item => item.planId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "新增生产计划"
    },
    handleUpdate(row) {
      this.reset()
      const planId = row.planId || this.ids
      getPlan(planId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改生产计划"
      })
    },
    handleCalculateMaterial() {
      if (!this.form.templateId || !this.form.targetOutput) {
        this.$modal.msgWarning("请先输入配方模板ID和目标产量")
        return
      }
      this.calcLoading = true
      calculateMaterial(this.form.templateId, this.form.targetOutput).then(response => {
        this.materialList = response.data || []
        this.calcLoading = false
        if (this.materialList.length === 0) {
          this.$modal.msgWarning("未找到该配方的原料配比信息")
        } else {
          const shortage = this.materialList.filter(item => item.status === '缺货')
          if (shortage.length > 0) {
            this.$modal.msgWarning(`警告：${shortage.length} 种原料库存不足！`)
          }
        }
      }).catch(() => {
        this.calcLoading = false
      })
    },
    handleStartProduction(row) {
      this.currentPlan = row
      this.startProductionOpen = true
      this.startMaterialList = []
      this.startCalcLoading = true
      calculateMaterial(row.templateId, row.targetOutput).then(response => {
        this.startMaterialList = response.data || []
        this.startCalcLoading = false
        if (this.startMaterialList.length === 0) {
          this.$modal.msgWarning("未找到该配方的原料配比信息")
        }
      }).catch(() => {
        this.startCalcLoading = false
      })
    },
    confirmStartProduction() {
      if (this.hasShortage) {
        this.$modal.msgError("存在原料库存不足，无法执行生产")
        return
      }
      this.startLoading = true
      startProduction(this.currentPlan.planId, this.startMaterialList).then(response => {
        this.$modal.msgSuccess("生产启动成功，批次号：" + response.data)
        this.startProductionOpen = false
        this.startLoading = false
        this.getList()
      }).catch(() => {
        this.startLoading = false
      })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.planId != null) {
            updatePlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addPlan(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleDelete(row) {
      const planIds = row.planId || this.ids
      this.$modal.confirm('是否确认删除生产计划编号为"' + planIds + '"的数据项？').then(function() {
        return delPlan(planIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('system/plan/export', { ...this.queryParams }, `plan_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
