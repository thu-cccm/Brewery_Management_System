import request from '@/utils/request'

export function getCostTrend(months) {
  return request({
    url: '/brew/stats/costTrend',
    method: 'get',
    params: { months }
  })
}

export function getMaterialWarningList() {
  return request({
    url: '/brew/stats/materialWarning',
    method: 'get'
  })
}

export function getProductionCompare(months) {
  return request({
    url: '/brew/stats/productionCompare',
    method: 'get',
    params: { months }
  })
}

export function getDashboardData() {
  return request({
    url: '/brew/stats/dashboard',
    method: 'get'
  })
}
