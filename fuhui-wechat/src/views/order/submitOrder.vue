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
      <van-cell :value="payType" title="支付方式" />
    </van-cell-group>
    <van-submit-bar
      :price="price"
      :loading="isload"
      button-text="提交订单"
      @submit="submitOrder()"
    />
  </div>
</template>
<script>
import { Dialog } from 'vant'
import { getProductById } from '@/api/product'
import { submitOrder } from '@/api/order'
import { Toast } from 'vant'

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
      isload: false,
      id: null,
      payType: null,
      name: null,
      price: null,
      desc: null
    }
  },
  created() {
    this.getProductById()
  },
  methods: {
    getProductById() {
      getProductById(this.$route.query.id).then(response => {
        const info = response.resultData.info
        console.log(info)
        if (info !== null) {
          // 初始化数据
          this.id = info.id
          this.name = info.name
          this.price = info.price
          this.desc = info.productDesc
          if (info.type === '0') {
            this.payType = '积分支付'
          } else {
            this.payType = '微信支付'
          }
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
    },
    submitOrder() {
      this.isload = true
      submitOrder(this.id).then(response => {
        if (response.resultCode === '1') {
          Dialog.alert({
            message: '支付成功'
          }).then(() => {
            this.isload = false
            this.$router.push({
              path: '/home'
            })
          })
        } else {
          this.isload = false
          Toast(response.resultMsg)
        }
      })
    }
  }
}
</script>
<style>

</style>
