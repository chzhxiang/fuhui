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
        :desc="product.desc"
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
export default {
  data() {
    return {
      active: 0,
      headImg: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538735358592&di=b53c1927fb601607621b4fc0886ae63b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014bac554219b90000019ae9d8b5c5.jpg',
      productList: [{
        id: '90e6a8c5-c87b-11e8-bb15-00163e0a9c22',
        tag: '积分',
        name: '停车场抵用券',
        price: '10',
        desc: '线上支付停车费用时自动抵扣',
        imageURL: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538735829102&di=7ebf19fa4c55f5474e90ce444b8b0e73&imgtype=0&src=http%3A%2F%2Fimg.go007.com%2F2016%2F09%2F24%2F69f9d175ea53b0d4_0.jpg'
      }, {
        id: '99276ac7-c87b-11e8-bb15-00163e0a9c22',
        tag: '积分',
        name: '篮球场抵用券',
        price: '20',
        desc: '线上预约蓝球场自动抵扣',
        imageURL: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538735997172&di=159497d476946c3e1bd9c5dc9534b3f6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D1cff4e9bc3177f3e0439f44e18a651b2%2F6609c93d70cf3bc7520a11c1db00baa1cd112a91.jpg'
      }, {
        id: '9cb85ea7-c87b-11e8-bb15-00163e0a9c22',
        tag: '积分',
        name: '线上课程抵用券',
        price: '20',
        desc: '线上预约课程时自动抵扣',
        imageURL: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538736061386&di=63a0f1e89ade896362925eb15ea84078&imgtype=0&src=http%3A%2F%2Fwww.xianzhi.net%2Fuploads%2F140117%2F1-14011G502304M.jpg'
      }]
    }
  },
  methods: {
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
