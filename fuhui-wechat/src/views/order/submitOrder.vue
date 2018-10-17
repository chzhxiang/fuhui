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
      <van-cell :value="useNum + '次'" title="产品规格" clickable is-link @click="openActionSheet" />
      <van-cell v-if="payType === '1'" :value="cashPrice / 100 | format" title="商品价格" />
      <van-cell v-if="payType === '0'" :value="pointsPrice" title="商品积分" />
      <van-cell title="支付方式" />
      <van-radio-group v-model="payType">
        <van-cell icon="credit-pay" title="积分支付" clickable @click="check0">
          <van-radio name="0" />
        </van-cell>
        <van-cell icon="wechat" title="微信支付" clickable @click="check1">
          <van-radio name="1" />
        </van-cell>
      </van-radio-group>
    </van-cell-group>
    <van-actionsheet
      v-model="actionsheet"
      :actions="productNumList"
      @select="onSelect"
    />
    <van-submit-bar
      :price="price"
      :loading="isload"
      :currency="currency"
      button-text="立即购买"
      @submit="submitOrder()"
    >
      <span v-if="payType === '0'" slot="tip">
        您当前选择的支付方式为积分支付
      </span>
      <span v-if="payType === '1'" slot="tip">
        您当前选择的支付方式为微信支付
      </span>
    </van-submit-bar>
  </div>
</template>
<script>
import { Dialog } from 'vant'
import { getProductById, getProductConfigByProductId } from '@/api/product'
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
      flag: false,
      isload: false,
      id: null,
      payType: '0',
      useNum: 1,
      name: null,
      cashPrice: null,
      pointsPrice: null,
      price: null,
      desc: null,
      actionsheet: false,
      radio: 0,
      currency: '',
      productNumList: [
        {
          name: '1'
        }
      ]
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
          this.cashPrice = info.cashPrice
          this.desc = info.productDesc
          this.pointsPrice = info.pointsPrice
          this.price = info.pointsPrice * 100
        }
      })
    },
    check0() { // 选择使用积分支付
      this.payType = '0'
      this.price = this.pointsPrice * 100 * this.useNum
      this.currency = ''
      console.log(this.payType)
    },
    check1() { // 选择使用现金支付
      this.payType = '1'
      this.price = this.cashPrice * this.useNum
      this.currency = '￥'
      console.log(this.payType)
    },
    onSelect(item) {
      this.actionsheet = false
      this.useNum = item.name
      if (this.payType === '0') {
        this.price = this.pointsPrice * item.name * 100
      } else if (this.payType === '1') {
        this.price = this.cashPrice * item.name
      } else { // 此情况应永不会出现
        this.price = this.price * item.name
      }
    },
    openActionSheet() {
      this.actionsheet = true
      getProductConfigByProductId(this.id).then(response => {
        const list = response.resultData.list
        for (const index in list) {
          list[index].name = list[index].useNum
        }
        this.productNumList = list
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
      submitOrder(this.id, this.useNum, this.payType).then(response => {
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
