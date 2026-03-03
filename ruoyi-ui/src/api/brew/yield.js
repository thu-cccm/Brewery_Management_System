import request from '@/utils/request'

// 查询批次损耗分析列表
export function listYield(query) {
  return request({
    url: '/brew/yield/list',
    method: 'get',
    params: query
  })
}

// 查询批次损耗分析详细
export function getYield(yieldId) {
  return request({
    url: '/brew/yield/' + yieldId,
    method: 'get'
  })
}

// 新增批次损耗分析
export function addYield(data) {
  return request({
    url: '/brew/yield',
    method: 'post',
    data: data
  })
}

// 修改批次损耗分析
export function updateYield(data) {
  return request({
    url: '/brew/yield',
    method: 'put',
    data: data
  })
}

// 删除批次损耗分析
export function delYield(yieldId) {
  return request({
    url: '/brew/yield/' + yieldId,
    method: 'delete'
  })
}
