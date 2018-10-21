import request from '@/utils/request'

export function findBasketBallUsePeople(appointmentDate, appointmentPeroid) {
  return request({
    url: '/mp/wechatAppointment/findBasketBallUsePeople',
    method: 'post',
    data: {
      appointmentDate,
      appointmentPeroid
    }
  })
}

export function saveBasketBallAppointment(appointmentDate, appointmentPeroid, cardId) {
  return request({
    url: '/mp/wechatAppointment/saveBasketBallAppointment',
    method: 'post',
    data: {
      appointmentDate,
      appointmentPeroid,
      cardId
    }
  })
}

export function findOnlineUsePeople(courseId) {
  return request({
    url: '/mp/wechatAppointment/findOnlineUsePeople',
    method: 'post',
    data: {
      courseId
    }
  })
}

export function saveOnlineAppointment(courseId, cardId) {
  return request({
    url: '/mp/wechatAppointment/saveOnlineAppointment',
    method: 'post',
    data: {
      courseId,
      cardId
    }
  })
}

export function findAllAppointmentByOpenId() {
  return request({
    url: '/mp/wechatAppointment/findAllAppointmentByOpenId',
    method: 'post'
  })
}

export function cancelAppointment(appointmentId) {
  return request({
    url: '/mp/wechatAppointment/cancelAppointment',
    method: 'post',
    data: {
      appointmentId
    }
  })
}
