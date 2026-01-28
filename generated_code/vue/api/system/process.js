import request from '@/utils/request'

// 查询ç”Ÿäº§è¿‡ç¨‹列表
export function listProcess(query) {
  return request({
    url: '/system/process/list',
    method: 'get',
    params: query
  })
}

// 查询ç”Ÿäº§è¿‡ç¨‹详细
export function getProcess(processId) {
  return request({
    url: '/system/process/' + processId,
    method: 'get'
  })
}

// 新增ç”Ÿäº§è¿‡ç¨‹
export function addProcess(data) {
  return request({
    url: '/system/process',
    method: 'post',
    data: data
  })
}

// 修改ç”Ÿäº§è¿‡ç¨‹
export function updateProcess(data) {
  return request({
    url: '/system/process',
    method: 'put',
    data: data
  })
}

// 删除ç”Ÿäº§è¿‡ç¨‹
export function delProcess(processId) {
  return request({
    url: '/system/process/' + processId,
    method: 'delete'
  })
}
