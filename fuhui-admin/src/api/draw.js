import request from '@/utils/request'

export function getWechatOrderList() {
  return request({
    url: '/admin/adminDraw/getAdminDrawList',
    method: 'post'
  })
}

export function saveAdminDraw(id, scale) {
  return request({
    url: '/admin/adminDraw/saveAdminDraw',
    method: 'post',
    data: {
      id,
      scale
    }
  })
}
