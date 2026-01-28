import request from '@/utils/request'

// ??生产批次??
export function listBatch(query) {
  return request({
    url: '/system/batch/list',
    method: 'get',
    params: query
  })
}

// ??生产批次??
export function getBatch(batchId) {
  return request({
    url: '/system/batch/' + batchId,
    method: 'get'
  })
}

// ??生产批次
export function addBatch(data) {
  return request({
    url: '/system/batch',
    method: 'post',
    data: data
  })
}

// ??生产批次
export function updateBatch(data) {
  return request({
    url: '/system/batch',
    method: 'put',
    data: data
  })
}

// ??生产批次
export function delBatch(batchId) {
  return request({
    url: '/system/batch/' + batchId,
    method: 'delete'
  })
}
