<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配方ID" prop="formulaId">
        <el-input
          v-model="queryParams.formulaId"
          placeholder="请输入关联配方ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产阶段" prop="stageKey">
        <el-select v-model="queryParams.stageKey" placeholder="请选择生产阶段" clearable>
          <el-option label="糖化" value="mashing" />
          <el-option label="煮沸" value="boiling" />
          <el-option label="发酵" value="fermentation" />
        </el-select>
      </el-form-item>
      <el-form-item label="参数名称" prop="paramName">
        <el-input
          v-model="queryParams.paramName"
          placeholder="请输入参数名称"
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
          v-hasPermi="['brew:standard:add']"
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
          v-hasPermi="['brew:standard:edit']"
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
          v-hasPermi="['brew:standard:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['brew:standard:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标准ID" align="center" prop="standardId" />
      <el-table-column label="关联配方ID" align="center" prop="formulaId" />
      <el-table-column label="生产阶段" align="center" prop="stageKey">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.stageKey == 'mashing'">糖化</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'boiling'" type="warning">煮沸</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'fermentation'" type="success">发酵</el-tag>
          <el-tag v-else>{{ scope.row.stageKey }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="参数名称" align="center" prop="paramName" />
      <el-table-column label="参数代码" align="center" prop="paramCode" />
      <el-table-column label="标准值" align="center" prop="standardValue" />
      <el-table-column label="允许下限" align="center" prop="minValue" />
      <el-table-column label="允许上限" align="center" prop="maxValue" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="排序号" align="center" prop="orderNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['brew:standard:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['brew:standard:remove']"
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

    <!-- 添加或修改工艺参数标准对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="配方ID" prop="formulaId">
          <el-input v-model="form.formulaId" placeholder="请输入关联配方ID" />
        </el-form-item>
        <el-form-item label="生产阶段" prop="stageKey">
          <el-select v-model="form.stageKey" placeholder="请选择生产阶段">
            <el-option label="糖化" value="mashing" />
            <el-option label="煮沸" value="boiling" />
            <el-option label="发酵" value="fermentation" />
          </el-select>
        </el-form-item>
        <el-form-item label="参数名称" prop="paramName">
          <el-input v-model="form.paramName" placeholder="请输入参数名称" />
        </el-form-item>
        <el-form-item label="参数代码" prop="paramCode">
          <el-input v-model="form.paramCode" placeholder="请输入参数代码" />
        </el-form-item>
        <el-form-item label="标准值" prop="standardValue">
          <el-input v-model="form.standardValue" placeholder="请输入标准值" />
        </el-form-item>
        <el-form-item label="允许下限" prop="minValue">
          <el-input v-model="form.minValue" placeholder="请输入允许下限" />
        </el-form-item>
        <el-form-item label="允许上限" prop="maxValue">
          <el-input v-model="form.maxValue" placeholder="请输入允许上限" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="排序号" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入排序号" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listStandard, getStandard, delStandard, addStandard, updateStandard } from "@/api/brew/standard";

export default {
  name: "Standard",
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
      // 工艺参数标准表格数据
      standardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        formulaId: null,
        stageKey: null,
        paramName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        formulaId: [
          { required: true, message: "关联配方ID不能为空", trigger: "blur" }
        ],
        stageKey: [
          { required: true, message: "生产阶段不能为空", trigger: "change" }
        ],
        paramName: [
          { required: true, message: "参数名称不能为空", trigger: "blur" }
        ],
        paramCode: [
          { required: true, message: "参数代码不能为空", trigger: "blur" }
        ],
        standardValue: [
          { required: true, message: "标准值不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工艺参数标准列表 */
    getList() {
      this.loading = true;
      listStandard(this.queryParams).then(response => {
        this.standardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        standardId: null,
        formulaId: null,
        stageKey: null,
        paramName: null,
        paramCode: null,
        standardValue: null,
        minValue: null,
        maxValue: null,
        unit: null,
        orderNum: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.standardId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工艺参数标准";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const standardId = row.standardId || this.ids
      getStandard(standardId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工艺参数标准";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.standardId != null) {
            updateStandard(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStandard(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const standardIds = row.standardId || this.ids;
      this.$modal.confirm('是否确认删除工艺参数标准编号为"' + standardIds + '"的数据项？').then(function() {
        return delStandard(standardIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('brew/standard/export', {
        ...this.queryParams
      }, `standard_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
