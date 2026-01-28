import request from '@/utils/request'

// 查询è´¨æ£€æ ‡å‡†列表
export function listStandard(query) {
  return request({
    url: '/system/standard/list',
    method: 'get',
    params: query
  })
}

// 查询è´¨æ£€æ ‡å‡†详细
export function getStandard(standardId) {
  return request({
    url: '/system/standard/' + standardId,
    method: 'get'
  })
}

// 新增è´¨æ£€æ ‡å‡†
export function addStandard(data) {
  return request({
    url: '/system/standard',
    method: 'post',
    data: data
  })
}

// 修改è´¨æ£€æ ‡å‡†
export function updateStandard(data) {
  return request({
    url: '/system/standard',
    method: 'put',
    data: data
  })
}

// 删除è´¨æ£€æ ‡å‡†
export function delStandard(standardId) {
  return request({
    url: '/system/standard/' + standardId,
    method: 'delete'
  })
}
