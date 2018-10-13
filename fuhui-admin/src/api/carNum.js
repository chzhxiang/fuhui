import request from '@/utils/request'

export function getCarNumListByPhone(phone) {
  return request({
    url: '/admin/adminCarNum/getCarNumListByPhone',
    method: 'post',
    data: {
      phone
    }
  })
}

export function delCarNum(id) {
  return request({
    url: '/admin/adminCarNum/delCarNum',
    method: 'post',
    data: {
      id
    }
  })
}
