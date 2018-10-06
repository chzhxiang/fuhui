<template>
  <div>
    <van-nav-bar
      title="提交订单"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 15px;" />
    <van-cell-group>
      <van-cell :value="name" :label="desc" title="商品名称" />
      <van-cell value="x1" title="商品数量" />
      <van-cell :value="price / 100 | format" title="商品价格" />
      <van-cell value="积分支付" title="支付方式" />
    </van-cell-group>
    <van-submit-bar
      :price="price"
      button-text="提交订单"
      @submit="submitOrder()"
    />
  </div>
</template>
<script>
import { Dialog } from 'vant'

export default {
  filters: {
    format: function(money) {
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
      id: this.$route.query.id,
      tag: '积分',
      name: '停车场抵用券',
      price: 1000,
      desc: '线上支付停车费用时自动抵扣'
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
    },
    submitOrder() {
      console.log(this.id)
      // 后台进行下单以及支付等操作，操作成功后
      Dialog.alert({
        message: '支付成功'
      }).then(() => {
        this.$router.push({
          path: '/home'
        })
      })
    }
  }
}
</script>
<style>

</style>
