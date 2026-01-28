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
      <el-form-item label="åŽŸæ–™ID" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入åŽŸæ–™ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰" prop="materialBatchNo">
        <el-input
          v-model="queryParams.materialBatchNo"
          placeholder="请输入åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®¡åˆ’ç”¨é‡" prop="plannedQuantity">
        <el-input
          v-model="queryParams.plannedQuantity"
          placeholder="请输入è®¡åˆ’ç”¨é‡"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å®žé™…ç”¨é‡" prop="actualQuantity">
        <el-input
          v-model="queryParams.actualQuantity"
          placeholder="请输入å®žé™…ç”¨é‡"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" prop="materialCost">
        <el-input
          v-model="queryParams.materialCost"
          placeholder="请输入åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ä½¿ç”¨æ—¶é—´" prop="useTime">
        <el-date-picker clearable
          v-model="queryParams.useTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择ä½¿ç”¨æ—¶é—´">
        </el-date-picker>
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
          v-hasPermi="['system:batchmaterial:add']"
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
          v-hasPermi="['system:batchmaterial:edit']"
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
          v-hasPermi="['system:batchmaterial:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:batchmaterial:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="batchmaterialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="è®°å½•ID" align="center" prop="recordId" />
      <el-table-column label="æ‰¹æ¬¡å·" align="center" prop="batchNo" />
      <el-table-column label="åŽŸæ–™ID" align="center" prop="materialId" />
      <el-table-column label="åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰" align="center" prop="materialBatchNo" />
      <el-table-column label="è®¡åˆ’ç”¨é‡" align="center" prop="plannedQuantity" />
      <el-table-column label="å®žé™…ç”¨é‡" align="center" prop="actualQuantity" />
      <el-table-column label="åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="materialCost" />
      <el-table-column label="ä½¿ç”¨æ—¶é—´" align="center" prop="useTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.useTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="æ“ä½œäºº" align="center" prop="operatorName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:batchmaterial:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:batchmaterial:remove']"
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

    <!-- 添加或修改æ‰¹æ¬¡åŽŸæ–™对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ‰¹æ¬¡å·" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入æ‰¹æ¬¡å·" />
        </el-form-item>
        <el-form-item label="åŽŸæ–™ID" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入åŽŸæ–™ID" />
        </el-form-item>
        <el-form-item label="åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰" prop="materialBatchNo">
          <el-input v-model="form.materialBatchNo" placeholder="请输入åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰" />
        </el-form-item>
        <el-form-item label="è®¡åˆ’ç”¨é‡" prop="plannedQuantity">
          <el-input v-model="form.plannedQuantity" placeholder="请输入è®¡åˆ’ç”¨é‡" />
        </el-form-item>
        <el-form-item label="å®žé™…ç”¨é‡" prop="actualQuantity">
          <el-input v-model="form.actualQuantity" placeholder="请输入å®žé™…ç”¨é‡" />
        </el-form-item>
        <el-form-item label="åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" prop="materialCost">
          <el-input v-model="form.materialCost" placeholder="请输入åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="ä½¿ç”¨æ—¶é—´" prop="useTime">
          <el-date-picker clearable
            v-model="form.useTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择ä½¿ç”¨æ—¶é—´">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="æ“ä½œäºº" prop="operatorName">
          <el-input v-model="form.operatorName" placeholder="请输入æ“ä½œäºº" />
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
import { listBatchmaterial, getBatchmaterial, delBatchmaterial, addBatchmaterial, updateBatchmaterial } from "@/api/system/batchmaterial"

export default {
  name: "Batchmaterial",
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
      // æ‰¹æ¬¡åŽŸæ–™表格数据
      batchmaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        materialId: null,
        materialBatchNo: null,
        plannedQuantity: null,
        actualQuantity: null,
        materialCost: null,
        useTime: null,
        operatorName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchNo: [
          { required: true, message: "æ‰¹æ¬¡å·不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "åŽŸæ–™ID不能为空", trigger: "blur" }
        ],
        plannedQuantity: [
          { required: true, message: "è®¡åˆ’ç”¨é‡不能为空", trigger: "blur" }
        ],
        actualQuantity: [
          { required: true, message: "å®žé™…ç”¨é‡不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询æ‰¹æ¬¡åŽŸæ–™列表 */
    getList() {
      this.loading = true
      listBatchmaterial(this.queryParams).then(response => {
        this.batchmaterialList = response.rows
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
        materialId: null,
        materialBatchNo: null,
        plannedQuantity: null,
        actualQuantity: null,
        materialCost: null,
        useTime: null,
        operatorName: null,
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
      this.title = "添加æ‰¹æ¬¡åŽŸæ–™"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getBatchmaterial(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改æ‰¹æ¬¡åŽŸæ–™"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateBatchmaterial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addBatchmaterial(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除æ‰¹æ¬¡åŽŸæ–™编号为"' + recordIds + '"的数据项？').then(function() {
        return delBatchmaterial(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/batchmaterial/export', {
        ...this.queryParams
      }, `batchmaterial_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
