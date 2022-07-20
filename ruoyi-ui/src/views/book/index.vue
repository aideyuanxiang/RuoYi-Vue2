<template>
  <div class="app-container">

    <!-- 搜索框 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
      <el-form-item label="图书编号" prop="bookId">
        <el-input
          v-model="queryParams.bookId"
          placeholder="请输入图书编号"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="图书名称" prop="bookName">
        <el-input
          v-model="queryParams.bookName"
          placeholder="请输入图书名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="所属用户" prop="belongTo">

        <el-select
          v-model="queryParams.belongTo"
          filterable
          remote
          reserve-keyword
          placeholder="所属用户"
          clearable
          style="width: 240px"
          :remote-method="remoteMethod"
          >
          <el-option
            v-for="item in userList"
            :key="item.userId"
            :label="item.nickName"
            :value="item.userId">
          </el-option>
        </el-select>

      </el-form-item>

      <el-form-item label="评价标记" prop="evaluateId">
        <el-select
          v-model="queryParams.evaluateId"
          placeholder="评价标记"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.my_book_evaluate"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 按钮栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-has-permi="['library:book:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="upload.open=true"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
    </el-row>

    <!-- 数据表 -->
    <el-table
      :data="tableData"
      @selection-change="handleSelectionChange"
      style="width: 100%">

      <el-table-column
        type="selection"
        width="55">
      </el-table-column>

      <el-table-column
        prop="bookId"
        label="图书编号"
        width="180">
      </el-table-column>

      <el-table-column
        prop="bookName"
        label="图书名称"
        width="180">
      </el-table-column>

      <el-table-column
        prop="bookPrice"
        label="图书价格">
      </el-table-column>

      <el-table-column label="评价标签" align="center" prop="evaluateId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.my_book_evaluate" :value="scope.row.evaluateId"/>
        </template>
      </el-table-column>

      <el-table-column
        prop="bookUrl"
        label="图书封面">
        <template slot-scope="scope">
          <image-preview :src="scope.row.bookUrl" width="40px" height="40px"/>
        </template>
      </el-table-column>

      <el-table-column
        prop="sysUser.nickName"
        label="登记人">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>姓名: {{ scope.row.sysUser.userName }}</p>
            <p>性别: {{ scope.row.sysUser.sex}}</p>
            <el-avatar shape="square" :size="50" :src="realSrc(scope)"></el-avatar>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.sysUser.nickName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- v-show 可不可以被看见 ，分页-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!--新增修改对话框 -->
    <el-dialog title="图书信息" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" status-icon ref="form" :model="form" >
        <el-form-item label="图书编号" :label-width="formLabelWidth" prop="bookId">
          <el-input v-model="form.bookId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="图书名称" :label-width="formLabelWidth" prop="bookName">
          <el-input v-model="form.bookName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图书价格" :label-width="formLabelWidth" prop="bookPrice">
          <el-input v-model="form.bookPrice" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图书描述" :label-width="formLabelWidth" prop="bookDesc">
          <!-- vmodel 表示双向绑定 -->
          <editor :value="form.bookDesc" v-model="form.bookDesc"/>
<!--          <el-input type="textarea" v-model="form.bookDesc" autocomplete="off"></el-input>-->
        </el-form-item>

        <el-form-item label="图书类型" :label-width="formLabelWidth" prop="typeId">
          <el-select v-model="form.typeId" placeholder="请选择图书类型">
            <el-option
              v-for="item in bookTypes"
              :key="item.typeId"
              :value="item.typeId"
              :label="item.typeName"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="用户评价" :label-width="formLabelWidth" prop="evaluateId">
          <el-select v-model="form.evaluateId" placeholder="请选择评价标签">
            <el-option
              v-for="dict in dict.type.my_book_evaluate"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"/>
          </el-select>
        </el-form-item>

        <el-form-item label="图书封面" :label-width="formLabelWidth">
          <image-upload :value="form.bookUrl" v-model="form.bookUrl"/>
        </el-form-item>

        <el-table
          :data="form.subscribes"
          border
          stripe
          style="width: 100%">
          <el-table-column
            prop="subscriber"
            label="订阅人"
            width="180">
          </el-table-column>
          <el-table-column
            prop="subscribeTime"
            label="订阅时间"
            width="180">
          </el-table-column>
          <el-table-column
            prop="subscribeStar"
            label="期待值">
            <template slot-scope="scope">
              <el-rate
                v-model="scope.row.subscribeStar"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}">
              </el-rate>

            </template>
          </el-table-column>
        </el-table>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>

    <!--上传excel，导入数据 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        :headers="upload.headers"
        accept=".xlsx, .xls"
        :action="upload.url"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 面积图 -->
    <el-button type="primary" @click="initEchartsTest()">生成图表</el-button>
    <div id="main" style="width: 300px; height: 300px" ></div>
  </div>
</template>

<script>
import { listUser, listUserLikeNickName } from "@/api/system/user";
import {getBookList,addBook,getBook,updateBook,deleteById,subCount,listbookTypes} from "@/api/book/index"
import {reset} from "chalk";
import {getToken} from "@/utils/auth";
export default {
  name: "index",
  dicts:['my_book_evaluate'],
  created() {
    //解析Vue对象并挂载
    //页面初始化时会执行
    //该方法是异步方法，发送的请求过程中用户可以操作页面
    //请求相应后准备一个回调方法
    // var vm=this; //获取vue对象
    // getBookList().then(res =>{
    //
    //   vm.tableData=res.data;
    // })
    this.getList()
    listbookTypes().then(res=>{
      this.bookTypes=res.data
    })
  },
  mounted() {  //加载完vue之后再进行页面初始化
  },
  data() {
    var priceValidate =(rule,value,callback)=>{

      var reg1=/^[0-9]*$/;
      var reg2=/^(-?\d+)(\.\d+)?$/;
      if(!value){
        return callback(new Error("请录入书本价格"))
      }

      if(!reg1.test(value)&&!reg2.test(value)){
        return callback(new Error("请输入数值"))
      }else{
        if(value<0){
          return callback(new Error("价格必须大于0"))
        }else{}
        return callback()
      }
    }
    return {
      dialogFormVisible : false,
      testShow:true,
      total:0,
      ids:[],
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "上传图书",
        url: process.env.VUE_APP_BASE_API +'/books/import',
        headers: { Authorization: "Bearer " + getToken() },
        isUploading: false
      },
      formLabelWidth:180,
      form:{
        bookId: '',
        bookName:'',
        bookPrice:'',
        bookDesc:'',
        bookUrl:'',
        subscribes:[],
        evaluateId:''
      },
      rules:{
        bookName:[
          {required:true,message:'请填写图书名称',trigger:'blur'},
          // {type:'enum',enum:['xx','xxx']}
        ],
        bookPrice:[
          {validator:priceValidate, trigger:'blur'}
        ],
        typeId:[
          {required:true,message:'请填写图书名称',trigger:'blur'}
        ]
      },
      queryParams:{
        pageNum:1,
        pageSize:10,
        bookName:'',
        bookId:'',
        belongTo:'',
        evaluateId:''
      },
      userList:[],
      tableData: [],
      bookTypes:[{
        typeId:1,
        typeName:"历史传记"
      },
        {
          typeId:2,
          typeName:"言情小说"
        },
        {
          typeId:3,
          typeName:"科幻小说"
        }
      ]
    }
  },
  methods:{

    resetQuery(){
      this.resetForm("queryForm");
      this.handleQuery();
    },

    remoteMethod(query){
      var vm=this;
      listUserLikeNickName({
        nickName:query
      }).then(res=>{
        vm.userList=res.data
      })
    },

    initEchartsTest(){
      var vm=this;
      subCount().then(res=>{
        vm.initEcharts(res.data.bookNameList,res.data.subscribeCountList)
      })
    },

    initEcharts(bookNameList,subscribeCount){
      var chartDom = document.getElementById('main');
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: bookNameList
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: subscribeCount,
            type: 'line',
            areaStyle: {}
          }
        ]
      };

      option && myChart.setOption(option);
    },
    realSrc(scope){
      return process.env.VUE_APP_BASE_API+scope.row.sysUser.avatar;
    },

    reset(){
      this.form={
        bookId: '',
        bookName:'',
        bookPrice:'',
        bookDesc:'',
        bookUrl:'',
        subscribes:[],
        evaluateId:''
      };
    },

    handleExport(){
      this.download('books/export',{},`book_${new Date().getTime()}.xlsx`)
    },

    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },

    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$modal.msgSuccess(response.msg);
      this.getList();
    },

    cancel(){
       this.reset();
       this.dialogFormVisible=false;
    },

    toggleVShow(){
      this.testShow=!testShow
    },

    getList(){
      getBookList(this.queryParams).then(response=>{

        this.tableData=response.rows;
        this.total=response.total;
      })
    },
    handleQuery(){
      this.queryParams.pageNum=1
      this.getList()
    },
    handleAdd(){
      this.reset();
      this.dialogFormVisible=true;
    },
    submitForm(){

      this.$refs['form'].validate((valid) => {
        if (valid) {
          if(this.form.bookId===undefined||this.form.bookId===null||this.form.bookId===''){
            addBook(this.form).then(response=>{
              this.$modal.msgSuccess("新增成功");
              this.dialogFormVisible=false;
              this.getList();
              this.reset();
            })
          }else {
            updateBook(this.form).then(res=>{
              this.$modal.msgSuccess(res.msg);
              this.dialogFormVisible=false;
              this.getList();
              this.reset();
            })
          }
        } else {
          this.$modal.msgWarning('请按照规范录入数据')
          return false;
        }
      });


    },
    handleEdit(row){

      getBook(row.bookId).then(res=>{
        this.form=res.data;
        this.dialogFormVisible=true;
      })
    },
    handleDelete(row){

      const ids=row.bookId || this.ids

      this.$confirm('此操作将永久删除该图书, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        deleteById(ids).then(res=>{
          this.$modal.msgSuccess(res.msg);
          this.getList();
        })

      });
    },

    handleSelectionChange(selection){
      this.ids = selection.map(item => item.bookId);
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('books/importTemplate', {
      }, `book_template_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>

<style scoped>

</style>
