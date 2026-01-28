<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="é…’å“ç§ç±»ID" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入é…’å“ç§ç±»ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ ‡å‡†åç§°" prop="standardName">
        <el-input
          v-model="queryParams.standardName"
          placeholder="请输入æ ‡å‡†åç§°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰" prop="alcoholMin">
        <el-input
          v-model="queryParams.alcoholMin"
          placeholder="请输入é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰" prop="alcoholMax">
        <el-input
          v-model="queryParams.alcoholMax"
          placeholder="请输入é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰" prop="alcoholTolerance">
        <el-input
          v-model="queryParams.alcoholTolerance"
          placeholder="请输入é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è‰²æ³½æ ‡å‡†" prop="colorStandard">
        <el-input
          v-model="queryParams.colorStandard"
          placeholder="请输入è‰²æ³½æ ‡å‡†"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ°”å‘³æ ‡å‡†" prop="smellStandard">
        <el-input
          v-model="queryParams.smellStandard"
          placeholder="请输入æ°”å‘³æ ‡å‡†"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å£æ„Ÿæ ‡å‡†" prop="tasteStandard">
        <el-input
          v-model="queryParams.tasteStandard"
          placeholder="请输入å£æ„Ÿæ ‡å‡†"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isDefault">
        <el-input
          v-model="queryParams.isDefault"
          placeholder="请输入æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰"
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
          v-hasPermi="['system:standard:add']"
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
          v-hasPermi="['system:standard:edit']"
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
          v-hasPermi="['system:standard:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:standard:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="æ ‡å‡†ID" align="center" prop="standardId" />
      <el-table-column label="é…’å“ç§ç±»ID" align="center" prop="categoryId" />
      <el-table-column label="æ ‡å‡†åç§°" align="center" prop="standardName" />
      <el-table-column label="é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰" align="center" prop="alcoholMin" />
      <el-table-column label="é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰" align="center" prop="alcoholMax" />
      <el-table-column label="é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰" align="center" prop="alcoholTolerance" />
      <el-table-column label="è‰²æ³½æ ‡å‡†" align="center" prop="colorStandard" />
      <el-table-column label="æ°”å‘³æ ‡å‡†" align="center" prop="smellStandard" />
      <el-table-column label="å£æ„Ÿæ ‡å‡†" align="center" prop="tasteStandard" />
      <el-table-column label="å…¶ä»–æŒ‡æ ‡ï¼ˆJSONæ ¼å¼ï¼‰" align="center" prop="otherIndicators" />
      <el-table-column label="æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰" align="center" prop="isDefault" />
      <el-table-column label="çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:standard:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:standard:remove']"
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

    <!-- 添加或修改è´¨æ£€æ ‡å‡†对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="é…’å“ç§ç±»ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入é…’å“ç§ç±»ID" />
        </el-form-item>
        <el-form-item label="æ ‡å‡†åç§°" prop="standardName">
          <el-input v-model="form.standardName" placeholder="请输入æ ‡å‡†åç§°" />
        </el-form-item>
        <el-form-item label="é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰" prop="alcoholMin">
          <el-input v-model="form.alcoholMin" placeholder="请输入é…’ç²¾åº¦æœ€å°å€¼ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰" prop="alcoholMax">
          <el-input v-model="form.alcoholMax" placeholder="请输入é…’ç²¾åº¦æœ€å¤§å€¼ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰" prop="alcoholTolerance">
          <el-input v-model="form.alcoholTolerance" placeholder="请输入é…’ç²¾åº¦å…è®¸è¯¯å·®ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="è‰²æ³½æ ‡å‡†" prop="colorStandard">
          <el-input v-model="form.colorStandard" placeholder="请输入è‰²æ³½æ ‡å‡†" />
        </el-form-item>
        <el-form-item label="æ°”å‘³æ ‡å‡†" prop="smellStandard">
          <el-input v-model="form.smellStandard" placeholder="请输入æ°”å‘³æ ‡å‡†" />
        </el-form-item>
        <el-form-item label="å£æ„Ÿæ ‡å‡†" prop="tasteStandard">
          <el-input v-model="form.tasteStandard" placeholder="请输入å£æ„Ÿæ ‡å‡†" />
        </el-form-item>
        <el-form-item label="å…¶ä»–æŒ‡æ ‡ï¼ˆJSONæ ¼å¼ï¼‰" prop="otherIndicators">
          <el-input v-model="form.otherIndicators" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isDefault">
          <el-input v-model="form.isDefault" placeholder="请输入æ˜¯å¦é»˜è®¤æ ‡å‡†ï¼ˆ0å¦ 1æ˜¯ï¼‰" />
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
import { listStandard, getStandard, delStandard, addStandard, updateStandard } from "@/api/system/standard"

export default {
  name: "Standard",
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
      // è´¨æ£€æ ‡å‡†表格数据
      standardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryId: null,
        standardName: null,
        alcoholMin: null,
        alcoholMax: null,
        alcoholTolerance: null,
        colorStandard: null,
        smellStandard: null,
        tasteStandard: null,
        otherIndicators: null,
        isDefault: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryId: [
          { required: true, message: "é…’å“ç§ç±»ID不能为空", trigger: "blur" }
        ],
        standardName: [
          { required: true, message: "æ ‡å‡†åç§°不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询è´¨æ£€æ ‡å‡†列表 */
    getList() {
      this.loading = true
      listStandard(this.queryParams).then(response => {
        this.standardList = response.rows
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
        standardId: null,
        categoryId: null,
        standardName: null,
        alcoholMin: null,
        alcoholMax: null,
        alcoholTolerance: null,
        colorStandard: null,
        smellStandard: null,
        tasteStandard: null,
        otherIndicators: null,
        isDefault: null,
        status: null,
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
      this.ids = selection.map(item => item.standardId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加è´¨æ£€æ ‡å‡†"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const standardId = row.standardId || this.ids
      getStandard(standardId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改è´¨æ£€æ ‡å‡†"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.standardId != null) {
            updateStandard(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStandard(this.form).then(response => {
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
      const standardIds = row.standardId || this.ids
      this.$modal.confirm('是否确认删除è´¨æ£€æ ‡å‡†编号为"' + standardIds + '"的数据项？').then(function() {
        return delStandard(standardIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/standard/export', {
        ...this.queryParams
      }, `standard_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
