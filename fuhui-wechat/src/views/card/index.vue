<template>
  <div>
    <van-panel title="停车场抵用券" desc="该券可在停车场结费时使用" status="未使用">
      <div slot="footer">
        <van-button size="small" type="danger">去使用</van-button>
      </div>
    </van-panel>
    <div style="height: 10px;"></div>
    <van-panel title="在线课程抵用券" desc="该券可在预约在线课程时使用" status="未使用">
      <div slot="footer">
        <van-button size="small" type="danger">去使用</van-button>
      </div>
    </van-panel>
    <div style="height: 10px;"></div>
    <van-panel title="篮球场抵用券" desc="该券可在预约篮球场时使用" status="未使用">
      <div slot="footer">
        <van-button size="small" type="danger">去使用</van-button>
      </div>
    </van-panel>
    <div style="height: 50px;"></div>
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
      active: 2
    }
  }
}
</script>
<style>
.van-panel__footer {
  text-align: right;
}
</style>
