import request from '@/utils/request'

// ??批次原料??
export function listBatchmaterial(query) {
  return request({
    url: '/system/batchmaterial/list',
    method: 'get',
    params: query
  })
}

// ??批次原料??
export function getBatchmaterial(recordId) {
  return request({
    url: '/system/batchmaterial/' + recordId,
    method: 'get'
  })
}

// ??批次原料
export function addBatchmaterial(data) {
  return request({
    url: '/system/batchmaterial',
    method: 'post',
    data: data
  })
}

// ??批次原料
export function updateBatchmaterial(data) {
  return request({
    url: '/system/batchmaterial',
    method: 'put',
    data: data
  })
}

// ??批次原料
export function delBatchmaterial(recordId) {
  return request({
    url: '/system/batchmaterial/' + recordId,
    method: 'delete'
  })
}
