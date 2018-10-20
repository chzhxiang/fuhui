<template>
  <div>
    <van-nav-bar
      title="我的订单"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div v-if="flag">
      <div style="height: 15px;" />
      <div v-for="order in orderList" :key="order.id">
        <van-panel :title="order.productName" :desc="order.payType === '0' ? '微信支付' : '积分支付'" status="已完成">
          <div class="panel-div">
            下单时间：{{ order.payTime | dataFormat }}
          </div>
          <div v-if="order.payType === '0'" class="panel-money">
            实付款：<span class="panel-money-span">￥{{ order.orderMoney / 100 | numberFormat }}</span>
          </div>
          <div v-else class="panel-money">
            实付积分：<span class="panel-money-span">{{ order.orderMoney | numberFormat }}</span>
          </div>
        </van-panel>
        <div style="height: 15px;" />
      </div>
    </div>
    <div v-else class="no-data-div">
      暂无数据
    </div>
  </div>
</template>
<script>
import moment from 'moment'
import { getOrderList } from '@/api/order'

export default {
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    },
    numberFormat: function(money) {
      if (money && money != null) {
        money = String(money)
        const left = money.split('.')[0]
        let right = money.split('.')[1]
        right = right ? (right.length >= 2 ? '.' + right.substr(0, 2) : '.' + right + '0') : '.00'
        const temp = left.split('').reverse().join('').match(/(\d{1,3})/g)
        return (Number(money) < 0 ? '-' : '') + temp.join(',').split('').reverse().join('') + right
      } else if (money === 0) { // 注意===在这里的使用，如果传入的money为0,if中会将其判定为boolean类型，故而要另外做===判断
        return '0.00'
      } else {
        return ''
      }
    }
  },
  data() {
    return {
      flag: false,
      orderList: [{
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        name: '停车场抵扣券',
        createDate: 1538707519000,
        payType: '0',
        order_money: '10',
        desc: ''
      }, {
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        name: '线上课程抵扣券',
        createDate: 1538707519000,
        payType: '1',
        order_money: '100',
        desc: ''
      }, {
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        name: '篮球场抵扣券',
        createDate: 1538707519000,
        payType: '1',
        order_money: '20',
        desc: ''
      }]
    }
  },
  created() {
    this.getOrderList()
  },
  methods: {
    getOrderList() {
      getOrderList().then(response => {
        const list = response.resultData.list
        console.log(list)
        if (list !== null) {
          this.flag = true
          this.orderList = list
        }
      })
    },
    onClickLeft() {
      this.$router.go(-1)
    },
    onClickRight() {
      this.$router.push({
        path: '/home'
      })
    }
  }
}
</script>
<style>
.van-panel__content .panel-div {
  padding: 5px 15px;
  font-size: 12px;
  line-height: 1.2;
  color: #666;
}
.panel-money {
  padding: 5px 15px;
  font-size: 12px;
  line-height: 1.2;
  text-align: right
}
.panel-money-span {
  font-size: 14px;
}
.no-data-div {
  padding: 20px;
  text-align: center;
  font-size: 14px;
  color: #999;
}
</style>
