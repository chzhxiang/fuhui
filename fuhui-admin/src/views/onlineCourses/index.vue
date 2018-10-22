<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="getCourseList">新增课程</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column label="课程名称" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.createDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="上课地址" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.address }}
        </template>
      </el-table-column>
      <el-table-column label="课程描述" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column label="课程开始时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.startDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="课程结束时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.endDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="是否有效" align="center" width="300">
        <template slot-scope="scope">
          <span v-if="scope.row.flag === '1'">有效</span>
          <span v-else>无效</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            icon="el-icon-edit"
            @click="getOnlineCourseById(scope.row.id)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="delOnlineCoursesById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="dialogFormVisible" title="在线课程">
      <el-form :model="course">
        <el-form-item :label-width="formLabelWidth" label="课程名称">
          <el-input v-model="course.name" autocomplete="off" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="课程描述">
          <el-input v-model="course.description" autocomplete="off" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="上课地址">
          <el-input v-model="course.address" autocomplete="off" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="限制人数">
          <el-input v-model="course.num" autocomplete="off" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="开始时间">
          <el-date-picker
            v-model="course.startDate"
            type="datetime"
            placeholder="选择开始日期时间" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="结束时间">
          <el-date-picker
            v-model="course.endDate"
            type="datetime"
            placeholder="选择结束日期时间" />
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="对应产品">
          <el-select v-model="course.productId" placeholder="请选择对应产品">
            <el-option
              v-for="item in products"
              :key="item.id"
              :label="item.name"
              :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="限制人数">
          <el-switch
            v-model="course.flag"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-value="1"
            inactive-value="0" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOnlineCourse">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getCourseList } from '@/api/product'
import { saveOnlineCourse, getOnlineCoursesList, delOnlineCoursesById, getOnlineCourseById } from '@/api/onlineCourse'
import moment from 'moment'

export default {
  inject: ['reload'],
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      phone: '',
      page1: 1,
      totalSize: null,
      pageSize: null,
      pageNum: null,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      course: {
        id: '',
        address: '',
        description: '',
        name: '',
        productId: [],
        startDate: '',
        endDate: '',
        num: '',
        flag: ''
      },
      products: []
    }
  },
  created() {
    this.fetchData()
    this.listLoading = false
  },
  methods: {
    fetchData() {
      getOnlineCoursesList().then(response => {
        this.list = response.resultData.list
        console.log(response.resultData.list)
      })
    },
    getCourseList() {
      this.dialogFormVisible = true
      getCourseList().then(response => {
        console.log(response.resultData.list)
        this.products = response.resultData.list
      })
    },
    saveOnlineCourse() {
      saveOnlineCourse(this.course.id, this.course.address, this.course.description, this.course.name, this.course.productId, this.course.num, moment(this.course.startDate).format('YYYY-MM-DD HH:mm:ss'), moment(this.course.endDate).format('YYYY-MM-DD HH:mm:ss'), this.course.flag).then(response => {
        if (response.resultCode === '1') {
          this.dialogFormVisible = false
          this.$message({
            message: '恭喜你，课程新增成功',
            type: 'success'
          })
        }
        this.reload()
      })
    },
    getOnlineCourseById(id) {
      this.getCourseList()
      getOnlineCourseById(id).then(response => {
        const info = response.resultData.info
        console.log(response.resultData.info)
        this.dialogFormVisible = true
        this.course.id = info.id
        this.course.address = info.address
        this.course.description = info.description
        this.course.name = info.name
        this.course.productId = info.productId
        this.course.num = info.num
        this.course.startDate = info.startDate
        this.course.endDate = info.endDate
        this.course.flag = info.flag
      })
    },
    delOnlineCoursesById(id) {
      this.$confirm('删除课程将不可逆，请确认操作', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delOnlineCoursesById(id).then(response => {
          if (response.resultCode === '1') {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            this.reload()
          } else {
            this.$message({
              type: 'warning',
              message: '删除失败'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>
