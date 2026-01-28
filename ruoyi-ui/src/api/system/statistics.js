import request from '@/utils/request'

// ???本统计??
export function listStatistics(query) {
  return request({
    url: '/system/statistics/list',
    method: 'get',
    params: query
  })
}

// ???本统计??
export function getStatistics(statId) {
  return request({
    url: '/system/statistics/' + statId,
    method: 'get'
  })
}

// ???本统计
export function addStatistics(data) {
  return request({
    url: '/system/statistics',
    method: 'post',
    data: data
  })
}

// ???本统计
export function updateStatistics(data) {
  return request({
    url: '/system/statistics',
    method: 'put',
    data: data
  })
}

// ???本统计
export function delStatistics(statId) {
  return request({
    url: '/system/statistics/' + statId,
    method: 'delete'
  })
}
