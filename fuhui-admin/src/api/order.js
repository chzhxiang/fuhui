import request from '@/utils/request'

export function getWechatOrderList(phone, payType, orderStatus, orderNo, transactionId, txOrderNo, pageNum, pageSize) {
  return request({
    url: '/admin/adminWechatOrder/getWechatOrderList',
    method: 'post',
    data: {
      phone,
      payType,
      orderStatus,
      orderNo,
      transactionId,
      txOrderNo,
      pageNum,
      pageSize
    }
  })
}
