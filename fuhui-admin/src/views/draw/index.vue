<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column label="名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="概率" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.scale }}%</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="updateScale(scope.row.id, scope.row.name, scope.row.scale)">修改概率</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getWechatOrderList, saveAdminDraw } from '@/api/draw'
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
      getWechatOrderList().then(response => {
        this.list = response.resultData.list
        this.listLoading = false
      })
    },
    updateScale(id, name, scale) {
      this.$prompt('当前【' + name + '】概率为：' + scale + '%，请输入您想修改的概率', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
        center: true,
        inputErrorMessage: '概率格式不正确'
      }).then(({ value }) => {
        saveAdminDraw(id, value).then(response => {
          if (response.resultCode === '1') {
            this.$message({
              type: 'success',
              message: '概率修改成功'
            })
            this.reload()
          } else {
            this.$message({
              type: 'warning',
              message: '概率修改失败'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消操作'
        })
      })
    }
  }
}
</script>
