import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/admin',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/admin',
    component: Layout,
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/index'),
        meta: { title: '会员管理', icon: 'user' }
      }
    ]
  },

  {
    path: '/carNum',
    component: Layout,
    children: [
      {
        path: 'carNum',
        name: 'CarNum',
        component: () => import('@/views/carNum/index'),
        meta: { title: '车牌管理', icon: 'carNum' }
      }
    ]
  },

  {
    path: '/points',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Points',
        component: () => import('@/views/points/index'),
        meta: { title: '积分流水', icon: 'jifen' }
      }
    ]
  },

  {
    path: '/onlineCourses',
    component: Layout,
    children: [
      {
        path: 'onlineCourses',
        name: 'OnlineCourses',
        component: () => import('@/views/onlineCourses/index'),
        meta: { title: '课程管理', icon: 'course' }
      }
    ]
  },

  {
    path: '/order',
    component: Layout,
    children: [
      {
        path: 'order',
        name: 'Order',
        component: () => import('@/views/order/index'),
        meta: { title: '订单管理', icon: 'order' }
      }
    ]
  },

  {
    path: '/appointment',
    component: Layout,
    children: [
      {
        path: 'appointment',
        name: 'Appointment',
        component: () => import('@/views/appointment/index'),
        meta: { title: '预约管理', icon: 'appointment' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
