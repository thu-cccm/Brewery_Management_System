<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="统计周期" prop="statPeriod">
        <el-input
          v-model="queryParams.statPeriod"
          placeholder="请输入统计周期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总产量(L)" prop="totalOutput">
        <el-input
          v-model="queryParams.totalOutput"
          placeholder="请输入总产量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总成本(元)" prop="totalCost">
        <el-input
          v-model="queryParams.totalCost"
          placeholder="请输入总成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原料成本(元)" prop="materialCost">
        <el-input
          v-model="queryParams.materialCost"
          placeholder="请输入原料成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="能耗成本(元)" prop="energyCost">
        <el-input
          v-model="queryParams.energyCost"
          placeholder="请输入能耗成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人工成本(元)" prop="laborCost">
        <el-input
          v-model="queryParams.laborCost"
          placeholder="请输入人工成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其他成本(元)" prop="otherCost">
        <el-input
          v-model="queryParams.otherCost"
          placeholder="请输入其他成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位成本(元/L)" prop="unitCost">
        <el-input
          v-model="queryParams.unitCost"
          placeholder="请输入单位成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成本趋势" prop="costTrend">
        <el-select v-model="queryParams.costTrend" placeholder="请选择成本趋势" clearable>
          <el-option label="上升" value="1" />
          <el-option label="下降" value="2" />
          <el-option label="持平" value="3" />
        </el-select>
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
      <el-table-column label="统计ID" align="center" prop="statId" />
      <el-table-column label="统计周期" align="center" prop="statPeriod" />
      <el-table-column label="统计类型" align="center" prop="statType">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.statType == '1'">月度</el-tag>
          <el-tag v-else-if="scope.row.statType == '2'" type="success">季度</el-tag>
          <el-tag v-else-if="scope.row.statType == '3'" type="warning">年度</el-tag>
          <span v-else>{{ scope.row.statType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总产量(L)" align="center" prop="totalOutput" />
      <el-table-column label="总成本(元)" align="center" prop="totalCost" />
      <el-table-column label="原料成本(元)" align="center" prop="materialCost" />
      <el-table-column label="能耗成本(元)" align="center" prop="energyCost" />
      <el-table-column label="人工成本(元)" align="center" prop="laborCost" />
      <el-table-column label="其他成本(元)" align="center" prop="otherCost" />
      <el-table-column label="单位成本(元/L)" align="center" prop="unitCost" />
      <el-table-column label="成本趋势" align="center" prop="costTrend">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.costTrend == '1'" type="danger">上升</el-tag>
          <el-tag v-else-if="scope.row.costTrend == '2'" type="success">下降</el-tag>
          <el-tag v-else-if="scope.row.costTrend == '3'" type="info">持平</el-tag>
          <span v-else>{{ scope.row.costTrend }}</span>
        </template>
      </el-table-column>
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

    <!-- 成本统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="统计周期" prop="statPeriod">
          <el-input v-model="form.statPeriod" placeholder="请输入统计周期（202601/2026Q1）" />
        </el-form-item>
        <el-form-item label="总产量(L)" prop="totalOutput">
          <el-input v-model="form.totalOutput" placeholder="请输入总产量" />
        </el-form-item>
        <el-form-item label="总成本(元)" prop="totalCost">
          <el-input v-model="form.totalCost" placeholder="请输入总成本" />
        </el-form-item>
        <el-form-item label="原料成本(元)" prop="materialCost">
          <el-input v-model="form.materialCost" placeholder="请输入原料成本" />
        </el-form-item>
        <el-form-item label="能耗成本(元)" prop="energyCost">
          <el-input v-model="form.energyCost" placeholder="请输入能耗成本" />
        </el-form-item>
        <el-form-item label="人工成本(元)" prop="laborCost">
          <el-input v-model="form.laborCost" placeholder="请输入人工成本" />
        </el-form-item>
        <el-form-item label="其他成本(元)" prop="otherCost">
          <el-input v-model="form.otherCost" placeholder="请输入其他成本" />
        </el-form-item>
        <el-form-item label="单位成本(元/L)" prop="unitCost">
          <el-input v-model="form.unitCost" placeholder="请输入单位成本" />
        </el-form-item>
        <el-form-item label="成本趋势" prop="costTrend">
          <el-select v-model="form.costTrend" placeholder="请选择成本趋势">
            <el-option label="上升" value="1" />
            <el-option label="下降" value="2" />
            <el-option label="持平" value="3" />
          </el-select>
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
import { listStatistics, getStatistics, delStatistics, addStatistics, updateStatistics } from "@/api/system/statistics"

export default {
  name: "Statistics",
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
      // ?本统计量单位?
      statisticsList: [],
      // 删除
      title: "",
      // open: false,
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
      form: {},
      rules: {
        statPeriod: [
          { required: true, message: "统计周期不能为空", trigger: "blur" }
        ],
        statType: [
          { required: true, message: "请选择统计类型", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询成本统计列表 */
    getList() {
      this.loading = true
      listStatistics(this.queryParams).then(response => {
        this.statisticsList = response.rows
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.statId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加成本统计"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const statId = row.statId || this.ids
      getStatistics(statId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改成本统计"
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
      this.$modal.confirm('是否确认删除成本统计编号为"' + statIds + '"的数据项？').then(function() {
        return delStatistics(statIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 请输入 */
    handleExport() {
      this.download('system/statistics/export', {
        ...this.queryParams
      }, `statistics_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
