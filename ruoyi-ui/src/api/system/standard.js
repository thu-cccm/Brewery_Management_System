import request from '@/utils/request'

// ??质检标准??
export function listStandard(query) {
  return request({
    url: '/system/standard/list',
    method: 'get',
    params: query
  })
}

// ??质检标准??
export function getStandard(standardId) {
  return request({
    url: '/system/standard/' + standardId,
    method: 'get'
  })
}

// ??质检标准
export function addStandard(data) {
  return request({
    url: '/system/standard',
    method: 'post',
    data: data
  })
}

// ??质检标准
export function updateStandard(data) {
  return request({
    url: '/system/standard',
    method: 'put',
    data: data
  })
}

// ??质检标准
export function delStandard(standardId) {
  return request({
    url: '/system/standard/' + standardId,
    method: 'delete'
  })
}
