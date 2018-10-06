<template>
  <div>
    <van-nav-bar
      title="二维码"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 15px;" />
    <div id="qrcode" class="qrcode" />
  </div>
</template>
<script>
import QRCode from 'qrcodejs2'

export default {
  mounted() {
    this.qrcode()
  },
  methods: {
    qrcode() {
      const qrcode = new QRCode('qrcode', {
        width: 250,
        height: 250, // 高度
        text: this.$route.query.id, // 二维码内容
        render: 'canvas', // 设置渲染方式（有两种方式 table和canvas，默认是canvas）
        background: '#f0f',
        foreground: '#ff0'
      })
      console.log(qrcode)
      this.show = true
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
.qrcode {
  width: 250px;
  position: fixed;
  top: 50%;
  left: 50%;
  max-height: 100%;
  transform: translate3d(-50%, -50%, 0);
  -webkit-transform: translate3d(-50%, -50%, 0);
  background-color: #ffffff
}
</style>
