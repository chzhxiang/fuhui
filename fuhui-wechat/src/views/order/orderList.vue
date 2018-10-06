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
    <div style="height: 15px;" />
    <div v-for="order in orderList" :key="order.id">
      <van-panel :title="order.titile" :desc="order.payType === '0' ? '微信支付' : '积分支付'" status="已完成">
        <div class="panel-div">
          下单时间：{{ order.createDate | dataFormat }}
        </div>
        <div v-if="order.payType === '0'" class="panel-money">
          实付款：<span class="panel-money-span">￥{{ order.order_money }}</span>
        </div>
        <div v-else class="panel-money">
          实付积分：<span class="panel-money-span">{{ order.order_money }}</span>
        </div>
      </van-panel>
      <div style="height: 15px;" />
    </div>
  </div>
</template>
<script>
import moment from 'moment'

export default {
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      orderList: [{
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        titile: '停车场抵扣券',
        createDate: 1538707519000,
        payType: '0',
        order_money: '10',
        desc: ''
      }, {
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        titile: '线上课程抵扣券',
        createDate: 1538707519000,
        payType: '1',
        order_money: '100',
        desc: ''
      }, {
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        titile: '篮球场抵扣券',
        createDate: 1538707519000,
        payType: '1',
        order_money: '20',
        desc: ''
      }]
    }
  },
  methods: {
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
</style>
