<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="成本项编码" prop="itemCode">
        <el-input
          v-model="queryParams.itemCode"
          placeholder="请输入成本项编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成本项名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入成本项名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计算方式" prop="calculationMethod">
        <el-select v-model="queryParams.calculationMethod" placeholder="请选择计算方式" clearable>
          <el-option label="固定值" value="1" />
          <el-option label="按产量" value="2" />
          <el-option label="按工时" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="单位成本(元)" prop="unitCost">
        <el-input
          v-model="queryParams.unitCost"
          placeholder="请输入单位成本"
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
          v-hasPermi="['system:costitem:add']"
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
          v-hasPermi="['system:costitem:edit']"
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
          v-hasPermi="['system:costitem:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:costitem:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="costitemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="成本项ID" align="center" prop="itemId" />
      <el-table-column label="成本项编码" align="center" prop="itemCode" />
      <el-table-column label="成本项名称" align="center" prop="itemName" />
      <el-table-column label="成本类型" align="center" prop="itemType">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.itemType == '1'">原料成本</el-tag>
          <el-tag v-else-if="scope.row.itemType == '2'" type="warning">能耗成本</el-tag>
          <el-tag v-else-if="scope.row.itemType == '3'" type="success">人工成本</el-tag>
          <el-tag v-else-if="scope.row.itemType == '4'" type="info">其他</el-tag>
          <span v-else>{{ scope.row.itemType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计算方式" align="center" prop="calculationMethod">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.calculationMethod == '1'">固定值</el-tag>
          <el-tag v-else-if="scope.row.calculationMethod == '2'" type="success">按产量</el-tag>
          <el-tag v-else-if="scope.row.calculationMethod == '3'" type="warning">按工时</el-tag>
          <span v-else>{{ scope.row.calculationMethod }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单位成本(元)" align="center" prop="unitCost" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == '0'" type="success">正常</el-tag>
          <el-tag v-else type="danger">停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:costitem:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:costitem:remove']"
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

    <!-- 成本项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="成本项编码" prop="itemCode">
          <el-input v-model="form.itemCode" placeholder="请输入成本项编码" />
        </el-form-item>
        <el-form-item label="成本项名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入成本项名称" />
        </el-form-item>
        <el-form-item label="成本类型" prop="itemType">
          <el-select v-model="form.itemType" placeholder="请选择成本类型">
            <el-option label="原料成本" value="1" />
            <el-option label="能耗成本" value="2" />
            <el-option label="人工成本" value="3" />
            <el-option label="其他" value="4" />
          </el-select>
        </el-form-item>
        <el-form-item label="计算方式" prop="calculationMethod">
          <el-select v-model="form.calculationMethod" placeholder="请选择计算方式">
            <el-option label="固定值" value="1" />
            <el-option label="按产量" value="2" />
            <el-option label="按工时" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="单位成本(元)" prop="unitCost">
          <el-input v-model="form.unitCost" placeholder="请输入单位成本" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="0">正常</el-radio>
            <el-radio label="1">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
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
import { listCostitem, getCostitem, delCostitem, addCostitem, updateCostitem } from "@/api/system/costitem"

export default {
  name: "Costitem",
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
      // ?本项请输入
      costitemList: [],
      // 删除
      title: "",
      // open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        itemCode: null,
        itemName: null,
        itemType: null,
        calculationMethod: null,
        unitCost: null,
        status: null,
      },
      form: {},
      rules: {
        itemCode: [
          { required: true, message: "成本项编码不能为空", trigger: "blur" }
        ],
        itemName: [
          { required: true, message: "成本项名称不能为空", trigger: "blur" }
        ],
        itemType: [
          { required: true, message: "请选择成本类型", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询成本项列表 */
    getList() {
      this.loading = true
      listCostitem(this.queryParams).then(response => {
        this.costitemList = response.rows
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
        itemId: null,
        itemCode: null,
        itemName: null,
        itemType: null,
        calculationMethod: null,
        unitCost: null,
        status: "0",
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.itemId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加成本项"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const itemId = row.itemId || this.ids
      getCostitem(itemId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改成本项"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.itemId != null) {
            updateCostitem(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCostitem(this.form).then(response => {
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
      const itemIds = row.itemId || this.ids
      this.$modal.confirm('是否确认删除成本项ID为"' + itemIds + '"的数据项？').then(function() {
        return delCostitem(itemIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 请输入 */
    handleExport() {
      this.download('system/costitem/export', {
        ...this.queryParams
      }, `costitem_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
