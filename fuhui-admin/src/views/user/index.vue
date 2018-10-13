<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="手机号">
        <el-input v-model="phone" placeholder="请输入手机号" suffix-icon="el-icon-edit" />
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
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.createDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="openid" align="center">
        <template slot-scope="scope">
          {{ scope.row.openId }}
        </template>
      </el-table-column>
      <el-table-column label="积分" align="center">
        <template slot-scope="scope">
          {{ scope.row.points }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="addPoints(scope.row.id)">加积分</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="delPhone(scope.row.id)">解绑手机</el-button>
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
import { delPhone, addPoints, getWechatList } from '@/api/user'
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
      pageNum: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getWechatList(this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    addPoints(id) {
      this.$prompt('请输入积分', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
        center: true,
        inputErrorMessage: '积分格式不正确'
      }).then(({ value }) => {
        addPoints(id, value).then(response => {
          if (response.resultCode === '1') {
            this.$message({
              type: 'success',
              message: '增加积分成功'
            })
            this.reload()
          } else {
            this.$message({
              type: 'warning',
              message: '增加积分失败'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消操作'
        })
      })
    },
    delPhone(id) {
      this.$confirm('解绑手机号将不可逆，请确认操作', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delPhone(id).then(response => {
          if (response.resultCode === '1') {
            this.$message({
              type: 'success',
              message: '解绑成功'
            })
            this.reload()
          } else {
            this.$message({
              type: 'warning',
              message: '解绑失败'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消解绑'
        })
      })
    },
    onSelect() {
      this.listLoading = true
      getWechatList(this.phone, this.pageNum, this.pageSize).then(response => {
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleSizeChange(val) {
      getWechatList(this.phone, this.pageNum, val).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      getWechatList(this.phone, val, this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.pageNum = val
        this.listLoading = false
      })
    }
  }
}
</script>
