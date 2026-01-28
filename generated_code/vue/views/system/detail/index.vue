<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="æ¨¡æ¿ID" prop="templateId">
        <el-input
          v-model="queryParams.templateId"
          placeholder="请输入æ¨¡æ¿ID"
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
      <el-form-item label="é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰" prop="materialRatio">
        <el-input
          v-model="queryParams.materialRatio"
          placeholder="请输入é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="åŸºå‡†ç”¨é‡" prop="baseQuantity">
        <el-input
          v-model="queryParams.baseQuantity"
          placeholder="请输入åŸºå‡†ç”¨é‡"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isMainMaterial">
        <el-input
          v-model="queryParams.isMainMaterial"
          placeholder="请输入æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æŽ’åº" prop="sortOrder">
        <el-input
          v-model="queryParams.sortOrder"
          placeholder="请输入æŽ’åº"
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
          v-hasPermi="['system:detail:add']"
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
          v-hasPermi="['system:detail:edit']"
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
          v-hasPermi="['system:detail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:detail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="detailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="æ˜Žç»†ID" align="center" prop="detailId" />
      <el-table-column label="æ¨¡æ¿ID" align="center" prop="templateId" />
      <el-table-column label="åŽŸæ–™ID" align="center" prop="materialId" />
      <el-table-column label="é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰" align="center" prop="materialRatio" />
      <el-table-column label="åŸºå‡†ç”¨é‡" align="center" prop="baseQuantity" />
      <el-table-column label="æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰" align="center" prop="isMainMaterial" />
      <el-table-column label="æŽ’åº" align="center" prop="sortOrder" />
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:detail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:detail:remove']"
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

    <!-- 添加或修改é…æ–¹æ˜Žç»†对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æ¨¡æ¿ID" prop="templateId">
          <el-input v-model="form.templateId" placeholder="请输入æ¨¡æ¿ID" />
        </el-form-item>
        <el-form-item label="åŽŸæ–™ID" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入åŽŸæ–™ID" />
        </el-form-item>
        <el-form-item label="é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰" prop="materialRatio">
          <el-input v-model="form.materialRatio" placeholder="请输入é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰" />
        </el-form-item>
        <el-form-item label="åŸºå‡†ç”¨é‡" prop="baseQuantity">
          <el-input v-model="form.baseQuantity" placeholder="请输入åŸºå‡†ç”¨é‡" />
        </el-form-item>
        <el-form-item label="æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰" prop="isMainMaterial">
          <el-input v-model="form.isMainMaterial" placeholder="请输入æ˜¯å¦ä¸»æ–™ï¼ˆ0å¦ 1æ˜¯ï¼‰" />
        </el-form-item>
        <el-form-item label="æŽ’åº" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入æŽ’åº" />
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
import { listDetail, getDetail, delDetail, addDetail, updateDetail } from "@/api/system/detail"

export default {
  name: "Detail",
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
      // é…æ–¹æ˜Žç»†表格数据
      detailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateId: null,
        materialId: null,
        materialRatio: null,
        baseQuantity: null,
        isMainMaterial: null,
        sortOrder: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateId: [
          { required: true, message: "æ¨¡æ¿ID不能为空", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "åŽŸæ–™ID不能为空", trigger: "blur" }
        ],
        materialRatio: [
          { required: true, message: "é…æ¯”ç³»æ•°ï¼ˆåŽŸæ–™ç”¨é‡/åŸºå‡†äº§é‡ï¼‰不能为空", trigger: "blur" }
        ],
        baseQuantity: [
          { required: true, message: "åŸºå‡†ç”¨é‡不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询é…æ–¹æ˜Žç»†列表 */
    getList() {
      this.loading = true
      listDetail(this.queryParams).then(response => {
        this.detailList = response.rows
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
        detailId: null,
        templateId: null,
        materialId: null,
        materialRatio: null,
        baseQuantity: null,
        isMainMaterial: null,
        sortOrder: null,
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
      this.ids = selection.map(item => item.detailId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加é…æ–¹æ˜Žç»†"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const detailId = row.detailId || this.ids
      getDetail(detailId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改é…æ–¹æ˜Žç»†"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.detailId != null) {
            updateDetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addDetail(this.form).then(response => {
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
      const detailIds = row.detailId || this.ids
      this.$modal.confirm('是否确认删除é…æ–¹æ˜Žç»†编号为"' + detailIds + '"的数据项？').then(function() {
        return delDetail(detailIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/detail/export', {
        ...this.queryParams
      }, `detail_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
