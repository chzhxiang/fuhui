import request from '@/utils/request'

export function submitOrder(id) {
  return request({
    url: '/mp/wechatOrder/submitOrder',
    method: 'post',
    data: {
      id
    }
  })
}

export function getOrderList() {
  return request({
    url: '/mp/wechatOrder/getOrderList',
    method: 'post'
  })
}
