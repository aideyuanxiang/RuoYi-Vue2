<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="旅游名城" prop="travelName">
        <el-input
          v-model="queryParams.travelName"
          placeholder="请输入旅游名城"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目的地" prop="destination">
        <el-input
          v-model="queryParams.destination"
          placeholder="请输入目的地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出发地" prop="departure">
        <el-input
          v-model="queryParams.departure"
          placeholder="请输入出发地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出发时间" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出发时间">
        </el-date-picker>
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
          v-hasPermi="['travel:travel:add']"
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
          v-hasPermi="['travel:travel:edit']"
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
          v-hasPermi="['travel:travel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['travel:travel:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="travelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="travelId" />
      <el-table-column label="旅游名城" align="center" prop="travelName" />
      <el-table-column label="目的地" align="center" prop="destination" />
      <el-table-column label="出发地" align="center" prop="departure" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="出发时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交通工具" align="center" prop="travelTransform">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.travel_transform" :value="scope.row.travelTransform ? scope.row.travelTransform.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['travel:travel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['travel:travel:remove']"
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

    <!-- 添加或修改旅游信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="旅游名城" prop="travelName">
          <el-input v-model="form.travelName" placeholder="请输入旅游名城" />
        </el-form-item>
        <el-form-item label="目的地" prop="destination">
          <el-input v-model="form.destination" placeholder="请输入目的地" />
        </el-form-item>
        <el-form-item label="出发地" prop="departure">
          <el-input v-model="form.departure" placeholder="请输入出发地" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="出发时间" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交通工具">
          <el-checkbox-group v-model="form.travelTransform">
            <el-checkbox
              v-for="dict in dict.type.travel_transform"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-divider content-position="center">旅游订单信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMyTravelOrder">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMyTravelOrder">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="myTravelOrderList" :row-class-name="rowMyTravelOrderIndex" @selection-change="handleMyTravelOrderSelectionChange" ref="myTravelOrder">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="支付人" prop="payedBy" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.payedBy" placeholder="请输入支付人" />
            </template>
          </el-table-column>
          <el-table-column label="支付时间" prop="payedDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.payedDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择支付时间" />
            </template>
          </el-table-column>
          <el-table-column label="支付金额" prop="payedMoney" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.payedMoney" placeholder="请输入支付金额" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTravel, getTravel, delTravel, addTravel, updateTravel } from "@/api/travel/travel";

export default {
  name: "Travel",
  dicts: ['travel_transform'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMyTravelOrder: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 旅游信息表格数据
      travelList: [],
      // 旅游订单表格数据
      myTravelOrderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        travelName: null,
        destination: null,
        departure: null,
        price: null,
        startDate: null,
        travelTransform: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        travelName: [
          { required: true, message: "旅游名城不能为空", trigger: "blur" }
        ],
        travelTransform: [
          { required: true, message: "交通工具不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询旅游信息列表 */
    getList() {
      this.loading = true;
      listTravel(this.queryParams).then(response => {
        this.travelList = response.rows;
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
        travelId: null,
        travelName: null,
        destination: null,
        departure: null,
        price: null,
        startDate: null,
        travelTransform: []
      };
      this.myTravelOrderList = [];
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.travelId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加旅游信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const travelId = row.travelId || this.ids
      getTravel(travelId).then(response => {
        this.form = response.data;
        this.form.travelTransform = this.form.travelTransform.split(",");
        this.myTravelOrderList = response.data.myTravelOrderList;
        this.open = true;
        this.title = "修改旅游信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.travelTransform = this.form.travelTransform.join(",");
          this.form.myTravelOrderList = this.myTravelOrderList;
          if (this.form.travelId != null) {
            updateTravel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTravel(this.form).then(response => {
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
      const travelIds = row.travelId || this.ids;
      this.$modal.confirm('是否确认删除旅游信息编号为"' + travelIds + '"的数据项？').then(function() {
        return delTravel(travelIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 旅游订单序号 */
    rowMyTravelOrderIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 旅游订单添加按钮操作 */
    handleAddMyTravelOrder() {
      let obj = {};
      obj.payedBy = "";
      obj.payedDate = "";
      obj.payedMoney = "";
      this.myTravelOrderList.push(obj);
    },
    /** 旅游订单删除按钮操作 */
    handleDeleteMyTravelOrder() {
      if (this.checkedMyTravelOrder.length == 0) {
        this.$modal.msgError("请先选择要删除的旅游订单数据");
      } else {
        const myTravelOrderList = this.myTravelOrderList;
        const checkedMyTravelOrder = this.checkedMyTravelOrder;
        this.myTravelOrderList = myTravelOrderList.filter(function(item) {
          return checkedMyTravelOrder.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleMyTravelOrderSelectionChange(selection) {
      this.checkedMyTravelOrder = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('travel/travel/export', {
        ...this.queryParams
      }, `travel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
