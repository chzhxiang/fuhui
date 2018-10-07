<template>
  <div>
    <van-nav-bar
      title="标题"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 15px;" />
    <van-cell-group>
      <van-field v-model="inputValue" placeholder="请输入车牌号，例如沪A88888" readonly="readonly" @click="chepai" />
    </van-cell-group>
    <div class="btn-div">
      <van-button slot="button" :loading="isload" size="large" type="primary" style="background-color: #38f; border: 1px solid #38f; height: 40px; line-height: 38px;" @click="bindCarNum">确认提交</van-button>
    </div>
    <VueCustomKeyboard
      :isOpen="isOpen"
      :onChange="onChange"
      :onBlur="onBlur"
      :onDone="onDone"
    />
  </div>
</template>
<script>
import VueCustomKeyboard from 'vue-custom-keyboard'
import 'vue-custom-keyboard/dist/vue-custom-keyboard.min.css'
import { Dialog } from 'vant'
import { Toast } from 'vant'
import { bindCarNum } from '@/api/wechatUser'

export default {
  components: {
    VueCustomKeyboard
  },
  data() {
    return {
      inputValue: '',
      isOpen: false,
      isload: false
    }
  },
  methods: {
    chepai() {
      this.isOpen = true
    },
    bindCarNum() {
      this.isload = true
      Dialog.confirm({
        title: '提示',
        message: '您当前输入的车牌是：' + this.inputValue
      }).then(() => {
        bindCarNum(this.inputValue).then(response => {
          this.isload = false
          if (response.resultCode) {
            Toast(response.resultMsg)
            this.$router.push({
              path: '/home'
            })
          } else {
            Toast(response.resultMsg)
          }
        })
      }).catch(() => {
        this.isload = false
        Toast('取消操作')
      })
    },
    onChange(value) {
      this.inputValue = value
    },
    onBlur() {
      this.isOpen = false
    },
    onDone() {
      this.isOpen = false
    },
    showKeyboard() {
      this.isOpen = true
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
.btn-div {
  position: absolute;
  bottom: 30px;
  width: 90%;
  text-align: center;
  left: 5%;
}
.button {
  font-size: 1rem;
}
.button i {
  font-size: 2rem!important;
}
</style>
