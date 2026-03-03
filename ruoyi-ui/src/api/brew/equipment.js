import request from '@/utils/request'

// 查询设备台账列表
export function listEquipment(query) {
  return request({
    url: '/brew/equipment/list',
    method: 'get',
    params: query
  })
}

// 查询设备台账详细
export function getEquipment(equipmentId) {
  return request({
    url: '/brew/equipment/' + equipmentId,
    method: 'get'
  })
}

// 新增设备台账
export function addEquipment(data) {
  return request({
    url: '/brew/equipment',
    method: 'post',
    data: data
  })
}

// 修改设备台账
export function updateEquipment(data) {
  return request({
    url: '/brew/equipment',
    method: 'put',
    data: data
  })
}

// 删除设备台账
export function delEquipment(equipmentId) {
  return request({
    url: '/brew/equipment/' + equipmentId,
    method: 'delete'
  })
}
