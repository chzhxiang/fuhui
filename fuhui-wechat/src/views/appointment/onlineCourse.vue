<template>
  <div>
    <van-nav-bar
      title="课程预约"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 15px;" />
    <van-cell-group>
      <van-cell :value="courseName" title="课程名称" clickable is-link @click="selectCourse" />
      <van-cell :value="address" title="上课地址" />
      <van-cell :value="description" title="课程介绍" />
      <van-cell :value="startDate" title="开始时间" />
      <van-cell :value="endDate" title="结束时间" />
      <van-cell :value="num" title="剩余预约数" />
    </van-cell-group>
    <div class="btn-div">
      <van-button slot="button" :loading="isload" size="large" type="danger" style="height: 40px; line-height: 38px;" @click="submitData">确认提交</van-button>
    </div>
    <van-actionsheet
      v-model="actionsheet"
      :actions="coursList"
      @select="onSelect"
    />
  </div>
</template>
<script>
import moment from 'moment'
import { Toast } from 'vant'
import { Dialog } from 'vant'

import { getCourseListByCardType } from '@/api/onlineCourse'
import { findOnlineUsePeople, saveOnlineAppointment } from '@/api/appointment'

export default {
  data() {
    return {
      actionsheet: false,
      coursList: [],
      courseName: '请选择',
      address: '',
      description: '',
      startDate: '',
      endDate: '',
      num: '',
      courseId: '',
      isload: false
    }
  },
  created() {
    this.getCourseList()
  },
  methods: {
    getCourseList() {
      getCourseListByCardType(this.$route.query.cardType).then(response => {
        this.coursList = response.resultData.list
        console.log(this.coursList)
      })
    },
    onSelect(item) {
      this.actionsheet = false
      console.log(item)
      this.courseName = item.name
      this.address = item.address
      this.description = item.description
      this.startDate = moment(item.startDate).format('YYYY-MM-DD HH:mm:ss')
      this.endDate = moment(item.endDate).format('YYYY-MM-DD HH:mm:ss')
      this.courseId = item.id
      findOnlineUsePeople(item.id).then(response => {
        console.log(response)
        this.num = item.num - response.resultData.size
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
    selectCourse() {
      this.actionsheet = true
    },
    submitData() {
      this.isload = true
      if (this.courseName === '请选择') {
        Toast('请选择您要预约的课程')
        this.isload = false
        return
      }
      saveOnlineAppointment(this.courseId, this.$route.query.cardId).then(response => {
        if (response.resultCode === '1') {
          Dialog.alert({
            message: response.resultMsg
          }).then(() => {
            this.isload = false
            this.$router.push({
              path: '/card'
            })
          })
        } else {
          Dialog.alert({
            message: response.resultMsg
          }).then(() => {
            this.isload = false
          })
        }
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
