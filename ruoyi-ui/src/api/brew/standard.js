import request from '@/utils/request'

// 查询工艺参数标准列表
export function listStandard(query) {
  return request({
    url: '/brew/standard/list',
    method: 'get',
    params: query
  })
}

// 查询工艺参数标准详细
export function getStandard(standardId) {
  return request({
    url: '/brew/standard/' + standardId,
    method: 'get'
  })
}

// 新增工艺参数标准
export function addStandard(data) {
  return request({
    url: '/brew/standard',
    method: 'post',
    data: data
  })
}

// 修改工艺参数标准
export function updateStandard(data) {
  return request({
    url: '/brew/standard',
    method: 'put',
    data: data
  })
}

// 删除工艺参数标准
export function delStandard(standardId) {
  return request({
    url: '/brew/standard/' + standardId,
    method: 'delete'
  })
}
