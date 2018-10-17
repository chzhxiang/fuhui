import request from '@/utils/request'

export function getCardList() {
  return request({
    url: '/mp/wechatCard/getCardList',
    method: 'post'
  })
}

export function getCardUseLogList(cardId) {
  return request({
    url: '/mp/wechatCard/getCardUseLogList',
    method: 'post',
    data: {
      cardId
    }
  })
}
