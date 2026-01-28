<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ç”Ÿäº§è®¡åˆ’ID" prop="planId">
        <el-input
          v-model="queryParams.planId"
          placeholder="请输入ç”Ÿäº§è®¡åˆ’ID"
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
      <el-form-item label="å®žé™…äº§é‡ï¼ˆLï¼‰" prop="actualOutput">
        <el-input
          v-model="queryParams.actualOutput"
          placeholder="请输入å®žé™…äº§é‡ï¼ˆLï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ç”Ÿäº§æ—¥æœŸ" prop="productionDate">
        <el-date-picker clearable
          v-model="queryParams.productionDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择ç”Ÿäº§æ—¥æœŸ">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="å‘é…µå¼€å§‹æ—¶é—´" prop="fermentationStart">
        <el-date-picker clearable
          v-model="queryParams.fermentationStart"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择å‘é…µå¼€å§‹æ—¶é—´">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="å‘é…µç»“æŸæ—¶é—´" prop="fermentationEnd">
        <el-date-picker clearable
          v-model="queryParams.fermentationEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择å‘é…µç»“æŸæ—¶é—´">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="å®žé™…å‘é…µå¤©æ•°" prop="fermentationDays">
        <el-input
          v-model="queryParams.fermentationDays"
          placeholder="请输入å®žé™…å‘é…µå¤©æ•°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰" prop="environmentTemp">
        <el-input
          v-model="queryParams.environmentTemp"
          placeholder="请输入çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰" prop="environmentHumidity">
        <el-input
          v-model="queryParams.environmentHumidity"
          placeholder="请输入çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è´Ÿè´£äºº" prop="responsiblePerson">
        <el-input
          v-model="queryParams.responsiblePerson"
          placeholder="请输入è´Ÿè´£äºº"
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
          v-hasPermi="['system:batch:add']"
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
          v-hasPermi="['system:batch:edit']"
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
          v-hasPermi="['system:batch:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:batch:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="batchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="æ‰¹æ¬¡ID" align="center" prop="batchId" />
      <el-table-column label="æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰" align="center" prop="batchNo" />
      <el-table-column label="ç”Ÿäº§è®¡åˆ’ID" align="center" prop="planId" />
      <el-table-column label="é…’å“ç§ç±»ID" align="center" prop="categoryId" />
      <el-table-column label="é…æ–¹æ¨¡æ¿ID" align="center" prop="templateId" />
      <el-table-column label="å®žé™…äº§é‡ï¼ˆLï¼‰" align="center" prop="actualOutput" />
      <el-table-column label="ç”Ÿäº§æ—¥æœŸ" align="center" prop="productionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="å‘é…µå¼€å§‹æ—¶é—´" align="center" prop="fermentationStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.fermentationStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="å‘é…µç»“æŸæ—¶é—´" align="center" prop="fermentationEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.fermentationEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="å®žé™…å‘é…µå¤©æ•°" align="center" prop="fermentationDays" />
      <el-table-column label="çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰" align="center" prop="environmentTemp" />
      <el-table-column label="çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰" align="center" prop="environmentHumidity" />
      <el-table-column label="è´Ÿè´£äºº" align="center" prop="responsiblePerson" />
      <el-table-column label="æ‰¹æ¬¡çŠ¶æ€ï¼ˆ1å¤‡æ–™ä¸­ 2å‘é…µä¸­ 3è’¸é¦ä¸­ 4è´¨æ£€ä¸­ 5å·²å®Œæˆ 6å¼‚å¸¸ï¼‰" align="center" prop="batchStatus" />
      <el-table-column label="è´¨æ£€çŠ¶æ€ï¼ˆ0æœªæ£€ 1åˆæ ¼ 2ä¸åˆæ ¼ï¼‰" align="center" prop="qualityStatus" />
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:batch:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:batch:remove']"
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

    <!-- 添加或修改ç”Ÿäº§æ‰¹æ¬¡对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰" />
        </el-form-item>
        <el-form-item label="ç”Ÿäº§è®¡åˆ’ID" prop="planId">
          <el-input v-model="form.planId" placeholder="请输入ç”Ÿäº§è®¡åˆ’ID" />
        </el-form-item>
        <el-form-item label="é…’å“ç§ç±»ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入é…’å“ç§ç±»ID" />
        </el-form-item>
        <el-form-item label="é…æ–¹æ¨¡æ¿ID" prop="templateId">
          <el-input v-model="form.templateId" placeholder="请输入é…æ–¹æ¨¡æ¿ID" />
        </el-form-item>
        <el-form-item label="å®žé™…äº§é‡ï¼ˆLï¼‰" prop="actualOutput">
          <el-input v-model="form.actualOutput" placeholder="请输入å®žé™…äº§é‡ï¼ˆLï¼‰" />
        </el-form-item>
        <el-form-item label="ç”Ÿäº§æ—¥æœŸ" prop="productionDate">
          <el-date-picker clearable
            v-model="form.productionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择ç”Ÿäº§æ—¥æœŸ">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="å‘é…µå¼€å§‹æ—¶é—´" prop="fermentationStart">
          <el-date-picker clearable
            v-model="form.fermentationStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择å‘é…µå¼€å§‹æ—¶é—´">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="å‘é…µç»“æŸæ—¶é—´" prop="fermentationEnd">
          <el-date-picker clearable
            v-model="form.fermentationEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择å‘é…µç»“æŸæ—¶é—´">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="å®žé™…å‘é…µå¤©æ•°" prop="fermentationDays">
          <el-input v-model="form.fermentationDays" placeholder="请输入å®žé™…å‘é…µå¤©æ•°" />
        </el-form-item>
        <el-form-item label="çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰" prop="environmentTemp">
          <el-input v-model="form.environmentTemp" placeholder="请输入çŽ¯å¢ƒæ¸©åº¦ï¼ˆâ„ƒï¼‰" />
        </el-form-item>
        <el-form-item label="çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰" prop="environmentHumidity">
          <el-input v-model="form.environmentHumidity" placeholder="请输入çŽ¯å¢ƒæ¹¿åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="è´Ÿè´£äºº" prop="responsiblePerson">
          <el-input v-model="form.responsiblePerson" placeholder="请输入è´Ÿè´£äºº" />
        </el-form-item>
        <el-form-item label="å¤‡æ³¨" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入åˆ é™¤æ ‡å¿—ï¼ˆ0ä»£è¡¨å­˜åœ¨ 2ä»£è¡¨åˆ é™¤ï¼‰" />
        </el-form-item>
        <el-divider content-position="center">æ‰¹æ¬¡åŽŸæ–™信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBrewBatchMaterial">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBrewBatchMaterial">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="brewBatchMaterialList" :row-class-name="rowBrewBatchMaterialIndex" @selection-change="handleBrewBatchMaterialSelectionChange" ref="brewBatchMaterial">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="åŽŸæ–™ID" prop="materialId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialId" placeholder="请输入åŽŸæ–™ID" />
            </template>
          </el-table-column>
          <el-table-column label="åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰" prop="materialBatchNo" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialBatchNo" placeholder="请输入åŽŸæ–™æ‰¹æ¬¡å·ï¼ˆä¾›åº”å•†æä¾›ï¼Œå¯æ‰‹å·¥å½•å…¥ï¼‰" />
            </template>
          </el-table-column>
          <el-table-column label="è®¡åˆ’ç”¨é‡" prop="plannedQuantity" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.plannedQuantity" placeholder="请输入è®¡åˆ’ç”¨é‡" />
            </template>
          </el-table-column>
          <el-table-column label="å®žé™…ç”¨é‡" prop="actualQuantity" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.actualQuantity" placeholder="请输入å®žé™…ç”¨é‡" />
            </template>
          </el-table-column>
          <el-table-column label="åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" prop="materialCost" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.materialCost" placeholder="请输入åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" />
            </template>
          </el-table-column>
          <el-table-column label="ä½¿ç”¨æ—¶é—´" prop="useTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.useTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择ä½¿ç”¨æ—¶é—´" />
            </template>
          </el-table-column>
          <el-table-column label="æ“ä½œäºº" prop="operatorName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.operatorName" placeholder="请输入æ“ä½œäºº" />
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
import { listBatch, getBatch, delBatch, addBatch, updateBatch } from "@/api/system/batch"

export default {
  name: "Batch",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBrewBatchMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ç”Ÿäº§æ‰¹æ¬¡表格数据
      batchList: [],
      // æ‰¹æ¬¡åŽŸæ–™表格数据
      brewBatchMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        planId: null,
        categoryId: null,
        templateId: null,
        actualOutput: null,
        productionDate: null,
        fermentationStart: null,
        fermentationEnd: null,
        fermentationDays: null,
        environmentTemp: null,
        environmentHumidity: null,
        responsiblePerson: null,
        batchStatus: null,
        qualityStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchNo: [
          { required: true, message: "æ‰¹æ¬¡å·ï¼ˆå”¯ä¸€è¿½æº¯ç ï¼‰不能为空", trigger: "blur" }
        ],
        planId: [
          { required: true, message: "ç”Ÿäº§è®¡åˆ’ID不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "é…’å“ç§ç±»ID不能为空", trigger: "blur" }
        ],
        templateId: [
          { required: true, message: "é…æ–¹æ¨¡æ¿ID不能为空", trigger: "blur" }
        ],
        productionDate: [
          { required: true, message: "ç”Ÿäº§æ—¥æœŸ不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询ç”Ÿäº§æ‰¹æ¬¡列表 */
    getList() {
      this.loading = true
      listBatch(this.queryParams).then(response => {
        this.batchList = response.rows
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
        batchId: null,
        batchNo: null,
        planId: null,
        categoryId: null,
        templateId: null,
        actualOutput: null,
        productionDate: null,
        fermentationStart: null,
        fermentationEnd: null,
        fermentationDays: null,
        environmentTemp: null,
        environmentHumidity: null,
        responsiblePerson: null,
        batchStatus: null,
        qualityStatus: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null
      }
      this.brewBatchMaterialList = []
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
      this.ids = selection.map(item => item.batchId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加ç”Ÿäº§æ‰¹æ¬¡"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const batchId = row.batchId || this.ids
      getBatch(batchId).then(response => {
        this.form = response.data
        this.brewBatchMaterialList = response.data.brewBatchMaterialList
        this.open = true
        this.title = "修改ç”Ÿäº§æ‰¹æ¬¡"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.brewBatchMaterialList = this.brewBatchMaterialList
          if (this.form.batchId != null) {
            updateBatch(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addBatch(this.form).then(response => {
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
      const batchIds = row.batchId || this.ids
      this.$modal.confirm('是否确认删除ç”Ÿäº§æ‰¹æ¬¡编号为"' + batchIds + '"的数据项？').then(function() {
        return delBatch(batchIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
	/** æ‰¹æ¬¡åŽŸæ–™序号 */
    rowBrewBatchMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** æ‰¹æ¬¡åŽŸæ–™添加按钮操作 */
    handleAddBrewBatchMaterial() {
      let obj = {}
      obj.materialId = ""
      obj.materialBatchNo = ""
      obj.plannedQuantity = ""
      obj.actualQuantity = ""
      obj.materialCost = ""
      obj.useTime = ""
      obj.operatorName = ""
      this.brewBatchMaterialList.push(obj)
    },
    /** æ‰¹æ¬¡åŽŸæ–™删除按钮操作 */
    handleDeleteBrewBatchMaterial() {
      if (this.checkedBrewBatchMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的æ‰¹æ¬¡åŽŸæ–™数据")
      } else {
        const brewBatchMaterialList = this.brewBatchMaterialList
        const checkedBrewBatchMaterial = this.checkedBrewBatchMaterial
        this.brewBatchMaterialList = brewBatchMaterialList.filter(function(item) {
          return checkedBrewBatchMaterial.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleBrewBatchMaterialSelectionChange(selection) {
      this.checkedBrewBatchMaterial = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/batch/export', {
        ...this.queryParams
      }, `batch_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
