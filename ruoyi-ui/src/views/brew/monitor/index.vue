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
      <el-form-item label="生产阶段" prop="stageKey">
        <el-select v-model="queryParams.stageKey" placeholder="请选择生产阶段" clearable>
          <el-option label="糖化" value="mashing" />
          <el-option label="煮沸" value="boiling" />
          <el-option label="发酵" value="fermentation" />
          <el-option label="过滤" value="filtering" />
          <el-option label="包装" value="packaging" />
        </el-select>
      </el-form-item>
      <el-form-item label="操作员" prop="operator">
        <el-input
          v-model="queryParams.operator"
          placeholder="请输入操作员"
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
          v-hasPermi="['brew:monitor:add']"
        >记录数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['brew:monitor:edit']"
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
          v-hasPermi="['brew:monitor:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['brew:monitor:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="monitorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="监控ID" align="center" prop="monitorId" />
      <el-table-column label="生产批次" align="center" prop="batchId" />
      <el-table-column label="生产阶段" align="center" prop="stageKey">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.stageKey == 'mashing'">糖化</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'boiling'" type="warning">煮沸</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'fermentation'" type="success">发酵</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'filtering'" type="info">过滤</el-tag>
          <el-tag v-else-if="scope.row.stageKey == 'packaging'">包装</el-tag>
          <el-tag v-else>{{ scope.row.stageKey }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="记录时间" align="center" prop="recordTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="温度(℃)" align="center" prop="temperature" />
      <el-table-column label="压力(MPa)" align="center" prop="pressure" />
      <el-table-column label="糖度(°P)" align="center" prop="sugarDegree" />
      <el-table-column label="PH值" align="center" prop="phValue" />
      <el-table-column label="酒精度" align="center" prop="alcohol" />
      <el-table-column label="操作员" align="center" prop="operator" />
      <el-table-column label="异常" align="center" prop="isAbnormal">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isAbnormal == '1'" type="danger">异常</el-tag>
          <el-tag v-else type="success">正常</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['brew:monitor:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['brew:monitor:remove']"
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

    <!-- 添加或修改生产过程监控对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="生产批次ID" prop="batchId">
          <el-input v-model="form.batchId" placeholder="请输入生产批次ID" />
        </el-form-item>
        <el-form-item label="生产阶段" prop="stageKey">
          <el-select v-model="form.stageKey" placeholder="请选择生产阶段">
            <el-option label="糖化" value="mashing" />
            <el-option label="煮沸" value="boiling" />
            <el-option label="发酵" value="fermentation" />
            <el-option label="过滤" value="filtering" />
            <el-option label="包装" value="packaging" />
          </el-select>
        </el-form-item>
        <el-form-item label="记录时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择记录时间">
          </el-date-picker>
        </el-form-item>
        <el-row>
            <el-col :span="12">
                <el-form-item label="温度(℃)" prop="temperature">
                  <el-input-number v-model="form.temperature" :precision="2" :step="0.1" style="width: 100%"></el-input-number>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="压力(MPa)" prop="pressure">
                  <el-input-number v-model="form.pressure" :precision="2" :step="0.01" style="width: 100%"></el-input-number>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <el-form-item label="糖度(°P)" prop="sugarDegree">
                  <el-input-number v-model="form.sugarDegree" :precision="2" :step="0.1" style="width: 100%"></el-input-number>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="PH值" prop="phValue">
                  <el-input-number v-model="form.phValue" :precision="2" :step="0.1" style="width: 100%"></el-input-number>
                </el-form-item>
            </el-col>
        </el-row>
        <el-form-item label="酒精度" prop="alcohol">
           <el-input-number v-model="form.alcohol" :precision="2" :step="0.1" style="width: 45%"></el-input-number>
           <span style="margin-left: 10px">%vol</span>
        </el-form-item>
        <el-form-item label="操作员" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入操作员" />
        </el-form-item>
        <el-form-item label="是否异常" prop="isAbnormal">
          <el-radio-group v-model="form.isAbnormal">
             <el-radio label="0">正常</el-radio>
             <el-radio label="1">异常</el-radio>
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
import { listMonitor, getMonitor, delMonitor, addMonitor, updateMonitor } from "@/api/brew/monitor";

export default {
  name: "Monitor",
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
      // 生产过程监控表格数据
      monitorList: [],
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
        operator: null,
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
          { required: true, message: "生产阶段不能为空", trigger: "change" }
        ],
        recordTime: [
          { required: true, message: "记录时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产过程监控列表 */
    getList() {
      this.loading = true;
      listMonitor(this.queryParams).then(response => {
        this.monitorList = response.rows;
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
        monitorId: null,
        batchId: null,
        stageKey: null,
        recordTime: new Date(),
        temperature: null,
        pressure: null,
        sugarDegree: null,
        phValue: null,
        alcohol: null,
        operator: null,
        isAbnormal: "0",
        remark: null,
        createTime: null
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
      this.ids = selection.map(item => item.monitorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加监控记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const monitorId = row.monitorId || this.ids
      getMonitor(monitorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改监控记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.monitorId != null) {
            updateMonitor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMonitor(this.form).then(response => {
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
      const monitorIds = row.monitorId || this.ids;
      this.$modal.confirm('是否确认删除生产过程监控编号为"' + monitorIds + '"的数据项？').then(function() {
        return delMonitor(monitorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('brew/monitor/export', {
        ...this.queryParams
      }, `monitor_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
