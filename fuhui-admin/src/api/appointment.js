import request from '@/utils/request'

export function getAppointmentList(phone, flag, status, pageNum, pageSize) {
  return request({
    url: '/admin/adminAppointment/getAppointmentList',
    method: 'post',
    data: {
      phone,
      flag,
      status,
      pageNum,
      pageSize
    }
  })
}

export function cancelAppointment(id) {
  return request({
    url: '/admin/adminAppointment/cancelAppointment',
    method: 'post',
    data: {
      id
    }
  })
}
