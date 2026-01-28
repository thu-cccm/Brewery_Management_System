<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="原料ID" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入原料ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="?动数?（正数入库，负数出库）" prop="changeQuantity">
        <el-input
          v-model="queryParams.changeQuantity"
          placeholder="请输入动数?（正数入库，负数出库）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="?动?库存" prop="beforeQuantity">
        <el-input
          v-model="queryParams.beforeQuantity"
          placeholder="请输入动库存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="?动?库存" prop="afterQuantity">
        <el-input
          v-model="queryParams.afterQuantity"
          placeholder="请输入动库存"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关批次编号（入库时填供应商批次，出库时填生产批次）" prop="relatedBatchNo">
        <el-input
          v-model="queryParams.relatedBatchNo"
          placeholder="请输入关批次编号（入库时填供应商批次，出库时填生产批次）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人" prop="operatorName">
        <el-input
          v-model="queryParams.operatorName"
          placeholder="请输入操作人"
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
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="原料ID" align="center" prop="materialId" />
      <el-table-column label="?动类型（1入库 2出库 3盘点 4报?）" align="center" prop="changeType" />
      <el-table-column label="?动数?（正数入库，负数出库）" align="center" prop="changeQuantity" />
      <el-table-column label="?动?库存" align="center" prop="beforeQuantity" />
      <el-table-column label="?动?库存" align="center" prop="afterQuantity" />
      <el-table-column label="关批次编号（入库时填供应商批次，出库时填生产批次）" align="center" prop="relatedBatchNo" />
      <el-table-column label="操作人" align="center" prop="operatorName" />
      <el-table-column label="?动原因" align="center" prop="changeReason" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:stock:edit']"
          >操作</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:stock:remove']"
          >操作</el-button>
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

    <!-- 删除对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="原料ID" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入原料ID" />
        </el-form-item>
        <el-form-item label="?动数?（正数入库，负数出库）" prop="changeQuantity">
          <el-input v-model="form.changeQuantity" placeholder="请输入动数?（正数入库，负数出库）" />
        </el-form-item>
        <el-form-item label="?动?库存" prop="beforeQuantity">
          <el-input v-model="form.beforeQuantity" placeholder="请输入动库存" />
        </el-form-item>
        <el-form-item label="?动?库存" prop="afterQuantity">
          <el-input v-model="form.afterQuantity" placeholder="请输入动库存" />
        </el-form-item>
        <el-form-item label="关批次编号（入库时填供应商批次，出库时填生产批次）" prop="relatedBatchNo">
          <el-input v-model="form.relatedBatchNo" placeholder="请输入关批次编号（入库时填供应商批次，出库时填生产批次）" />
        </el-form-item>
        <el-form-item label="操作人" prop="operatorName">
          <el-input v-model="form.operatorName" placeholder="请输入操作人" />
        </el-form-item>
        <el-form-item label="?动原因" prop="changeReason">
          <el-input v-model="form.changeReason" type="textarea" placeholder="删除" />
        </el-form-item>
        <el-form-item label="删除标志（0代表存在 2代表删除）" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志（0代表存在 2代表删除）" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">确 定</el-button>
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
      // loading: true,
      // ids: [],
      // 删除
      single: true,
      // 删除
      multiple: true,
      // showSearch: true,
      // total: 0,
      // 库存动请输入
      stockList: [],
      // 删除
      title: "",
      // open: false,
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
      form: {},
      rules: {
        materialId: [
          { required: true, message: "原料ID请输入", trigger: "blur" }
        ],
        changeType: [
          { required: true, message: "?动类型（1入库 2出库 3盘点 4报?）请输入", trigger: "change" }
        ],
        changeQuantity: [
          { required: true, message: "?动数?（正数入库，负数出库）请输入", trigger: "blur" }
        ],
        beforeQuantity: [
          { required: true, message: "?动?库存请输入", trigger: "blur" }
        ],
        afterQuantity: [
          { required: true, message: "?动?库存请输入", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 操作库存动操作 */
    getList() {
      this.loading = true
      listStock(this.queryParams).then(response => {
        this.stockList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    cancel() {
      this.open = false
      this.reset()
    },
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
    /** 请输入 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 请输入 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 请输入 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "操作库存动"
    },
    /** 请输入 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getStock(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "操作库存动"
      })
    },
    /** 请输入 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateStock(this.form).then(response => {
              this.$modal.msgSuccess("请输入")
              this.open = false
              this.getList()
            })
          } else {
            addStock(this.form).then(response => {
              this.$modal.msgSuccess("请输入")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 请输入 */
    handleDelete(row) {
      const recordIds = row.recordId || this.ids
      this.$modal.confirm('请输入库存动请输入"' + recordIds + '"删除').then(function() {
        return delStock(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("请输入")
      }).catch(() => {})
    },
    /** 请输入 */
    handleExport() {
      this.download('system/stock/export', {
        ...this.queryParams
      }, `stock_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
