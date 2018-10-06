<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="手机号">
        <el-input v-model="phone" placeholder="请输入手机号" suffix-icon="el-icon-edit" />
      </el-form-item>
      <el-form-item label="订单状态">
        <el-select v-model="orderStatus" clearable placeholder="请选择">
            <el-option
            v-for="item in orderStatusList"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付类型">
        <el-select v-model="payType" clearable placeholder="请选择">
            <el-option
            v-for="item in payTypeList"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="系统订单号">
        <el-input v-model="orderNo" placeholder="请输入系统订单号" suffix-icon="el-icon-edit" />
      </el-form-item>
      <el-form-item label="微信支付订单号">
        <el-input v-model="transactionId" placeholder="请输入支付订单号" suffix-icon="el-icon-edit" />
      </el-form-item>
      <el-form-item label="微信订单号">
        <el-input v-model="txOrderNo" placeholder="请输入微信订单号" suffix-icon="el-icon-edit" />
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
      <el-table-column label="创建时间" align="center" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          {{ scope.row.createDate | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="openid" align="center" width="150">
        <template slot-scope="scope">
          {{ scope.row.openId }}
        </template>
      </el-table-column>
      <el-table-column label="订单金额" align="center" width="150">
        <template slot-scope="scope">
          {{ scope.row.orderMoney }}
        </template>
      </el-table-column>
      <el-table-column label="系统订单号" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.orderNo }}
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" width="150">
        <template slot-scope="scope">
          <span v-if="scope.row.orderStatus === '0'">创建</span>
          <span v-else>已完成</span>
        </template>
      </el-table-column>
      <el-table-column label="下单时间" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.orderTime | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="订单支付时间" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.payTime | dataFormat }}
        </template>
      </el-table-column>
      <el-table-column label="订单支付类型" align="center" width="150">
        <template slot-scope="scope">
          <span v-if="scope.row.payType === '0'">微信支付</span>
          <span v-else>积分支付</span>
        </template>
      </el-table-column>
      <el-table-column label="产品编号" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.productNo }}
        </template>
      </el-table-column>
      <el-table-column label="微信支付订单号" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.transactionId }}
        </template>
      </el-table-column>
      <el-table-column label="微信订单号" align="center" width="300">
        <template slot-scope="scope">
          {{ scope.row.txOrderNo }}
        </template>
      </el-table-column>
      <el-table-column label="订单更新时间" align="center" width="200">
        <template slot-scope="scope">
          {{ scope.row.updateDate | dataFormat }}
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
import { getWechatOrderList } from '@/api/order'
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
      pageNum: null,
      orderNo: null,
      transactionId: null,
      txOrderNo: null,
      orderStatus: null,
      orderStatusList: [{
          value: null,
          label: '请选择'
      }, {
          value: '0',
          label: '创建'
      }, {
          value: '1',
          label: '已完成'
      }],
      payType: null,
      payTypeList: [{
          value: null,
          label: '请选择'
      }, {
          value: '0',
          label: '微信支付'
      }, {
          value: '1',
          label: '积分支付'
      }]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getWechatOrderList(this.phone, this.payType, this.orderStatus, this.orderNo, this.transactionId, this.txOrderNo, this.pageNum, this.pageSize).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleSizeChange(val) {
      this.listLoading = true
      getWechatOrderList(this.phone, this.payType, this.orderStatus, this.orderNo, this.transactionId, this.txOrderNo, this.pageNum, val).then(response => {
        console.log(response.resultData.page)
        this.totalSize = response.resultData.page.totalElements
        this.pageSize = response.resultData.page.size
        this.list = response.resultData.page.content
        this.listLoading = false
      })
    },
    handleCurrentChange(val) {
      this.listLoading = true
      getWechatOrderList(this.phone, this.payType, this.orderStatus, this.orderNo, this.transactionId, this.txOrderNo, val, this.pageSize).then(response => {
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
      getWechatOrderList(this.phone, this.payType, this.orderStatus, this.orderNo, this.transactionId, this.txOrderNo, this.pageNum, this.pageSize).then(response => {
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
