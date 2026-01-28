<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="åŽŸæ–™ID" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入åŽŸæ–™ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰" prop="changeQuantity">
        <el-input
          v-model="queryParams.changeQuantity"
          placeholder="请输入å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å˜åŠ¨å‰åº“å­˜" prop="beforeQuantity">
        <el-input
          v-model="queryParams.beforeQuantity"
          placeholder="请输入å˜åŠ¨å‰åº“å­˜"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å˜åŠ¨åŽåº“å­˜" prop="afterQuantity">
        <el-input
          v-model="queryParams.afterQuantity"
          placeholder="请输入å˜åŠ¨åŽåº“å­˜"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰" prop="relatedBatchNo">
        <el-input
          v-model="queryParams.relatedBatchNo"
          placeholder="请输入å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰"
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
          v-hasPermi="['system:stock:add']"
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
          v-hasPermi="['system:stock:edit']"
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
          v-hasPermi="['system:stock:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:stock:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="è®°å½•ID" align="center" prop="recordId" />
      <el-table-column label="åŽŸæ–™ID" align="center" prop="materialId" />
      <el-table-column label="å˜åŠ¨ç±»åž‹ï¼ˆ1å…¥åº“ 2å‡ºåº“ 3ç›˜ç‚¹ 4æŠ¥æŸï¼‰" align="center" prop="changeType" />
      <el-table-column label="å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰" align="center" prop="changeQuantity" />
      <el-table-column label="å˜åŠ¨å‰åº“å­˜" align="center" prop="beforeQuantity" />
      <el-table-column label="å˜åŠ¨åŽåº“å­˜" align="center" prop="afterQuantity" />
      <el-table-column label="å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰" align="center" prop="relatedBatchNo" />
      <el-table-column label="æ“ä½œäºº" align="center" prop="operatorName" />
      <el-table-column label="å˜åŠ¨åŽŸå› " align="center" prop="changeReason" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:stock:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:stock:remove']"
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

    <!-- 添加或修改åº“å­˜å˜åŠ¨对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="åŽŸæ–™ID" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入åŽŸæ–™ID" />
        </el-form-item>
        <el-form-item label="å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰" prop="changeQuantity">
          <el-input v-model="form.changeQuantity" placeholder="请输入å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰" />
        </el-form-item>
        <el-form-item label="å˜åŠ¨å‰åº“å­˜" prop="beforeQuantity">
          <el-input v-model="form.beforeQuantity" placeholder="请输入å˜åŠ¨å‰åº“å­˜" />
        </el-form-item>
        <el-form-item label="å˜åŠ¨åŽåº“å­˜" prop="afterQuantity">
          <el-input v-model="form.afterQuantity" placeholder="请输入å˜åŠ¨åŽåº“å­˜" />
        </el-form-item>
        <el-form-item label="å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰" prop="relatedBatchNo">
          <el-input v-model="form.relatedBatchNo" placeholder="请输入å…³è”æ‰¹æ¬¡å·ï¼ˆå…¥åº“æ—¶å¡«ä¾›åº”å•†æ‰¹æ¬¡ï¼Œå‡ºåº“æ—¶å¡«ç”Ÿäº§æ‰¹æ¬¡ï¼‰" />
        </el-form-item>
        <el-form-item label="æ“ä½œäºº" prop="operatorName">
          <el-input v-model="form.operatorName" placeholder="请输入æ“ä½œäºº" />
        </el-form-item>
        <el-form-item label="å˜åŠ¨åŽŸå› " prop="changeReason">
          <el-input v-model="form.changeReason" type="textarea" placeholder="请输入内容" />
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
import { listStock, getStock, delStock, addStock, updateStock } from "@/api/system/stock"

export default {
  name: "Stock",
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
      // åº“å­˜å˜åŠ¨表格数据
      stockList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialId: null,
        changeType: null,
        changeQuantity: null,
        beforeQuantity: null,
        afterQuantity: null,
        relatedBatchNo: null,
        operatorName: null,
        changeReason: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialId: [
          { required: true, message: "åŽŸæ–™ID不能为空", trigger: "blur" }
        ],
        changeType: [
          { required: true, message: "å˜åŠ¨ç±»åž‹ï¼ˆ1å…¥åº“ 2å‡ºåº“ 3ç›˜ç‚¹ 4æŠ¥æŸï¼‰不能为空", trigger: "change" }
        ],
        changeQuantity: [
          { required: true, message: "å˜åŠ¨æ•°é‡ï¼ˆæ­£æ•°å…¥åº“ï¼Œè´Ÿæ•°å‡ºåº“ï¼‰不能为空", trigger: "blur" }
        ],
        beforeQuantity: [
          { required: true, message: "å˜åŠ¨å‰åº“å­˜不能为空", trigger: "blur" }
        ],
        afterQuantity: [
          { required: true, message: "å˜åŠ¨åŽåº“å­˜不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询åº“å­˜å˜åŠ¨列表 */
    getList() {
      this.loading = true
      listStock(this.queryParams).then(response => {
        this.stockList = response.rows
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
        materialId: null,
        changeType: null,
        changeQuantity: null,
        beforeQuantity: null,
        afterQuantity: null,
        relatedBatchNo: null,
        operatorName: null,
        changeReason: null,
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
      this.title = "添加åº“å­˜å˜åŠ¨"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getStock(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改åº“å­˜å˜åŠ¨"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateStock(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStock(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除åº“å­˜å˜åŠ¨编号为"' + recordIds + '"的数据项？').then(function() {
        return delStock(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/stock/export', {
        ...this.queryParams
      }, `stock_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
