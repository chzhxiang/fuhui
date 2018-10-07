<template>
  <div>
    <div class="my_header_v4_wrap">
      <div class="my_header_v4">
        <div class="my_header_v4_main">
          <div class="my_header_v4_avatar">
            <div class="my_header_v4_avatar_img">
              <img id="headPic" :src="wechatHeadImg">
            </div>
          </div>
          <div class="my_header_v4_msg">
            <div class="my_header_v4_name">
              <span v-if="isPhone" class="my_header_v4_name_text">手机号：{{ phone }}</span>
              <span v-else class="my_header_v4_name_text">暂未绑定手机号</span>
            </div>
            <div class="my_header_v4_pin">账户积分：{{ points }}</div>
          </div>
        </div>
      </div>
    </div>
    <van-cell-group v-if="isPhone">
      <van-cell :value="phone" title="手机号" icon="phone"/>
    </van-cell-group>
    <van-cell-group v-else>
      <van-cell value="点击绑定" icon="phone" is-link url="#/bindPhone">
        <template slot="title">
          <span class="van-cell-text">手机号</span>
        </template>
      </van-cell>
    </van-cell-group>
    <van-cell-group v-if="isCarNum">
      <van-cell :value="carNum" title="车牌号" icon="logistics" />
    </van-cell-group>
    <van-cell-group v-else>
      <van-cell value="点击绑定" icon="logistics" is-link @click="checkPhone">
        <template slot="title">
          <span class="van-cell-text">车牌号</span>
        </template>
      </van-cell>
    </van-cell-group>
    <div style="height: 15px;" />
    <van-cell-group>
      <van-cell title="我的预约" icon="clock" is-link url="#/appointment" />
      <van-cell title="我的订单" icon="pending-orders" is-link url="#/orderList" />
    </van-cell-group>
    <van-tabbar v-model="active">
      <van-tabbar-item icon="shop-collect" to="shop">商城</van-tabbar-item>
      <van-tabbar-item icon="setting" to="introduction">走进富荟</van-tabbar-item>
      <van-tabbar-item icon="credit-pay" to="card">卡券</van-tabbar-item>
      <van-tabbar-item icon="contact" to="home">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
import { Dialog } from 'vant'
import { getUserInfo } from '@/api/wechatUser'
import { getToken } from '@/utils/auth'
import { Toast } from 'vant'

export default {
  data() {
    return {
      active: 3,
      points: '0',
      isPhone: false,
      phone: '',
      isCarNum: false,
      carNum: '沪A888888',
      wechatHeadImg: ''
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      getUserInfo(getToken()).then(response => {
        console.log(response.resultData.info)
        const info = response.resultData.info
        if (response.resultCode === '1') {
          // 初始化数据

          if (info.points !== null) {
            this.points = info.points
          }
          if (info.phone !== null) {
            this.isPhone = true
            this.phone = info.phone
          }
          if (info.carNumber !== null) {
            this.isCarNum = true
            this.carNum = info.carNumber
          }
          if (info.wechatHeadImg !== null) {
            this.wechatHeadImg = info.wechatHeadImg
          }
        } else {
          Toast('服务器异常，请稍后重试~')
        }
      })
    },
    checkPhone() {
      console.log(11111)
      if (this.isPhone === false) {
        Toast('请先绑定手机号！')
      } else {
        this.$router.push({
          path: '/bindCarNum'
        })
      }
    }
  }
}
</script>
<style>
.my_header_v4_wrap {
  margin-bottom: 15px;
  padding: 10px;
  background-color: #fff;
}
.my_header_v4_wrap .my_header_v4 {
    overflow: hidden;
    position: relative;
    border-radius: 6px;
    font-size: 12px;
    color: #fff;
    background: -webkit-linear-gradient(left,#eb3c3c,#ff7459);
    background: linear-gradient(90deg,#eb3c3c,#ff7459);
    box-shadow: 0 2px 5px rgba(255,98,98,.4);
}
.my_header_v4_wrap .my_header_v4_main {
    position: relative;
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-align: center;
    -webkit-align-items: center;
    align-items: center;
    padding: 25px 15px;
}
.my_header_v4_wrap .my_header_v4_avatar {
    position: relative;
    margin-right: 10px;
    width: 60px;
    height: 60px;
    border: 1px solid hsla(0,0%,100%,.4);
    border-radius: 60px;
    box-shadow: 0 2px 10px rgba(0,0,0,.15);
}
.my_header_v4_wrap .my_header_v4_avatar_img {
    overflow: hidden;
    border-radius: 60px;
    padding-top: 100%;
}
.my_header_v4_wrap .my_header_v4_avatar img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border-radius: 60px;
}
.my_header_v4_wrap .my_header_v4_msg {
    -webkit-box-flex: 1;
    -webkit-flex: 1;
    flex: 1;
    min-width: 0;
}
.my_header_v4_wrap .my_header_v4_pin {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: hsla(0,0%,100%,.7);
    margin-top: 5px;
}
.my_header_v4_wrap .my_header_v4_name {
    position: relative;
    font-size: 14px;
    margin-right: 20px;
    white-space: nowrap;
}
</style>
