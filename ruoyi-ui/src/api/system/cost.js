import request from '@/utils/request'

// ??批次?本??
export function listCost(query) {
  return request({
    url: '/system/cost/list',
    method: 'get',
    params: query
  })
}

// ??批次?本??
export function getCost(costId) {
  return request({
    url: '/system/cost/' + costId,
    method: 'get'
  })
}

// ??批次?本
export function addCost(data) {
  return request({
    url: '/system/cost',
    method: 'post',
    data: data
  })
}

// ??批次?本
export function updateCost(data) {
  return request({
    url: '/system/cost',
    method: 'put',
    data: data
  })
}

// ??批次?本
export function delCost(costId) {
  return request({
    url: '/system/cost/' + costId,
    method: 'delete'
  })
}
