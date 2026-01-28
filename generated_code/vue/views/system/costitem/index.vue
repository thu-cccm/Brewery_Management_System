<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="æˆæœ¬é¡¹ç¼–ç " prop="itemCode">
        <el-input
          v-model="queryParams.itemCode"
          placeholder="请输入æˆæœ¬é¡¹ç¼–ç "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="æˆæœ¬é¡¹åç§°" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入æˆæœ¬é¡¹åç§°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰" prop="calculationMethod">
        <el-input
          v-model="queryParams.calculationMethod"
          placeholder="请输入è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰" prop="unitCost">
        <el-input
          v-model="queryParams.unitCost"
          placeholder="请输入å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰"
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
      <el-table-column label="æˆæœ¬é¡¹ID" align="center" prop="itemId" />
      <el-table-column label="æˆæœ¬é¡¹ç¼–ç " align="center" prop="itemCode" />
      <el-table-column label="æˆæœ¬é¡¹åç§°" align="center" prop="itemName" />
      <el-table-column label="æˆæœ¬ç±»åž‹ï¼ˆ1åŽŸæ–™æˆæœ¬ 2èƒ½è€—æˆæœ¬ 3äººå·¥æˆæœ¬ 4å…¶ä»–ï¼‰" align="center" prop="itemType" />
      <el-table-column label="è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰" align="center" prop="calculationMethod" />
      <el-table-column label="å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰" align="center" prop="unitCost" />
      <el-table-column label="çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰" align="center" prop="status" />
      <el-table-column label="å¤‡æ³¨" align="center" prop="remark" />
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

    <!-- 添加或修改æˆæœ¬é¡¹对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="æˆæœ¬é¡¹ç¼–ç " prop="itemCode">
          <el-input v-model="form.itemCode" placeholder="请输入æˆæœ¬é¡¹ç¼–ç " />
        </el-form-item>
        <el-form-item label="æˆæœ¬é¡¹åç§°" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入æˆæœ¬é¡¹åç§°" />
        </el-form-item>
        <el-form-item label="è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰" prop="calculationMethod">
          <el-input v-model="form.calculationMethod" placeholder="请输入è®¡ç®—æ–¹å¼ï¼ˆ1å›ºå®šå€¼ 2æŒ‰äº§é‡ 3æŒ‰å·¥æ—¶ï¼‰" />
        </el-form-item>
        <el-form-item label="å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰" prop="unitCost">
          <el-input v-model="form.unitCost" placeholder="请输入å•ä½æˆæœ¬ï¼ˆå…ƒï¼‰" />
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
import { listCostitem, getCostitem, delCostitem, addCostitem, updateCostitem } from "@/api/system/costitem"

export default {
  name: "Costitem",
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
      // æˆæœ¬é¡¹表格数据
      costitemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
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
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        itemCode: [
          { required: true, message: "æˆæœ¬é¡¹ç¼–ç 不能为空", trigger: "blur" }
        ],
        itemName: [
          { required: true, message: "æˆæœ¬é¡¹åç§°不能为空", trigger: "blur" }
        ],
        itemType: [
          { required: true, message: "æˆæœ¬ç±»åž‹ï¼ˆ1åŽŸæ–™æˆæœ¬ 2èƒ½è€—æˆæœ¬ 3äººå·¥æˆæœ¬ 4å…¶ä»–ï¼‰不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询æˆæœ¬é¡¹列表 */
    getList() {
      this.loading = true
      listCostitem(this.queryParams).then(response => {
        this.costitemList = response.rows
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
        itemId: null,
        itemCode: null,
        itemName: null,
        itemType: null,
        calculationMethod: null,
        unitCost: null,
        status: null,
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
      this.ids = selection.map(item => item.itemId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加æˆæœ¬é¡¹"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const itemId = row.itemId || this.ids
      getCostitem(itemId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改æˆæœ¬é¡¹"
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
      this.$modal.confirm('是否确认删除æˆæœ¬é¡¹编号为"' + itemIds + '"的数据项？').then(function() {
        return delCostitem(itemIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/costitem/export', {
        ...this.queryParams
      }, `costitem_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
