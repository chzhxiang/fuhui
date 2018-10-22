import request from '@/utils/request'

export function saveOnlineCourse(id, address, description, name, productId, num, startDate, endDate, flag) {
  return request({
    url: '/admin/onlineCourses/saveOnlineCourse',
    method: 'post',
    data: {
      id,
      address,
      description,
      name,
      productId,
      num,
      startDate,
      endDate,
      flag
    }
  })
}

export function getOnlineCoursesList() {
  return request({
    url: '/admin/onlineCourses/getOnlineCoursesList',
    method: 'post'
  })
}

export function delOnlineCoursesById(id) {
  return request({
    url: '/admin/onlineCourses/delOnlineCoursesById',
    method: 'post',
    data: {
      id
    }
  })
}

export function getOnlineCourseById(id) {
  return request({
    url: '/admin/onlineCourses/getOnlineCourseById',
    method: 'post',
    data: {
      id
    }
  })
}
