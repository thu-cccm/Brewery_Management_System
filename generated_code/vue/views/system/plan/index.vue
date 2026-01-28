<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="è®¡åˆ’ç¼–å·" prop="planCode">
        <el-input
          v-model="queryParams.planCode"
          placeholder="请输入è®¡åˆ’ç¼–å·"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®¡åˆ’åç§°" prop="planName">
        <el-input
          v-model="queryParams.planName"
          placeholder="请输入è®¡åˆ’åç§°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’å“ç§ç±»ID" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入é…’å“ç§ç±»ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…æ–¹æ¨¡æ¿ID" prop="templateId">
        <el-input
          v-model="queryParams.templateId"
          placeholder="请输入é…æ–¹æ¨¡æ¿ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ç›®æ ‡äº§é‡ï¼ˆLï¼‰" prop="targetOutput">
        <el-input
          v-model="queryParams.targetOutput"
          placeholder="请输入ç›®æ ‡äº§é‡ï¼ˆLï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®¡åˆ’å¼€å§‹æ—¥æœŸ" prop="planStartDate">
        <el-date-picker clearable
          v-model="queryParams.planStartDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择è®¡åˆ’å¼€å§‹æ—¥æœŸ">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="è®¡åˆ’ç»“æŸæ—¥æœŸ" prop="planEndDate">
        <el-date-picker clearable
          v-model="queryParams.planEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择è®¡åˆ’ç»“æŸæ—¥æœŸ">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="è´Ÿè´£äºº" prop="responsiblePerson">
        <el-input
          v-model="queryParams.responsiblePerson"
          placeholder="请输入è´Ÿè´£äºº"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®¡æ ¸äºº" prop="approveBy">
        <el-input
          v-model="queryParams.approveBy"
          placeholder="请输入å®¡æ ¸äºº"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®¡æ ¸æ—¶é—´" prop="approveTime">
        <el-date-picker clearable
          v-model="queryParams.approveTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择å®¡æ ¸æ—¶é—´">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="è®¡åˆ’ID" align="center" prop="planId" />
      <el-table-column label="è®¡åˆ’ç¼–å·" align="center" prop="planCode" />
      <el-table-column label="è®¡åˆ’åç§°" align="center" prop="planName" />
      <el-table-column label="é…’å“ç§ç±»ID" align="center" prop="categoryId" />
      <el-table-column label="é…æ–¹æ¨¡æ¿ID" align="center" prop="templateId" />
      <el-table-column label="ç›®æ ‡äº§é‡ï¼ˆLï¼‰" align="center" prop="targetOutput" />
      <el-table-column label="è®¡åˆ’å¼€å§‹æ—¥æœŸ" align="center" prop="planStartDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="è®¡åˆ’ç»“æŸæ—¥æœŸ" align="center" prop="planEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="è´Ÿè´£äºº" align="center" prop="responsiblePerson" />
      <el-table-column label="è®¡åˆ’çŠ¶æ€ï¼ˆ1å¾…å®¡æ ¸ 2å·²å®¡æ ¸ 3ç”Ÿäº§ä¸­ 4å·²å®Œæˆ 5å·²å–æ¶ˆï¼‰" align="center" prop="planStatus" />
      <el-table-column label="å®¡æ ¸äºº" align="center" prop="approveBy" />
      <el-table-column label="å®¡æ ¸æ—¶é—´" align="center" prop="approveTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.approveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:plan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:plan:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改ç”Ÿäº§è®¡åˆ’对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="è®¡åˆ’ç¼–å·" prop="planCode">
          <el-input v-model="form.planCode" placeholder="请输入è®¡åˆ’ç¼–å·" />
        </el-form-item>
        <el-form-item label="è®¡åˆ’åç§°" prop="planName">
          <el-input v-model="form.planName" placeholder="请输入è®¡åˆ’åç§°" />
        </el-form-item>
        <el-form-item label="é…’å“ç§ç±»ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入é…’å“ç§ç±»ID" />
        </el-form-item>
        <el-form-item label="é…æ–¹æ¨¡æ¿ID" prop="templateId">
          <el-input v-model="form.templateId" placeholder="请输入é…æ–¹æ¨¡æ¿ID" />
        </el-form-item>
        <el-form-item label="ç›®æ ‡äº§é‡ï¼ˆLï¼‰" prop="targetOutput">
          <el-input v-model="form.targetOutput" placeholder="请输入ç›®æ ‡äº§é‡ï¼ˆLï¼‰" />
        </el-form-item>
        <el-form-item label="è®¡åˆ’å¼€å§‹æ—¥æœŸ" prop="planStartDate">
          <el-date-picker clearable
            v-model="form.planStartDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择è®¡åˆ’å¼€å§‹æ—¥æœŸ">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="è®¡åˆ’ç»“æŸæ—¥æœŸ" prop="planEndDate">
          <el-date-picker clearable
            v-model="form.planEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择è®¡åˆ’ç»“æŸæ—¥æœŸ">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="è´Ÿè´£äºº" prop="responsiblePerson">
          <el-input v-model="form.responsiblePerson" placeholder="请输入è´Ÿè´£äºº" />
        </el-form-item>
        <el-form-item label="å®¡æ ¸äºº" prop="approveBy">
          <el-input v-model="form.approveBy" placeholder="请输入å®¡æ ¸äºº" />
        </el-form-item>
        <el-form-item label="å®¡æ ¸æ—¶é—´" prop="approveTime">
          <el-date-picker clearable
            v-model="form.approveTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择å®¡æ ¸æ—¶é—´">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="å¤‡æ³¨" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰" />
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
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/plan"

export default {
  name: "Plan",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ç”Ÿäº§è®¡åˆ’表格数据
      planList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planCode: null,
        planName: null,
        categoryId: null,
        templateId: null,
        targetOutput: null,
        planStartDate: null,
        planEndDate: null,
        responsiblePerson: null,
        planStatus: null,
        approveBy: null,
        approveTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        planCode: [
          { required: true, message: "è®¡åˆ’ç¼–å·不能为空", trigger: "blur" }
        ],
        planName: [
          { required: true, message: "è®¡åˆ’åç§°不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "é…’å“ç§ç±»ID不能为空", trigger: "blur" }
        ],
        templateId: [
          { required: true, message: "é…æ–¹æ¨¡æ¿ID不能为空", trigger: "blur" }
        ],
        targetOutput: [
          { required: true, message: "ç›®æ ‡äº§é‡ï¼ˆLï¼‰不能为空", trigger: "blur" }
        ],
        planStartDate: [
          { required: true, message: "è®¡åˆ’å¼€å§‹æ—¥æœŸ不能为空", trigger: "blur" }
        ],
        planEndDate: [
          { required: true, message: "è®¡åˆ’ç»“æŸæ—¥æœŸ不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询ç”Ÿäº§è®¡åˆ’列表 */
    getList() {
      this.loading = true
      listPlan(this.queryParams).then(response => {
        this.planList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        planId: null,
        planCode: null,
        planName: null,
        categoryId: null,
        templateId: null,
        targetOutput: null,
        planStartDate: null,
        planEndDate: null,
        responsiblePerson: null,
        planStatus: null,
        approveBy: null,
        approveTime: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.planId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加ç”Ÿäº§è®¡åˆ’"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const planId = row.planId || this.ids
      getPlan(planId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改ç”Ÿäº§è®¡åˆ’"
      })
    },
    /** 提交按钮 */
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
    /** 删除按钮操作 */
    handleDelete(row) {
      const planIds = row.planId || this.ids
      this.$modal.confirm('是否确认删除ç”Ÿäº§è®¡åˆ’编号为"' + planIds + '"的数据项？').then(function() {
        return delPlan(planIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/plan/export', {
        ...this.queryParams
      }, `plan_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
