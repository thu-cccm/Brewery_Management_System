<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="equipmentName">
        <el-input
          v-model="queryParams.equipmentName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="equipmentCode">
        <el-input
          v-model="queryParams.equipmentCode"
          placeholder="请输入设备编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="equipmentType">
        <el-select v-model="queryParams.equipmentType" placeholder="请选择设备类型" clearable>
          <el-option label="发酵罐" value="0" />
          <el-option label="糖化锅" value="1" />
          <el-option label="过滤槽" value="2" />
          <el-option label="灌装机" value="3" />
          <el-option label="其他" value="9" />
        </el-select>
      </el-form-item>
      <el-form-item label="设备状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择设备状态" clearable>
          <el-option label="空闲" value="0" />
          <el-option label="运行中" value="1" />
          <el-option label="清洗中" value="2" />
          <el-option label="维修中" value="3" />
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
          v-hasPermi="['brew:equipment:add']"
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
          v-hasPermi="['brew:equipment:edit']"
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
          v-hasPermi="['brew:equipment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['brew:equipment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="equipmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备ID" align="center" prop="equipmentId" />
      <el-table-column label="设备名称" align="center" prop="equipmentName" />
      <el-table-column label="设备编号" align="center" prop="equipmentCode" />
      <el-table-column label="设备类型" align="center" prop="equipmentType">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.equipmentType == '0'">发酵罐</el-tag>
          <el-tag v-else-if="scope.row.equipmentType == '1'" type="success">糖化锅</el-tag>
          <el-tag v-else-if="scope.row.equipmentType == '2'" type="info">过滤槽</el-tag>
          <el-tag v-else-if="scope.row.equipmentType == '3'" type="warning">灌装机</el-tag>
          <el-tag v-else type="info">其他</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="设备状态" align="center" prop="status">
        <template slot-scope="scope">
           <el-tag v-if="scope.row.status == '0'" type="success">空闲</el-tag>
           <el-tag v-else-if="scope.row.status == '1'" type="primary">运行中</el-tag>
           <el-tag v-else-if="scope.row.status == '2'" type="warning">清洗中</el-tag>
           <el-tag v-else-if="scope.row.status == '3'" type="danger">维修中</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="存放位置" align="center" prop="location" />
      <el-table-column label="容量(L)" align="center" prop="capacity" />
      <el-table-column label="上次维保日期" align="center" prop="lastMaintenanceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastMaintenanceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['brew:equipment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['brew:equipment:remove']"
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

    <!-- 添加或修改设备台账对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="equipmentName">
          <el-input v-model="form.equipmentName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备编号" prop="equipmentCode">
          <el-input v-model="form.equipmentCode" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="设备类型" prop="equipmentType">
          <el-select v-model="form.equipmentType" placeholder="请选择设备类型">
            <el-option label="发酵罐" value="0" />
            <el-option label="糖化锅" value="1" />
            <el-option label="过滤槽" value="2" />
            <el-option label="灌装机" value="3" />
            <el-option label="其他" value="9" />
          </el-select>
        </el-form-item>
        <el-form-item label="设备状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择设备状态">
            <el-option label="空闲" value="0" />
            <el-option label="运行中" value="1" />
            <el-option label="清洗中" value="2" />
            <el-option label="维修中" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="存放位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入存放位置" />
        </el-form-item>
        <el-form-item label="容量(L)" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入容量(L)" />
        </el-form-item>
        <el-form-item label="生产厂家" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入生产厂家" />
        </el-form-item>
        <el-form-item label="购买日期" prop="purchaseDate">
          <el-date-picker clearable
            v-model="form.purchaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择购买日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上次维保" prop="lastMaintenanceDate">
          <el-date-picker clearable
            v-model="form.lastMaintenanceDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上次维保日期">
          </el-date-picker>
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
import { listEquipment, getEquipment, delEquipment, addEquipment, updateEquipment } from "@/api/brew/equipment";

export default {
  name: "Equipment",
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
      // 设备台账表格数据
      equipmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equipmentName: null,
        equipmentCode: null,
        equipmentType: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        equipmentName: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
        equipmentCode: [
          { required: true, message: "设备编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备台账列表 */
    getList() {
      this.loading = true;
      listEquipment(this.queryParams).then(response => {
        this.equipmentList = response.rows;
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
        equipmentId: null,
        equipmentName: null,
        equipmentCode: null,
        equipmentType: null,
        status: "0",
        location: null,
        capacity: null,
        manufacturer: null,
        purchaseDate: null,
        lastMaintenanceDate: null,
        remark: null,
        delFlag: null,
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
      this.ids = selection.map(item => item.equipmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备台账";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const equipmentId = row.equipmentId || this.ids
      getEquipment(equipmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备台账";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.equipmentId != null) {
            updateEquipment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEquipment(this.form).then(response => {
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
      const equipmentIds = row.equipmentId || this.ids;
      this.$modal.confirm('是否确认删除设备台账编号为"' + equipmentIds + '"的数据项？').then(function() {
        return delEquipment(equipmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('brew/equipment/export', {
        ...this.queryParams
      }, `equipment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
