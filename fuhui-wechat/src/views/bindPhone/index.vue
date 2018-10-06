<template>
  <div>
    <van-nav-bar
      title="手机绑定"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 10px;" />
    <van-cell-group>
      <van-field v-model="value" placeholder="请输入您的手机号" />
    </van-cell-group>
    <van-cell-group>
      <van-field
        v-model="sms"
        center
        clearable
        label="短信验证码"
        placeholder="请输入短信验证码"
      >
        <van-button slot="button" :loading="smsLoad" size="small" type="primary" style="background-color: #38f; border: 1px solid #38f;">发送验证码</van-button>
      </van-field>
    </van-cell-group>
    <div class="btn-div">
      <van-button slot="button" :loading="isload" size="large" type="primary" style="background-color: #38f; border: 1px solid #38f; height: 40px; line-height: 38px;" @click="sumbitData">确认提交</van-button>
    </div>
  </div>
</template>
<script>
import { Dialog } from 'vant'

export default {
  data() {
    return {
      value: '',
      sms: '',
      smsLoad: false,
      isload: false
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
    sumbitData() {
      Dialog.alert({
        message: '恭喜您，手机绑定成功！'
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
.btn-div {
  position: absolute;
  bottom: 30px;
  width: 90%;
  text-align: center;
  left: 5%;
}
</style>

