import router from './router'
import { getToken, setToken } from '@/utils/auth' // 验权
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
// import { Dialog } from 'vant'

router.beforeEach((to, from, next) => {
  console.log(to)
  console.log(from)
  if (getToken()) { // 如果token存在，直接next
    next()
  } else { // 如果token不存，则去服务端取一个，存入cookie
    if (to.query.token !== null && to.query.token !== undefined && to.query.token !== '') {
      setToken(to.query.token)
      next()
    } else {
      window.location.href = 'http://fuhui.kaixindaka.com/mp/wechatUser/oauth2Wechat'
    }
    // getTokens('NA2i760YXSgfsiOlQl8z4ps5Zll73FfM').then(response => {
    //   if (response.resultCode === '1') {
    //     setToken(response.resultData.token)
    //     next()
    //   } else {
    //     Dialog.alert({
    //       message: response.resultMsg
    //     }).then(() => {
    //       next()
    //     })
    //   }
    // })
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
