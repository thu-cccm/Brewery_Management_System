import request from '@/utils/request'

// 查询æˆæœ¬ç»Ÿè®¡列表
export function listStatistics(query) {
  return request({
    url: '/system/statistics/list',
    method: 'get',
    params: query
  })
}

// 查询æˆæœ¬ç»Ÿè®¡详细
export function getStatistics(statId) {
  return request({
    url: '/system/statistics/' + statId,
    method: 'get'
  })
}

// 新增æˆæœ¬ç»Ÿè®¡
export function addStatistics(data) {
  return request({
    url: '/system/statistics',
    method: 'post',
    data: data
  })
}

// 修改æˆæœ¬ç»Ÿè®¡
export function updateStatistics(data) {
  return request({
    url: '/system/statistics',
    method: 'put',
    data: data
  })
}

// 删除æˆæœ¬ç»Ÿè®¡
export function delStatistics(statId) {
  return request({
    url: '/system/statistics/' + statId,
    method: 'delete'
  })
}
