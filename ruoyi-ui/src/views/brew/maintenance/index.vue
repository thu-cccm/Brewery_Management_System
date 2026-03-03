<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备ID" prop="equipmentId">
        <el-input
          v-model="queryParams.equipmentId"
          placeholder="请输入设备ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维保类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择维保类型" clearable>
          <el-option label="日常保养" value="0" />
          <el-option label="定期维护" value="1" />
          <el-option label="故障维修" value="2" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 可视化图表区域 -->
    <el-row :gutter="20" class="mb8" v-if="maintenanceList.length > 0">
      <el-col :span="12">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-date"></i> 维保日历视图 (近30天)</span>
          </div>
          <div id="calendarChart" style="height: 300px; width: 100%;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-s-tools"></i> 维保类型统计</span>
          </div>
          <div id="typeChart" style="height: 300px; width: 100%;"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['brew:maintenance:add']"
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
          v-hasPermi="['brew:maintenance:edit']"
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
          v-hasPermi="['brew:maintenance:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maintenanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="维保ID" align="center" prop="maintenanceId" />
      <el-table-column label="设备ID" align="center" prop="equipmentId" />
      <el-table-column label="维保日期" align="center" prop="maintenanceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.maintenanceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="维保类型" align="center" prop="type">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type == '0'">日常保养</el-tag>
          <el-tag v-else-if="scope.row.type == '1'" type="warning">定期维护</el-tag>
          <el-tag v-else-if="scope.row.type == '2'" type="danger">故障维修</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="维保内容" align="center" prop="content" />
      <el-table-column label="执行人" align="center" prop="performer" />
      <el-table-column label="费用(元)" align="center" prop="cost" />
      <el-table-column label="结果" align="center" prop="result" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['brew:maintenance:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['brew:maintenance:remove']"
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

    <!-- 添加或修改维保记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备ID" prop="equipmentId">
          <el-input v-model="form.equipmentId" placeholder="请输入设备ID" />
        </el-form-item>
        <el-form-item label="维保日期" prop="maintenanceDate">
          <el-date-picker clearable
            v-model="form.maintenanceDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择维保日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="维保类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择维保类型">
            <el-option label="日常保养" value="0" />
            <el-option label="定期维护" value="1" />
            <el-option label="故障维修" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="维保内容" prop="content">
          <el-input v-model="form.content" type="textarea" placeholder="请输入维保内容" />
        </el-form-item>
        <el-form-item label="执行人" prop="performer">
          <el-input v-model="form.performer" placeholder="请输入执行人" />
        </el-form-item>
        <el-form-item label="费用" prop="cost">
          <el-input v-model="form.cost" placeholder="请输入费用" />
        </el-form-item>
        <el-form-item label="结果" prop="result">
          <el-input v-model="form.result" placeholder="请输入结果" />
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
import { listMaintenance, getMaintenance, delMaintenance, addMaintenance, updateMaintenance } from "@/api/brew/maintenance";
import * as echarts from 'echarts'

export default {
  name: "Maintenance",
  data() {
    return {
      calendarChart: null,
      typeChart: null,
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
      // 维保记录表格数据
      maintenanceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equipmentId: null,
        type: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        equipmentId: [
          { required: true, message: "设备ID不能为空", trigger: "blur" }
        ],
        maintenanceDate: [
          { required: true, message: "维保日期不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "维保内容不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询维保记录列表 */
    getList() {
      this.loading = true;
      listMaintenance(this.queryParams).then(response => {
        this.maintenanceList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.$nextTick(() => {
          this.initCharts();
        });
      }).catch(() => {
          this.loading = false;
          // Mock data if backend fails
          this.maintenanceList = []; 
      });
    },
    initCharts() {
      if (!this.maintenanceList || this.maintenanceList.length === 0) return;
      this.initCalendarChart();
      this.initTypeChart();
      
      window.addEventListener("resize", () => {
        this.calendarChart && this.calendarChart.resize();
        this.typeChart && this.typeChart.resize();
      });
    },
    initCalendarChart() {
      if (this.calendarChart) this.calendarChart.dispose();
      const chartDom = document.getElementById('calendarChart');
      if (!chartDom) return;
      this.calendarChart = echarts.init(chartDom);
      
      // Simulate calendar data: count maintenance per day
      const dataMap = {};
      this.maintenanceList.forEach(item => {
        const date = item.maintenanceDate.split(' ')[0]; // yyyy-MM-dd
        dataMap[date] = (dataMap[date] || 0) + 1;
      });
      
      const data = Object.keys(dataMap).map(key => [key, dataMap[key]]);
      
      const option = {
        tooltip: {
          position: 'top',
          formatter: function (p) {
            const format = echarts.format.getTooltipMarkup('day', p.value[0]);
            return format + ' 维保次数: ' + p.value[1];
          }
        },
        visualMap: {
          min: 0,
          max: 5,
          calculable: true,
          orient: 'horizontal',
          left: 'center',
          top: 'top',
          inRange: {
            color: ['#ebedf0', '#c6e48b', '#7bc96f', '#239a3b', '#196127']
          }
        },
        calendar: {
          top: 60,
          left: 30,
          right: 30,
          cellSize: ['auto', 13],
          range: new Date().getFullYear(), // Current year
          itemStyle: {
            borderWidth: 0.5
          },
          yearLabel: { show: false }
        },
        series: [{
          type: 'heatmap',
          coordinateSystem: 'calendar',
          data: data
        }]
      };
      this.calendarChart.setOption(option);
    },
    initTypeChart() {
      if (this.typeChart) this.typeChart.dispose();
      const chartDom = document.getElementById('typeChart');
      if (!chartDom) return;
      this.typeChart = echarts.init(chartDom);
      
      const typeMap = { '0': '日常保养', '1': '定期维护', '2': '故障维修' };
      const counts = {};
      this.maintenanceList.forEach(item => {
        const label = typeMap[item.type] || '未知';
        counts[label] = (counts[label] || 0) + 1;
      });
      
      const data = Object.keys(counts).map(key => ({ value: counts[key], name: key }));
      
      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 10,
          data: Object.keys(counts)
        },
        series: [
          {
            name: '维保类型',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '20',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: data
          }
        ]
      };
      this.typeChart.setOption(option);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        maintenanceId: null,
        equipmentId: null,
        maintenanceDate: null,
        type: "0",
        content: null,
        performer: null,
        cost: null,
        result: null,
        remark: null
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
      this.ids = selection.map(item => item.maintenanceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加维保记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const maintenanceId = row.maintenanceId || this.ids
      getMaintenance(maintenanceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改维保记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.maintenanceId != null) {
            updateMaintenance(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaintenance(this.form).then(response => {
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
      const maintenanceIds = row.maintenanceId || this.ids;
      this.$modal.confirm('是否确认删除维保记录编号为"' + maintenanceIds + '"的数据项？').then(function() {
        return delMaintenance(maintenanceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
