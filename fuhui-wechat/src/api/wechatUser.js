import request from '@/utils/request'

export function getTokens(key) {
  return request({
    url: '/mp/wechatUser/getToken',
    method: 'post',
    data: {
      key
    }
  })
}

export function getUserInfo() {
  return request({
    url: '/mp/wechatUser/getUserInfo',
    method: 'post'
  })
}

export function sendSMS(phone) {
  return request({
    url: '/mp/wechatUser/sendSMS',
    method: 'post',
    data: {
      phone
    }
  })
}

export function bindPhone(code, phone) {
  return request({
    url: '/mp/wechatUser/bindPhone',
    method: 'post',
    data: {
      code,
      phone
    }
  })
}

export function bindCarNum(carNum) {
  return request({
    url: '/mp/wechatUser/bindCarNum',
    method: 'post',
    data: {
      carNum
    }
  })
}
