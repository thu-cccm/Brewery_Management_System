import request from '@/utils/request'

// 查询è´¨æ£€è®°å½•列表
export function listQuality(query) {
  return request({
    url: '/system/quality/list',
    method: 'get',
    params: query
  })
}

// 查询è´¨æ£€è®°å½•详细
export function getQuality(recordId) {
  return request({
    url: '/system/quality/' + recordId,
    method: 'get'
  })
}

// 新增è´¨æ£€è®°å½•
export function addQuality(data) {
  return request({
    url: '/system/quality',
    method: 'post',
    data: data
  })
}

// 修改è´¨æ£€è®°å½•
export function updateQuality(data) {
  return request({
    url: '/system/quality',
    method: 'put',
    data: data
  })
}

// 删除è´¨æ£€è®°å½•
export function delQuality(recordId) {
  return request({
    url: '/system/quality/' + recordId,
    method: 'delete'
  })
}
