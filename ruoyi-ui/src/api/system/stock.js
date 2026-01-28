import request from '@/utils/request'

// ??库存?动??
export function listStock(query) {
  return request({
    url: '/system/stock/list',
    method: 'get',
    params: query
  })
}

// ??库存?动??
export function getStock(recordId) {
  return request({
    url: '/system/stock/' + recordId,
    method: 'get'
  })
}

// ??库存?动
export function addStock(data) {
  return request({
    url: '/system/stock',
    method: 'post',
    data: data
  })
}

// ??库存?动
export function updateStock(data) {
  return request({
    url: '/system/stock',
    method: 'put',
    data: data
  })
}

// ??库存?动
export function delStock(recordId) {
  return request({
    url: '/system/stock/' + recordId,
    method: 'delete'
  })
}
