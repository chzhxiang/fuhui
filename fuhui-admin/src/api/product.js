import request from '@/utils/request'

export function getCourseList() {
  return request({
    url: '/admin/adminProduct/getCourseList',
    method: 'post'
  })
}
