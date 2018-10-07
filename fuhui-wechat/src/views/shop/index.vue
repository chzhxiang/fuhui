<template>
  <div>
    <div class="head-div">
      <img :src="headImg" class="head-img" alt="">
    </div>
    <div style="height: 15px;" />
    <div v-for="product in productList" :key="product.id">
      <van-card
        :tag="product.tag"
        :price="product.price"
        :desc="product.productDesc"
        :title="product.name"
        :thumb="product.imageURL"
        num="1"
      >
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
  data() {
    return {
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
          for (const index in list) {
            if (list[index].type === '0') {
              list[index].tag = '积分'
              list[index].price = list[index].price / 100
            }
          }
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
