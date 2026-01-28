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
      <el-form-item label="å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰" prop="processStage">
        <el-input
          v-model="queryParams.processStage"
          placeholder="请输入å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®°å½•æ—¶é—´" prop="processTime">
        <el-date-picker clearable
          v-model="queryParams.processTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择è®°å½•æ—¶é—´">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰" prop="avgTemperature">
        <el-input
          v-model="queryParams.avgTemperature"
          placeholder="请输入å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰" prop="avgHumidity">
        <el-input
          v-model="queryParams.avgHumidity"
          placeholder="请输入å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ“ä½œäºº" prop="operatorName">
        <el-input
          v-model="queryParams.operatorName"
          placeholder="请输入æ“ä½œäºº"
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
          v-hasPermi="['system:process:add']"
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
          v-hasPermi="['system:process:edit']"
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
          v-hasPermi="['system:process:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:process:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="è®°å½•ID" align="center" prop="processId" />
      <el-table-column label="æ‰¹æ¬¡å·" align="center" prop="batchNo" />
      <el-table-column label="å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰" align="center" prop="processStage" />
      <el-table-column label="è®°å½•æ—¶é—´" align="center" prop="processTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.processTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰" align="center" prop="avgTemperature" />
      <el-table-column label="å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰" align="center" prop="avgHumidity" />
      <el-table-column label="å·¥åºæ•°æ®ï¼ˆJSONæ ¼å¼å­˜å‚¨å…¶ä»–å‚æ•°ï¼‰" align="center" prop="processData" />
      <el-table-column label="æ“ä½œäºº" align="center" prop="operatorName" />
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:process:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:process:remove']"
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

    <!-- 添加或修改ç”Ÿäº§è¿‡ç¨‹对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入æ‰¹æ¬¡å·" />
        </el-form-item>
        <el-form-item label="å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰" prop="processStage">
          <el-input v-model="form.processStage" placeholder="请输入å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰" />
        </el-form-item>
        <el-form-item label="è®°å½•æ—¶é—´" prop="processTime">
          <el-date-picker clearable
            v-model="form.processTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择è®°å½•æ—¶é—´">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰" prop="avgTemperature">
          <el-input v-model="form.avgTemperature" placeholder="请输入å¹³å‡æ¸©åº¦ï¼ˆâ„ƒï¼‰" />
        </el-form-item>
        <el-form-item label="å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰" prop="avgHumidity">
          <el-input v-model="form.avgHumidity" placeholder="请输入å¹³å‡æ¹¿åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="å·¥åºæ•°æ®ï¼ˆJSONæ ¼å¼å­˜å‚¨å…¶ä»–å‚æ•°ï¼‰" prop="processData">
          <el-input v-model="form.processData" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="æ“ä½œäºº" prop="operatorName">
          <el-input v-model="form.operatorName" placeholder="请输入æ“ä½œäºº" />
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
import { listProcess, getProcess, delProcess, addProcess, updateProcess } from "@/api/system/process"

export default {
  name: "Process",
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
      // ç”Ÿäº§è¿‡ç¨‹表格数据
      processList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        processStage: null,
        processTime: null,
        avgTemperature: null,
        avgHumidity: null,
        processData: null,
        operatorName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchNo: [
          { required: true, message: "æ‰¹æ¬¡å·不能为空", trigger: "blur" }
        ],
        processStage: [
          { required: true, message: "å·¥åºé˜¶æ®µï¼ˆ1å¤‡æ–™ 2å‘é…µ 3è’¸é¦ 4çŒè£…ï¼‰不能为空", trigger: "blur" }
        ],
        processTime: [
          { required: true, message: "è®°å½•æ—¶é—´不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询ç”Ÿäº§è¿‡ç¨‹列表 */
    getList() {
      this.loading = true
      listProcess(this.queryParams).then(response => {
        this.processList = response.rows
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
        processId: null,
        batchNo: null,
        processStage: null,
        processTime: null,
        avgTemperature: null,
        avgHumidity: null,
        processData: null,
        operatorName: null,
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
      this.ids = selection.map(item => item.processId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加ç”Ÿäº§è¿‡ç¨‹"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const processId = row.processId || this.ids
      getProcess(processId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改ç”Ÿäº§è¿‡ç¨‹"
      })
    },
    /** 提交按钮 */
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
    /** 删除按钮操作 */
    handleDelete(row) {
      const processIds = row.processId || this.ids
      this.$modal.confirm('是否确认删除ç”Ÿäº§è¿‡ç¨‹编号为"' + processIds + '"的数据项？').then(function() {
        return delProcess(processIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/process/export', {
        ...this.queryParams
      }, `process_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
