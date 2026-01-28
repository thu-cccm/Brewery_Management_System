<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="批次编号" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入批次编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="质检标准ID" prop="standardId">
        <el-input
          v-model="queryParams.standardId"
          placeholder="请输入质检标准ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检测时间" prop="inspectTime">
        <el-date-picker clearable
          v-model="queryParams.inspectTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请输入检测时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="检测人" prop="inspectorName">
        <el-input
          v-model="queryParams.inspectorName"
          placeholder="请输入检测人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="蒸操作酒精度（%）" prop="alcoholBefore">
        <el-input
          v-model="queryParams.alcoholBefore"
          placeholder="请输入蒸操作酒精度（%）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="蒸操作酒精度（%）" prop="alcoholAfter">
        <el-input
          v-model="queryParams.alcoholAfter"
          placeholder="请输入蒸操作酒精度（%）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实际酒精度（%）" prop="alcoholActual">
        <el-input
          v-model="queryParams.alcoholActual"
          placeholder="请输入实际酒精度（%）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒精度误差（%）" prop="alcoholError">
        <el-input
          v-model="queryParams.alcoholError"
          placeholder="请输入酒精度误差（%）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产酒率（%）" prop="wineOutputRate">
        <el-input
          v-model="queryParams.wineOutputRate"
          placeholder="请输入产酒率（%）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="色泽检测结果" prop="colorResult">
        <el-input
          v-model="queryParams.colorResult"
          placeholder="请输入色泽检测结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="气味检测结果" prop="smellResult">
        <el-input
          v-model="queryParams.smellResult"
          placeholder="请输入气味检测结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="?感检测结果" prop="tasteResult">
        <el-input
          v-model="queryParams.tasteResult"
          placeholder="请输入感检测结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="质检结果（1格 2操作格 3待检）" prop="qualityResult">
        <el-input
          v-model="queryParams.qualityResult"
          placeholder="请输入质检结果（1格 2操作格 3待检）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="质评分（0-100）" prop="qualityScore">
        <el-input
          v-model="queryParams.qualityScore"
          placeholder="请输入质评分（0-100）"
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
          v-hasPermi="['system:quality:add']"
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
          v-hasPermi="['system:quality:edit']"
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
          v-hasPermi="['system:quality:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:quality:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="qualityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="批次编号" align="center" prop="batchNo" />
      <el-table-column label="质检标准ID" align="center" prop="standardId" />
      <el-table-column label="检测时间" align="center" prop="inspectTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检测人" align="center" prop="inspectorName" />
      <el-table-column label="蒸操作酒精度（%）" align="center" prop="alcoholBefore" />
      <el-table-column label="蒸操作酒精度（%）" align="center" prop="alcoholAfter" />
      <el-table-column label="实际酒精度（%）" align="center" prop="alcoholActual" />
      <el-table-column label="酒精度误差（%）" align="center" prop="alcoholError" />
      <el-table-column label="产酒率（%）" align="center" prop="wineOutputRate" />
      <el-table-column label="色泽检测结果" align="center" prop="colorResult" />
      <el-table-column label="气味检测结果" align="center" prop="smellResult" />
      <el-table-column label="?感检测结果" align="center" prop="tasteResult" />
      <el-table-column label="质检结果（1格 2操作格 3待检）" align="center" prop="qualityResult" />
      <el-table-column label="质评分（0-100）" align="center" prop="qualityScore" />
      <el-table-column label="操作格原因" align="center" prop="unqualifiedReason" />
      <el-table-column label="处建议" align="center" prop="handleSuggestion" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:quality:edit']"
          >操作</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:quality:remove']"
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

    <!-- 删除质检记录人操作 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="批次编号" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入批次编号" />
        </el-form-item>
        <el-form-item label="质检标准ID" prop="standardId">
          <el-input v-model="form.standardId" placeholder="请输入质检标准ID" />
        </el-form-item>
        <el-form-item label="检测时间" prop="inspectTime">
          <el-date-picker clearable
            v-model="form.inspectTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请输入检测时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检测人" prop="inspectorName">
          <el-input v-model="form.inspectorName" placeholder="请输入检测人" />
        </el-form-item>
        <el-form-item label="蒸操作酒精度（%）" prop="alcoholBefore">
          <el-input v-model="form.alcoholBefore" placeholder="请输入蒸操作酒精度（%）" />
        </el-form-item>
        <el-form-item label="蒸操作酒精度（%）" prop="alcoholAfter">
          <el-input v-model="form.alcoholAfter" placeholder="请输入蒸操作酒精度（%）" />
        </el-form-item>
        <el-form-item label="实际酒精度（%）" prop="alcoholActual">
          <el-input v-model="form.alcoholActual" placeholder="请输入实际酒精度（%）" />
        </el-form-item>
        <el-form-item label="酒精度误差（%）" prop="alcoholError">
          <el-input v-model="form.alcoholError" placeholder="请输入酒精度误差（%）" />
        </el-form-item>
        <el-form-item label="产酒率（%）" prop="wineOutputRate">
          <el-input v-model="form.wineOutputRate" placeholder="请输入产酒率（%）" />
        </el-form-item>
        <el-form-item label="色泽检测结果" prop="colorResult">
          <el-input v-model="form.colorResult" placeholder="请输入色泽检测结果" />
        </el-form-item>
        <el-form-item label="气味检测结果" prop="smellResult">
          <el-input v-model="form.smellResult" placeholder="请输入气味检测结果" />
        </el-form-item>
        <el-form-item label="?感检测结果" prop="tasteResult">
          <el-input v-model="form.tasteResult" placeholder="请输入感检测结果" />
        </el-form-item>
        <el-form-item label="质检结果（1格 2操作格 3待检）" prop="qualityResult">
          <el-input v-model="form.qualityResult" placeholder="请输入质检结果（1格 2操作格 3待检）" />
        </el-form-item>
        <el-form-item label="质评分（0-100）" prop="qualityScore">
          <el-input v-model="form.qualityScore" placeholder="请输入质评分（0-100）" />
        </el-form-item>
        <el-form-item label="操作格原因" prop="unqualifiedReason">
          <el-input v-model="form.unqualifiedReason" type="textarea" placeholder="删除" />
        </el-form-item>
        <el-form-item label="处建议" prop="handleSuggestion">
          <el-input v-model="form.handleSuggestion" type="textarea" placeholder="删除" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="删除" />
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
import { listQuality, getQuality, delQuality, addQuality, updateQuality } from "@/api/system/quality"

export default {
  name: "Quality",
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
      // 质检记录人请输入
      qualityList: [],
      // 删除
      title: "",
      // open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNo: null,
        standardId: null,
        inspectTime: null,
        inspectorName: null,
        alcoholBefore: null,
        alcoholAfter: null,
        alcoholActual: null,
        alcoholError: null,
        wineOutputRate: null,
        colorResult: null,
        smellResult: null,
        tasteResult: null,
        qualityResult: null,
        qualityScore: null,
        unqualifiedReason: null,
        handleSuggestion: null,
      },
      form: {},
      rules: {
        batchNo: [
          { required: true, message: "批次删除", trigger: "blur" }
        ],
        standardId: [
          { required: true, message: "质检标准ID请输入", trigger: "blur" }
        ],
        inspectTime: [
          { required: true, message: "检测时间请输入", trigger: "blur" }
        ],
        inspectorName: [
          { required: true, message: "检测人请输入", trigger: "blur" }
        ],
        qualityResult: [
          { required: true, message: "质检结果（1格 2操作格 3待检）请输入", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 操作质检记录人? */
    getList() {
      this.loading = true
      listQuality(this.queryParams).then(response => {
        this.qualityList = response.rows
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
        recordId: null,
        batchNo: null,
        standardId: null,
        inspectTime: null,
        inspectorName: null,
        alcoholBefore: null,
        alcoholAfter: null,
        alcoholActual: null,
        alcoholError: null,
        wineOutputRate: null,
        colorResult: null,
        smellResult: null,
        tasteResult: null,
        qualityResult: null,
        qualityScore: null,
        unqualifiedReason: null,
        handleSuggestion: null,
        remark: null,
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
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 请输入 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "操作质检记录"
    },
    /** 请输入 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getQuality(recordId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "操作质检记录"
      })
    },
    /** 请输入 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateQuality(this.form).then(response => {
              this.$modal.msgSuccess("请输入")
              this.open = false
              this.getList()
            })
          } else {
            addQuality(this.form).then(response => {
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
      const recordIds = row.recordId || this.ids
      this.$modal.confirm('请输入质检记录人操作"' + recordIds + '"删除').then(function() {
        return delQuality(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("请输入")
      }).catch(() => {})
    },
    /** 请输入 */
    handleExport() {
      this.download('system/quality/export', {
        ...this.queryParams
      }, `quality_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
