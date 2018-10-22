<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="手机号">
        <el-input v-model="phone" placeholder="请输入手机号" suffix-icon="el-icon-edit" />
      </el-form-item>
      <el-form-item label="是否有效">
        <el-select v-model="flag" clearable placeholder="请选择">
          <el-option label="请选择" value="" />
          <el-option label="有效" value="1" />
          <el-option label="无效" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否核销">
        <el-select v-model="status" clearable placeholder="请选择">
          <el-option label="请选择" value="" />
          <el-option label="未核销" value="0" />
          <el-option label="已核销" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSelect">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column label="预约名称" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="openId" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.openId }}
        </template>
      </el-table-column>
      <el-table-column label="课程描述" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column label="篮球场预约时间" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.appointmentDate + ' ' + scope.row.appointmentPeroid }}
        </template>
      </el-table-column>
      <el-table-column label="预约创建时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.createDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="课程预约开始时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.courseStartDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="课程预约结束时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.courseEndDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="是否核销" align="center" width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.status === '0'">未核销</span>
          <span v-if="scope.row.status === '1'">已核销</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有效" align="center" width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.flag === '1'">有效</span>
          <span v-else>无效</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.flag === '1'"
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="cancelAppointment(scope.row.id)">取消预约</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :total="totalSize"
        :current-page.sync="page1"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
    </div>
  </div>
</template>
<script>
import { getAppointmentList, cancelAppointment } from '@/api/appointment'
import moment from 'moment'

export default {
  inject: ['reload'],
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      flag: '',
      status: '',
      list: null,
      listLoading: true,
      page1: 1,
      totalSize: null,
      pageSize: null,
      pageNum: null
    }
  },
  created() {
    this.fetchData()
    this.listLoading = false
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getAppointmentList(this.phone, this.flag, this.status, this.pageNum, this.pageSize).then(response => {
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
        console.log(response.resultData.page)
      })
    },
    handleSizeChange(val) {
      this.listLoading = true
      getAppointmentList(this.phone, this.flag, this.status, this.pageNum, val).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      this.listLoading = true
      getAppointmentList(this.phone, this.flag, this.status, val, this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.pageNum = val
        this.listLoading = false
      })
    },
    onSelect() {
      this.listLoading = true
      getAppointmentList(this.phone, this.flag, this.status, this.pageNum, this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    cancelAppointment(id) {
      this.$confirm('此操作将永久取消该预约, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        cancelAppointment(id).then(response => {
          this.$message({
            message: '预约取消成功',
            type: 'success'
          })
          this.reload()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        })
      })
    }
  }
}
</script>
