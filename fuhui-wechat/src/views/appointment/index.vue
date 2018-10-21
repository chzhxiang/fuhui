<template>
  <div>
    <van-nav-bar
      title="我的预约"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 15px;" />
    <div v-for="appointment in appointmentList" :key="appointment.id">
      <van-panel :title="appointment.name" :desc="appointment.description" :status="appointment.status === '0' ? '已预约' : '已完成'">
        <div v-if="appointment.status === '0' && appointment.type > '1'" class="panel-div">
          <div>
            课程开始时间：{{ appointment.courseStartDate | dataFormat }}
          </div>
          <div>
            课程结束时间：{{ appointment.courseEndDate | dataFormat }}
          </div>
        </div>
        <div v-if="appointment.status === '1' && appointment.type > '1'" class="panel-div">
          <div>
            课程开始时间：{{ appointment.courseStartDate | dataFormat }}
          </div>
          <div>
            课程结束时间：{{ appointment.courseEndDate | dataFormat }}
          </div>
          <div>
            预约核销时间：{{ appointment.updateDate | dataFormat }}
          </div>
        </div>
        <div v-if="appointment.status === '0' && appointment.type === '1'" class="panel-div">
          预约时间：{{ appointment.appointmentDate + ' ' + appointment.appointmentPeroid }}
        </div>
        <div v-if="appointment.status === '1' && appointment.type === '1'" class="panel-div">
          <div>
            预约时间：{{ appointment.appointmentDate + ' ' + appointment.appointmentPeroid }}
          </div>
          <div>
            核销时间：{{ appointment.updateDate | dataFormat }}
          </div>
        </div>
        <div v-if="appointment.status === '0'" slot="footer" style="text-align: right;">
          <van-button size="small" plain type="warning" @click="qrcode(appointment.id)">二维码</van-button>
          <van-button size="small" type="danger" style="margin-left: 15px;" @click="cancelAppointment(appointment.id)">取消预约</van-button>
        </div>
      </van-panel>
      <div style="height: 15px;" />
    </div>
  </div>
</template>
<script>
import moment from 'moment'
import { Dialog, Toast } from 'vant'

import { findAllAppointmentByOpenId, cancelAppointment } from '@/api/appointment'

export default {
  inject: ['reload'],
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      time: 1538707519000,
      appointmentList: [],
      show: false
    }
  },
  created() {
    this.findAllAppointmentByOpenId()
  },
  methods: {
    findAllAppointmentByOpenId() {
      findAllAppointmentByOpenId().then(response => {
        this.appointmentList = response.resultData.list
        console.log(response.resultData)
      })
    },
    cancelAppointment(id) {
      Dialog.confirm({
        title: '提示',
        message: '预约一旦取消将无法恢复，请确认'
      }).then(() => {
        cancelAppointment(id).then(response => {
          if (response.resultCode === '1') {
            // 调用api完成取消操作
            Toast('预约已取消')
            this.reload()
          } else {
            Toast('预约取消失败')
          }
        })
      }).catch(() => {
        Toast('操作已撤销')
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
    qrcode(id) {
      this.$router.push({
        path: '/qrcode',
        query: { id: id }
      })
    }
  }
}
</script>
<style>
.van-panel__content .panel-div {
  padding: 5px 15px;
  font-size: 12px;
  line-height: 1.2;
  color: #666;
}
.popup-div {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0,0,0,.7);
}
.qrcode {
  z-index: 2000;
  width: 250px;
  position: fixed;
  top: 50%;
  left: 50%;
  max-height: 100%;
  transform: translate3d(-50%, -50%, 0);
  -webkit-transform: translate3d(-50%, -50%, 0);
}
</style>
