<template>
  <div>
    <van-nav-bar
      title="卡券使用记录"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div v-if="flag">
      <div style="height: 15px;" />
      <div v-for="useLog in useLogList" :key="useLog.id">
        <van-panel :title="useLog.productName" :status="'核销方：' + useLog.writeOff">
          <div class="panel-div">使用时间: {{ useLog.createDate | dataFormat }}</div>
        </van-panel>
        <div style="height: 10px;" />
      </div>
    </div>
    <div v-else class="no-data-div">
      暂无数据
    </div>
  </div>
</template>
<script>
import moment from 'moment'
import { getCardUseLogList } from '@/api/card'

export default {
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      flag: false,
      useLogList: []
    }
  },
  created() {
    this.getCardUseLogList()
  },
  methods: {
    getCardUseLogList() {
      getCardUseLogList(this.$route.query.cardId).then(response => {
        const list = response.resultData.list
        if (list.length > 0) {
          this.flag = true
          this.useLogList = list
        }
      })
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
.van-panel__footer {
  text-align: right;
}
.no-data-div {
  padding: 20px;
  text-align: center;
  font-size: 14px;
  color: #999;
}
.panel-div {
  padding: 10px 15px;
  color: #666;
  font-size: 14px;
}
</style>
