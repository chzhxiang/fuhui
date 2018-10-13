import request from '@/utils/request'

export function getWechatList(phone, pageNum, pageSize) {
  return request({
    url: '/admin/adminWechatUser/getWechatUserList',
    method: 'post',
    data: {
      phone,
      pageNum,
      pageSize
    }
  })
}

export function delCarNumber(id) {
  return request({
    url: '/admin/adminWechatUser/delCarNumber',
    method: 'post',
    data: {
      id
    }
  })
}

export function delPhone(id) {
  return request({
    url: '/admin/adminWechatUser/delPhone',
    method: 'post',
    data: {
      id
    }
  })
}

export function addPoints(id, points) {
  return request({
    url: '/admin/adminWechatUser/addPoints',
    method: 'post',
    data: {
      id,
      points
    }
  })
}

export function getWechatUserListByPhone(phone, pageSize) {
  return request({
    url: '/admin/adminWechatUser/getWechatUserListByPhone',
    method: 'post',
    data: {
      phone,
      pageSize
    }
  })
}
