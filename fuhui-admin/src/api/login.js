import request from '@/utils/request'

export function checkUserName(username) {
  return request({
    url: '/admin/adminUser/checkUserName',
    method: 'post',
    data: {
      username
    }
  })
}

export function login(username, password) {
  return request({
    url: '/admin/adminUser/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/adminUser/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/admin/adminUser/logout',
    method: 'post'
  })
}
