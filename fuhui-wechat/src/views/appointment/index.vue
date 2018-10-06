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
      <van-panel :title="appointment.titile" :desc="appointment.desc" :status="appointment.status === '1' ? '已预约' : '已完成'">
        <div v-if="appointment.status === '1'" class="panel-div">
          预约时间：{{ appointment.appointmentDate | dataFormat }}
        </div>
        <div v-else class="panel-div">完成时间：{{ appointment.appointmentDate | dataFormat }}</div>
        <div v-if="appointment.status === '1'" slot="footer" style="text-align: right;">
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

export default {
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      time: 1538707519000,
      appointmentList: [{
        id: '49495c72-c867-11e8-bb15-00163e0a9c22',
        titile: '线上课程',
        desc: '线上课程描述，可没有',
        status: '1',
        appointmentDate: 1538707519000
      }, {
        id: '50bcdef6-c867-11e8-bb15-00163e0a9c22',
        titile: '篮球场',
        desc: '篮球场描述，可没有',
        status: '1',
        appointmentDate: 1538707519000
      }, {
        id: '3',
        titile: '线上课程',
        desc: '线上课程描述，可没有',
        status: '2',
        appointmentDate: 1538707519000
      }],
      show: false
    }
  },
  methods: {
    cancelAppointment(id) {
      Dialog.confirm({
        title: '提示',
        message: '预约一旦取消将无法恢复，请确认'
      }).then(() => {
        // 调用api完成取消操作
        Toast('预约已取消')
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
