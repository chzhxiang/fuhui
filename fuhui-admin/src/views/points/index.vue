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
      <el-table-column label="openid">
        <template slot-scope="scope">
          {{ scope.row.openId }}
        </template>
      </el-table-column>
      <el-table-column label="积分" align="center">
        <template slot-scope="scope">
          {{ scope.row.points }}
        </template>
      </el-table-column>
      <el-table-column label="操作类型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type === '0'">服务台充值</span>
          <span v-else-if="scope.row.type === '1'">用户扫描充值</span>
          <span v-else>支付</span>
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
import { getPointsList } from '@/api/points'
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
      page1: 1,
      phone: '',
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
      getPointsList(this.phone, this.pageNum, this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleSizeChange(val) {
      this.listLoading = true
      getPointsList(this.phone, this.pageNum, val).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      this.listLoading = true
      getPointsList(this.phone, val, this.pageSize).then(response => {
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
      console.log(this.phone)
      getPointsList(this.phone, this.pageNum, this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    }
  }
}
</script>
