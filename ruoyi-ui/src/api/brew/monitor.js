import request from '@/utils/request'

// 查询生产过程监控列表
export function listMonitor(query) {
  return request({
    url: '/brew/monitor/list',
    method: 'get',
    params: query
  })
}

// 查询生产过程监控详细
export function getMonitor(monitorId) {
  return request({
    url: '/brew/monitor/' + monitorId,
    method: 'get'
  })
}

// 新增生产过程监控
export function addMonitor(data) {
  return request({
    url: '/brew/monitor',
    method: 'post',
    data: data
  })
}

// 修改生产过程监控
export function updateMonitor(data) {
  return request({
    url: '/brew/monitor',
    method: 'put',
    data: data
  })
}

// 删除生产过程监控
export function delMonitor(monitorId) {
  return request({
    url: '/brew/monitor/' + monitorId,
    method: 'delete'
  })
}
