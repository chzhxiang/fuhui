import request from '@/utils/request'

export function getPointsList(phone, pageNum, pageSize) {
  return request({
    url: '/admin/adminPoints/getPointsList',
    method: 'post',
    data: {
      phone,
      pageNum,
      pageSize
    }
  })
}
