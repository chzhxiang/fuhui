import request from '@/utils/request'

export function getCardList() {
  return request({
    url: '/mp/wechatCard/getCardList',
    method: 'post'
  })
}
