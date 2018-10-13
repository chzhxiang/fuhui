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
          {{ scope.row.openid }}
        </template>
      </el-table-column>
      <el-table-column label="车牌号" align="center">
        <template slot-scope="scope">
          {{ scope.row.carNum }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="delCarNum(scope.row.id)">解绑车牌</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getCarNumListByPhone, delCarNum } from '@/api/carNum'
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
    this.listLoading = false
  },
  methods: {
    delCarNum(id) {
      this.$confirm('解绑车牌号将不可逆，请确认操作', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delCarNum(id).then(response => {
          if (response.resultCode === '1') {
            this.$message({
              type: 'success',
              message: '解绑成功'
            })
            this.onSelect()
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
      getCarNumListByPhone(this.phone).then(response => {
        console.log(response.resultData.list)
        this.list = response.resultData.list
        this.listLoading = false
      })
    }
  }
}
</script>
