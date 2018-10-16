import request from '@/utils/request'

export function submitOrder(id, useNum) {
  return request({
    url: '/mp/wechatOrder/submitOrder',
    method: 'post',
    data: {
      id,
      useNum
    }
  })
}

export function getOrderList() {
  return request({
    url: '/mp/wechatOrder/getOrderList',
    method: 'post'
  })
}
