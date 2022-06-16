<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="区域名称" prop="zoneName">
        <el-input
          v-model="queryParams.zoneName"
          placeholder="请输入区域名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属上级规划" prop="preZoneId">
        <el-input
          v-model="queryParams.preZoneId"
          placeholder="请输入所属上级规划"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省市县" prop="zoneType">
        <el-select v-model="queryParams.zoneType" placeholder="请选择省市县" clearable>
          <el-option
            v-for="dict in dict.type.zone_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建日期" prop="createDate">
        <el-date-picker clearable
          v-model="queryParams.createDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="区域代码" prop="zoneCode">
        <el-input
          v-model="queryParams.zoneCode"
          placeholder="请输入区域代码"
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
          v-hasPermi="['travel:zone:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="zoneList"
      row-key="zoneId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="区域名称" prop="zoneName" />
      <el-table-column label="所属上级规划" align="center" prop="preZoneId" />
      <el-table-column label="省市县" align="center" prop="zoneType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.zone_type" :value="scope.row.zoneType"/>
        </template>
      </el-table-column>
      <el-table-column label="创建日期" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="区域代码" align="center" prop="zoneCode" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['travel:zone:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['travel:zone:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['travel:zone:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改全国通用地区码对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区域名称" prop="zoneName">
          <el-input v-model="form.zoneName" placeholder="请输入区域名称" />
        </el-form-item>
        <el-form-item label="所属上级规划" prop="preZoneId">
          <treeselect v-model="form.preZoneId" :options="zoneOptions" :normalizer="normalizer" placeholder="请选择所属上级规划" />
        </el-form-item>
        <el-form-item label="省市县" prop="zoneType">
          <el-select v-model="form.zoneType" placeholder="请选择省市县">
            <el-option
              v-for="dict in dict.type.zone_type"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建日期" prop="createDate">
          <el-date-picker clearable
            v-model="form.createDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="区域代码" prop="zoneCode">
          <el-input v-model="form.zoneCode" placeholder="请输入区域代码" />
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
import { listZone, getZone, delZone, addZone, updateZone } from "@/api/travel/zone";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Zone",
  dicts: ['zone_type'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 全国通用地区码表格数据
      zoneList: [],
      // 全国通用地区码树选项
      zoneOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        zoneName: null,
        preZoneId: null,
        zoneType: null,
        createDate: null,
        zoneCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        zoneName: [
          { required: true, message: "区域名称不能为空", trigger: "blur" }
        ],
        zoneType: [
          { required: true, message: "省市县不能为空", trigger: "change" }
        ],
        zoneCode: [
          { required: true, message: "区域代码不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询全国通用地区码列表 */
    getList() {
      this.loading = true;
      listZone(this.queryParams).then(response => {
        this.zoneList = this.handleTree(response.data, "zoneId", "preZoneId");
        this.loading = false;
      });
    },
    /** 转换全国通用地区码数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.zoneId,
        label: node.zoneName,
        children: node.children
      };
    },
	/** 查询全国通用地区码下拉树结构 */
    getTreeselect() {
      listZone().then(response => {
        this.zoneOptions = [];
        const data = { zoneId: 0, zoneName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "zoneId", "preZoneId");
        this.zoneOptions.push(data);
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
        zoneId: null,
        zoneName: null,
        preZoneId: null,
        zoneType: null,
        createDate: null,
        zoneCode: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.zoneId) {
        this.form.preZoneId = row.zoneId;
      } else {
        this.form.preZoneId = 0;
      }
      this.open = true;
      this.title = "添加全国通用地区码";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.preZoneId = row.zoneId;
      }
      getZone(row.zoneId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改全国通用地区码";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.zoneId != null) {
            updateZone(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addZone(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除全国通用地区码编号为"' + row.zoneId + '"的数据项？').then(function() {
        return delZone(row.zoneId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
