<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰" prop="statPeriod">
        <el-input
          v-model="queryParams.statPeriod"
          placeholder="请输入ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ€»äº§é‡ï¼ˆLï¼‰" prop="totalOutput">
        <el-input
          v-model="queryParams.totalOutput"
          placeholder="请输入æ€»äº§é‡ï¼ˆLï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æ€»æˆæœ¬ï¼ˆå…ƒï¼‰" prop="totalCost">
        <el-input
          v-model="queryParams.totalCost"
          placeholder="请输入æ€»æˆæœ¬ï¼ˆå…ƒï¼‰"
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
      <el-form-item label="èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰" prop="energyCost">
        <el-input
          v-model="queryParams.energyCost"
          placeholder="请输入èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰" prop="laborCost">
        <el-input
          v-model="queryParams.laborCost"
          placeholder="请输入äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰" prop="otherCost">
        <el-input
          v-model="queryParams.otherCost"
          placeholder="请输入å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰" prop="unitCost">
        <el-input
          v-model="queryParams.unitCost"
          placeholder="请输入å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰" prop="costTrend">
        <el-input
          v-model="queryParams.costTrend"
          placeholder="请输入æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰"
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
          v-hasPermi="['system:statistics:add']"
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
          v-hasPermi="['system:statistics:edit']"
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
          v-hasPermi="['system:statistics:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:statistics:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="statisticsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ç»Ÿè®¡ID" align="center" prop="statId" />
      <el-table-column label="ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰" align="center" prop="statPeriod" />
      <el-table-column label="ç»Ÿè®¡ç±»åž‹ï¼ˆ1æœˆåº¦ 2å­£åº¦ 3å¹´åº¦ï¼‰" align="center" prop="statType" />
      <el-table-column label="æ€»äº§é‡ï¼ˆLï¼‰" align="center" prop="totalOutput" />
      <el-table-column label="æ€»æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="totalCost" />
      <el-table-column label="åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="materialCost" />
      <el-table-column label="èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="energyCost" />
      <el-table-column label="äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="laborCost" />
      <el-table-column label="å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="otherCost" />
      <el-table-column label="å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰" align="center" prop="unitCost" />
      <el-table-column label="æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰" align="center" prop="costTrend" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:statistics:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:statistics:remove']"
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

    <!-- 添加或修改æˆæœ¬ç»Ÿè®¡对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰" prop="statPeriod">
          <el-input v-model="form.statPeriod" placeholder="请输入ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰" />
        </el-form-item>
        <el-form-item label="æ€»äº§é‡ï¼ˆLï¼‰" prop="totalOutput">
          <el-input v-model="form.totalOutput" placeholder="请输入æ€»äº§é‡ï¼ˆLï¼‰" />
        </el-form-item>
        <el-form-item label="æ€»æˆæœ¬ï¼ˆå…ƒï¼‰" prop="totalCost">
          <el-input v-model="form.totalCost" placeholder="请输入æ€»æˆæœ¬ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" prop="materialCost">
          <el-input v-model="form.materialCost" placeholder="请输入åŽŸæ–™æˆæœ¬ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰" prop="energyCost">
          <el-input v-model="form.energyCost" placeholder="请输入èƒ½è€—æˆæœ¬ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰" prop="laborCost">
          <el-input v-model="form.laborCost" placeholder="请输入äººå·¥æˆæœ¬ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰" prop="otherCost">
          <el-input v-model="form.otherCost" placeholder="请输入å…¶ä»–æˆæœ¬ï¼ˆå…ƒï¼‰" />
        </el-form-item>
        <el-form-item label="å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰" prop="unitCost">
          <el-input v-model="form.unitCost" placeholder="请输入å•ä½æˆæœ¬ï¼ˆå…ƒ/Lï¼‰" />
        </el-form-item>
        <el-form-item label="æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰" prop="costTrend">
          <el-input v-model="form.costTrend" placeholder="请输入æˆæœ¬è¶‹åŠ¿ï¼ˆ1ä¸Šå‡ 2ä¸‹é™ 3æŒå¹³ï¼‰" />
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
import { listStatistics, getStatistics, delStatistics, addStatistics, updateStatistics } from "@/api/system/statistics"

export default {
  name: "Statistics",
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
      // æˆæœ¬ç»Ÿè®¡表格数据
      statisticsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        statPeriod: null,
        statType: null,
        totalOutput: null,
        totalCost: null,
        materialCost: null,
        energyCost: null,
        laborCost: null,
        otherCost: null,
        unitCost: null,
        costTrend: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        statPeriod: [
          { required: true, message: "ç»Ÿè®¡å‘¨æœŸï¼ˆ202601/2026Q1ï¼‰不能为空", trigger: "blur" }
        ],
        statType: [
          { required: true, message: "ç»Ÿè®¡ç±»åž‹ï¼ˆ1æœˆåº¦ 2å­£åº¦ 3å¹´åº¦ï¼‰不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询æˆæœ¬ç»Ÿè®¡列表 */
    getList() {
      this.loading = true
      listStatistics(this.queryParams).then(response => {
        this.statisticsList = response.rows
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
        statId: null,
        statPeriod: null,
        statType: null,
        totalOutput: null,
        totalCost: null,
        materialCost: null,
        energyCost: null,
        laborCost: null,
        otherCost: null,
        unitCost: null,
        costTrend: null,
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
      this.ids = selection.map(item => item.statId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加æˆæœ¬ç»Ÿè®¡"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const statId = row.statId || this.ids
      getStatistics(statId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改æˆæœ¬ç»Ÿè®¡"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.statId != null) {
            updateStatistics(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStatistics(this.form).then(response => {
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
      const statIds = row.statId || this.ids
      this.$modal.confirm('是否确认删除æˆæœ¬ç»Ÿè®¡编号为"' + statIds + '"的数据项？').then(function() {
        return delStatistics(statIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/statistics/export', {
        ...this.queryParams
      }, `statistics_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
