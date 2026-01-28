import request from '@/utils/request'

// 查询ç”Ÿäº§è®¡åˆ’列表
export function listPlan(query) {
  return request({
    url: '/system/plan/list',
    method: 'get',
    params: query
  })
}

// 查询ç”Ÿäº§è®¡åˆ’详细
export function getPlan(planId) {
  return request({
    url: '/system/plan/' + planId,
    method: 'get'
  })
}

// 新增ç”Ÿäº§è®¡åˆ’
export function addPlan(data) {
  return request({
    url: '/system/plan',
    method: 'post',
    data: data
  })
}

// 修改ç”Ÿäº§è®¡åˆ’
export function updatePlan(data) {
  return request({
    url: '/system/plan',
    method: 'put',
    data: data
  })
}

// 删除ç”Ÿäº§è®¡åˆ’
export function delPlan(planId) {
  return request({
    url: '/system/plan/' + planId,
    method: 'delete'
  })
}
