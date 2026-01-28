import request from '@/utils/request'

// 查询æˆæœ¬é¡¹列表
export function listCostitem(query) {
  return request({
    url: '/system/costitem/list',
    method: 'get',
    params: query
  })
}

// 查询æˆæœ¬é¡¹详细
export function getCostitem(itemId) {
  return request({
    url: '/system/costitem/' + itemId,
    method: 'get'
  })
}

// 新增æˆæœ¬é¡¹
export function addCostitem(data) {
  return request({
    url: '/system/costitem',
    method: 'post',
    data: data
  })
}

// 修改æˆæœ¬é¡¹
export function updateCostitem(data) {
  return request({
    url: '/system/costitem',
    method: 'put',
    data: data
  })
}

// 删除æˆæœ¬é¡¹
export function delCostitem(itemId) {
  return request({
    url: '/system/costitem/' + itemId,
    method: 'delete'
  })
}
