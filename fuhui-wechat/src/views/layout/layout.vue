<template>
  <div>
    <app-main/>
    <van-tabbar v-model="active">
      <van-tabbar-item icon="shop-collect" to="shop">商城</van-tabbar-item>
      <van-tabbar-item icon="setting" to="introduction">走进富荟</van-tabbar-item>
      <van-tabbar-item icon="credit-pay" to="card">卡包</van-tabbar-item>
      <van-tabbar-item icon="contact" to="home">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
import { AppMain } from './components'

export default {
  name: 'Layout',
  components: {
    AppMain
  },
  data() {
    return {
      active: 3
    }
  }
}
</script>