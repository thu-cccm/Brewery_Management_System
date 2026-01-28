import request from '@/utils/request'

// 查询åº“å­˜å˜åŠ¨列表
export function listStock(query) {
  return request({
    url: '/system/stock/list',
    method: 'get',
    params: query
  })
}

// 查询åº“å­˜å˜åŠ¨详细
export function getStock(recordId) {
  return request({
    url: '/system/stock/' + recordId,
    method: 'get'
  })
}

// 新增åº“å­˜å˜åŠ¨
export function addStock(data) {
  return request({
    url: '/system/stock',
    method: 'post',
    data: data
  })
}

// 修改åº“å­˜å˜åŠ¨
export function updateStock(data) {
  return request({
    url: '/system/stock',
    method: 'put',
    data: data
  })
}

// 删除åº“å­˜å˜åŠ¨
export function delStock(recordId) {
  return request({
    url: '/system/stock/' + recordId,
    method: 'delete'
  })
}
