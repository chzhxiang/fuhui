<template>
  <div>
    <van-nav-bar
      title="篮球场预约"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 15px;" />
    <van-cell-group>
      <van-cell value="富荟广场东北角" title="篮球场地址" />
      <van-cell :value="appointmentDate | dataFormat" title="预约日期" is-link @click="selectDate" />
      <van-cell :value="time" title="预约时间段" clickable is-link @click="openActionSheet" />
      <van-cell :value="num" title="剩余场地数" />
    </van-cell-group>
    <div class="btn-div">
      <van-button slot="button" :loading="isload" size="large" type="danger" style="height: 40px; line-height: 38px;" @click="submitData">确认提交</van-button>
    </div>
    <van-actionsheet
      v-model="actionsheet"
      :actions="timeList"
      @select="onSelect"
    />
    <van-datetime-picker
      v-if="flag"
      v-model="appointmentDate"
      :min-date="minDate"
      :max-date="maxDate"
      type="date"
      @cancel="cancel"
      @confirm="confirm"
    />
  </div>
</template>
<script>
import moment from 'moment'
import { Toast } from 'vant'
import { Dialog } from 'vant'

import { findBasketBallUsePeople, saveBasketBallAppointment } from '@/api/appointment'

export default {
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD')
    }
  },
  data() {
    return {
      flag: false,
      minDate: moment(new Date()).add(1, 'd').toDate(),
      maxDate: moment(new Date()).add(1, 'M').toDate(),
      appointmentDate: moment(new Date()).add(1, 'd').toDate(),
      time: '',
      actionsheet: false,
      timeList: [
        {
          name: '06:00 - 08:00'
        },
        {
          name: '08:00 - 10:00'
        },
        {
          name: '10:00 - 12:00'
        },
        {
          name: '12:00 - 14:00'
        },
        {
          name: '14:00 - 16:00'
        },
        {
          name: '16:00 - 18:00'
        }
      ],
      num: null,
      isload: false
    }
  },
  methods: {
    selectDate() {
      this.flag = true
    },
    onClickLeft() {
      this.$router.go(-1)
    },
    onClickRight() {
      this.$router.push({
        path: '/home'
      })
    },
    confirm() {
      this.flag = false
      console.log(moment(this.appointmentDate).format('YYYY-MM-DD'))
      if ((this.appointmentDate !== null && this.appointmentDate !== '') && (this.time !== null && this.time !== '')) {
        findBasketBallUsePeople(moment(this.appointmentDate).format('YYYY-MM-DD'), this.time).then(response => {
          this.num = 4 - response.resultData.size
        })
      }
    },
    cancel() {
      this.flag = false
    },
    openActionSheet() {
      this.actionsheet = true
    },
    onSelect(item) {
      this.actionsheet = false
      this.time = item.name
      if ((this.appointmentDate !== null && this.appointmentDate !== '') && (this.time !== null && this.time !== '')) {
        findBasketBallUsePeople(moment(this.appointmentDate).format('YYYY-MM-DD'), this.time).then(response => {
          this.num = 4 - response.resultData.size
        })
      }
    },
    submitData() {
      this.isload = true
      if (this.appointmentDate === null || this.appointmentDate === '') {
        Toast('请选择预约日期')
        this.isload = false
        return
      }
      if (this.time === null || this.time === '') {
        Toast('请选择预约时间段')
        this.isload = false
        return
      }
      if (this.num < 1) {
        Toast('当前时间段已约满，请更换预约时间段')
        this.isload = false
        return
      }
      console.log(this.$route.query.cardId)
      saveBasketBallAppointment(moment(this.appointmentDate).format('YYYY-MM-DD'), this.time, this.$route.query.cardId).then(response => {
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
.van-picker {
  bottom: 0;
  position: absolute;
  width: 100%;
}
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
