<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="æŠ¥å‘Šç¼–å·" prop="reportNo">
        <el-input
          v-model="queryParams.reportNo"
          placeholder="请输入æŠ¥å‘Šç¼–å·"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入æ‰¹æ¬¡å·"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è´¨æ£€è®°å½•ID" prop="recordId">
        <el-input
          v-model="queryParams.recordId"
          placeholder="请输入è´¨æ£€è®°å½•ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æŠ¥å‘Šæ ‡é¢˜" prop="reportTitle">
        <el-input
          v-model="queryParams.reportTitle"
          placeholder="请输入æŠ¥å‘Šæ ‡é¢˜"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®¡æ‰¹äºº" prop="approveBy">
        <el-input
          v-model="queryParams.approveBy"
          placeholder="请输入å®¡æ‰¹äºº"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®¡æ‰¹æ—¶é—´" prop="approveTime">
        <el-date-picker clearable
          v-model="queryParams.approveTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择å®¡æ‰¹æ—¶é—´">
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
          v-hasPermi="['system:report:add']"
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
          v-hasPermi="['system:report:edit']"
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
          v-hasPermi="['system:report:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:report:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="æŠ¥å‘ŠID" align="center" prop="reportId" />
      <el-table-column label="æŠ¥å‘Šç¼–å·" align="center" prop="reportNo" />
      <el-table-column label="æ‰¹æ¬¡å·" align="center" prop="batchNo" />
      <el-table-column label="è´¨æ£€è®°å½•ID" align="center" prop="recordId" />
      <el-table-column label="æŠ¥å‘Šæ ‡é¢˜" align="center" prop="reportTitle" />
      <el-table-column label="æŠ¥å‘Šå†…å®¹ï¼ˆHTMLæ ¼å¼ï¼‰" align="center" prop="reportContent" />
      <el-table-column label="æŠ¥å‘ŠçŠ¶æ€ï¼ˆ1è‰ç¨¿ 2å·²å‘å¸ƒ 3å·²å½’æ¡£ï¼‰" align="center" prop="reportStatus" />
      <el-table-column label="å®¡æ‰¹çŠ¶æ€ï¼ˆ0å¾…å®¡æ‰¹ 1å·²é€šè¿‡ 2å·²é©³å›žï¼‰" align="center" prop="approveStatus" />
      <el-table-column label="å®¡æ‰¹äºº" align="center" prop="approveBy" />
      <el-table-column label="å®¡æ‰¹æ—¶é—´" align="center" prop="approveTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.approveTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:report:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:report:remove']"
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

    <!-- 添加或修改è´¨æ£€æŠ¥å‘Š对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æŠ¥å‘Šç¼–å·" prop="reportNo">
          <el-input v-model="form.reportNo" placeholder="请输入æŠ¥å‘Šç¼–å·" />
        </el-form-item>
        <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入æ‰¹æ¬¡å·" />
        </el-form-item>
        <el-form-item label="è´¨æ£€è®°å½•ID" prop="recordId">
          <el-input v-model="form.recordId" placeholder="请输入è´¨æ£€è®°å½•ID" />
        </el-form-item>
        <el-form-item label="æŠ¥å‘Šæ ‡é¢˜" prop="reportTitle">
          <el-input v-model="form.reportTitle" placeholder="请输入æŠ¥å‘Šæ ‡é¢˜" />
        </el-form-item>
        <el-form-item label="æŠ¥å‘Šå†…å®¹ï¼ˆHTMLæ ¼å¼ï¼‰">
          <editor v-model="form.reportContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="å®¡æ‰¹äºº" prop="approveBy">
          <el-input v-model="form.approveBy" placeholder="请输入å®¡æ‰¹äºº" />
        </el-form-item>
        <el-form-item label="å®¡æ‰¹æ—¶é—´" prop="approveTime">
          <el-date-picker clearable
            v-model="form.approveTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择å®¡æ‰¹æ—¶é—´">
          </el-date-picker>
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
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/system/report"

export default {
  name: "Report",
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
      // è´¨æ£€æŠ¥å‘Š表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportNo: null,
        batchNo: null,
        recordId: null,
        reportTitle: null,
        reportContent: null,
        reportStatus: null,
        approveStatus: null,
        approveBy: null,
        approveTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        reportNo: [
          { required: true, message: "æŠ¥å‘Šç¼–å·不能为空", trigger: "blur" }
        ],
        batchNo: [
          { required: true, message: "æ‰¹æ¬¡å·不能为空", trigger: "blur" }
        ],
        recordId: [
          { required: true, message: "è´¨æ£€è®°å½•ID不能为空", trigger: "blur" }
        ],
        reportTitle: [
          { required: true, message: "æŠ¥å‘Šæ ‡é¢˜不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询è´¨æ£€æŠ¥å‘Š列表 */
    getList() {
      this.loading = true
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows
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
        reportId: null,
        reportNo: null,
        batchNo: null,
        recordId: null,
        reportTitle: null,
        reportContent: null,
        reportStatus: null,
        approveStatus: null,
        approveBy: null,
        approveTime: null,
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
      this.ids = selection.map(item => item.reportId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加è´¨æ£€æŠ¥å‘Š"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const reportId = row.reportId || this.ids
      getReport(reportId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改è´¨æ£€æŠ¥å‘Š"
      })
    },
    /** 提交按钮 */
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
    /** 删除按钮操作 */
    handleDelete(row) {
      const reportIds = row.reportId || this.ids
      this.$modal.confirm('是否确认删除è´¨æ£€æŠ¥å‘Š编号为"' + reportIds + '"的数据项？').then(function() {
        return delReport(reportIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/report/export', {
        ...this.queryParams
      }, `report_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
