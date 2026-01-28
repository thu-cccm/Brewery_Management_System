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
      <el-form-item label="æˆæœ¬é¡¹ID" prop="costItemId">
        <el-input
          v-model="queryParams.costItemId"
          placeholder="请输入æˆæœ¬é¡¹ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰" prop="actualCost">
        <el-input
          v-model="queryParams.actualCost"
          placeholder="请输入å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰" prop="costQuantity">
        <el-input
          v-model="queryParams.costQuantity"
          placeholder="请输入æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®°å½•æ—¶é—´" prop="recordTime">
        <el-date-picker clearable
          v-model="queryParams.recordTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择è®°å½•æ—¶é—´">
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
          v-hasPermi="['system:cost:add']"
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
          v-hasPermi="['system:cost:edit']"
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
          v-hasPermi="['system:cost:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:cost:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="costList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="æˆæœ¬ID" align="center" prop="costId" />
      <el-table-column label="æ‰¹æ¬¡å·" align="center" prop="batchNo" />
      <el-table-column label="æˆæœ¬é¡¹ID" align="center" prop="costItemId" />
      <el-table-column label="å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰" align="center" prop="actualCost" />
      <el-table-column label="æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰" align="center" prop="costQuantity" />
      <el-table-column label="è®¡ç®—æ˜Žç»†" align="center" prop="calculationDetail" />
      <el-table-column label="è®°å½•æ—¶é—´" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cost:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cost:remove']"
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

    <!-- 添加或修改æ‰¹æ¬¡æˆæœ¬对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入æ‰¹æ¬¡å·" />
        </el-form-item>
        <el-form-item label="æˆæœ¬é¡¹ID" prop="costItemId">
          <el-input v-model="form.costItemId" placeholder="请输入æˆæœ¬é¡¹ID" />
        </el-form-item>
        <el-form-item label="å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰" prop="actualCost">
          <el-input v-model="form.actualCost" placeholder="请输入å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰" prop="costQuantity">
          <el-input v-model="form.costQuantity" placeholder="请输入æˆæœ¬æ•°é‡ï¼ˆå¦‚å·¥æ—¶ã€ç”¨ç”µé‡ï¼‰" />
        </el-form-item>
        <el-form-item label="è®¡ç®—æ˜Žç»†" prop="calculationDetail">
          <el-input v-model="form.calculationDetail" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="è®°å½•æ—¶é—´" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择è®°å½•æ—¶é—´">
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
import { listCost, getCost, delCost, addCost, updateCost } from "@/api/system/cost"

export default {
  name: "Cost",
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
      // æ‰¹æ¬¡æˆæœ¬表格数据
      costList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        costItemId: null,
        actualCost: null,
        costQuantity: null,
        calculationDetail: null,
        recordTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchNo: [
          { required: true, message: "æ‰¹æ¬¡å·不能为空", trigger: "blur" }
        ],
        costItemId: [
          { required: true, message: "æˆæœ¬é¡¹ID不能为空", trigger: "blur" }
        ],
        actualCost: [
          { required: true, message: "å®žé™…æˆæœ¬é‡‘é¢ï¼ˆå…ƒï¼‰不能为空", trigger: "blur" }
        ],
        recordTime: [
          { required: true, message: "è®°å½•æ—¶é—´不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询æ‰¹æ¬¡æˆæœ¬列表 */
    getList() {
      this.loading = true
      listCost(this.queryParams).then(response => {
        this.costList = response.rows
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
        costId: null,
        batchNo: null,
        costItemId: null,
        actualCost: null,
        costQuantity: null,
        calculationDetail: null,
        recordTime: null,
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
      this.ids = selection.map(item => item.costId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加æ‰¹æ¬¡æˆæœ¬"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const costId = row.costId || this.ids
      getCost(costId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改æ‰¹æ¬¡æˆæœ¬"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.costId != null) {
            updateCost(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCost(this.form).then(response => {
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
      const costIds = row.costId || this.ids
      this.$modal.confirm('是否确认删除æ‰¹æ¬¡æˆæœ¬编号为"' + costIds + '"的数据项？').then(function() {
        return delCost(costIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/cost/export', {
        ...this.queryParams
      }, `cost_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
