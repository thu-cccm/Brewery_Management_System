<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="åŽŸæ–™ç¼–ç " prop="materialCode">
        <el-input
          v-model="queryParams.materialCode"
          placeholder="请输入åŽŸæ–™ç¼–ç "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="åŽŸæ–™åç§°" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入åŽŸæ–™åç§°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰" prop="materialUnit">
        <el-input
          v-model="queryParams.materialUnit"
          placeholder="请输入è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è§„æ ¼è¯´æ˜Ž" prop="materialSpec">
        <el-input
          v-model="queryParams.materialSpec"
          placeholder="请输入è§„æ ¼è¯´æ˜Ž"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å•ä»·ï¼ˆå…ƒï¼‰" prop="unitPrice">
        <el-input
          v-model="queryParams.unitPrice"
          placeholder="请输入å•ä»·ï¼ˆå…ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ä¾›åº”å•†åç§°" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入ä¾›åº”å•†åç§°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ä¾›åº”å•†è”ç³»æ–¹å¼" prop="supplierContact">
        <el-input
          v-model="queryParams.supplierContact"
          placeholder="请输入ä¾›åº”å•†è”ç³»æ–¹å¼"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å½“å‰åº“å­˜æ•°é‡" prop="stockQuantity">
        <el-input
          v-model="queryParams.stockQuantity"
          placeholder="请输入å½“å‰åº“å­˜æ•°é‡"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="åº“å­˜é¢„è­¦é˜ˆå€¼" prop="stockWarningValue">
        <el-input
          v-model="queryParams.stockWarningValue"
          placeholder="请输入åº“å­˜é¢„è­¦é˜ˆå€¼"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isWarning">
        <el-input
          v-model="queryParams.isWarning"
          placeholder="请输入æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰"
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
          v-hasPermi="['system:material:add']"
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
          v-hasPermi="['system:material:edit']"
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
          v-hasPermi="['system:material:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:material:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="åŽŸæ–™ID" align="center" prop="materialId" />
      <el-table-column label="åŽŸæ–™ç¼–ç " align="center" prop="materialCode" />
      <el-table-column label="åŽŸæ–™åç§°" align="center" prop="materialName" />
      <el-table-column label="åŽŸæ–™ç±»åž‹ï¼ˆ1ç²®é£Ÿç±» 2è¾…æ–™ç±» 3åŒ…è£…ç±»ï¼‰" align="center" prop="materialType" />
      <el-table-column label="è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰" align="center" prop="materialUnit" />
      <el-table-column label="è§„æ ¼è¯´æ˜Ž" align="center" prop="materialSpec" />
      <el-table-column label="å•ä»·ï¼ˆå…ƒï¼‰" align="center" prop="unitPrice" />
      <el-table-column label="ä¾›åº”å•†åç§°" align="center" prop="supplierName" />
      <el-table-column label="ä¾›åº”å•†è”ç³»æ–¹å¼" align="center" prop="supplierContact" />
      <el-table-column label="å½“å‰åº“å­˜æ•°é‡" align="center" prop="stockQuantity" />
      <el-table-column label="åº“å­˜é¢„è­¦é˜ˆå€¼" align="center" prop="stockWarningValue" />
      <el-table-column label="æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰" align="center" prop="isWarning" />
      <el-table-column label="çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰" align="center" prop="status" />
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:material:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:material:remove']"
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

    <!-- 添加或修改åŽŸæ–™ä¿¡æ¯对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="åŽŸæ–™ç¼–ç " prop="materialCode">
          <el-input v-model="form.materialCode" placeholder="请输入åŽŸæ–™ç¼–ç " />
        </el-form-item>
        <el-form-item label="åŽŸæ–™åç§°" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入åŽŸæ–™åç§°" />
        </el-form-item>
        <el-form-item label="è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰" prop="materialUnit">
          <el-input v-model="form.materialUnit" placeholder="请输入è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰" />
        </el-form-item>
        <el-form-item label="è§„æ ¼è¯´æ˜Ž" prop="materialSpec">
          <el-input v-model="form.materialSpec" placeholder="请输入è§„æ ¼è¯´æ˜Ž" />
        </el-form-item>
        <el-form-item label="å•ä»·ï¼ˆå…ƒï¼‰" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入å•ä»·ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="ä¾›åº”å•†åç§°" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入ä¾›åº”å•†åç§°" />
        </el-form-item>
        <el-form-item label="ä¾›åº”å•†è”ç³»æ–¹å¼" prop="supplierContact">
          <el-input v-model="form.supplierContact" placeholder="请输入ä¾›åº”å•†è”ç³»æ–¹å¼" />
        </el-form-item>
        <el-form-item label="å½“å‰åº“å­˜æ•°é‡" prop="stockQuantity">
          <el-input v-model="form.stockQuantity" placeholder="请输入å½“å‰åº“å­˜æ•°é‡" />
        </el-form-item>
        <el-form-item label="åº“å­˜é¢„è­¦é˜ˆå€¼" prop="stockWarningValue">
          <el-input v-model="form.stockWarningValue" placeholder="请输入åº“å­˜é¢„è­¦é˜ˆå€¼" />
        </el-form-item>
        <el-form-item label="æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isWarning">
          <el-input v-model="form.isWarning" placeholder="请输入æ˜¯å¦é¢„è­¦ï¼ˆ0å¦ 1æ˜¯ï¼‰" />
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
import { listMaterial, getMaterial, delMaterial, addMaterial, updateMaterial } from "@/api/system/material"

export default {
  name: "Material",
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
      // åŽŸæ–™ä¿¡æ¯表格数据
      materialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialCode: null,
        materialName: null,
        materialType: null,
        materialUnit: null,
        materialSpec: null,
        unitPrice: null,
        supplierName: null,
        supplierContact: null,
        stockQuantity: null,
        stockWarningValue: null,
        isWarning: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialCode: [
          { required: true, message: "åŽŸæ–™ç¼–ç 不能为空", trigger: "blur" }
        ],
        materialName: [
          { required: true, message: "åŽŸæ–™åç§°不能为空", trigger: "blur" }
        ],
        materialType: [
          { required: true, message: "åŽŸæ–™ç±»åž‹ï¼ˆ1ç²®é£Ÿç±» 2è¾…æ–™ç±» 3åŒ…è£…ç±»ï¼‰不能为空", trigger: "change" }
        ],
        materialUnit: [
          { required: true, message: "è®¡é‡å•ä½ï¼ˆkg/L/ä¸ªï¼‰不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询åŽŸæ–™ä¿¡æ¯列表 */
    getList() {
      this.loading = true
      listMaterial(this.queryParams).then(response => {
        this.materialList = response.rows
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
        materialId: null,
        materialCode: null,
        materialName: null,
        materialType: null,
        materialUnit: null,
        materialSpec: null,
        unitPrice: null,
        supplierName: null,
        supplierContact: null,
        stockQuantity: null,
        stockWarningValue: null,
        isWarning: null,
        status: null,
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
      this.ids = selection.map(item => item.materialId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加åŽŸæ–™ä¿¡æ¯"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const materialId = row.materialId || this.ids
      getMaterial(materialId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改åŽŸæ–™ä¿¡æ¯"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.materialId != null) {
            updateMaterial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addMaterial(this.form).then(response => {
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
      const materialIds = row.materialId || this.ids
      this.$modal.confirm('是否确认删除åŽŸæ–™ä¿¡æ¯编号为"' + materialIds + '"的数据项？').then(function() {
        return delMaterial(materialIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/material/export', {
        ...this.queryParams
      }, `material_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
