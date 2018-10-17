<template>
  <div>
    <div v-if="flag">
      <div v-for="card in useList" :key="card.id">
        <van-panel :title="card.productName" :desc="card.desc" status="未使用">
          <div slot="footer">
            <van-button size="small" plain type="warning">使用记录</van-button>
            <van-button size="small" type="danger" style="margin-left: 10px;">去使用</van-button>
          </div>
        </van-panel>
        <div style="height: 10px;" />
      </div>
      <div v-for="card in unList" :key="card.id">
        <van-panel :title="card.productName" :desc="card.desc" status="已使用">
          <div class="panel-div">使用时间: {{ card.updateDate | dataFormat }}</div>
        </van-panel>
        <div style="height: 10px;" />
      </div>
    </div>
    <div v-else class="no-data-div">
      暂无数据
    </div>
    <div style="height: 50px;" />
    <van-tabbar v-model="active">
      <van-tabbar-item icon="shop-collect" to="shop">商城</van-tabbar-item>
      <van-tabbar-item icon="setting" to="introduction">走进富荟</van-tabbar-item>
      <van-tabbar-item icon="credit-pay" to="card">卡券</van-tabbar-item>
      <van-tabbar-item icon="contact" to="home">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
import moment from 'moment'
import { getCardList } from '@/api/card'

export default {
  filters: {
    dataFormat: function(el) {
      return moment(el).format('YYYY-MM-DD HH:mm:ss')
    }
  },
  data() {
    return {
      active: 2,
      useList: null,
      unList: null,
      flag: false
    }
  },
  created() {
    this.getCardList()
  },
  methods: {
    getCardList() {
      getCardList().then(response => {
        // 初始化数据
        if (response.resultData !== null) {
          this.flag = true
          const list = response.resultData.list
          console.log(response.resultData)
          const useList = []
          const unList = []
          for (const index in list) {
            list[index].desc = '剩余使用次数：' + list[index].useNum + ' 次'
            if (list[index].status === '1') {
              unList.push(list[index])
            } else {
              useList.push(list[index])
            }
          }
          this.useList = useList
          this.unList = unList
        }
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
