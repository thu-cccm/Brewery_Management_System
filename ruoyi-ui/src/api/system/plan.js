import request from '@/utils/request'

// ??生产计划??
export function listPlan(query) {
  return request({
    url: '/system/plan/list',
    method: 'get',
    params: query
  })
}

// ??生产计划??
export function getPlan(planId) {
  return request({
    url: '/system/plan/' + planId,
    method: 'get'
  })
}

// ??生产计划
export function addPlan(data) {
  return request({
    url: '/system/plan',
    method: 'post',
    data: data
  })
}

// ??生产计划
export function updatePlan(data) {
  return request({
    url: '/system/plan',
    method: 'put',
    data: data
  })
}

// ??生产计划
export function delPlan(planId) {
  return request({
    url: '/system/plan/' + planId,
    method: 'delete'
  })
}
