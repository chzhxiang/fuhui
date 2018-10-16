<template>
  <div>
    <div class="head-div">
      <img :src="headImg" class="head-img" alt="">
    </div>
    <div style="height: 15px;" />
    <div v-for="product in productList" :key="product.id">
      <van-card
        :price="product.cashPrice / 100 | numberFormat"
        :title="product.name"
        :thumb="product.imageURL"
        tag="积分"
        currency="￥"
      >
        <div slot="desc">
          <div class="van-card__row">
            <div class="van-card__title">积分价格</div>
            <div class="van-card__price">{{ product.pointsPrice }} 积分</div>
          </div>
        </div>
        <div slot="footer">
          <van-button type="danger" size="small" @click="goOrder(product.id)">立即兑换</van-button>
        </div>
      </van-card>
      <div style="height: 15px;" />
    </div>
    <div style="height: 50px;" />
    <van-tabbar v-model="active">
      <van-tabbar-item icon="shop-collect" to="shop">商城</van-tabbar-item>
      <van-tabbar-item icon="setting" to="introduction">走进富荟</van-tabbar-item>
      <van-tabbar-item icon="credit-pay" to="card">卡券</van-tabbar-item>
      <van-tabbar-item icon="contact" to="home">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
import { getProductList } from '@/api/product'

export default {
  filters: {
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
      isPoints: false,
      active: 0,
      headImg: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538735358592&di=b53c1927fb601607621b4fc0886ae63b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014bac554219b90000019ae9d8b5c5.jpg',
      productList: null
    }
  },
  created() {
    this.getProductList()
  },
  methods: {
    getProductList() {
      getProductList().then(response => {
        if (response.resultData !== null) {
          const list = response.resultData.list
          console.log(list)
          for (const index in list) {
            if (list[index].pointsPrice === null) {
              console.log(list[index].id)
              list[index].isPoints = false
            } else {
              list[index].isPoints = true
            }
          }
          console.log(list)
          this.productList = list
        }
      })
    },
    goOrder(id) {
      this.$router.push({
        path: '/submitOrder',
        query: { id: id }
      })
    }
  }
}
</script>
<style>
.head-div {
  width: 100%;
}
.head-img {
  width: 100%;
}
</style>
