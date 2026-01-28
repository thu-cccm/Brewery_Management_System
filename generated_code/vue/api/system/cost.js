import request from '@/utils/request'

// 查询æ‰¹æ¬¡æˆæœ¬列表
export function listCost(query) {
  return request({
    url: '/system/cost/list',
    method: 'get',
    params: query
  })
}

// 查询æ‰¹æ¬¡æˆæœ¬详细
export function getCost(costId) {
  return request({
    url: '/system/cost/' + costId,
    method: 'get'
  })
}

// 新增æ‰¹æ¬¡æˆæœ¬
export function addCost(data) {
  return request({
    url: '/system/cost',
    method: 'post',
    data: data
  })
}

// 修改æ‰¹æ¬¡æˆæœ¬
export function updateCost(data) {
  return request({
    url: '/system/cost',
    method: 'put',
    data: data
  })
}

// 删除æ‰¹æ¬¡æˆæœ¬
export function delCost(costId) {
  return request({
    url: '/system/cost/' + costId,
    method: 'delete'
  })
}
