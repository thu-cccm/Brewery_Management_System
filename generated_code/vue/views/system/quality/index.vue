<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入æ‰¹æ¬¡å·"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è´¨æ£€æ ‡å‡†ID" prop="standardId">
        <el-input
          v-model="queryParams.standardId"
          placeholder="请输入è´¨æ£€æ ‡å‡†ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ£€æµ‹æ—¶é—´" prop="inspectTime">
        <el-date-picker clearable
          v-model="queryParams.inspectTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择æ£€æµ‹æ—¶é—´">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="æ£€æµ‹äºº" prop="inspectorName">
        <el-input
          v-model="queryParams.inspectorName"
          placeholder="请输入æ£€æµ‹äºº"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="alcoholBefore">
        <el-input
          v-model="queryParams.alcoholBefore"
          placeholder="请输入è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="alcoholAfter">
        <el-input
          v-model="queryParams.alcoholAfter"
          placeholder="请输入è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="alcoholActual">
        <el-input
          v-model="queryParams.alcoholActual"
          placeholder="请输入å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰" prop="alcoholError">
        <el-input
          v-model="queryParams.alcoholError"
          placeholder="请输入é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="äº§é…’çŽ‡ï¼ˆ%ï¼‰" prop="wineOutputRate">
        <el-input
          v-model="queryParams.wineOutputRate"
          placeholder="请输入äº§é…’çŽ‡ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è‰²æ³½æ£€æµ‹ç»“æžœ" prop="colorResult">
        <el-input
          v-model="queryParams.colorResult"
          placeholder="请输入è‰²æ³½æ£€æµ‹ç»“æžœ"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ°”å‘³æ£€æµ‹ç»“æžœ" prop="smellResult">
        <el-input
          v-model="queryParams.smellResult"
          placeholder="请输入æ°”å‘³æ£€æµ‹ç»“æžœ"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å£æ„Ÿæ£€æµ‹ç»“æžœ" prop="tasteResult">
        <el-input
          v-model="queryParams.tasteResult"
          placeholder="请输入å£æ„Ÿæ£€æµ‹ç»“æžœ"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰" prop="qualityResult">
        <el-input
          v-model="queryParams.qualityResult"
          placeholder="请输入è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰" prop="qualityScore">
        <el-input
          v-model="queryParams.qualityScore"
          placeholder="请输入è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:quality:add']"
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
          v-hasPermi="['system:quality:edit']"
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
          v-hasPermi="['system:quality:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:quality:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="qualityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="è®°å½•ID" align="center" prop="recordId" />
      <el-table-column label="æ‰¹æ¬¡å·" align="center" prop="batchNo" />
      <el-table-column label="è´¨æ£€æ ‡å‡†ID" align="center" prop="standardId" />
      <el-table-column label="æ£€æµ‹æ—¶é—´" align="center" prop="inspectTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="æ£€æµ‹äºº" align="center" prop="inspectorName" />
      <el-table-column label="è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰" align="center" prop="alcoholBefore" />
      <el-table-column label="è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰" align="center" prop="alcoholAfter" />
      <el-table-column label="å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰" align="center" prop="alcoholActual" />
      <el-table-column label="é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰" align="center" prop="alcoholError" />
      <el-table-column label="äº§é…’çŽ‡ï¼ˆ%ï¼‰" align="center" prop="wineOutputRate" />
      <el-table-column label="è‰²æ³½æ£€æµ‹ç»“æžœ" align="center" prop="colorResult" />
      <el-table-column label="æ°”å‘³æ£€æµ‹ç»“æžœ" align="center" prop="smellResult" />
      <el-table-column label="å£æ„Ÿæ£€æµ‹ç»“æžœ" align="center" prop="tasteResult" />
      <el-table-column label="è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰" align="center" prop="qualityResult" />
      <el-table-column label="è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰" align="center" prop="qualityScore" />
      <el-table-column label="ä¸åˆæ ¼åŽŸå› " align="center" prop="unqualifiedReason" />
      <el-table-column label="å¤„ç†å»ºè®®" align="center" prop="handleSuggestion" />
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:quality:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:quality:remove']"
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

    <!-- 添加或修改è´¨æ£€è®°å½•对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入æ‰¹æ¬¡å·" />
        </el-form-item>
        <el-form-item label="è´¨æ£€æ ‡å‡†ID" prop="standardId">
          <el-input v-model="form.standardId" placeholder="请输入è´¨æ£€æ ‡å‡†ID" />
        </el-form-item>
        <el-form-item label="æ£€æµ‹æ—¶é—´" prop="inspectTime">
          <el-date-picker clearable
            v-model="form.inspectTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择æ£€æµ‹æ—¶é—´">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="æ£€æµ‹äºº" prop="inspectorName">
          <el-input v-model="form.inspectorName" placeholder="请输入æ£€æµ‹äºº" />
        </el-form-item>
        <el-form-item label="è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="alcoholBefore">
          <el-input v-model="form.alcoholBefore" placeholder="请输入è’¸é¦å‰é…’ç²¾åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="alcoholAfter">
          <el-input v-model="form.alcoholAfter" placeholder="请输入è’¸é¦åŽé…’ç²¾åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="alcoholActual">
          <el-input v-model="form.alcoholActual" placeholder="请输入å®žé™…é…’ç²¾åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰" prop="alcoholError">
          <el-input v-model="form.alcoholError" placeholder="请输入é…’ç²¾åº¦è¯¯å·®ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="äº§é…’çŽ‡ï¼ˆ%ï¼‰" prop="wineOutputRate">
          <el-input v-model="form.wineOutputRate" placeholder="请输入äº§é…’çŽ‡ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="è‰²æ³½æ£€æµ‹ç»“æžœ" prop="colorResult">
          <el-input v-model="form.colorResult" placeholder="请输入è‰²æ³½æ£€æµ‹ç»“æžœ" />
        </el-form-item>
        <el-form-item label="æ°”å‘³æ£€æµ‹ç»“æžœ" prop="smellResult">
          <el-input v-model="form.smellResult" placeholder="请输入æ°”å‘³æ£€æµ‹ç»“æžœ" />
        </el-form-item>
        <el-form-item label="å£æ„Ÿæ£€æµ‹ç»“æžœ" prop="tasteResult">
          <el-input v-model="form.tasteResult" placeholder="请输入å£æ„Ÿæ£€æµ‹ç»“æžœ" />
        </el-form-item>
        <el-form-item label="è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰" prop="qualityResult">
          <el-input v-model="form.qualityResult" placeholder="请输入è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰" />
        </el-form-item>
        <el-form-item label="è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰" prop="qualityScore">
          <el-input v-model="form.qualityScore" placeholder="请输入è´¨é‡è¯„åˆ†ï¼ˆ0-100ï¼‰" />
        </el-form-item>
        <el-form-item label="ä¸åˆæ ¼åŽŸå› " prop="unqualifiedReason">
          <el-input v-model="form.unqualifiedReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="å¤„ç†å»ºè®®" prop="handleSuggestion">
          <el-input v-model="form.handleSuggestion" type="textarea" placeholder="请输入内容" />
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
import { listQuality, getQuality, delQuality, addQuality, updateQuality } from "@/api/system/quality"

export default {
  name: "Quality",
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
      // è´¨æ£€è®°å½•表格数据
      qualityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        standardId: null,
        inspectTime: null,
        inspectorName: null,
        alcoholBefore: null,
        alcoholAfter: null,
        alcoholActual: null,
        alcoholError: null,
        wineOutputRate: null,
        colorResult: null,
        smellResult: null,
        tasteResult: null,
        qualityResult: null,
        qualityScore: null,
        unqualifiedReason: null,
        handleSuggestion: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchNo: [
          { required: true, message: "æ‰¹æ¬¡å·不能为空", trigger: "blur" }
        ],
        standardId: [
          { required: true, message: "è´¨æ£€æ ‡å‡†ID不能为空", trigger: "blur" }
        ],
        inspectTime: [
          { required: true, message: "æ£€æµ‹æ—¶é—´不能为空", trigger: "blur" }
        ],
        inspectorName: [
          { required: true, message: "æ£€æµ‹äºº不能为空", trigger: "blur" }
        ],
        qualityResult: [
          { required: true, message: "è´¨æ£€ç»“æžœï¼ˆ1åˆæ ¼ 2ä¸åˆæ ¼ 3å¾…å¤æ£€ï¼‰不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询è´¨æ£€è®°å½•列表 */
    getList() {
      this.loading = true
      listQuality(this.queryParams).then(response => {
        this.qualityList = response.rows
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
        recordId: null,
        batchNo: null,
        standardId: null,
        inspectTime: null,
        inspectorName: null,
        alcoholBefore: null,
        alcoholAfter: null,
        alcoholActual: null,
        alcoholError: null,
        wineOutputRate: null,
        colorResult: null,
        smellResult: null,
        tasteResult: null,
        qualityResult: null,
        qualityScore: null,
        unqualifiedReason: null,
        handleSuggestion: null,
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
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加è´¨æ£€è®°å½•"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getQuality(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改è´¨æ£€è®°å½•"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateQuality(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addQuality(this.form).then(response => {
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
      const recordIds = row.recordId || this.ids
      this.$modal.confirm('是否确认删除è´¨æ£€è®°å½•编号为"' + recordIds + '"的数据项？').then(function() {
        return delQuality(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/quality/export', {
        ...this.queryParams
      }, `quality_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
