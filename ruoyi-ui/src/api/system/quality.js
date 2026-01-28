import request from '@/utils/request'

// ??质检记录??
export function listQuality(query) {
  return request({
    url: '/system/quality/list',
    method: 'get',
    params: query
  })
}

// ??质检记录??
export function getQuality(recordId) {
  return request({
    url: '/system/quality/' + recordId,
    method: 'get'
  })
}

// ??质检记录
export function addQuality(data) {
  return request({
    url: '/system/quality',
    method: 'post',
    data: data
  })
}

// ??质检记录
export function updateQuality(data) {
  return request({
    url: '/system/quality',
    method: 'put',
    data: data
  })
}

// ??质检记录
export function delQuality(recordId) {
  return request({
    url: '/system/quality/' + recordId,
    method: 'delete'
  })
}
