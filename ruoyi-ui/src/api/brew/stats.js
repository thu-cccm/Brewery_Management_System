import request from '@/utils/request'

// 获取全链路追溯数据
export function getTraceData(batchId) {
  return request({
    url: '/brew/trace/' + batchId,
    method: 'get'
  })
}

// 获取损耗分析统计
export function getYieldStats() {
  return request({
    url: '/brew/stats/yield',
    method: 'get'
  })
}

// 获取生产概览统计
export function getProductionStats() {
  return request({
    url: '/brew/stats/production',
    method: 'get'
  })
}

// 获取产量对比统计
export function getProductionCompare() {
  return request({
    url: '/brew/stats/productionCompare',
    method: 'get'
  })
}

// 获取首页仪表盘数据
export function getDashboardData() {
  return request({
    url: '/brew/stats/dashboard',
    method: 'get'
  })
}
