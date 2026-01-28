<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模ID" prop="templateId">
        <el-input
          v-model="queryParams.templateId"
          placeholder="请输入模ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原料ID" prop="materialId">
        <el-input
          v-model="queryParams.materialId"
          placeholder="请输入原料ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="?比系数（原料用?/基准产?）" prop="materialRatio">
        <el-input
          v-model="queryParams.materialRatio"
          placeholder="请输入比系数（原料用?/基准产?）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="基准用?" prop="baseQuantity">
        <el-input
          v-model="queryParams.baseQuantity"
          placeholder="请输入基准用?"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是主料（0? 1是）" prop="isMainMaterial">
        <el-input
          v-model="queryParams.isMainMaterial"
          placeholder="请输入是主料（0? 1是）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排?" prop="sortOrder">
        <el-input
          v-model="queryParams.sortOrder"
          placeholder="请输入排?"
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
      <el-table-column label="明细ID" align="center" prop="detailId" />
      <el-table-column label="模ID" align="center" prop="templateId" />
      <el-table-column label="原料ID" align="center" prop="materialId" />
      <el-table-column label="?比系数（原料用?/基准产?）" align="center" prop="materialRatio" />
      <el-table-column label="基准用?" align="center" prop="baseQuantity" />
      <el-table-column label="是主料（0? 1是）" align="center" prop="isMainMaterial" />
      <el-table-column label="排?" align="center" prop="sortOrder" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:detail:edit']"
          >操作</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:detail:remove']"
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

    <!-- 请输入方明细请输入 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模ID" prop="templateId">
          <el-input v-model="form.templateId" placeholder="请输入模ID" />
        </el-form-item>
        <el-form-item label="原料ID" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入原料ID" />
        </el-form-item>
        <el-form-item label="?比系数（原料用?/基准产?）" prop="materialRatio">
          <el-input v-model="form.materialRatio" placeholder="请输入比系数（原料用?/基准产?）" />
        </el-form-item>
        <el-form-item label="基准用?" prop="baseQuantity">
          <el-input v-model="form.baseQuantity" placeholder="请输入基准用?" />
        </el-form-item>
        <el-form-item label="是主料（0? 1是）" prop="isMainMaterial">
          <el-input v-model="form.isMainMaterial" placeholder="请输入是主料（0? 1是）" />
        </el-form-item>
        <el-form-item label="排?" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排?" />
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
import { listDetail, getDetail, delDetail, addDetail, updateDetail } from "@/api/system/detail"

export default {
  name: "Detail",
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
      // ?方明细请输入
      detailList: [],
      // 删除
      title: "",
      // open: false,
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
      form: {},
      rules: {
        templateId: [
          { required: true, message: "模ID请输入", trigger: "blur" }
        ],
        materialId: [
          { required: true, message: "原料ID请输入", trigger: "blur" }
        ],
        materialRatio: [
          { required: true, message: "?比系数（原料用?/基准产?）请输入", trigger: "blur" }
        ],
        baseQuantity: [
          { required: true, message: "基准用删除", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 请输入方明细操作 */
    getList() {
      this.loading = true
      listDetail(this.queryParams).then(response => {
        this.detailList = response.rows
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
      this.ids = selection.map(item => item.detailId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 请输入 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "请输入方明细"
    },
    /** 请输入 */
    handleUpdate(row) {
      this.reset()
      const detailId = row.detailId || this.ids
      getDetail(detailId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "请输入方明细"
      })
    },
    /** 请输入 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.detailId != null) {
            updateDetail(this.form).then(response => {
              this.$modal.msgSuccess("请输入")
              this.open = false
              this.getList()
            })
          } else {
            addDetail(this.form).then(response => {
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
      const detailIds = row.detailId || this.ids
      this.$modal.confirm('请输入方明细请输入"' + detailIds + '"删除').then(function() {
        return delDetail(detailIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("请输入")
      }).catch(() => {})
    },
    /** 请输入 */
    handleExport() {
      this.download('system/detail/export', {
        ...this.queryParams
      }, `detail_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
