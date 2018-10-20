import request from '@/utils/request'

export function getCourseListByCardType(cardType) {
  return request({
    url: '/mp/onlineCourse/getCourseListByCardType',
    method: 'post',
    data: {
      cardType
    }
  })
}
