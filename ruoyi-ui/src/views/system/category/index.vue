<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="类别编码" prop="categoryCode">
        <el-input
          v-model="queryParams.categoryCode"
          placeholder="请输入类别编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别名称" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入类别名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒精度最小值(%)" prop="alcoholRangeMin">
        <el-input
          v-model="queryParams.alcoholRangeMin"
          placeholder="请输入酒精度最小值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒精度最大值(%)" prop="alcoholRangeMax">
        <el-input
          v-model="queryParams.alcoholRangeMax"
          placeholder="请输入酒精度最大值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发酵周期最少天数" prop="fermentationDaysMin">
        <el-input
          v-model="queryParams.fermentationDaysMin"
          placeholder="请输入最少天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发酵周期最大天数" prop="fermentationDaysMax">
        <el-input
          v-model="queryParams.fermentationDaysMax"
          placeholder="请输入最大天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最佳发酵温度(℃)" prop="optimalTemperature">
        <el-input
          v-model="queryParams.optimalTemperature"
          placeholder="请输入最佳发酵温度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最佳发酵湿度(%)" prop="optimalHumidity">
        <el-input
          v-model="queryParams.optimalHumidity"
          placeholder="请输入最佳发酵湿度"
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
      <el-table-column label="类别ID" align="center" prop="categoryId" />
      <el-table-column label="类别编码" align="center" prop="categoryCode" />
      <el-table-column label="类别名称" align="center" prop="categoryName" />
      <el-table-column label="酒类型" align="center" prop="categoryType">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.categoryType == '1'">白酒</el-tag>
          <el-tag v-else-if="scope.row.categoryType == '2'" type="warning">啤酒</el-tag>
          <el-tag v-else-if="scope.row.categoryType == '3'" type="success">黄酒</el-tag>
          <el-tag v-else-if="scope.row.categoryType == '4'" type="danger">果酒</el-tag>
          <span v-else>{{ scope.row.categoryType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="酒精度最小值(%)" align="center" prop="alcoholRangeMin" />
      <el-table-column label="酒精度最大值(%)" align="center" prop="alcoholRangeMax" />
      <el-table-column label="发酵周期最少天数" align="center" prop="fermentationDaysMin" />
      <el-table-column label="发酵周期最大天数" align="center" prop="fermentationDaysMax" />
      <el-table-column label="最佳发酵温度(℃)" align="center" prop="optimalTemperature" />
      <el-table-column label="最佳发酵湿度(%)" align="center" prop="optimalHumidity" />
      <el-table-column label="类别描述" align="center" prop="description" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == '0'" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.status == '1'" type="danger">停用</el-tag>
          <span v-else>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
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

    <!-- 删除对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="类别编码" prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入类别编码" />
        </el-form-item>
        <el-form-item label="类别名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入类别名称" />
        </el-form-item>
        <el-form-item label="酒类型" prop="categoryType">
          <el-select v-model="form.categoryType" placeholder="请选择酒类型">
            <el-option label="白酒" value="1" />
            <el-option label="啤酒" value="2" />
            <el-option label="黄酒" value="3" />
            <el-option label="果酒" value="4" />
          </el-select>
        </el-form-item>
        <el-form-item label="酒精度最小值(%)" prop="alcoholRangeMin">
          <el-input v-model="form.alcoholRangeMin" placeholder="请输入酒精度最小值" />
        </el-form-item>
        <el-form-item label="酒精度最大值(%)" prop="alcoholRangeMax">
          <el-input v-model="form.alcoholRangeMax" placeholder="请输入酒精度最大值" />
        </el-form-item>
        <el-form-item label="发酵周期最少天数" prop="fermentationDaysMin">
          <el-input v-model="form.fermentationDaysMin" placeholder="请输入最少天数" />
        </el-form-item>
        <el-form-item label="发酵周期最大天数" prop="fermentationDaysMax">
          <el-input v-model="form.fermentationDaysMax" placeholder="请输入最大天数" />
        </el-form-item>
        <el-form-item label="最佳发酵温度(℃)" prop="optimalTemperature">
          <el-input v-model="form.optimalTemperature" placeholder="请输入最佳发酵温度" />
        </el-form-item>
        <el-form-item label="最佳发酵湿度(%)" prop="optimalHumidity">
          <el-input v-model="form.optimalHumidity" placeholder="请输入最佳发酵湿度" />
        </el-form-item>
        <el-form-item label="类别描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入类别描述" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="0">正常</el-radio>
            <el-radio label="1">停用</el-radio>
          </el-radio-group>
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
      // loading: true,
      // ids: [],
      // 删除
      single: true,
      // 删除
      multiple: true,
      // showSearch: true,
      // total: 0,
      // 酒操作类请输入
      categoryList: [],
      // 删除
      title: "",
      // open: false,
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
      form: {},
      rules: {
        categoryCode: [
          { required: true, message: "类别编码不能为空", trigger: "blur" }
        ],
        categoryName: [
          { required: true, message: "类别名称不能为空", trigger: "blur" }
        ],
        categoryType: [
          { required: true, message: "请选择酒类型", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询酒操作类列表 */
    getList() {
      this.loading = true
      listCategory(this.queryParams).then(response => {
        this.categoryList = response.rows
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
        status: "0",
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.categoryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加酒操作类"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const categoryId = row.categoryId || this.ids
      getCategory(categoryId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改酒操作类"
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
      this.$modal.confirm('是否确认删除类别编号为"' + categoryIds + '"的数据项？').then(function() {
        return delCategory(categoryIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 请输入 */
    handleExport() {
      this.download('system/category/export', {
        ...this.queryParams
      }, `category_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
