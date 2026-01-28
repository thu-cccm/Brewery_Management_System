import request from '@/utils/request'

// ??生产过程??
export function listProcess(query) {
  return request({
    url: '/system/process/list',
    method: 'get',
    params: query
  })
}

// ??生产过程??
export function getProcess(processId) {
  return request({
    url: '/system/process/' + processId,
    method: 'get'
  })
}

// ??生产过程
export function addProcess(data) {
  return request({
    url: '/system/process',
    method: 'post',
    data: data
  })
}

// ??生产过程
export function updateProcess(data) {
  return request({
    url: '/system/process',
    method: 'put',
    data: data
  })
}

// ??生产过程
export function delProcess(processId) {
  return request({
    url: '/system/process/' + processId,
    method: 'delete'
  })
}
