import request from '@/utils/request'

// 查询维保记录列表
export function listMaintenance(query) {
  return request({
    url: '/brew/maintenance/list',
    method: 'get',
    params: query
  })
}

// 查询维保记录详细
export function getMaintenance(maintenanceId) {
  return request({
    url: '/brew/maintenance/' + maintenanceId,
    method: 'get'
  })
}

// 新增维保记录
export function addMaintenance(data) {
  return request({
    url: '/brew/maintenance',
    method: 'post',
    data: data
  })
}

// 修改维保记录
export function updateMaintenance(data) {
  return request({
    url: '/brew/maintenance',
    method: 'put',
    data: data
  })
}

// 删除维保记录
export function delMaintenance(maintenanceId) {
  return request({
    url: '/brew/maintenance/' + maintenanceId,
    method: 'delete'
  })
}
