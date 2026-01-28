<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="æ¨¡æ¿ç¼–ç " prop="templateCode">
        <el-input
          v-model="queryParams.templateCode"
          placeholder="请输入æ¨¡æ¿ç¼–ç "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ¨¡æ¿åç§°" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="请输入æ¨¡æ¿åç§°"
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
      <el-form-item label="åŸºå‡†äº§é‡ï¼ˆLï¼‰" prop="baseOutput">
        <el-input
          v-model="queryParams.baseOutput"
          placeholder="请输入åŸºå‡†äº§é‡ï¼ˆLï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="expectedAlcohol">
        <el-input
          v-model="queryParams.expectedAlcohol"
          placeholder="请输入é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isDefault">
        <el-input
          v-model="queryParams.isDefault"
          placeholder="请输入æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰"
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
          v-hasPermi="['system:template:add']"
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
          v-hasPermi="['system:template:edit']"
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
          v-hasPermi="['system:template:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:template:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="æ¨¡æ¿ID" align="center" prop="templateId" />
      <el-table-column label="æ¨¡æ¿ç¼–ç " align="center" prop="templateCode" />
      <el-table-column label="æ¨¡æ¿åç§°" align="center" prop="templateName" />
      <el-table-column label="é…’å“ç§ç±»ID" align="center" prop="categoryId" />
      <el-table-column label="åŸºå‡†äº§é‡ï¼ˆLï¼‰" align="center" prop="baseOutput" />
      <el-table-column label="é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰" align="center" prop="expectedAlcohol" />
      <el-table-column label="é…æ–¹è¯´æ˜Ž" align="center" prop="formulaDescription" />
      <el-table-column label="æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰" align="center" prop="isDefault" />
      <el-table-column label="çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:template:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:template:remove']"
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

    <!-- 添加或修改é…æ–¹æ¨¡æ¿对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ¨¡æ¿ç¼–ç " prop="templateCode">
          <el-input v-model="form.templateCode" placeholder="请输入æ¨¡æ¿ç¼–ç " />
        </el-form-item>
        <el-form-item label="æ¨¡æ¿åç§°" prop="templateName">
          <el-input v-model="form.templateName" placeholder="请输入æ¨¡æ¿åç§°" />
        </el-form-item>
        <el-form-item label="é…’å“ç§ç±»ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入é…’å“ç§ç±»ID" />
        </el-form-item>
        <el-form-item label="åŸºå‡†äº§é‡ï¼ˆLï¼‰" prop="baseOutput">
          <el-input v-model="form.baseOutput" placeholder="请输入åŸºå‡†äº§é‡ï¼ˆLï¼‰" />
        </el-form-item>
        <el-form-item label="é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰" prop="expectedAlcohol">
          <el-input v-model="form.expectedAlcohol" placeholder="请输入é¢„æœŸé…’ç²¾åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="é…æ–¹è¯´æ˜Ž" prop="formulaDescription">
          <el-input v-model="form.formulaDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isDefault">
          <el-input v-model="form.isDefault" placeholder="请输入æ˜¯å¦é»˜è®¤é…æ–¹ï¼ˆ0å¦ 1æ˜¯ï¼‰" />
        </el-form-item>
        <el-form-item label="åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰" />
        </el-form-item>
        <el-divider content-position="center">é…æ–¹æ˜Žç»†信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBrewFormulaDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBrewFormulaDetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="brewFormulaDetailList" :row-class-name="rowBrewFormulaDetailIndex" @selection-change="handleBrewFormulaDetailSelectionChange" ref="brewFormulaDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="åŽŸæ–™ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialId" placeholder="请输入åŽŸæ–™ID" />
            </template>
          </el-table-column>
          <el-table-column label="é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰" prop="materialRatio" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialRatio" placeholder="请输入é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰" />
            </template>
          </el-table-column>
          <el-table-column label="åŸºå‡†ç”¨é‡" prop="baseQuantity" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.baseQuantity" placeholder="请输入åŸºå‡†ç”¨é‡" />
            </template>
          </el-table-column>
          <el-table-column label="æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isMainMaterial" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.isMainMaterial" placeholder="请输入æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰" />
            </template>
          </el-table-column>
          <el-table-column label="æŽ’åº" prop="sortOrder" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sortOrder" placeholder="请输入æŽ’åº" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTemplate, getTemplate, delTemplate, addTemplate, updateTemplate } from "@/api/system/template"

export default {
  name: "Template",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBrewFormulaDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // é…æ–¹æ¨¡æ¿表格数据
      templateList: [],
      // é…æ–¹æ˜Žç»†表格数据
      brewFormulaDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateCode: null,
        templateName: null,
        categoryId: null,
        baseOutput: null,
        expectedAlcohol: null,
        formulaDescription: null,
        isDefault: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateCode: [
          { required: true, message: "æ¨¡æ¿ç¼–ç 不能为空", trigger: "blur" }
        ],
        templateName: [
          { required: true, message: "æ¨¡æ¿åç§°不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "é…’å“ç§ç±»ID不能为空", trigger: "blur" }
        ],
        baseOutput: [
          { required: true, message: "åŸºå‡†äº§é‡ï¼ˆLï¼‰不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询é…æ–¹æ¨¡æ¿列表 */
    getList() {
      this.loading = true
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows
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
        templateId: null,
        templateCode: null,
        templateName: null,
        categoryId: null,
        baseOutput: null,
        expectedAlcohol: null,
        formulaDescription: null,
        isDefault: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null
      }
      this.brewFormulaDetailList = []
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
      this.ids = selection.map(item => item.templateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加é…æ–¹æ¨¡æ¿"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const templateId = row.templateId || this.ids
      getTemplate(templateId).then(response => {
        this.form = response.data
        this.brewFormulaDetailList = response.data.brewFormulaDetailList
        this.open = true
        this.title = "修改é…æ–¹æ¨¡æ¿"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.brewFormulaDetailList = this.brewFormulaDetailList
          if (this.form.templateId != null) {
            updateTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addTemplate(this.form).then(response => {
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
      const templateIds = row.templateId || this.ids
      this.$modal.confirm('是否确认删除é…æ–¹æ¨¡æ¿编号为"' + templateIds + '"的数据项？').then(function() {
        return delTemplate(templateIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
	/** é…æ–¹æ˜Žç»†序号 */
    rowBrewFormulaDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** é…æ–¹æ˜Žç»†添加按钮操作 */
    handleAddBrewFormulaDetail() {
      let obj = {}
      obj.materialId = ""
      obj.materialRatio = ""
      obj.baseQuantity = ""
      obj.isMainMaterial = ""
      obj.sortOrder = ""
      obj.remark = ""
      this.brewFormulaDetailList.push(obj)
    },
    /** é…æ–¹æ˜Žç»†删除按钮操作 */
    handleDeleteBrewFormulaDetail() {
      if (this.checkedBrewFormulaDetail.length == 0) {
        this.$modal.msgError("请先选择要删除的é…æ–¹æ˜Žç»†数据")
      } else {
        const brewFormulaDetailList = this.brewFormulaDetailList
        const checkedBrewFormulaDetail = this.checkedBrewFormulaDetail
        this.brewFormulaDetailList = brewFormulaDetailList.filter(function(item) {
          return checkedBrewFormulaDetail.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleBrewFormulaDetailSelectionChange(selection) {
      this.checkedBrewFormulaDetail = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/template/export', {
        ...this.queryParams
      }, `template_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
