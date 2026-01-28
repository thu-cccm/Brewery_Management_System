<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="统计周期（202601/2026Q1）" prop="statPeriod">
        <el-input
          v-model="queryParams.statPeriod"
          placeholder="请输入统计周期（202601/2026Q1）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总产?（L）" prop="totalOutput">
        <el-input
          v-model="queryParams.totalOutput"
          placeholder="请输入总产?（L）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总本（元）" prop="totalCost">
        <el-input
          v-model="queryParams.totalCost"
          placeholder="请输入总本（元）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原料本（元）" prop="materialCost">
        <el-input
          v-model="queryParams.materialCost"
          placeholder="请输入原料本（元）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="能耗本（元）" prop="energyCost">
        <el-input
          v-model="queryParams.energyCost"
          placeholder="请输入能耗本（元）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人工本（元）" prop="laborCost">
        <el-input
          v-model="queryParams.laborCost"
          placeholder="请输入人工本（元）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其他本（元）" prop="otherCost">
        <el-input
          v-model="queryParams.otherCost"
          placeholder="请输入其他本（元）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="请输入本（元/L）" prop="unitCost">
        <el-input
          v-model="queryParams.unitCost"
          placeholder="请输入本（元/L）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="?本趋势（1上? 2下? 3平）" prop="costTrend">
        <el-input
          v-model="queryParams.costTrend"
          placeholder="请输入本趋势（1上? 2下? 3平）"
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
      <el-table-column label="统计ID" align="center" prop="statId" />
      <el-table-column label="统计周期（202601/2026Q1）" align="center" prop="statPeriod" />
      <el-table-column label="统计类型（1月度 2季度 3年度）" align="center" prop="statType" />
      <el-table-column label="总产?（L）" align="center" prop="totalOutput" />
      <el-table-column label="总本（元）" align="center" prop="totalCost" />
      <el-table-column label="原料本（元）" align="center" prop="materialCost" />
      <el-table-column label="能耗本（元）" align="center" prop="energyCost" />
      <el-table-column label="人工本（元）" align="center" prop="laborCost" />
      <el-table-column label="其他本（元）" align="center" prop="otherCost" />
      <el-table-column label="请输入本（元/L）" align="center" prop="unitCost" />
      <el-table-column label="?本趋势（1上? 2下? 3平）" align="center" prop="costTrend" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:statistics:edit']"
          >操作</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:statistics:remove']"
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

    <!-- 请输入本统计量单位 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="统计周期（202601/2026Q1）" prop="statPeriod">
          <el-input v-model="form.statPeriod" placeholder="请输入统计周期（202601/2026Q1）" />
        </el-form-item>
        <el-form-item label="总产?（L）" prop="totalOutput">
          <el-input v-model="form.totalOutput" placeholder="请输入总产?（L）" />
        </el-form-item>
        <el-form-item label="总本（元）" prop="totalCost">
          <el-input v-model="form.totalCost" placeholder="请输入总本（元）" />
        </el-form-item>
        <el-form-item label="原料本（元）" prop="materialCost">
          <el-input v-model="form.materialCost" placeholder="请输入原料本（元）" />
        </el-form-item>
        <el-form-item label="能耗本（元）" prop="energyCost">
          <el-input v-model="form.energyCost" placeholder="请输入能耗本（元）" />
        </el-form-item>
        <el-form-item label="人工本（元）" prop="laborCost">
          <el-input v-model="form.laborCost" placeholder="请输入人工本（元）" />
        </el-form-item>
        <el-form-item label="其他本（元）" prop="otherCost">
          <el-input v-model="form.otherCost" placeholder="请输入其他本（元）" />
        </el-form-item>
        <el-form-item label="请输入本（元/L）" prop="unitCost">
          <el-input v-model="form.unitCost" placeholder="请输入本（元/L）" />
        </el-form-item>
        <el-form-item label="?本趋势（1上? 2下? 3平）" prop="costTrend">
          <el-input v-model="form.costTrend" placeholder="请输入本趋势（1上? 2下? 3平）" />
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
          { required: true, message: "统计周期（202601/2026Q1）请输入", trigger: "blur" }
        ],
        statType: [
          { required: true, message: "统计类型（1月度 2季度 3年度）请输入", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 请输入本统计操作 */
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
      this.ids = selection.map(item => item.statId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 请输入 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "请输入本统计"
    },
    /** 请输入 */
    handleUpdate(row) {
      this.reset()
      const statId = row.statId || this.ids
      getStatistics(statId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "请输入本统计"
      })
    },
    /** 请输入 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.statId != null) {
            updateStatistics(this.form).then(response => {
              this.$modal.msgSuccess("请输入")
              this.open = false
              this.getList()
            })
          } else {
            addStatistics(this.form).then(response => {
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
      const statIds = row.statId || this.ids
      this.$modal.confirm('请输入本统计量单位"' + statIds + '"删除').then(function() {
        return delStatistics(statIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("请输入")
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
