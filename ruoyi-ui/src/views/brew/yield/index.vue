<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产批次" prop="batchId">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入生产批次ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阶段" prop="stageKey">
        <el-select v-model="queryParams.stageKey" placeholder="请选择阶段" clearable>
          <el-option label="糖化" value="mashing" />
          <el-option label="发酵" value="fermentation" />
          <el-option label="包装" value="packaging" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否异常" prop="isAbnormal">
        <el-select v-model="queryParams.isAbnormal" placeholder="请选择是否异常" clearable>
          <el-option label="否" value="0" />
          <el-option label="是" value="1" />
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
          v-hasPermi="['brew:yield:add']"
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
          v-hasPermi="['brew:yield:edit']"
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
          v-hasPermi="['brew:yield:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['brew:yield:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="yieldList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="损耗ID" align="center" prop="yieldId" />
      <el-table-column label="生产批次" align="center" prop="batchId" />
      <el-table-column label="阶段" align="center" prop="stageKey">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.stageKey == 'mashing'">糖化</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'fermentation'" type="success">发酵</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'packaging'" type="warning">包装</el-tag>
          <el-tag v-else>{{ scope.row.stageKey }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="投入数量" align="center" prop="inputQty" />
      <el-table-column label="产出数量" align="center" prop="outputQty" />
      <el-table-column label="损耗数量" align="center" prop="lossQty" />
      <el-table-column label="损耗率(%)" align="center" prop="lossRate">
        <template slot-scope="scope">
           <span :style="{ color: scope.row.lossRate > 5 ? 'red' : 'green' }">
             {{ scope.row.lossRate }}%
           </span>
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="是否异常" align="center" prop="isAbnormal">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isAbnormal == '1'" type="danger">是</el-tag>
          <el-tag v-else type="success">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['brew:yield:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['brew:yield:remove']"
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

    <!-- 添加或修改批次损耗分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="生产批次" prop="batchId">
          <el-input v-model="form.batchId" placeholder="请输入生产批次ID" />
        </el-form-item>
        <el-form-item label="阶段" prop="stageKey">
          <el-select v-model="form.stageKey" placeholder="请选择阶段">
            <el-option label="糖化" value="mashing" />
            <el-option label="发酵" value="fermentation" />
            <el-option label="包装" value="packaging" />
          </el-select>
        </el-form-item>
        <el-form-item label="投入数量" prop="inputQty">
          <el-input v-model="form.inputQty" placeholder="请输入投入数量" @change="calculateLoss" />
        </el-form-item>
        <el-form-item label="产出数量" prop="outputQty">
          <el-input v-model="form.outputQty" placeholder="请输入产出数量" @change="calculateLoss" />
        </el-form-item>
        <el-form-item label="损耗数量" prop="lossQty">
          <el-input v-model="form.lossQty" placeholder="自动计算" readonly />
        </el-form-item>
        <el-form-item label="损耗率(%)" prop="lossRate">
          <el-input v-model="form.lossRate" placeholder="自动计算" readonly />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="是否异常" prop="isAbnormal">
          <el-radio-group v-model="form.isAbnormal">
            <el-radio label="0">否</el-radio>
            <el-radio label="1">是</el-radio>
          </el-radio-group>
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
import { listYield, getYield, delYield, addYield, updateYield } from "@/api/brew/yield";

export default {
  name: "Yield",
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
      // 批次损耗分析表格数据
      yieldList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchId: null,
        stageKey: null,
        isAbnormal: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchId: [
          { required: true, message: "生产批次ID不能为空", trigger: "blur" }
        ],
        stageKey: [
          { required: true, message: "阶段不能为空", trigger: "change" }
        ],
        inputQty: [
          { required: true, message: "投入数量不能为空", trigger: "blur" }
        ],
        outputQty: [
          { required: true, message: "产出数量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询批次损耗分析列表 */
    getList() {
      this.loading = true;
      listYield(this.queryParams).then(response => {
        this.yieldList = response.rows;
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
        yieldId: null,
        batchId: null,
        stageKey: null,
        inputQty: null,
        outputQty: null,
        lossQty: null,
        lossRate: null,
        unit: null,
        isAbnormal: "0",
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 计算损耗 */
    calculateLoss() {
        if (this.form.inputQty && this.form.outputQty) {
            let input = parseFloat(this.form.inputQty);
            let output = parseFloat(this.form.outputQty);
            if (!isNaN(input) && !isNaN(output)) {
                this.form.lossQty = (input - output).toFixed(2);
                if (input > 0) {
                    this.form.lossRate = ((this.form.lossQty / input) * 100).toFixed(2);
                } else {
                    this.form.lossRate = 0;
                }
            }
        }
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
      this.ids = selection.map(item => item.yieldId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加损耗分析记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const yieldId = row.yieldId || this.ids
      getYield(yieldId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改损耗分析记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.yieldId != null) {
            updateYield(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYield(this.form).then(response => {
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
      const yieldIds = row.yieldId || this.ids;
      this.$modal.confirm('是否确认删除批次损耗分析编号为"' + yieldIds + '"的数据项？').then(function() {
        return delYield(yieldIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('brew/yield/export', {
        ...this.queryParams
      }, `yield_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
