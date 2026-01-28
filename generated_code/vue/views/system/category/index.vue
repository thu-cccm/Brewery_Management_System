<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="å“ç±»ç¼–ç " prop="categoryCode">
        <el-input
          v-model="queryParams.categoryCode"
          placeholder="请输入å“ç±»ç¼–ç "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å“ç±»åç§°" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入å“ç±»åç§°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰" prop="alcoholRangeMin">
        <el-input
          v-model="queryParams.alcoholRangeMin"
          placeholder="请输入é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰" prop="alcoholRangeMax">
        <el-input
          v-model="queryParams.alcoholRangeMax"
          placeholder="请输入é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•°" prop="fermentationDaysMin">
        <el-input
          v-model="queryParams.fermentationDaysMin"
          placeholder="请输入å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•°" prop="fermentationDaysMax">
        <el-input
          v-model="queryParams.fermentationDaysMax"
          placeholder="请输入å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰" prop="optimalTemperature">
        <el-input
          v-model="queryParams.optimalTemperature"
          placeholder="请输入æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰" prop="optimalHumidity">
        <el-input
          v-model="queryParams.optimalHumidity"
          placeholder="请输入æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰"
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
          v-hasPermi="['system:category:add']"
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
          v-hasPermi="['system:category:edit']"
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
          v-hasPermi="['system:category:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:category:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="å“ç±»ID" align="center" prop="categoryId" />
      <el-table-column label="å“ç±»ç¼–ç " align="center" prop="categoryCode" />
      <el-table-column label="å“ç±»åç§°" align="center" prop="categoryName" />
      <el-table-column label="é…’ç±»åž‹ï¼ˆ1ç™½é…’ 2å•¤é…’ 3é»„é…’ 4æžœé…’ï¼‰" align="center" prop="categoryType" />
      <el-table-column label="é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰" align="center" prop="alcoholRangeMin" />
      <el-table-column label="é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰" align="center" prop="alcoholRangeMax" />
      <el-table-column label="å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•°" align="center" prop="fermentationDaysMin" />
      <el-table-column label="å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•°" align="center" prop="fermentationDaysMax" />
      <el-table-column label="æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰" align="center" prop="optimalTemperature" />
      <el-table-column label="æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰" align="center" prop="optimalHumidity" />
      <el-table-column label="å“ç±»æè¿°" align="center" prop="description" />
      <el-table-column label="çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:category:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:category:remove']"
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

    <!-- 添加或修改é…’å“ç§ç±»对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="å“ç±»ç¼–ç " prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入å“ç±»ç¼–ç " />
        </el-form-item>
        <el-form-item label="å“ç±»åç§°" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入å“ç±»åç§°" />
        </el-form-item>
        <el-form-item label="é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰" prop="alcoholRangeMin">
          <el-input v-model="form.alcoholRangeMin" placeholder="请输入é…’ç²¾åº¦èŒƒå›´-æœ€å°å€¼ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰" prop="alcoholRangeMax">
          <el-input v-model="form.alcoholRangeMax" placeholder="请输入é…’ç²¾åº¦èŒƒå›´-æœ€å¤§å€¼ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•°" prop="fermentationDaysMin">
          <el-input v-model="form.fermentationDaysMin" placeholder="请输入å‘é…µå‘¨æœŸ-æœ€å°å¤©æ•°" />
        </el-form-item>
        <el-form-item label="å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•°" prop="fermentationDaysMax">
          <el-input v-model="form.fermentationDaysMax" placeholder="请输入å‘é…µå‘¨æœŸ-æœ€å¤§å¤©æ•°" />
        </el-form-item>
        <el-form-item label="æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰" prop="optimalTemperature">
          <el-input v-model="form.optimalTemperature" placeholder="请输入æœ€ä½³å‘é…µæ¸©åº¦ï¼ˆâ„ƒï¼‰" />
        </el-form-item>
        <el-form-item label="æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰" prop="optimalHumidity">
          <el-input v-model="form.optimalHumidity" placeholder="请输入æœ€ä½³å‘é…µæ¹¿åº¦ï¼ˆ%ï¼‰" />
        </el-form-item>
        <el-form-item label="å“ç±»æè¿°" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
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
import { listCategory, getCategory, delCategory, addCategory, updateCategory } from "@/api/system/category"

export default {
  name: "Category",
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
      // é…’å“ç§ç±»表格数据
      categoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryCode: null,
        categoryName: null,
        categoryType: null,
        alcoholRangeMin: null,
        alcoholRangeMax: null,
        fermentationDaysMin: null,
        fermentationDaysMax: null,
        optimalTemperature: null,
        optimalHumidity: null,
        description: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryCode: [
          { required: true, message: "å“ç±»ç¼–ç 不能为空", trigger: "blur" }
        ],
        categoryName: [
          { required: true, message: "å“ç±»åç§°不能为空", trigger: "blur" }
        ],
        categoryType: [
          { required: true, message: "é…’ç±»åž‹ï¼ˆ1ç™½é…’ 2å•¤é…’ 3é»„é…’ 4æžœé…’ï¼‰不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询é…’å“ç§ç±»列表 */
    getList() {
      this.loading = true
      listCategory(this.queryParams).then(response => {
        this.categoryList = response.rows
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
        categoryId: null,
        categoryCode: null,
        categoryName: null,
        categoryType: null,
        alcoholRangeMin: null,
        alcoholRangeMax: null,
        fermentationDaysMin: null,
        fermentationDaysMax: null,
        optimalTemperature: null,
        optimalHumidity: null,
        description: null,
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
      this.ids = selection.map(item => item.categoryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加é…’å“ç§ç±»"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const categoryId = row.categoryId || this.ids
      getCategory(categoryId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改é…’å“ç§ç±»"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.categoryId != null) {
            updateCategory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCategory(this.form).then(response => {
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
      const categoryIds = row.categoryId || this.ids
      this.$modal.confirm('是否确认删除é…’å“ç§ç±»编号为"' + categoryIds + '"的数据项？').then(function() {
        return delCategory(categoryIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/category/export', {
        ...this.queryParams
      }, `category_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
